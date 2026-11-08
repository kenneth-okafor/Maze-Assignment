public class Cell {
    //Position of the cell in the maze
    private int row;
    private int col;

    //Walls surrounding the cell
    private boolean northWall;
    private boolean southWall;
    private boolean westWall;
    private boolean eastWall;

    //To track whether the cell has been visited or not
    private boolean visited;

    //Cell constructor
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;

        //All cells begin with four walls
        this.northWall = true;
        this.southWall = true;
        this.westWall = true;
        this.eastWall = true;

        this.visited = false;
    }

}
