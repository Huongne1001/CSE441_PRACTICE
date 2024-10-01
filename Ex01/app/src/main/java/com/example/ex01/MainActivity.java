package com.example.ex01;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    // Khai báo các biến giao diện tại đây
    EditText edta, edtb, edtKQ;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
// Ánh xạ Id cho các biến giao diện
        edta = findViewById(R.id.edta);
        edtb = findViewById(R.id.edtb);
        edtKQ = findViewById(R.id.edtKQ);
        btn = findViewById(R.id.btn);
// Xử lý tương tác với người dùng
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = Integer.parseInt(edta.getText().toString()); //
                //Lấy dữ liệu từ edta, ép sang kiểu int và gán vào biến a
                int b = Integer.parseInt(edtb.getText().toString());
                int c = a + b;
                edtKQ.setText(c+""); // Hiển thị kết quả
            }
        });
    }
}
