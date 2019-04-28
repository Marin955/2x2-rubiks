package pkg2x2_cube_solver;

/*  
*    Functions for turning the faces of the cube. 
*    Only RFU moves neccessary. 
*    r is for R
*    r_p is for R'
*    ...
*/

public class CubeOperations {
    
    private int[] tempPiecePositions, tmp_conn, tempConnectionsInfo;
    private boolean[][] boolean2D;
    private int tmp_pos2;
    
    CubeOperations() {
        tempPiecePositions = new int[8];
        
        //adjacency matrix
        boolean2D = new boolean[24][24];
        
        tmp_conn = new int[8];
        tempConnectionsInfo = new int[12];
    }
    
    //method for converting one-dimensional int[] array (that contains information about the 2D adjacency matrix) into the 2D matrix
    public void connectionsTo2Dboolean (int[] connInfo) {
        for(int i=0; i<24; i++) {
            for(int j=0; j<24; j++) {
                boolean2D[i][j] = false;
            }
        }
        
        for(int i=0; i<12; i++) {
            boolean2D[((int)(connInfo[i]/24))][connInfo[i]%24] = true;
            boolean2D[connInfo[i]%24][((int)(connInfo[i]/24))] = true;
        }
    }
    
    //method for converting the 2D adjacency matrix back into the single-dimension array containing the info about the matrix
    public int[] twoDboolean2connections (boolean[][] conn) {
        int counter = 0;
        int[] connInfo = new int[12];
        
        for(int row=0; row<24; row++) {
            for(int column=row; column<24; column++) {
                if(conn[row][column] == true) {
                    connInfo[counter] = row * 24 + column;
                    counter++;
                }
            }
        }
        counter = 0;
        return connInfo;
    }
    
    private void cubeCreate(Cube cubie) {
        System.arraycopy(cubie.piece_positions, 0, tempPiecePositions, 0, 8);
        System.arraycopy(cubie.connectionsInfo, 0, tempConnectionsInfo, 0, 12);
        connectionsTo2Dboolean(tempConnectionsInfo);
    }
    
    /*
    *   Below are methods for each face turn of the cube (R, R', R2, U, U', U2, F, F', F2)
    *   Each one modifies the adjacency matrix and the corner positions of the cube
    */
    
    Cube r(Cube cubie) {
        cubeCreate(cubie);
        Cube rcubie = new Cube(tempPiecePositions, tempConnectionsInfo, cubie.prev_moves);
        rMoveConnectionsSearch(rcubie);
        
        boolean2D[tmp_conn[0]][tmp_conn[2]] = true;       //Connecting new pieces
        boolean2D[tmp_conn[2]][tmp_conn[0]] = true;
        boolean2D[tmp_conn[3]][tmp_conn[5]] = true;
        boolean2D[tmp_conn[5]][tmp_conn[3]] = true;
        boolean2D[tmp_conn[4]][tmp_conn[6]] = true;
        boolean2D[tmp_conn[6]][tmp_conn[4]] = true;
        boolean2D[tmp_conn[7]][tmp_conn[1]] = true;
        boolean2D[tmp_conn[1]][tmp_conn[7]] = true;
        
        tmp_pos2 = rcubie.piece_positions[1];          //Switching positions
        rcubie.piece_positions[1] = rcubie.piece_positions[2]; 
        rcubie.piece_positions[2] = rcubie.piece_positions[5];
        rcubie.piece_positions[5] = rcubie.piece_positions[6];
        rcubie.piece_positions[6] = tmp_pos2;
        
        rcubie.connectionsInfo = twoDboolean2connections(boolean2D);
        
        rcubie.prev_moves = rcubie.prev_moves + "R  ";
        return rcubie;
    }
    
    Cube r_p(Cube cubie) {
        cubeCreate(cubie);
        Cube rcubie = new Cube(tempPiecePositions, tempConnectionsInfo, cubie.prev_moves);
        rMoveConnectionsSearch(rcubie);
        
        boolean2D[tmp_conn[0]][tmp_conn[6]] = true;       //Connecting new pieces
        boolean2D[tmp_conn[6]][tmp_conn[0]] = true;
        boolean2D[tmp_conn[3]][tmp_conn[1]] = true;
        boolean2D[tmp_conn[1]][tmp_conn[3]] = true;
        boolean2D[tmp_conn[4]][tmp_conn[2]] = true;
        boolean2D[tmp_conn[2]][tmp_conn[4]] = true;
        boolean2D[tmp_conn[7]][tmp_conn[5]] = true;
        boolean2D[tmp_conn[5]][tmp_conn[7]] = true;
        
        tmp_pos2 = rcubie.piece_positions[1];          //Switching positions
        rcubie.piece_positions[1] = rcubie.piece_positions[6]; 
        rcubie.piece_positions[6] = rcubie.piece_positions[5];
        rcubie.piece_positions[5] = rcubie.piece_positions[2];
        rcubie.piece_positions[2] = tmp_pos2;
        
        rcubie.connectionsInfo = twoDboolean2connections(boolean2D);
        
        rcubie.prev_moves = rcubie.prev_moves + "R' ";
        return rcubie;
    }
    
