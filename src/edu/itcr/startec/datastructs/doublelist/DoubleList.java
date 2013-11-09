package edu.itcr.startec.datastructs.doublelist;

import java.util.Iterator;

import edu.itcr.startec.datastructs.ListInterface;


/**
 * Manages the reading of the list.
 * @author zyoruk , jeukel
 * @param <K>
 */
class DoubleListIterator<K> implements Iterator<K> {

    DoubleList<K> list;
    DoubleListNode<K> current;
    
    /**
     * Constructor of the iterator
     * @param list receives a DoubleList.
     */
    public DoubleListIterator(DoubleList<K> list) {
        this.list = list;
        this.current = null;
    }
    
    /**
     * This method checks if current node hast a next reference.
     * @return Boolean true if has next or false if not.
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
     * @return the next reference of the item. 
     */
    @Override
    public K next() {
        if(this.current == null) {
            return null;
        }
        return this.current.getElem();
    }

    /**
     * @exception Cant perform a remove operation.
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}


/**
 * 
 * @author zyoruk, jeukel
 *
 * Creates the double list and manahes all its operations.
 * @param <K>
 */
public class DoubleList<K> implements ListInterface<K>, Iterable<K> {
    
    protected int length;
    protected DoubleListNode<K> head;
    protected DoubleListNode<K> tail;

    /**
     * Constructor
     */
    public DoubleList() {
        this.length = 0;
        this.head = null;
        this.tail = null;
    }
    
    /**
     * @return if it is empty, true . Otherwise, false.
     */
    @Override
    public boolean isEmpty() {
        return this.head == null;
    }

    /**
     * Adds to the last position the element.
     * @param pk node.
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
     * Deletes the element.
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
     * @return the size of the double list.
     */
    @Override
    public int length() {
        return this.length;
    }

    /**
     * @return true if the list contains the element; false otherwise.
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
     * Insert the element at the especified position.
     * @param pos Integer of the position.
     * @param pk element to insert.
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
     * Cleans totally the list leaving it empty.
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
     * Calls the iterator of the list.
     */
    @Override
    public Iterator<K> iterator() {
        return new DoubleListIterator<K>(this);
    }
    /**
     * @return head element as string, used on queue peek.
     */
    public String returnElem(){
    	return (String) this.head.getElem();
    }
    
    /**
     * @return String with head, tail and lenght of the list.
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
     * this is especifically used for units nad structures lists.
     * @return the descritiom of every single unit.
     */

    /**
     * Adds at the end.
     * @param pk Double List Node.
     */
    @Override
    public boolean insert(K pk) {
        DoubleListNode<K> node = new DoubleListNode<K>(pk);

        // Insert node
        if (this.head == null){
        	this.tail = node;
        }else{
        	node.setNext(this.head);
        	this.head.setPrevious(node);
        }
        // Check head
        this.head = node;

        this.length += 1;
        return true;
	}

    /**
     * Deletes the first node for the circular list.
     */
	//Erase first node
	@Override
	public boolean delete() {
		//Set tmp list
        DoubleListNode<K> node = this.head.getNext();
        
        //Destroy
        this.head.getNext().setPrevious(null);
        this.head = null;
                
        //Set new list
        this.head = node;
        return true;
	}

	/**
	 * Erases the last node of the circular list.
	 */
	//Erase last node
	@Override
	public boolean cut() {
		if (this.tail == null){
			return false;
		} else if (this.tail.getPrevious() == null){
			this.head = null;
			this.tail = null;
		} else {
			this.tail = this.tail.getPrevious();
			this.tail.setNext(null);
		}
		this.length--;
		return true;
	}

	@Override
	public K getRootData() {
		// TODO Auto-generated method stub
		return null;
	}
}
