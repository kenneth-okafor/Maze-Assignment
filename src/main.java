import javax.swing.*;
import java.awt.*;
import java.util.List;

public class main {
    public static void main(String[] args){

        Maze maze = new Maze(10,10);

        MazeGenerator mazeGen = new MazeGenerator(maze);
        mazeGen.generateMaze();

        javax.swing.SwingUtilities.invokeLater(() -> {

            javax.swing.JFrame frame =
                    new javax.swing.JFrame("Maze Assignment");

            MazePanel mazePan = new MazePanel(maze, null);
            JPanel buttonPanel = new JPanel();

            String[] mazeSizes = {"5 x 5", "10 x 10", "20 x 20"};

            JComboBox<String> sizeBox = new JComboBox<>(mazeSizes);

            //Default selection 10x10
            sizeBox.setSelectedItem("10 x 10");

            buttonPanel.add(new JLabel("Maze Size:"));
            buttonPanel.add(sizeBox);

            JButton generateButton = new JButton("Create Maze");
            buttonPanel.add(generateButton);

            JButton solveButton = new JButton("Solve Maze");
            buttonPanel.add(solveButton);

            JButton clearButton = new JButton("Clear Maze");
            buttonPanel.add(clearButton);

            generateButton.addActionListener(e -> {

                //new 10 x 10 maze
                String selectedSize = (String) sizeBox.getSelectedItem();

                int size =10;

                if (selectedSize != null) {
                    size = Integer.parseInt(selectedSize.split(" x ")[0]);
                }

                Maze newMaze = new Maze(size,size);

                //Creates random paths using DFS
                MazeGenerator newMazeGen = new MazeGenerator(newMaze);
                newMazeGen.generateMaze();

                //Display new maze
                mazePan.setMaze(newMaze);

                frame.pack();
                frame.setLocationRelativeTo(null);
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

            clearButton.addActionListener(e -> {
                //Clear shortest path
                mazePan.setSolution(null);
            });

            frame.setLayout(new BorderLayout());
            frame.add(mazePan, BorderLayout.CENTER);
            frame.add(buttonPanel, BorderLayout.SOUTH);

            frame.pack();

            frame.setDefaultCloseOperation(
                    javax.swing.JFrame.EXIT_ON_CLOSE);

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
