public class Node<T> {

	/* Attributes *******************************************************************/
	private T data;
    private Node<T> next;
    
    /* Constructors *****************************************************************/
    public Node() {}
    
    public Node(T d) {
        data = d;
        next = null;
    }
    
    /* Setters **********************************************************************/
    public void setData(T d) {
        data = d;   
    }
    
    public void setNext(Node<T> N) {
        next = N;
    }
    
    /* Getters **********************************************************************/
    public T getData() {
        return data;   
    }
    
    public Node<T> getNext() {
        return next;   
    }
    
    /* Other methods ***************************************************************/
	/**
	 * This method computes and returns the size of the list.  
	 * The size of a list is its number of nodes.
	 */	
    public int size() {
        Node<T> iter = this;
        int size = 0;
        while (iter != null) {
            size++;
            iter = iter.getNext();
        }
        return size;
    }
    
    public void printNode() {
        System.out.println(data.toString());   
    }
}