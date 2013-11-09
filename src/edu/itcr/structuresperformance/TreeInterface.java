package edu.itcr.structuresperformance;

public interface TreeInterface<K> {
	public int length();
	public boolean isEmpty();
    public boolean insert(K pk);
    public boolean delete(K pk);
    public boolean exists(K pk);
    public boolean setEmpty();
}