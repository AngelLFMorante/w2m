package com.w2m.app.infraestructura.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Aspect for logging spacecraft-related actions, particularly when a spacecraft
 * is requested with a negative ID.
 * This aspect intercepts method calls in the `SpacecraftService` where a spacecraft
 * ID is passed, and logs a warning if the ID is negative.
 *
 * @author Angel Lf Morante
 * @version 1.0
 */
@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Pointcut that matches methods in the SpacecraftService that receive a Long id
     * as parameter (specifically the `getSpacecraftById` method).
     *
     * @param id The spacecraft ID passed to the method
     */
    @Pointcut("execution(* com.w2m.app.application.service.SpacecraftService.getSpacecraftById(Long)) && args(id)")
    public void spacecraftByIdMethod(Long id) {
    }

    /**
     * Before advice that logs a warning when a spacecraft ID is negative.
     *
     * This method runs before the target method `getSpacecraftById` in
     * `SpacecraftService` is executed. It checks if the provided spacecraft
     * ID is negative and logs a warning message.
     *
     * @param id The spacecraft ID passed to the method.
     */
    @Before("spacecraftByIdMethod(id)")
    public void logSpacecraftWithNegativeId(Long id){
        if(id != null && id < 0){
           logger.warn("WARNING: A ship with a negative ship ID was requested: {}", id);
        }
    }
}
