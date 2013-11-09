package edu.itcr.startec.datastructs.doublelist;

import java.util.Iterator;

import edu.itcr.startec.datastructs.ListInterface;


/**
 * The iterator is meant to be in charge of moving through the Circular List.
 * @author zyoruk , jeukel
 *
 * @param <K> This implements the idea of generics that will let us create
 * circular lists that contain any kind of type .
 */

class DoubleCircularListIterator<K> implements Iterator<K> {

    DoubleCircularList<K> list;
    DoubleListNode<K> current;
    
    /**
     * This sets the variables, it is the constructor.
     * @param list
     */
    public DoubleCircularListIterator(DoubleCircularList<K> list) {
        this.list = list;
        this.current = null;
    }
    
    /**
     * This will tell us if the current node has a next node.
     */
    @Override
    public boolean hasNext() {
        
        if(this.current == null) {
            if(this.list.isEmpty()) {
                return false;
            }
            this.current = this.list.head;
            return true;
        }
        
        this.current = this.current.getNext();
        return this.current != null;
    }

    /**
     * @return The next node.
     */
    @Override
    public K next() {
        if(this.current == null) {
            return null;
        }
        return this.current.getElem();
    }

    /**
     * @exception Cant perform a remove method.
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
/**
 * Creates the double circular list and manages all its operations.
 * 
 * @author zyoruk , jeukel
 *
 * @param <K> Let us create any kind of doublecircular list.
 */
public class DoubleCircularList<K> implements ListInterface<K>, Iterable<K> {
    
    protected int length;
    protected DoubleListNode<K> head;
    protected DoubleListNode<K> tail;

    /**
     * Constructor.
     */
    public DoubleCircularList() {
        this.length = 0;
        this.head = null;
        this.tail = null;
    }
    
    /**
     * @return True or False depending if it is empty or not .
     */
    @Override
    public boolean isEmpty() {
        return this.head == null;
    }

    /**
     * This will add the element to the end of the list.
     * @param pk element of the circular list.
     */
    @Override
    public boolean append(K pk) {

        DoubleListNode<K> node = new DoubleListNode<K>(pk);

        if(isEmpty()) {
            this.head = node;
            this.tail = node;
        } else {
            this.tail.setNext(node);
            node.setPrevious(this.tail);
        }
        this.tail = node;
        this.length += 1;
        return true;
    }

    /**
     * This will delete the element the it receives.
     * @param pk any element of the circular list.
     */
    @Override
    public boolean delete(K pk) {
        
        if(isEmpty()) {
            return false;
        }
        if(this.length == 1) {
            if(this.head.getElem().equals(pk)) {
                clear();
                this.length -= 1;
                return true;
            }
            return false;
        }
        
        DoubleListNode<K> current = this.head;
        while(current != null) {
            if(current.getElem().equals(pk)) {
                // Remove node
                if(current == this.tail){
                	this.tail = current.getPrevious();
                	current.getPrevious().setNext(current.getNext());
                	this.length -= 1;
                	return true;
                }
                if (current == this.head){
                	this.head = current.getNext();
                	current.getNext().setPrevious(current.getPrevious());
                	this.length -= 1;
                	return true;
                }
                current.getPrevious().setNext(current.getNext());
                current.getNext().setPrevious(current.getPrevious());
                current.setNext(null);
                current = null;
                this.length -= 1;
                return true;
            }            
            current = current.getNext();
        }        
        return false;
    }

    /**
     * @return Size of the list.
     */
    @Override
    public int length() {
        return this.length;
    }

    /**
     * @return True if the circular list contains the element or false if not.
     */
    @Override
    public boolean exists(K pk) {
        for(K ck : this) {
            if(ck.equals(pk)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This will insert the element where the user wants to.
     * @param pos Position to insert.
     * @param pk Element to insert.
     */
    @Override
    public boolean insert(int pos, K pk) {
        DoubleListNode<K> node = new DoubleListNode<K>(pk);
        int i;

        // Check valid position
        if((pos < 0) || (pos > this.length)) {
            return false;
        }

        // Search position
        DoubleListNode<K> current = this.head;
        for(i = 0; i != pos; i++) {
            current = current.getNext();
        }
        
        // Insert node
        if(this.length == i){
            this.tail.setNext(node);
            node.setPrevious(this.tail);
        } else {
            node.setNext(current);
            node.setPrevious(current.getPrevious());
            if(current.getPrevious() != null) {
                current.getPrevious().setNext(node);
                current.setPrevious(node);
            }
        }

        // Check head
        if(current == this.head) {
            this.head = node;
        }
        // Check tail
        if(this.length == i){
            this.tail = tail.getNext();
        }
        this.length += 1;
        return true;
    }

    /**
     * Empties the circular list.
     */
    @Override
    public boolean clear() {
        DoubleListNode<K> temp = null;
        while(this.head != null) {
            temp = this.head.getNext(); 
            this.head.setNext(null);
            this.head = temp;
        }
        this.tail = null;
        this.length = 0;
        return true;
    }
    
    /**
     * @return a new double circular list iterator. 
     */
    @Override
    public Iterator<K> iterator() {
        return new DoubleCircularListIterator<K>(this);
    }
    
    /**
     * This describes us the Double Circular List.
     * @return A string containing information about the lenght ,head and tail
     */
    public String describe() {
        StringBuilder result = new StringBuilder();

        result.append("List: ");
        for(K k : this) {
            result.append(String.format("%s ", k.toString()));
        }
        result.append("\n");

        result.append(String.format("Length: %d\n", this.length));
        result.append(
                String.format("Head: %s\n", this.head.getElem().toString())
            );
        result.append(
                String.format("Tail: %s\n", this.tail.getElem().toString())
            );

        return result.toString();
    }
    
    /**
     * When talking about lists containing units, we had to create this method.
     * @return It will return a string with all the attributes of every single
     * unit inside a list.
     */
    
    /**
     * This will add the element as the first position.
     */
    //Insert as head
    @Override
    public boolean insert(K pk) {
        DoubleListNode<K> node = new DoubleListNode<K>(pk);

        // Insert node
        node.setNext(this.head);
        node.setPrevious(this.tail);
        
        this.head = node;
        //First insert
        if(isEmpty()) {
            this.tail = node;
        }
        
        this.tail.setNext(this.head);
        this.head.getNext().setPrevious(this.tail);

        this.length += 1;
        return true;
	}
    
    /**
     * Deletes the head of the list.
     */
    //Delete head
	@Override
	public boolean delete() {
		//Set tmp list
        DoubleListNode<K> node = this.head.getNext();
        
        //Destroy
        this.head.getNext().setPrevious(null);
        this.head = null; // Destoys the node.
                
        //Set new list
        this.head = node;
        return true;
	}

	/**
	 * Deletes the tail of the list.
	 */
	@Override
	public boolean cut() {
		DoubleListNode<K> node = this.tail.getPrevious();
		
		this.tail.getPrevious().setNext(null);
		this.tail = null; //Destroys the node.
		this.tail = node;
		return true;
	}

	@Override
	public K getRootData() {
		// TODO Auto-generated method stub
		return null;
	}
}
