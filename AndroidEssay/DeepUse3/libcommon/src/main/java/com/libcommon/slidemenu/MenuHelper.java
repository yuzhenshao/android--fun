package com.libcommon.slidemenu;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;

import com.libcommon.R;


public class MenuHelper implements RecyclerView.OnItemTouchListener {
    private static final float DEFAULT_SCROLL_THRESHOLD_RATIO = 1 / 3f;
    private static final int DEFAULT_VELOCITY_THRESHOLD = 200; /* dp */
    private static final float DEFAULT_ANIMATE_AVERAGE_SPEED = 0.5f; /* dp/s */

    private final Context context;
    private boolean isIntercepted;
    private final ViewConfiguration viewConfig;
    private final RecyclerView recyclerView;
    private final MenuEnableDecider menuEnableDecider;
    private View lastTouchView;
    private int lastTouchedPosition = -1;
    private int downX;
    private int downY;
    private int lastX = -1;
    private boolean mDragging;
    /* px/s */
    private final float animateAverageSpeed;
    private VelocityTracker velocityTracker;
    private final float scrollThresholdRatio = DEFAULT_SCROLL_THRESHOLD_RATIO;
    private final float velocityThreshold;

    public static void attach(@NonNull RecyclerView recyclerView, @NonNull MenuEnableDecider menuEnableDecider) {
        new MenuHelper(recyclerView, menuEnableDecider);
    }

