import java.util.Optional;

/**
 * <p>Singly linked list.</p>
 * @author ITryHard
 */
public class LinkedList {
    private Node head = null;
    private Node tail = null;

    private int size = 0;

    /**
     * <p>Add a new node to the ending of linked list.</p>
     * @param data {@link Integer} type variable to store {@link Node} data.
     */
    public void addNode(int data){
        if(size == 0) {
            head = new Node(data);
            tail = head;
            head.isHead = true;
            head.isTail = true;
        }else {
            Node newNode = new Node(data);
            newNode.isTail = true;
            tail.nextNode = newNode;
            tail.isTail = false;
            tail = newNode;
        }
        size++;
    }
    /**
     * <p>Add a new node to the beginning of linked list.</p>
     * @param data {@link Integer} type variable to store {@link Node} data.
     */
    public void addFirstNode(int data){
        if(size==0){
            head = new Node(data);
            tail = head;
            head.isHead = true;
            tail.isTail = true;
        }else {
            Node newNode = new Node(data);
            newNode.nextNode = head;
            newNode.isTail = head.isTail;
            newNode.isHead = true;
            head.isHead = false;
            head = newNode;
        }

        size++;
    }
    /**
     * Add a new node in linked list by provided index.
     * @param data {@link Integer} type variable to store {@link Node} data.
     * @param index {@link Integer} index where the node will be inserted.
     */
    public void insertNode(int data, int index){
        if(index < size && !(index<0)){
            if(index == 0){
                addFirstNode(data);
            } else if (index == size -1) {
                addNode(data);
            }
            else {
                Optional<Node> node = getNode(index-1);
                if(node.isPresent()) {
                    Node currentNode = node.get();
                    Node newNode = new Node(data);
                    newNode.nextNode = currentNode.nextNode;
                    currentNode.nextNode = newNode;
                    size++;
                }
                else System.out.println("Node is null.");
            }
        } else {
            System.out.println("Invalid index.");
        }
    }
    /**
     * <p>Removes the node from linked list by provided index.</p>
     * @param index {@link Integer} index of element to remove.
     */
    public void removeNode(int index){
        if (index < size && !(index < 0)) {
            if (size > 1) {
                if(index == 0) {
                    head.nextNode.isHead = true;
                    head.nextNode.isTail = head.isTail;
                    head = head.nextNode;
                }
                else if(index == size -1){
                    Optional<Node> node = getNode(index);
                    if(node.isPresent()){
                    Node currentNode = node.get();
                    currentNode.isHead = currentNode.nextNode.isHead;
                    currentNode.isTail = true;
                    currentNode.nextNode = null;
                    } else System.out.println("Node is null.");
                }
                else {
                    Optional<Node> node = getNode(index);
                    if(node.isPresent()) {
                        Node currentNode = node.get();
                        currentNode.nextNode = currentNode.nextNode.nextNode;
                    }
                }
            }

            size--;
            if(size == 0){
                head=null;
                tail=null;
            }
        }
        else {
            System.out.println("Invalid index");
        }
    }

