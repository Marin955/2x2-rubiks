package pkg2x2_cube_solver;

/* Functions for turning the faces of the cube. Only RFU moves neccessary. r_p is for R'. Not optimized because R' just does R x3. */

public class Cube_Operations {
    
    Cube r(Cube cubie) {
        Cube rcubie = new Cube(cubie.piece_positions, cubie.conn);
        int[] tmp_pos = new int[8];
        int[] tmp_conn = new int[8];
        int tmp_pos2;
        for (int h=0; h<4; h++) {               //Finding what piece is in position 0
            for(int i=0; i<8; i++){
                if(rcubie.piece_positions[i] == h*2) {
                    tmp_pos[h*2] = i;                                   
                    break;
                }
            }   
            for(int i=0; i<8; i++){             //Finding what piece is at position 1
                if(rcubie.piece_positions[i] == (h*2)+1) {
                    tmp_pos[(h*2)+1] = i;                               
                    break;
                }
            }
            for (int i=tmp_pos[h*2]*3; i<(tmp_pos[h*2]*3)+3; i++) {     //Finding their 3x3 grid in the matrix and searching for a connected side 
                for(int j=tmp_pos[(h*2)+1]*3; j<(tmp_pos[(h*2)+1]*3)+3; j++) {
                    if(rcubie.conn[i][j] == true) {
                        tmp_conn[h*2] = i;                              
                        tmp_conn[(h*2)+1] = j;                          
                        rcubie.conn[i][j] = false;              //Disconnect it because it will be reconnected with piece in position 2
                        rcubie.conn[j][i] = false;
                    }
                }
            }
        }
        
        rcubie.conn[tmp_conn[0]][tmp_conn[2]] = true;       //Connecting new pieces
        rcubie.conn[tmp_conn[2]][tmp_conn[0]] = true;
        rcubie.conn[tmp_conn[3]][tmp_conn[5]] = true;
        rcubie.conn[tmp_conn[5]][tmp_conn[3]] = true;
        rcubie.conn[tmp_conn[4]][tmp_conn[6]] = true;
        rcubie.conn[tmp_conn[6]][tmp_conn[4]] = true;
        rcubie.conn[tmp_conn[7]][tmp_conn[1]] = true;
        rcubie.conn[tmp_conn[1]][tmp_conn[7]] = true;
        
        tmp_pos2 = rcubie.piece_positions[tmp_pos[1]];          //Switching positions
        rcubie.piece_positions[tmp_pos[1]] = rcubie.piece_positions[tmp_pos[6]]; 
        rcubie.piece_positions[tmp_pos[6]] = rcubie.piece_positions[tmp_pos[5]];
        rcubie.piece_positions[tmp_pos[5]] = rcubie.piece_positions[tmp_pos[2]];
        rcubie.piece_positions[tmp_pos[2]] = tmp_pos2;
        
        rcubie.prev_moves = rcubie.prev_moves + "R ";
        return rcubie;
        
    }
    
    Cube r_p(Cube cubie) {
        Cube rcubie = new Cube(cubie.piece_positions, cubie.conn);
        rcubie = this.r(rcubie);
        rcubie = this.r(rcubie);
        rcubie = this.r(rcubie);
        rcubie.prev_moves = rcubie.prev_moves + "R' ";
        return rcubie;
    }
    
    Cube r2(Cube cubie) {
        Cube rcubie = new Cube(cubie.piece_positions, cubie.conn);
        rcubie = this.r(rcubie);
        rcubie = this.r(rcubie);
        rcubie.prev_moves = rcubie.prev_moves + "R2 ";
        return rcubie;
    }
    
    Cube u(Cube cubie) {        //Same as r method
        Cube ucubie = new Cube(cubie.piece_positions, cubie.conn);
        int[] tmp_pos = new int[8];
        int[] tmp_conn = new int[8];
        int tmp_pos2;
        for (int h=0; h<4; h++) {
            for(int i=0; i<8; i++){
                if(ucubie.piece_positions[i] == h) {
                    tmp_pos[h*2] = i;
                    break;                                  //tmp_pos0 = 0, tmp_pos2 = 1
                }
            }   
            for(int i=0; i<8; i++){
                if(ucubie.piece_positions[i] == (7-h)) {
                    tmp_pos[(h*2)+1] = i;    
                    break;                                  //tmp_pos1 = 7, tmp_pos3 = 6
                }
            }
            for (int i=tmp_pos[h*2]*3; i<(tmp_pos[h*2]*3)+3; i++) {
                for(int j=tmp_pos[(h*2)+1]*3; j<(tmp_pos[(h*2)+1]*3)+3; j++) {
                    if(ucubie.conn[i][j] == true) {
                        tmp_conn[h*2] = i;                              //tmp_con0 = 0, tmp_con2=3
                        tmp_conn[(h*2)+1] = j;                          //tmp_con1 = 21, tmp_con3=18
                        ucubie.conn[i][j] = false;
                        ucubie.conn[j][i] = false;
                    }
                }
            }
        }
        
        ucubie.conn[tmp_conn[0]][tmp_conn[3]] = true;
        ucubie.conn[tmp_conn[3]][tmp_conn[0]] = true;
        ucubie.conn[tmp_conn[2]][tmp_conn[5]] = true;
        ucubie.conn[tmp_conn[5]][tmp_conn[2]] = true;
        ucubie.conn[tmp_conn[4]][tmp_conn[7]] = true;
        ucubie.conn[tmp_conn[7]][tmp_conn[4]] = true;
        ucubie.conn[tmp_conn[6]][tmp_conn[1]] = true;
        ucubie.conn[tmp_conn[1]][tmp_conn[6]] = true;
        
        tmp_pos2 = ucubie.piece_positions[tmp_pos[0]];
        ucubie.piece_positions[tmp_pos[0]] = ucubie.piece_positions[tmp_pos[2]]; 
        ucubie.piece_positions[tmp_pos[2]] = ucubie.piece_positions[tmp_pos[4]];
        ucubie.piece_positions[tmp_pos[4]] = ucubie.piece_positions[tmp_pos[6]];
        ucubie.piece_positions[tmp_pos[6]] = tmp_pos2;
        
        ucubie.prev_moves = ucubie.prev_moves + "U ";
        return ucubie;
    }
    
