public class Cell {
    //Position of the cell in the maze
    private int row;
    private int col;

    //Walls surrounding the cell
    private boolean northSide;
    private boolean southSide;
    private boolean westSide;
    private boolean eastSide;

    //To track whether the cell has been visited or not
    private boolean visited;

    //Cell constructor
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;

        //All cells begin with four walls
        this.northSide = true;
        this.southSide = true;
        this.westSide = true;
        this.eastSide = true;

        this.visited = false;
    }

    //Position of the cell
    public int getRow() {
        return this.row;
    }
    public int getCol() {
        return this.col;
    }

    //Checks which sides are present
    public boolean hasNorthSide() {
        return this.northSide;
    }
    public boolean hasSouthSide() {
        return this.southSide;
    }
    public boolean hasWestSide() {
        return this.westSide;
    }
    public boolean hasEastSide() {
        return this.eastSide;
    }

    //Checks whether the cell has been visited
    public boolean isVisited() {
        return this.visited;
    }

    //Updates visited status
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    //Removes sides to make path
    public void removeNorthSide() {
        this.northSide = false;
    }
    public void removeSouthSide() {
        this.southSide = false;
    }
    public void removeWestSide() {
        this.westSide = false;
    }
    public void removeEastSide() {
        this.eastSide = false;
    }
}
