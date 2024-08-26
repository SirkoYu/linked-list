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
                Node currentNode = getNode(index);
                Node newNode = new Node(data);
                if (currentNode != null) {
                    newNode.nextNode = currentNode.nextNode;
                    currentNode.nextNode = newNode;
                }
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
                    Node currentNode = getNode(index);
                    if (currentNode != null) {
                        currentNode.isHead = currentNode.nextNode.isHead;
                        currentNode.isTail = true;
                        currentNode.nextNode = null;
                    }
                }
                else {
                    Node currentNode = getNode(index);
                    if (currentNode != null) {
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
    private Node getNode(int index) {
        if (index < size && !(index < 0)) {
            if(index == 0 ) {
                return getFirst();
            }
            else if (index == size -1) {
                return getLast();
            }
            else {
                Node currentNode = head;
                for (int i = 0; i < index - 1; i++) {
                    currentNode = currentNode.nextNode;
                }
                return currentNode;
            }
        }
        else {
            System.out.println("Invalid index");
            return null;
        }
    }

    private Node getLast() {
        return tail;
    }

    private Node getFirst() {
        return head;
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
    public void showLinkedList(){
        if (size!=0) {
            System.out.print("[");
            Node currentNode = head;
            while(true){
                System.out.print(currentNode);
                if(currentNode.isTail) {
                    System.out.println("]");
                    break;
                }
                System.out.print(", ");
                currentNode = currentNode.nextNode;
            }
        } else System.out.println("List is empty.");
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
        private int data;
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