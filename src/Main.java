import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = List.of(10,11,14,22,35,56,76);
        LinkedList linkedList = new LinkedList();
        for(Integer num: list){
            linkedList.addNode(num);
        }
        linkedList.showLinkedList();
//        linkedList.clear();
//        linkedList.showLinkedList();
        linkedList.insertNode(42, 5);
        linkedList.showLinkedList();
    }
}
