package queue;

public class LinkedListDemo {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.add(new Hero(1,"张三","江西"));
        linkedList.add(new Hero(2,"里斯","江西"));
        linkedList.add(new Hero(3,"王五","江西"));
        linkedList.add(new Hero(4,"赵六","江西"));
        linkedList.list();
    }
}


/**
 * 链表
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
            if (temp.next==null) {
                break;
            }
            if (temp.next.no > hero.no) {
                break;
            }
            temp=temp.next;
        }
        hero.next=temp.next;
        temp.next=hero;
    }

    //展示链表
    public void list() {
        //使用辅助指针从头节点遍历到末尾
        Hero temp = head;
        while (temp.next!=null) {
            System.out.println(temp);
            temp=temp.next;
        }
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

