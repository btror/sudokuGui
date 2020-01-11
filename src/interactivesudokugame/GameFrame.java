package interactivesudokugame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * GUI Window to display the game of Sudoku
 *
 * @author Brandon Rorie
 * @version 1/7/20
 *
 */
public final class GameFrame extends javax.swing.JFrame implements ActionListener {

    private static String difficulty = "medium";
    private static JButton[][] grid = new JButton[9][9];
    private static JButton[] options = new JButton[9];
    private static Color color = Color.white;
    private static JLabel strikes;
    private static JPanel strikePanel;
    private static int[][] solved = new int[9][9];
    private static ArrayList<JButton> clickedButtons = new ArrayList<>();
    private static String misses = "";

    /**
     * default constructor
     */
    public GameFrame() {
        initComponents();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(585, 775)); //575 775
        this.setMinimumSize(new Dimension(565, 585));
        this.setMaximumSize(new Dimension(1300, 1300));
        this.setTitle("Sudoku    Width: " + this.getWidth() + "    Height: " + this.getHeight());
        this.setResizable(true);
        this.getContentPane().setBackground(Color.black);
        this.setLocationRelativeTo(null);

        JPanel p = new JPanel();

        getContentPane().addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                Component c = (Component) e.getSource();
                int spacex = 0;
                int spacey = 0;
                setTitle("Sudoku    Width: " + c.getWidth() + "    Height: " + c.getHeight());
                for (int i = 0; i < grid.length; i++) {
                    for (int j = 0; j < grid.length; j++) {
                        grid[i][j].setSize(new Dimension(c.getWidth() / 9, (c.getHeight() / 12)));
                        grid[i][j].setLocation(spacex, spacey);
                        spacex += grid[i][j].getWidth();

                        if (j == 2 || j == 5) {
                            spacex += 5;
                        }
                    }
                    spacex = 0;
                    spacey += grid[i][i].getHeight();
                    if (i == 2 || i == 5) {
                        spacey += 5;
                    }
                }

