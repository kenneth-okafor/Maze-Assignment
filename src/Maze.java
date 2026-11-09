public class Maze {
    //Dimensions of maze
    private int rows;
    private int cols;

    //2D array for cell storage
    private Cell[][] grid;

    //Constructor to create maze
    public Maze(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        //Creating 2D array
        this.grid = new Cell[rows][cols];

        //Creates a cell at every position
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                this.grid[row][col] = new Cell(row, col);
            }
        }
    }

    //Returns num of rows
    public int getRows() {
        return this.rows;
    }
    //Returns num of col
    public int getCols() {
        return this.cols;
    }
    //Returns cell at specific position
    public Cell getCell(int row, int col) {
        return this.grid[row][col];
    }
}
