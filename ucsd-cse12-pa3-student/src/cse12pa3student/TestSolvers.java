package cse12pa3student;

/*
 * Write your JUnit tests here
 * Use the formatMaze() method to get nicer JUnit output
 */

import org.junit.Test;
import static org.junit.Assert.*;

public class TestSolvers {


	/* Helper method to compare two mazes */
	public void checkMaze1(SearchWorklist wl, Maze startMaze, String[] expected) {
		Square s = MazeSolver.solve(startMaze, new StackWorklist());
		if(expected == null) { assertNull(s); }
		else {
			String actualStr = formatMaze(startMaze.showSolution());
			String expectedStr = formatMaze(expected);
			assertEquals(expectedStr, actualStr);
		}
	}
	
	public void checkMaze2(SearchWorklist wl, Maze startMaze, String[] expected) {
		Square s = MazeSolver.solve(startMaze, new QueueWorklist());
		if(expected == null) { assertNull(s); }
		else {
			String actualStr = formatMaze(startMaze.showSolution());
			String expectedStr = formatMaze(expected);
			assertEquals(expectedStr, actualStr);
		}
	}
	

	/* Helper method to format String[] output as String */
	public String formatMaze(String[] arr) {
		String result = "";
		for (String s: arr)
			result += "\n"+s;
		return (result+"\n");
	}

	
	
	/* Worklist Tests */
	
	@Test
	public void testStackWorklist() {
		Square s1 = new Square(0,0, false);
		Square s2 = new Square(1,0, false);
		Square s3 = new Square(1,1, false);
		SearchWorklist swl = new StackWorklist();
		
		swl.add(s1);
		swl.add(s2);
		assertEquals(false, swl.isEmpty());
		
		swl.remove(); 
		swl.remove();
		assertEquals(true, swl.isEmpty());
		
		//Ensure stack behavior
		swl.add(s1);
		swl.add(s2);
		swl.add(s3);
		assertEquals(s3, swl.remove());
	}
	

	@Test
	public void testQueueWorklist() {
		Square s1 = new Square(0,0, false);
		Square s2 = new Square(1,0, false);
		Square s3 = new Square(1,1, false);
		SearchWorklist qwl = new QueueWorklist();
		
		qwl.add(s1);
		qwl.add(s2);
		assertEquals(false, qwl.isEmpty());
		
		qwl.remove(); 
		qwl.remove();
		assertEquals(true, qwl.isEmpty());
		
		//Ensure queue behavior
		qwl.add(s1);
		qwl.add(s2);
		qwl.add(s3);
		assertEquals(s1, qwl.remove());
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
		checkMaze1(new StackWorklist(), m, stackExpected);
	}
	
	//@Test
	public void testFailingOrder() {
		String[] expected = {
				"#_#_",
				"****",
				"*##S",
				"F___"
			};
		String[] actualAndWrong= {
				"#_#_",
				"____",
				"_##S",
				"F***"
			};
		assertEquals(formatMaze(expected), formatMaze(actualAndWrong));
	}
	
	@Test
	public void diffOrder(){
		Maze m = new Maze(new String[]{
				"#_#_",
				"____",
				"_##S",
				"F___"
			});
		String[] queueExpected = {
				"#_#_",
				"____",
				"_##S",
				"F***"
			};
		checkMaze2(new QueueWorklist(), m, queueExpected);
	}
	
	@Test
	public void size1(){
		Maze m = new Maze(new String[]{
				"S"
			});
		checkMaze2(new QueueWorklist(), m, null);
	}
	
	@Test
	public void stopEarly(){
		Maze m = new Maze(new String[]{
				"####",
				"####",
				"##FS",
				"####"
			});
		String[] Expected = {
				"####",
				"####",
				"##FS",
				"####"
			};
		checkMaze1(new StackWorklist(), m, Expected);
	}
	
	@Test
	public void stopEarly2(){
		Maze m = new Maze(new String[]{
				"####",
				"####",
				"#F_S",
				"####"
			});
		String[] Expected = {
				"####",
				"####",
				"#F*S",
				"####"
			};
		checkMaze1(new StackWorklist(), m, Expected);
	}
	
	@Test
	public void wall(){
		Maze m = new Maze(new String[]{
				"###_",
				"__#_",
				"_F#S",
				"__#_"
			});
		checkMaze1(new StackWorklist(), m, null);
	}
	
	@Test
	public void diagonalMove1(){
		Maze m = new Maze(new String[]{
				"S###",
				"#_##",
				"##_#",
				"###F"
		});
		checkMaze1(new StackWorklist(), m, null);	
	}
	
	@Test
	public void noWall1(){
		Maze m = new Maze(new String[]{
				"S___",
				"____",
				"____",
				"___F"
		});
		String[] Expected = {
				"S**_",
				"***_",
				"***_",
				"***F"
			};
		checkMaze1(new StackWorklist(), m, Expected);	
	}
	
	@Test
	public void diagonalMove2(){
		Maze m = new Maze(new String[]{
				"S_##",
				"___#",
				"#___",
				"##_F"
		});
		String[] Expected = {
				"S_##",
				"**_#",
				"#**_",
				"##*F"
			};
		checkMaze1(new StackWorklist(), m, Expected);	
	}
}