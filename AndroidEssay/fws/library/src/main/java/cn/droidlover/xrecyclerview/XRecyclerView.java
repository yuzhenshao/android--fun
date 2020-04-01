package cn.droidlover.xrecyclerview;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

import java.util.Collections;
import java.util.List;

import cn.droidlover.xrecyclerview.divider.HorizontalDividerItemDecoration;
import cn.droidlover.xrecyclerview.divider.VerticalDividerItemDecoration;

/**
 * Created by wanglei on 2016/10/30.
 */

public class XRecyclerView extends RecyclerView {

    private float xFactor = 1.0f;
    private float yFactor = 1.0f;

    LayoutManagerType layoutManagerType;        //LayoutManager类型
    private int[] lastScrollPositions;      //瀑布流位置存储
    private int[] firstScrollPositions;

    LoadMoreUIHandler loadMoreUIHandler;
    View loadMoreView;      //加载更多控件

    private boolean loadMore = false;
    private int totalPage = 1;
    private int currentPage = 1;
    private boolean isRefresh = false;
    private boolean isRefreshEnabled = true;  //是否可刷新
    private int lastVelocityY = 0;

    XRecyclerAdapter adapter;

    StateCallback stateCallback;
    OnRefreshAndLoadMoreListener onRefreshAndLoadMoreListener;

    public static final int LOAD_MORE_ITEM_SLOP = 2;


    private static final int INVALID_POSITION = -1; // 触摸到的点不在子View范围内
    private static final int INVALID_CHILD_WIDTH = -1;  // 子ItemView不含两个子View
    private static final int SNAP_VELOCITY = 600;   // 最小滑动速度

    private VelocityTracker mVelocityTracker;   // 速度追踪器
    private int mTouchSlop; // 认为是滑动的最小距离（一般由系统提供）
    private Rect mTouchFrame;   // 子View所在的矩形范围
    private Scroller mScroller;
    private float mLastX;   // 滑动过程中记录上次触碰点X
    private float mFirstX, mFirstY; // 首次触碰范围
    private boolean mIsSlide;   // 是否滑动子View
    private ViewGroup mFlingView;   // 触碰的子View
    private int mPosition;  // 触碰的view的位置
    private int mMenuViewWidth;    // 菜单按钮宽度


    public XRecyclerView(Context context) {
        this(context, null);
    }

