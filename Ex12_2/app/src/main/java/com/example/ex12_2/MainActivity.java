package com.example.ex12_2;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    //Khai báo các biến
    ListView lv;
    ArrayList<String> arraywork;
    ArrayAdapter<String> arrAdapater;
    EditText edtwork,edth,edtm;
    TextView txtdate;
    Button btnwork;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edth = findViewById(R.id.edthour);
        edtm = findViewById(R.id.edtmi);
        edtwork = findViewById(R.id.edtwork);
        btnwork = findViewById(R.id.btnadd);
        lv = findViewById(R.id.listView1);
        txtdate = findViewById(R.id.txtdate);
        //Ở đây chúng ta không sử dụng dữ liệu cố định, mà dữ liệu của //Listview được cập nhật trong quá trình chạy, nên ở đây ta
        //khai báo mảng ArrayList kiểu String rỗng
        arraywork = new ArrayList<>();
        //Khai báo ArrayAdapter, đưa mảng dữ liệu vào ArrayAdapter
        arrAdapater = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arraywork);
        //Đưa Adapter có dữ liệu lên Listview
        lv.setAdapter(arrAdapater);
        //Lấy ngày giờ hệ thống
        Date currentDate = Calendar.getInstance().getTime();
        //Format theo định dạng dd/mm/yyyy
        java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy");
        //Hiển thị lên TextView
        txtdate.setText("Hôm Nay: "+simpleDateFormat.format(currentDate));
        //Viết phương thức khi Click vào Button btnwork
        btnwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Nếu 1 trong 3 Edit Text không có nội dung thì hiện lên thông báo bằng hộp thoại Dialog
                if(edtwork.getText().toString().equals("")||edth.getText().toString().equals("")
                        ||edtm.getText().toString().equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Info missing");
                    builder.setMessage("Please enter all information of the work");
                    builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.show();
                }
                //Lấy nội dung công việc và thời gian ra từ Edit Text và đưa vào Mảng arraywork, cập nhật lại Adapter
                else {
                    String str = edtwork.getText().toString() + " - "+ edth.getText().toString() + ":"+edtm.getText().toString();
                    //Để Thêm dữ liệu cho Listview, chúng ta cần 2 bước:
                    //Cập Thêm liệu cho mảng
                    arraywork.add(str); //Xóa: arraywork.remove(i)
                    //Cập nhật lại Adapter
                    arrAdapater.notifyDataSetChanged();
                    edth.setText("");
                    edtm.setText("");
                    edtwork.setText("");
                }
            }
        });
    }
}