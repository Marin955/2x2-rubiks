package pkg2x2_cube_solver;

import java.util.Arrays;

/* 

 * One instance of a cube. Every piece has it's own index in the "piece_positions" array, for example green-orange-yellow corner is 0.
 * Indexes are given based on:
 *      UP SIDE:        DOWN SIDE:
        
        |0|1|           |4|5|
        |3|2|           |7|6|
    (I used red in front, green on top orientation)

* Adjacency matrix is used for knowing which piece is connected with which. In a solved state, the yellow of the GOY corner is connected
* with the white of the GOW corner, so if the colors of the pieces are opposite of each other then they're considered connected.
* Example of the matrix for the solved state is given in the Excel table.

*/
public class Cube {
    int[] piece_positions = new int[8]; //contains information about which corner-piece is where
    int[] connectionsInfo = new int[12]; //contains information about connections between pieces
    String prev_moves = "Solution: "; //Idea is to store the moves that were done on the cube in order to be able to just print the solution.
    Cube (int[] positions, int[] connectionsInfo, String prev_moves) {
        this.piece_positions = positions;
        this.connectionsInfo = connectionsInfo;
        this.prev_moves = prev_moves;
    }
    
    Cube (Cube cubie) {
        System.arraycopy(cubie.piece_positions, 0, this.piece_positions, 0, 8);
        System.arraycopy(cubie.connectionsInfo, 0, this.connectionsInfo, 0, 12);
        this.prev_moves = cubie.prev_moves;
    }

    //Cube is solved when connections are the same as when the cube is solved. 
    //Piece positions is irrelevant for checking if solved, so hash only the connections.

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Arrays.hashCode(this.connectionsInfo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cube other = (Cube) obj;
        return Arrays.equals(this.connectionsInfo, other.connectionsInfo);
    }
}
