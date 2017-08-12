package com.example.base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;

/**
 * Created by zeal on 2017/8/11.
 */

public class Test {

    public static void main(String[] args) {

        try {
            byte[] bytes = null;

            Person person = new Person("zeal", 25);

            ByteArrayOutputStream bo = new ByteArrayOutputStream();

            ObjectOutputStream oos = new ObjectOutputStream(bo);

            oos.writeObject(person);

            bytes = bo.toByteArray();





            oos.close();
            bo.close();


            ByteArrayInputStream bi = new ByteArrayInputStream(bytes);

            ObjectInputStream oi = new ObjectInputStream(bi);

            Person person2  = (Person) oi.readObject();

            System.out.println(person2);


        } catch (Exception e) {

        }
    }
}
