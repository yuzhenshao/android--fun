package com.mfzn.deepusesSer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.support.multidex.MultiDexApplication;


import com.mfzn.deepusesSer.utils.HttpsTrustManager;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import cn.droidlover.xdroidmvp.imageloader.ILFactory;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.NetProvider;
import cn.droidlover.xdroidmvp.net.RequestHandler;
import cn.droidlover.xdroidmvp.net.XApi;
import cn.jpush.android.api.JPushInterface;
import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

public class BaseApplication extends MultiDexApplication {
    private static Context context;
//    private UploadUtile userUtils;
//    public static IWXAPI mWxApi;

    //以下属性应用于整个应用程序，合理利用资源，减少资源浪费
    private static Context mContext;//上下文
    private static Thread mMainThread;//主线程
    private static long mMainThreadId;//主线程id
    private static Looper mMainLooper;//循环队列
    private static Handler mHandler;//主线程Handler

    // APP_ID 替换为你的应用从官方网站申请到的合法appID
    public static final String APP_ID = "wxe915c0aa9e0c5ff3";
    // IWXAPI 是第三方app和微信通信的openApi接口
    public static IWXAPI api;

    @Override
    public void onCreate() {
        super.onCreate();

        //对全局属性赋值
        mContext = getApplicationContext();
        mMainThread = Thread.currentThread();
        mMainThreadId = android.os.Process.myTid();
        mHandler = new Handler();

        JPushInterface.init(this);
        context = this;
//        userUtils = new UploadUtile();

//        initBugly();

        //AppConst.WEIXIN.APP_ID是指你应用在微信开放平台上的AppID，记得替换。
//        mWxApi = WXAPIFactory.createWXAPI(this, "wx9abaef8bc5a87836", true);
//        // 将该app注册到微信
//        mWxApi.registerApp("wx9abaef8bc5a87836");
//
//        //初始化ShareSDK
//        MobSDK.init(this);
//        MobSDK.init(this, "2b11f43f78458", "4fc9ee2a9497c414790b63cb1d6587c0");

        //初始化腾讯X5内核  加载webview
//        initX5WebView();

        //初始化微信
        regToWx();

        ILFactory.getLoader().init(this);
        XApi.registerProvider(new NetProvider() {
            //配置拦截器
            @Override
            public Interceptor[] configInterceptors() {
                return new Interceptor[]{};
            }

            //配置https
            @Override
            public void configHttps(OkHttpClient.Builder builder) {
                builder.sslSocketFactory(HttpsTrustManager.createSSLSocketFactory())
                        .hostnameVerifier(new HttpsTrustManager.TrustAllHostnameVerifier());
            }

            //配置cookie
            @Override
            public CookieJar configCookie() {
                return null;
            }

            //配置通用请求handler
            @Override
            public RequestHandler configHandler() {
                return null;
            }

            //配置连接超时时长，单位毫秒
            @Override
            public long configConnectTimeoutMills() {
                return 10000;
            }

            //配置读超时时长，单位毫秒
            @Override
            public long configReadTimeoutMills() {
                return 10000;
            }

            //配置是否打印log
            @Override
            public boolean configLogEnable() {
                return true;
            }

            //公共异常处理
            @Override
            public boolean handleError(NetError error) {
                return false;
            }

            @Override
            public boolean dispatchProgressEnable() {
                return false;
            }
        });
//        NineGridView.setImageLoader(new PicassoImageLoader());

    }

    private void regToWx() {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, APP_ID, true);

        // 将应用的appId注册到微信
        api.registerApp(APP_ID);

        //建议动态监听微信启动广播进行注册到微信
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // 将该app注册到微信
                api.registerApp(APP_ID);
            }
        }, new IntentFilter(ConstantsAPI.ACTION_REFRESH_WXAPP));
    }

//    private void initBugly() {
//        Beta.autoInit = true;
//        Beta.autoCheckUpgrade = false;
//        Bugly.init(getApplicationContext(), "9eef9e64f7", true);
//    }
//
//    private void initX5WebView() {
//        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
//            @Override
//            public void onViewInitFinished(boolean arg0) {
//                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
//            }
//
//            @Override
//            public void onCoreInitFinished() {
//            }
//        };
//        //允许使用流量下载
//        QbSdk.setDownloadWithoutWifi(true);
//        //x5内核初始化接口
//        QbSdk.initX5Environment(getApplicationContext(), cb);
//    }
//
//
//    public UploadUtile getUserUtils() {
//        return userUtils;
//    }


    /**
//     * Picasso 加载
//     */
//    private class PicassoImageLoader implements NineGridView.ImageLoader {
//
//        @Override
//        public void onDisplayImage(Context context, ImageView imageView, String url) {
//            Picasso.with(context).load(url)//
//                    .placeholder(R.drawable.ic_default_image)//
//                    .error(R.drawable.ic_default_image)//
//                    .into(imageView);
//        }
//
//        @Override
//        public Bitmap getCacheImage(String url) {
//            return null;
//        }
//    }


    public static Context getContext() {
        return context;
    }


    /**
     * 圆角显示图片-Picas/*so
     *//*
    class RoundTransform implements Transformation {
        private int radius;//圆角值

        public RoundTransform(int radius) {
            this.radius = radius;
        }

        @Override
        public Bitmap transform(Bitmap source) {
            int width = source.getWidth();
            int height = source.getHeight();
            //画板
            Bitmap bitmap = Bitmap.createBitmap(width, height, source.getConfig());
            Paint paint = new Paint();
            Canvas canvas = new Canvas(bitmap);//创建同尺寸的画布
            paint.setAntiAlias(true);//画笔抗锯齿
            paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
            //画圆角背景
            RectF rectF = new RectF(new Rect(0, 0, width, height));//赋值
            canvas.drawRoundRect(rectF, radius, radius, paint);//画圆角矩形
            //
            paint.setFilterBitmap(true);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(source, 0, 0, paint);
            source.recycle();//释放

            return bitmap;
        }

        @Override
        public String key() {
            return "round : radius = " + radius;
        }
    }*/

    public static void setContext(Context mContext) {
        BaseApplication.mContext = mContext;
    }

    public static Thread getMainThread() {
        return mMainThread;
    }

    public static void setMainThread(Thread mMainThread) {
        BaseApplication.mMainThread = mMainThread;
    }

    public static long getMainThreadId() {
        return mMainThreadId;
    }

    public static void setMainThreadId(long mMainThreadId) {
        BaseApplication.mMainThreadId = mMainThreadId;
    }

    public static Looper getMainThreadLooper() {
        return mMainLooper;
    }

    public static void setMainThreadLooper(Looper mMainLooper) {
        BaseApplication.mMainLooper = mMainLooper;
    }

    public static Handler getMainHandler() {
        return mHandler;
    }

    public static void setMainHandler(Handler mHandler) {
        BaseApplication.mHandler = mHandler;
    }
}
