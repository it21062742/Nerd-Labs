package com.example.blood;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class PharmacyImageUpload extends AppCompatActivity {

    ImageView image;
    private static final int IMAGE_REQ = 100;
    private Uri imagePath;
    private Bitmap imageToStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_image_upload);

        image = findViewById(R.id.presImage);
    }
    public void chooseImage(View view)
    {
        Intent objIntent = new Intent();
        objIntent.setType("image/*");
        objIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(objIntent, IMAGE_REQ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        try {
            super.onActivityResult(requestCode, resultCode, data);

            if(requestCode==IMAGE_REQ && resultCode==RESULT_OK && data!=null && data.getData()!=null)
            {
                imagePath=data.getData();
                imageToStore= MediaStore.Images.Media.getBitmap(getContentResolver(), imagePath);
                image.setImageBitmap(imageToStore);

                Intent i = new Intent(getApplicationContext(), RequestDeliveryPharmacy.class);

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                imageToStore.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                byte [] imageInBytes = byteArrayOutputStream.toByteArray();

                i.putExtra("image", imageInBytes);
                startActivity(i);
            }
        }catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}