package com.hjt.suanfa.link;

/**
 * @author hjt
 * @date:2023/3/13
 */
public class Node {

    private static Node head = new Node();

    //数据域
    public Integer data;

    //指针域，指向下一个节点
    public Node next;

    public Node() {
    }

    public Node(int data) {
        this.data = data;
    }

    public Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }


    /**
     * 向链表添加数据
     *
     * @param value 要添加的数据
     */
    public static void addData(int value) {

        //初始化要加入的节点
        Node newNode = new Node(value);

        //临时节点
        Node temp = head;

        // 找到尾节点
        while (temp.next != null) {
            temp = temp.next;
        }

        // 已经包括了头节点.next为null的情况了～
        temp.next = newNode;

    }


    /**
     * 遍历链表
     *
     * @param head 头节点
     */
    public static void traverse(Node head) {


        //临时节点，从首节点开始
        Node temp = head.next;

        while (temp != null) {

            if (temp.data != null) {
                System.out.println("输出：" + temp.data);
            }

            //继续下一个
            temp = temp.next;
        }
    }



    /**
     * 插入节点
     *
     * @param head  头指针
     * @param index 要插入的位置
     * @param value 要插入的值
     */
    public static void insertNode(Node head, int index, int value) {


        //首先需要判断指定位置是否合法，
        if (index < 1 || index > linkListLength(head) + 1) {
            System.out.println("插入位置不合法。");
            return;
        }

        //临时节点，从头节点开始
        Node temp = head;

        //记录遍历的当前位置
        int currentPos = 0;

        //初始化要插入的节点
        Node insertNode = new Node(value);

        while (temp.next != null) {

            //找到上一个节点的位置了
            if ((index - 1) == currentPos) {

                //temp表示的是上一个节点

                //将原本由上一个节点的指向交由插入的节点来指向
                insertNode.next = temp.next;

                //将上一个节点的指针域指向要插入的节点
                temp.next = insertNode;

                return;

            }

            currentPos++;
            temp = temp.next;
        }

    }

    /**
     * 获取链表的长度
     * @param head 头指针
     */
    public static int  linkListLength(Node head) {

        int length = 0;

        //临时节点，从首节点开始
        Node temp = head.next;

        // 找到尾节点
        while (temp != null) {
            length++;
            temp = temp.next;
        }

        return length;
    }

    public static void main(String[] args) {

    }



}
