public class main {
    public static void main(String[] args){
        //Test Cell.java
        /*
        Cell cellA = new Cell(0,0);
        Cell cellB = new Cell(1,0);

        System.out.println("Cell A south side: " + cellA.hasSouthSide());
        System.out.println("Cell B north side: " + cellB.hasNorthSide());

        cellA.removeSouthSide();
        cellB.removeNorthSide();

        System.out.println("After removing sides: ");
        System.out.println("Cell A south side: " + cellA.hasSouthSide());
        System.out.println("Cell B south side: " + cellB.hasNorthSide());
        */

        //Test Maze.java
        /*
        Maze maze = new Maze(4,5);

        System.out.println("Rows: " + maze.getRows());
        System.out.println("Cols: " + maze.getCols());

        Cell cell = maze.getCell(2,3);

        System.out.println("Cell row: " + cell.getRow());
        System.out.println("Cell col: " + cell.getCol());

        System.out.println("West side: " + cell.hasWestSide());
        System.out.println("East side: " + cell.hasEastSide());

        System.out.println("Visited: " + cell.isVisited());
         */

        //Second Maze Test
        /*
        Maze maze = new Maze(5,6);

        Cell cellA = maze.getCell(3,4);
        Cell cellB = maze.getCell(6,1);

        System.out.println("Cell A: " + (cellA != null));
        System.out.println("Cell B: " + (cellB != null));
         */

        //Test MazeGenerator.java
        /*
        Maze maze = new Maze(4,4);
        MazeGenerator mazeGen = new MazeGenerator(maze);

        Cell curr = maze.getCell(2,2);

        System.out.println("Unvisited neighbours: "
                    + mazeGen.getUnvisitedNeighbours(curr).size());

        curr = maze.getCell(0,0);

        System.out.println("Corner neighbours: "
                    + mazeGen.getUnvisitedNeighbours(curr).size());
        */

        //Random method
        Maze maze = new Maze(3,3);
        MazeGenerator mazeGen = new MazeGenerator(maze);

        Cell curr = maze.getCell(1,1);

        for (int i = 0; i < 5; i++){
            Cell neighbour = mazeGen.getRandomNeighbour(curr);

            System.out.println("Selected cell: ("
                    + neighbour.getRow() + ", "
                    + neighbour.getCol() + ")" );
        }
    }

}
