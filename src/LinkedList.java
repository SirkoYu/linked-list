/**
 * <p>An object for storing a single node of linked list. Has five attributes:</p>
 * <ul>
 * <li>data - stores an integer node value.</li>
 * <li>nextNode - link to next node in linked list.</li>
 * <li>previousNode - link to previous node in linked list.</li>
 * <li>isHead - boolean value (is true when node is head of the list)</li>
 * <li>isTail - boolean value (is true when node is tail of the list)</li>
 * </ul>
 * @author ITryHard
 */

public class LinkedList {
    private Node head;
    private Node tail;

    private int size;

    public int size(){
        return this.size;
    }
    static class Node {
        private int data;
        private Node nextNode = null;
        private Node previousNode = null;

        boolean isHead = false;

        boolean isTail = false;

        /**
         * <p>A constructor for single node of linked list.
          * @param data stores an integer node value.</p>
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
        public boolean hasPreviousNode(){
            return previousNode != null;
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