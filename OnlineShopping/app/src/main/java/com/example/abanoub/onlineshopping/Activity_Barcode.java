package com.example.abanoub.onlineshopping;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Activity_Barcode extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private static final int REQUEST_CAMERA = 1;
    private ZXingScannerView scannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if(checkPermission())
            {
                Toast.makeText(getApplicationContext(),"permission is granted", Toast.LENGTH_LONG).show();
            }

            else
            {
                requestPermission();
            }
        }

    }

    private boolean checkPermission()
    {
        return (ContextCompat.checkSelfPermission(Activity_Barcode.this, Manifest.permission.CAMERA) == getPackageManager().PERMISSION_GRANTED);
    }
    private void requestPermission()
    {
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},REQUEST_CAMERA);
    }
    public void onRequestPermissionResult(int requestCode,String permission[],int grantResults[])
    {
        switch(requestCode)
        {
            case REQUEST_CAMERA:
                if(grantResults.length >0)
                {
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_DENIED;
                    if(cameraAccepted)
                    {
                        Toast.makeText(getApplicationContext(),"Permission Granted",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Permission Denied",Toast.LENGTH_LONG).show();
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                        {
                            if(shouldShowRequestPermissionRationale(Manifest.permission.CAMERA))
                            {
                                displayAlertMessage("You need to allow access for both Permissions", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                                            requestPermissions(new String[]{Manifest.permission.CAMERA},REQUEST_CAMERA);
                                    }
                                });
                                return;
                            }
                        }
                    }
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if(checkPermission())
            {
                if(scannerView == null)
                {
                    scannerView = new ZXingScannerView(this);
                    setContentView(scannerView);
                }
                scannerView.setResultHandler(this);
                scannerView.startCamera();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        scannerView.startCamera();
    }

    public void displayAlertMessage(String message, DialogInterface.OnClickListener listener)
    {
        new AlertDialog.Builder(Activity_Barcode.this).setMessage(message).setPositiveButton("OK",listener)
                .setNegativeButton("Cancel",null).create().show();
    }

    @Override
    public void handleResult(Result result) {
        String scanResult = result.getText();
        AlertDialog.Builder build = new AlertDialog.Builder(this);
        build.setTitle("ScanResult");

        build.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                scannerView.resumeCameraPreview(Activity_Barcode.this);
            }
        });
        build.setMessage(scanResult);
        AlertDialog alert = build.create();
        alert.show();
    }
}