    public XRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setUpView();
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mScroller = new Scroller(context);
    }

    private void setUpView() {
        addOnScrollListener(processMoreListener);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (adapter == null) return;

        if (!(adapter instanceof XRecyclerAdapter)) {
            adapter = new XRecyclerAdapter(adapter);
        }

        super.setAdapter(adapter);

        if (adapter.getItemCount() > 0) {
            if (getStateCallback() != null) getStateCallback().notifyContent();
        }

        final XRecyclerAdapter finalAdapter = (XRecyclerAdapter) adapter;
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                update();
            }

            @Override
            public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
                super.onItemRangeMoved(fromPosition, toPosition, itemCount);
                update();
            }

            @Override
            public void onItemRangeRemoved(int positionStart, int itemCount) {
                super.onItemRangeRemoved(positionStart, itemCount);
                update();
            }

            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                update();
            }

            @Override
            public void onItemRangeChanged(int positionStart, int itemCount) {
                super.onItemRangeChanged(positionStart, itemCount);
                update();
            }

            private void update() {
                int dataCount = finalAdapter.getDataCount();
                if (dataCount > 0) {
                    if (isRefresh) {
                        isRefresh = false;
                    }
                    if (loadMore) {
                        loadMoreCompleted();
                    }
                    if (getStateCallback() != null) getStateCallback().notifyContent();
                } else {
                    if (finalAdapter.getHeaderSize() > 0 || finalAdapter.getFooterSize() > 0) {
                        if (loadMoreView != null) loadMoreView.setVisibility(GONE);
                    } else {
                        if (getStateCallback() != null) getStateCallback().notifyEmpty();
                    }

                }

                if (getStateCallback() != null) getStateCallback().refreshState(false);
            }
        });

        this.adapter = (XRecyclerAdapter) adapter;

    }

    public XRecyclerView stateCallback(StateCallback stateCallback) {
        this.stateCallback = stateCallback;
        return this;
    }

    public StateCallback getStateCallback() {
        return stateCallback;
    }

    @Override
    public XRecyclerAdapter getAdapter() {
        return adapter;
    }

    public void onRefresh() {
        currentPage = 1;
        isRefresh = true;
        if (getOnRefreshAndLoadMoreListener() != null) {
            getOnRefreshAndLoadMoreListener().onRefresh();
        }
    }

    @Override
    public void setLayoutManager(LayoutManager layout) {
        if (layout == null) {
            throw new IllegalArgumentException("LayoutManager can not be null.");
        }
        super.setLayoutManager(layout);

        if (layout instanceof GridLayoutManager) {
            int spanCount = ((GridLayoutManager) layout).getSpanCount();
            setSpanLookUp(layout, spanCount);
        }

        if (layout instanceof StaggeredGridLayoutManager) {
            int spanCount = ((StaggeredGridLayoutManager) layout).getSpanCount();
            setSpanLookUp(layout, spanCount);
        }
    }

    @Override
    public boolean fling(int velocityX, int velocityY) {
        lastVelocityY = velocityY;
        return super.fling((int) (velocityX * xFactor), (int) (velocityY * yFactor));
    }

    public XRecyclerView xFactor(float xFactor) {
        this.xFactor = xFactor;
        return this;
    }

    public XRecyclerView yFactor(float yFactor) {
        this.yFactor = yFactor;
        return this;
    }


    public XRecyclerView verticalLayoutManager(Context context) {
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        setLayoutManager(manager);
        return this;
    }

    public XRecyclerView horizontalLayoutManager(Context context) {
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        setLayoutManager(manager);
        return this;
    }

    public XRecyclerView gridLayoutManager(Context context, int spanCount) {
        GridLayoutManager manager = new GridLayoutManager(context, spanCount);
        setLayoutManager(manager);
        return this;
    }

    public XRecyclerView verticalStaggeredLayoutManager(int spanCount) {
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL);
        setLayoutManager(manager);
        return this;
    }

    public XRecyclerView horizontalStaggeredLayoutManager(int spanCount) {
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL);
        setLayoutManager(manager);
        return this;
    }

    public int getLastVisibleItemPosition() {
        return getLastVisibleItemPosition(getLayoutManager());
    }

    private int getLastVisibleItemPosition(RecyclerView.LayoutManager layoutManager) {
        int lastVisibleItemPosition = -1;
        if (layoutManagerType == null) {
            if (layoutManager instanceof LinearLayoutManager) {
                layoutManagerType = LayoutManagerType.LINEAR;
            } else if (layoutManager instanceof GridLayoutManager) {
                layoutManagerType = LayoutManagerType.GRID;
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                layoutManagerType = LayoutManagerType.STAGGERED_GRID;
            } else {
                throw new RuntimeException("Unsupported LayoutManager used. Valid ones are LinearLayoutManager, GridLayoutManager and StaggeredGridLayoutManager");
            }
        }

        switch (layoutManagerType) {
            case LINEAR:
                lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                break;
            case GRID:
                lastVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
                break;
            case STAGGERED_GRID:
                StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                if (lastScrollPositions == null)
                    lastScrollPositions = new int[staggeredGridLayoutManager.getSpanCount()];

                staggeredGridLayoutManager.findLastVisibleItemPositions(lastScrollPositions);
                lastVisibleItemPosition = findMax(lastScrollPositions);
                break;
        }
        return lastVisibleItemPosition;
    }


    private int getFirstVisibleItemPosition(RecyclerView.LayoutManager layoutManager) {
        int firstVisibleItemPosition = -1;
        if (layoutManagerType == null) {
            if (layoutManager instanceof LinearLayoutManager) {
                layoutManagerType = LayoutManagerType.LINEAR;
            } else if (layoutManager instanceof GridLayoutManager) {
                layoutManagerType = LayoutManagerType.GRID;
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                layoutManagerType = LayoutManagerType.STAGGERED_GRID;
            } else {
                throw new RuntimeException("Unsupported LayoutManager used. Valid ones are LinearLayoutManager, GridLayoutManager and StaggeredGridLayoutManager");
            }
        }

        switch (layoutManagerType) {
            case LINEAR:
                firstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                break;
            case GRID:
                firstVisibleItemPosition = ((GridLayoutManager) layoutManager).findFirstVisibleItemPosition();
                break;
            case STAGGERED_GRID:
                StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                if (lastScrollPositions == null)
                    lastScrollPositions = new int[staggeredGridLayoutManager.getSpanCount()];

                staggeredGridLayoutManager.findLastVisibleItemPositions(firstScrollPositions);
                firstVisibleItemPosition = findMin(firstScrollPositions);
                break;
        }
        return firstVisibleItemPosition;
    }


    private int findMax(int[] lastPositions) {
        int max = Integer.MIN_VALUE;
        for (int value : lastPositions) {
            if (value > max)
                max = value;
        }
        return max;
    }

    private int findMin(int[] positions) {
        int min = Integer.MIN_VALUE;
        for (int value : positions) {
            if (value < min)
                min = value;
        }
        return min;
    }


    private void setSpanLookUp(RecyclerView.LayoutManager layoutManager, final int spanCount) {
        if (layoutManager instanceof GridLayoutManager) {
            ((GridLayoutManager) layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (adapter != null) {
                        return adapter.isHeaderOrFooter(position) ? spanCount : 1;
                    }
                    return GridLayoutManager.DEFAULT_SPAN_COUNT;
                }
            });
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            ((StaggeredGridLayoutManager) layoutManager).setSpanCount(spanCount);
        }

    }

    /**
     * 设置SpanSizeLookup
     *
     * @param layoutManager
     * @param lookup
     */
    public void setGridSpanLookUp(GridLayoutManager layoutManager, final GridLayoutManager.SpanSizeLookup lookup) {
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (adapter != null) {
                    if (adapter.isHeaderOrFooter(position)) return 1;

                    return lookup.getSpanSize(position);
                }
                return GridLayoutManager.DEFAULT_SPAN_COUNT;
            }
        });
    }

    public boolean addHeaderView(int position, View view) {
        boolean result = false;
        if (view == null) {
            return result;
        }
        if (adapter != null) {
            result = adapter.addHeadView(position, view);
        }
        return result;
    }

    public boolean addHeaderView(View view) {
        boolean result = false;
        if (view == null) {
            return result;
        }
        if (adapter != null) {
            result = adapter.addHeadView(view);
        }
        return result;
    }

    public boolean removeHeaderView(View view) {
        boolean result = false;
        if (view == null) {
            return result;
        }
        if (adapter != null) {
            result = adapter.removeHeadView(view);
        }
        return result;
    }

    public boolean removeAllHeaderView() {
        boolean result = false;
        if (adapter != null) {
            result = adapter.removeAllHeadersView();
        }
        return result;
    }

    public boolean addFooterView(View view) {
        boolean result = false;
        if (view == null) {
            return result;
        }
        if (adapter != null) {
            result = adapter.addFootView(view);
        }
        return result;
    }

    public boolean addFooterView(int position, View view) {
        boolean result = false;
        if (view == null) {
            return result;
        }
        if (adapter != null) {
            result = adapter.addFootView(position, view);
        }
        return result;
    }

    public boolean removeFooterView(View view) {
        boolean result = false;
        if (view == null) {
            return result;
        }
        if (adapter != null) {
            result = adapter.removeFootView(view);
        }
        return result;
    }

    public boolean removeAllFootView() {
        boolean result = false;
        if (adapter != null) {
            result = adapter.removeAllFootView();
        }
        return result;
    }

    public int getHeaderCount() {
        int count = 0;
        if (adapter != null) {
            count = adapter.getHeaderSize();
        }
        return count;
    }

    public List<View> getHeaderViewList() {
        if (adapter != null) {
            return adapter.getHeaderViewList();
        }
        return Collections.EMPTY_LIST;
    }

    public int getFooterCount() {
        int count = 0;
        if (adapter != null) {
            return adapter.getFooterSize();
        }
        return count;
    }

    public List<View> getFooterViewList() {
        if (adapter != null) {
            return adapter.getFooterViewList();
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * 使用默认的加载更多
     */
    public void useDefLoadMoreView() {
        SimpleLoadMoreFooter loadMoreFooter = new SimpleLoadMoreFooter(getContext());
        setLoadMoreView(loadMoreFooter);
        setLoadMoreUIHandler(loadMoreFooter);
    }


    public void loadMoreFooterView(View view) {
        setLoadMoreView(view);
        setLoadMoreUIHandler((LoadMoreUIHandler) view);
    }

    /**
     * 设置加载更多布局
     *
     * @param view
     */
    public void setLoadMoreView(View view) {
        if (loadMoreView != null && loadMoreView != view) {
            removeFooterView(view);
        }
        loadMoreView = view;

        addFooterView(view);
    }

    public void setLoadMoreUIHandler(LoadMoreUIHandler loadMoreUIHandler) {
        this.loadMoreUIHandler = loadMoreUIHandler;
    }

    private void loadMoreCompleted() {
        if (loadMoreUIHandler != null) {
            loadMoreUIHandler.onLoadFinish(totalPage > currentPage);
        }
        loadMore = false;
        if (getStateCallback() != null) {
            changeRefreshEnableState(true);
            getStateCallback().notifyContent();
        }
    }

    public void setPage(final int currentPage, final int totalPage) {
        this.currentPage = currentPage;
        this.totalPage = totalPage;
        if (loadMoreUIHandler != null) {
            loadMoreUIHandler.onLoadFinish(totalPage > currentPage);
        }
    }

    /**
     * 改变 刷新可用 的状态
     *
     * @param isEnabled
     */
    private void changeRefreshEnableState(boolean isEnabled) {
        if (!isRefreshEnabled) return;
        if (getStateCallback() != null) {
            getStateCallback().refreshEnabled(isEnabled);
        }
    }

    /**
     * 刷新数据
     */
    public void refreshData() {
        if (getStateCallback() != null) getStateCallback().refreshState(true);
        if (getOnRefreshAndLoadMoreListener() != null) {
            getOnRefreshAndLoadMoreListener().onRefresh();
        }
    }

    RecyclerView.OnScrollListener processMoreListener = new RecyclerView.OnScrollListener() {

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);

            if (adapter == null
                    || recyclerView.getLayoutManager() == null
                    || isRefresh) return;

            int totalCount = adapter.getItemCount();

            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && !loadMore
                    && lastVelocityY > 0
                    && getLastVisibleItemPosition(recyclerView.getLayoutManager()) + LOAD_MORE_ITEM_SLOP > totalCount) {

                if (totalPage > currentPage) {
                    loadMore = true;

                    if (getOnRefreshAndLoadMoreListener() != null) {
                        getOnRefreshAndLoadMoreListener().onLoadMore(++currentPage);

                        changeRefreshEnableState(false);

                        if (loadMoreUIHandler != null) {
                            loadMoreUIHandler.onLoading();
                        }
                    }
                } else {
                    loadMoreCompleted();
                }

            } else {
                changeRefreshEnableState(true);
            }

        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
        }
    };

    public XRecyclerView setOnRefreshAndLoadMoreListener(OnRefreshAndLoadMoreListener onRefreshAndLoadMoreListener) {
        this.onRefreshAndLoadMoreListener = onRefreshAndLoadMoreListener;
        changeRefreshEnableState(true);
        return this;
    }

    public XRecyclerView noDivider() {
        setItemAnimator(new DefaultItemAnimator());
        setHasFixedSize(true);
        return this;
    }

    public XRecyclerView horizontalDivider(@ColorRes int colorRes, @DimenRes int dimenRes) {
        setItemAnimator(new DefaultItemAnimator());
        setHasFixedSize(true);
        addItemDecoration(new HorizontalDividerItemDecoration.Builder(getContext())
                .colorResId(colorRes)
                .size(getContext().getResources().getDimensionPixelSize(dimenRes))
                .build()
        );
        return this;
    }

    public XRecyclerView verticalDivider(@ColorRes int colorRes, @DimenRes int dimenRes) {
        setItemAnimator(new DefaultItemAnimator());
        setHasFixedSize(true);
        addItemDecoration(new VerticalDividerItemDecoration.Builder(getContext())
                .colorResId(colorRes)
                .size(getContext().getResources().getDimensionPixelSize(dimenRes))
                .build()
        );
        return this;
    }


    public OnRefreshAndLoadMoreListener getOnRefreshAndLoadMoreListener() {
        return onRefreshAndLoadMoreListener;
    }

    public boolean isRefreshEnabled() {
        return isRefreshEnabled;
    }

    /**
     * 设置是否可下拉刷新
     *
     * @param refreshEnabled
     */
    public void setRefreshEnabled(boolean refreshEnabled) {
        isRefreshEnabled = refreshEnabled;
    }


    enum LayoutManagerType {
        LINEAR, GRID, STAGGERED_GRID
    }

    public interface StateCallback {
        void notifyEmpty();

        void notifyContent();

        void refreshState(boolean isRefresh);

        void refreshEnabled(boolean isEnabled);
    }

    public interface OnRefreshAndLoadMoreListener {
        void onRefresh();

        void onLoadMore(int page);
    }


    private void releaseVelocity() {
        if (mVelocityTracker != null) {
            mVelocityTracker.clear();
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    private void obtainVelocity(MotionEvent event) {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
    }

    public int pointToPosition(int x, int y) {
        int firstPosition = ((LinearLayoutManager) getLayoutManager()).findFirstVisibleItemPosition();
        Rect frame = mTouchFrame;
        if (frame == null) {
            mTouchFrame = new Rect();
            frame = mTouchFrame;
        }

        final int count = getChildCount();
        for (int i = count - 1; i >= 0; i--) {
            final View child = getChildAt(i);
            if (child.getVisibility() == View.VISIBLE) {
                child.getHitRect(frame);
                if (frame.contains(x, y)) {
                    return firstPosition + i;
                }
            }
        }
        return INVALID_POSITION;
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            mFlingView.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }

    /**
     * 将显示子菜单的子view关闭
     * 这里本身是要自己来实现的，但是由于不定制item，因此不好监听器点击事件，因此需要调用者手动的关闭
     */
    public void closeMenu() {
        if (mFlingView != null && mFlingView.getScrollX() != 0) {
            mFlingView.scrollTo(0, 0);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        if(getLayoutManager() instanceof StaggeredGridLayoutManager){
          return super.onInterceptTouchEvent(e);
        }
        int x = (int) e.getX();
        int y = (int) e.getY();
        obtainVelocity(e);
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!mScroller.isFinished()) {  // 如果动画还没停止，则立即终止动画
                    mScroller.abortAnimation();
                }
                mFirstX = mLastX = x;
                mFirstY = y;
                mPosition = pointToPosition(x, y);  // 获取触碰点所在的position
                if (mPosition != INVALID_POSITION) {
                    View view = mFlingView;
                    // 获取触碰点所在的view
                    mFlingView = (ViewGroup) getChildAt(mPosition - ((LinearLayoutManager) getLayoutManager()).findFirstVisibleItemPosition());
                    // 这里判断一下如果之前触碰的view已经打开，而当前碰到的view不是那个view则立即关闭之前的view，此处并不需要担动画没完成冲突，因为之前已经abortAnimation
                    if (view != null && mFlingView != view && view.getScrollX() != 0) {
                        view.scrollTo(0, 0);
                    }
                    // 这里进行了强制的要求，RecyclerView的子ViewGroup必须要有2个子view,这样菜单按钮才会有值，
                    // 需要注意的是:如果不定制RecyclerView的子View，则要求子View必须要有固定的width。
                    // 比如使用LinearLayout作为根布局，而content部分width已经是match_parent，此时如果菜单view用的是wrap_content，menu的宽度就会为0。
                    if (mFlingView.getChildCount() == 2) {
                        mMenuViewWidth = mFlingView.getChildAt(1).getWidth();
                    } else {
                        mMenuViewWidth = INVALID_CHILD_WIDTH;
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                mVelocityTracker.computeCurrentVelocity(1000);
                // 此处有俩判断，满足其一则认为是侧滑：
                // 1.如果x方向速度大于y方向速度，且大于最小速度限制；
                // 2.如果x方向的侧滑距离大于y方向滑动距离，且x方向达到最小滑动距离；
                float xVelocity = mVelocityTracker.getXVelocity();
                float yVelocity = mVelocityTracker.getYVelocity();
                if (Math.abs(xVelocity) > SNAP_VELOCITY && Math.abs(xVelocity) > Math.abs(yVelocity)
                        || Math.abs(x - mFirstX) >= mTouchSlop
                        && Math.abs(x - mFirstX) > Math.abs(y - mFirstY)) {
                    mIsSlide = true;
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                releaseVelocity();
                break;
        }
        return super.onInterceptTouchEvent(e);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (mIsSlide && mPosition != INVALID_POSITION) {
            float x = e.getX();
            obtainVelocity(e);
            switch (e.getAction()) {
                case MotionEvent.ACTION_DOWN:   // 因为没有拦截，所以不会被调用到
                    break;
                case MotionEvent.ACTION_MOVE:
                    // 随手指滑动
                    if (mMenuViewWidth != INVALID_CHILD_WIDTH) {
                        float dx = mLastX - x;
                        if (mFlingView.getScrollX() + dx <= mMenuViewWidth
                                && mFlingView.getScrollX() + dx > 0) {
                            mFlingView.scrollBy((int) dx, 0);
                        }
                        mLastX = x;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    if (mMenuViewWidth != INVALID_CHILD_WIDTH) {
                        int scrollX = mFlingView.getScrollX();
                        mVelocityTracker.computeCurrentVelocity(1000);
                        // 此处有两个原因决定是否打开菜单：
                        // 1.菜单被拉出宽度大于菜单宽度一半；
                        // 2.横向滑动速度大于最小滑动速度；
                        // 注意：之所以要小于负值，是因为向左滑则速度为负值
                        if (mVelocityTracker.getXVelocity() < -SNAP_VELOCITY) {    // 向左侧滑达到侧滑最低速度，则打开
                            mScroller.startScroll(scrollX, 0, mMenuViewWidth - scrollX, 0, Math.abs(mMenuViewWidth - scrollX));
                        } else if (mVelocityTracker.getXVelocity() >= SNAP_VELOCITY) {  // 向右侧滑达到侧滑最低速度，则关闭
                            mScroller.startScroll(scrollX, 0, -scrollX, 0, Math.abs(scrollX));
                        } else if (scrollX >= mMenuViewWidth / 2) { // 如果超过删除按钮一半，则打开
                            mScroller.startScroll(scrollX, 0, mMenuViewWidth - scrollX, 0, Math.abs(mMenuViewWidth - scrollX));
                        } else {    // 其他情况则关闭
                            mScroller.startScroll(scrollX, 0, -scrollX, 0, Math.abs(scrollX));
                        }
                        invalidate();
                    }
                    mMenuViewWidth = INVALID_CHILD_WIDTH;
                    mIsSlide = false;
                    mPosition = INVALID_POSITION;
                    releaseVelocity();  // 这里之所以会调用，是因为如果前面拦截了，就不会执行ACTION_UP,需要在这里释放追踪
                    break;
            }
            return true;
        } else {
            // 此处防止RecyclerView正常滑动时，还有菜单未关闭
            closeMenu();
            // Velocity，这里的释放是防止RecyclerView正常拦截了，但是在onTouchEvent中却没有被释放；
            // 有三种情况：1.onInterceptTouchEvent并未拦截，在onInterceptTouchEvent方法中，DOWN和UP一对获取和释放；
            // 2.onInterceptTouchEvent拦截，DOWN获取，但事件不是被侧滑处理，需要在这里进行释放；
            // 3.onInterceptTouchEvent拦截，DOWN获取，事件被侧滑处理，则在onTouchEvent的UP中释放。
            releaseVelocity();
        }
        return super.onTouchEvent(e);
    }
}