    Cube r2(Cube cubie) {
        cubeCreate(cubie);
        Cube rcubie = new Cube(tempPiecePositions, tempConnectionsInfo, cubie.prev_moves);
        rMoveConnectionsSearch(rcubie);
        
        boolean2D[tmp_conn[0]][tmp_conn[5]] = true;       //Connecting new pieces
        boolean2D[tmp_conn[5]][tmp_conn[0]] = true;
        boolean2D[tmp_conn[3]][tmp_conn[6]] = true;
        boolean2D[tmp_conn[6]][tmp_conn[3]] = true;
        boolean2D[tmp_conn[4]][tmp_conn[1]] = true;
        boolean2D[tmp_conn[1]][tmp_conn[4]] = true;
        boolean2D[tmp_conn[7]][tmp_conn[2]] = true;
        boolean2D[tmp_conn[2]][tmp_conn[7]] = true;
        
        tmp_pos2 = rcubie.piece_positions[1];          //Switching positions
        rcubie.piece_positions[1] = rcubie.piece_positions[5]; 
        rcubie.piece_positions[5] = tmp_pos2;
        tmp_pos2 = rcubie.piece_positions[2];
        rcubie.piece_positions[2] = rcubie.piece_positions[6];
        rcubie.piece_positions[6] = tmp_pos2;
        
        rcubie.connectionsInfo = twoDboolean2connections(boolean2D);
        
        rcubie.prev_moves = rcubie.prev_moves + "R2 ";
        return rcubie;
    }
    
    Cube u(Cube cubie) {   
        cubeCreate(cubie);
        Cube ucubie = new Cube(tempPiecePositions, tempConnectionsInfo, cubie.prev_moves);
        uMoveConnectionsSearch(ucubie);
        
        boolean2D[tmp_conn[0]][tmp_conn[6]] = true;
        boolean2D[tmp_conn[6]][tmp_conn[0]] = true;
        boolean2D[tmp_conn[1]][tmp_conn[5]] = true;
        boolean2D[tmp_conn[5]][tmp_conn[1]] = true;
        boolean2D[tmp_conn[4]][tmp_conn[2]] = true;
        boolean2D[tmp_conn[2]][tmp_conn[4]] = true;
        boolean2D[tmp_conn[3]][tmp_conn[7]] = true;
        boolean2D[tmp_conn[7]][tmp_conn[3]] = true;
        
        tmp_pos2 = ucubie.piece_positions[0];
        ucubie.piece_positions[0] = ucubie.piece_positions[3]; 
        ucubie.piece_positions[3] = ucubie.piece_positions[2];
        ucubie.piece_positions[2] = ucubie.piece_positions[1];
        ucubie.piece_positions[1] = tmp_pos2;
        
        ucubie.connectionsInfo = twoDboolean2connections(boolean2D);
        
        ucubie.prev_moves = ucubie.prev_moves + "U  ";
        return ucubie;
    }
    
    Cube u_p(Cube cubie) {
        cubeCreate(cubie);
        Cube ucubie = new Cube(tempPiecePositions, tempConnectionsInfo, cubie.prev_moves);
        uMoveConnectionsSearch(ucubie);
        
        boolean2D[tmp_conn[0]][tmp_conn[4]] = true;
        boolean2D[tmp_conn[4]][tmp_conn[0]] = true;
        boolean2D[tmp_conn[1]][tmp_conn[7]] = true;
        boolean2D[tmp_conn[7]][tmp_conn[1]] = true;
        boolean2D[tmp_conn[6]][tmp_conn[2]] = true;
        boolean2D[tmp_conn[2]][tmp_conn[6]] = true;
        boolean2D[tmp_conn[3]][tmp_conn[5]] = true;
        boolean2D[tmp_conn[5]][tmp_conn[3]] = true;
        
        tmp_pos2 = ucubie.piece_positions[0];
        ucubie.piece_positions[0] = ucubie.piece_positions[1]; 
        ucubie.piece_positions[1] = ucubie.piece_positions[2];
        ucubie.piece_positions[2] = ucubie.piece_positions[3];
        ucubie.piece_positions[3] = tmp_pos2;
        
        ucubie.connectionsInfo = twoDboolean2connections(boolean2D);
        
        ucubie.prev_moves = ucubie.prev_moves + "U' ";
        return ucubie;
    }
    
