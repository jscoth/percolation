package percolation;
import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.princeton.cs.algs4.*;

public class Percolation {
	private int size;	  // width of 1 row on the square grid
	private int endpoint; // the array position of the virtual node at the bottom of the grid
	private WeightedQuickUnionUF grid;
	
	public Percolation (int n) {
		size = n;
		endpoint = (n*n)+1;
		
		// system contains two virtual nodes, one at the top and one at the bottom
		// therefore size is n^2 + 2
		 grid = new WeightedQuickUnionUF((n*n)+2);
		 
		 // the top virtual node is connected to the first row, which is all sites from 1 to n:
		 for (int i = 1; i < n + 1; i++)
		 {
			 System.out.println("DEBUG: union: 0 and " + i );
			 grid.union(0, i);
		 }
	
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
		// not on top row
		if (currentSite > size) {
			grid.union(currentSite, currentSite - size);// north
			System.out.println("connected " + currentSite  +" and " + (currentSite - size) );
		}
		// not on right side
		if (currentSite % size != 0) {
			grid.union(currentSite, currentSite + 1); 	// east
			System.out.println("connected " + currentSite  +" and " + (currentSite +1) );
		}
		//not on left side
		if ((currentSite % size) != 1) {
			grid.union(currentSite, currentSite - 1); 	// west
			System.out.println("connected " + currentSite + " and " + (currentSite - 1) );
		}
		// not on bottom row
		if (currentSite < endpoint - size -1) {
			grid.union(currentSite, currentSite + size); 	// south
			System.out.println("connected " + currentSite  +" and " + (currentSite + size) );
		}

	}
	
	public boolean isOpen(int i, int j) {
		int currentSite = ConvertXY(i, j);
		
		Boolean north,east,west,south;
		north = south = east = west = true;
		
		// not on top row
		if (currentSite > size)
			north = grid.connected(currentSite, currentSite - size);	// north
		// not on right side
		if (currentSite % size != 0)
			east = grid.connected(currentSite, currentSite + 1); 		// east
		//not on left side
		if ((currentSite % size) != 1)
			west = grid.connected(currentSite, currentSite - 1); 		// west
		// not on bottom row
		if (currentSite < endpoint - size -1)
			south = grid.connected(currentSite, currentSite + size); 	// south
		
		return north && east && west && south;
	}
	
	public boolean isFull(int i, int j) {
		if (isOpen(i, j))
			return grid.connected(0, ConvertXY(i,j));
		else
			return false;
		
	}
	
	public boolean percolates() {
		return grid.connected(0, endpoint);
	}
	
	/*
	 * Converts x,y coordinates for use on a 1 dimensional array representing
	 * the same area
	 */
	private int ConvertXY(int x, int y)
	{
		return (x + (y * size) + 1);
	}
	
//	public static void main(String[] args) {
//		Percolation p = new Percolation(3);
//		p.open(1,1);
//		//p.open(1,1);
//		//p.open(1,1);
//		
//		System.out.println(p.percolates());
//		
//		
//	}
}
