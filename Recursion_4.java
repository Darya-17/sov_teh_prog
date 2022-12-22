public class Main {
    public static class Node<T> {
        private T data;
        private int key;
        private Node<T> leftChild;
        private Node<T> rightChild;

        public Node(T data, int key) {
            this.data = data;
            this.key = key;
        }

        public void setLeftChild(Node<T> newNode) {
            leftChild = newNode;
        }

        public void setRightChild(Node<T> newNode) {
            rightChild = newNode;
        }

        public Node<T> getLeftChild() {
            return leftChild;
        }

        public Node<T> getRightChild() {
            return rightChild;
        }

        public T getData() {
            return data;
        }

        public int getKey() {
            return key;
        }
    }

    public static class BinaryTree<T> {
        private Node<T> root;

        public Node<T> find(int key) {
            Node<T> current = root;
            while (current.getKey() != key) {
                if (key < current.getKey())
                    current = current.getLeftChild();
                else
                    current = current.getRightChild();
                if (current == null)
                    return null;
            }
            return current;
        }

        public void insert(T insertData, int key) {
            Node<T> current = root;
            Node<T> parent;
            Node<T> newNode = new Node<>(insertData, key);
            if (root == null)
                root = newNode;
            else {
                while (true) {
                    parent = current;
                    if (key < current.getKey()) {
                        current = current.getLeftChild();
                        if (current == null) {
                            parent.setLeftChild(newNode);
                            return;
                        }
                    }
                    else {
                        current = current.getRightChild();
                        if (current == null) {
                            parent.setRightChild(newNode);
                            return;
                        }
                    }
                }
            }
        }

        public Node<T> getMinimum(Node<T> startPoint) {
            Node<T> current = startPoint;
            Node<T> parent = current;
            while (current != null) {
                parent = current;
                current = current.getLeftChild();
            }
            return parent;
        }

        public Node<T> getMaximum(Node<T> startPoint) {
            Node<T> current = startPoint;
            Node<T> parent = current;
            while (current != null) {
                parent = current;
                current = current.getRightChild();
            }
            return parent;
        }

        public Node<T> getSuccessor(Node<T> deleteNode) {
            Node<T> parentSuccessor = deleteNode;
            Node<T> successor = deleteNode;
            Node<T> current = successor.getRightChild();
            while (current != null) {
                parentSuccessor = successor;
                successor = current;
                current = current.getLeftChild();
            }

            if (successor != deleteNode.getRightChild()) {
                parentSuccessor.setLeftChild(successor.getRightChild());
                successor.setRightChild(deleteNode.getRightChild());
            }
            return successor;
        }

        public boolean delete(int deleteKey) {
            Node<T> current = root;
            Node<T> parent = current;
            boolean isLeftChild = false;
            while (current.getKey() != deleteKey) {
                parent = current;
                if (deleteKey < current.getKey()) {
                    current = current.getLeftChild();
                    isLeftChild = true;
                } else {
                    isLeftChild = false;
                    current = current.getRightChild();
                }
                if (current == null)
                    return false;
            }

            if (current.getLeftChild() == null && current.getRightChild() == null) {
                if (current == root)
                    current = null;
                else if (isLeftChild)
                    parent.setLeftChild(null);
                else
                    parent.setRightChild(null);
            }
            else if (current.getRightChild() == null) {
                if (current == root)
                    root = current.getLeftChild();
                else if (isLeftChild)
                    parent.setLeftChild(current.getLeftChild());
                else
                    current.setRightChild(current.getLeftChild());
            } else if (current.getLeftChild() == null) {
                if (current == root)
                    root = current.getRightChild();
                else if (isLeftChild)
                    parent.setLeftChild(current.getRightChild());
                else
                    parent.setRightChild(current.getRightChild());
            }
            else {
                Node<T> successor = getSuccessor(current);
                if (current == root)
                    root = successor;
                else if (isLeftChild)
                    parent.setLeftChild(successor);
                else
                    parent.setRightChild(successor);
            }
            return true;
        }

        public void inOrder(Node<T> current) {
            if (current != null) {
                inOrder(current.getLeftChild());
                System.out.println(current.getData() + " ");
                inOrder(current.getRightChild());
            }
        }
    }


    public static void main(String[] args) {
        var tree = new BinaryTree<Integer>();
        tree.insert(3,2);
        tree.insert(5,4);
        tree.insert(5,4);
        tree.insert(6,3);
        tree.insert(50,7);
    }
}