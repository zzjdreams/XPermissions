package com.zzj.xpermissions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.zzj.xpermission.OnPermissionCallback;
import com.zzj.xpermission.Permission;
import com.zzj.xpermission.XXPermissions;
import com.zzj.xpermissions.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        mainBinding.btnTest1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                XXPermissions.with(MainActivity.this)
                        .permission(Permission.CAMERA)
                        .permission(Permission.ACCESS_FINE_LOCATION)
                        .interceptor(new PermissionInterceptor())
                        .request(new OnPermissionCallback() {

                            @Override
                            public void onGranted(@NonNull List<String> permissions, boolean allGranted) {
                                if (!allGranted) {
                                    return;
                                }
                                Snackbar.make(view, String.format(getString(R.string.demo_obtain_permission_success_hint),
                                        PermissionNameConvert.getPermissionString(MainActivity.this, permissions)), Snackbar.LENGTH_SHORT).show();
//                                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                            }
                        });
            }
        });
    }
}