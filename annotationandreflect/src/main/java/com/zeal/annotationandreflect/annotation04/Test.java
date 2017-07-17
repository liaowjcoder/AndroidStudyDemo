package com.zeal.annotationandreflect.annotation04;

/**
 * Created by liaowj on 2017/7/17.
 */

public class Test
{

    public static final void main(String[] args) {

        FlagDemo flagDemo = new FlagDemo();
        //flagDemo.setColor("");
        flagDemo.setColor(FlagDemo.GREEN);

        System.out.println("color:"+flagDemo.getColor());

    }
}
