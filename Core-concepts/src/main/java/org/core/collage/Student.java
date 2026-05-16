package org.core.collage;

import org.springframework.stereotype.Component;

@Component
public class Student {

    public Student() {
        System.out.println("creating student object");
    }

    public void  show(){
        System.out.println("i am a studet");
    }

}
