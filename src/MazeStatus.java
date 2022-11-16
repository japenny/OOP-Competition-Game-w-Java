public class MazeStatus {

	/************* ATTRIBUTES ************************/
	private Maze M;
	private int[] location = new int[2];

	/************* CONSTRUCTORS ************************/
	public MazeStatus(Maze Current, int row, int col) {
		M = new Maze(Current.getBoard());
		location[0] = row;
		location[1] = col;
	}

	/************* GETTERS ************************/
	public Maze getMaze() {
		return M;
	}

	public int[] getLocation() {
		return location;
	}

	/************* MODIFIERS ************************/
	public void changeLocation(int row, int col) {
		location[0] = row;
		location[1] = col;
	}
}
