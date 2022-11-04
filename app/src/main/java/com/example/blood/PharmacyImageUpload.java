package com.example.blood;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PackageManagerCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.blood.UtilAndModel.DeliveryReqClass;

import java.io.ByteArrayOutputStream;

public class PharmacyImageUpload extends AppCompatActivity {

    private static final int IMAGE_REQ = 100;
    private static final int CAMERA_REQUEST = 101;
    public static final int STORAGE_REQUEST= 102;
    ImageView image;
    ActivityResultLauncher<String> mGetContent;
    ActivityResultLauncher<Intent> mGetPermission;
    private Uri imagePath;
    private Bitmap imageToStore;
    private DeliveryReqClass dh;
    Button chooseBtn;
    private String[] storagePermission,cameraPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_image_upload);

        image = findViewById(R.id.presImage);
        dh = new DeliveryReqClass(getApplicationContext());
        chooseBtn = findViewById(R.id.chooseBtn);

        cameraPermission=new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermission=new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        chooseBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                int avatar = 0;
                if(avatar == 0){
                    if(!checkCameraPermission()){
                        requestCameraPermission();
                    }else{
                        pickFromGallery();
                    }
                }else if (avatar == 1) {
                    if (!checkStoragePermission()) {
                        requestStoragePermission();
                    } else {
                        pickFromGallery();
                    }
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestStoragePermission() {
        requestPermissions(storagePermission, STORAGE_REQUEST);
    }

    private boolean checkStoragePermission() {
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)==(PackageManager.PERMISSION_GRANTED);
        return result;
    }

    private void pickFromGallery() {
        Intent objectIntent = new Intent();
        objectIntent.setType("image/*");
        objectIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(objectIntent,CAMERA_REQUEST);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestCameraPermission() {
        requestPermissions(cameraPermission,CAMERA_REQUEST);
    }

    private boolean checkCameraPermission() {
        boolean result = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)==(PackageManager.PERMISSION_GRANTED);
        boolean result2 = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)==(PackageManager.PERMISSION_GRANTED);
        return result && result2;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case CAMERA_REQUEST:{
                if(grantResults.length>0){
                    boolean camera_accept=grantResults[0]==PackageManager.PERMISSION_GRANTED;
                    boolean storage_accept=grantResults[1]==PackageManager.PERMISSION_GRANTED;
                    if(camera_accept&&storage_accept){
                        pickFromGallery();
                    }else{
                        Toast.makeText(this, "Camera And Storage Permission Denied",Toast.LENGTH_SHORT).show();
                    }
                }
            }
            break;
            case STORAGE_REQUEST:{
                if(grantResults.length>0){
                    boolean storage_accept=grantResults[0]==PackageManager.PERMISSION_GRANTED;

                    if(storage_accept){
                        pickFromGallery();
                    }else{
                        Toast.makeText(this, "please enable storage permission",Toast.LENGTH_SHORT).show();
                    }
                }
            }
            break;
        }
    }


//    public void chooseImage(View view) {
//        Intent objIntent = new Intent();
//        objIntent.setType("image/*");
//        objIntent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(objIntent, IMAGE_REQ);
//    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
                imagePath = data.getData();
                imageToStore = MediaStore.Images.Media.getBitmap(getContentResolver(), imagePath);
                image.setImageBitmap(imageToStore);

                Intent i = new Intent(getApplicationContext(), RequestDeliveryPharmacy.class);

                byte[] imageInBytes = dh.imageViewToByte(imageToStore);

                i.putExtra("image", imageInBytes);
                startActivity(i);
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}

