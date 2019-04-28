package pkg2x2_cube_solver;

import java.util.HashSet;
import java.util.Iterator;

public class SolutionFinding {
    
    Cube solvedState, initial, tempCube, tempCube2;
    
    //String that stores the final solution
    String solution;
    
    boolean solutionFound, methodVisited;
    CubeOperations operation;
    
    /*  
    *   Storing all of the generated cube states into a HashSet because it doesn't allow duplicates
    *   and it's fast with adding, removing and checking whether the solution has been found.
    */
    HashSet<Cube> set, tempSet;
    
    Iterator<Cube> iter, iter2;
    
    SolutionFinding(Cube solvedstate, Cube initial) {
        this.solvedState = solvedstate;
        this.initial = initial;
        solutionFound = false;
        methodVisited = false;
        operation = new CubeOperations();
        set = new HashSet<>();
        set.add(initial);
        tempSet = new HashSet<>();
        iter = set.iterator();
    }
    
    public String getSolution() {
        return this.solution;
    }
    
    //recursive function that repeats until the solution is found
    public void findSolution() {
        if(!methodVisited && iter.next() == this.solvedState) this.solution = "Cube is already solved";
        methodVisited = true;
        iter = set.iterator();
        
        //do all 9 moves on every cube state
        while(iter.hasNext()) {
            tempCube = iter.next();
            
            tempCube2 = operation.r(tempCube);
            tempSet.add(new Cube(tempCube2));
            if(tempCube2.hashCode() == solvedState.hashCode()) break;
            tempCube2 = operation.r2(tempCube);
            tempSet.add(new Cube(tempCube2));
            if(tempCube2.hashCode() == solvedState.hashCode()) break;
            tempCube2 = operation.r_p(tempCube);
            tempSet.add(new Cube(tempCube2));
            if(tempCube2.hashCode() == solvedState.hashCode()) break;
            
            tempCube2 = operation.f(tempCube);
            tempSet.add(new Cube(tempCube2));
            if(tempCube2.hashCode() == solvedState.hashCode()) break;
            tempCube2 = operation.f2(tempCube);
            tempSet.add(new Cube(tempCube2));
            if(tempCube2.hashCode() == solvedState.hashCode()) break;
            tempCube2 = operation.f_p(tempCube);
            tempSet.add(new Cube(tempCube2));
            if(tempCube2.hashCode() == solvedState.hashCode()) break;
            
            tempCube2 = operation.u(tempCube);
            tempSet.add(new Cube(tempCube2));
            if(tempCube2.hashCode() == solvedState.hashCode()) break;
            tempCube2 = operation.u2(tempCube);
            tempSet.add(new Cube(tempCube2));
            if(tempCube2.hashCode() == solvedState.hashCode()) break;
            tempCube2 = operation.u_p(tempCube);
            tempSet.add(new Cube(tempCube2));
            if(tempCube2.hashCode() == solvedState.hashCode()) break;
        }
        //add all cube states that differ from previously generated ones
        set.addAll(tempSet);
        
        tempSet.clear();
        System.out.println("Current HashSet size: " + set.size());
        
        iter2 = set.iterator();
        while(iter2.hasNext()) {
            tempCube = iter2.next();
            if(tempCube.hashCode() == solvedState.hashCode()) {
                solutionFound = true;
                solution = tempCube.prev_moves;
                break;
            }
        }
        if(!solutionFound) {
            findSolution();
        }
    }
}
