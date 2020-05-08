package com.example.swachhbin12;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import java.util.Random;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class BuyFragment extends Fragment {
    EditText name, address, zip, phone, otp;
    Button verify, submit;
    RadioGroup waste;
    RadioButton veg_waste, org_waste;
    DatabaseReference reff;
    int n;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buy, container, false);
        name = view.findViewById(R.id.user_name);
        address = view.findViewById(R.id.user_address);
        zip = view.findViewById(R.id.user_zip);
        phone = view.findViewById(R.id.user_phone);
        otp = view.findViewById(R.id.user_otp);
        submit = view.findViewById(R.id.user_submit);
        verify = view.findViewById(R.id.user_verify);
        waste = view.findViewById(R.id.user_radio_group);
        veg_waste = view.findViewById(R.id.user_radio_veg);
        org_waste = view.findViewById(R.id.user_radio_org);
        reff = FirebaseDatabase.getInstance().getReference("m");


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(),Integer.toString(n),Toast.LENGTH_SHORT).show();


                String name1 = name.getText().toString().trim();
                String address1 = address.getText().toString().trim();
                String zip1 = zip.getText().toString().trim();
                String phone1 = phone.getText().toString().trim();
                String otp1 = otp.getText().toString().trim();
                int selectId = waste.getCheckedRadioButtonId();
                String waste1;
                if (selectId == R.id.user_radio_veg)
                    waste1 = "Vegetable Waste";
                else
                    waste1 = "Organic Waste";
                if(otp1.equals(Integer.toString(n))) {

                    name.getText().clear();
                    address.getText().clear();
                    zip.getText().clear();
                    phone.getText().clear();
                    otp.getText().clear();
                    String id = reff.push().getKey();
                    member m = new member(id, name1, address1, phone1, waste1, zip1);

                    reff.child(id).setValue(m);
                    Toast.makeText(getActivity(), "Requested Successfullyl", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getActivity(), "Enter Correct OTP", Toast.LENGTH_SHORT).show();
                }

            }
        });
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random rand =new Random();
                n = rand.nextInt(9999);
                n+=1;
                String smsMessage = Integer.toString(n)+" is your OTP to order Waste from Swachh Bin";

                final int PERMISSION_REQUEST_CODE = 1;

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {

                    if (ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.SEND_SMS)
                            == PackageManager.PERMISSION_DENIED) {
                        Log.d("permission", "permission denied to SEND_SMS - requesting it");
                        String[] permissions = {Manifest.permission.SEND_SMS};

                        requestPermissions(permissions, PERMISSION_REQUEST_CODE);

                    } else {
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(phone.getText().toString(), null, smsMessage, null, null);
                        Toast.makeText(getActivity(), "OTP sent.",
                                Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
        return view;
    }
}


