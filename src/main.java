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
        /*
        Maze maze = new Maze(3,3);
        MazeGenerator mazeGen = new MazeGenerator(maze);

        Cell curr = maze.getCell(1,1);

        for (int i = 0; i < 5; i++){
            Cell neighbour = mazeGen.getRandomNeighbour(curr);

            System.out.println("Selected cell: ("
                    + neighbour.getRow() + ", "
                    + neighbour.getCol() + ")" );
        }
         */

        //removeSides test
        /*
        Maze maze = new Maze(3,3);
        MazeGenerator mazeGen = new MazeGenerator(maze);

        Cell curr = maze.getCell(1,1);
        Cell next =  maze.getCell(1,2);

        System.out.println("Before removing sides: ");
        System.out.println("Current east: " + curr.hasEastSide());
        System.out.println("Next west: " + next.hasWestSide());

        mazeGen.removeSides(curr, next);

        System.out.println("After removing sides: ");
        System.out.println("Current east: " + curr.hasEastSide());
        System.out.println("Next west: " + next.hasWestSide());
         */

        //Stack test
        /*
        Maze maze = new Maze(4,4);
        StackReferenceBased stack = new StackReferenceBased();

        Cell one = new Cell(0,0);
        Cell two = new Cell(0,1);
        Cell three = new Cell(0,2);

        //Adding cells to stack
        stack.push(one);
        stack.push(two);
        stack.push(three);

        //Check top cell
        Cell curr = (Cell) stack.peek();

        System.out.println("Top cell: (" +
                curr.getRow() + "," +
                curr.getCol() + ")");

        //Remove top cell
        stack.pop();

        curr = (Cell) stack.peek();

        System.out.println("After pop: (" +
                curr.getRow() + "," +
                curr.getCol() + ")");
         */

        //DFS initialisation stage testing
        /*
        Maze maze = new Maze(4,4);
        MazeGenerator mazeGen = new MazeGenerator(maze);

        mazeGen.generateMaze();

        System.out.println("Starting cell visited: " +
                maze.getCell(0,0).isVisited());
         */

        //DFS loop test
        /*
        Maze maze = new Maze(30,30);
        MazeGenerator mazeGen =  new MazeGenerator(maze);

        mazeGen.generateMaze();
        int visitedCount = 0;

        for (int row = 0; row < maze.getRows(); row++){
            for (int col = 0; col < maze.getCols(); col++){
                if (maze.getCell(row,col).isVisited()){
                    visitedCount++;
                }
            }
        }

        System.out.println("Visited cells: " + visitedCount);
        System.out.println("Total cells: " +
                maze.getRows() * maze.getCols());

        //Check to see if removing a side from one cell
        // removes the corresponding side from its neighbour
        boolean sidesCorrect = true;

        for (int row = 0; row < maze.getRows(); row++){
            for (int col = 0; col < maze.getCols(); col++){

                Cell curr =  maze.getCell(row,col);

                //Check east-west sides
                if (col < maze.getCols() -1){
                    Cell east =  maze.getCell(row,col+1);

                    if (curr.hasEastSide() != east.hasWestSide()){
                        sidesCorrect = false;
                    }
                }

                //Check north-south sides
                if  (row < maze.getRows() -1){
                    Cell south =  maze.getCell(row+1,col);

                    if (curr.hasSouthSide() != south.hasNorthSide()){
                        sidesCorrect = false;
                    }
                }
            }
        }

        System.out.println("Sides consistent: " + sidesCorrect);

        //Path counter
        int paths  = 0;

        for (int row = 0; row < maze.getRows(); row++){
            for (int col = 0; col < maze.getCols(); col++){

                Cell curr =  maze.getCell(row,col);

                //Count open paths to the east
                if (col < maze.getCols() -1 && !curr.hasEastSide()){
                    paths++;
                }

                //Count open paths to the south
                if (row < maze.getRows() -1 && !curr.hasSouthSide()){
                    paths++;
                }
            }
        }

        int expectedPaths = maze.getRows() * maze.getCols() - 1;

        System.out.println("Paths: " + paths);
        System.out.println("Expected paths: " + expectedPaths);
        System.out.println("Perfect maze path count: " +
                (paths == expectedPaths));
         */


        //First queue array test
        /*
        Maze maze =  new Maze(4,4);
        QueueArrayBased queue = new QueueArrayBased();

        Cell one = maze.getCell(0,0);
        Cell two = maze.getCell(0,1);
        Cell three = maze.getCell(0,2);
        Cell four = maze.getCell(0,3);
        Cell five = maze.getCell(0,4);

        //Add cells to queue
        queue.enqueue(one);
        queue.enqueue(two);
        queue.enqueue(three);
        queue.enqueue(four);
        queue.enqueue(five);

        //Retrieve & remove first cell
        Cell curr = (Cell)queue.dequeue();

        System.out.println("First removed: (" +
                curr.getRow() + "," +
                curr.getCol() + ")");

        //Retrieve & remove next cell
        curr = (Cell)queue.dequeue();

        System.out.println("Second removed: (" +
                curr.getRow() + "," +
                curr.getCol() + ")");

        curr = (Cell)queue.dequeue();

        System.out.println("Third removed: (" +
                curr.getRow() + "," +
                curr.getCol() + ")");
         */

        //Updated queue test
        QueueArrayBased queue = new QueueArrayBased(100);
        for(int i = 0; i < 100; i++){
            queue.enqueue(i);
        }

        System.out.println("Queue full: " + queue.isFull());
        System.out.println("First removed: " + queue.dequeue());
        System.out.println("Queue full after dequeue: " + queue.isFull());
    }
}
