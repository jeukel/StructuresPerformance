/*
 * AVLNode.java
 *
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
 */

package edu.itcr.structuresperformance.avl;

class AVLNode<K>{
    K data;
    int balance;
    AVLNode<K> left, right;

    public AVLNode(K data){
        this.data = data;
        this.left = null;
        this.right = null;
        this.balance = 0;
    }
    
    public K getData(){
    	return this.data;
    }
    
    public AVLNode(K data, AVLNode<K> left, AVLNode<K> rigth) {
        this.data = data;
        this.left = left;
        this.right = rigth;
    }

	public AVLNode<K> getLeft() {
		return this.left;
	}
	
	public AVLNode<K> getRight() {
		return this.right;
	}
}
