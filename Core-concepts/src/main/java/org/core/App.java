package org.core;

import org.core.Scopes.Samosa;
import org.core.collage.Student;
import org.core.collage.Teacher;
import org.core.concepts.Car;
import org.core.concepts.ConfigClass;
import org.core.concepts.Engine;
import org.core.lifecycle.UserDao;
import org.core.qualifier.Human;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

//        ApplicationContext container = new ClassPathXmlApplicationContext("config.xml");
        //container initialise ho jata hai aur object ko bna leta hai

        AnnotationConfigApplicationContext container = new AnnotationConfigApplicationContext(ConfigClass.class);

//        Engine engine1 = container.getBean("engine1" , Engine.class);
//        engine1.startengine();
//
//        //object kahi banaya hee ni , wo object spring container ne bnaya
//
//        System.out.println("------------------");
//
//        Car car = container.getBean("car" , Car.class);
//        car.start();
//
//        Student student = container.getBean("student" , Student.class);
//        System.out.println(student);
//        student.show();
//
//        Teacher teacher= container.getBean("teacher" , Teacher.class);
//        System.out.println(teacher);
//
//        Human kartik =container.getBean("human" , Human.class);
//        kartik.tryColdDrink();

//        Car car = container.getBean("car" , Car.class);
//        car.start();

//        UserDao userDao=container.getBean(" userDao", UserDao.class);
//        userDao.saveUser();


        Samosa samosa = container.getBean("samosa"  , Samosa.class);
        Samosa samosa1 = container.getBean("samosa"  , Samosa.class);
        Samosa samosa2 = container.getBean("samosa"  , Samosa.class);
        System.out.println(samosa);
        System.out.println(samosa1);
        System.out.println(samosa2);


//        container.close();
//        container.registerShutdownHook();
    }
}
