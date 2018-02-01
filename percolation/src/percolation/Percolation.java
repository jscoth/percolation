package percolation;
import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.princeton.cs.algs4.*;

public class Percolation {
	private int size;	  // width of 1 row on the square grid
	private int endpoint; // the array position of the virtual node at the bottom of the grid
	private WeightedQuickUnionUF grid;
	private boolean[] open;
	
	public Percolation (int n) {
		size = n;
		endpoint = (n*n)+1;
		
		// system contains two virtual nodes, one at the top and one at the bottom
		// therefore size is n^2 + 2
		 grid = new WeightedQuickUnionUF((n*n)+2);
		 open = new boolean[(n*n)+2];
		 
//		 // the top virtual node is connected to the first row, which is all sites from 1 to n:
//		 for (int i = 1; i < n + 1; i++)
//		 {
//			 System.out.println("DEBUG: union: 0 and " + i );
//			 grid.union(0, i);
//		 }
//	
		 // the bottom virtual node is connected to the bottom row, which is 
		 // all sites from (n^2)-n+1 to (n^2)+1
		 for (int i = (n*n)-n+1; i < endpoint; i++)
		 {
			 System.out.println("DEBUG: union: " + endpoint + " and " + i );
			 grid.union(endpoint, i);
		 }
		 
		
	}
	
	public void open(int i, int j) {

		int currentSite = ConvertXY(i, j);	
		open[currentSite] = true;
		// not on top row
		if (currentSite > size) {
			grid.union(currentSite, currentSite - size);// north
			System.out.println("connected " + currentSite  +" and " + (currentSite - size) );
		}
		// else if it is on top row, connect to virtual node
		else
		{
			grid.union(currentSite, 0);
		}
		
		// not on right side
		if (currentSite % size != 0 && isOpen(i,j+1)) {
			grid.union(currentSite, currentSite + 1); 	// east
			System.out.println("connected horizontally " + currentSite  +" and " + (currentSite +1) );
		}
		//not on left side
		if ((currentSite % size) != 1 && isOpen(i,j-1)) {
			grid.union(currentSite, currentSite - 1); 	// west
			System.out.println("connected " + currentSite + " and " + (currentSite - 1) );
		}

	}
	
	public boolean isOpen(int i, int j) {

		return open[ConvertXY(i, j)];
	}
	

	public boolean isFull(int i, int j) {
		if (isOpen(i, j))
			return grid.connected(0, ConvertXY(i,j));
		else
			return false;
		
	}
	
	public boolean percolates() {
		return grid.connected(0, ((size*size)+1));
	}
	
	/*
	 * Converts x,y coordinates for use on a 1 dimensional array representing
	 * the same area
	 */
	private int ConvertXY(int y, int x)
	{
		return (x + (y * size) + 1);
	}
	
	public static void main(String[] args) {
		Percolation p = new Percolation(3);

		
		System.out.println(p.percolates());
		
		
	}
}
