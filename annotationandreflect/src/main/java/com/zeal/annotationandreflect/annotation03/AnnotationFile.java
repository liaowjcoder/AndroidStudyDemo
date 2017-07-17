package com.zeal.annotationandreflect.annotation03;

import com.zeal.annotationandreflect.annotation02.AnnotationTest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by liaowj on 2017/7/17.
 *
 * ANNOTATION_TYPE
 * CONSTRUCTOR
 * FIELD
 * LOCAL_VARIABLE
 * METHOD
 * PACKAGE
 * PARAMETER
 * TYPE
 */

@Retention(RetentionPolicy.RUNTIME)
//只能用在字段上的注解
@Target(ElementType.FIELD)
public @interface AnnotationFile {

    String getMessage() default "hello java";

}
