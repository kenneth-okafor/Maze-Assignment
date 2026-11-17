public class MazeSolver {
    private Maze maze;

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void solveMaze(){

        QueueArrayBased queue =
                new QueueArrayBased(maze.getRows() * maze.getCols());

        boolean[][] visited = new boolean[maze.getRows()][maze.getCols()];

        //Starting cell
        Cell start = maze.getCell(0, 0);

        //Add starting cell to queue
        queue.enqueue(start);

        visited[start.getRow()][start.getCol()] = true;

        System.out.println("BFS starting at (" +
                start.getRow() + ", " +
                start.getCol() + ")");

        int exploredCount = 0;

        while (!queue.isEmpty()){
            Cell curr = (Cell) queue.dequeue();
            exploredCount++;

            System.out.println("Exploring cell: (" +
                    curr.getRow() + "," +
                    curr.getCol() + ")");

            int row = curr.getRow();
            int col = curr.getCol();

            //Checks if there's an opening path to the north
            if (!curr.hasNorthSide()){

                Cell north = maze.getCell(row - 1, col);

                if (north != null && !visited[row -1][col]){
                    queue.enqueue(north);
                    visited[row -1][col] = true;

                    System.out.println("Added north neighbour: (" +
                            north.getRow() + "," +
                            north.getCol() + ")");
                }
            }

            //Checks if there's an opening path to the south
            if (!curr.hasSouthSide()){

                Cell south = maze.getCell(row + 1, col);

                if (south != null && !visited[row + 1][col]){
                    queue.enqueue(south);
                    visited[row + 1][col] = true;

                    System.out.println("Added south neighbour: (" +
                            south.getRow() + "," +
                            south.getCol() + ")");
                }
            }

            //Checks if there's an opening path to the west
            if (!curr.hasWestSide()){

                Cell west = maze.getCell(row, col - 1);

                if (west != null && !visited[row][col - 1]){
                    queue.enqueue(west);
                    visited[row][col - 1] = true;

                    System.out.println("Added west neighbour: (" +
                            west.getRow() + "," +
                            west.getCol() + ")");
                }
            }

            //Checks if there's an opening path to the east
            if (!curr.hasEastSide()){

                Cell east = maze.getCell(row, col + 1);

                if (east != null && !visited[row][col +1]){
                    queue.enqueue(east);
                    visited[row][col+1] = true;

                    System.out.println("Added east neighbour: (" +
                            east.getRow() + "," +
                            east.getCol() + ")");
                }
            }
        }

        System.out.println("Explored count: " + exploredCount);
        System.out.println("Total explored count: " +
                maze.getRows() * maze.getCols());

        System.out.println("Queue empty: " + queue.isEmpty());

        System.out.println("BFS start visited " + visited[0][0]);
        System.out.println("BFS visited " + visited[0][1]);
    }
}
