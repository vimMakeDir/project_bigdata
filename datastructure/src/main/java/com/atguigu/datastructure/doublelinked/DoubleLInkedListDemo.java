package com.atguigu.datastructure.doublelinked;

import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;

/**
 * @author lxk
 * @create 2020-10-26 19:50
 */
public class DoubleLInkedListDemo {
    public static void main(String[] args) {
        System.out.println("双向链表的测试");
        HeroNode hero1 = new HeroNode(1,"宋江","及时雨");
        HeroNode hero2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3 = new HeroNode(3,"鲁智深","花和尚");
        HeroNode hero4 = new HeroNode(4,"孙二娘","母老虎");

        //创建一个双向链表对象
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        doubleLinkedList.list();

        HeroNode newHeroNode = new HeroNode(4, "孙大娘", "母老虎");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表情况");
        doubleLinkedList.list();

        doubleLinkedList.del(4);
        System.out.println("删除后的链表情况");
        doubleLinkedList.list();


    }
}

//创建双向链表类
class DoubleLinkedList{
    //初始化头节点
    private HeroNode head = new HeroNode(0,"","");
    //返回头节点
    public HeroNode getHead(){
        return head;
    }

    //遍历双向链表
    public void list(){
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        //定义辅助变量来遍历
        HeroNode temp = head.next;
        while (true){
            //判断是否到链表最后
            if(temp == null){
                break;
            }
            System.out.println(temp);
            //将temp后移
            temp = temp.next;
        }
    }

    public void add(HeroNode heroNode){
        HeroNode temp = head;
        //遍历链表找到最后
        while(true){
            //找到最后节点，跳出循环
            if(temp.next == null){
                break;
            }
            //没找到继续遍历
            temp = temp.next;
        }
        //找到最后，添加新节点
        //形成一个双向链表
        temp.next = heroNode;
        heroNode.prev = temp;
    }

    public void update(HeroNode newHeroNode){
        //判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        //定义辅助节点
        HeroNode temp = head.next;
        boolean flag = false;
        while (true){
            if(temp == null){
                break;//已经遍历完成
            }
            if(temp.no == newHeroNode.no){
                //找到
                flag = true;
                break;
            }
            temp =temp.next;
        }
        //根据flag判断是否找到了要修改的节点
        if(flag){
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        }else{
            System.out.println("没有找到要修改的节点");
        }
    }

    //从双向链表中删除一个节点
    public void del(int no){
        //判断当前链表是否为空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }

        HeroNode temp = head.next;
        boolean flag = false;
        while (true){
            if(temp == null){
                break;//已经遍历完成
            }
            if(temp.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.prev.next = temp.next;
            //代码存在风险，存在空指针异常
            //加上if判断
            if(temp.next != null){
                temp.next.prev = temp.prev;
            }
        }else{
            System.out.println("要删除的节点不存在");
        }
    }







}

//定义节点
class HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;//指向下一个节点
    public HeroNode prev;//指向上一个节点

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode[" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ']';
    }
}