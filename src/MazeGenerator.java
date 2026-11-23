import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MazeGenerator {
    private Maze maze;
    private Random random =  new Random();
    private StackReferenceBased animationStack;
    private boolean generationComplete;

    public MazeGenerator(Maze maze) {
        this.maze = maze;
    }

    public void startGeneration() {

        //Create fresh stack for animated DFS
        animationStack = new StackReferenceBased();

        generationComplete = false;

        //Start at top-left cell
        Cell start = maze.getCell(0,0);

        //Mark starting cell as visited
        start.setVisited(true);

        //Push onto stack
        animationStack.push(start);
    }

    public boolean generateStep() {

        //No cells left to explore
        if (animationStack.isEmpty()) {
            generationComplete = true;
            return false;
        }

        //Look at current cell
        Cell curr = (Cell) animationStack.peek();

        //Find a random unvisited neighbour
        Cell next = getRandomNeighbour(curr);

        if (next != null) {

            //Remove side between cells
            removeSides(curr, next);

            //Mark neighbour as visited
            next.setVisited(true);

            //Push onto stack
            animationStack.push(next);

        } else  {

            //Dead end: backtrack
            animationStack.pop();
        }

        return true;
    }

    public boolean isGenerationComplete() {
        return generationComplete;
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
            curr.removeSouthSide();
            next.removeNorthSide();
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

    public void generateMaze(){
        startGeneration();

        while (generateStep()) {

        }
    }
}
