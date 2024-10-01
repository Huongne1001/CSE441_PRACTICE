package com.example.ex02;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    Button btncall;

    public MainActivity() {
        super();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "CR424 - on Destroy()", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this," CR424 - onStop", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this," CR424 - onPause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this," CR424 - onStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this," CR424 - onRestart", Toast.LENGTH_SHORT).show()

    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this," CR424 - onResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this,"CR424 - onCreat()",Toast.LENGTH_SHORT).show();
        btncall = findViewById(R.id.btncall);
        btncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// TODO Auto-generated method stub
                Intent intent1 = new Intent(MainActivity.this,subactivity.class);
                startActivity(intent1);
            }
        });
    }
    }