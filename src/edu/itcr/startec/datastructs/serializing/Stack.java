package edu.itcr.startec.datastructs.serializing;

import edu.itcr.startec.datastructs.simplelist.SimpleList;

/**
 * 
 * @author zyoruk, jeukel
 *
 * @param <K>
 */
public class Stack<K> extends SimpleList<K>{

	/**
	 * This is adds to the first position
	 * @param pk
	 */
    public boolean push(K pk) {
        return insert(pk);
    }

    /**
     * Returns the element at the top as a string.
     * @return calls describe() of simplelist.
     */
    public String top() {
        return describe();
    }

    /**
     * Deletes the first node of the list.
     * @return calls simplelist delete().
     */
    public boolean pop() {
        return delete();
    }

}
