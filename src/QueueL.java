public class QueueL<T> {

	// Attributes *****************************************************************
	private Node<T> head;
	private Node<T> tail;
	private Node<T> next;
	private int size;

    // Constructors ****************************************************************
	public QueueL() {}

	public QueueL(Node<T> N) {
		head = N;
		tail = N;
		next = null;
		size = 1;
	}

	// Getters and Setters *********************************************************
	public void setNext(Node<T> N) {
		next = N;
	}
	public Node<T> getNext() {return next;}
	/**
	 * @return the head
	 */
	public Node<T> getHead() {
		return head;
	}

	/**
	 * @param head the head to set
	 */
	public void setHead(Node<T> head) {
		this.head = head;
	}

	/**
	 * @return the tail
	 */
	public Node<T> getTail() {
		return tail;
	}

	/**
	 * @param tail the tail to set
	 */
	public void setTail(Node<T> tail) {
		this.tail = tail;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	// Other methods, specific to Stacks *******************************************
	// peek, dequeue, enqueue, isEmpty

	/* This method returns the head of the queue and leaves it in the queue.
	 * @return
	 */
	public Node<T> peek() {
        return head;
    }

	/** This method removes and returns the head if the queue is not empty.
	 */
	public Node<T> dequeue() {
		if(isEmpty()) return null;

		Node<T> thehead = head;
		if(size == 1) {
			setHead(null);
			setTail(null);
		}
		else{
			setHead(head.getNext());
		}
		size--;
		return thehead;
	}

	/* This methods adds a node that contains data at the end of the queue.
	 * @param data
	 */
	public void enqueue(T data) {
		Node<T> enq = new Node<>(data);

		if(isEmpty()){
			setHead(enq);
			setTail(enq);
		}
		else{
			tail.setNext(enq);
			setTail(tail.getNext());
		}
		size++;
	}

	/** This method returns true if the queue is empty, false otherwise
	 * @return
	 */
	public boolean isEmpty() {
        if(size == 0) return true;
		return false;
	}

}
