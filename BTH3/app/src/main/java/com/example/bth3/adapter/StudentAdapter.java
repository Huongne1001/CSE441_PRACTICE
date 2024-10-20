package com.example.bth3.adapter;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.bth3.R;
import com.example.bth3.entities.Student;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private List<Student> studentList;

    public StudentAdapter(List<Student> studentList) {
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = studentList.get(position);
        if (student.getGender().equals("Nữ")) {
            holder.imgStudent.setImageResource(R.drawable.female);
        }
        else {
            holder.imgStudent.setImageResource(R.drawable.male);
        }

        // Sử dụng trim() để loại bỏ dấu cách
        String lastName = student.getFull_name().getLast().trim();
        String middleName = student.getFull_name().getMidd().trim();
        String firstName = student.getFull_name().getFirst().trim();

        holder.tvStudentName.setText(lastName + " " + middleName + " " + firstName);
        holder.tvStudentID.setText(student.getId());
        holder.tvGpa.setText(String.valueOf(student.getGpa()));
        // Xử lý sự kiện khi click vào item để hiển thị chi tiết
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), StudentDetailActivity.class);
                intent.putExtra("STUDENT_DATA", student); // Chuyển dữ liệu sinh viên
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView tvStudentName, tvStudentID, tvGpa;
        ImageView imgStudent;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStudentName = itemView.findViewById(R.id.textViewName);
            tvStudentID = itemView.findViewById(R.id.textViewId);
            imgStudent = itemView.findViewById(R.id.imageViewStudent);
            tvGpa = itemView.findViewById(R.id.textViewGpa);
        }
    }


}

