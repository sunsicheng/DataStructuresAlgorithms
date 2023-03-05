package queue;

public class LinkedListDemo {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.add(new Hero(1, "张三", "江西"));
        linkedList.add(new Hero(3, "里斯", "江西"));
        linkedList.add(new Hero(4, "周结论", "江西"));
        linkedList.add(new Hero(2, "赵六", "江西"));
        linkedList.add(new Hero(7, "林俊杰", "江西"));
        linkedList.add(new Hero(6, "高启强", "江西"));
        linkedList.add(new Hero(8, "高启盛", "江西"));
        linkedList.add(new Hero(5, "泰叔", "江西"));
        linkedList.list();
//        linkedList.update(new Hero(4,"王五","北京"));
//        linkedList.list();
//        linkedList.delete(3);
//        linkedList.delete(4);
//        linkedList.delete(1);
//        linkedList.list();
        System.out.println("-----------------");
//        System.out.println(linkedList.listEndK(5));
        linkedList.reverse();
        linkedList.list();
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

