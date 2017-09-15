package reader.fungo.org.okiodemo;

import java.io.File;
import java.nio.charset.Charset;

import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Sink;
import okio.Source;

/**
 * https://programtalk.com/java-api-usage-examples/okio.BufferedSource/
 * http://useof.org/java-open-source/okio.Source
 */
public class Demo {
    public static void main(String[] args) {
        //souceTest();
        //sinkTest();

        //copyTest();

        //byteStringTest();

        bufferTest();
    }


    private static void bufferTest() {

        try {
            Buffer buffer = new Buffer();


            buffer.writeUtf8("HELLO WORLD");


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


    }

    /**
     * 文件拷贝就这么简单实现啦...
     */
    private static void copyTest() {
        try {
            File sourceFile = new File("d://test.txt");
            BufferedSource bufferedSource = Okio.buffer(Okio.source(sourceFile));

            File destFile = new File("d://test2.txt");
            if (!destFile.exists()) {
                destFile.createNewFile();
            }

            BufferedSink bufferedSink = Okio.buffer(Okio.sink(destFile));

            //这两种方式都可以实现数据的拷贝

            //bufferedSource.readAll(bufferedSink);

            bufferedSink.writeAll(bufferedSource);
            bufferedSink.close();
            bufferedSource.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sinkTest() {
        try {
            File file = new File("d://test.txt");
            Sink sink = Okio.sink(file);
            BufferedSink bufferedSink = Okio.buffer(sink);

            //写入utf-8格式的字符串，会把先前的数据清除，而不是append的方式
            //bufferedSink.writeUtf8("你好啊，zeal");
            bufferedSink.writeInt(520);
            bufferedSink.writeLong(1000000);
            //bufferedSink.writeString("我爱 OKio",Charset.forName("UTF-8"));

            bufferedSink.close();

            BufferedSource bufferSource = Okio.buffer(Okio.source(file));
            System.out.println("readInt:" + bufferSource.readInt());

            //提前close，然后再读取就会报异常。
            //bufferSource.close();
            System.out.println("readLong:" + bufferSource.readLong());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void souceTest() {
        //从文件中读取数据
        try {
            Source source = null;
            File file = new File("d://test.txt");

            source = Okio.source(file);

            BufferedSource bufferSource = Okio.buffer(source);


            //以字节数组的形式读取出来
            //byte[] bytes = bufferSource.readByteArray();
            //System.out.println(new String(bytes, Charset.forName("UTF-8")));

            //System.out.println("--------------------------------");

            //以UTF-8字符串的形式读取出来
            //String content = bufferSource.readUtf8();
            //System.out.println("content:" + content);
            //System.out.println("--------------------------------");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}