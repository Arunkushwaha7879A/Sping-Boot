package org.core.concepts;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("car")
public class Car implements InitializingBean  , DisposableBean {

//    @Autowired
     Engine engine;

    public Engine getEngine() {
        return engine;
    }
//@Autowired
    public Car(Engine engine) {
        this.engine = engine;
        System.out.println("setting using constructor");
    }


    public Car() {
        System.out.println("car is instansiated");
    }

        @Autowired
    public void setEngine(Engine engine) {
        this.engine = engine;
        System.out.println("setting injection");
    }

    //manually creating object and injecting to it.
//    Engine engine = new Engine(); //dependency

    public void start(){
        engine.startengine();
        System.out.println("car started");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //after property set this method called automatically as callback
        //mtlb property set ho chuki hai ab app apna kuch kaam karlo ye hame
        //initializingbean ne dia hai

        System.out.println("we are in after property set");
        System.out.println(engine);
        System.out.println("-------------------");
    }

    @Override
    public void destroy() throws Exception {
        //just before the destruction of bean ye method chalega
        System.out.println("car bean is going to destroy");
        System.out.println("-------------------");
    }
}
