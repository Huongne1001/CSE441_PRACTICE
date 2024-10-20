package com.example.bth3;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bth3.entities.Student;


public class StudentDetailActivity extends AppCompatActivity {
    private TextView tvStudentName, tvStudentID, tvGpa, tvGender, tvAdress, tvEmail, tvDOB, tvMajor, tvYear;
    private ImageView imgStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_student_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tvStudentName = findViewById(R.id.textViewFullName);
        tvStudentID = findViewById(R.id.textViewId);
        tvGpa = findViewById(R.id.textViewGpa);
        tvGender = findViewById(R.id.textViewGender);
        imgStudent = findViewById(R.id.imageViewStudent1);
        tvAdress = findViewById(R.id.textViewAdress);
        tvEmail = findViewById(R.id.textViewEmail);
        tvDOB = findViewById(R.id.textViewDOB);
        tvMajor = findViewById(R.id.textViewMajor);
        tvYear = findViewById(R.id.textViewYear);

        // Lấy dữ liệu từ Intent
        Student student = (Student) getIntent().getSerializableExtra("STUDENT_DATA");

        if (student != null) {
            tvStudentName.setText("Họ và tên: "+student.getFull_name().getLast() + " " + student.getFull_name().getMidd() + " " + student.getFull_name().getFirst());
            tvStudentID.setText("Mã SV: "+student.getId());
            tvGpa.setText("Điểm TB tích lũy:"+String.valueOf(student.getGpa()));
            tvGender.setText("Giới tính: "+student.getGender());
            tvAdress.setText("Địa chỉ: "+student.getAddress());
            tvEmail.setText("Email: "+student.getEmail());
            tvDOB.setText("Ngày sinh: "+student.getBirth_date());
            tvMajor.setText("Chuyên nghành: "+student.getMajor());
            tvYear.setText("SV năm thứ: "+student.getYear());

            if (student.getGender().equals("Nữ")) {
                imgStudent.setImageResource(R.drawable.female);
            } else {
                imgStudent.setImageResource(R.drawable.male);
            }
        }

    }
}
