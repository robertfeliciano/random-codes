package mazeAssignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    public boolean findMazePath(int x, int y) {
        if (x >= maze.getNCols() || y >= maze.getNRows() || x < 0 || y < 0) {
        	return false;
        }
        else if (maze.getColor(x, y) != NON_BACKGROUND) {
        	return false;
        }
        else if (x == (maze.getNCols() - 1) && y == (maze.getNRows() - 1)) {
        	maze.recolor(x, y, PATH);
        	return true;
        }
        else {
        	maze.recolor(x, y, PATH);
        	if (findMazePath(x, y+1) || findMazePath(x+1, y) || findMazePath(x, y-1) || findMazePath(x-1, y)) {
        		return true;
        	}
        	else {
        		maze.recolor(x, y, TEMPORARY);
        		return false;
        	}
        }
    }
    
    public ArrayList<ArrayList<PairInt>> findAllMazePaths() {
        return findAllMazePaths(0, 0); // (0, 0) is the start point.
    }
    
    /**
     * finds all maze paths to the exit from the coordinates given in the parameters
     * @param x the x-coordinate of the starting point
     * @param y the y-coordinate of the starting point
     */
    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y){
    	ArrayList<ArrayList<PairInt>> result = new ArrayList<>();
    	Stack<PairInt> trace = new Stack<>();
    	findMazePathStackBased(0, 0, result, trace);
    	return result;
    }
    
    /**
     * Finds all paths to the exit and represents them as lists of coordinates
     * @param x coordinate for starting point
     * @param y coordinate for starting point
     * @param result a list which contains all lists of coordinates needed to reach exit
     * @param trace a stack which traces the current path being recursed over
     * @return result once all possible traces have been found
     */
    public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) {
    	if (x >= maze.getNCols() || y >= maze.getNRows() || x < 0 || y < 0) {
    		return;
    	}
    	else if (maze.getColor(x, y) != NON_BACKGROUND) {		
    		return;
   		}
    	else if (x == (maze.getNCols() - 1) && y == (maze.getNRows() - 1)) {
    		trace.push(new PairInt(x, y));
    		ArrayList<PairInt> tempL = new ArrayList<PairInt>();
    		tempL.addAll(trace);
    		result.add(tempL);
    		trace.pop();
    		maze.recolor(x, y, NON_BACKGROUND);
    	}
    	else {
    		maze.recolor(x, y, PATH);
    		trace.push(new PairInt(x,y));
    		findMazePathStackBased(x, y+1, result, trace);
   			findMazePathStackBased(x+1, y, result, trace);
   			findMazePathStackBased(x, y-1, result, trace);
   			findMazePathStackBased(x-1, y, result, trace);
   			maze.recolor(x, y, NON_BACKGROUND);
   			trace.pop();
   		}
   	}
    
    public ArrayList<PairInt> findMazePathMin() {
        return findMazePathMin(0, 0); // (0, 0) is the start point.
    }
    
    /**
     * finds the shortest path through the maze
     * @param x the x-coordinate of the starting position
     * @param y the y-coordinate of the starting position
     * @return the list of coordinates in the shortest path
     */
    public ArrayList<PairInt> findMazePathMin(int x, int y){
    	ArrayList<ArrayList<PairInt>> res = findAllMazePaths(x, y);
    	if (!(findMazePath())) {
    		return new ArrayList<PairInt>();
    	}
    	//create hashmap of lists of PairInts where the keys to each list is the list's size
    	Map<Integer, ArrayList<PairInt>> map = new HashMap<Integer, ArrayList<PairInt>>();
    	for (ArrayList<PairInt> i : res) {
    		map.put(i.size(), i);
    	}
    	//set min amount to an arbitrarily large number
    	int min = Integer.MAX_VALUE;
    	//if the keys to the hashmap are the sizes of the ArrayLists, we need the min key
    	for (int key : map.keySet()) {
    		if (key < min) {
    			min = key;
    		}
    	}
    	//A is the shortest list; we get it by using the key "min" we just found
    	ArrayList<PairInt> A = map.get(min);
    	return A;
    }
    
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }

    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
}
/*</listing>*/
