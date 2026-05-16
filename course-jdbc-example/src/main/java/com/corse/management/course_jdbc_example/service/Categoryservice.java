package com.corse.management.course_jdbc_example.service;

import com.corse.management.course_jdbc_example.dao.CategoryDao;
import com.corse.management.course_jdbc_example.dao.CourseDao;
import com.corse.management.course_jdbc_example.entities.Category;
import com.corse.management.course_jdbc_example.entities.Course;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Categoryservice {

    private CategoryDao categoryDao;
    private CourseDao courseDao;

    public Categoryservice(CategoryDao categoryDao, CourseDao courseDao) {
        this.categoryDao = categoryDao;
        this.courseDao = courseDao;
    }

    @Transactional
    public void saveCategorywithcourse(Category category , Course course){
        categoryDao.save(category);
        courseDao.save(course);
    }
}
