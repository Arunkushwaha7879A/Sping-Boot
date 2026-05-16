package com.corse.management.course_jdbc_example.dao;

import com.corse.management.course_jdbc_example.entities.Category;
import com.corse.management.course_jdbc_example.utils.CategoryRowMapper;
import jakarta.annotation.PostConstruct;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Repository
public class CategoryDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //table create karne ka tarike - ya toh constructor banaoo , ya toh init method using postcontruct,
    //ya schema.sql in rsource - aur yaha pe apni queries ko put kardo aur jaishee project chalega
    //spring auto detect karlega apki sari sql queries jo apne schema.sql ke andar rakhi hai

    //create table
    @PostConstruct
    public void init(){
        //10 baar object banaooge toh 10 baar table create ho jaeegi so use -create table if not exists  categories
        String createTableQuery = "create table if not exists  categories (id int primary key , title varchar(100)not null , description varchar(500)) ";
        jdbcTemplate.update(createTableQuery);
        System.out.println("table craeted: ");
    }

    //SAVE CATEGORY
    public Category save (Category category){
        //parameterized query
        String query = "insert into categories(id , title , description) values(?,?,?)";

        int rows = jdbcTemplate.update(
                query,
                category.getId(),
                category.getTitle(),
                category.getDesription()
        );
        System.out.println( rows + "affected");
        return category;
    }



    //UPADTE CATEGORY

    public Category update(int categoryId , Category newCat){
        String query = "update categories set title=?, description=? WHERE id = ?";
        int update = jdbcTemplate.update(query,
                newCat.getTitle(),
                newCat.getDesription(),
                categoryId
                );

        System.out.println("updated " + update);
        newCat.setId(categoryId);
        return newCat;
    }


    //GET ALL CATEGORY
    public List<Category> getAll(){
        String query = "select * from categories";
//        List<Map<String, @Nullable Object>> maps = jdbcTemplate.queryForList(query);

//        List<Category> categories = jdbcTemplate.queryForList(query, Category.class);

        List<Category> categories = jdbcTemplate.query(query , new CategoryRowMapper());
        return  categories;
    }


    //GET SINGLE CATEGORY BY ID

   public Category get(int categoryId){
       String query = "select * from categories where id = ?";
//       return jdbcTemplate.queryForObject(query ,
//               Category.class,
//               categoryId);

      return  jdbcTemplate.queryForObject(query, new CategoryRowMapper() , categoryId);
   }


    //DELETE CATEGORY

    public void delete(int categoryId){
        String query = "delete from categories where id =?";
        jdbcTemplate.update(query , categoryId);
    }
}
