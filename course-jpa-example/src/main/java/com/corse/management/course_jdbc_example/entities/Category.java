package com.corse.management.course_jdbc_example.entities;

public class Category {

    private int id;
    private String title;
    private String desription;

    public Category(int id, String title, String desription) {
        this.id = id;
        this.title = title;
        this.desription = desription;
    }

    public Category() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesription() {
        return desription;
    }

    public void setDesription(String desription) {
        this.desription = desription;
    }
}
