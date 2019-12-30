package com.dxl.recvapk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private String LOG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fastInstall();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        fastInstall();
    }

    public void fastInstall() {
        //在Activity的onCreate()或者onNewIntent()中
        Intent intent = getIntent();
        Uri uri = intent.getData();
        if (uri != null) {
            String scheme = uri.getScheme();
            Log.d(LOG, "scheme：" + scheme);
            String host = uri.getHost();
            Log.d(LOG, "host: " + host);
            String port = uri.getPort() + "";
            Log.d(LOG, "port: " + port);
            String path = uri.getPath();
            Log.d(LOG, "path: " + path);
            String query = uri.getQuery();
            Log.d(LOG, "query: " + query);
            install(uri);
        } else {
            Toast.makeText(this, "Uri异常!", Toast.LENGTH_SHORT).show();
        }
    }

    public void install(Uri uri) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
        }
        startActivity(intent);
        finish();
    }
}
