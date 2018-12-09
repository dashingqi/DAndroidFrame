package cn.dashingqi.com.dandroidframe;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import cn.dashingqi.com.dandroidframe.interfaces.OnPermissionListener;
import cn.dashingqi.com.dandroidframe.util.DPermissionUtils;
import cn.dashingqi.com.dandroidframe.util.SpUtils.DPrefsUtils;
import cn.dashingqi.com.dandroidframe.util.SpUtils.SpKeys;
import cn.dashingqi.com.dandroidframe.util.log.DLoggerUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public final String TAG = MainActivity.class.getSimpleName();

    private String[] permissions = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.CALL_PHONE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DLoggerUtils.i(TAG, "onCreate");
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

        findViewById(R.id.btn_add_value).setOnClickListener(this);
        findViewById(R.id.btn_get_value).setOnClickListener(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
       DLoggerUtils.d(TAG,"onResume");


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        DPermissionUtils.onRequestPermissionResult(requestCode, permissions, grantResults);
        Log.d("TAG", "onRequestPermissionsResult");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_value:
                addValue();
                break;
            case R.id.btn_get_value:
                getValue();
                break;
        }
    }

    private void addValue() {
        DPrefsUtils.putValue(SpKeys.TEST_ONE, "DashingQi");
        DPrefsUtils.putValue(SpKeys.TEST_TWO, true);
        DPrefsUtils.putValue(SpKeys.TEST_THREE, 1.5f);
    }

    private void getValue() {
        String strValue = DPrefsUtils.getStringValue(SpKeys.TEST_ONE, "");
        DLoggerUtils.d(TAG, strValue);
        boolean intValue = DPrefsUtils.getBooleanValue(SpKeys.TEST_TWO, false);
        DLoggerUtils.d(TAG, intValue);
        float floatValue = DPrefsUtils.getFloatValue(SpKeys.TEST_THREE, 0.0f);
        DLoggerUtils.d(TAG, floatValue);

    }
}
