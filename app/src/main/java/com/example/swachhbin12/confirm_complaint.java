package com.example.swachhbin12;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class confirm_complaint extends AppCompatActivity {
    TextView name1,phone1,complaint1;
    Button b;
    FirebaseDatabase database;

    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_complaint);
        name1 = (TextView) findViewById(R.id.complaint_name);
        phone1 = (TextView) findViewById(R.id.complaint_phone);
        complaint1=(TextView) findViewById(R.id.complaint_main);

        b = findViewById(R.id.complaint_process);
        final String playerData = getIntent().getStringExtra("ans1");
        //Toast.makeText(getApplicationContext(),playerData, Toast.LENGTH_SHORT).show();

        reff = FirebaseDatabase.getInstance().getReference("c").child(playerData);

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = (String) dataSnapshot.child("name").getValue();

                String phnum = (String) dataSnapshot.child("phone").getValue();
                String cmpl=(String) dataSnapshot.child("complaint").getValue();


                //Toast.makeText(getApplicationContext(),cmpl, Toast.LENGTH_SHORT).show();
                name1.setText(name);
                phone1.setText(phnum);
                complaint1.setText(cmpl);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff = FirebaseDatabase.getInstance().getReference("c").child(playerData);
                String num = "" + phone1.getText();
                //Toast.makeText(confirm.this, num, Toast.LENGTH_SHORT).show();
                String smsMessage = "Your Complaint is being processed , officer will reach you soon.";

                final int PERMISSION_REQUEST_CODE = 1;

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {

                    if (checkSelfPermission(Manifest.permission.SEND_SMS)
                            == PackageManager.PERMISSION_DENIED) {
                        Log.d("permission", "permission denied to SEND_SMS - requesting it");
                        String[] permissions = {Manifest.permission.SEND_SMS};

                        requestPermissions(permissions, PERMISSION_REQUEST_CODE);

                    } else {
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(num, null, smsMessage, null, null);
                        Toast.makeText(getApplicationContext(), "SMS sent.",
                                Toast.LENGTH_LONG).show();
                    }
                }
                reff.removeValue();
                Intent i = new Intent(getApplicationContext(),complain.class);
                startActivity(i);
            }
        });
    }
    public void onBackPressed()
    {
        Intent i = new Intent(getApplicationContext(),complain.class);
        startActivity(i);
    }
}

