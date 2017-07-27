package com.zeal.ipc4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by liaowj on 2017/7/5.
 *
 *
 * user:com.zeal.ipc4.User@74a14482
 * zeal--25--true
 * d_user:com.zeal.ipc4.User@214c265e
 * zeal--25--true
 *
 * 序列化和反序列化得到的结果 User 对象是一样的，但是地址值是不一样的。
 *
 * serialVersionUID：使用保证反序列化和序列化前的数据格式是一致的，可以减少反序列失败问题。
 *
 *
 * 1.静态成员是属于类的因此不会参加到序列化过程中。
 * 2.transient 表示的成员变量不参与序列化。
 */

public class Main {

    public static void main(String[] args) {
        User user = new User("zeal", 25, true);
        try {
            serialization(user);

            User d_user = deserialization();

            System.out.println("user:" + user + "\n" +
                    user.name + "--" + user.age + "--" + user.isMale
            );


            System.out.println("d_user:" + d_user + "\n" +
                    d_user.name + "--" + d_user.age + "--" + d_user.isMale);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    序列化 user 对象
     */
    public static void serialization(User user) throws Exception {
        File file = new File("d://user.txt");

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));

        oos.writeObject(user);

        oos.close();
    }

    /*
    反序列化
     */
    public static User deserialization() throws Exception {
        File file = new File("d://user.txt");

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));

        User user = (User) ois.readObject();
        ois.close();
        return user;
    }
}
