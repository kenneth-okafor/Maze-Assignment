import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MazeSolver {
    private Maze maze;

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public List<Cell> solveMaze(){

        QueueArrayBased queue =
                new QueueArrayBased(maze.getRows() * maze.getCols());

        boolean[][] visited = new boolean[maze.getRows()][maze.getCols()];

        Cell[][] parent =  new Cell[maze.getRows()][maze.getCols()];

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
                    parent[row -1][col] = curr;

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
                    parent[row + 1][col] = curr;

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
                    parent[row][col - 1] = curr;

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
                    parent[row][col+1] = curr;

                    System.out.println("Added east neighbour: (" +
                            east.getRow() + "," +
                            east.getCol() + ")");
                }
            }
        }

        Cell finish = maze.getCell(
                maze.getRows()-1,
                maze.getCols()-1);

        ArrayList<Cell> path = new ArrayList<>();
        Cell curr = finish;

        while (curr != null){
            path.add(curr);
            curr = parent[curr.getRow()][curr.getCol()];
        }
        Collections.reverse(path);

        System.out.println("Shortest path: ");
        for(int i = 0; i < path.size(); i++){
            Cell cell = path.get(i);

            System.out.print("(" +
                    cell.getRow() + "," +
                    cell.getCol() + ")");

            if (i < path.size()-1){
                System.out.print(" -> ");
            }
        }

        System.out.println();
        System.out.println("Path length: " + (path.size() - 1));

        boolean validPath = true;

        for (int i = 0; i < path.size() - 1; i++){

            Cell current = path.get(i);
            Cell next = path.get(i + 1);

            int rowDiff = next.getRow() - current.getRow();
            int colDiff = next.getCol() - current.getCol();

            if (rowDiff == -1 && colDiff == 0){
                if (current.hasNorthSide() || next.hasSouthSide()){
                    validPath = false;
                }
            } else if (rowDiff == 1 && colDiff == 0){
                if (current.hasSouthSide() || next.hasNorthSide()){
                    validPath = false;
                }
            } else if (rowDiff == 0 && colDiff == -1){
                if (current.hasWestSide() || next.hasEastSide()){
                    validPath = false;
                }
            }  else if (rowDiff == 0 && colDiff == 1){
                if (current.hasEastSide() || next.hasWestSide()){
                    validPath = false;
                }
            } else {
                validPath = false;
            }
        }

        System.out.println("Valid path: " + validPath);

        Cell previous = parent[finish.getRow()][finish.getCol()];

        if (previous != null) {
            System.out.println("Finish cell: (" +
                    finish.getRow() + "," +
                    finish.getCol() + ")");

            System.out.println("Previous cell: (" +
                    previous.getRow() + "," +
                    previous.getCol() + ")");
        }

        System.out.println("Explored count: " + exploredCount);
        System.out.println("Total explored count: " +
                maze.getRows() * maze.getCols());

        System.out.println("Queue empty: " + queue.isEmpty());

        System.out.println("BFS start visited " + visited[0][0]);
        System.out.println("BFS visited " + visited[0][1]);

        return path;
    }
}
