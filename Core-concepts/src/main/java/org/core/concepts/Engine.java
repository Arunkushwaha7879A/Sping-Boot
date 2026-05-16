package org.core.concepts;

import org.springframework.stereotype.Component;

@Component("engine1")
public class Engine {
    public void startengine(){
        System.out.println("engine started");
    }

}
