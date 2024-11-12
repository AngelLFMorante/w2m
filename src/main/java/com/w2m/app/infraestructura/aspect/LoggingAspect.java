package com.w2m.app.infraestructura.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.w2m.app.application.service.SpacecraftService.getSpacecraftById(..)) && args(id)")
    public void logSpacecraftWithNegativeId(Long id){
        if(id < 0){
            System.out.println("WARNING: A ship with a negative ship ID was requested: " + id);
        }
    }
}
