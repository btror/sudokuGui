package interactivesudokugame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 * GUI Window to display the game of Sudoku
 *
 * @author Brandon Rorie
 * @version 1/7/20
 *
 */
public final class GameFrame extends javax.swing.JFrame implements ActionListener {

    private JFrame frame;

    private static String difficulty = "medium";
    private static JButton[][] grid = new JButton[9][9];
    private static ArrayList<JButton> buttons = new ArrayList<>();
    //private static JButton[] options = new JButton[9];
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

        frame = new JFrame();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(400, 500)); //575 775
        this.setMinimumSize(new Dimension(100, 150));
        this.setTitle("Sudoku    Width: " + this.getWidth() + "    Height: " + this.getHeight());
        this.setResizable(true);
        this.getContentPane().setBackground(Color.black);
        this.setLocationRelativeTo(null);
        
        createGrid();
        mediumPuzzle();
        
        this.setVisible(true);

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

        //JPanel p2 = null;
        JPanel p1 = new JPanel(new GridLayout(1, 3));

        JPanel p2 = new JPanel(new GridLayout(12, 1));
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                JButton button = new JButton();
                grid[i][j] = button;
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

                p2.add(button);

            }

        }
        Border b = (BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        int i1;
        for (int i = 0; i < 9; i++) {
            if (i == 2 || i == 5) {
                i1 = 5;
            } else {
                i1 = 1;
            }
            for (int j = 0; j < 9; j++) {
                if (j == 2 || j == 5) {
                    b = BorderFactory.createMatteBorder(1, 1, i1, 5, Color.BLACK);
                    grid[i][j].setBorder(b);
                } else {
                    b = (BorderFactory.createMatteBorder(1, 1, i1, 1, Color.BLACK));
                    grid[i][j].setBorder(b);
                }

            }
        }

        int x = 0;
        for (int i = 0; i < 27; i++) {
            if (i < 18) {
                JPanel p = new JPanel();
                if (i == 12 || i == 13 || i == 14) {
                    p.setBackground(Color.WHITE);
                    switch (i) {
                        case 12:
                            p.setBorder(BorderFactory.createMatteBorder(2, 2, 1, 0, Color.BLACK));
                            break;
                        case 13:
                            strikes = new JLabel();
                            p.add(strikes);
                            p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 0, Color.BLACK));
                            break;
                        case 14:
                            p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 2, Color.BLACK));
                            break;
                        default:
                            break;
                    }

                } else {
                    p.setBackground(Color.RED);
                }
                p2.add(p);
            } else {
                JButton button = new JButton();
                button.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
                int spot = x + 1;
                button.setText("" + spot);
                button.setBackground(Color.white);
                p2.add(button);
                options[x] = button;
                
                options[x].addActionListener(new ActionListener() {
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
                    }

                });
                x++;

            }
        }

        p1.add(p2);

//        GridLayout bottomGrid = new GridLayout(4, 3);
//        int index = 0;
//
//        //bottom left
//        JPanel bottom1 = new JPanel(bottomGrid);
//
//        for (int i = 0; i < 12; i++) {
//            if (i < 9) {
//                bottom1.add(new JLabel());
//            } else {
//                JButton button = new JButton();
//                bottom1.add(button);
//                options.add(button);
//                index++;
//            }
//
//        }
//
//        p1.add(bottom1);
//
//        //bottom middle
//        JPanel bottom2 = new JPanel(bottomGrid);
//
//        for (int i = 0; i < 12; i++) {
//            if (i < 9) {
//                JPanel strike = new JPanel();
//                if (i == 3 || i == 4 || i == 5) {
//                    strike.setBackground(Color.red);
//                    switch (i) {
//                        case 3: //top, left, bottom
//                            strike.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 0, Color.BLACK));
//                            break;
//                        case 4: //top, bottom
//                            strike.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.BLACK));
//                            break;
//                        case 5: //top, right, bottom
//                            strike.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 3, Color.BLACK));
//                            break;
//                    }
//
//                }
//                bottom2.add(strike);
//            } else {
//                JButton button = new JButton();
//                bottom2.add(button);
//                options.add(button);
//                index++;
//            }
//
//        }
//
//        p1.add(bottom2);
//
//        //bottom right
//        JPanel bottom3 = new JPanel(bottomGrid);
//
//        for (int i = 0; i < 12; i++) {
//            if (i < 9) {
//                bottom3.add(new JLabel());
//            } else {
//                JButton button = new JButton();
//                bottom3.add(button);
//                options.add(button);
//                index++;
//            }
//
//        }
//
//        for (int i = 0; i < 9; i++) {
//            int hold = i + 1;
//            String num = Integer.toString(hold);
//            options.get(i).setText(num);
//        }
//
//        p1.add(bottom3);
        this.add(p1);
//
//        for (int i = 0; i < grid.length; i++) {
//            for (int j = 0; j < grid.length; j++) {
//                // action listener for the options buttons
//            }
//        }

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
        //strikes.setText("");
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
