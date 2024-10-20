package com.example.btth04;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.btth04.entities.Student;

import java.util.ArrayList;

public class StudentAdapter extends ArrayAdapter<Student> {

    public StudentAdapter(Context context, ArrayList<Student> students) {
        super(context, R.layout.item_student, students); // Replace with your custom layout resource ID
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Inflate the custom layout
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.item_student, parent, false);

        // Get student data for this position
        Student student = getItem(position);

        // Find views in the custom layout (e.g., TextViews for name and email)
        TextView nameText = (TextView) customView.findViewById(R.id.textViewName);
        TextView classText = (TextView) customView.findViewById(R.id.textViewClass);
        TextView gpaText = (TextView) customView.findViewById(R.id.textViewGPA);

        // Set student data to the views
        nameText.setText(student.getName());
        classText.setText(student.getStudentClass());
        gpaText.setText(String.valueOf(student.getGpa()));

        // You can add additional views and set their data here if needed

        // Return the complete view
        return customView;
    }
}
