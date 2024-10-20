package com.example.btth04;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.btth04.entities.Student;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public static class FirebaseDatabaseHelper {
        private FirebaseDatabase mdatabase;
        private DatabaseReference mref;

        //constructor
        public FirebaseDatabaseHelper() {
            mdatabase = FirebaseDatabase.getInstance();
            mref = mdatabase.getReference("students");
        }

        public void addStudent(Student student){

            //get id node con push
            String id = mref.push().getKey();
            student.setId(id);
            //set value student vao node con
            mref.child(id).setValue(student);
        }

        public void updateStudent(String id, Student student){
            //set value student vao node con co id = id
            mref.child(id).setValue(student);
        }

        public void deleteStudent(String id){
            //xoa node con co id = id
            mref.child(id).removeValue();
        }


        public DatabaseReference getRef(){
            return mref;
        }

    }
}