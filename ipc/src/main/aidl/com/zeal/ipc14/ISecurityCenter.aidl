// ISecurityCenter.aidl
package com.zeal.ipc14;

interface ISecurityCenter {

    //加密
    String encryt(String content);
    //解密
    String decrypt(String password);
}
