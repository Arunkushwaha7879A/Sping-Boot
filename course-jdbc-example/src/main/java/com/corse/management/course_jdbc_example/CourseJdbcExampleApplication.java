package com.corse.management.course_jdbc_example;

import com.corse.management.course_jdbc_example.dao.CategoryDao;
import com.corse.management.course_jdbc_example.dao.CourseDao;
import com.corse.management.course_jdbc_example.entities.Category;
import com.corse.management.course_jdbc_example.entities.Course;
import com.corse.management.course_jdbc_example.entities.CourseCategoryData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class CourseJdbcExampleApplication implements CommandLineRunner {
    @Autowired
    CategoryDao categoryDao;

    @Autowired
    CourseDao courseDao;

    //implement isilie kia taki hum categorydao ko use kar sake

	public static void main(String[] args) {
		SpringApplication.run(CourseJdbcExampleApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        System.out.println("application started");

        //category create karne ja rhe hai
//        Category category = new Category();
//        category.setId(102);
//        category.setTitle("new ");
//        category.setDesription("this is a trending new");
//
//        Category savedcategory= categoryDao.save(category);
//        System.out.println("category created with id"+ savedcategory.getId());




//        List<Category> categoryList=categoryDao.getAll();
//
//        categoryList.forEach(category -> {
//            System.out.println(category.getTitle()+"\t" + category.getId());
//        });




//        Category category = categoryDao.get(101);
//        System.out.println(category.getTitle());




//        categoryDao.delete(102);


//        Course course = new Course();
//        course.setCategoryId(101);
//        course.setCourseId(10003);
//        course.setTitle("spring boot-live");
//        course.setDescription("first spring boot - live");
//        course.setPrice(5000);
//
//        courseDao.save(course);



//        Course course = courseDao.get(10002);
//        System.out.println(course.getDescription());


//        List<Course> courseByCategory = courseDao.getCourseByCategory(101);
//        courseByCategory.forEach(course -> System.out.println(course.getTitle()));

        List<CourseCategoryData> courseCategoryData = courseDao.courseCategoryData();
        courseCategoryData.forEach(data -> {
            System.out.println(data.getCategoryTitle() + "\t" + data.getCourseTitle());
        });



    }
}
