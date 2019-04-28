package pkg2x2_cube_solver;

public class String2Cube {
    
    /*
    *   Class that converts a user inputted String describing the cube state -> into a cube object
    */
    
    //String containing the colors of the cube
    private String str;
    
    //Cube object to be created from the given String
    private Cube cube;
    
    
    boolean[][] grid;
    
    String2Cube(String str) {
        this.str = str;
        grid = new boolean[24][24];
    }

    public void convert() {
        int[] piece_positions = new int[8];
        int[] connectionsInfo = new int[12];
        int row = 0, column = 0, piece;
        CubeOperations operation = new CubeOperations();
        
        String[] defaultCornerStringOrder = new String[8];
        
        defaultCornerStringOrder[0] = "goy";
        defaultCornerStringOrder[1] = "gow";
        defaultCornerStringOrder[2] = "grw";
        defaultCornerStringOrder[3] = "gry";
        defaultCornerStringOrder[4] = "bry";
        defaultCornerStringOrder[5] = "brw";
        defaultCornerStringOrder[6] = "bow";
        defaultCornerStringOrder[7] = "boy";
        
        String[] inputedCornerStrings = new String[8];
        
        inputedCornerStrings[0] = "" + str.charAt(0) + str.charAt(4) + str.charAt(17);
        inputedCornerStrings[1] = "" + str.charAt(1) + str.charAt(16) + str.charAt(13);
        inputedCornerStrings[2] = "" + str.charAt(2) + str.charAt(12) + str.charAt(9);
        inputedCornerStrings[3] = "" + str.charAt(3) + str.charAt(5) + str.charAt(8);
        inputedCornerStrings[4] = "" + str.charAt(20) + str.charAt(6) + str.charAt(11);
        inputedCornerStrings[5] = "" + str.charAt(21) + str.charAt(10) + str.charAt(15);
        inputedCornerStrings[6] = "" + str.charAt(22) + str.charAt(14) + str.charAt(19);
        inputedCornerStrings[7] = "" + str.charAt(23) + str.charAt(18) + str.charAt(7);
        
        for(int i=0; i<8; i++) {
            if("gyo".equals(inputedCornerStrings[i]) || "goy".equals(inputedCornerStrings[i]) || "yog".equals(inputedCornerStrings[i]) || "ygo".equals(inputedCornerStrings[i]) || "ogy".equals(inputedCornerStrings[i]) || "oyg".equals(inputedCornerStrings[i])) piece_positions[i] = 0;
            if("gwo".equals(inputedCornerStrings[i]) || "gow".equals(inputedCornerStrings[i]) || "wog".equals(inputedCornerStrings[i]) || "wgo".equals(inputedCornerStrings[i]) || "ogw".equals(inputedCornerStrings[i]) || "owg".equals(inputedCornerStrings[i])) piece_positions[i] = 1;
            if("gwr".equals(inputedCornerStrings[i]) || "grw".equals(inputedCornerStrings[i]) || "wrg".equals(inputedCornerStrings[i]) || "wgr".equals(inputedCornerStrings[i]) || "rgw".equals(inputedCornerStrings[i]) || "rwg".equals(inputedCornerStrings[i])) piece_positions[i] = 2;
            if("gyr".equals(inputedCornerStrings[i]) || "gry".equals(inputedCornerStrings[i]) || "yrg".equals(inputedCornerStrings[i]) || "ygr".equals(inputedCornerStrings[i]) || "rgy".equals(inputedCornerStrings[i]) || "ryg".equals(inputedCornerStrings[i])) piece_positions[i] = 3;
            if("byr".equals(inputedCornerStrings[i]) || "bry".equals(inputedCornerStrings[i]) || "yrb".equals(inputedCornerStrings[i]) || "ybr".equals(inputedCornerStrings[i]) || "rby".equals(inputedCornerStrings[i]) || "ryb".equals(inputedCornerStrings[i])) piece_positions[i] = 4;
            if("bwr".equals(inputedCornerStrings[i]) || "brw".equals(inputedCornerStrings[i]) || "wrb".equals(inputedCornerStrings[i]) || "wbr".equals(inputedCornerStrings[i]) || "rbw".equals(inputedCornerStrings[i]) || "rwb".equals(inputedCornerStrings[i])) piece_positions[i] = 5;
            if("bwo".equals(inputedCornerStrings[i]) || "bow".equals(inputedCornerStrings[i]) || "wob".equals(inputedCornerStrings[i]) || "wbo".equals(inputedCornerStrings[i]) || "obw".equals(inputedCornerStrings[i]) || "owb".equals(inputedCornerStrings[i])) piece_positions[i] = 6;
            if("byo".equals(inputedCornerStrings[i]) || "boy".equals(inputedCornerStrings[i]) || "yob".equals(inputedCornerStrings[i]) || "ybo".equals(inputedCornerStrings[i]) || "oby".equals(inputedCornerStrings[i]) || "oyb".equals(inputedCornerStrings[i])) piece_positions[i] = 7;
        }
        
        piece = piece_positions[0];
        for(int i=0; i<3; i++) {
            if(defaultCornerStringOrder[piece].charAt(i) == str.charAt(0)) column = piece * 3 + i;
        }
        piece = piece_positions[7];
        for(int i=0; i<3; i++) {
            if(defaultCornerStringOrder[piece].charAt(i) == str.charAt(23)) row = piece * 3 + i;
        }
        grid[row][column] = true;
        grid[column][row] = true;
        
        piece = piece_positions[0];
        for(int i=0; i<3; i++) {
            if(defaultCornerStringOrder[piece].charAt(i) == str.charAt(4)) column = piece * 3 + i;
        }
        piece = piece_positions[1];
        for(int i=0; i<3; i++) {
            if(defaultCornerStringOrder[piece].charAt(i) == str.charAt(13)) row = piece * 3 + i;
        }
        grid[row][column] = true;
        grid[column][row] = true;
        
        piece = piece_positions[0];
        for(int i=0; i<3; i++) {
            if(defaultCornerStringOrder[piece].charAt(i) == str.charAt(17)) column = piece * 3 + i;
        }
        piece = piece_positions[3];
        for(int i=0; i<3; i++) {
            if(defaultCornerStringOrder[piece].charAt(i) == str.charAt(8)) row = piece * 3 + i;
        }
        grid[row][column] = true;
        grid[column][row] = true;
        
        piece = piece_positions[1];
        for(int i=0; i<3; i++) {
            if(defaultCornerStringOrder[piece].charAt(i) == str.charAt(1)) column = piece * 3 + i;
        }
        piece = piece_positions[6];
        for(int i=0; i<3; i++) {
            if(defaultCornerStringOrder[piece].charAt(i) == str.charAt(22)) row = piece * 3 + i;
        }
        grid[row][column] = true;
        grid[column][row] = true;
        
        piece = piece_positions[1];
        for(int i=0; i<3; i++) {
            if(defaultCornerStringOrder[piece].charAt(i) == str.charAt(16)) column = piece * 3 + i;
        }
        piece = piece_positions[2];
        for(int i=0; i<3; i++) {
            if(defaultCornerStringOrder[piece].charAt(i) == str.charAt(9)) row = piece * 3 + i;
        }
        grid[row][column] = true;
        grid[column][row] = true;
        
        piece = piece_positions[2];
        for(int i=0; i<3; i++) {
            if(defaultCornerStringOrder[piece].charAt(i) == str.charAt(2)) column = piece * 3 + i;
        }
        piece = piece_positions[5];
        for(int i=0; i<3; i++) {
            if(defaultCornerStringOrder[piece].charAt(i) == str.charAt(21)) row = piece * 3 + i;
        }
        grid[row][column] = true;
        grid[column][row] = true;
        
        piece = piece_positions[2];
        for(int i=0; i<3; i++) {
            if(defaultCornerStringOrder[piece].charAt(i) == str.charAt(12)) column = piece * 3 + i;
        }
        piece = piece_positions[3];
        for(int i=0; i<3; i++) {
            if(defaultCornerStringOrder[piece].charAt(i) == str.charAt(5)) row = piece * 3 + i;
        }
        grid[row][column] = true;
        grid[column][row] = true;
        
        piece = piece_positions[3];
        for(int i=0; i<3; i++) {
            if(defaultCornerStringOrder[piece].charAt(i) == str.charAt(3)) column = piece * 3 + i;
        }
        piece = piece_positions[4];
        for(int i=0; i<3; i++) {
            if(defaultCornerStringOrder[piece].charAt(i) == str.charAt(20)) row = piece * 3 + i;
        }
        grid[row][column] = true;
        grid[column][row] = true;
        
        piece = piece_positions[7];
        for(int i=0; i<3; i++) {
            if(defaultCornerStringOrder[piece].charAt(i) == str.charAt(7)) column = piece * 3 + i;
        }
        piece = piece_positions[6];
        for(int i=0; i<3; i++) {
            if(defaultCornerStringOrder[piece].charAt(i) == str.charAt(14)) row = piece * 3 + i;
        }
        grid[row][column] = true;
        grid[column][row] = true;
        
        piece = piece_positions[7];
        for(int i=0; i<3; i++) {
            if(defaultCornerStringOrder[piece].charAt(i) == str.charAt(18)) column = piece * 3 + i;
        }
        piece = piece_positions[4];
        for(int i=0; i<3; i++) {
            if(defaultCornerStringOrder[piece].charAt(i) == str.charAt(11)) row = piece * 3 + i;
        }
        grid[row][column] = true;
        grid[column][row] = true;
        
        piece = piece_positions[6];
        for(int i=0; i<3; i++) {
            if(defaultCornerStringOrder[piece].charAt(i) == str.charAt(19)) column = piece * 3 + i;
        }
        piece = piece_positions[5];
        for(int i=0; i<3; i++) {
            if(defaultCornerStringOrder[piece].charAt(i) == str.charAt(10)) row = piece * 3 + i;
        }
        grid[row][column] = true;
        grid[column][row] = true;
        
        piece = piece_positions[5];
        for(int i=0; i<3; i++) {
            if(defaultCornerStringOrder[piece].charAt(i) == str.charAt(15)) column = piece * 3 + i;
        }
        piece = piece_positions[4];
        for(int i=0; i<3; i++) {
            if(defaultCornerStringOrder[piece].charAt(i) == str.charAt(6)) row = piece * 3 + i;
        }
        grid[row][column] = true;
        grid[column][row] = true;
        
        connectionsInfo = operation.twoDboolean2connections(grid);
        
        cube = new Cube(piece_positions, connectionsInfo, "");
    }

    public Cube getCube() {
        return cube;
    }

    public void setCube(Cube cube) {
        this.cube = cube;
    }
    
}
