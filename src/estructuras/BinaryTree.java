/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras;

/**
 *
 * @author Kevin Zhang <kzhang@espol.edu.ec>
 */
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree<T> {

    private BinaryNode<T> root;

    public BinaryTree() {
        this.root = new BinaryNode<>();
    }

    public BinaryTree(BinaryNode<T> root) {
        this.root = root;
    }

    public BinaryTree(T content) {
        this.root = new BinaryNode<>(content);
    }

    public BinaryNode<T> getRoot() {
        return root;
    }

    public void setRoot(BinaryNode<T> root) {
        this.root = root;
    }

    public void setLeft(BinaryTree<T> tree) {
        this.root.setLeft(tree);
    }

    public void setRight(BinaryTree<T> tree) {
        this.root.setRight(tree);
    }

    public BinaryTree<T> getLeft() {
        return this.root.getLeft();
    }

    public BinaryTree<T> getRight() {
        return this.root.getRight();
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public boolean isLeaf() {
        return this.root.getLeft() == null && this.root.getRight() == null;
    }

    public int countLeavesRecursive() {
        if (this.isEmpty()) {
            return 0;
        } else if (this.isLeaf()) {
            return 1;
        } else {
            int leavesLeft = 0;
            int leavesRight = 0;
            if (this.root.getLeft() != null) {
                leavesLeft = this.root.getLeft().countLeavesRecursive();
            }
            if (this.root.getRight() != null) {
                leavesRight = this.root.getRight().countLeavesRecursive();
            }
            return leavesLeft + leavesRight;
        }
    }

    public int countLeavesIterative() {
        Stack<BinaryTree<T>> stack = new Stack();
        int count = 0;
        if (this.isEmpty()) {
            return count;
        } else {
            stack.push(this);
            while (!stack.empty()) {
                BinaryTree<T> subtree = stack.pop();
                if (subtree.root.getLeft() != null) {
                    stack.push(subtree.root.getLeft());
                }
                if (subtree.root.getRight() != null) {
                    stack.push(subtree.root.getRight());
                }
                if (subtree.isLeaf()) {
                    count++;
                }
            }
        }
        return count;
    }

    public BinaryNode<T> recursiveSearch(T content, Comparator<T> cmp) {
        if (this.isEmpty()) {
            return null;
        } else {
            if (cmp.compare(this.root.getContent(), content) == 0) {
                return this.root;
            } else {
                BinaryNode<T> tmp = null;
                if (this.root.getLeft() != null) {
                    tmp = this.root.getLeft().recursiveSearch(content, cmp);
                }
                if (tmp == null) {
                    if (this.root.getRight() != null) {
                        return this.root.getRight().recursiveSearch(content, cmp);
                    }
                }
                return tmp;
            }
        }
    }

    public BinaryNode<T> searchIterative(T content, Comparator<T> cmp) {

        Stack<BinaryTree<T>> stack = new Stack();
        if (this.isEmpty()) {
            return null;
        } else {
            stack.push(this);
            BinaryNode<T> search = null;
            while (!stack.isEmpty()) {
                BinaryTree<T> subtree = stack.pop();
                if (cmp.compare(subtree.getRoot().getContent(), content) == 0) {
                    search = subtree.getRoot();
                }
                if (subtree.getLeft() != null) {
                    stack.push(subtree.getLeft());
                }
                if (subtree.getRight() != null) {
                    stack.push(subtree.getRight());
                }
            }
            return search;
        }

    }

    public BinaryNode<T> getMinRecursive(Comparator<T> cmp) {
        if (this.isEmpty()) {
            return null;
        } else if (this.isLeaf()) {
            return root;
        } else {

            if (this.getLeft() != null && this.getRight() == null) { // solo tiene hijo izquierdo
                BinaryNode<T> leftMin = getLeft().getMinRecursive(cmp);
                return cmp.compare(leftMin.getContent(), root.getContent()) < 0 ? leftMin : root;
            } else if (this.getRight() != null && this.getLeft() == null) { // solo tiene hijo derecho
                BinaryNode<T> rightMin = getRight().getMinRecursive(cmp);
                return cmp.compare(rightMin.getContent(), root.getContent()) < 0 ? rightMin : root;
            } else { //tiene ambos hijos
                BinaryNode<T> leftMin = this.getLeft().getMinRecursive(cmp);
                BinaryNode<T> rightMin = this.getRight().getMinRecursive(cmp);
                BinaryNode<T> minChild = null;
                if (cmp.compare(leftMin.getContent(), rightMin.getContent()) < 0) {
                    minChild = leftMin;
                } else {
                    minChild = rightMin;
                }
                return cmp.compare(minChild.getContent(), root.getContent()) < 0 ? minChild : root;

            }

        }
    }

    public BinaryNode<T> getMinIterative(Comparator<T> cmp) {

        if (this.isEmpty()) {
            return null;
        }

        Stack<BinaryTree<T>> tree = new Stack();
        tree.push(this);
        BinaryNode<T> minimal = root;

        while (!tree.isEmpty()) {

            BinaryTree<T> subtree = tree.pop();
            if (cmp.compare(subtree.getRoot().getContent(), minimal.getContent()) < 0) {
                minimal = subtree.getRoot();
            }

            if (subtree.getLeft() != null) {
                tree.push(subtree.getLeft());
            }
            if (subtree.getRight() != null) {
                tree.push(subtree.getRight());
            }

        }

        return minimal;

    }

    public int countDescendantsRecursive() {
        if (this.isEmpty() || this.isLeaf()) {
            return 0;
        } else {
            int leftDescendants = 0;
            int rightDescendants = 0;

            if (this.getLeft() != null) {
                leftDescendants = 1 + this.getLeft().countDescendantsRecursive();
            }
            if (this.getRight() != null) {
                rightDescendants = 1 + this.getRight().countDescendantsRecursive();
            }

            return leftDescendants + rightDescendants;
        }
    }

    public int countDescendantsIterative() {
        Stack<BinaryTree<T>> stack = new Stack();
        int count = 0;
        if (this.isEmpty()) {
            return count;
        } else {
            stack.push(this);
            while (!stack.empty()) {
                BinaryTree<T> subtree = stack.pop();

                if (subtree.getLeft() != null) {
                    stack.push(subtree.getLeft());
                    count++;
                }
                if (subtree.getRight() != null) {
                    stack.push(subtree.getRight());
                    count++;
                }
            }
        }
        return count;
    }

    public BinaryNode<T> findParentRecursive(BinaryNode<T> son, Comparator<T> cmp) {

        BinaryNode<T> parent = null;

        if (getLeft() != null) {
            if (cmp.compare(getLeft().getRoot().getContent(), son.getContent()) == 0) {
                return root;
            }
            if (this.getLeft().findParentRecursive(son, cmp) != null) {
                parent = getLeft().findParentRecursive(son, cmp);
            }
        }

        if (getRight() != null) {
            if (cmp.compare(getRight().getRoot().getContent(), son.getContent()) == 0) {
                return root;
            }
            if (getRight().findParentRecursive(son, cmp) != null) {
                parent = getRight().findParentRecursive(son, cmp);
            }
        }

        return parent;

    }

    public BinaryNode<T> findParentIterative(BinaryNode<T> son, Comparator<T> cmp) {

        if (isEmpty()) {
            return null;
        }

        Stack<BinaryTree<T>> tree = new Stack();
        tree.push(this);

        while (!tree.isEmpty()) {

            BinaryTree<T> subtree = tree.pop();
            if (subtree.hasLeft()) {
                if (cmp.compare(subtree.getLeft().getRoot().getContent(), son.getContent()) == 0) {
                    return subtree.getRoot();
                }
                tree.push(subtree.getLeft());
            }
            if (subtree.hasRight()) {
                if (cmp.compare(subtree.getRight().getRoot().getContent(), son.getContent()) == 0) {
                    return subtree.getRoot();
                }
                tree.push(subtree.getRight());
            }

        }
        return null;
    }

    public int countLevelsRecursive() {

        if (isEmpty()) {
            return 0;
        }
        if (isLeaf()) {
            return 1;
        }

        int maxLevelLeft = 0;
        int maxLevelRight = 0;

        if (hasLeft()) {
            maxLevelLeft = getLeft().countLevelsRecursive();
        }
        if (hasRight()) {
            maxLevelRight = getRight().countLevelsRecursive();
        }

        return 1 + Math.max(maxLevelLeft, maxLevelRight);

    }

    public int countLevelsIterative() {
        int level = 0;
        if (!isEmpty()) {
            int elementosRestantes = 1;
            int elementosEnNivel = 0;
            Queue<BinaryTree<T>> tree = new ArrayDeque();
            tree.offer(this);
            while (!tree.isEmpty()) {
                BinaryTree<T> subtree = tree.poll();
                elementosRestantes--;

                if (subtree.hasLeft()) {
                    tree.offer(subtree.getLeft());
                    elementosEnNivel++;
                }
                if (subtree.hasRight()) {
                    tree.offer(subtree.getRight());
                    elementosEnNivel++;
                }

                if (elementosRestantes == 0) {
                    level++;
                    elementosRestantes = elementosEnNivel;
                    elementosEnNivel = 0;
                }

            }

        }
        return level;
    }

    public boolean isLeftyRecursive() {
        if (isEmpty()) {
            return true;
        }
        if (isLeaf()) {
            return true;
        }

        if (hasLeft()) {
            if (hasRight()) {   //tiene hijo izq y der
                boolean hasMoreLeftDescendants = getLeft().countDescendantsRecursive() > getRight().countDescendantsRecursive();
                return getLeft().isLeftyRecursive() && getRight().isLeftyRecursive() && hasMoreLeftDescendants;
            } else { //tiene solo hijo izq
                return true;
            }
        } else { //tiene solo hijo der
            return false;
        }

    }

    public boolean isLeftyIterative() {
        boolean isLefty = true;
        if (!isEmpty()) {
            Stack<BinaryTree<T>> tree = new Stack<>();
            tree.push(this);
            while (!tree.isEmpty()) {
                BinaryTree<T> subtree = tree.pop();
                if (subtree.hasLeft()) {
                    tree.push(subtree.getLeft());
                }
                if (subtree.hasRight()) {
                    tree.push(subtree.getRight());
                    if (!subtree.hasLeft()) //si solo tiene hijo derecho, ya no es lefty
                    {
                        isLefty = false;
                    }
                }
            }
            if (hasLeft() && hasRight()) {
                boolean hasMoreLeftDescendants = getLeft().countDescendantsIterative() > getRight().countDescendantsIterative();
                return isLefty && hasMoreLeftDescendants;
            }
        }

        return isLefty;
    }

    public boolean isIdenticalRecursive(BinaryTree<T> other, Comparator<T> cmp) {
        if (isLeaf() && other.isLeaf()) {
            return cmp.compare(getRoot().getContent(), other.getRoot().getContent()) == 0;
        }
        boolean leftIsIdentical = !hasLeft() && !other.hasLeft();
        boolean rightIsIdentical = !hasRight() && !other.hasRight();

        if (hasLeft() && other.hasLeft()) {
            leftIsIdentical = getLeft().isIdenticalRecursive(other.getLeft(), cmp);
        }
        if (hasRight() && other.hasRight()) {
            rightIsIdentical = getRight().isIdenticalRecursive(other.getRight(), cmp);
        }

        return leftIsIdentical && rightIsIdentical;
    }

    public boolean isIdenticalIterative(BinaryTree<T> other, Comparator<T> cmp) {
        boolean isIdentical = true;
        if (!isEmpty() && !other.isEmpty()) {
            Stack<BinaryTree<T>> tree = new Stack();
            Stack<BinaryTree<T>> otherTree = new Stack();
            tree.push(this);
            otherTree.push(other);
            while (!tree.isEmpty() && !otherTree.isEmpty()) {
                BinaryTree<T> subtree = tree.pop();
                BinaryTree<T> otherSubtree = otherTree.pop();

                if (subtree.hasLeft()) {
                    tree.push(subtree.getLeft());
                }
                if (otherSubtree.hasLeft()) {
                    otherTree.push(otherSubtree.getLeft());
                }
                if (subtree.hasRight()) {
                    tree.push(subtree.getRight());
                }
                if (otherSubtree.hasRight()) {
                    otherTree.push(otherSubtree.getRight());
                }

                if (cmp.compare(subtree.getRoot().getContent(), otherSubtree.getRoot().getContent()) != 0) {
                    isIdentical = false;
                }
            }
            if (tree.isEmpty() ^ otherTree.isEmpty()) {
                isIdentical = false;
            }
        }

        if (isEmpty() ^ other.isEmpty()) {
            isIdentical = false;
        }

        return isIdentical;
    }

    public void largestValueOfEachLevelRecursive(Comparator<T> cmp) {
        List<T> valores = new ArrayList<>();
        Recursive(this.getRoot(), valores, 0, cmp);
        for (T valor : valores) {
            System.out.print(valor + " ");
        }
        System.out.println("");

    }

    private void Recursive(BinaryNode<T> node, List<T> valores, int level, Comparator<T> cmp) {
        if (node == null) {
            return;
        }
        if (level == valores.size()) {
            valores.add(node.getContent());
        } else {
            if (cmp.compare(valores.get(level), node.getContent()) >= 0) {
                valores.set(level, valores.get(level));
            } else {
                valores.set(level, node.getContent());
            }

        }
        if (node.getLeft() != null) {
            Recursive(node.getLeft().getRoot(), valores, level + 1, cmp);
        }
        if (node.getRight() != null) {
            Recursive(node.getRight().getRoot(), valores, level + 1, cmp);
        }

    }

    public void largestValueOfEachLevelIterative(Comparator<T> cmp) {
        if (this.isEmpty()) {
            return;
        }
        Stack<BinaryNode<T>> pila = new Stack();
        pila.push(root);

        Map<BinaryNode<T>, Integer> mapa = new HashMap();
        Map<Integer, List<T>> valoresNivel = new HashMap();

        mapa.put(root, 1);

        while (!pila.isEmpty()) {

            BinaryNode<T> bn = pila.pop();
            int nivel = mapa.get(bn) + 1;
            if (bn.getLeft() != null) {
                BinaryNode<T> izquierdo = bn.getLeft().getRoot();
                pila.push(izquierdo);
                mapa.put(izquierdo, nivel);
                if (valoresNivel.containsKey(nivel)) {
                    valoresNivel.get(nivel).add(izquierdo.getContent());
                } else {
                    List<T> l1 = new LinkedList();
                    l1.add(izquierdo.getContent());
                    valoresNivel.put(nivel, l1);
                }
            }
            if (bn.getRight() != null) {
                BinaryNode<T> derecho = bn.getRight().getRoot();
                pila.push(derecho);
                mapa.put(derecho, nivel);
                if (valoresNivel.containsKey(nivel)) {
                    valoresNivel.get(nivel).add(derecho.getContent());
                } else {
                    List<T> lista = new LinkedList();
                    lista.add(derecho.getContent());
                    valoresNivel.put(nivel, lista);
                }
            }
        }
        System.out.print(root.getContent() + " ");

        valoresNivel.keySet().forEach(i -> {
            System.out.print(valoresNivel.get(i).stream().max(cmp).get() + " ");
        });

        System.out.println("");
    }

    public int countNodesWithOnlyChildRecursive() {
        int nodes = 0;
        if (hasLeft()) {
            if (!hasRight()) {
                nodes++;
            } else {
                nodes += getLeft().countNodesWithOnlyChildRecursive() + getRight().countNodesWithOnlyChildRecursive();
            }
        }
        if (hasRight()) {
            if (!hasLeft()) {
                nodes++;
            }
        }

        return nodes;
    }

    public int countNodesWithOnlyChildIterative() {
        int nodes = 0;
        if (!isEmpty()) {
            Stack<BinaryTree<T>> tree = new Stack<>();
            tree.push(this);
            while (!tree.isEmpty()) {
                BinaryTree<T> subtree = tree.pop();
                if (subtree.hasLeft()) {
                    tree.push(subtree.getLeft());
                    if (!subtree.hasRight()) {
                        nodes++;
                    }
                }
                if (subtree.hasRight()) {
                    tree.push(subtree.getRight());
                    if (!subtree.hasLeft()) {
                        nodes++;
                    }
                }
            }
        }

        return nodes;
    }

    public boolean isHeightBalancedRecursive() {
        if (this.isEmpty()) {
            return true;
        }
        if (this.isLeaf()) {
            return true;
        }
        if (this.getLeft() != null && this.getRight() != null) {
            int diferencia = this.getLeft().countLevelsRecursive() - this.getRight().countLevelsRecursive();
            if (diferencia == 1 || diferencia == 0 || diferencia == -1) {
                return this.getLeft().isHeightBalancedRecursive() && this.getRight().isHeightBalancedRecursive();
            }
            return false;
        } else if (this.getLeft() != null) {
            return this.getLeft().isLeaf();
        } else {
            return this.getRight().isLeaf();
        }
    }

    public boolean isHeightBalancedIterative() {
        if (this.isEmpty()) {
            return true;
        }
        Stack<BinaryTree<T>> pila = new Stack<>();
        pila.push(this);

        while (!pila.isEmpty()) {
            BinaryTree<T> bn = pila.pop();
            if (bn.getLeft() != null && bn.getRight() != null) {
                int diferencia = this.getLeft().countLevelsIterative() - this.getRight().countLevelsIterative();
                if (diferencia != 1 && diferencia != 0 && diferencia != -1) {
                    return false;
                }
                pila.push(bn.getLeft());
                pila.push(bn.getRight());
            } else if (bn.getLeft() != null) {
                if (!bn.getLeft().isLeaf()) {
                    return false;
                }
                pila.push(bn.getLeft());

            } else if (bn.getRight() != null) {
                if (!bn.getRight().isLeaf()) {
                    return false;
                }
                pila.push(bn.getRight());
            }
        }
        return true;

    }

    public boolean hasLeft() {
        return getLeft() != null;
    }

    public boolean hasRight() {
        return getRight() != null;
    }
    
    //@ispovala
    public static BinaryTree<Integer> arrayTree(int[] arreglo) {
        boolean mismosValores = true;
        int valor = 0;

        if (arreglo.length >= 2) {
            for (int i = 0; i < arreglo.length - 1; i++) {
                mismosValores = arreglo[i] == arreglo[i + 1];
                valor = arreglo[i];
                if (!mismosValores) {
                    break;
                }
            }
        } else {
            mismosValores = true;
            valor = arreglo[0];
        }

        if (mismosValores) {
            return new BinaryTree<>(valor);
        } else {
            int[] arrIzquierda = Arrays.copyOfRange(arreglo, 0, arreglo.length / 2);
            int[] arrDerecha = Arrays.copyOfRange(arreglo, arreglo.length / 2, arreglo.length);
            BinaryTree<Integer> arbol = new BinaryTree<>(-1);
            arbol.setLeft(arrayTree(arrIzquierda));
            arbol.setRight(arrayTree(arrDerecha));
            return arbol;
        }
    }
    
    /*
    //@ispovala
    public BSTNode<E, K> searchNodeByKey(K key) {

        BSTNode<E, K> temp = root;

        while (temp != null) {
            if (cmp.compare(key, temp.getKey()) < 0) {
                temp = temp.getLeft();
            } else if (cmp.compare(key, temp.getKey()) > 0) {
                temp = temp.getRight();
            } else {
                return temp;
            }
        }
        return null;
    }
    */

}
