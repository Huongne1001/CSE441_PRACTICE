package com.example.recyclerviewdemo;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<LandMark> landmarks = new ArrayList<>();
    List<IntroSlide> introSlides = new ArrayList<>();


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

        //Khởi tạo danh sách
        //landmarks.add(new LandMark(R.mipmap.anh, "Tháp Eiffel"));
        //landmarks.add(new LandMark(R.mipmap.anh, "Cung điện Buckingham"));
        //landmarks.add(new LandMark(R.mipmap.anh, "Tượng Nữ thần Tự do"));

        //Khởi tạo danh sách
        introSlides.add(new IntroSlide(R.mipmap.anh, "Chào mừng!", "Đây là ứng dụng giới thiệu sản phẩm tuyệt vời của chúng tôi."));
        introSlides.add(new IntroSlide(R.mipmap.anh, "Chào mừng!", "Đây là ứng dụng giới thiệu sản phẩm tuyệt vời của chúng tôi."));
        introSlides.add(new IntroSlide(R.mipmap.anh, "Chào mừng!", "Đây là ứng dụng giới thiệu sản phẩm tuyệt vời của chúng tôi."));

    }
}