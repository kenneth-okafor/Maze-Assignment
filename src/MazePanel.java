import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.util.List;

public class MazePanel extends JPanel {
    private Maze maze;
    private int cellSize = 30;
    private List<Cell> solution;

    public void setSolution(List<Cell> solution) {
        this.solution = solution;
        repaint();
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
        this.solution = null;

        setPreferredSize(new Dimension(
                maze.getCols() * cellSize + 1,
                maze.getRows() * cellSize + 1));

        revalidate();
        repaint();
    }

    public Maze getMaze() {
        return this.maze;
    }

    public MazePanel(Maze maze, List<Cell> solution) {
        this.maze = maze;
        this.solution = solution;

        setPreferredSize(new Dimension(
                maze.getCols() * cellSize + 1,
                maze.getRows() * cellSize + 1));
    }
    @Override
    protected  void  paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);

        for(int row = 0; row < maze.getRows(); row++) {
            for(int col = 0; col < maze.getCols(); col++) {

                Cell cell = maze.getCell(row, col);

                int x = col * cellSize;
                int y = row * cellSize;

                if (solution != null && solution.contains(cell)) {
                    g.setColor(Color.GREEN);
                    g.fillRect(x + 1, y + 1,
                            cellSize - 1, cellSize - 1);
                }

                if (row == 0 && col == 0) {
                    //Starting cell
                    g.setColor(Color.BLUE);
                    g.fillRect(x + 1, y + 1,
                            cellSize - 1, cellSize - 1);

                } else if (row == maze.getRows() - 1
                        && col == maze.getCols() - 1) {
                    //Finishing cell
                    g.setColor(Color.RED);
                    g.fillRect(x + 1, y + 1, cellSize - 1, cellSize - 1);
                }

                //Colour set to black before drawing sides
                g.setColor(Color.BLACK);

                //North side
                if (cell.hasNorthSide()) {
                    g.drawLine(x, y, x + cellSize, y);
                }

                //South side
                if (cell.hasSouthSide()) {
                    g.drawLine(x, y + cellSize,
                            x + cellSize, y + cellSize);
                }

                //West side
                if (cell.hasWestSide()) {
                    g.drawLine(x, y, x,  y + cellSize);
                }

                //East side
                if (cell.hasEastSide()) {
                    g.drawLine(x + cellSize, y,
                            x + cellSize, y + cellSize);
                }
            }
        }
    }
}
