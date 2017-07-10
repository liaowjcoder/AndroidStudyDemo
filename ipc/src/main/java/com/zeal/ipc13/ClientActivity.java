package com.zeal.ipc13;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zeal.ipc.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liaowj on 2017/7/10.
 * <p>
 * 1.和服务端建立连接
 * 2.接收服务端消息
 * 3.发送消息
 */

public class ClientActivity extends AppCompatActivity {

    private Socket mSocket;
    private PrintWriter mPrintWriter;
    private BufferedReader mReader;

    private static final int MSG_CONNECT_SERVER = 0;
    private static final int MSG_GET_FROM_SERVER = 1;
    private static final int MSG_SEND_FROM_CLIENT = 2;


    private TextView mTextView;
    private EditText mEditText;
    private Button mButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);

        mTextView = (TextView) findViewById(R.id.textview);
        mEditText = (EditText) findViewById(R.id.edittext);
        mButton = (Button) findViewById(R.id.sendBtn);

        Intent intent = new Intent(this, TCPServerService.class);
        startService(intent);
        new Thread(new ServerRunnable()).start();

    }

    class ServerRunnable implements Runnable {
        @Override
        public void run() {
            Socket socket = null;

            while (socket == null) {
                try {
                    socket = new Socket("localhost", 10086);
                    mSocket = socket;

                    mPrintWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

                    mHandler.sendEmptyMessage(MSG_CONNECT_SERVER);
                } catch (IOException e) {
                    e.printStackTrace();
                    continue;
                }
            }

            //接受服务端的消息
            try {
                BufferedReader mReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while (!ClientActivity.this.isFinishing()) {

                    String msgFromServer = mReader.readLine();
                    //Log.e("zeal","receive from server "+ msgFromServer);
                    String date = getFormatDate();
                    String msg = "server " + date + " " + msgFromServer + "\n";
                    Message message = Message.obtain();
                    message.what = MSG_GET_FROM_SERVER;
                    message.obj = msg;
                    mHandler.sendMessage(message);
                }

                Log.e("zeal", "quit");
                mPrintWriter.close();
                mReader.close();
                mSocket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case MSG_CONNECT_SERVER:
                    Log.e("zeal", "与服务端建立连接");
                    mButton.setEnabled(true);
                    break;
                case MSG_GET_FROM_SERVER:
                    String msgFromServer = (String) msg.obj;
                    Log.e("zeal", "MSG_GET_FROM_SERVER " + msgFromServer);
                    mTextView.setText(mTextView.getText().toString() + "\n" + msgFromServer);

                    break;
                case MSG_SEND_FROM_CLIENT:
                    String msgFromClient = (String) msg.obj;
                    mTextView.setText(mTextView.getText().toString() +"\n"+ msgFromClient);
                    break;
            }
        }
    };

    private String getFormatDate() {
        Date date = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        String s = sdf.format(date);
        return s;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mSocket != null) {
            try {
                mSocket.shutdownInput();
                mSocket.shutdownOutput();
                mSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void send(View view) {
        String sendMsg = mEditText.getText().toString();

        if (!TextUtils.isEmpty(sendMsg) && mPrintWriter != null) {

            String time = getFormatDate();
            String msg = "client " + time + " " + mEditText.getText().toString();

            Message message = Message.obtain();
            message.obj = msg;
            message.what = MSG_SEND_FROM_CLIENT;
            mHandler.sendMessage(message);

            mPrintWriter.println(sendMsg);
        }

    }
}
