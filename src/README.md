# Maze Generator and Solver
## Project Overview

This is a Java desktop application that generates and solves mazes using Depth-First Search (DFS) and Breadth-First Search (BFS).
The application uses Java Swing to display the maze and animate the generation and solving processes.

## Features
- Generate random mazes using DFS with backtracking.
- Solve mazes using BFS to find the shortest path.
- Animate maze generation and pathfinding.
- Select between 5×5, 10×10 and 20×20 maze sizes.
- Display explored cells and the final solution using different colours.
- Clear the solution without removing the generated maze.

## Algorithms and Data Structures
**Depth-First Search (DFS):** Used to generate the maze. A stack stores cells while the algorithm explores unvisited neighbours and backtracks when it reaches a dead end.
**Breadth-First Search (BFS):** Used to solve the maze. A queue explores accessible neighbouring cells, and parent references are used to reconstruct the shortest path.

The project uses the stack and queue implementations from our Data Structures and Algorithms labs.

## Project Structure
- `Cell.java` — Stores each cell's coordinates, walls and visited state.
- `Maze.java` — Creates and manages the 2D grid.
- `MazeGenerator.java` — Generates mazes using DFS.
- `MazeSolver.java` — Solves mazes using BFS.
- `MazePanel.java` — Draws the maze and animations.
- `main.java` / `Main.java` — Creates the GUI and handles button actions.
- Stack and Queue classes — Provide the data structures used by the algorithms.

## Running the Application
1. Open the project in IntelliJ IDEA.
2. Make sure a Java JDK is configured.
3. Open `main.java` (or `Main.java` if renamed).
4. Run its `main()` method.
5. Use **Create Maze** to generate a maze and **Solve Maze** to find the shortest path.
6. Use **Clear Maze** to remove the highlighted solution.

## Design Choices
The maze is represented using a two-dimensional array of `Cell` objects. Each cell stores its row and column, with `(0,0)` representing the top-left corner.
Each cell initially has four walls. DFS removes walls between neighbouring cells to create passages.
The GUI uses a custom `JPanel` to draw cells, walls and the solution. Swing timers allow the algorithms to be displayed step by step.

## Testing
The application has been tested using 5×5, 10×10 and 20×20 mazes. Tests included generating new mazes, finding valid paths, clearing solutions and creating another maze after solving.