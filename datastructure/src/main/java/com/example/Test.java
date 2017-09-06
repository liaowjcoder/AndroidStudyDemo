package com.example;

public class Test {

    public class Note{

        int data;

        Note next;

    }


    public static void deleteNode(Note head,Note node) {
        //删除的是尾节点,采用顺序查找找到尾节点的前一节点
        if(node.next==null) {
            while (head.next!=null) {
                head = head.next;
            }

            head.next = null;
        }

        //删除的头结点
    }

}




