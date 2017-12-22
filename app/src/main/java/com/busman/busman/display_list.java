package com.busman.busman;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class display_list extends AppCompatActivity implements Serializable{

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

        stu_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                student student =  studentList.get(i);
               /* Bundle b = new Bundle();
                b.putSerializable("value", (Serializable) student);*/ //pass b as argument in showupdatedialog function
                showupdatedialog(student.getId(),student.getName(),student.getStop(),student.getFees());
                return true;
            }
        });
    }

    private void showupdatedialog(final String id, String name, final String stop, final String fee){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogview = inflater.inflate(R.layout.dialogbox,null);

        dialog.setView(dialogview);

        final EditText u_name = (EditText) dialogview.findViewById(R.id.u_name);
        final Spinner u_stop = (Spinner) dialogview.findViewById(R.id.u_stop);
        final EditText u_fees = (EditText) dialogview.findViewById(R.id.u_fees);
        final Button update = (Button) dialogview.findViewById(R.id.update);
       // List<student> students;
        u_name.setText(name);
        //u_stop.
        u_fees.setText(fee);
        /*student s = (student) b.getSerializable("value");
        s.*/
       // u_stop.setSelection();
        //student student = students.get(position);
       // u_stop.setSelection();

        dialog.setTitle("     Updating Student : " + name);
        final AlertDialog alertDialog = dialog.create();
        alertDialog.show();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = u_name.getText().toString().trim();
                String stop = u_stop.getSelectedItem().toString();
                String fee = u_fees.getText().toString().trim();
                if (!TextUtils.isEmpty(name)) {
                    updatestudent(id, name, stop, fee);
                    alertDialog.dismiss();
                }
            }
        });

    }
    public boolean updatestudent(String id,String name,String stop,String fee){
        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("students").child(id);
        student student = new student(id,name,stop,fee);
        dbref.setValue(student);
        Toast.makeText(this, "Student Updated Successfully", Toast.LENGTH_SHORT).show();
        return true;
    }
}
