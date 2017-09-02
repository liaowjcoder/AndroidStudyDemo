package com.zeal.annotationandreflect.annotation03;

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
 *
 * @Target 它是一个元注解，也就是注解的注解；
 * 如果一个注解没使用@Target去描述，那么这个注解就可以使用任何位置，
 * 而使用了@Target描述的注解，就可以将该注解的使用约束在指定的位置
 * 上去使用。
 *
 * 注意:它只能用于描述注解，而不是直接使用在非注解的声明上。
 * @Targt 内的属性只能出现一次，否则会编译失败,如下：
 *
 * @Target ({{ElementType.FIELD, ElementType.METHOD, ElementType.FIELD}})
 * public @interface AnnotationTest{
 *
 * }
 *
 */

@Retention(RetentionPolicy.RUNTIME)
//只能用在字段上的注解
@Target(ElementType.FIELD)
public @interface AnnotationFile {

    String getMessage() default "hello java";

}
