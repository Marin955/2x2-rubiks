package pkg2x2_cube_solver;

public class Main {

    public static void main(String[] args) {
        
        CubeOperations oper = new CubeOperations();
        SolutionFinding solve;
        
        //creating a Cube object that is in a solved state
        String solved = "ggggyyyyrrrrwwwwoooobbbb";
        String2Cube convertOperation4SolvedState = new String2Cube(solved);
        convertOperation4SolvedState.convert();
        Cube solvedState = new Cube(convertOperation4SolvedState.getCube().piece_positions, convertOperation4SolvedState.getCube().connectionsInfo, "");
        
        //Input of whatever state we want solved
        String stateToBeSolved = "ywborworbyrwogywrbgogbgy";
        String2Cube convertOperation4StateToBeSolved = new String2Cube(stateToBeSolved);
        convertOperation4StateToBeSolved.convert();
        Cube initial = new Cube(convertOperation4StateToBeSolved.getCube().piece_positions, convertOperation4StateToBeSolved.getCube().connectionsInfo, "");
        initial.prev_moves = "Solution: ";
        
        solve = new SolutionFinding(solvedState, initial);
        solve.findSolution();
        System.out.println("---------------------------------");
        System.out.println(solve.getSolution());
        System.out.println("---------------------------------");
    }
}
