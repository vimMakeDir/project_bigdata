package com.atguigu.datastructure.doublelinked;

/**
 * @author lxk
 * @create 2020-10-27 20:42
 */
public class Josepfu {
    public static void main(String[] args) {
        //测试环形链表
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(25);
        circleSingleLinkedList.showBoy();
        //测试一把小孩出圈的顺序
        System.out.println("***************");
        circleSingleLinkedList.outCircleList(1,2,25);

        System.out.println("这是热备的输出语句");
        System.out.println("这是主分支的输出语句");
        System.out.println("怎么不起作用啊");
    }
}

//创建环形单项链表
class CircleSingleLinkedList {
    //创建一个first节点，当前没有编号
    private Boy first = new Boy(-1);

    //添加小孩节点，构建成环形链表
    public void addBoy(int nums) {
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        Boy curBoy = null;//辅助指针，
        //使用for循环创建环形链表
        for (int i = 1; i <= nums; i++) {
            //根据编号创建小孩节点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if (i == 1) {
                first = boy;
                first.setNext(first);//构成环
                curBoy = first;
            } else {
                curBoy.setNext(boy);//让当前的后指针指向新节点
                boy.setNext(first);//让新节点的后指针指向first
                curBoy = boy;
            }
        }
    }

    //遍历当前的环形链表
    public void showBoy() {
        if (first == null) {
            System.out.println("链表为空");
            return;
        }
        //因为first不能动，只动辅助指针
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号 %d \n", curBoy.getNo());
            if (curBoy.getNext() == first) {
                //说明遍历完毕
                break;
            }
            curBoy = curBoy.getNext();//让boy后移
        }
    }

    public void outCircleList(int startNo, int countNum, int Nums) {
        //根据用户的输入计算出出圈的顺序
        //先校验数据
        if (first == null || startNo < 1 || startNo > Nums) {
            System.out.println("参数输入有误，请重新输入");
        }
        //创建辅助指针
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) {//说明helper指向最后的小孩节点
                break;
            }
            helper = helper.getNext();
        }
        //小孩报数前，先让first和helper移动k-1次
        for (int j = 0; j < startNo - 1; j++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //当小孩报数时，让first和helper指针同时移动m-1次，然后出圈
        while (true){
            if(helper == first){//说明只有一个节点
                break;
            }
            //让first和helper同时一定countNum - 1 次
            for(int j = 0;j<countNum-1;j++){
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时first指向的节点就是要出圈的节点
            System.out.printf("小孩%d 出圈\n",first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号%d\n",first.getNo());

    }


}


//创建boy类，节点
class Boy {
    private int no;//编号
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}