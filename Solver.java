package pkg2x2_cube_solver;

import java.util.*;
/* 
 * It works, but it takes forever, I have to find a way to optimize the algorithm.
 * The longest solution has proven to be max 11 moves long. What I've done requires
 * 9^11 or 31,381,059,609 objects in a linkedlist for the worst case scenario of 11 moves.
 * If the solution is 5 moves long, then the solution is found in approx. 4 seconds.
 * At 6 moves it would take 65 hours, so I have to find a better way, obviously.
*/

public class Solver {
    Cube temp;
    String alreadySolved = "Cube is already solved!";
    Cube_Operations oper = new Cube_Operations();
    LinkedList<Cube> states = new LinkedList<>();       //Idea is to save all possible cube states in a linkedlist
    Solver(Cube cubie) {
        states.addFirst(cubie);
    }
    
    String findSolution() {
        int current_size;
        if(oper.isitSolved(states.getFirst()) == true) {
            return alreadySolved;
        }
        while(true) {           //keep creating cube states until eventually the solution is found
            current_size = states.size();
            for(int i=0; i<current_size; i++) {         //do all 9 (RFU) moves on a cube state and store them, and then do it again and again
                states.add(i*9, oper.r(states.get(i*9)));
                if(oper.isitSolved(states.get(i*9)) == true) {
                        return states.get(i*9).prev_moves;
                }
                states.add(i*9, oper.r_p(states.get(i*9+1)));
                if(oper.isitSolved(states.get(i*9)) == true) {
                        return states.get(i*9).prev_moves;
                }
                states.add(i*9, oper.r2(states.get(i*9+2)));
                if(oper.isitSolved(states.get(i*9)) == true) {
                        return states.get(i*9).prev_moves;
                }
                states.add(i*9, oper.f(states.get(i*9+3)));
                if(oper.isitSolved(states.get(i*9)) == true) {
                        return states.get(i*9).prev_moves;
                }
                states.add(i*9, oper.f_p(states.get(i*9+4)));
                if(oper.isitSolved(states.get(i*9)) == true) {
                        return states.get(i*9).prev_moves;
                }
                states.add(i*9, oper.f2(states.get(i*9+5)));
                if(oper.isitSolved(states.get(i*9)) == true) {
                        return states.get(i*9).prev_moves;
                }
                states.add(i*9, oper.u(states.get(i*9+6)));
                if(oper.isitSolved(states.get(i*9)) == true) {
                        return states.get(i*9).prev_moves;
                }
                states.add(i*9, oper.u_p(states.get(i*9+7)));
                if(oper.isitSolved(states.get(i*9)) == true) {
                        return states.get(i*9).prev_moves;
                }
                states.add(i*9, oper.u2(states.get(i*9+8)));
                if(oper.isitSolved(states.get(i*9)) == true) {
                        return states.get(i*9).prev_moves;
                }
                temp = states.remove(i*9+9);
            }
        }
    }
}