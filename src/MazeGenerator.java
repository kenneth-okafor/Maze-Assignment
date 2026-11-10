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

    //removeSides method
    public void removeSides(Cell curr, Cell next){
        int rowDiff = next.getRow() - curr.getRow();
        int colDiff = next.getCol() - curr.getCol();

        //North
        if (rowDiff == -1 && colDiff == 0){
            curr.removeNorthSide();
            next.removeSouthSide();
        }

        //South
        else if (rowDiff == 1 && colDiff == 0){
            curr.removeEastSide();
            next.removeWestSide();
        }

        //West
        else if (rowDiff == 0 && colDiff == -1){
            curr.removeWestSide();
            next.removeEastSide();
        }

        // East
        else if (rowDiff == 0 && colDiff == 1){
            curr.removeEastSide();
            next.removeWestSide();
        }
    }
}
