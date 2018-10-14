
package pkg2x2_cube_solver;

//Unfinished. Idea is to generate arrays of Cube objects, then doing all face turns on each element, storing everything in new array until a solved state is generated. 

public class Solver {
    Cube cubie;
    String alreadySolved = "Cube is already solved!";
    Cube_Operations oper = new Cube_Operations();
    Solver(Cube cubie) {
        this.cubie = new Cube(cubie.piece_positions, cubie.conn);
    }
    
    String recursive(Cube[] states) {
        String sth = "Nope";
        return sth;
    }
    
    String findSolution() {
        if (oper.isitSolved(cubie)) {
            return alreadySolved;
        }
        else {
            Cube[] initial = new Cube[9];
            initial[0] = oper.r(cubie);
            initial[1] = oper.r_p(cubie);
            initial[2] = oper.r2(cubie);
            initial[3] = oper.u(cubie);
            initial[4] = oper.u_p(cubie);
            initial[5] = oper.u2(cubie);
            initial[6] = oper.f(cubie);
            initial[7] = oper.f_p(cubie);
            initial[8] = oper.f2(cubie);
            for (Cube initial1 : initial) {
                if(oper.isitSolved(initial1)) {
                    return initial1.prev_moves;
                }
            }
            
        }
        return this.alreadySolved;
    }
}
