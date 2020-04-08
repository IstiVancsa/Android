package com.example.lab1.ui.Camera;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.camera2.CameraCaptureSession;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.lab1.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CameraActivity extends AppCompatActivity implements android.view.SurfaceHolder.Callback {

    private Button CaptureButton;
    private SurfaceView SurfaceViewCamera;
    private Camera MyCamera;
    private android.view.SurfaceHolder SurfaceHolder;
    private Camera.PictureCallback pictureCallback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state))
            if (!checkPermission())
                requestPermission();

        CaptureButton = findViewById(R.id.buttonCapture);
        SurfaceViewCamera = findViewById(R.id.surfaceViewCamera);
        SurfaceHolder = SurfaceViewCamera.getHolder();
        SurfaceHolder.addCallback(this);
        SurfaceHolder.setType(android.view.SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        CaptureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyCamera.takePicture(null, null, pictureCallback);
            }
        });

        pictureCallback = new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                Bitmap cbmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), null, true);

                String pathFileName = currentDateFormat();
                StorePhotoToStorage(cbmp, pathFileName);

                Toast.makeText(getApplicationContext(), "Well done! You look amazing!", Toast.LENGTH_LONG).show();

                CameraActivity.this.MyCamera.startPreview();
            }
        };
    }

    private void StorePhotoToStorage(Bitmap cbmp, String pathFileName) {
        File outputFile = new File(Environment.getExternalStorageState(), "/DCMI/beautifulPhoto_" + pathFileName + ".png");

        try{
            FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
            cbmp.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String currentDateFormat() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
        String currentDateTime = dateFormat.format(new Date());

        return currentDateTime;
    }

    @Override
    public void surfaceCreated(android.view.SurfaceHolder holder) {
        try{
            int cameraCount = 0;
            Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
            cameraCount = Camera.getNumberOfCameras();
            for (int camIdx = 0; camIdx < cameraCount; camIdx++) {
                Camera.getCameraInfo(camIdx, cameraInfo);
                if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                    try {
                        MyCamera = Camera.open(camIdx);
                    } catch (RuntimeException e) {
                        Log.e("112", "Camera failed to open: " + e.getLocalizedMessage());
                    }
                }
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        Camera.Parameters parameters;
        parameters = MyCamera.getParameters();
        parameters.setPreviewFrameRate(60);
        parameters.setPreviewSize(352, 288);
        MyCamera.setParameters(parameters);
        MyCamera.setDisplayOrientation(90);

        try{
            MyCamera.setPreviewDisplay(SurfaceHolder);
            MyCamera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void surfaceChanged(android.view.SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(android.view.SurfaceHolder holder) {
        MyCamera.stopPreview();
        MyCamera.release();
        MyCamera = null;
    }
    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.CAMERA)) {
            Toast.makeText(getApplicationContext(), "Write External Storage permission allows us to create files. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, 112);
        }
    }
    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }
}
