package cse12pa3student;

import java.util.ArrayList;
import java.util.Queue;

/*
 * Class to implement SearchWorklist as a Stack and a Queue.
 * You can use any built-in Java collections for this class.
 */

class StackWorklist implements SearchWorklist {
	ArrayList lst;
	
	/**
	 * Constructor of StackWorklist
	 */
	public StackWorklist(){
		lst = new ArrayList();
	}
	
	@Override
	/**
	 * add a Square at the top of lst.
	 * @param c The Square to be added.
	 */
	public void add(Square c) {
		lst.add(0, c);
	}

	@Override
	/**
	 * remove the Square at the top of the lst.
	 * @return the Square removed.
	 */
	public Square remove() {
		return (Square) lst.remove(0);
	}

	@Override
	/**
	 * @return true if lst is empty
	 */
	public boolean isEmpty() {
		if (lst.size() == 0)
			return true;
		return false;
	}
	
}

class QueueWorklist implements SearchWorklist {
	ArrayList lst;
	
	/**
	 * The constructor of QueueWorklist
	 */
	public QueueWorklist(){
		lst = new ArrayList();
	}
	
	@Override
	/**
	 * add a Square at the top of lst.
	 * @param c The Square to be added.
	 */
	public void add(Square c) {
		lst.add(lst.size(), c);
	}

	@Override
	/**
	 * remove the Square at the top of lst
	 * @return the Square removed.
	 */
	public Square remove() {
		return (Square) lst.remove(0);
	}

	@Override
	/**
	 * @return true if lst is empty.
	 */
	public boolean isEmpty() {
		if (lst.size() == 0)
			return true;
		return false;
	}

}

public interface SearchWorklist {
	void add(Square c);
	Square remove();
	boolean isEmpty();
}
