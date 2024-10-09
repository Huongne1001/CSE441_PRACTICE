package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class Employee implements Parcelable {
    private int id;
    private String name;
    private String dateOfBirth;
    private double salary;

    public Employee(int id, String name, String dateOfBirth, double salary) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
    }

    protected Employee(Parcel in) {
        id = in.readInt();
        name = in.readString();
        dateOfBirth = in.readString();
        salary = in.readDouble();
    }

    public static final Creator<Employee> CREATOR = new Creator<Employee>() {
        @Override
        public Employee createFromParcel(Parcel in) {
            return new Employee(in);
        }

        @Override
        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(dateOfBirth);
        parcel.writeDouble(salary);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
