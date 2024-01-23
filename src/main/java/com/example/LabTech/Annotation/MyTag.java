package com.example.LabTech.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // Specifies where the annotation can be used (in this case, on methods)
@Retention(RetentionPolicy.RUNTIME) // Specifies how long the annotation should be retained (in this case, at runtime)
public @interface MyTag {

    String value() default "";
}
