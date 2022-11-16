
public class StackA<T> {
	/** Attributes ****************************************/
	private int top;
	private T[] stack;

	/** Constructors **************************************/
	public StackA() {
		stack = (T[]) new Object[10]; // new T[10]; // by default, the size of the array is 10
		top = -1;
	}

	public StackA(int size) {
		stack = (T[]) new Object[size];
		top = -1;
	}

	/** Getters and Setters *********************************/
	/**
	 * @return the top
	 */
	public int getTop() {
		return top;
	}

	/**
	 * @param top the top to set
	 */
	public void setTop(int top) {
		this.top = top;
	}

	/**
	 * @return the stack
	 */
	public T[] getStack() {
		return stack;
	}

	/**
	 * @param stack the stack to set
	 */
	public void setStack(T[] stack) {
		this.stack = stack;
	}

	/** Other methods, specific to Stacks *********************/
	// peek, pop, push, isEmpty, isFull

	/** This method returns the top element of the stack, but leaves it on the stack
	 * @return
	 */
	public T peek() {
		if(!isEmpty()){
			return getStack()[top];
		}
		return null;
    }

	/** This method returns the top element of the stack, and removes it from the stack
	 * @return
	 */
	public T pop() {
		if(!isEmpty()){
			T toReturn = getStack()[top];
			getStack()[top] = null;
			top--;
			return toReturn;
		}
		return null;
	}

	/** This method adds data onto the stack is the stack is not full
	 * @param data
	 */
	public void push(T data) {
        if(!isFull()){
			top++;
			getStack()[top] = data;
			//set stack to getstack thing
		}

	}

	/** This method returns true is the stack is empty, false otherwise
	 */
	public boolean isEmpty() {
		if(top == -1) return true;
		return false;
	}

	/** This method returns true is the stack is full, false otherwise
	 */
	public boolean isFull() {
		if(top == getStack().length-1 ) return true;
		return false;
	}
}
