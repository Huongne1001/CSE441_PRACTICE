package com.example.bth03;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CreateActivity extends AppCompatActivity {
    private EditText etStudentId, etFullName, etBirthDate, etEmail, etGpa;
    private Spinner spnMajor, spnExpYear, spnAddr;
    private Button btnSubmit;
    private RadioGroup radioGroupGender;
    private List<Student> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create);

        // Khởi tạo các trường nhập
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

        // Khởi tạo danh sách sinh viên
        studentList = new ArrayList<>();

        btnSubmit.setOnClickListener(v -> {
            saveStudentData();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

        // Thiết lập nút back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    // Xử lý sự kiện nút back trên Toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Quay lại màn hình trước
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveStudentData() {
        // Lấy dữ liệu từ các trường
        String id = etStudentId.getText().toString().trim();
        String fullName = etFullName.getText().toString().trim();
        String birthDate = etBirthDate.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String address = spnAddr.getSelectedItem().toString();
        String major = spnMajor.getSelectedItem().toString();
        String gpaStr = etGpa.getText().toString().trim();
        int expYear = spnExpYear.getSelectedItemPosition(); // Vị trí của năm trong Spinner
        String gender = getSelectedGender();

        // Kiểm tra các trường không được để trống
        if (id.isEmpty()) {
            etStudentId.setError("Mã sinh viên không được để trống!");
            return;
        }

        if (fullName.isEmpty()) {
            etFullName.setError("Họ và tên không được để trống!");
            return;
        }

        if (email.isEmpty()) {
            etEmail.setError("Email không được để trống!");
            return;
        }

        // Kiểm tra định dạng ngày sinh
        if (!isValidDate(birthDate)) {
            etBirthDate.setError("Ngày sinh không hợp lệ! Vui lòng nhập theo định dạng dd/MM/yyyy");
            return;
        }

        // Kiểm tra GPA
        if (gpaStr.isEmpty()) {
            etGpa.setError("GPA không được để trống!");
            return;
        }

        double gpa;
        try {
            gpa = Double.parseDouble(gpaStr);
            if (gpa < 0 || gpa > 4.0) {
                etGpa.setError("GPA phải nằm trong khoảng từ 0 đến 4.0!");
                return;
            }
        } catch (NumberFormatException e) {
            etGpa.setError("GPA phải là một số!");
            return;
        }

        int year = Integer.parseInt(String.valueOf(spnExpYear.getSelectedItemPosition()));
        // Tạo đối tượng sinh viên
        Student student = new Student(id, new Student.FullName(fullName, "", ""), gender, birthDate, email, address, major, gpa, year+1);

        // Đọc danh sách sinh viên từ SharedPreferences
        loadStudentList();

        // Thêm sinh viên vào danh sách
        studentList.add(student);

        // Lưu vào SharedPreferences
        saveToSharedPreferences();

        // Hiển thị thông báo
        Toast.makeText(this, "Thêm sinh viên thành công!", Toast.LENGTH_SHORT).show();

        setResult(RESULT_OK);
        finish();
    }

    private void loadStudentList() {
        SharedPreferences sharedPreferences = getSharedPreferences("student_prefs", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("student_list", null);
        Type type = new TypeToken<List<Student>>() {}.getType();

        if (json != null) {
            studentList = gson.fromJson(json, type);
        } else {
            studentList = new ArrayList<>(); // Khởi tạo danh sách mới nếu chưa có dữ liệu
        }
    }

    private void saveToSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("student_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Chuyển danh sách sinh viên thành chuỗi JSON
        Gson gson = new Gson();
        String json = gson.toJson(studentList);

        editor.putString("student_list", json);
        editor.apply();
    }

    private boolean isValidDate(String date) {
        // Kiểm tra định dạng ngày tháng năm
        String regex = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(\\d{4})$";
        return date.matches(regex);
    }

    private String getSelectedGender() {
        int selectedId = radioGroupGender.getCheckedRadioButtonId();
        if (selectedId != -1) { // Kiểm tra xem có radio button nào được chọn không
            RadioButton selectedRadioButton = findViewById(selectedId);
            return selectedRadioButton.getText().toString();
        }
        return ""; // Trả về chuỗi rỗng nếu không có giới tính được chọn
    }


}