package com.javarush.test.level29.lesson15.big01.human;

import java.util.ArrayList;
import java.util.List;

public class University {
    private List<Student> students = new ArrayList<>();
    private String name;
    private int age;

    public University(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        for (Student student : students) {
            if (student.getAverageGrade() == averageGrade)
                return student;
        }
        return null;
    }

    public Student getStudentWithMaxAverageGrade() {
        Student student = null;
        double max = 0;
        for (Student student1 : students) {
            if (student1.getAverageGrade() > max) {
                max = student1.getAverageGrade();
                student = student1;
            }
        }
        return student;
    }

    public Student getStudentWithMinAverageGrade() {
        Student student = null;
        double min = students.get(0).getAverageGrade();
        for (Student student1 : students) {
            if (student1.getAverageGrade() < min) {
                min = student1.getAverageGrade();
                student = student1;
            }
        }
        return student;
    }

    public void expel(Student student) {
        students.remove(student);
    }
}
