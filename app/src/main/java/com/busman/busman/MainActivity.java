package com.busman.busman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    DatabaseReference databaseReference;

    EditText id_no,name;
    Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this,"hello",Toast.LENGTH_SHORT).show();

        databaseReference = FirebaseDatabase.getInstance().getReference("students");

        id_no = (EditText) findViewById(R.id.id_no);
        name = (EditText) findViewById(R.id.name);
        ok = (Button) findViewById(R.id.ok);

        ok.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                senddata();
            }
        });
    }
    public void senddata(){
        String id = id_no.getText().toString();
        String nm = name.getText().toString();

        if(!TextUtils.isEmpty(id)){
           // String i = databaseReference.push().getKey();

            student s = new student(id,nm);
            databaseReference.child(id).setValue(s);

            Toast.makeText(this,"student is added",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"enter data",Toast.LENGTH_SHORT).show();
        }
    }
}
