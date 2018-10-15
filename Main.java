package pkg2x2_cube_solver;

public class Main {

    public static void main(String[] args) {
        
        //Manual input of the solved state in the adjacency matrix. Used it to test out different methods.
        
        boolean[][] matrix = new boolean[24][24];
        int[] pos = new int[8];
        matrix[0][21] = true;
        matrix[1][10] = true;
        matrix[2][5] = true;
        matrix[3][18] = true;
        matrix[4][7] = true;
        matrix[5][2] = true;
        matrix[6][15] = true;
        matrix[7][4] = true;
        matrix[8][11] = true;
        matrix[9][12] = true;
        matrix[10][1] = true;
        matrix[11][8] = true;
        matrix[12][9] = true;
        matrix[13][22] = true;
        matrix[14][17] = true;
        matrix[15][6] = true;
        matrix[16][19] = true;
        matrix[17][14] = true;
        matrix[18][3] = true;
        matrix[19][16] = true;
        matrix[20][23] = true;
        matrix[21][0] = true;
        matrix[22][13] = true;
        matrix[23][20] = true;
        for(int i=0; i<8; i++) {
            pos[i] = i;
        }
        
        Cube initial = new Cube(pos, matrix);
        Cube_Operations oper = new Cube_Operations();
        initial = oper.f2(initial);     //doing a bunch of moves on a solved cube to scramble it, and then solving it
        initial = oper.r(initial);
        initial = oper.u_p(initial);
        initial = oper.f(initial);
        initial = oper.r(initial);
        //initial = oper.u_p(initial);
        //initial = oper.f(initial);
        initial.prev_moves = "Solution: ";
        Solver solveit = new Solver(initial);
        System.out.println(solveit.findSolution());     //it finds the solution, but it doesn't print it correctly, working on it
        
        
        
        
        /*                                          Using this to print the cube state, not relevant to anything
        for (int i=0; i<8; i++) {
            System.out.print(initial.piece_positions[i]);
        }
        for(int i=0; i<24; i++) {
            for (int j=0; j<24; j++) {
                if(initial.conn[i][j] == true) System.out.print("1 ");
                else System.out.print("0 ");
            }
            System.out.println();
        }
        System.out.println("------------");
        for(int i=0; i<24; i++) {
            for (int j=0; j<24; j++) {
                if(initial.conn[i][j] == true) System.out.print("1 ");
                else System.out.print("0 ");
            }
            System.out.println();
        }
        for (int i=0; i<8; i++) {
            System.out.print(initial.piece_positions[i]);
        }
        */
        
    }
    
}
