package com.example.off_courses.models;

public class Course {
    int id;
    String img,title,date,level,description_text,student_amount,group_amount;

    public Course(int id, String img, String title, String date, String level, String description_text) {
        this.id = id;
        this.img = img;
        this.title = title;
        this.date = date;
        this.level = level;
        this.description_text = description_text;
    }

    public Course(int id, String img, String title, String date, String level) {
        this.id = id;
        this.img = img;
        this.title = title;
        this.date = date;
        this.level = level;
    }

    public String getDescription_text() {
        return description_text;
    }

    public void setDescription_text(String description_text) {
        this.description_text = description_text;
    }

    public String getStudent_amount() {
        return student_amount;
    }

    public void setStudent_amount(String student_amount) {
        this.student_amount = student_amount;
    }

    public String getGroup_amount() {
        return group_amount;
    }

    public void setGroup_amount(String group_amount) {
        this.group_amount = group_amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

}
