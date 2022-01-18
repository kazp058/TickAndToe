/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Kevin Zhang <kzhang@espol.edu.ec>
 */
public class TreeNode<E> {
    
    private E content;
    private List<Tree<E>> children;
    
    public TreeNode(E content){
        this.content = content;
        this.children = new LinkedList<>();
    }

    public E getContent() {
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public List<Tree<E>> getChildren() {
        return children;
    }

    public void setChildren(List<Tree<E>> children) {
        this.children = children;
    }    
}
