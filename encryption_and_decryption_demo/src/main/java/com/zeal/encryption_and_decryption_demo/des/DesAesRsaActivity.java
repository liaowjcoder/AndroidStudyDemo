package com.zeal.encryption_and_decryption_demo.des;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.TextView;

import com.zeal.encryption_and_decryption_demo.R;
import com.zeal.encryption_and_decryption_demo.aes.Aes;
import com.zeal.encryption_and_decryption_demo.rsa.Rsa;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class DesAesRsaActivity extends AppCompatActivity {

    private boolean isDes;
    private boolean isAes;
    private boolean isRsa;

    private String data = "我是廖伟健~";//需要加密的数据
    private String key = "%^$@%^$^@*";//密钥

    private TextView result;

    private String mDesEncryptResult;
    private String mDesDecryptResult;
    private String mAesEncryptResult;
    private String mRsaEncryptByPublicKey;


    private RSAPublicKey mPublicKey;
    private RSAPrivateKey mPrivateKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_des_aes_rsa);

        result = (TextView) findViewById(R.id.result);
    }


    /*
    对称加密des
     */
    public void des(View view) {

        if (!isDes) {//加密

            mDesEncryptResult = new String(DES.encrypt(data, key));

            result.setText(mDesEncryptResult);

        } else {//解密
            mDesDecryptResult = DES.decrypt(mDesEncryptResult, key);
            result.setText(mDesDecryptResult);
        }
        isDes = !isDes;


    }

    /*
    对称加密Aes
     */
    public void aes(View view) {

        if (!isAes) {//加密

            mAesEncryptResult = new String(Aes.encrypt(key, data));
            result.setText(mAesEncryptResult);
        } else {//解密
            result.setText(new String(Aes.decrypt(key, mAesEncryptResult)));
        }
        isAes = !isAes;


    }

    public void rsa(View view) {
        try {
            if (!isRsa) {//加密
                //生成密钥对
                KeyPair keyPair = Rsa.generateRSAKeyPair(1024);
                //得到公钥
                mPublicKey = (RSAPublicKey) keyPair.getPublic();
                //得到私钥

                mPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
                //使用公钥加密
                byte[] encryptByPublicKey = Rsa.encryptByPublicKey(data.getBytes(), mPublicKey.getEncoded());
                mRsaEncryptByPublicKey = new String(encryptByPublicKey);
                result.setText(mRsaEncryptByPublicKey);
            } else {//解密
                //使用私钥解密
                byte[] decryptByPrivateKey = Rsa.decryptByPrivateKey(Base64.decode(mRsaEncryptByPublicKey.getBytes(),Base64.DEFAULT), mPrivateKey.getEncoded());
                String rsaDecryptByPrivateKey = new String(decryptByPrivateKey);
                result.setText(rsaDecryptByPrivateKey);
            }
            isRsa = !isRsa;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