    public MenuHelper(@NonNull RecyclerView recyclerView, @NonNull MenuEnableDecider menuEnableDecider) {
        this.recyclerView = recyclerView;
        this.menuEnableDecider = menuEnableDecider;
        recyclerView.addOnItemTouchListener(this);
        context = recyclerView.getContext();
        viewConfig = ViewConfiguration.get(context);
        float density = context.getResources().getDisplayMetrics().density;
        animateAverageSpeed = DEFAULT_ANIMATE_AVERAGE_SPEED * density;
        velocityThreshold = DEFAULT_VELOCITY_THRESHOLD * density;
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent event) {
        if (event.getPointerCount() > 1)
            return true;
        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                lastX = downX = x;
                downY = y;
                isIntercepted = false;
                RecyclerView.ViewHolder vh = findViewHolder(event);
                if (vh != null) {
                    int touchPosition = vh.getAdapterPosition();
                    if (touchPosition != lastTouchedPosition && lastTouchView != null) {
                        closeMenu(lastTouchView);
                    }
                    lastTouchView = vh.itemView;
                    lastTouchedPosition = touchPosition;
                } else {
                    if(lastTouchView != null) {
                        closeMenu(lastTouchView);
                    }
                    lastTouchView = null;
                    lastTouchedPosition = -1;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                isIntercepted = tryIntercept(x, y);
                recyclerView.getParent().requestDisallowInterceptTouchEvent(isIntercepted);
                break;
            case MotionEvent.ACTION_UP:
                isIntercepted = tryIntercept(x, y);
                if (!mDragging && lastTouchView != null) {
                    closeMenu(lastTouchView);
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                isIntercepted = tryIntercept(x, y);
                break;
        }
        return isIntercepted;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent event) {
        if(lastTouchView == null) {
            return;
        }
        if (velocityTracker == null) {
            velocityTracker = VelocityTracker.obtain();
        }
        velocityTracker.addMovement(event);
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                lastX = (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                if (!mDragging && tryIntercept((int) event.getX(), (int) event.getY())) {
                    mDragging = true;
                }
                if (mDragging) {
                    tryOpenMenu(lastTouchView, event);
                    lastX = (int) event.getX();
                }
                break;
            case MotionEvent.ACTION_UP:
                mDragging = false;
                tryOpenMenu(lastTouchView, event);
                if (Math.abs(downX - event.getX()) > viewConfig.getScaledTouchSlop()
                        || Math.abs(downY - event.getY()) > viewConfig.getScaledTouchSlop()) {
                    event.setAction(MotionEvent.ACTION_CANCEL);
                }
                velocityTracker.clear();
                velocityTracker.recycle();
                velocityTracker = null;
                break;
            case MotionEvent.ACTION_CANCEL:
                mDragging = false;
                if (lastTouchView != null) {
                    closeMenu(lastTouchView);
                }
                break;
        }
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        if (!disallowIntercept) {
            return;
        }
        if (lastTouchView != null) {
            closeMenu(lastTouchView);
        }
    }

    private boolean tryIntercept(int x, int y) {
        int disX = downX - x;
        int disY = downY - y;
        return (Math.abs(disX) > viewConfig.getScaledTouchSlop() && Math.abs(disX) > Math.abs(disY));
    }

    private RecyclerView.ViewHolder findViewHolder(MotionEvent event) {
        View child = findChildView(event);
        if (child == null) {
            return null;
        }
        return recyclerView.getChildViewHolder(child);
    }

    private View findChildView(MotionEvent event) {
        final float x = event.getX();
        final float y = event.getY();
        return recyclerView.findChildViewUnder(x, y);
    }

    private void tryOpenMenu(View itemView, MotionEvent event) {
        if (itemView == null) {
            return;
        }
        int menuWidth = getMenuWidth(itemView);
        velocityTracker.computeCurrentVelocity(1000);
        float velocity = velocityTracker.getXVelocity();
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if (velocity > 0 && itemView.getScrollX() > 0) {
                    scroll(itemView, event, menuWidth);
                } else if (enable(itemView)
                        && velocity < 0
                        && itemView.getScrollX() < menuWidth) {
                    scroll(itemView, event, menuWidth);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (Math.abs(velocity) > velocityThreshold) {
                    if (velocity > 0) {
                        closeMenu(itemView);
                    } else if (enable(itemView)) {
                        openMenu(itemView);
                    }
                } else {
                    if (itemView.getScrollX() >= scrollThresholdRatio * menuWidth) {
                        openMenu(itemView);
                    } else {
                        closeMenu(itemView);
                    }
                }
                break;
        }
    }

    private void scroll(View itemView, MotionEvent event, int menuWidth) {
        int diff = (int) (lastX - event.getX());
        int to = diff + itemView.getScrollX();
        to = to > 0 ? to : 0;
        to = to < menuWidth ? to : menuWidth;
        itemView.scrollTo(to, 0);
    }

    private int getMenuWidth(View itemView) {
        View menu = itemView.findViewById(R.id.slide_menu_menu);
        if (menu == null) {
            return 0;
        }
        return menu.getMeasuredWidth();
    }

    private void closeMenu(View itemView) {
        int scrollX = itemView.getScrollX();
        if (scrollX > 0) {
            ObjectAnimator animator = ObjectAnimator.ofInt(itemView, "scrollX", scrollX, 0);
            animator.setInterpolator(new AccelerateInterpolator());
            animator.setDuration((long) Math.abs(scrollX / animateAverageSpeed));
            animator.start();
        }
    }

    private void openMenu(View itemView) {
        int menuWidth = getMenuWidth(itemView);
        int remaining = menuWidth - itemView.getScrollX();
        if (remaining > 0) {
            long duration = (long) (remaining / animateAverageSpeed);
            ObjectAnimator animator = ObjectAnimator.ofInt(itemView, "scrollX", itemView.getScrollX(), itemView.getScrollX() + remaining);
            animator.setInterpolator(new AccelerateInterpolator());
            animator.setDuration(duration);
            animator.start();
        }
    }

    private boolean enable(View itemView) {
        RecyclerView.ViewHolder viewHolder = recyclerView.getChildViewHolder(itemView);
        int position = recyclerView.getChildAdapterPosition(itemView);
        if (position == -1 || !MenuAdapterHelper.isNormalView(viewHolder) || position >= recyclerView.getAdapter().getItemCount()) {
            return false;
        }
        return menuEnableDecider != null && menuEnableDecider.enable(position);
    }

    public interface MenuEnableDecider {
        boolean enable(int position);
    }
}
