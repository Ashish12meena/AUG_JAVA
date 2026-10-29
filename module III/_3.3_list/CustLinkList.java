
class Node {
    int data;
    Node prev;
    Node next;

    Node(int data) {
        this.data = data;
        prev = null;
        next = null;
    }

}

public class CustLinkList {
    int size=0;
    Node head;
    Node tail;
    public CustLinkList(){
        size=0;
    }

    public void add(int element){
        Node n1 = new Node(element);
        if (size==0) {
            head = n1;
            tail = n1;
            size++;
        }else{
            tail.next = n1;
            n1.prev = tail;
            tail = tail.next;
            size++;
        }

    }

    public void add(int element , int index){
        if (index>size) {
            System.out.println("index outOfBondErro");
            return;
        }
        int i =0;

        Node newNode = new Node(element);

        Node nexNode = head;

        while (!(i>index)) {
            nexNode = nexNode.next;
        }

        Node prevNode = nexNode.prev;


        newNode.next = nexNode;
        nexNode.prev = newNode;


        prevNode.next = newNode;
        newNode.prev=prevNode;




    }
  
}
