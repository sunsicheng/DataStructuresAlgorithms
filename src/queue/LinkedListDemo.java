package queue;

public class LinkedListDemo {
    public static void main(String[] args) {
        LinkedList linkedList1 = new LinkedList();
        LinkedList linkedList2 = new LinkedList();
        linkedList1.add(new Hero(1, "张三", "江西"));
        linkedList1.add(new Hero(3, "里斯", "江西"));
        linkedList1.add(new Hero(4, "周结论", "江西"));
        linkedList1.add(new Hero(2, "赵六", "江西"));
        linkedList2.add(new Hero(7, "林俊杰", "江西"));
        linkedList2.add(new Hero(6, "高启强", "江西"));
        linkedList2.add(new Hero(8, "高启盛", "江西"));
        linkedList2.add(new Hero(5, "泰叔", "江西"));
        linkedList1.list();
        System.out.println("--------------------------------------");
//        linkedList2.list();
//        System.out.println("--------------------------------------");
//        linkedList.update(new Hero(4,"王五","北京"));
//        linkedList.list();
//        linkedList.delete(3);
//        linkedList.delete(4);
//        linkedList.delete(1);
//        linkedList.list();
//        System.out.println("-----------------");
//        System.out.println(linkedList.listEndK(5));
//        linkedList1.combine(linkedList2);
//        linkedList1.list();
        Hero reverse = linkedList1.reverse2();
        System.out.println(reverse);
        //linkedList1.list();
    }
}


/**
 * 链表增删改查实现
 */
class LinkedList {
    //初始化头节点
    private Hero head = new Hero(0, "", "");

    //末尾添加元素
    public void add(Hero hero) {
        //使用辅助指针从头节点遍历到末尾
        Hero temp = head;
        while (true) {
            //如果链表为空，只有一个头节点，直接加入
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > hero.no) {
                break;
            }
            temp = temp.next;
        }
        hero.next = temp.next;
        temp.next = hero;
    }

    /**
     * 修改链表
     */

    public void update(Hero hero) {
        Hero temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }

            if (temp.next.no == hero.no) {
                flag = true;
                break;
            }
            temp = temp.next;

        }
        if (flag) {
            hero.next = temp.next.next;
            temp.next = hero;
        }
    }


    /***
     * 删除元素
     * @param no
     */
    public void delete(int no) {
        Hero temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }

            if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        temp.next = temp.next.next;
    }

    //展示链表
    public void list() {
        //使用辅助指针从头节点遍历到末尾
        Hero temp = head;
        while (true) {
            System.out.println(temp);
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
    }


    /***
     * 获取链表倒数第K个元素
     * 使用快慢指针，让一个指针先走k步
     * @return
     */
    public Hero listEndK(int k) {
        Hero first = head;
        Hero second = head;
        //first 先移动k个元素
        while (k > 1) {
            if (first.next == null) {
                if (k > 0) {
                    System.out.println("链表元素太少");
                }
                break;
            }
            first = first.next;
            k--;
        }

        while (true) {
            if (first.next == null) {
                break;
            }
            first = first.next;
            second = second.next;
        }
        return second;
    }


    /***
     * 链表反转,头插法，遍历链表每个元素，插到头节点后面
     */
    public void reverse() {
        //先定义一个节点
        Hero revHead = new Hero(0, "", "");
        Hero temp = head;
        while (temp.next != null) {
            //先保存一下当前节点
            Hero curent = temp.next;
            // ******* 需要将当前节点断下来，否则将curent的节点指向revHead.next（null），会导致后面的temp也都为null
            head.next = temp.next.next;
            //插入到头节点后面
            curent.next = revHead.next;
            revHead.next = curent;
            //将temp移动到头节点
            temp=head;
        }
        head.next = revHead.next;
    }

    public LinkedList combine(LinkedList list) {

        //如果两个都是空链表，直接返回空链表
        if (head.next == null && list.head.next == null) {
            return this;
        } else if (head.next == null && list.head.next != null) {   //有一个空链表返回不为空的那个
            return list;
        } else if (head.next != null && list.head.next == null) {   //有一个空链表返回不为空的那个
            return this;
        } else {            //两个都不为空的情况
            Hero listHead = list.head;
            while (true) {      //遍历一个链表
                if (listHead.next == null) {        // 链表遍历结束，所有元素插入完毕
                    break;
                }
                //取下头结点下的第一个元素
                Hero insertNode = listHead.next;
                listHead.next = listHead.next.next;
                insertNode.next = null;
                //遍历源链表，找到待插入元素的位置上
                Hero temp = head.next;
                while (true) {
                    if (temp.next == null) {
                        break;
                    }

                    if (insertNode.no < temp.next.no) {
                        break;
                    }
                    temp = temp.next;
                }
                //插入节点
                insertNode.next = temp.next;
                temp.next = insertNode;
            }
            return this;
        }
    }


    //反转时需要三个指针分别指向前中后三个结点，中间结点的next指向前结点完成中间结点的反转，后指针指向中间结点的原后继，防止链表丢失
    public Hero reverse2(){
        Hero pre=null;
        Hero cur=head;
        Hero next;
        while (cur!=null){
            next=cur.next;      //保存当前节点
            cur.next=pre;       //当前节点的下一节点指向pre,完成翻转
            pre=cur;            //指针往后移
            cur=next;
        }
        return pre;
    }
}




/**
 * 水浒英雄实体类，代表链表上的每个节点
 */
class Hero {
    public int no;
    public String name;
    public String nickName;
    public Hero next;

    public Hero(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "LinkedList{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }

}

