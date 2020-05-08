package com.example.swachhbin12;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ComplaintFragment extends Fragment {
    EditText name, phone, complaint;
    Button submit;
    DatabaseReference reff;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_complaint, container, false);
        name = view.findViewById(R.id.complaint_name);
        phone = view.findViewById(R.id.complaint_phone);
        complaint = view.findViewById(R.id.complaint_main);
        submit = view.findViewById(R.id.complaint_submit);
        reff = FirebaseDatabase.getInstance().getReference("c");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = name.getText().toString();
                String phone1 = phone.getText().toString();
                String complaint1 = complaint.getText().toString();
                String id1 = reff.push().getKey();
                complaint_member c = new complaint_member(id1, name1, phone1, complaint1);
                reff.child(id1).setValue(c);
                name.getText().clear();
                phone.getText().clear();
                complaint.getText().clear();


                Toast.makeText(getActivity(), "Complaint Registered", Toast.LENGTH_SHORT).show();

            }
        });


        return view;
    }
}

