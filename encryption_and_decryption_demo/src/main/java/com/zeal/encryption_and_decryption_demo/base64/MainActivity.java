package com.zeal.encryption_and_decryption_demo.base64;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.widget.ImageView;

import com.zeal.encryption_and_decryption_demo.R;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            //Base64的应用：将一个对象的byte数组进行base64加密


            //从资源文件中加载出一个Bitmap
            Bitmap sourceBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.a);

            //将该Bitamp进行base64加密为byte数组
            ByteArrayOutputStream bo = new ByteArrayOutputStream();


            //注意：不能使用ObjectOutputStream.writeObject(bitmap)

            sourceBitmap.compress(Bitmap.CompressFormat.PNG,100,bo);

            byte[] sourceBitmapByteArr = bo.toByteArray();

            bo.close();

            String encodeBitmapString = Base64.encodeToString(sourceBitmapByteArr, Base64.DEFAULT);
            System.out.println(encodeBitmapString);

            //----------将encodeBitmapString网络传输到其他地方使用----------

            //将加密后的字符串通过base64进行解密为String
            byte[] decodeBitmapByteArr = Base64.decode(encodeBitmapString,Base64.DEFAULT);


            //将byte数组转化为bitmap对象

            Bitmap decodeBitmap = BitmapFactory.decodeByteArray(decodeBitmapByteArr, 0, decodeBitmapByteArr.length);

            //设置到imgaeview中显示

            ImageView imageView = (ImageView) findViewById(R.id.imageview);
            imageView.setImageBitmap(decodeBitmap);
        }catch (Exception e){
            e.printStackTrace();
        }






    }
}
