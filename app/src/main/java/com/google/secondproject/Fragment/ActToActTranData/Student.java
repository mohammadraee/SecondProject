package com.google.secondproject.Fragment.ActToActTranData;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Student implements Parcelable {

    private String name;
    private String id;
    private int score;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public Student(String name, String id, int score) {
        this.name = name;
        this.id = id;
        this.score = score;
    }

    public static List<Student> samples(){
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("Ali","9825365742",17));
        studentList.add(new Student("Meysam","9825365743",18));
        studentList.add(new Student("Ebrahim","9825365744",19));
        studentList.add(new Student("Amin","9825365775",20));
        studentList.add(new Student("Babak","9825365746",16));
        studentList.add(new Student("Mosa","9825526742",15));
        studentList.add(new Student("Soheil","9895365742",18));
        studentList.add(new Student("Mehran","9812565742",20));
        studentList.add(new Student("Ghasem","9825365742",18));
        studentList.add(new Student("Javad","9826565742",14));
        studentList.add(new Student("Akbar","9825248742",16));
        studentList.add(new Student("Mohammad","9821265742",19));
        studentList.add(new Student("Mahdi","9825389742",17));
        studentList.add(new Student("Mahmod","9878365742",18));
        studentList.add(new Student("Hamid","9825363742",17));
        return studentList;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(id);
        dest.writeInt(score);
    }

    public Student(Parcel parcel){
        this.name= parcel.readString();
        this.id= parcel.readString();
        this.score=parcel.readInt();
    }

    public static Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel source) {
            return new Student(source);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };
}
