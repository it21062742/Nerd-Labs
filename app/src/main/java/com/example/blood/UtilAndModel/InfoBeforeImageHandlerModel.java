package com.example.blood.UtilAndModel;

import android.content.Context;
import android.database.Cursor;

import Database.InfoBeforeImage;
import Database.InfoBeforeImageHandler;

/* This class is to store the the information sent before uploading the image as the intent returning from
upload image page clears all information in the screen. */

public class InfoBeforeImageHandlerModel {
    InfoBeforeImageHandler info;

    private String name, contact, area;
    private Context context;


    public InfoBeforeImageHandlerModel(Context context) {
        this.context = context;

        info = new InfoBeforeImageHandler(context, InfoBeforeImage.Info.TABLENAME, null, 1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Cursor getData() {
        Cursor cursor = info.getData();
        return cursor;
    }

    public void setData(String name, String area, String contact) {
        Boolean result = info.addRecord(name, area, contact);

        if (result == true) {
            this.area = area;
            this.name = name;
            this.contact = contact;
        }
    }

    public void update(String name, String area, String contact) {
        Boolean result = info.Update(name, area, contact);

        if (result == true) {
            this.area = area;
            this.name = name;
            this.contact = contact;
        }
    }

    public void checkAndUpdate(String name, String area, String contact) {
        InfoBeforeImageHandlerModel infoClass = new InfoBeforeImageHandlerModel(context);

        Cursor cursor = infoClass.getData();

        if (cursor == null)
            infoClass.setData(name, area, contact);
        else
            infoClass.update(name, area, contact);
    }
}
