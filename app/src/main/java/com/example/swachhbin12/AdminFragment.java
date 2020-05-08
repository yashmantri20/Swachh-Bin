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

public class AdminFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_admin,container,false);
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final EditText e1= (EditText) view.findViewById(R.id.admin_username);
        final EditText e2= (EditText) view.findViewById(R.id.admin_password);

        Button b = (Button) view.findViewById(R.id.admin_login);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a="admin";
                String b="dustbin66";
                //Toast.makeText(getActivity(),e1.getText().toString(),Toast.LENGTH_SHORT).show();
                //Toast.makeText(getActivity(),e2.getText().toString(),Toast.LENGTH_SHORT).show();
                if(e1.getText().toString().equals(a) && e2.getText().toString().equals(b)){
                    Toast.makeText(getActivity(),e1.getText().toString(),Toast.LENGTH_SHORT).show();
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container,new DashboardFragment()).commit();
                }
                else
                {
                    Toast.makeText(getActivity(),"Enter Valid id, password",Toast.LENGTH_SHORT).show();
                }

            }
        });





    }






}
