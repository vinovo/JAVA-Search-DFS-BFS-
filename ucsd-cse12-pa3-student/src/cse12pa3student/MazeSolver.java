package cse12pa3student;

/* Class to implement a Maze solver */

public abstract class MazeSolver {

	/**
	 * Find a path from start to finish for a solvable maze
	 * @param maze Maze
	 * @param wl SearchWorklist
	 * @return finish square
	 */
	public static Square solve(Maze maze, SearchWorklist wl) {
		/* Complete this method */
		wl.add(maze.start);
		Square current;
		while (!wl.isEmpty()) {
			current = wl.remove();
			current.visit();
			if (current.equals(maze.finish))
				return current;
			else {
				Square[] n = collectNeighbor(current, maze);
				for (int i = 0; i < n.length; i++) {
					if (n[i] != null) {
						n[i].setPrevious(current);
						wl.add(n[i]);
					}
				}
			}
		}
		return null;
	}

	/* Add any helper methods you want */
	/**
	 * create an array of available neighbor of a Square
	 * @param s Square
	 * @param maze Maze
	 * @return an array of available neighbor
	 */
	static Square[] collectNeighbor(Square s, Maze maze) {
		Square[] a = new Square[4];
		Square East = null, South = null, West = null, North = null;
		if (s.getRow() + 1 < maze.rows)
			South = maze.contents[s.getRow() + 1][s.getCol()];
		if (s.getRow() - 1 >= 0)
			North = maze.contents[s.getRow() - 1][s.getCol()];
		if (s.getCol() + 1 < maze.cols)
			East = maze.contents[s.getRow()][s.getCol() + 1];
		if (s.getCol() - 1 >= 0)
			West = maze.contents[s.getRow()][s.getCol() - 1];
		if (East != null && !East.getIsWall() && !East.isVisited())
			a[0] = East;
		if (South != null && !South.getIsWall() && !South.isVisited())
			a[1] = South;
		if (West != null && !West.getIsWall() && !West.isVisited())
			a[2] = West;
		if (North != null && !North.getIsWall() && !North.isVisited())
			a[3] = North;
		return a;
	}
}
