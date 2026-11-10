import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MazeGenerator {
    private Maze maze;
    private Random random =  new Random();

    public MazeGenerator(Maze maze) {
        this.maze = maze;
    }

    //Finds every unvisited neighbouring cell
    public List<Cell> getUnvisitedNeighbours(Cell curr){
        List<Cell> neighbors = new ArrayList<>();

        int row = curr.getRow();
        int col = curr.getCol();

        //North
        Cell north = maze.getCell(row - 1, col);
        if (north != null && !north.isVisited()) {
            neighbors.add(north);
        }

        //South
        Cell south = maze.getCell(row + 1, col);
        if (south != null && !south.isVisited()) {
            neighbors.add(south);
        }

        //West
        Cell west = maze.getCell(row, col - 1);
        if (west != null && !west.isVisited()) {
            neighbors.add(west);
        }

        //East
        Cell east = maze.getCell(row, col + 1);
        if (east != null && !east.isVisited()) {
            neighbors.add(east);
        }
        return neighbors;
    }

    //Selects an unvisited neighbouring cell at random
    public Cell getRandomNeighbour(Cell curr){
        List<Cell> neighbors = getUnvisitedNeighbours(curr);

        //Checks if there are no available neighbours
        if (neighbors.isEmpty()){
            return null;
        }

        //Random index generator
        int randomIndex = random.nextInt(neighbors.size());
        return neighbors.get(randomIndex);
    }
}