                strikePanel.setBounds(c.getWidth() / 3, spacey + spacey / 21, c.getWidth() / 3 + 10, c.getHeight() / 15);
                spacey += c.getHeight() / 25 * 3;
                spacex = c.getWidth() / 12 - c.getWidth() / 15;
                for (int i = 0; i < options.length; i++) {
                    options[i].setSize(new Dimension(c.getWidth() / 12, c.getHeight() / 15));
                    options[i].setLocation(spacex, spacey + spacey / 50);
                    spacex += options[i].getWidth() + options[i].getWidth() / 3;
                }

            }
        });

        createGrid();
        mediumPuzzle();

    }
    
    public static String getDifficulty() {
        return difficulty;
    }

    /**
     * Sets the color
     *
     * @param c the color of the grid
     */
    public static void selectedGridColor(Color c) {
        color = c;
    }

    /**
     * Getter: gets the current grid
     *
     * @return JButton[][] the sudoku grid
     */
    public static JButton[][] getGrid() {
        return grid;
    }

    /**
     * Getter: gets a generated easy puzzle
     *
     * @return JButton[][] an easy puzzle
     */
    public static JButton[][] getEasyPuzzle() {
        return easyPuzzle();
    }

    /**
     * Getter: gets a generated medium puzzle
     *
     * @return JButton[][] a medium puzzle
     */
    public static JButton[][] getMediumPuzzle() {
        return mediumPuzzle();
    }

    /**
     * Getter: gets a generated hard puzzle
     *
     * @return JButton[][] a hard puzzle
     */
    public static JButton[][] getHardPuzzle() {
        return hardPuzzle();
    }

    /**
     * Getter: gets a generated very hard puzzle
     *
     * @return JButton[][] a very hard puzzle
     */
    public static JButton[][] getVeryHardPuzzle() {
        return veryHardPuzzle();
    }

    /**
     * Setter: sets the difficulty of the game
     *
     * @param difficult a string representing the difficulty the game will be
     */
    public static void setDifficulty(String difficult) {
        difficulty = difficult;
    }

    /**
     * Method that generates an easy puzzle
     *
     * @return JButton[][] board an easy board
     */
    public static JButton[][] easyPuzzle() {
        JButton[][] board = generateBoard();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                int random = (int) (Math.random() * 15) + 1; //1-15
                if (random == 1 || random == 2 || random == 3 || random == 4 || random == 5) {
                    board[i][j].setLabel("");
                }
            }
        }
        return board;
    }

    /**
     * Method that generates a medium puzzle
     *
     * @return JButton[][] board a medium puzzle
     */
    public static JButton[][] mediumPuzzle() {
        JButton[][] board = generateBoard();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                int random = (int) (Math.random() * 12) + 1; //1-12
                if (random == 1 || random == 2 || random == 3 || random == 4 || random == 5) {
                    board[i][j].setLabel("");
                }
            }
        }
        return board;
    }

    /**
     * Method that generates a very hard puzzle
     *
     * @return JButton[][] board a very hard puzzle
     */
    public static JButton[][] veryHardPuzzle() {
        JButton[][] board = generateBoard();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                int random = (int) (Math.random() * 8) + 1; //1-8
                if (random == 1 || random == 2 || random == 3 || random == 4 || random == 5) {
                    board[i][j].setLabel("");
                }
            }
        }
        return board;
    }

    /**
     * Method that generates a hard puzzle
     *
     * @return JButton[][] board a hard puzzle
     */
    public static JButton[][] hardPuzzle() {
        JButton[][] board = generateBoard();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                int random = (int) (Math.random() * 10) + 1; //1-10
                if (random == 1 || random == 2 || random == 3 || random == 4 || random == 5) {
                    board[i][j].setLabel("");
                }
            }
        }
        return board;
    }

    /**
     * Method that highlights the squares that are horizontal and vertical to a
     * JButton in a JButton 2D array
     *
     * @param list a grid of buttons
     * @param button a button in the grid
     * @return JButton[][] list a list with the horizontal and vertical points
     * to the given JButton highlighted
     */
    public static JButton[][] horAndVertSquares(JButton[][] list, JButton button) {
        int x = -1;
        int y = -1;
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list.length; j++) {
                if (list[i][j] == button) {
                    x = j;
                    y = i;
                }
            }
        }

        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list.length; j++) {
                if (j == x || i == y) {
                    list[i][j].setBackground(Color.cyan);
                } else {
                    list[i][j].setBackground(color);
                }

            }
        }
        return list;

    }

    /**
     * Method that highlights the JButtons in a JButton grid that have the same
     * number as the JButton clicked
     *
     * @param list a grid of buttons
     * @param button a button in the grid
     * @return JButton[][] list a list with all of the JButtons with the same
     * number as the given JButton highlighted
     */
    public static JButton[][] numLocater(JButton[][] list, JButton button) {
        int x = -1;
        int y = -1;
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list.length; j++) {
                if (list[i][j] == button) {
                    x = j;
                    y = i;
                }
            }
        }

        String string = list[y][x].getText();

        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list.length; j++) {
                if (list[i][j].getText().equals(string)) {
                    list[i][j].setBackground(Color.cyan);
                } else {
                    list[i][j].setBackground(color);
                }
            }
        }

        return list;

    }

    /**
     * Method that creates a grid of buttons in the GameFrame, as well as the
     * option buttons and strike-board at the bottom
     */
    public void createGrid() {

//        int spacex = 10;
//        int spacey = 10;
        int spacex = 10;
        int spacey = 10;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                JButton button = new JButton();
                button.setBackground(Color.white); //give the user the option to change the color later on
                grid[i][j] = button;//575 775
                grid[i][j].setBounds(spacex, spacey, 60, 60);
                grid[i][j].setFont(new Font("Arial", Font.PLAIN, 30));
                this.add(grid[i][j]);

                //add action listner
                grid[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton button = (JButton) e.getSource();

                        if (button.getText().equals("")) {
                            clickedButtons.add(button);
                        }
                        if (clickedButtons.size() > 1 && button.getText().equals("")) {
                            button.setBackground(Color.CYAN);

                            clickedButtons.get(clickedButtons.size() - 2).setBackground(color);

                            horAndVertSquares(grid, button);

                        } else if (clickedButtons.size() <= 1 && button.getText().equals("")) {
                            button.setBackground(Color.CYAN);

                            horAndVertSquares(grid, button);
                        }

                        if (!button.getText().equals("")) {
                            numLocater(grid, button);
                        }

                    }

                });

                spacex += 60;
                if (spacex == 190 || spacex == 375) {
                    spacex += 5;
                }
            }
            spacex = 10;
            spacey += 60;
            if (spacey == 190 || spacey == 375) {
                spacey += 5;
            }
        }

        //strike column
        JPanel panel = new JPanel();
        panel.setBounds(207, spacey + 20, 155, 35);
        this.add(panel);
        panel.setBackground(Color.white);
        strikePanel = panel;
        JLabel label = new JLabel();
        panel.add(label);
        strikes = label;

        //bottom buttons
        spacex = 17;
        spacey += 80;
        int count = 1;
        for (int i = 0; i < 9; i++) {
            JButton button = new JButton();

            button.setBackground(Color.white);
            options[i] = button;
            options[i].setBounds(spacex, spacey, 55, 55);
            String num = Integer.toString(i + 1);
            options[i].setText(num);
            options[i].setFont(new Font("Arial", Font.BOLD, 20));
            spacex += 60;
            this.add(options[i]);

            //add action listener
            options[i].addActionListener(new ActionListener() { //solved is an array of buttons that is solved
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton button = (JButton) e.getSource();

                    
                    //set pink button to the number selected
                    if (clickedButtons.size() > 0) {
                        clickedButtons.get(clickedButtons.size() - 1).setText(button.getText());
                    }

                    int x = -1;
                    int y = -1;
                    //see if the number is in the correct spot
                    for (int i = 0; i < grid.length; i++) {
                        for (int j = 0; j < grid.length; j++) {
                            String string = grid[i][j].getText();
                            int value = -1;
                            if (string.equals("")) {
                                value = 0;
                            } else {
                                value = Integer.parseInt(string);
                            }
                            if (value != solved[i][j] && value != 0) {//it is in wrong spot
                                clickedButtons.get(clickedButtons.size() - 1).setBackground(Color.PINK);
                                clickedButtons.get(clickedButtons.size() - 1).setText("");
                                misses += " X ";
                                strikes.setText(misses);
                                if (misses.equals(" X  X  X ")) {
                                    grid = losingGrid(grid);
                                    strikes.setText("<HTML><CENTER>YOU LOSE</CENTER></HTML>");
                                }
                            }
                        }
                    }

                    //see if the user won
                    boolean win = checkWin(grid);
                    if (win == true) {
                        grid = winningGrid(grid);
                        strikes.setText("<HTML><CENTER>YOU WIN</CENTER></HTML>");
                    }
                }
            });
        }

    }

    /**
     * Method that returns whether or not the player has filled out the entire
     * board correctly
     *
     * @param list a grid of buttons
     * @return boolean true if the player filled out the entire grid correctly
     * and false if otherwise
     */
    public static boolean checkWin(JButton[][] list) {

        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list.length; j++) {
                if (list[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Method that creates the grid that is displayed if the player has won the
     * game (a smiley face)
     *
     * @param list a grid of buttons
     * @return JButton[][] list a grid of buttons with a smiley face and victory
     * message
     */
    public static JButton[][] winningGrid(JButton[][] list) {
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list.length; j++) {
                list[i][j].setText("");
                if ((i == 2 && j == 2)
                        || (i == 2 && j == 6)
                        || (i == 5 && j == 2)
                        || (i == 6 && j == 3)
                        || (i == 6 && j == 4)
                        || (i == 6 && j == 5)
                        || (i == 5 && j == 6)) {
                    list[i][j].setBackground(Color.black);
                } else {
                    list[i][j].setBackground(Color.GREEN);
                }
            }
        }
        return list;
    }

    /**
     * Method that creates the grid that is displayed if the player has guessed
     * incorrectly 3 times and has 3 strikes (if the player has lost)(a sad
     * face)
     *
     * @param list a grid of buttons
     * @return JButton[][] list a grid of buttons with a sad face and defeat
     * message
     */
    public static JButton[][] losingGrid(JButton[][] list) {
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list.length; j++) {
                list[i][j].setText("");
                if ((i == 2 && j == 2)
                        || (i == 2 && j == 6)
                        || (i == 6 && j == 2)
                        || (i == 5 && j == 3)
                        || (i == 5 && j == 4)
                        || (i == 5 && j == 5)
                        || (i == 6 && j == 6)
                        || (i == 6 && j == 6)) {
                    list[i][j].setBackground(Color.black);
                } else {
                    list[i][j].setBackground(Color.RED);
                }
            }
        }
        return list;
    }

    /**
     * Method that generates a game board of integers
     *
     * @return JButton[][] grid a copy of the integer board
     */
    public static JButton[][] generateBoard() {
        misses = "";
        strikes.setText("");
        //fill a board with 0's
        int[][] board = new int[9][9];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = 0;
            }
        }

        int j = 0;
        //insert a random number (1-9) in random spots then use the solve method
        for (int i = 0; i < board.length; i++) {
            int random = (int) (Math.random() * 9) + 1;
            board[i][j] = random;

            if (i > 0 && i % 3 != 0) {
                //check the previous spot for similarities and correct it if there is
                while (board[i - 1][j - 1] == board[i][j]) {
                    board[i][j] = (int) (Math.random() * 9) + 1;
                }
            }

            if (i == 2) {
                while (board[i][j] == board[0][0] || board[i][j] == board[i - 1][j - 1]) {
                    board[i][j] = (int) (Math.random() * 9) + 1;
                }

            }
            if (i == 5) {
                while (board[i][j] == board[3][3] || board[i][j] == board[i - 1][j - 1]) {
                    board[i][j] = (int) (Math.random() * 9) + 1;
                }
            }
            if (i == 8) {
                while (board[i][j] == board[6][6] || board[i][j] == board[i - 1][j - 1]) {
                    board[i][j] = (int) (Math.random() * 9) + 1;
                }
            }
            j++;
        }

        solve(board);

        for (int i = 0; i < grid.length; i++) {
            for (int k = 0; k < grid.length; k++) {
                String string = Integer.toString(board[i][k]);
                grid[i][k].setLabel(string);
                grid[i][k].setBackground(color); //try this
                String s = grid[i][k].getText();
                int value = -1;
                if (s.equals("")) {
                    value = 0;
                } else {
                    value = Integer.parseInt(s);
                }
                solved[i][k] = value;
            }
        }
        return grid;
    }

    /**
     * Method that checks if the board is solved
     *
     * @param board int[][] board the integer version of the grid
     * @return boolean true if the board is solved, false if it isn't
     */
    public static boolean solve(int[][] board) {
        int[] find = findEmpty(board);
        int col;
        int row;
        if (find == null) {
            return true;
        } else {
            col = find[1];
            row = find[0];
        }

        int temp[] = {row, col};

        for (int i = 1; i < 10; i++) {
            if (isValid(board, i, temp)) {
                board[row][col] = i;
                if (solve(board)) {
                    return true;
                }
                board[row][col] = 0;
            }

        }
        return false;

    }

    /**
     * Method that checks if the current board is valid
     *
     * @param board int[][] board the board of the game
     * @param num int num a number to see if it is in the right spot
     * @param pos int[] pos the coordinate position of the num in the board
     * @return boolean true if the number is in the right spot, false if it
     * isn't
     */
    public static boolean isValid(int[][] board, int num, int pos[]) {
        //check row
        for (int i = 0; i < board.length; i++) {
            if (board[pos[0]][i] == num && pos[1] != i) {
                return false;
            }
        }
        //check columns
        for (int i = 0; i < board.length; i++) {
            if (board[i][pos[1]] == num && pos[0] != i) {
                return false;
            }
        }

        //check box
        int boxX = pos[1] / 3;
        int boxY = pos[0] / 3;

        for (int i = boxY * 3; i < boxY * 3 + 3; i++) {
            for (int j = boxX * 3; j < boxX * 3 + 3; j++) {
                int[] temp = {i, j};
                if (board[i][j] == num && temp != pos) {
                    return false;
                }
            }
        }

        return true;

    }

    /**
     * Method that gets the position of empty places on the board
     *
     * @param board int[][] board the game board
     * @return int[] coordinate the coordinate of the spot that is empty and
     * null if it does not exist
     */
    public static int[] findEmpty(int[][] board) {
        int[] coordinate = new int[2]; //index 0 is y, index 1 is x
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 0) {
                    coordinate[0] = i; //j is y coordinate
                    coordinate[1] = j; //i is x coordinate
                    return coordinate;
                }

            }
        }
        return null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gameMenuBar = new javax.swing.JMenuBar();
        gameOptions = new javax.swing.JMenu();
        generateNewPuzzleItem = new javax.swing.JMenuItem();
        changeDifficultyItem = new javax.swing.JMenuItem();
        settingsMenu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        gameOptions.setText("Game");

        generateNewPuzzleItem.setText("Generate New Puzzle");
        generateNewPuzzleItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateNewPuzzleItemActionPerformed(evt);
            }
        });
        gameOptions.add(generateNewPuzzleItem);

        changeDifficultyItem.setText("Change Difficulty");
        changeDifficultyItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeDifficultyItemActionPerformed(evt);
            }
        });
        gameOptions.add(changeDifficultyItem);

        gameMenuBar.add(gameOptions);

        settingsMenu.setText("Settings");

        jMenuItem1.setText("Personalize");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        settingsMenu.add(jMenuItem1);

        gameMenuBar.add(settingsMenu);

        setJMenuBar(gameMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 518, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 482, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //change the theme of the game
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        ThemePickerWindow themePicker = new ThemePickerWindow(this);
        themePicker.setVisible(true);


    }//GEN-LAST:event_jMenuItem1ActionPerformed

    //change the difficulty of the puzzle
    private void changeDifficultyItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeDifficultyItemActionPerformed
        DifficultyPickerWindow difficultyPicker = new DifficultyPickerWindow(this);
        difficultyPicker.setVisible(true);
    }//GEN-LAST:event_changeDifficultyItemActionPerformed


    private void generateNewPuzzleItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateNewPuzzleItemActionPerformed

        switch (difficulty) {
            case "easy":
                easyPuzzle();
                break;
            case "medium":
                mediumPuzzle();
                break;
            case "hard":
                hardPuzzle();
                break;
            case "very hard":
                veryHardPuzzle();
                break;
        }
    }//GEN-LAST:event_generateNewPuzzleItemActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem changeDifficultyItem;
    private javax.swing.JMenuBar gameMenuBar;
    private javax.swing.JMenu gameOptions;
    private javax.swing.JMenuItem generateNewPuzzleItem;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenu settingsMenu;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
