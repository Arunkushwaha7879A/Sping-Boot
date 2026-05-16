package org.core.qualifier;

import org.springframework.stereotype.Component;

@Component
public class Pepsi implements ColdDrink{

    @Override
    public void drink() {
        System.out.println("drinking pepsi");
    }
}