    Cube u2(Cube cubie) {
        cubeCreate(cubie);
        Cube ucubie = new Cube(tempPiecePositions, tempConnectionsInfo, cubie.prev_moves);
        uMoveConnectionsSearch(ucubie);
        
        boolean2D[tmp_conn[0]][tmp_conn[5]] = true;
        boolean2D[tmp_conn[5]][tmp_conn[0]] = true;
        boolean2D[tmp_conn[1]][tmp_conn[4]] = true;
        boolean2D[tmp_conn[4]][tmp_conn[1]] = true;
        boolean2D[tmp_conn[7]][tmp_conn[2]] = true;
        boolean2D[tmp_conn[2]][tmp_conn[7]] = true;
        boolean2D[tmp_conn[3]][tmp_conn[6]] = true;
        boolean2D[tmp_conn[6]][tmp_conn[3]] = true;
        
        tmp_pos2 = ucubie.piece_positions[0];
        ucubie.piece_positions[0] = ucubie.piece_positions[2]; 
        ucubie.piece_positions[2] = tmp_pos2;
        tmp_pos2 = ucubie.piece_positions[1];
        ucubie.piece_positions[1] = ucubie.piece_positions[3];
        ucubie.piece_positions[3] = tmp_pos2;
        
        ucubie.connectionsInfo = twoDboolean2connections(boolean2D);
        
        ucubie.prev_moves = ucubie.prev_moves + "U2 ";
        return ucubie;
    }
    
    Cube f(Cube cubie) {
        cubeCreate(cubie);
        Cube fcubie = new Cube(tempPiecePositions, tempConnectionsInfo, cubie.prev_moves);
        fMoveConnectionsSearch(fcubie);
        
        boolean2D[tmp_conn[0]][tmp_conn[4]] = true;
        boolean2D[tmp_conn[4]][tmp_conn[0]] = true;
        boolean2D[tmp_conn[3]][tmp_conn[1]] = true;
        boolean2D[tmp_conn[1]][tmp_conn[3]] = true;
        boolean2D[tmp_conn[6]][tmp_conn[2]] = true;
        boolean2D[tmp_conn[2]][tmp_conn[6]] = true;
        boolean2D[tmp_conn[7]][tmp_conn[5]] = true;
        boolean2D[tmp_conn[5]][tmp_conn[7]] = true;
        
        tmp_pos2 = fcubie.piece_positions[3];
        fcubie.piece_positions[3] = fcubie.piece_positions[4]; 
        fcubie.piece_positions[4] = fcubie.piece_positions[5];
        fcubie.piece_positions[5] = fcubie.piece_positions[2];
        fcubie.piece_positions[2] = tmp_pos2;
        
        fcubie.connectionsInfo = twoDboolean2connections(boolean2D);
        
        fcubie.prev_moves = fcubie.prev_moves + "F  ";
        return fcubie;
    }
    
    Cube f_p(Cube cubie) {
        cubeCreate(cubie);
        Cube fcubie = new Cube(tempPiecePositions, tempConnectionsInfo, cubie.prev_moves);
        fMoveConnectionsSearch(fcubie);
        
        boolean2D[tmp_conn[0]][tmp_conn[2]] = true;
        boolean2D[tmp_conn[2]][tmp_conn[0]] = true;
        boolean2D[tmp_conn[5]][tmp_conn[1]] = true;
        boolean2D[tmp_conn[1]][tmp_conn[5]] = true;
        boolean2D[tmp_conn[6]][tmp_conn[4]] = true;
        boolean2D[tmp_conn[4]][tmp_conn[6]] = true;
        boolean2D[tmp_conn[7]][tmp_conn[3]] = true;
        boolean2D[tmp_conn[3]][tmp_conn[7]] = true;
        
        tmp_pos2 = fcubie.piece_positions[3];
        fcubie.piece_positions[3] = fcubie.piece_positions[2]; 
        fcubie.piece_positions[2] = fcubie.piece_positions[5];
        fcubie.piece_positions[5] = fcubie.piece_positions[4];
        fcubie.piece_positions[4] = tmp_pos2;
        
        fcubie.connectionsInfo = twoDboolean2connections(boolean2D);
        
        fcubie.prev_moves = fcubie.prev_moves + "F' ";
        return fcubie;
    }
    
