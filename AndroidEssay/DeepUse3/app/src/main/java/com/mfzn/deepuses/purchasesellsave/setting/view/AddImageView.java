package com.mfzn.deepuses.purchasesellsave.setting.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.net.ImageUploadManager;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.ImageAdapter;
import com.mfzn.deepuses.utils.Bimp;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.ImageCompressUtil;
import com.mfzn.deepuses.utils.PhotographDialog;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.kit.KnifeKit;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

import static android.app.Activity.RESULT_OK;

/**
 * @author syz @date 2020-03-26
 */
public class AddImageView extends LinearLayout {

    @BindView(R.id.title)
    TextView mTitle;

    @BindView(R.id.add_image)
    ImageView mAddImage;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private ImageAdapter mAdapter;

    private List<Bitmap> mBitmaps = new ArrayList<>();
    private List<File> mBitmapFiles = new ArrayList<>();

    private Activity mContext;

    public AddImageView(Context context) {
        this(context, null);
    }


    public AddImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_add_image, this);
        KnifeKit.bind(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new ImageAdapter(mBitmaps);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int i) {
                removeImage(i);
            }
        });
        mAddImage.setOnClickListener(v -> {
            if(mContext!=null&&!mContext.isDestroyed()) {
                PhotographDialog.photographDialog(mContext, mBitmaps);
            }
        });
    }

    public void init(Activity context,String title){
        this.mContext=context;
        mTitle.setText(title);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //拍照，相册返回
        if (requestCode == Constants.REAL_NAME_PAIZHAO) {
            String cameraFile = PhotographDialog.mSp.getString("img", "");
            Bitmap bitmap = BitmapFactory.decodeFile(PhotographDialog.Image_SAVEDIR + "/" + cameraFile);//根据路径转为bitmap
            if (bitmap != null) {
                addImage(cameraFile,ImageCompressUtil.compressBySize(bitmap, 480, 480));
            }
        } else if (requestCode == Constants.RESULT_LOAD_IMAGE) {
            if (mBitmaps.size() < 9 && resultCode == RESULT_OK && null != data) {
                ArrayList<String> mSelectPath = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                try {
                    for (String path : mSelectPath) {
                        Bitmap bitmap = Bimp.revitionImageSize(path);
                        addImage(path,Bimp.createFramedPhoto(480, 480, bitmap, 0));
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void addImage(String filePath,Bitmap bitmap) {
        if (bitmap != null) {
            mBitmapFiles.add(new File(filePath));
            mBitmaps.add(bitmap);
            mAdapter.notifyItemInserted(mBitmaps.size() - 1);
            mAddImage.setVisibility(mBitmaps.size() == 9 ? GONE : VISIBLE);
        }
    }

    private void removeImage(int position) {
        if (mBitmaps != null && mBitmaps.size() > position) {
            mBitmaps.remove(position);
            mBitmapFiles.remove(position);
            mAdapter.notifyItemRemoved(position);
            mAddImage.setVisibility(mBitmaps.size() == 9 ? GONE : VISIBLE);
        }
    }

    public List<File> getBitmapFiles(){
        return mBitmapFiles;
    }

    private int uploadImageIndex = 0;

    public void upLoadFile(ImageUploadManager.ImageUploadCallback callback) {
        uploadImageIndex=0;
        StringBuffer fileUrls = new StringBuffer();
        for (int i = 0; i < mBitmapFiles.size(); i++) {
            if (mBitmapFiles.get(i).exists()) {
                ImageUploadManager.uploadImage(mBitmapFiles.get(i), new ImageUploadManager.ImageUploadCallback() {

                    @Override
                    public void uploadSuccess(String url) {
                        if (uploadImageIndex > 0) {
                            fileUrls.append(",");
                        }
                        uploadImageIndex++;
                        fileUrls.append(url);
                        if (uploadImageIndex == mBitmapFiles.size()) {
                            callback.uploadSuccess(fileUrls.toString());
                        }
                    }

                    @Override
                    public void uoloadFailed(String error) {
                        uploadImageIndex++;
                        if (uploadImageIndex == mBitmapFiles.size() - 1) {
                            callback.uploadSuccess(fileUrls.toString());
                        }
                    }
                });
            }
        }
    }
}
