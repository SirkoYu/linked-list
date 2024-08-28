import java.util.List;


public class Main {
    public static void main(String[] args) {
        List<Integer> list = List.of(10,14, 22, 11, 9);
        LinkedList linkedList = new LinkedList();

        for(Integer num: list){
            linkedList.addNode(num);
        }
        System.out.println(linkedList);

        linkedList.addFirstNode(7);
        System.out.println(linkedList);

        linkedList.insertNode(13, 3);
        System.out.println(linkedList);

        linkedList = linkedList.mergeSort();
        System.out.println(linkedList);

        LinkedList.Node node = linkedList.get(0);
        System.out.println(node.isHead() ? "Node is head." : "Node is`t head.");
        System.out.println(node.isTail() ? "Node is tail." : "Node is`t tail.");
        System.out.println(node.hasNextNode() ? "Node has next Node." : "Node does`t have next Node.");
        System.out.println(STR."Node data: \{node.getData()}");

        System.out.println(linkedList.isEmpty()? "Empty list." : linkedList);

        linkedList.clear();
        System.out.println(linkedList);

        System.out.print(linkedList.isEmpty()? "Empty list." : linkedList);
    }
}
