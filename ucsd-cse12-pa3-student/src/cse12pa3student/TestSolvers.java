package cse12pa3student;

/*
 * Write your JUnit tests here
 * Use the formatMaze() method to get nicer JUnit output
 */

import org.junit.Test;
import static org.junit.Assert.*;

public class TestSolvers {
	@Test
	public void testSolveSimpleStack() {
		Maze m = new Maze(new String[]{
			"____#__S",
			"____#___",
			"F____#__",
			"___####_",
			"________",
		});
		
		Square s = MazeSolver.solve(m, new StackWorklist());
		System.out.println(String.join("\n", m.showSolution()));
		assertNotNull(s);
		assertArrayEquals(new String[]{
				"***_#**S",
				"*_*_#**_",
				"F_*__#**",
				"__*####*",
				"__******"
			}, m.showSolution());
	}
	
	@Test
	public void testSolveSimpleQueue() {
		Maze m = new Maze(new String[]{
			"____#__S",
			"____#___",
			"F____#__",
			"___####_",
			"________",
		});
		
		Square s = MazeSolver.solve(m, new QueueWorklist());
		System.out.println(String.join("\n", m.showSolution()));
		assertNotNull(s);
		assertArrayEquals(new String[]{
				"____#__S",
				"____#__*",
				"F**__#_*",
				"__*####*",
				"__******"
			}, m.showSolution());
	}
	
	@Test
	public void testSolveTrivialQueue() {
		String[] mazeSpec = {
				"FS"
			};
		testWithQueue(new Maze(mazeSpec), new String[]{
				"FS"
			});
	}
	
	public void testWithStack(Maze maze, String[] expected) {
		Square s = MazeSolver.solve(maze, new StackWorklist());
		assertNotNull(s);
		assertArrayEquals(expected, maze.showSolution());
	}
	public void testWithQueue(Maze maze, String[] expected) {
		Square s = MazeSolver.solve(maze, new QueueWorklist());
		assertNotNull(s);
		assertArrayEquals(expected, maze.showSolution());
	}
	
	@Test
	public void testSolveTrivialStack() {
		String[] mazeSpec = {
				"FS"
			};
		testWithStack(new Maze(mazeSpec), new String[]{
				"FS"
			});
	}
	@Test
	public void testSolveOneStepQueue() {
		String[] mazeSpec = {
				"F_S"
			};
		testWithQueue(new Maze(mazeSpec), new String[]{
				"F*S"
			});
	}
	@Test
	public void testSolveOneStepStack() {
		String[] mazeSpec = {
				"F_S"
			};
		testWithStack(new Maze(mazeSpec), new String[]{
				"F*S"
			});
	}
	
	@Test
	public void testSolveCornerStack() {
		String[] mazeSpec = {
				"F_",
				"_S"
			};
		testWithStack(new Maze(mazeSpec), new String[]{
				"F*",
				"_S"
			});
	}
	
	@Test
	public void testSolveCornerQueue() {
		String[] mazeSpec = {
				"F_",
				"_S"
			};
		testWithQueue(new Maze(mazeSpec), new String[]{
				"F*",
				"_S"
			});
	}
	@Test
	public void testSolveAdjacent() {
		String[] mazeSpec = {
				"__",
				"SF"
			};
		testWithStack(new Maze(mazeSpec), new String[]{
				"**",
				"SF"
			});
	}
	
	/* Worklist Tests */
	@Test
	public void testStackWorklist() {
		Square s1 = new Square(0,0, false);
		Square s2 = new Square(1,1, false);
		SearchWorklist swl = new StackWorklist();
		
		swl.add(s1);
		swl.add(s2);
		assertEquals(false, swl.isEmpty());
		
		swl.remove(); 
		swl.remove();
		assertEquals(true, swl.isEmpty());
		
		//Ensure that behaves like a stack
	}
	

	@Test
	public void testQueueWorklist() {
		Square s1 = new Square(0,0, false);
		Square s2 = new Square(1,1, false);
		SearchWorklist qwl = new QueueWorklist();
		
		qwl.add(s1);
		qwl.add(s2);
		assertEquals(false, qwl.isEmpty());
		
		qwl.remove(); 
		qwl.remove();
		assertEquals(true, qwl.isEmpty());
	}	
	
	@Test
	public void testHelper(SearchWorklist wl, Maze startMaze, String[] expected) {
		Square s = MazeSolver.solve(startMaze, new StackWorklist());
		if(expected == null) { assertNull(s); }
		else {
			String actualStr = formatMaze(startMaze.showSolution());
			String expectedStr = formatMaze(expected);
			assertEquals(expectedStr, actualStr);
		}
	}
	
	@Test
	public void testClassExample() {
		Maze m = new Maze(new String[]{
				"#_#_",
				"____",
				"_##S",
				"F___"
			});
		String[] stackExpected = {
				"#_#_",
				"****",
				"*##S",
				"F___"
			};
		testHelper(new StackWorklist(), m, stackExpected);
	}
	
	
	@Test
	public void testStrings() {
		Maze m = new Maze(new String[]{
				"____#__S",
				"____#___",
				"F____#__",
				"___####_",
				"________",
			});
			
			Square s = MazeSolver.solve(m, new StackWorklist());
			System.out.println(String.join("\n", m.showSolution()));
			assertNotNull(s);
			
			//Cleaner output
			String actual = formatMaze(m.showSolution());
			String expected = formatMaze (new String[]{
					"***_#**S",
					"*_*_#**_",
					"F_*__#**",
					"__*####*",
					"__******"
				});
			
			assertEquals(actual,expected);
	}
	
	/* Helper method to format String[] output as String */
	public String formatMaze(String[] arr) {
		String result = "";
		for (String s: arr)
			result += "\n"+s;
		return (result+"\n");
	}
}



