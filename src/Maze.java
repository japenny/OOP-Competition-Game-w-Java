import java.util.Arrays;

public class Maze {

	// Attributes *****************************************************************
	private char[][] board;
	private char[][] playboard;
	private int Spoints;
	private int Qpoints;

    // Constructors ****************************************************************
	public Maze() {}

	public Maze(char[][] maze) {
		board = maze;
		/*board = new char[maze.length][maze[0].length];
		for (int i=0; i<maze.length; i++) {
			for (int j=0; j<maze[0].length; j++)
				board[i][j] = maze[i][j];
		}*/
	}

    // Getters and Setters *********************************************************
	/**
	 * @return the board
	 */
	public char[][] getBoard() {
		return board;
	}

	/**
	 * @param board the board to set
	 */
	public void setBoard(char[][] board) {
		this.board = board;
	}

	// Other methods, specific to Mazes *******************************************
	// print board, inDepthFirst, byLevel, QSCompetition
	public void printBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++)
				System.out.print(board[i][j] + ", ");
			System.out.println();
		}
	}


	/**
     * TODO 2.1: Complete below the body of the method inDepthFirst
     * This method navigates through the maze in a depth-first manner.
     * As it goes, it collects rewards and keeps track of the number of visited cells.
     * It returns an 1D array of 2 int elements, representing: the number of visited cells
     * and the total reward at the time when the exit is reached.
     */
	public int[] inDepthFirst() {
		int[] scores = new int[2]; // will hold the number of points and the number of steps
		char[][] currentboard = new char[board.length][board[0].length];
		int[] currentlocation;
		int numSteps = 0; // keeps track of the number of visited cells
		int Spoints = 0; // keeps track of the number of points collected throughout the traversal

		StackA<MazeStatus> S = new StackA<MazeStatus>(this.board.length*this.board[0].length);
		MazeStatus MS = new MazeStatus(this,0,0); // current status as you start the navigation in the top left corner
		S.push(MS); // you initialize the stack

		while (!S.isEmpty()) {
			//We remove maze from top of stack
			MazeStatus popMS = S.pop();

			//gets location and data of Stack
			currentlocation = popMS.getLocation();
			char data = board[currentlocation[0]][currentlocation[1]];

			// Update number of steps
			numSteps++;

			// We check if we are getting any rewards and update the Spoints
			if( data == 'P' ) Spoints += 15;
			else if(data == 'p') Spoints += 10;
			else if(data == 's') Spoints += 5;
			else if(data == 'E'){
				Spoints += 50;
				break;
			}

			//Update cell as visited
			board[currentlocation[0]][currentlocation[1]] = 'X';

			// We identify the next immediate steps in the currentboards (up / left / down / right?)
			if( !(currentlocation[0]-1 < 0) && board[ currentlocation[0]-1 ][ currentlocation[1] ] != 'X'){
				MS = new MazeStatus(this, currentlocation[0]-1 , currentlocation[1]);
				S.push(MS);
			}

			if( !(currentlocation[0]+1 > board.length-1) && board[ currentlocation[0]+1 ][ currentlocation[1] ] != 'X'){
				MS = new MazeStatus(this, currentlocation[0]+1 , currentlocation[1]);
				S.push(MS);
			}


			if( !(currentlocation[1]-1 < 0) && board[ currentlocation[0] ][ currentlocation[1]-1 ] != 'X'){
				MS = new MazeStatus(this, currentlocation[0] , currentlocation[1]-1);
				S.push(MS);
			}

			if(!(currentlocation[1]+1 > board[0].length-1) && board[ currentlocation[0] ][ currentlocation[1]+1 ] != 'X'){
				MS = new MazeStatus(this, currentlocation[0] , currentlocation[1]+1);
				S.push(MS);
			}

		}

		scores[0] = Spoints;
		scores[1] = numSteps;
		return scores;
	}

	/**
     * TODO 2.2: Complete below the body of the method byLevel
     * This method navigates through the maze in a level-order manner.
     * As it goes, it collects rewards and keeps track of the number of visited cells.
     * It returns an 1D array of 2 int elements, representing: the number of visited cells
     * and the total reward at the time when the exit is reached.
     */
	public int[] byLevel() {
		int[] scores = new int[2]; // will hold the number of points and the number of steps
		char[][] currentboard = new char[board.length][board[0].length];
		int[] currentlocation;
		int numSteps = 0; // keeps track of the number of visited cells
		int Qpoints = 0; // keeps track of the number of points collected throughout the traversal

		QueueL<MazeStatus> Q = new QueueL<MazeStatus>();
		MazeStatus MS = new MazeStatus(this,0,0); // current status as you start the navigation in the top left corner
		Q.enqueue(MS);  // you initialize the queue

		while (!Q.isEmpty()) {
			//remove the maze at the head of our queue
			MazeStatus deq = Q.dequeue().getData();

			//gets location and data of Stack
			currentlocation = deq.getLocation();
			char data = board[currentlocation[0]][currentlocation[1]];

            // We update our number of steps
			numSteps++;

			// We check if we are getting any rewards and update the Qpoints
			if( data == 'P' ) Qpoints += 15;
			else if(data == 'p') Qpoints += 10;
			else if(data == 's') Qpoints += 5;
			else if(data == 'E'){
				Qpoints += 50;
				break;
			}

            // We update the cell as visited
			board[currentlocation[0]][currentlocation[1]] = 'X';

            // We identify the next immediate steps (up / left / down / right?)
            // and enqueue the corresponding maze status in the queue
			if( (currentlocation[0]-1 >= 0) && board[ currentlocation[0]-1 ][ currentlocation[1] ] != 'X'){
				MS = new MazeStatus(this, currentlocation[0]-1 , currentlocation[1]);
				Q.enqueue(MS);
			}

			if( (currentlocation[0]+1 < board.length) && board[ currentlocation[0]+1 ][ currentlocation[1] ] != 'X'){
				MS = new MazeStatus(this, currentlocation[0]+1 , currentlocation[1]);
				Q.enqueue(MS);
			}

			if( (currentlocation[1]-1 >= 0) && board[ currentlocation[0] ][ currentlocation[1]-1 ] != 'X'){
				MS = new MazeStatus(this, currentlocation[0] , currentlocation[1]-1);
				Q.enqueue(MS);
			}

			if((currentlocation[1]+1 < board[0].length) && board[ currentlocation[0] ][ currentlocation[1]+1 ] != 'X' ){
				MS = new MazeStatus(this, currentlocation[0] , currentlocation[1]+1);
				Q.enqueue(MS);
			}


		}

		scores[0] = Qpoints;
		scores[1] = numSteps;
		return scores;
	}


	/**
     * TODO 3.1: Complete below the body of the method QScompetition
     * This method runs the previous two methods step by step (as if in parallel)
     * It stops whenever one of the two processes (stack-based or queue-based) reaches the exit.
     * At this point, the process that collected the most reward points wins.
     */

	public void QScompetition() {
        // We start by initializing the two mazes into two similar boards at different addresses
        char[][] Sboard = new char[board.length][board[0].length];
		char[][] Qboard = new char[board.length][board[0].length];
		for (int i=0; i<board.length; i++) {
			for (int j=0; j<board[0].length; j++) {
				Sboard[i][j] = board[i][j];
				Qboard[i][j] = board[i][j];
			}
		}

		char[][] Scurrentboard = new char[board.length][board[0].length];
		char[][] Qcurrentboard = new char[board.length][board[0].length];
		int[] Scurrentlocation;
		int[] Qcurrentlocation;
		Spoints = 0;  // keeps track of the number of points collected throughout the stack-based traversal
		Qpoints = 0;  // keeps track of the number of points collected throughout the queue-based traversal
		boolean found = false; //keeps track if exit is found

		// Initialize the Q for the traversal
		QueueL<MazeStatus> Q = new QueueL<MazeStatus>();
		Maze MQ = new Maze(Qboard);
		MazeStatus QMS = new MazeStatus(MQ,0,0);
		Q.enqueue(QMS);

		// Initialize the S for the traversal
		StackA<MazeStatus> S = new StackA<MazeStatus>(this.board.length*this.board[0].length);
		Maze MS = new Maze(Sboard);
		MazeStatus SMS = new MazeStatus(MS,0,0);
		S.push(SMS);

		//traverse through maze
		while (!Q.isEmpty() && !S.isEmpty()) {
			// We remove the data from Queue and Stack
			MazeStatus deqMS = Q.dequeue().getData();
			MazeStatus popMS = S.pop();

			//gets location of Queue and Stack
			Scurrentlocation = popMS.getLocation();
			Qcurrentlocation = deqMS.getLocation();

			//gets data from Queue and Stack
			char dataQ = board[Scurrentlocation[0]][Scurrentlocation[1]];
			char dataS = board[Qcurrentlocation[0]][Qcurrentlocation[1]];

			// We check if we are getting any rewards and update the Qpoints && Spoints
			//Updates Qpoints
			if( dataQ == 'P' ) Qpoints += 15;
			else if(dataQ == 'p') Qpoints += 10;
			else if(dataQ == 's') Qpoints += 5;
			else if(dataQ == 'E'){
				Qpoints += 50;
				found = true;
			}
			//Updates Spoints
			if( dataS == 'P' ) Spoints += 15;
			else if(dataS == 'p') Spoints += 10;
			else if(dataS == 's') Spoints += 5;
			else if(dataS == 'E'){
				Spoints += 50;
				found = true;
			}

            // We update the cell as visited in the Qcurrentboard && Scurrentboard
			Qcurrentboard[Qcurrentlocation[0]][Qcurrentlocation[1]] = 'X';
			Scurrentboard[Scurrentlocation[0]][Scurrentlocation[1]] = 'X';

            // If one process has reached the end, then we check which one has the most points
            // and we declare the winner and exit
			if(found){
				if(Qpoints == Spoints) System.out.println("Ah snap its a Tie");
				else System.out.println( (Qpoints > Spoints) ? "Queue Wins :)" : "Stack Wins :)" );
				break;
			}

            // If none has reached the exit yet:
            // We identify the next immediate steps in the currentboards (up / left / down / right?)
            // and add the corresponding maze status in the queue/stack

			//Identifies next steps for Queue
			if( (Qcurrentlocation[0]-1 >= 0) && board[ Qcurrentlocation[0]-1 ][ Qcurrentlocation[1] ] != 'X' &&
					Qcurrentboard[ Qcurrentlocation[0]-1 ][ Qcurrentlocation[1] ] != 'X'){
				MazeStatus add = new MazeStatus(this, Qcurrentlocation[0]-1 , Qcurrentlocation[1]);
				Q.enqueue(add);
			}

			if( (Qcurrentlocation[0]+1 < board.length) && board[ Qcurrentlocation[0]+1 ][ Qcurrentlocation[1] ] != 'X' &&
					Qcurrentboard[ Qcurrentlocation[0]+1 ][ Qcurrentlocation[1] ] != 'X'){
				MazeStatus add = new MazeStatus(this, Qcurrentlocation[0]+1 , Qcurrentlocation[1]);
				Q.enqueue(add);
			}

			if( (Qcurrentlocation[1]-1 >= 0) && board[ Qcurrentlocation[0] ][ Qcurrentlocation[1]-1 ] != 'X' &&
					Qcurrentboard[ Qcurrentlocation[0] ][ Qcurrentlocation[1]-1 ] != 'X'){
				MazeStatus add = new MazeStatus(this, Qcurrentlocation[0] , Qcurrentlocation[1]-1);
				Q.enqueue(add);
			}

			if((Qcurrentlocation[1]+1 < board[0].length) && board[ Qcurrentlocation[0] ][ Qcurrentlocation[1]+1 ] != 'X' &&
					Qcurrentboard[ Qcurrentlocation[0] ][ Qcurrentlocation[1]+1 ] != 'X'){
				MazeStatus add = new MazeStatus(this, Qcurrentlocation[0] , Qcurrentlocation[1]+1);
				Q.enqueue(add);
			}


			//Identifies next steps for Stack
			if( !(Scurrentlocation[0]-1 < 0) && board[ Scurrentlocation[0]-1 ][ Scurrentlocation[1] ] != 'X' &&
					Scurrentboard[ Scurrentlocation[0]-1 ][ Scurrentlocation[1] ] != 'X'){
				MazeStatus add = new MazeStatus(this, Scurrentlocation[0]-1 , Scurrentlocation[1]);
				S.push(add);
			}

			if( !(Scurrentlocation[0]+1 > board.length-1) && board[ Scurrentlocation[0]+1 ][ Scurrentlocation[1] ] != 'X' &&
					Scurrentboard[ Scurrentlocation[0]+1 ][ Scurrentlocation[1] ] != 'X'){
				MazeStatus add = new MazeStatus(this, Scurrentlocation[0]+1 , Scurrentlocation[1]);
				S.push(add);
			}

			if( !(Scurrentlocation[1]-1 < 0) && board[ Scurrentlocation[0] ][ Scurrentlocation[1]-1 ] != 'X'  &&
					Scurrentboard[ Scurrentlocation[0] ][ Scurrentlocation[1]-1 ] != 'X'){
				MazeStatus add = new MazeStatus(this, Scurrentlocation[0] , Scurrentlocation[1]-1);
				S.push(add);
			}

			if(!(Scurrentlocation[1]+1 > board[0].length-1) && board[ Scurrentlocation[0] ][ Scurrentlocation[1]+1 ] != 'X' &&
					Scurrentboard[ Scurrentlocation[0] ][ Scurrentlocation[1]+1 ] != 'X'){
				MazeStatus add = new MazeStatus(this, Scurrentlocation[0] , Scurrentlocation[1]+1);
				S.push(add);
			}
 		}

	}



	//returns who won through string
	public String SQwin(){
		if(Qpoints == Spoints) return "Ah snap its a Tie";
		return (Qpoints > Spoints) ? "Queue Wins :)" : "Stack Wins :)";
	}


}
