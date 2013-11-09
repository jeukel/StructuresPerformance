/*
 * AVLTree.java
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

import edu.itcr.startec.datastructs.simplelist.SimpleList;
import edu.itcr.structuresperformance.TreeInterface;

public class AVLTree<K> implements TreeInterface<K>{

	//******************Global Variables*************//
    private AVLNode<K> root;
    boolean aux;

    /*
     * Constructors
     */ 
    public AVLTree() {
        root = null;
        aux = true;
    }

    public AVLTree(AVLNode<K> proot) {
        this.root = proot;
    }
    
	@Override
	public boolean isEmpty(){
		return this.root == null;
    }	
	
	@Override
	public boolean insert(K data) {
        if ((!exists(data))) {
            AVLNode<K> info = new AVLNode<K>(data);
            this.root = insert_balanced(this.root, info);
        } else {
            System.out.println("El elemento ya existe");
            return false;
        }
        return true;
    }

    private AVLNode<K> insert_balanced(AVLNode<K> proot, AVLNode<K> pnode) {
        AVLNode<K> node;
        AVLNode<K> info = pnode;
        if (isEmpty()) {
            proot = info;
            aux = true;
        }else {
        	if(pnode.data.getClass().equals(Integer.class)){
	            if ((Integer)pnode.data < (Integer)proot.data) {
	                proot.left = insert_balanced(proot.left, pnode);
	                if (aux) {
	                    switch (proot.balance) {
	                        case 1:
	                            proot.balance = 0;
	                            aux = false;
	                            break;
	                        case 0:
	                            proot.balance = -1;
	                            break;
	                        /*
	                         * Se reestructura ya que pasaria a valer -2 y
	                         * se desequilibra a la izquierda. 
	                         */
	                            
	                        case -1:
	                            node = proot.left;
	                            if (node.balance == -1) {
	                                proot = leftLeftRotation(proot, node);
	                            } else {
	                                proot = leftRightRotation(proot, node);
	                            }
	                            aux = false;
	                            break;
	                    }
	                }
	            }else {
	                if ((Integer)pnode.data > (Integer)proot.data) {
	                    proot.right = insert_balanced(proot.right, pnode);
	                    if (aux) {
	                        switch (proot.balance) {
	                            case -1:
	                                proot.balance = 0;
	                                aux = false;
	                                break;
	                            case 0:
	                                proot.balance = 1;
	                                break;
	                            /* se reestructura ya que pasaria a valer 2 y 
	                             * se desequilibra a la derecha
	                             */
	                            case 1:
	                                node = proot.right;
	                                if (node.balance == 1) {
	                                    proot = rightRightRotation(proot, node);
	                                } else {
	                                    proot = rightLeftRotation(proot, node);
	                                }
	                                aux = false;
	                                break;
	                        }
	                    }
	                }
	            }
	        }
        }
        return proot;
    }

	@Override
	public boolean exists(K pk) {
    	AVLNode<K> temp = this.root;
        boolean exists = false;
        while (temp != null) {
            if (pk == temp.getData()) {
                exists = true;
                return exists;
            } else {
            	if(pk.getClass().equals(Integer.class)){
	                if ((Integer)pk > (Integer)temp.getData()) {
	                    temp = temp.right;
	                }else {
	                    temp = temp.left;
	                    if (temp == null) {
	                        return exists;
	                    }
	                }
            	}
            }
        }
        return exists;
	}    
    
    /*
     * Devuelve el mayor de los menores a la hora de eliminar
     */
    public AVLNode<K> greaterThanUnder(AVLNode<K> nodo) {
        AVLNode<K> dad = nodo;
        AVLNode<K> aux = nodo.left;
        try {
            while (aux.right != null) {
                dad = aux;
                aux = aux.right;
            }
            dad.right = aux.left;

        } catch (Exception e) {
        }
        return aux;
    }    

	@Override
	public boolean setEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	//********************DELETE********************//
	@Override
	public boolean delete(K data) {
        if ((!exists(data))) {
            System.out.println("El elemento no existe");
            return false;
        } else {
            eliminates(data);
        }
        return true;
    }

    void eliminates(K data) {
        if (data == getRoot().data && 
        	getRoot().right == null && 
        	getRoot().left == null) {
        	
                root = null;
        } else {
            AVLNode<K> nodetoremover = nodeToRemover(data, getRoot());
            System.out.println("Nodo a eliminar " + nodetoremover.data);
            if (nodetoremover.data == data && 
            	nodetoremover.right == null && 
            	nodetoremover.left == null) {
            	
                System.out.println("Nodo eliminar " + getRoot().left.data);
                root.left = null;
            }
            if (nodetoremover.left != null) {
                AVLNode<K> greaterthanunder = greaterThanUnder(nodetoremover);
                System.out.println("el mayor de menores es " + 
                					greaterthanunder.data);
                
                if (greaterthanunder.data == data) {
                    System.out.println("Se eliminara el " + 
                    					getRoot().left.data);
                    root.left = null;
                }
            } else {
            }
        }
    }

    private AVLNode<K> nodeToRemover(K data, AVLNode<K> root) {
        AVLNode<K> raiz = root;
        while (data != raiz.data) {
            if ((Integer)data > (Integer)raiz.data) {
                raiz = raiz.right;
            } else {
                raiz = raiz.left;
            }
        }
        return raiz;
    }
    
  //**********************************************************************//

    //   Desequilibrio Interno
    //   Rotacion Derecha - Izquierda
    private AVLNode<K> rightLeftRotation(AVLNode<K> root, AVLNode<K> node1) {
        AVLNode<K> node2;
        node2 = node1.left;
        root.right = node2.left;
        node2.left = root;
        node1.left = node2.right;
        node2.right = node1;
        if (node2.balance == 1) {
            root.balance = -1;
        } else {
            root.balance = 0;
        }
        if (node2.balance == -1) {
            node1.balance = 1;
        } else {
            node1.balance = 0;
        }
        node2.balance = 0;
        root = node2;
        return root;
    }
    
    // Rotacion Izquierda - Derecha
    private AVLNode<K> leftRightRotation(AVLNode<K> root, AVLNode<K> node1) {
        AVLNode<K> node2;
        node2 = node1.right;
        root.left = node2.right;
        node2.right = root;
        node1.right = node2.left;
        node2.left = node1;
        if (node2.balance == 1) {
            node1.balance = -1;
        } else {
            node1.balance = 0;
        }
        if (node2.balance == -1) {
            root.balance = 1;
        } else {
            root.balance = 0;
        }
        node2.balance = 0;
        root = node2;
        return root;
    }

    // Desequilibrio Externo
    // Rotacion Izquierda -Izquierda
    private AVLNode<K> leftLeftRotation(AVLNode<K> root, AVLNode<K> node1) {
        root.left = node1.right;
        node1.right = root;
        if (node1.balance == -1) {

            root.balance = 0;
            node1.balance = 0;
        } else {

            root.balance = -1;
            node1.balance = 1;
        }
        root = node1;
        return root;
    }
    
    // Rotacion Derecha - Derecha
    private AVLNode<K> rightRightRotation(AVLNode<K> root, AVLNode<K> node1) {
        root.right = node1.left;
        node1.left = root;
        if (node1.balance == 1) {
            root.balance = 0;
            node1.balance = 0;
        } else {
            root.balance = 1;
            node1.balance = -1;
        }
        root = node1;
        return root;
    }
   
  //**********************************************************************//
    /*
     * Tamaño
     */
    
    @Override
	public int length() {
    	return size_extended(root);
	}
    
    private int size_extended (AVLNode<K> node){
        if (node == null){
            return 0;
        }
        return 1 + size_extended(node.left) + size_extended(node.right);
    }
    
  //**********************************************************************//
        
    public AVLNode<K> getRoot() {
        return this.root;
    }
    
  //**********************************************************************//
    /*
	 * How to goes through the tree made by an exit.
	 */
	public SimpleList<K> preorden (){
        SimpleList<K> list = new SimpleList<K>();
        list.append(this.root.getData());
        if (this.root.getLeft() != null){
        	list = preorden_extended(this.root.getLeft(),list);
        }
        if (this.root.getRight() != null){
        	list = preorden_extended(this.root.getRight(),list);
        }
        return list;
    }
	
	/*
	 * Extended method for going though the exit.
	 */
	private SimpleList<K> preorden_extended(AVLNode<K> pnode,
											SimpleList<K> plist){
		
		System.out.println(pnode.getData());
        plist.append(pnode.getData());
        if (pnode.getLeft() != null){
        	plist = preorden_extended(this.root.getLeft(),plist);
        }
        if (pnode.getRight() != null){
        	plist = preorden_extended(this.root.getRight(),plist);
        }
        return plist;
	}
	/*
	 * Inorden
	 */
	public SimpleList<K> inorden (){
        SimpleList<K> list = new SimpleList<K>();
        if (this.root.getLeft() != null){
        	list = inorden_extended(this.root.getLeft(),list);
        }
        list.append(this.root.getData());
        if (this.root.getRight() != null){
        	list = inorden_extended(this.root.getRight(),list);
        }
        return list;
    }
	
	private SimpleList<K> inorden_extended(AVLNode<K> pnode,
										   SimpleList<K> plist){
		
		System.out.println(pnode.getData());
        if (pnode.getLeft() != null){
        	plist = inorden_extended(this.root.getLeft(),plist);
        }
        plist.append(this.root.getData());
        if (pnode.getRight() != null){
        	plist = inorden_extended(this.root.getRight(),plist);
        }
        return plist;
	}
	
	/*
	 * Postorden
	 */
	public SimpleList<K> postorden (){
        SimpleList<K> list = new SimpleList<K>();
        if (this.root.getLeft() != null){
        	list = postorden_extended(this.root.getLeft(),list);
        }
        if (this.root.getRight() != null){
        	list = postorden_extended(this.root.getRight(),list);
        }
        list.append(this.root.getData());
        return list;
    }

	private SimpleList<K> postorden_extended(AVLNode<K> pnode,
											 SimpleList<K> plist){
		
		System.out.println(pnode.getData());
        if (pnode.getLeft() != null){
        	plist = postorden_extended(this.root.getLeft(),plist);
        }
        if (pnode.getRight() != null){
        	plist = postorden_extended(this.root.getRight(),plist);
        }
        plist.append(this.root.getData());
        return plist;
	}

	
}
