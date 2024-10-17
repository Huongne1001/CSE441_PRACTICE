package com.example.bth03;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText etStudentId, etFullName, etBirthDate, etEmail, etGpa;
    private Spinner spnMajor, spnExpYear, spnAddr;
    private Button btnSubmit;
    private RadioGroup radioGroupGender;
    private List<Student> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Khởi tạo các trường nhập
        etStudentId = findViewById(R.id.etStudentId);
        etFullName = findViewById(R.id.etFullName);
        etBirthDate = findViewById(R.id.etBirthDate);
        etEmail = findViewById(R.id.etEmail);
        etGpa = findViewById(R.id.etGpa);
        spnMajor = findViewById(R.id.spnMajor);
        btnSubmit = findViewById(R.id.btnSubmit);
        spnExpYear = findViewById(R.id.spnExpYear);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        spnAddr = findViewById(R.id.spnAddr);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}