    Cube u_p(Cube cubie) {
        Cube ucubie = new Cube(cubie.piece_positions, cubie.conn);
        ucubie = this.u(ucubie);
        ucubie = this.u(ucubie);
        ucubie = this.u(ucubie);
        
        ucubie.prev_moves = ucubie.prev_moves + "U' ";
        return ucubie;
    }
    
    Cube u2(Cube cubie) {
        Cube ucubie = new Cube(cubie.piece_positions, cubie.conn);
        ucubie = this.u(ucubie);
        ucubie = this.u(ucubie);
        
        ucubie.prev_moves = ucubie.prev_moves + "U2 ";
        return ucubie;
    }
    
    Cube f(Cube cubie) {            //Same as r method
        Cube fcubie = new Cube(cubie.piece_positions, cubie.conn);
        int[] tmp_pos = new int[8];
        int[] tmp_conn = new int[8];
        int tmp_pos2;
        for (int h=0; h<4; h++) {
            if (h<2) {
                for(int i=0; i<8; i++){
                    if(fcubie.piece_positions[i] == h) {
                        tmp_pos[h*2] = i;
                        break;                                  
                    }
                }   
                for(int i=0; i<8; i++){
                    if(fcubie.piece_positions[i] == (3-h)) {
                        tmp_pos[(h*2)+1] = i;    
                        break;                                  
                    }
                }
                for (int i=tmp_pos[h*2]*3; i<(tmp_pos[h*2]*3)+3; i++) {
                    for(int j=tmp_pos[(h*2)+1]*3; j<(tmp_pos[(h*2)+1]*3)+3; j++) {
                        if(fcubie.conn[i][j] == true) {
                            tmp_conn[h*2] = i;                              
                            tmp_conn[(h*2)+1] = j;                          
                            fcubie.conn[i][j] = false;
                            fcubie.conn[j][i] = false;
                        }
                    }
                }
            }
            else {
                 for(int i=0; i<8; i++){
                    if(fcubie.piece_positions[i] == (h+4)) {
                        tmp_pos[h*2] = i;
                        break;                                  
                    }
                }   
                for(int i=0; i<8; i++){
                    if(fcubie.piece_positions[i] == (7-h)) {
                        tmp_pos[(h*2)+1] = i;    
                        break;                                  
                    }
                }
                for (int i=tmp_pos[h*2]*3; i<(tmp_pos[h*2]*3)+3; i++) {
                    for(int j=tmp_pos[(h*2)+1]*3; j<(tmp_pos[(h*2)+1]*3)+3; j++) {
                        if(fcubie.conn[i][j] == true) {
                            tmp_conn[h*2] = i;                              
                            tmp_conn[(h*2)+1] = j;                          
                            fcubie.conn[i][j] = false;
                            fcubie.conn[j][i] = false;
                        }
                    }
                } 
            }
        }
        
        fcubie.conn[tmp_conn[0]][tmp_conn[7]] = true;
        fcubie.conn[tmp_conn[7]][tmp_conn[0]] = true;
        fcubie.conn[tmp_conn[2]][tmp_conn[1]] = true;
        fcubie.conn[tmp_conn[1]][tmp_conn[2]] = true;
        fcubie.conn[tmp_conn[4]][tmp_conn[3]] = true;
        fcubie.conn[tmp_conn[3]][tmp_conn[4]] = true;
        fcubie.conn[tmp_conn[6]][tmp_conn[5]] = true;
        fcubie.conn[tmp_conn[5]][tmp_conn[6]] = true;
        
        tmp_pos2 = fcubie.piece_positions[tmp_pos[1]];
        fcubie.piece_positions[tmp_pos[1]] = fcubie.piece_positions[tmp_pos[3]]; 
        fcubie.piece_positions[tmp_pos[3]] = fcubie.piece_positions[tmp_pos[5]];
        fcubie.piece_positions[tmp_pos[5]] = fcubie.piece_positions[tmp_pos[7]];
        fcubie.piece_positions[tmp_pos[7]] = tmp_pos2;
        
        fcubie.prev_moves = fcubie.prev_moves + "F ";
        return fcubie;
    }
    
    Cube f_p(Cube cubie) {
        Cube fcubie = new Cube(cubie.piece_positions, cubie.conn);
        fcubie = this.f(fcubie);
        fcubie = this.f(fcubie);
        fcubie = this.f(fcubie);
        
        fcubie.prev_moves = fcubie.prev_moves + "F' ";
        return fcubie;
    }
    
    Cube f2(Cube cubie) {
        Cube fcubie = new Cube(cubie.piece_positions, cubie.conn);
        fcubie = this.f(fcubie);
        fcubie = this.f(fcubie);
        
        fcubie.prev_moves = fcubie.prev_moves + "F2 ";
        return fcubie;
    }
    
    boolean isitSolved(Cube cubie) {        //Function that takes a cube state as an argument and check whether that cube state is solved.
        return cubie.conn[0][21] == true && cubie.conn[1][10] == true && cubie.conn[2][5] == true && cubie.conn[12][9] == true && cubie.conn[13][22] == true && cubie.conn[14][17] == true;
    }
}
