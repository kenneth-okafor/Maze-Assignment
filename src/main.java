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
        Maze maze = new Maze(4,5);

        System.out.println("Rows: " + maze.getRows());
        System.out.println("Cols: " + maze.getCols());

        Cell cell = maze.getCell(2,3);

        System.out.println("Cell row: " + cell.getRow());
        System.out.println("Cell col: " + cell.getCol());

        System.out.println("West side: " + cell.hasWestSide());
        System.out.println("East side: " + cell.hasEastSide());

        System.out.println("Visited: " + cell.isVisited());
    }

}
