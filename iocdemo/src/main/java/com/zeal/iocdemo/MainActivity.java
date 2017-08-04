package com.zeal.iocdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.user)
    EditText username;
    @BindView(R.id.pass)
    EditText password;


    @OnClick(R.id.submit) void submit() {

        Toast.makeText(this, username.getText().toString()+"-"+ password.getText().toString(), Toast.LENGTH_SHORT).show();

    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //绑定activity
        Unbinder unbinder = ButterKnife.bind(this);




        //unbinder.unbind();

    }
}
