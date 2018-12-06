package cn.dashingqi.com.dandroidframe.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.dashingqi.com.dandroidframe.interfaces.OnPermissionListener;

/**
 * <p>文件描述：<p>
 * <p>作者：DashingQi <p>
 * <p>创建时间：2018/12/6<p>
 * <p>更改时间：2018/12/6<p>
 * <p>版本号：1<p>
 */
public class DPermissionUtils {

    private static OnPermissionListener mOnPermissionListener;
    private static int mRequestCode;

    public static void requestPermissionsWithResult(Activity activity, int requestCode, String[] permissions, OnPermissionListener listener) {
        requestPermissions(activity, requestCode, permissions, listener);
    }

    public static void requestPermissionsWithResult(android.app.Fragment fragment, int requestCode, String[] permissions, OnPermissionListener listener) {
        requestPermissions(fragment, requestCode, permissions, listener);
    }

    public static void requestPermissionsWithResult(Fragment fragment, int requestCode, String[] permissions, OnPermissionListener listener) {
        requestPermissions(fragment, requestCode, permissions, listener);
    }

    /**
     * 请求权限
     *
     * @param object
     * @param requestCode
     * @param permissions
     * @param listener
     */
    @TargetApi(Build.VERSION_CODES.M)
    private static void requestPermissions(Object object, int requestCode, String[] permissions, OnPermissionListener listener) {
        checkObject(object);
        mOnPermissionListener = listener;
        if (checkPermissions(getContext(object), permissions)) {
            //权限都申请了
            if (mOnPermissionListener != null) {
                Log.d("TAG", "requestPermissions");
                mOnPermissionListener.onPermissionGranted();
                //制空，防止内存泄漏
                mOnPermissionListener = null;
            }
        } else {
            //权限有 没有申请的，需要把没有申请的权限列出来，重新申请
            List<String> deniedPermissions = getDeniedPermissions(getContext(object), permissions);
            mRequestCode = requestCode;
            if (object instanceof Activity)
                ((Activity) object).requestPermissions(deniedPermissions.toArray(new String[deniedPermissions.size()]), mRequestCode);
            else if (object instanceof Fragment)
                ((Fragment) object).requestPermissions(deniedPermissions.toArray(new String[deniedPermissions.size()]), mRequestCode);
            else if (object instanceof android.app.Fragment)
                ((android.app.Fragment) object).requestPermissions(deniedPermissions.toArray(new String[deniedPermissions.size()]), mRequestCode);
        }


    }

    /**
     * 权限请求结果的处理方法。
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    public static void onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResults) {
        if (mRequestCode == requestCode)
            if (verifyPermission(grantResults)) {
                if (mOnPermissionListener != null) {
                    mOnPermissionListener.onPermissionGranted();
                    mOnPermissionListener = null;
                }
            } else {
                if (mOnPermissionListener != null) {
                    mOnPermissionListener.onPermissionDenied();
                    mOnPermissionListener = null;
                }
            }
    }

    /**
     * 检查页面传入的对象
     *
     * @param object
     */
    private static void checkObject(Object object) {
        if (object == null) {
            throw new NullPointerException("Activity or Fragment should not be null");
        }

        boolean isActivity = object instanceof Activity;
        boolean isSupportFragment = object instanceof Fragment;
        boolean isAppFragment = object instanceof android.app.Fragment;

        if (!(isActivity || isSupportFragment || isAppFragment)) {
            throw new IllegalArgumentException("Caller must be an Activity or Fragment");
        }
    }

    /**
     * 检查当前应用权限的申请情况
     *
     * @return true：要申请的应用权限都申请了，false：部分或者全部要申请的权限没有申请。
     */
    private static boolean checkPermissions(Context context, String... permissions) {
        if (checkPhoneVersionCode()) {
            for (String permission : permissions)
                if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED)
                    return false;
        }

        return true;
    }

    /**
     * 换取上下文环境
     *
     * @param object
     * @return context
     */
    private static Context getContext(Object object) {
        Context context;
        if (object instanceof Fragment)
            context = ((Fragment) object).getActivity();
        else if (object instanceof android.app.Fragment)
            context = ((android.app.Fragment) object).getActivity();
        else
            context = (Activity) object;
        return context;
    }

    /**
     * 判断当前手机版本号是否>=6.0
     *
     * @return true：大于或者等于 false:小于
     */
    public static boolean checkPhoneVersionCode() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }


    /**
     * 将没有申请的权限整合到一起
     *
     * @param context     上下文环境
     * @param permissions 没有申请权限的集合
     * @return
     */
    private static List<String> getDeniedPermissions(Context context, String... permissions) {
        List<String> deniedLists = new ArrayList<>();
        for (String permission : permissions)
            if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED)
                deniedLists.add(permission);
        return deniedLists;
    }

    /**
     * 验证权限
     *
     * @param grantResults 请求的结果    grantResults.length() == 0 或者 -1 为没有申请成功  0 申请成功
     * @return false:失败  true：成功
     */
    private static boolean verifyPermission(int... grantResults) {
        //如果请求被取消，那么结果数组为空
        if (grantResults.length <= 0) {
            return false;
        }

        //循环判断权限是否被拒绝
        for (int grantResult : grantResults)
            if (grantResult != PackageManager.PERMISSION_GRANTED)
                return false;

        return true;
    }

    /**
     * 展示提醒框
     *
     * @param context
     */
    public static void showTipsDialog(final Context context) {

        new AlertDialog.Builder(context)
                .setTitle("提示信息")
                .setMessage("当前应用缺少必要权限，该功能暂时无法使用。如若需要，请单击【确定】按钮前往设置中心进行权限授权。")
                .setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings(context);
                    }
                }).

                show();
    }

    /**
     * 跳转到应用的设置界面
     *
     * @param context
     */
    private static void startAppSettings(Context context) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        context.startActivity(intent);
    }
}
