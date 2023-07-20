import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class NQueenSolver {
    private int n;
    private List<int[]> solutions;

    public NQueenSolver(int n) {
        this.n = n;
        this.solutions = new ArrayList<>();
    }

    public void solve() {
        int[] queens = new int[n];
        findSolutions(queens, 0);
    }

    private void findSolutions(int[] queens, int row) {
        if (row == n) {
            solutions.add(queens.clone());
        } else {
            for (int col = 0; col < n; col++) {
                if (isValidPlacement(queens, row, col)) {
                    queens[row] = col;
                    findSolutions(queens, row + 1);
                }
            }
        }
    }

    private boolean isValidPlacement(int[] queens, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (queens[i] == col || Math.abs(queens[i] - col) == Math.abs(i - row)) {
                return false;
            }
        }
        return true;
    }

    public List<int[]> getSolutions() {
        return solutions;
    }
}

class NQueenGUI extends JFrame {
    private int n;
    private List<int[]> solutions;
    private int solutionIndex;

    public NQueenGUI(int n) {
        this.n = n;
        this.solutions = new ArrayList<>();
        this.solutionIndex = 0;
        setTitle("N-Queen Solver");
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        solveNQueens();
        showNextSolution();
    }

    private void solveNQueens() {
        NQueenSolver solver = new NQueenSolver(n);
        solver.solve();
        solutions = solver.getSolutions();
    }

    private void showNextSolution() {
        if (solutionIndex < solutions.size()) {
            int[] queens = solutions.get(solutionIndex);
            BoardPanel boardPanel = new BoardPanel(queens);
            setContentPane(boardPanel);
            solutionIndex++;
            SwingUtilities.invokeLater(() -> {
                validate();
                repaint();
            });
        } else {
            JOptionPane.showMessageDialog(this, "No more solutions found!");
        }
    }

    private class BoardPanel extends JPanel {
        private int[] queens;

        public BoardPanel(int[] queens) {
            this.queens = queens;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int squareSize = Math.min(getWidth(), getHeight()) / n;

            // Draw the board
            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    if ((row + col) % 2 == 0) {
                        g.setColor(Color.WHITE);
                    } else {
                        g.setColor(Color.BLACK);
                    }
                    g.fillRect(col * squareSize, row * squareSize, squareSize, squareSize);
                }
            }

            // Draw the queens
            g.setColor(Color.RED);
            for (int row = 0; row < n; row++) {
                int col = queens[row];
                g.fillOval(col * squareSize + squareSize / 4, row * squareSize + squareSize / 4,
                        squareSize / 2, squareSize / 2);
            }
        }
    }
}

public class NQueenSolverGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            int n = 8; // Change this to solve for different N
            new NQueenGUI(n).setVisible(true);
        });
    }
}