    Cube f2(Cube cubie) {
        cubeCreate(cubie);
        Cube fcubie = new Cube(tempPiecePositions, tempConnectionsInfo, cubie.prev_moves);
        fMoveConnectionsSearch(fcubie);
        
        boolean2D[tmp_conn[0]][tmp_conn[5]] = true;
        boolean2D[tmp_conn[5]][tmp_conn[0]] = true;
        boolean2D[tmp_conn[4]][tmp_conn[1]] = true;
        boolean2D[tmp_conn[1]][tmp_conn[4]] = true;
        boolean2D[tmp_conn[6]][tmp_conn[3]] = true;
        boolean2D[tmp_conn[3]][tmp_conn[6]] = true;
        boolean2D[tmp_conn[7]][tmp_conn[2]] = true;
        boolean2D[tmp_conn[2]][tmp_conn[7]] = true;
        
        tmp_pos2 = fcubie.piece_positions[3];
        fcubie.piece_positions[3] = fcubie.piece_positions[5]; 
        fcubie.piece_positions[5] = tmp_pos2;
        tmp_pos2 = fcubie.piece_positions[2];
        fcubie.piece_positions[2] = fcubie.piece_positions[4];
        fcubie.piece_positions[4] = tmp_pos2;
        
        fcubie.connectionsInfo = twoDboolean2connections(boolean2D);
        
        fcubie.prev_moves = fcubie.prev_moves + "F2 ";
        return fcubie;
    }
    
    private void rMoveConnectionsSearch(Cube cubie) {
        for (int h=0; h<4; h++) {
            for (int i=cubie.piece_positions[h*2]*3; i<(cubie.piece_positions[h*2]*3)+3; i++) {     //Finding their 3x3 grid in the matrix and searching for a connected side 
                for(int j=cubie.piece_positions[(h*2)+1]*3; j<(cubie.piece_positions[(h*2)+1]*3)+3; j++) {
                    if(boolean2D[i][j] == true) {
                        tmp_conn[h*2] = i;                              
                        tmp_conn[(h*2)+1] = j;                          
                        boolean2D[i][j] = false;              
                        boolean2D[j][i] = false;
                    }
                }
            }
        }
    }
    
    private void uMoveConnectionsSearch(Cube cubie) {
        for (int h=0; h<4; h++) {
            for (int i=cubie.piece_positions[h]*3; i<(cubie.piece_positions[h]*3)+3; i++) {
                for(int j=cubie.piece_positions[7-h]*3; j<(cubie.piece_positions[7-h]*3)+3; j++) {
                    if(boolean2D[i][j] == true) {
                        tmp_conn[h] = i;                              //tmp_con0 = 0, tmp_con2=3
                        tmp_conn[7-h] = j;                          //tmp_con1 = 21, tmp_con3=18
                        boolean2D[i][j] = false;
                        boolean2D[j][i] = false;
                    }
                }
            }
        }
    }
    
    private void fMoveConnectionsSearch(Cube cubie) {
        for (int i=cubie.piece_positions[0]*3; i<(cubie.piece_positions[0]*3)+3; i++) {
            for(int j=cubie.piece_positions[3]*3; j<(cubie.piece_positions[3]*3)+3; j++) {
                if(boolean2D[i][j] == true) {
                    tmp_conn[0] = i;                              
                    tmp_conn[3] = j;                          
                    boolean2D[i][j] = false;
                    boolean2D[j][i] = false;
                }
            }
        } 
        
        for (int i=cubie.piece_positions[1]*3; i<(cubie.piece_positions[1]*3)+3; i++) {
            for(int j=cubie.piece_positions[2]*3; j<(cubie.piece_positions[2]*3)+3; j++) {
                if(boolean2D[i][j] == true) {
                    tmp_conn[1] = i;                              
                    tmp_conn[2] = j;                          
                    boolean2D[i][j] = false;
                    boolean2D[j][i] = false;
                }
            }
        }
        for (int i=cubie.piece_positions[6]*3; i<(cubie.piece_positions[6]*3)+3; i++) {
            for(int j=cubie.piece_positions[5]*3; j<(cubie.piece_positions[5]*3)+3; j++) {
                if(boolean2D[i][j] == true) {
                    tmp_conn[6] = i;                              
                    tmp_conn[5] = j;                          
                    boolean2D[i][j] = false;
                    boolean2D[j][i] = false;
                }
            }
        }
        for (int i=cubie.piece_positions[7]*3; i<(cubie.piece_positions[7]*3)+3; i++) {
            for(int j=cubie.piece_positions[4]*3; j<(cubie.piece_positions[4]*3)+3; j++) {
                if(boolean2D[i][j] == true) {
                    tmp_conn[7] = i;                              
                    tmp_conn[4] = j;                          
                    boolean2D[i][j] = false;
                    boolean2D[j][i] = false;
                }
            }
        }
    }
}

