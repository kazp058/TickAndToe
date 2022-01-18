/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Kevin Zhang <kzhang@espol.edu.ec>
 */
public class Tree<E> {

    private TreeNode<E> root;
    private Comparator cmp;

    public Tree() {
        this((TreeNode<E>) null);
    }
    
    public Tree(E root) {
        this(new TreeNode<E>(root));
    }

    public Tree(TreeNode<E> root) {
        this.root = root;
    }

    public TreeNode<E> getRoot() {
        return root;
    }

    public void setRoot(TreeNode<E> root) {
        this.root = root;
    }

    public List<Tree<E>> getChildren() {
        return root.getChildren();
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public boolean isLeaf() {
        return root.getChildren().isEmpty();
    }

    public void addChildren(E content) {
        TreeNode node = new TreeNode<E>(content);
        addChildren(node);
    }

    public void addChildren(TreeNode<E> node) {
        addChildren(node, root.getChildren().size());
    }
    
    public void addChildren(TreeNode<E> node, int i){
        root.getChildren().add(i, new Tree(node));
    }
    
    public void clear(){
        this.root = null;
    }
    
    public void clearTree(int i){
        root.getChildren().remove(i);
    }
    
    public List<TreeNode<E>> inorderTree(){
        if(this.root == null) return null;
        
        List<TreeNode<E>> result = new LinkedList<>();
        
        
        
        return result;
    }

    @Override
    public String toString() {
        return "Tree{" + root + '}';
    }
}
