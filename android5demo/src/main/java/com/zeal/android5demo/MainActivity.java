package com.zeal.android5demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;
/*
点击按钮切换不同的布局：GridLayoutManager 和 LinearLayoutManager之间切换
 */
public class MainActivity extends AppCompatActivity {

    private List<String> datas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final RecyclerView recyclerview = (RecyclerView) findViewById(R.id.recyclerview);




        MultiItemTypeAdapter<String> multiItemTypeAdapter = new MultiItemTypeAdapter<>(this,datas);

        multiItemTypeAdapter.addItemViewDelegate(new AViewItemType());
        multiItemTypeAdapter.addItemViewDelegate(new HeaderViewItemType());


        final LinearLayoutManager lin= new LinearLayoutManager(this);
        recyclerview.setLayoutManager(lin);

        final GridLayoutManager grid = new GridLayoutManager(this,2);

        //重要代码
        grid.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if(position==0) {
                    //当处于第一个时就显示一整行：返回的个数就是现实的该item占据的列数
                    return grid.getSpanCount();
                }
                return 1;
            }
        });


        initDatas();

        recyclerview.setAdapter(multiItemTypeAdapter);


        findViewById(R.id.switch_type).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(recyclerview.getLayoutManager() instanceof GridLayoutManager){
                    recyclerview.setLayoutManager(lin);
                }else{
                    recyclerview.setLayoutManager(grid);
                }
            }
        });






    }

    private void initDatas() {

        datas.add("B");
        datas.add("A1");
        datas.add("A2");
        datas.add("A3");
        datas.add("A4");
        datas.add("A5");
        datas.add("A6");
        datas.add("A7");
        datas.add("A8");
        datas.add("A9");
        datas.add("A10");
        datas.add("A11");
        datas.add("A12");
    }
}


class AViewItemType implements ItemViewDelegate<String>{

    @Override
    public int getItemViewLayoutId() {
        return R.layout.normal;
    }

    @Override
    public boolean isForViewType(String item, int position) {
        return item.startsWith("A");
    }

    @Override
    public void convert(ViewHolder holder, String s, int position) {
        holder.setText(R.id.text,s);
    }
}

class HeaderViewItemType implements ItemViewDelegate<String>{

    @Override
    public int getItemViewLayoutId() {
        return R.layout.header;
    }

    @Override
    public boolean isForViewType(String item, int position) {
        return item.startsWith("B");
    }

    @Override
    public void convert(ViewHolder holder, String s, int position) {
    }
}
