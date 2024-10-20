package com.example.bth3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bth3.adapter.StudentAdapter;
import com.example.bth3.entities.Student;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private StudentAdapter studentAdapter;
    private List<Student> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Lấy danh sách sinh viên từ SharedPreferences
        loadStudentList();

        // Nếu danh sách sinh viên không null
        if (studentList != null) {
            studentAdapter = new StudentAdapter(studentList);
            recyclerView.setAdapter(studentAdapter);
        } else {
            Log.e("MainActivity", "Student list is null");
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

        FloatingActionButton fab = findViewById(R.id.fabAddStudent);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Chuyển đến màn hình tạo mới sinh viên
                Intent intent = new Intent(MainActivity.this, CreateActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadStudentList() {
        SharedPreferences sharedPreferences = getSharedPreferences("student_prefs", MODE_PRIVATE);
        String json = sharedPreferences.getString("student_list", null);
        Type type = new TypeToken<ArrayList<Student>>() {}.getType();
        studentList = new Gson().fromJson(json, type);
        if (studentList == null) {
            studentList = new ArrayList<>();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_sort) {
            Toast.makeText(this, "Sắp xếp", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.sortById) {
            Toast.makeText(this, "Sắp xếp theo mã SV", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.sortByGpa) {
            Toast.makeText(this, "Sắp xếp theo điểm", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
    @Override
    protected void onResume() {
        super.onResume();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Lấy danh sách sinh viên từ SharedPreferences
        loadStudentList();

        // Nếu danh sách sinh viên không null
        if (studentList != null) {
            studentAdapter = new StudentAdapter(studentList);
            recyclerView.setAdapter(studentAdapter);
        } else {
            Log.e("MainActivity", "Student list is null");
        }// Tải lại danh sách khi trở về MainActivity
    }

}