package com.corse.management.course_jdbc_example.dao;

import com.corse.management.course_jdbc_example.entities.Category;
import com.corse.management.course_jdbc_example.entities.Course;
import com.corse.management.course_jdbc_example.entities.CourseCategoryData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CourseDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    ///TRANSACTION - FIRST INSERT CATEGORY THEN ONLY INSERT COURSE
    @Transactional
    public void saveCategoryThanCourse(Category category , Course course){

        //insert category code
        //raete category query
        //jdbc fire update


        //insert course code
        //raete course query
        //jdbc fire update


    }


    ///insert course in a batch

    public void saveCourseInBatch(List<Course> list){
        //single course
        String query = "insert into courses(courseId, title, description, price, category_id) values(?,?,?,?,?)";

        jdbcTemplate.batchUpdate(query , list , list.size() , (ps , course)->{
            ps.setInt(1 , course.getCourseId());
            ps.setString(2, course.getTitle());
            ps.setString(3, course.getDescription());
            ps.setString(4, course.getPrice());
            ps.setString(5, course.getCategoryId());

        });
    }

    //create course
    public Course save(Course course){
        String query =
                "insert into courses(courseId, title, description, price, category_id) values(?,?,?,?,?)";

        int row = jdbcTemplate.update(
                query,
                course.getCourseId(),
                course.getTitle(),
                course.getDescription(),
                course.getPrice(),
                course.getCategoryId()
        );

        System.out.println(row + " row affected");

        return course;
    }


    //get all course

    public List<Course> getAll(){
        String query = "select * from courses";
        List<Course> courses = jdbcTemplate.query(query, (rs, num) -> {
            Course course = new Course();

            course.setCourseId(rs.getInt("courseId"));
            course.setTitle(rs.getString("title"));
            course.setDescription(rs.getString("description"));
            course.setCategoryId(rs.getInt("categoryId"));
            return course;
        });
        return  courses;
    }

    //get single course

    //GET SINGLE COURSE

    //GET SINGLE COURSE

    public Course get(int courseId){

        String query =
                "select * from courses where courseId = ?";

        Course course1 =
                jdbcTemplate.queryForObject(query, (rs, num) -> {
                    Course c = new Course();
                    c.setCourseId(rs.getInt("courseId"));
                    c.setTitle(rs.getString("title"));
                    c.setDescription(rs.getString("description"));
                    c.setPrice(rs.getInt("price"));
                    c.setCategoryId(rs.getInt("category_id"));
                    return c;
                        },
                        courseId
                );
        return course1;
    }

    //update course

    //UPDATE COURSE

    public Course update(int courseId, Course newCourse){

        String query =
                "update courses set title=?, description=?, price=?, category_id=? where courseId=?";

        int rows = jdbcTemplate.update(
                query,
                newCourse.getTitle(),
                newCourse.getDescription(),
                newCourse.getPrice(),
                newCourse.getCategoryId(),
                courseId
        );

        System.out.println(rows + " row updated");

        newCourse.setCourseId(courseId);

        return newCourse;
    }

    //delete course

    public void delete(int courseId){

        String query =
                "delete from courses where courseId=?";

        int rows =
                jdbcTemplate.update(query, courseId);

        System.out.println(rows + " row deleted");
    }

    public List<Course> getCourseByCategory(int categoryId){
        String query = "select * from courses where category_Id=?";
        List<Course> courseList = jdbcTemplate.query(query , (rs, num) -> {
                    Course c = new Course();
                    c.setCourseId(rs.getInt("courseId"));
                    c.setTitle(rs.getString("title"));
                    c.setDescription(rs.getString("description"));
                    c.setPrice(rs.getInt("price"));
                    c.setCategoryId(rs.getInt("category_id"));
                    return c;
                },
                categoryId
        );
        return courseList;
    }



//inner jion
    public List<CourseCategoryData> courseCategoryData(){
        String query = "select\n" +
                "    c.title as categoryTitle,\n" +
                "    c.description as categoryDescription,\n" +
                "    co.title as courseTitle,\n" +
                "    co.description as courseDescription,\n" +
                "    co.price as coursePrice\n" +
                "\n" +
                "from categories c\n" +
                "\n" +
                "inner join courses co\n" +
                "on c.id = co.category_id;";

        List<CourseCategoryData> query1 = jdbcTemplate.query(query, (rs, rowNum) -> {
            CourseCategoryData data = new CourseCategoryData();

            data.setCategoryTitle(rs.getString("categoryTitle"));

            data.setCategoryDescription(rs.getString("categoryDescription"));

            data.setCourseTitle(rs.getString("courseTitle"));

            data.setCourseDescription(rs.getString("courseDescription"));

            data.setCoursePrice(rs.getInt("coursePrice"));

            return data;
        });
        return query1;
    }





}