    /**
     * <p>Returns the node by its index.</p>
     * @param index {@link Integer} index of the node.
     * @return {@link Node} object.
     */
    private Optional<Node> getNode(int index) {
        if (index < size && !(index < 0)) {
            if(index == 0 ) {
                return getFirst();
            }
            else if (index == size -1) {
                return getLast();
            }
            else {
                Node currentNode = head;
                for (int i = 0; i < index; i++) {
                    currentNode = currentNode.nextNode;
                }
                return Optional.of(currentNode);
            }
        }
        else {
            System.out.println("Invalid index");
            return Optional.empty();
        }
    }
    public Node get(int index) {
        if (index < size && !(index < 0)) {
            if(index == 0 ) {
                if(getFirst().isPresent()){
                    return getFirst().get();
                }
            }
            else if (index == size -1) {
                if(getLast().isPresent()){
                    return getLast().get();
                }
            }
            else {
                Node currentNode = head;
                for (int i = 0; i < index; i++) {
                    currentNode = currentNode.nextNode;
                }
                Optional<Node> node = Optional.of(currentNode);
                return node.get();
            }
        }
        else {
            System.out.println("Invalid index");
            return null;
        }
        return null;
    }
    public LinkedList splitList(int start, int end){
        if (size <= 1) {
            return this;
        }
            LinkedList newList = new LinkedList();
            for (int i = start; i < end; i++) {
                Optional<Node> node = getNode(i);
                if(node.isPresent()){
                    newList.addNode(node.get().data);
                }
                else {
                    System.out.println("Node is null.");
                }
            }
            return newList;
    }
    public LinkedList mergeSort(){
        if(size <= 1){
            return this;
        }
        int middle = size / 2;
        LinkedList left = splitList(0, middle);
        LinkedList right = splitList(middle, size);

        left = left.mergeSort();
        right = right.mergeSort();

        return mergeList(left, right);

    }
    private static LinkedList mergeList(LinkedList left, LinkedList right){
        LinkedList newList = new LinkedList();
        int i=0, j=0;

        while (i< left.size() && j< right.size()){
            Optional<Node> leftNode = left.getNode(i);
            Optional<Node> rightNode = right.getNode(j);
            if (leftNode.isPresent() && rightNode.isPresent()) {
                if(leftNode.get().data <= rightNode.get().data){
                    newList.addNode(leftNode.get().data);
                    i++;
                } else {
                    newList.addNode(rightNode.get().data);
                    j++;
                }
            } else {
                System.out.println("Node is null.");
            }
        }
        while (i < left.size()){
            Optional<Node> leftNode = left.getNode(i);
            if(leftNode.isPresent()) {
                newList.addNode(leftNode.get().data);
                i++;
            }
        }
        while (j < right.size()){
            Optional<Node> rightNode = right.getNode(j);
            if(rightNode.isPresent()) {
                newList.addNode(rightNode.get().data);
                j++;
            }
        }

        return newList;
    }
    private Optional<Node> getLast() {
        if(!isEmpty())
            return Optional.of(tail);
        return Optional.empty();
    }

    private Optional<Node> getFirst() {
        if(!isEmpty())
            return Optional.of(head);
        else return Optional.empty();
    }

    public int size(){
        return this.size;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public void clear(){
        while(!this.isEmpty()){
            removeNode(0);
        }
    }
    @Override
    public String toString(){
        StringBuilder list = new StringBuilder("[");
        for (int i = 0; i < size-1; i++) {
            Optional<Node> node = getNode(i);
            list.append(node.isPresent() ? node.get() : "Node is null").append(", ");
        }
        Optional<Node> node = getLast();
        node.ifPresent(value -> list.append(value.isTail() ? value : "").append("]"));
        return list.toString();
    }
    /**
     * <p>An object for storing a single node of linked list. Has five attributes:</p>
     * <ul>
     * <li>data - stores an integer node value.</li>
     * <li>nextNode - link to next node in linked list.</li>
     * <li>previousNode - link to previous node in linked list.</li>
     * <li>isHead - boolean value (is true when node is head of the list).</li>
     * <li>isTail - boolean value (is true when node is tail of the list).</li>
     * </ul>
     * @author ITryHard
     */
    static class Node {
        private final int data;
        private Node nextNode = null;

        boolean isHead = false;

        boolean isTail = false;

        /**
         * <p>A constructor for single node of linked list.
         * @param data {@link Integer} type variable to store {@link Node} data.</p>
         */

        public Node( int data){
            this.data = data;
        }

        public boolean isHead(){
            return isHead;
        }
        public boolean isTail(){
            return isTail;
        }
        public boolean hasNextNode(){
            return nextNode != null;
        }
        public int getData(){
            return data;
        }
        @Override
        public String toString(){
            return STR."Node data = \{data}";
        }
    }
}