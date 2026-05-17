package com.jpa.example.course_jpa_example.Repository;

import com.jpa.example.course_jpa_example.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User , Integer> {

    //Quermethod:
    //returntype findBy <property> (Type value);
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailOrderByNameAsc(String email);
    List<User> findByName(String name);
    List<User> findByNameIgnoreCase(String name);
    List<User> findByNameAndEmail(String name , String email);
    List<User> findByNameAndEmailAllIgnoreCase(String name , String email);
    List<User> findByNameOrEmail(String name , String email);
    int countByName(String name);
    boolean existsByEmail(String email);
    List<User> findByNameContaining(String nameKeyword);
    List<User> findByNameLike(String pattern);


    //manual defined query:

    @Query(value = "select * from jb_user" , nativeQuery = true)
    public List<User> getUsers();

    @Query(value = "select * from jb_user where email=:email" , nativeQuery = true)
    public List<User> getUsersByEmail(@Param("email") String email);

    @NativeQuery( "select * from jb_user where email =:email AND name =:name")
    public List<User> getUsersByEmailAndName(@Param("email") String email , @Param("name") String name);


//    {HQL/JQL}

    @Query("SELECT u FROM User u WHERE u.email =:email AND u.name =:name")
    public List<User> getUser1();

    @Query("SELECT u FROM User u WHERE u.address.country =:country")
    public List<User> getUserBycountry(@Param("country") String country);

}
