package com.github.herouu.trainhigh.design.factory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class HandleFactory {

    @Autowired
    private Map<String, AbstractHandle> map;

    public IHandler getHandler(String beanName){
        return map.get(beanName);
    }
}
