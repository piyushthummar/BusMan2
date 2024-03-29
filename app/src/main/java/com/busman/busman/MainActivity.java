package com.busman.busman;

import android.content.Intent;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DatabaseReference databaseReference;

    EditText id_no,name,fee;
    Button ok,displayb;
    Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this,"hello",Toast.LENGTH_SHORT).show();

        databaseReference = FirebaseDatabase.getInstance().getReference("students");

        id_no = (EditText) findViewById(R.id.id_no);
        name = (EditText) findViewById(R.id.name);
        sp = (Spinner) findViewById(R.id.stop);
        fee = (EditText) findViewById(R.id.fees);
        ok = (Button) findViewById(R.id.ok);
        displayb = (Button) findViewById(R.id.display);

        ok.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                senddata();
            }
        });

        displayb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"display button",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,display_list.class);
                startActivity(intent);
            }
        });
    }

    public void senddata(){
        String id = id_no.getText().toString();
        String nm = name.getText().toString();
        String stp = sp.getSelectedItem().toString();
        String fees = fee.getText().toString();

        if(!TextUtils.isEmpty(id)){
           // String i = databaseReference.push().getKey();

            student s = new student(id,nm,stp,fees);
            databaseReference.child(id).setValue(s);

            Toast.makeText(this,"student is added",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"enter data",Toast.LENGTH_SHORT).show();
        }
    }
}
