package queue;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

public class CycleSingleListDemo {
    public static void main(String[] args) {
        CycleSingleList cycleSingleList = new CycleSingleList();
        cycleSingleList.bulidCycle(10);
        cycleSingleList.showNodes();
        cycleSingleList.joseph(1, 3);
    }

}


/**
 * 环形链表
 */
class CycleSingleList {
    private Boy first = null;

    /***
     * 构建环形链表，每增加一个元素都将它指向头节点
     * @param nums
     */
    public void bulidCycle(int nums) {
        if (nums < 1) {
            System.out.println("传入参数错误，无法构建链表");
            return;
        }
        // 辅助指针，用于构建环形链表
        Boy current = null;
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                first.setNext(first);
                current = first;
            } else {
                current.setNext(boy);
                boy.setNext(first); //将新加入的节点指向头节点
                current = boy;
            }
        }
    }

    public void showNodes() {
        Boy temp = first;
        while (true) {
            System.out.printf("当前打印的元素是：%d ", temp.getNo());
            System.out.println();
            if (temp.getNext() == first) {
                break;
            }
            temp = temp.getNext();
        }

    }

    public void joseph(int start, int step) {
        Boy temp = first;
        //先定位到开始的位置
        while (start > 1) {
            temp = temp.getNext();
            start--;
        }

        //进行出队操作
        while (temp.getNext() != temp) {
            int step2=step;
            while (step2 > 1) {
                temp = temp.getNext();
                step2--;
            }
            //此时在待移除队列的前一个元素 temp.getNo()
            System.out.println(temp.getNext().getNo());
            temp.setNext(temp.getNext().getNext());
            //单链表删除元素无需断掉删除元素的连接，使用头插法的时候需要
        }
        //最后一个元素出队列
        System.out.println(temp.getNo());
    }
}


class Boy {
    private int no;
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