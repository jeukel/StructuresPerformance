package edu.itcr.startec.datastructs.serializing;

import edu.itcr.startec.datastructs.doublelist.DoubleList;
import edu.itcr.startec.datastructs.doublelist.DoubleListNode;

/**
 * Class to create a Queue. It extends from DoubleList because each node 
 * has references to the previous and next nodes of the list.
 * @author zyoruk,jeukel
 *
 * @param <K>
 */
public class Queue<K> extends DoubleList<K>{	
	
	/**
	 * Adds to the first position
	 * @param pk
	 */
	public void Enqueue(K pk){
		super.insert(pk);
	}
	
	/**
	 * Removes the last node.
	 */
	public void Dequeue(){
		super.cut();
	}

	/**
	 * 
	 * @return Gets the element of the first node as a string.
	 */
	public String Peek(){
		return super.returnElem();
	}
	
	public boolean EnqueueByPriority(K pk, int priori){
		DoubleListNode<K> node = new DoubleListNode<K>(pk);
        int i;

        // Check valid position
        if(priori < 0){
            return false;
        }

        // Search priority
        DoubleListNode<K> current = this.head;
        for(i = 0; current.getPriority() < current.getNext().getPriority(); i++) {
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

}
