package com.example.ex07_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    TextView txtKQ;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        txtKQ = findViewById(R.id.txtKQ);
        btnBack = findViewById(R.id.btnBack);

        //nhận intent
        Intent myIntent = getIntent();
        //lay bundle khoi intent
        Bundle myBundle = myIntent.getBundleExtra("mypackage");
        //lay du lieu khỏi bundle
        int a = myBundle.getInt("soa");
        int b = myBundle.getInt("sob");

        String nghiem = "";
        if(a == 0 && b==0){
            nghiem = "PT vo so nghiem";
        } else if (a==0 && b != 0) {
            nghiem = "PT vô nghiệm";
        }else {
            nghiem = "Ngiệm PT = "+ (-1.0)*b/a;
        }
        txtKQ.setText(nghiem);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}