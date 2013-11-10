package edu.itcr.startec.datastructs.serializing;

import edu.itcr.startec.datastructs.doublelist.DoubleList;

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

}
