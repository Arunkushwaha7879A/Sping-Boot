package com.corse.management.course_jdbc_example.entities;

public class Course {
    private int courseId;
    private String title;
    private String description;
    private int price;
    private int categoryId;

    public Course(int courseId, String title, String description, int price) {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public Course() {
    }

    public int getCourseId() {
        return courseId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
