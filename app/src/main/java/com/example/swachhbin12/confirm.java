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

public class confirm extends AppCompatActivity {

    TextView name1,address1,phone1,zip1,waste1;
    Button b;
    FirebaseDatabase database;

    DatabaseReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        name1 = findViewById(R.id.user_name);
        address1 = findViewById(R.id.user_address);
        name1 = findViewById(R.id.user_name);
        phone1 = findViewById(R.id.user_phone);
        zip1 = findViewById(R.id.user_zip);
        waste1 = findViewById(R.id.waste_type);
        b = findViewById(R.id.user_submit);

        final String playerData = getIntent().getStringExtra("ans");

        reff = FirebaseDatabase.getInstance().getReference("m").child(playerData);
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = (String) dataSnapshot.child("name").getValue();
                String address = (String) dataSnapshot.child("address").getValue();
                String phnum = (String) dataSnapshot.child("phone").getValue();
                String waste = (String) dataSnapshot.child("waste").getValue();
                String zip = (String) dataSnapshot.child("zip").getValue();
                //Toast.makeText(confirm.this, address, Toast.LENGTH_SHORT).show();
                name1.setText(name);
                address1.setText(address);
                phone1.setText(phnum);
                waste1.setText(waste);
                zip1.setText(zip);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff = FirebaseDatabase.getInstance().getReference("m").child(playerData);
                String num = "" + phone1.getText();
                Toast.makeText(confirm.this, num, Toast.LENGTH_SHORT).show();
                String smsMessage = "Your order is approved and it will be delivered soon.";

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
                Intent i = new Intent(getApplicationContext(),orders.class);
                startActivity(i);
            }
        });
    }
    public void onBackPressed()
    {
        Intent i = new Intent(getApplicationContext(),orders.class);
        startActivity(i);
    }
}
