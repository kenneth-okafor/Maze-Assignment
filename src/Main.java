import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //Initial maze
        Maze maze =  new Maze(10,10);
        MazeGenerator  mazeGen = new MazeGenerator(maze);
        mazeGen.generateMaze();

        SwingUtilities.invokeLater(() ->{
            JFrame frame = new JFrame("Maze Assignment");

            MazePanel mazePan = new MazePanel(maze, null);

            //Stores the currently running animation timer
            final Timer[] animationTimer = {null};

            //Tracks whether DFS generation is running
            final boolean[] generating = {false};

            JPanel buttonPanel = new JPanel();

            //Maze size selection
            String[] mazeSizes = {"5 x 5", "10 x 10", "20 x 20"};

            JComboBox<String> sizeBox = new JComboBox<>(mazeSizes);
            sizeBox.setSelectedItem("10 x 10");

            buttonPanel.add(new JLabel("Maze Size:"));
            buttonPanel.add(sizeBox);

            //Buttons
            JButton generateButton = new JButton("Create Maze");
            JButton solveButton = new JButton("Solve Maze");
            JButton clearButton = new JButton("Clear Maze");

            buttonPanel.add(generateButton);
            buttonPanel.add(solveButton);
            buttonPanel.add(clearButton);

            //CREATE MAZE BUTTON
            generateButton.addActionListener(e -> {

                //Stop any running animation
                if (animationTimer[0] != null) {
                    animationTimer[0].stop();
                    animationTimer[0] = null;
                }

                //Get selected maze size
                String selectedSize = (String) sizeBox.getSelectedItem();
                int size = 10;

                if  (selectedSize != null) {
                    size = Integer.parseInt(
                            selectedSize.split(" x ")[0]);
                }

                //Create a new maze
                Maze newMaze = new Maze(size, size);
                MazeGenerator newMazeGen = new MazeGenerator(newMaze);
                newMazeGen.startGeneration();

                //Display the new maze
                mazePan.setMaze(newMaze);

                frame.pack();
                frame.setLocationRelativeTo(null);

                //Prevent solving or clearing during generation
                generating[0] = true;
                solveButton.setEnabled(false);
                clearButton.setEnabled(false);

                //Animate DFS generation
                Timer generationTimer = new Timer(50, event -> {
                    //Perform one DFS step
                    boolean hasMoreSteps = newMazeGen.generateStep();
                    mazePan.repaint();

                    if (!hasMoreSteps) {
                        ((Timer) event.getSource()).stop();
                        animationTimer[0] = null;

                        generating[0] = false;

                        //Maze ready to solve
                        solveButton.setEnabled(true);
                        clearButton.setEnabled(true);

                        System.out.println("Maze generation complete");
                    }
                });
                //Store and start generation timer
                animationTimer[0] = generationTimer;
                generationTimer.start();
            });

            //SOLVE MAZE BUTTON
            solveButton.addActionListener(e -> {

                if (generating[0]) {
                    return;
                }

                //Stop any previous animation
                if (animationTimer[0] != null) {
                    animationTimer[0].stop();
                    animationTimer[0] = null;
                }

                //Get current maze
                Maze currMaze = mazePan.getMaze();

                //Solve using BFS and lab Queue
                MazeSolver solver = new MazeSolver(currMaze);

                List<Cell> solution = solver.solveMaze();

                //Get BFS exploration order
                List<Cell> explored = solver.getExploredCells();

                //Clear previous exploration and solution
                mazePan.setExploredCells(null);
                mazePan.setSolution(null);

                //Stop if no path was found
                if (solution == null || solution.isEmpty()
                        || explored == null || explored.isEmpty()) {
                    System.out.println("No solution");
                    return;
                }

                //Track BFS exploration progress
                final int[] index = {0};

                //Animate BFS exploration
                Timer exploreTimer = new Timer(100, event -> {
                    index[0]++;

                    //Display explored cells
                    mazePan.setExploredCells(
                            explored.subList(0, index[0]));

                    //When BFS exploration finishes
                    if (index[0] >= explored.size()) {
                        ((Timer) event.getSource()).stop();

                        //Store shortest path
                        mazePan.setSolution(solution);

                        //Hide green path initially
                        mazePan.setVisiblePathCells(0);

                        //Track path animation progress
                        final int[] pathIndex = {0};

                        //Animate shortest path
                        Timer pathTimer = new Timer(100, pathEvent -> {
                            pathIndex[0]++;
                            mazePan.setVisiblePathCells(pathIndex[0]);

                            //Stop when the full path is visible
                            if (pathIndex[0] >= solution.size()) {
                                ((Timer) pathEvent.getSource()).stop();
                                animationTimer[0] = null;
                            }
                        });
                        //Store and start path timer
                        animationTimer[0] = pathTimer;
                        pathTimer.start();
                    }
                });
                //Store and start exploration timer
                animationTimer[0] = exploreTimer;
                exploreTimer.start();
            });

            //CLEAR MAZE BUTTON
            clearButton.addActionListener(e -> {

                //Does not interrupt maze generation
                if (generating[0]) {
                    return;
                }

                //Stop any running animation
                if (animationTimer[0] != null) {
                    animationTimer[0].stop();
                    animationTimer[0] = null;
                }

                //Clear BFS exploration
                mazePan.setExploredCells(null);

                //Clear shortest path
                mazePan.setSolution(null);

                System.out.println("Maze cleared");
            });

            //FRAME SETUP
            frame.setLayout(new BorderLayout());
            frame.add(mazePan, BorderLayout.CENTER);
            frame.add(buttonPanel, BorderLayout.SOUTH);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}