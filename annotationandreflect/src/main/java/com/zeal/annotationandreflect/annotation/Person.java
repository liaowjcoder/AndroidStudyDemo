package com.zeal.annotationandreflect.annotation;

class Person {

    //这个注解表示过期的意思，不建议开发者去调用了。
    @Deprecated
    public void getPersonInfo() {

    }


    private int age;

    private String name;


    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode()+this.age;
    }

    //如果将参数Object类型变成Person类型的话，那么系统在读取 equals 时就不会去读该方法
    //而是读取 Object 中的 equals 方法去判断。
//    @Override
    public boolean equals(Person obj) {

        if (obj instanceof Person) {
            Person person = (Person) obj;

            if (person == this) {
                return true;
            }

            if (person.name.equals(this.name) && person.age == this.age) {
                return true;
            }

        }

        return super.equals(obj);
    }
}
