package cn.dashingqi.com.dandroidframe.interfaces;

/**
 * <p>文件描述：<p>
 * <p>作者：北京车车网络技术有限公司<p>
 * <p>创建时间：2018/12/6<p>
 * <p>更改时间：2018/12/6<p>
 * <p>版本号：1<p>
 */
public interface OnPermissionListener {
    //权限同意
    void onPermissionGranted();

    //权限拒绝
    void onPermissionDenied();
}
