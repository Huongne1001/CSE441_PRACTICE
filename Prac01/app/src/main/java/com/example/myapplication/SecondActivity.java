package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private EditText etFullName, etGPA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        etFullName = findViewById(R.id.etFullName);
        etGPA = findViewById(R.id.etGPA);
        Button btnSendData = findViewById(R.id.btnSendData);

        btnSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = etFullName.getText().toString();
                String gpa = etGPA.getText().toString();
                String result = "Họ tên: " + fullName + ", Điểm TB: " + gpa;

                Intent resultIntent = new Intent();
                resultIntent.putExtra("RESULT", result);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
