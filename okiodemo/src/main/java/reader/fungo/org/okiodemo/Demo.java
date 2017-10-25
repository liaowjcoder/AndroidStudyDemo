package reader.fungo.org.okiodemo;

import java.io.File;

import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Sink;
import okio.Source;

/**
 */
public class Demo {
    public static void main(String[] args) {
//        sinkTest();
//        souceTest();
//


        copyTest();

//        byteStringTest();

    }


    private static void bufferTest() {

        try {
            //将数据缓存到buffer之后然后写入到sink对应的文件中，可以指定从buffer中写入几位的数据
            //最后要记得flush
            Buffer buffer = new Buffer();


//            buffer.writeUtf8("HELLO WORLD");

            buffer.writeByte(1);
            buffer.writeByte(2);
            byte b = buffer.readByte();
            System.out.println(b);
            buffer.writeByte(3);

            File file = new File("d://test.txt");


            BufferedSink sink = Okio.buffer(Okio.sink(file));
            //这里这可以指定需要写入3位
            sink.write(buffer, 3);
            //再写入4位
            sink.write(buffer, 4);
            //这里需要flush，不然的话是不会将数据从缓冲区中写入到文件中的
            sink.flush();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void byteStringTest() {


        /**
         * ByteString 是一个不可变的字节序列
         * 可以理解为一个工具类
         *
         * ByteString 对象的创建
         *  1.ByteString不能直接调用其构造方法
         *  2.通过 of(byte[]) 构造 byteString对象
         *  3.内部有一个 byte[] data 属性，其专门用于存放字节序列，所有的操作都是基于这个 data 属性的
         *  function:
         * 1.将 byte 数组转化为 base64/base64url/md5/sha1/sha256/sha521等字符串
         * 2.将一个String转化为base64/Hex/digest(algorithm)该参数可以是md5，sha1,sha256,sha512
         */
        String str = "A";

        //转化为base64编码
        ByteString byteString = ByteString.of(str.getBytes());

        String base64 = byteString.base64();
        System.out.println("字符串" + str + "base64:" + base64);//QQ==
        System.out.println("utf-8:" + byteString.utf8());//utf-8:A
        System.out.println(byteString.size());//1
        System.out.println(byteString.hex());//41

        //base64url
        String url = "https://www.baidu.com?name=未见哥哥";
        ByteString byteString1 = ByteString.of(url.getBytes());
        String base64Url = byteString1.base64Url();
        System.out.println("base64Url:" + base64Url);//base64Url:aHR0cHM6Ly93d3cuYmFpZHUuY29tP25hbWU95pyq6KeB5ZOl5ZOl

        System.out.println("sha1:" + byteString.sha1());//sha1:[hex=6dcd4ce23d88e2ee9568ba546c007c63d9131c1b]
        System.out.println("sha1 length:" + "6dcd4ce23d88e2ee9568ba546c007c63d9131c1b".length());//sha1 length:40
        System.out.println("sha256:" + byteString.sha256());//sha1:[hex=6dcd4ce23d88e2ee9568ba546c007c63d9131c1b]
        System.out.println("sha256 length:" + "559aead08264d5795d3909718cdd05abd49572e84fe55590eef31a88a08fdffd".length());//sha256 length:64


        //转化为md5  注意是带有[hex开头
        ByteString md5 = byteString.md5();
        System.out.println(md5);//[hex=7fc56270e7a70fa81a5935b72eacbe29]
        System.out.println(md5.toString());//[hex=7fc56270e7a70fa81a5935b72eacbe29]
        System.out.println("7fc56270e7a70fa81a5935b72eacbe29".length());//32
        System.out.println(new String(md5.toByteArray()));
    }

    /**
     * 文件拷贝就这么简单实现啦...
     */
    private static void copyTest() {
        try {
            File sourceFile = new File("/Users/zeal/Desktop/test");
            BufferedSource bufferedSource = Okio.buffer(Okio.source(sourceFile));

            File destFile = new File("/Users/zeal/Desktop/test3");
            if (!destFile.exists()) {
                destFile.createNewFile();
            }

            BufferedSink bufferedSink = Okio.buffer(Okio.sink(destFile));

            //这两种方式都可以实现数据的拷贝

            bufferedSource.readAll(bufferedSink);
//            bufferedSink.writeAll(bufferedSource);
            bufferedSink.close();
            bufferedSource.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void souceTest() {
        //从文件中读取数据
        try {

            File file = new File("/Users/zeal/Desktop/test");

            Source source = Okio.source(file);

            BufferedSource bufferSource = Okio.buffer(source);


            //以字节数组的形式读取出来
//            byte[] bytes = bufferSource.readByteArray();
//            System.out.println(new String(bytes, Charset.forName("UTF-8")));


            String s = bufferSource.readUtf8Line();
            long data = bufferSource.readLong();
            System.out.println("--------------------------------" + s + "==" + data);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sinkTest() {
        try {
            File file = new File("/Users/zeal/Desktop/test");
            Sink sink = Okio.sink(file);
            BufferedSink bufferedSink = Okio.buffer(sink);

            //写入utf-8格式的字符串，会把先前的数据清除，而不是append的方式
            bufferedSink.writeUtf8("hello wrold\n");
            bufferedSink.writeLong(520);
            bufferedSink.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}