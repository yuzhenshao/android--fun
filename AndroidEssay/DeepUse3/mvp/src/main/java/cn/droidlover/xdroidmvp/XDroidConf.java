package cn.droidlover.xdroidmvp;

import cn.droidlover.xdroidmvp.imageloader.ILoader;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Created by wanglei on 2016/12/4.
 */

public class XDroidConf {
    // #log log打印
    public static boolean LOG = true; //配置是否显示log
    public static String LOG_TAG = "XDroid"; //配置log的tag

    // #cache 缓存数据
    public static String CACHE_SP_NAME = "config"; //配置sharedPref的文件名
    public static String CACHE_DISK_DIR = "cache"; //配置缓存文件夹名称

    // #router 路由 ，页面跳转
    public static int ROUTER_ANIM_ENTER = Router.RES_NONE; //配置enterAnimation
    public static int ROUTER_ANIM_EXIT = Router.RES_NONE; //配置exitAnimation

    // #imageloader
    public static int IL_LOADING_RES = ILoader.Options.RES_NONE; //配置图片加载loding占位图
    public static int IL_ERROR_RES = ILoader.Options.RES_NONE; //配置图片加载失败占位图

    // #dev model
    public static boolean DEV = true; //配置当前是否是开发阶段

    /**
     * config log
     *
     * @param log
     * @param logTag
     */
    public static void configLog(boolean log, String logTag) {
        LOG = log;
        if (!Kits.Empty.check(logTag)) {
            LOG_TAG = logTag;
        }
    }

    /**
     * conf cache
     *
     * @param spName
     * @param diskDir
     */
    public static void configCache(String spName, String diskDir) {
        if (!Kits.Empty.check(spName)) {
            CACHE_SP_NAME = spName;
        }
        if (!Kits.Empty.check(diskDir)) {
            CACHE_DISK_DIR = diskDir;
        }
    }

    /**
     * config dev
     *
     * @param isDev
     */
    public static void devMode(boolean isDev) {
        DEV = isDev;
    }

}
