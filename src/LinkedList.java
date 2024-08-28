import java.util.Optional;

/**
 * <p>Singly linked list.</p>
 * <p>Has three attributes: </p>
 * <ul>
 *     <li>head - {@link Node} type head of the list.</li>
 *     <li>tail - {@link Node} type tail of the list.</li>
 *     <li>size - {@link Integer} type size of the list.</li>
 * </ul>
 * @author ITryHard
 */
public class LinkedList {
    private Node head = null;
    private Node tail = null;

    private int size = 0;

    /**
     * <p>Adds new node with provided data at the ending of linked list. </p>
     * @param data {@link Integer} type, contains node data.
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
     * <p>Add a new node with provided data at the beginning of linked list.</p>
     * @param data {@link Integer} type, contains node data.
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
     * <p>Add a new node with provided data at the provided index in linked list.</p>
     * <p>Takes O(n) time.</p>
     * @param data has {@link Integer} type, contains node data.
     * @param index has {@link Integer} type, contains index of new node`s position.
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
     * <p>Removes the node from linked list at provided index.</p>
     * @param index has {@link Integer} type, contains index of the element to remove.
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
     * <p>Returns an Optional object of the node at provided index.</p>
     * @param index has {@link Integer} type, contains the index of node.
     * @return {@link Optional} object, witch contains the node.
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

    /**
     * <p>Returns node object by provided index.</p>
     * @param index has {@link Integer} type, contains the index of node.
     * @return {@link Node} object.
     */
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

    /**
     * Splits the current linked list into a sublist starting from the given start index up to,
     * but not including, the end index. If the list size is 1 or less, it returns the current list.
     *
     * @param start the starting index of the sublist (inclusive).
     * @param end the ending index of the sublist (exclusive).
     * @return a new LinkedList containing elements from the start index up to the end index.
     */
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
    /**
     * Sorts the linked list using the merge sort algorithm. If the size of the list is 1 or less,
     * it returns the current list as it is already sorted.
     *
     * @return a sorted LinkedList.
     */
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
    /**
     * Merges two sorted linked lists into a single sorted linked list. The method iterates through
     * both input lists, comparing their elements and adding the smaller element to the new list.
     * It continues until all elements from both lists have been added to the new list.
     *
     * @param left the first sorted LinkedList.
     * @param right the second sorted LinkedList.
     * @return a new LinkedList containing all elements from both input lists, sorted in ascending order.
     */
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
    /**
     * Retrieves the last node of the linked list, if it exists.
     *
     * @return an Optional containing the last node if the list is not empty;
     *         otherwise, an empty Optional.
     */
    private Optional<Node> getLast() {
        if(!isEmpty())
            return Optional.of(tail);
        return Optional.empty();
    }
    /**
     * Retrieves the first node of the linked list, if it exists.
     *
     * @return an Optional containing the first node if the list is not empty;
     *         otherwise, an empty Optional.
     */
    private Optional<Node> getFirst() {
        if(!isEmpty())
            return Optional.of(head);
        else return Optional.empty();
    }

    /**
     * Returns the number of elements in the linked list.
     *
     * @return the size of the linked list.
     */
    public int size(){
        return this.size;
    }
    /**
     * Checks if the linked list is empty.
     *
     * @return true if the linked list has no elements; false otherwise.
     */
    public boolean isEmpty(){
        return size==0;
    }
    /**
     * Removes all elements from the linked list.
     */
    public void clear(){
        while(!this.isEmpty()){
            removeNode(0);
        }
    }
    /**
     * Returns a string representation of the linked list. The representation
     * includes all nodes in the list, separated by commas, enclosed in square brackets.
     *
     * @return a string representing the elements of the linked list.
     */
    @Override
    public String toString(){
        StringBuilder list = new StringBuilder("[");
        for (int i = 0; i < size-1; i++) {
            Optional<Node> node = getNode(i);
            list.append(node.isPresent() ? node.get() : "Node is null").append(", ");
        }
        Optional<Node> node = getLast();
        node.ifPresentOrElse(value -> list.append(value.isTail() ? value : "").append("]"), () -> list.append("]"));
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
        /**
         * Checks if this node is the head of the linked list.
         *
         * @return true if this node is the head; false otherwise.
         */
        public boolean isHead(){
            return isHead;
        }
        /**
         * Checks if this node is the tail of the linked list.
         *
         * @return true if this node is the tail; false otherwise.
         */
        public boolean isTail(){
            return isTail;
        }
        /**
         * Checks if this node has a next node.
         *
         * @return true if this node has a next node; false otherwise.
         */
        public boolean hasNextNode(){
            return nextNode != null;
        }
        /**
         * Retrieves the data stored in this node.
         *
         * @return the integer data stored in this node.
         */
        public int getData(){
            return data;
        }
        /**
         * Returns a string representation of the node. The representation includes
         * the node's data value.
         *
         * @return a string representing the node's data.
         */
        @Override
        public String toString(){
            return STR."Node data = \{data}";
        }
    }
}