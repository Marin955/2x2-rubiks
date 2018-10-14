package pkg2x2_cube_solver;

/* 

 * One instance of a cube. Every piece has it's own index in the "piece_positions" array, for example green-orange-yellow corner is 0.
 * Indexes are given based on:
 *      UP SIDE:        DOWN SIDE:
        
        |0|1|           |4|5|
        |2|3|           |7|6|
    (I used red in front, green on top orientation)

* Adjacency matrix is used for knowing which piece is connected with which. In a solved state, the yellow of the GOY corner is connected
* with the white of the GOW corner, so if the colors of the pieces are opposite of each other then they're considered connected.
* Example of the matrix for the solved state is given in the Excel table.

*/
public class Cube {
    int[] piece_positions = new int[8];
    boolean[][] conn = new boolean[24][24];
    String prev_moves = "Solution: "; //Idea is to store the moves that were done on the cube in order to be able to just print the solution.
    Cube (int[] positions, boolean[][] connections) {
        System.arraycopy(positions, 0, this.piece_positions, 0, 8);
        for (int i=0; i<24; i++) {
            System.arraycopy(connections[i], 0, this.conn[i], 0, 24);
        }
    }
}
