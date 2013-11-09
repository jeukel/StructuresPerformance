/*
 * BinaryNode.java
 *
 * Copyright 2013 Daniel Jenkins <jeukel@gmail.com>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 *
 *
 */

package edu.itcr.structuresperformance.binary;

public class BinaryNode<K>{
    private K data;
    private BinaryNode<K> left, right;
    
    public BinaryNode() {
        this.data = null;
        this.left = null;
        this.right = null;
    }

    public BinaryNode(K pdata) {
        this.data = pdata;
        this.left = null;
        this.right = null;
    }
    
    //*********************************************************//
    /*
     * Regular setters and getters
     */
    //*********************************************************//
    
    public K getData(){
    	return this.data;
    }
    
    public BinaryNode<K> getLeft(){
    	return this.left;
    }
    
    public BinaryNode<K> getRight(){
    	return this.right;
    }
    
    public void setData(K pk){
    	this.data = pk;
    }
    
    public void setLeft(K pk){
    	BinaryNode<K> left = new BinaryNode<K>(pk);
    	this.left = left;
    }
    
    public void setLeft(BinaryNode<K> pk){
    	this.left = pk;
    }
    
    public void setRight(K pk){
    	BinaryNode<K> right = new BinaryNode<K>(pk);
    	this.right = right;
    }
    
    public void setRight(BinaryNode<K> pk){
    	this.right = pk;
    }
}
