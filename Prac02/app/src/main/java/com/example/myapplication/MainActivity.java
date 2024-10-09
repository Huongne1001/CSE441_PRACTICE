package com.example.myapplication;

import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Danh sách lưu trữ thông tin các nhân viên
    private ArrayList<Employee> employeeList = new ArrayList<>();
    private EditText etId, etName, etDob, etSalary;
    private TextView tvEmployeeInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các view trong layout
        etId = findViewById(R.id.et_id);
        etName = findViewById(R.id.et_name);
        etDob = findViewById(R.id.et_dob);
        etSalary = findViewById(R.id.et_salary);
        Button btnSubmit = findViewById(R.id.btn_submit);
        tvEmployeeInfo = findViewById(R.id.tv_employee_info);

        // Khôi phục dữ liệu nếu Activity bị khởi tạo lại (ví dụ xoay màn hình)
        if (savedInstanceState != null) {
            employeeList = savedInstanceState.getParcelableArrayList("employeeList");
            displayEmployeeInfo();
        }

        // Xử lý khi người dùng nhấn nút "Nhập thông tin"
        btnSubmit.setOnClickListener(view -> {
            String idStr = etId.getText().toString();
            String name = etName.getText().toString();
            String dob = etDob.getText().toString();
            String salaryStr = etSalary.getText().toString();

            if (!idStr.isEmpty() && !salaryStr.isEmpty()) {
                int id = Integer.parseInt(idStr);
                double salary = Double.parseDouble(salaryStr);

                // Tạo đối tượng Employee và thêm vào danh sách
                Employee employee = new Employee(id, name, dob, salary);
                employeeList.add(employee);

                // Hiển thị thông tin các nhân viên
                displayEmployeeInfo();

                // Xóa các trường nhập liệu sau khi nhập xong
                etId.setText("");
                etName.setText("");
                etDob.setText("");
                etSalary.setText("");
            } else {
                Toast.makeText(MainActivity.this, "Vui lòng nhập đầy đủ thông tin hợp lệ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Lưu trạng thái khi Activity bị hủy hoặc thay đổi vòng đời
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("employeeList", employeeList);
    }

    // Hiển thị thông tin danh sách các nhân viên trên TextView
    private void displayEmployeeInfo() {
        StringBuilder info = new StringBuilder();
        for (Employee employee : employeeList) {
            info.append("ID: ").append(employee.getId()).append("\n")
                    .append("Tên: ").append(employee.getName()).append("\n")
                    .append("Ngày sinh: ").append(employee.getDateOfBirth()).append("\n")
                    .append("Lương: ").append(employee.getSalary()).append("\n\n");
        }
        tvEmployeeInfo.setText(info.toString());
    }
}
