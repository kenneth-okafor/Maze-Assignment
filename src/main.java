import javax.swing.*;
import java.awt.*;
import java.util.List;

public class main {
    public static void main(String[] args){
        //MazePanel initial test
        Maze maze = new Maze(10,10);

        MazeGenerator mazeGen = new MazeGenerator(maze);
        mazeGen.generateMaze();

        javax.swing.SwingUtilities.invokeLater(() -> {

            javax.swing.JFrame frame =
                    new javax.swing.JFrame("Maze Assignment");

            MazePanel mazePan = new MazePanel(maze, null);
            JPanel buttonPanel = new JPanel();

            JButton generateButton = new JButton("Create Maze");
            buttonPanel.add(generateButton);

            JButton solveButton = new JButton("Solve Maze");
            buttonPanel.add(solveButton);

            generateButton.addActionListener(e -> {

                //new 10 x 10 maze
                Maze newMaze = new Maze(10,10);

                //Creates random paths using DFS
                MazeGenerator newMazeGen = new MazeGenerator(newMaze);
                newMazeGen.generateMaze();

                //Display new maze
                mazePan.setMaze(newMaze);
            });

            solveButton.addActionListener(e -> {

                //Display current maze
                Maze currMaze = mazePan.getMaze();

                //BFS solver for maze
                MazeSolver solver = new MazeSolver(currMaze);

                //Find shortest path
                List<Cell> solution = solver.solveMaze();

                //Display solution
                mazePan.setSolution(solution);
            });

            frame.setLayout(new BorderLayout());
            frame.add(mazePan, BorderLayout.CENTER);
            frame.add(buttonPanel, BorderLayout.SOUTH);

            frame.pack();

            frame.setDefaultCloseOperation(
                    javax.swing.JFrame.EXIT_ON_CLOSE);

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            //mazePan.setSolution(solution);
        });
    }
}
