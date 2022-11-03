package com.example.blood.TestingModels;

import static org.junit.Assert.*;

import android.graphics.Bitmap;
import android.widget.ImageView;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class DeliveryReqTesterTest {

    DeliveryReqTester deliveryReqClass = new DeliveryReqTester();
    
    @Test
    public void updateValidator() {
        Map<String, String> dataset1 = new HashMap<>();
        dataset1.put("name", "John 123");
        dataset1.put("area", "Angoda");
        dataset1.put("contact", "39813190310238");

        Map<String, String> dataset2 = new HashMap<>();
        dataset2.put("name", "John Ham");
        dataset2.put("area", "Nugegoda");
        dataset2.put("contact", "0771234567");

        Map<String, String> dataset3 = new HashMap<>();
        dataset3.put("name", "Sam Harvey");
        dataset3.put("area", "A1");
        dataset3.put("contact", "112");

        Boolean output1 = deliveryReqClass.updateValidator(dataset1.get("name"), dataset1.get("area"), dataset1.get("contact"));
        Boolean output2 = deliveryReqClass.updateValidator(dataset2.get("name"), dataset2.get("area"), dataset2.get("contact"));
        Boolean output3 = deliveryReqClass.updateValidator(dataset3.get("name"), dataset3.get("area"), dataset3.get("contact"));

        assertEquals(false, output1);
        assertEquals(true, output2);
        assertEquals(false, output3);
    }

    @Test
    public void validator() {
        Bitmap image = null;

        Map<String, String> dataset1 = new HashMap<>();
        dataset1.put("name", "John 123");
        dataset1.put("area", "Angoda");
        dataset1.put("contact", "39813190310238");

        Map<String, String> dataset2 = new HashMap<>();
        dataset2.put("name", "Sam Harvey");
        dataset2.put("area", "A1");
        dataset2.put("contact", "112");

        Boolean output1 = deliveryReqClass.validator(dataset1.get("name"), dataset1.get("area"), dataset1.get("contact"), image);
        Boolean output2 = deliveryReqClass.validator(dataset2.get("name"), dataset2.get("area"), dataset2.get("contact"), image);

        assertEquals(false, output1);
        assertEquals(false, output2);
    }
}