package com.example.blood;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class admin_add_doc_update extends AppCompatActivity {
    EditText ID, name, Email, contact, Hosp;
    Button Approve, Decline, emailBT;
    String iname, iemail, icontact, ihospital, iReq;
    Boolean clicked;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_doc_update);

        ID = findViewById(R.id.docID);
        name = findViewById(R.id.docName);
        contact = findViewById(R.id.docContact);
        Hosp = findViewById(R.id.doc_hospital);
//documents = findViewById(R.id.documents);
        Approve = findViewById(R.id.accept_btn);
        Decline = findViewById(R.id.decline_btn);
        emailBT = findViewById(R.id.email_btn);

        getAndSetIntentData();

        name.setEnabled(false);
        contact.setEnabled(false);
        Hosp.setEnabled(false);
        ID.setEnabled(false);
        clicked = false;
        
    }
    void getAndSetIntentData() {
        if (getIntent().hasExtra("ID") &&
                getIntent().hasExtra("Name") &&
                getIntent().hasExtra("Email") &&
                getIntent().hasExtra("Contact") &&
                getIntent().hasExtra("Hosp") ) {
            iReq = String.valueOf(getIntent().getStringExtra("ID")).trim();
            iname = String.valueOf(getIntent().getStringExtra("Name"));
            iemail = String.valueOf(getIntent().getStringExtra("Email"));
            icontact = String.valueOf(getIntent().getStringExtra("Contact"));
            ihospital = String.valueOf(getIntent().getStringExtra("Hosp"));
            Log.d("helpppppppppppppppppppp 1", iReq+" "+iname+" "+iemail+" "+icontact+" "+ihospital);

//            intent.putExtra("ID", String.valueOf(ID.get(position)));
//            intent.putExtra("Name", String.valueOf(Name.get(position)));
//            intent.putExtra("Email", String.valueOf(Email.get(position)));
//            intent.putExtra("Contact", String.valueOf(Contact.get(position)));
//            intent.putExtra("Hosp", String.valueOf(Hospital.get(position)));

            ID.setText(iReq);
            name.setText(iname);
            Email.setText(iemail);
            contact.setText(icontact);
            Hosp.setText(ihospital);
            Log.d("helpppppppppppppppppppp 2", ID+" "+name+" "+Email+" "+contact+" "+Hosp);
        } else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }
}