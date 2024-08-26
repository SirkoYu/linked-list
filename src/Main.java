public class Main {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.addNode(10);
        linkedList.addNode(11);
        linkedList.addNode(14);
        linkedList.addNode(22);
        linkedList.addNode(35);
        linkedList.addNode(56);
        linkedList.addNode(76);
        linkedList.showLinkedList();
        linkedList.clear();
        linkedList.showLinkedList();
    }
}
