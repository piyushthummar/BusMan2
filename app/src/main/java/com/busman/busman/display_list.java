package com.busman.busman;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class display_list extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_list);
        final ListView stu_list;
        final List<student> studentList;
        stu_list= (ListView) findViewById(R.id.studentList);
        studentList=new ArrayList<>();
        DatabaseReference databaseReference;

        databaseReference = FirebaseDatabase.getInstance().getReference("students");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    student student=postSnapshot.getValue(com.busman.busman.student.class);
                    studentList.add(student);
                }
                display stu_dis=new display(display_list.this,studentList);
                stu_list.setAdapter(stu_dis);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void updatestudent(String id,String name,String stop,String fee){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogview = inflater.inflate(R.layout.dialogbox,null);

        dialog.setView(dialogview);

        final EditText u_name = (EditText) findViewById(R.id.u_name);
        //final Spinner u_stop = (Spinner) findViewById(R.id.u_stop);
        final EditText u_fees = (EditText) findViewById(R.id.u_fees);
        final Button update = (Button) findViewById(R.id.update);

        u_name.setText(name);
        //u_stop.
        u_fees.setText(fee);


        dialog.setTitle("Updating student" + id);
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }
}
