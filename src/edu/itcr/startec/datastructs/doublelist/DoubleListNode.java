package edu.itcr.startec.datastructs.doublelist;

/**
 * 
 * @author zyoruk,jeukel
 *
 * @param <K>
 */
public class DoubleListNode<K> {
    
    private K elem;
    private DoubleListNode<K> next;
    private DoubleListNode<K> previous;
    private int priority;
    
    /**
     * creates the double list node with the element
     * @param elem
     */
    public DoubleListNode(K elem) {
        this.elem = elem;
        this.next = null;
        this.previous = null;
        this.priority = 0;
    }
    
    /**
     * 
     * @param elem head of the list
     * @param next element next to the head
     */
    public DoubleListNode(K elem, DoubleListNode<K> next) {
        this.elem = elem;
        this.next = next;
        this.previous = null;
        this.priority = 0;
    }
    
    public DoubleListNode(K elem, DoubleListNode<K> next, int ppriority) {
        this.elem = elem;
        this.next = next;
        this.previous = null;
        this.priority = ppriority;
    }
    
    /**
     * This will set the next element of the current one .
     * @param next element to be set as the next reference.
     */
    public void setNext(DoubleListNode<K> next) {
        this.next = next;
    }
    
    /**
     * Sets the previous element
     * @param previous Double List Node to be the previous 
     */
    public void setPrevious(DoubleListNode<K> previous) {
        this.previous = previous;
    }

    /**
     * 
     * @return Double List node next of the current one .
     */
    public DoubleListNode<K> getNext() {
        return this.next;
    }
    
    /**
     * 
     * @return Double list node previous of the current one .
     */
    public DoubleListNode<K> getPrevious() {
        return this.previous;
    }
    
    /**
     * 
     * @return Elem of the current node.
     */
    public K getElem() {
        return this.elem;
    }

	public int getPriority() {
		return this.priority;
	}
}