package com.example.blood;

import android.graphics.Bitmap;

public class ImageModelClass {
    private Bitmap image;

    public ImageModelClass(Bitmap image)
    {
        this.image = image;
    }
    public Bitmap getImage()
    {
        return image;
    }
}
