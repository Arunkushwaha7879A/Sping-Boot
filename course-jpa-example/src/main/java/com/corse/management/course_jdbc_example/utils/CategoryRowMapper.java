package com.corse.management.course_jdbc_example.utils;

import com.corse.management.course_jdbc_example.entities.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//logic o convert table row to category object
public class CategoryRowMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        //object category : blank
        Category category = new Category();

        //table se data nikalna hai aur category mein set karna hai
        category.setId(rs.getInt("id"));
        category.setTitle(rs.getString("title"));
        category.setDesription(rs.getString("description"));

        return category;
    }
}
