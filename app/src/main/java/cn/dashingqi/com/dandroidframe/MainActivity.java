package cn.dashingqi.com.dandroidframe;

import android.Manifest;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import cn.dashingqi.com.dandroidframe.interfaces.OnPermissionListener;
import cn.dashingqi.com.dandroidframe.util.DPermissionUtils;

public class MainActivity extends AppCompatActivity {

    private String[] permissions = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.CALL_PHONE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("TAG","onCreate");
        DPermissionUtils.requestPermissionsWithResult(this, 1, permissions, new OnPermissionListener() {
            @Override
            public void onPermissionGranted() {
                Log.d("TAG", "granted");
            }

            @Override
            public void onPermissionDenied() {
                Log.d("TAG", "denied");
                DPermissionUtils.showTipsDialog(MainActivity.this);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("TAG", "onResume");


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        DPermissionUtils.onRequestPermissionResult(requestCode, permissions, grantResults);
        Log.d("TAG", "onRequestPermissionsResult");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
