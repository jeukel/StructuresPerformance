/*
 * SplayTree.java
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

package edu.itcr.structuresperformance.splay;

import edu.itcr.startec.datastructs.simplelist.SimpleList;
import edu.itcr.structuresperformance.TreeInterface;


public class SplayTree<K> implements TreeInterface<K>{

    int cont;
    SplayNode<K> root;
    SplayNode<K> dad_node;
    SplayNode<K> son_node;
    K last;       //removed or inserted
    boolean flag = true;
    
    @Override
	public int length() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean exists(K pk) {
		search(pk);
		return true;
	}
    
    public SplayNode<K> search(K data) {
        if (data == getRoot().data) {
        } else {
            SplayNode<K> padre = null;
            SplayNode<K> hijo = this.root;
            
            if(data.getClass().equals(Integer.class)){
	            while ((hijo != null) && (hijo.data != data)) {
	                if ((Integer)data < (Integer)hijo.data) {
	                    padre = hijo;
	                    hijo = hijo.left;
	                }else{
	                    padre = hijo;
	                    hijo = hijo.right;
	                }
	            }
	            if (hijo == null) {
	                SplayNode<K> aux = gotGrandPa(padre);
	                if (padre != this.root) {
	                    Splay(aux, padre);
	                }
	            }else{
	                Splay(padre, hijo);
	            }
            }
        }
        return this.root;
    }

    //Inserta un elemento en un arbol splay
    @Override
    public boolean insert(K data) {
        if (!existBool(data)) {
            insert(data);
            last = data;
        } else {
            System.out.println("El elemento ya existe");
            return false;
        }
        return true;
    }
	
    public SplayNode insert_aux(K data) {
        if (this.root == null) {
            root = new SplayNode(data);
        } else {
            dad_node = null;
            son_node = getRoot();
            
            if(data.getClass().equals(Integer.class)){
            	while (son_node != null) {
                    if(data == son_node.data){
                        System.out.println("El nodo ya existe");
                    }else if ((Integer)data < (Integer)son_node.data) {
                        dad_node = son_node;
                        son_node = son_node.left;
                    }else {
                        dad_node = son_node;
                        son_node = son_node.right;
                    }
                }
                   
            
	            SplayNode<K> splaynode = new SplayNode<K>(data);
	            if ((Integer)data > (Integer)dad_node.data) {
	                dad_node.right = splaynode;
	                Splay(dad_node, splaynode);
	            } else {
	               dad_node.left = splaynode;
	                Splay(dad_node, splaynode);
	            }
            }
        }
        return this.root;
    }

    public SplayTree() {
        root = null;
    }

    public SplayTree(SplayNode<K> root) {
        this.root = root;
    }
 
    public void zagzag(SplayNode<K> grandPa) {
        if (cont < 2) {
            System.out.println("zag zag");
            cont++;
            SplayNode temp = new SplayNode(grandPa.data);
            temp.left = grandPa.left;
            temp.right = grandPa.left;
            temp.right = dad_node.left;
            grandPa.left = temp;
            grandPa.right = dad_node.right;
            grandPa.data = (K) dad_node.data;

            if (grandPa == getRoot()) {
                flag = false;
            }
            dad_node = grandPa;
        } else {
            cont = 0;
        }
    }

    //rotacion zag zig
    public void zagzig(SplayNode grandPa) {
        if (cont == 1 || cont == 2) {
            System.out.println("zag zig");
        }
        cont = 0;
        SplayNode temp = new SplayNode(grandPa.data);
        temp.left = grandPa.left;
        temp.right = grandPa.right;
        grandPa.data = son_node.data;
        temp.right = son_node.left;
        grandPa.left = temp;
        dad_node.left = son_node.right;
        grandPa.right = dad_node;
        if (grandPa == getRoot()) {
            root = grandPa;
            flag = false;
        }
        son_node = grandPa;
        dad_node = gotGrandPa(son_node);
    }

    //rotacion zig zig
    public void zigzig(SplayNode grandPa) {
        if (cont < 2) {
            System.out.print("zig zig");
            cont++;
            SplayNode temp = new SplayNode(grandPa.data);
            temp.left = grandPa.left;
            temp.right = grandPa.right;
            temp.left = dad_node.right;
            grandPa.right = temp;
            grandPa.left = dad_node.left;
            grandPa.data = dad_node.data;
            if (grandPa == getRoot()) {
                flag = false;
            }
            dad_node = grandPa;
        } else {
            cont = 0;
        }
    }

    //rotacion zig zag
    public void zigzag(SplayNode<K> grandPa) {
        if (cont == 1 || cont == 2) {
            System.out.println("zig zag");
        }
        cont = 0;
        SplayNode<K> temp = new SplayNode<K>((K)grandPa.data);
        temp.left = grandPa.left;
        temp.right = grandPa.right;
        grandPa.data = (K) son_node.data;
        temp.left = son_node.right;
        grandPa.right = temp;
        dad_node.right = son_node.left;
        grandPa.left = dad_node;
        if (grandPa == getRoot()) {
            root = grandPa;
            flag = false;
        }
        son_node = grandPa;
        dad_node = gotGrandPa(son_node);
    }

    //rotacion zig
    public void zig() {
        if (cont == 2) {
            System.out.println("zig ");
        }
        root.left = son_node.right;
        son_node.right = getRoot();
        root = son_node;
        cont = 0;
    }

    //rotacion zag
    public void zag() {
        if (cont == 2) {
            System.out.println("zag");
        }
        root.right = son_node.left;
        son_node.left = getRoot();
        root = son_node;
        cont = 0;
    }

    //sube el recien insertado a la root
    public void Splay(SplayNode<K> dad, SplayNode<K> son) {
        flag = true;
        dad_node = dad;
        son_node = son;
        while ((flag == true) && (gotGrandPa(dad_node) != null)) {
            SplayNode grandPa = gotGrandPa(dad_node);
            //zag zag
            if ((grandPa.right == dad_node) && (dad_node.right == son_node)) {
                zagzag(grandPa);
            } else {
                //zag zig
                if (grandPa.right == dad_node && 
                	dad_node.left == son_node){
                	
                    zagzig(grandPa);
                } else {
                    //zig zig
                    if (grandPa.left == dad_node && 
                    	dad_node.left == son_node){
                    	
                        zigzig(grandPa);
                    } //zig zag
                    else {
                        zigzag(grandPa);
                    }
                }
            }
        }
        if (son_node != this.root) {
            //zag
            if (getRoot().right == son_node) {
                zag();
            } //zig
            else {
                zig();
            }
        }
    }

    //grandPa de un nieto
    public SplayNode<K> gotGrandPa(SplayNode<K> node) {
        if (node == getRoot()) {
            return null;
        } else {
            SplayNode<K> dad = null;
            @SuppressWarnings("unchecked")
			SplayNode<K> son = this.root;
            
            if(node.data.equals(Integer.class)){
            	while (son != node) {
                    if ((Integer)node.data < (Integer)son.data) {
                        dad = son;
                        son = son.left;
                    } else {
                        dad = son;
                        son = son.right;
                    }
                }
            }            
            return dad;
        }
    }
    
	@Override
    public boolean delete(K data) {
        if (!existBool(data)) {
            System.out.println("El elemento no existe");
            System.out.println("Se bisela = " + lastVisited(data));
            K splay = lastVisited(data);
            search(splay);
            return false;
        } else {
            delete_aux(data);
        }
        return true;
    }

    private SplayNode<K> delete_aux(K data) {
    	SplayNode<K> to_erase = this.root;
        if(data.getClass().equals(Integer.class)){
        	if ((Integer)data == getRoot().data) {
                if ((getRoot().left == null) && (getRoot().right == null)){
                    root = null;
                } else {
                    if ((this.root.left != null) && (getRoot().right != null)){
                        SplayNode<K> aux = this.root;
                        this.root = BigSmall(getRoot());
                        this.root.left = aux.left;
                        this.root.right = aux.right;
                    } else {
                        if (getRoot().right != null) {
                            root = getRoot().right;
                        } else {
                            root = getRoot().left;
                        }
                    }
                }
                
            }else {
                SplayNode<K> dad = null;
                SplayNode<K> son = this.root;
                
                if(data.getClass().equals(Integer.class)){
                	while (son.data != (Integer)data) {
                        if ((Integer)data < (Integer)son.data) {
                            dad = son;
                            son = son.left;
                        } else {
                            dad = son;
                            son = son.right;
                        }
                    }
                }
                
                Splay(dad, son);
                SplayNode<K> raiz = this.root;
                delete_aux((K) this.root.data);
            }
        }
        return to_erase;    	
    }

    //buscar el mayor de los menores
    public SplayNode<K> BigSmall(SplayNode<K> nodo) {
        SplayNode<K> dad = nodo;
        SplayNode<K> aux = nodo.left;
        while (aux.right != null) {
            dad = aux;
            aux = aux.right;
        }
        dad.right = aux.left;
        return aux;
    }    

    //retorna si el elemento existe un elemento
    boolean existBool(K data) {
        SplayNode<K> temp = this.root;
        boolean exist = false;
        
        if(data.getClass().equals(Integer.class)){
        	while (temp != null) {
                if ((Integer)data == temp.data) {
                    exist = true;
                } else {
                    if ((Integer)data > (Integer)temp.data) {
                        temp = temp.right;
                    } else {
                        temp = temp.left;
                    }
                }
            }
        }        
        return exist;
    }

    private K lastVisited(K data) {
        SplayNode<K> nodoAuxiliar = this.root;
        K last_int = nodoAuxiliar.data;
        
        if(data.getClass().equals(Integer.class)){
        	while (nodoAuxiliar != null) {
                if (data == nodoAuxiliar.data){
                    nodoAuxiliar = null;
                } else {
                    if ((Integer)data > (Integer)nodoAuxiliar.data) {
                        nodoAuxiliar = nodoAuxiliar.right;
                    } else {
                        nodoAuxiliar = nodoAuxiliar.left;
                    }
                }
            }
        }        
        return last_int;
    }   
   
    public SplayNode<K> getRoot() {
        return root;
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
	private SimpleList<K> preorden_extended(SplayNode<K> pnode,
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
	
	private SimpleList<K> inorden_extended(SplayNode<K> pnode,
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

	private SimpleList<K> postorden_extended(SplayNode<K> pnode,
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
