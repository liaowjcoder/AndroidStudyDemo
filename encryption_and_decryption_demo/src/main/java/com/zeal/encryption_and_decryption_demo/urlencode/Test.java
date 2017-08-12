package com.zeal.encryption_and_decryption_demo.urlencode;

import java.net.URLDecoder;
import java.net.URLEncoder;

public class Test {

    //带有中文或者特殊字符的url中
    public static void main(String[] args) {


        String url = "http://www.baidu.com?serarch=\\\"哈哈\\\"&key=value";

        String encode = URLEncoder.encode(url);
        //encode:http%3A%2F%2Fwww.baidu.com%3Fserarch%3D%5C%22%E5%93%88%E5%93%88%5C%22%26key%3Dvalue
        System.out.println("encode:"+encode);


        String decode = URLDecoder.decode(encode);
        System.out.println("encode:"+decode);
    }

}
