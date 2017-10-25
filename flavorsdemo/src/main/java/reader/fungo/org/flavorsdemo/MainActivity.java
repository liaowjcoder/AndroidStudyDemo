package reader.fungo.org.flavorsdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //在 build.gradle 中 配置不同的 AUTO_UPDATE productFlavors
        //boolean autoUpdate = BuildConfig.AUTO_UPDATE;
        //Toast.makeText(this, "自动更新：" + autoUpdate, Toast.LENGTH_SHORT).show();

    }

    //public  void click(View view) {
        //Utils.toast(this);
    //}
}
