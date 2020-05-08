package com.example.swachhbin12;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class complain extends AppCompatActivity {
    ListView r;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    FirebaseDatabase database;
    DatabaseReference reff;
    complaint_member mem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain);
        mem = new complaint_member();
        r = findViewById(R.id.re);
        database  = FirebaseDatabase.getInstance();
        reff = database.getReference("c");
        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,R.layout.retrieve_orders,R.id.retrieve,list);

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    mem = ds.getValue(complaint_member.class);
                    list.add(mem.getId1().toString());
                    //list.add(mem.getName().toString() + " " + mem.getEmail() + " " + mem.getPass());
                }
                r.setAdapter(adapter);

                r.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

                        String farm = r.getItemAtPosition(i).toString();
                        Intent play = new Intent(getApplicationContext(),confirm_complaint.class);
                        play.putExtra("ans1",farm);
                        startActivity(play);

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void onBackPressed()
    {
        DashboardFragment df = new DashboardFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.fragment_container,df).commit();
    }
}
