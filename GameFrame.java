package interactivesudokugame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * GUI Window to display the game of Sudoku
 *
 * @author Brandon Rorie
 * @version 1/7/20
 *
 */
public final class GameFrame extends javax.swing.JFrame implements ActionListener {

    private static boolean gameOver = false;

    private static JButton[][] grid = new JButton[9][9];
    private static JButton[] options = new JButton[9];
    private static int[][] solved = new int[9][9];

    private static ArrayList<JLabel> strikeArea = new ArrayList<>();
    private static ArrayList<JPanel> backgroundPanels = new ArrayList<>();
    private static ArrayList<JButton> clickedButtons = new ArrayList<>();

    private static Color gridColor = Color.WHITE;
    private static Color backgroundColor = Color.WHITE;
    private static Color highlightColor = Color.LIGHT_GRAY;
    private static Color borderColor = Color.BLACK;

    private static int strikeAmount = 0;
    private static int amountOfAttempts = 7;

    /**
     * default constructor
     */
    public GameFrame() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(400, 500)); //575 775
        this.setMinimumSize(new Dimension(100, 150));
        this.setTitle("Sudoku    Width: " + this.getWidth() + "    Height: " + this.getHeight());
        this.setResizable(true);
        this.getContentPane().setBackground(Color.black);
        this.setLocationRelativeTo(null);

        setMenu();

        createGrid();
        easyPuzzle();

        this.setVisible(true);

    }

    public void setMenu() {
        JMenuBar menu = new JMenuBar();
        JMenu gameMenu = new JMenu("New Game");

        JMenuItem[] lvlList = new JMenuItem[4];
        String lvl = "";
        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0:
                    lvl = "Easy";
                    break;
                case 1:
                    lvl = "Medium";
                    break;
                case 2:
                    lvl = "Hard";
                    break;
                case 3:
                    lvl = "Very Hard";
                    break;
            }
            JMenuItem level = new JMenuItem(lvl);
            lvlList[i] = level;
            gameMenu.add(level);
        }

        lvlList[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameOver = false;
                strikeAmount = 0;
                for (int i = 0; i < amountOfAttempts; i++) {
                    strikeArea.get(i).setText("");
                }
                easyPuzzle();

            }
        });
        lvlList[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameOver = false;
                strikeAmount = 0;
                for (int i = 0; i < amountOfAttempts; i++) {
                    strikeArea.get(i).setText("");
                }
                mediumPuzzle();

            }
        });
        lvlList[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameOver = false;
                strikeAmount = 0;
                for (int i = 0; i < amountOfAttempts; i++) {
                    strikeArea.get(i).setText("");
                }
                hardPuzzle();

            }
        });
        lvlList[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameOver = false;
                strikeAmount = 0;
                for (int i = 0; i < amountOfAttempts; i++) {
                    strikeArea.get(i).setText("");
                }
                veryHardPuzzle();

            }
        });

        JMenu appearanceMenu = new JMenu("Appearance");

        JMenu gridMenu = new JMenu("Grid Color");
        JMenuItem white = new JMenuItem("White");
        JMenuItem black = new JMenuItem("Black");
        JMenuItem green = new JMenuItem("Green");
        JMenuItem blue = new JMenuItem("Blue");
        JMenuItem pink = new JMenuItem("Pink");

        white.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gridColor = Color.WHITE;
                borderColor = Color.BLACK;
                setGridColor(gridColor);
            }
        });
        black.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gridColor = Color.BLACK;

                borderColor = Color.WHITE;
                setGridColor(gridColor);

            }
        });
        green.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gridColor = Color.GREEN;
                borderColor = Color.BLACK;
                setGridColor(gridColor);
            }
        });
        blue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gridColor = Color.BLUE;
                borderColor = Color.BLACK;
                setGridColor(gridColor);
            }
        });
        pink.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gridColor = Color.PINK;
                borderColor = Color.BLACK;
                setGridColor(gridColor);
            }
        });

        gridMenu.add(white);
        gridMenu.add(black);
        gridMenu.add(green);
        gridMenu.add(blue);
        gridMenu.add(pink);

        JMenu backgroundMenu = new JMenu("Background Color");
        JMenuItem white1 = new JMenuItem("White");
        JMenuItem black1 = new JMenuItem("Black");
        JMenuItem green1 = new JMenuItem("Green");
        JMenuItem blue1 = new JMenuItem("Blue");
        JMenuItem pink1 = new JMenuItem("Pink");

        white1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setBackgroundColor(Color.WHITE);
            }
        });
        black1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setBackgroundColor(Color.BLACK);
            }
        });
        green1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setBackgroundColor(Color.GREEN);
            }
        });
        blue1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setBackgroundColor(Color.BLUE);
            }
        });
        pink1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setBackgroundColor(Color.PINK);
            }
        });

        backgroundMenu.add(white1);
        backgroundMenu.add(black1);
        backgroundMenu.add(green1);
        backgroundMenu.add(blue1);
        backgroundMenu.add(pink1);

        JMenu fontMenu = new JMenu("Font Size");
        JMenuItem small = new JMenuItem("Small");
        JMenuItem medium = new JMenuItem("Medium");
        JMenuItem large = new JMenuItem("Large");

        small.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Font f = new Font("Arial", Font.PLAIN, 14);

                setGameFont(f);
            }
        });

        medium.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Font f = new Font("Arial", Font.PLAIN, 26);

                setGameFont(f);
            }
        });

        large.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Font f = new Font("Arial", Font.PLAIN, 45);

                setGameFont(f);
            }
        });

        fontMenu.add(small);
        fontMenu.add(medium);
        fontMenu.add(large);

        JMenu highlightMenu = new JMenu("Highlight Color");
        JMenuItem yellow2 = new JMenuItem("Yellow");
        JMenuItem orange2 = new JMenuItem("Orange");
        JMenuItem cyan2 = new JMenuItem("Cyan");
        JMenuItem magenta2 = new JMenuItem("Magenta");
        JMenuItem pink2 = new JMenuItem("Pink");
        JMenuItem lightGray2 = new JMenuItem("Gray");

        yellow2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                highlightColor = Color.YELLOW;
                setHighlightColor(highlightColor);
            }
        });

        orange2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                highlightColor = Color.ORANGE;
                setHighlightColor(highlightColor);
            }
        });

        cyan2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                highlightColor = Color.CYAN;
                setHighlightColor(highlightColor);
            }
        });

        magenta2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                highlightColor = Color.magenta;
                setHighlightColor(highlightColor);
            }
        });

        pink2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                highlightColor = Color.PINK;
                setHighlightColor(highlightColor);
            }
        });

        lightGray2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                highlightColor = Color.LIGHT_GRAY;
                setHighlightColor(highlightColor);
            }
        });

        highlightMenu.add(lightGray2);
        highlightMenu.add(yellow2);
        highlightMenu.add(orange2);
        highlightMenu.add(cyan2);
        highlightMenu.add(magenta2);
        highlightMenu.add(pink2);

        appearanceMenu.add(highlightMenu);
        appearanceMenu.add(gridMenu);
        appearanceMenu.add(backgroundMenu);
        appearanceMenu.add(fontMenu);

        JMenu settingsMenu = new JMenu("Settings");
        JMenu changeStrikes = new JMenu("Change Amount of Attempts");
        JMenuItem three = new JMenuItem("3 Attempts");
        JMenuItem five = new JMenuItem("5 Attempts");
        JMenuItem seven = new JMenuItem("7 Attempts");
        JMenuItem nine = new JMenuItem("9 Attempts");
        JMenuItem unlimited = new JMenuItem("Unlimited");

        three.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeAmountOfAttempts(3);
            }
        });

        five.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeAmountOfAttempts(5);
            }
        });

        seven.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeAmountOfAttempts(7);
            }
        });

        nine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeAmountOfAttempts(9);
            }
        });

        unlimited.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeAmountOfAttempts(-1);
            }
        });

        changeStrikes.add(three);
        changeStrikes.add(five);
        changeStrikes.add(seven);
        changeStrikes.add(nine);
        changeStrikes.add(unlimited);
        settingsMenu.add(changeStrikes);

        menu.add(gameMenu);
        menu.add(appearanceMenu);
        menu.add(settingsMenu);

        this.setJMenuBar(menu);
    }

    public static void changeAmountOfAttempts(int amount) {

        amountOfAttempts = amount;
    }

    public static void setHighlightColor(Color color) {
        if (!clickedButtons.isEmpty()) {
            horAndVertSquares(grid, clickedButtons.get(clickedButtons.size() - 1));
        }
    }

    public static void setGameFont(Font font) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                grid[i][j].setFont(font);
            }
            options[i].setFont(font);
        }

        for (int i = 0; i < amountOfAttempts; i++) {
            strikeArea.get(i).setFont(font);
        }

    }

    public static void setGridColor(Color color) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j].setBackground(color);
                if (color == Color.BLACK || color == Color.BLUE) {
                    grid[i][j].setForeground(Color.WHITE);
                } else {
                    grid[i][j].setForeground(Color.BLACK);
                }
            }
        }

        Border b = (BorderFactory.createMatteBorder(1, 1, 1, 1, borderColor));
        int i1;
        for (int i = 0; i < 9; i++) {
            if (i == 2 || i == 5) {
                i1 = 5;
            } else {
                i1 = 1;
            }
            for (int j = 0; j < 9; j++) {
                if (j == 2 || j == 5) {
                    b = BorderFactory.createMatteBorder(1, 1, i1, 5, borderColor);
                    grid[i][j].setBorder(b);
                } else {
                    b = (BorderFactory.createMatteBorder(1, 1, i1, 1, borderColor));
                    grid[i][j].setBorder(b);
                }

            }
        }

    }

    public static void setBackgroundColor(Color color) {
        for (int i = 0; i < backgroundPanels.size(); i++) {
            backgroundPanels.get(i).setBackground(color);
        }
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
                    list[i][j].setBackground(highlightColor);
                } else {
                    list[i][j].setBackground(gridColor);
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
                    list[i][j].setBackground(highlightColor);
                } else {
                    list[i][j].setBackground(gridColor);
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
                        if (gameOver == false) {
                            JButton button = (JButton) e.getSource();

                            if (button.getText().equals("")) {
                                clickedButtons.add(button);
                            }
                            if (clickedButtons.size() > 1 && button.getText().equals("")) {
                                button.setBackground(highlightColor);

                                clickedButtons.get(clickedButtons.size() - 2).setBackground(gridColor);

                                horAndVertSquares(grid, button);

                            } else if (clickedButtons.size() <= 1 && button.getText().equals("")) {
                                button.setBackground(highlightColor);

                                horAndVertSquares(grid, button);
                            }

                            if (!button.getText().equals("")) {
                                numLocater(grid, button);
                            }
                        }
                    }

                });

                p2.add(button);

            }

        }

        Border b = (BorderFactory.createMatteBorder(1, 1, 1, 1, borderColor));
        int i1;
        for (int i = 0; i < 9; i++) {
            if (i == 2 || i == 5) {
                i1 = 5;
            } else {
                i1 = 1;
            }
            for (int j = 0; j < 9; j++) {
                if (j == 2 || j == 5) {
                    b = BorderFactory.createMatteBorder(1, 1, i1, 5, borderColor);
                    grid[i][j].setBorder(b);
                } else {
                    b = (BorderFactory.createMatteBorder(1, 1, i1, 1, borderColor));
                    grid[i][j].setBorder(b);
                }

            }
        }
        int index = 0;
        int x = 0;
        for (int i = 0; i < 27; i++) {
            if (i < 18) {
                JPanel p = new JPanel();
                //amountOfAttempts
                //i == 12 || i == 13 || i == 14
                switch (amountOfAttempts) {
                    case 3:
                        if (i == 12 || i == 13 || i == 14) {
                            p.setBackground(backgroundColor);
                            switch (i) {
                                case 12:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 2, 1, 0, borderColor));
                                    JLabel label = new JLabel();
                                    p.add(label);
                                    strikeArea.add(label);
                                    break;
                                case 13:
                                    //strikes = new JLabel();
                                    JLabel la = new JLabel();
                                    strikeArea.add(la);
                                    p.add(la);
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 0, borderColor));
                                    break;
                                case 14:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 2, borderColor));
                                    JLabel l = new JLabel();
                                    strikeArea.add(l);
                                    p.add(l);
                                    break;
                                default:
                                    break;
                            }
                            
                            index++;
                        } else {
                            p.setBackground(backgroundColor);
                            backgroundPanels.add(p);
                        }   break;
                //here
                    case 5:
                        if (i == 11 || i == 12 || i == 13 || i == 14 || i == 15) {
                            p.setBackground(backgroundColor);
                            switch (i) {
                                case 11:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 2, 1, 0, borderColor));
                                    JLabel label1 = new JLabel();
                                    p.add(label1);
                                    strikeArea.add(label1);
                                    break;
                                case 12:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 0, borderColor));
                                    JLabel label = new JLabel();
                                    p.add(label);
                                    strikeArea.add(label);
                                    break;
                                case 13:
                                    //strikes = new JLabel();
                                    JLabel la = new JLabel();
                                    strikeArea.add(la);
                                    p.add(la);
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 0, borderColor));
                                    break;
                                case 14:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 0, borderColor));
                                    JLabel l = new JLabel();
                                    strikeArea.add(l);
                                    p.add(l);
                                    break;
                                case 15:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 2, borderColor));
                                    JLabel l1 = new JLabel();
                                    strikeArea.add(l1);
                                    p.add(l1);
                                    break;
                                default:
                                    break;
                            }
                            
                            index++;
                        } else {
                            p.setBackground(backgroundColor);
                            backgroundPanels.add(p);
                        }   break;
                    case 7:
                        if (i == 10 || i == 11 || i == 12 || i == 13 || i == 14 || i == 15 || i == 16) {
                            p.setBackground(backgroundColor);
                            switch (i) {
                                case 10:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 2, 1, 0, borderColor));
                                    JLabel label2 = new JLabel();
                                    p.add(label2);
                                    strikeArea.add(label2);
                                    break;
                                case 11:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 0, borderColor));
                                    JLabel label1 = new JLabel();
                                    p.add(label1);
                                    strikeArea.add(label1);
                                    break;
                                case 12:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 0, borderColor));
                                    JLabel label = new JLabel();
                                    p.add(label);
                                    strikeArea.add(label);
                                    break;
                                case 13:
                                    //strikes = new JLabel();
                                    JLabel la = new JLabel();
                                    strikeArea.add(la);
                                    p.add(la);
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 0, borderColor));
                                    break;
                                case 14:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 0, borderColor));
                                    JLabel l = new JLabel();
                                    strikeArea.add(l);
                                    p.add(l);
                                    break;
                                case 15:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 0, borderColor));
                                    JLabel l1 = new JLabel();
                                    strikeArea.add(l1);
                                    p.add(l1);
                                    break;
                                case 16:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 2, borderColor));
                                    JLabel label4 = new JLabel();
                                    p.add(label4);
                                    strikeArea.add(label4);
                                    break;
                                default:
                                    break;
                            }
                            
                            index++;
                        } else {
                            p.setBackground(backgroundColor);
                            backgroundPanels.add(p);
                        }   break;
                    case 9:
                        if (i == 9 || i == 10 || i == 11 || i == 12 || i == 13 || i == 14 || i == 15 || i == 16 || i == 17) {
                            p.setBackground(backgroundColor);
                            switch (i) {
                                case 9:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 2, 1, 0, borderColor));
                                    JLabel label3 = new JLabel();
                                    p.add(label3);
                                    strikeArea.add(label3);
                                    break;
                                case 10:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 0, borderColor));
                                    JLabel label2 = new JLabel();
                                    p.add(label2);
                                    strikeArea.add(label2);
                                    break;
                                case 11:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 0, borderColor));
                                    JLabel label1 = new JLabel();
                                    p.add(label1);
                                    strikeArea.add(label1);
                                    break;
                                case 12:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 0, borderColor));
                                    JLabel label = new JLabel();
                                    p.add(label);
                                    strikeArea.add(label);
                                    break;
                                case 13:
                                    //strikes = new JLabel();
                                    JLabel la = new JLabel();
                                    strikeArea.add(la);
                                    p.add(la);
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 0, borderColor));
                                    break;
                                case 14:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 0, borderColor));
                                    JLabel l = new JLabel();
                                    strikeArea.add(l);
                                    p.add(l);
                                    break;
                                case 15:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 0, borderColor));
                                    JLabel l1 = new JLabel();
                                    strikeArea.add(l1);
                                    p.add(l1);
                                    break;
                                case 16:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 0, borderColor));
                                    JLabel label4 = new JLabel();
                                    p.add(label4);
                                    strikeArea.add(label4);
                                    break;
                                case 17:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 2, borderColor));
                                    JLabel label5 = new JLabel();
                                    p.add(label5);
                                    strikeArea.add(label5);
                                    break;
                                default:
                                    break;
                            }
                            
                            index++;
                        } else {
                            p.setBackground(backgroundColor);
                            backgroundPanels.add(p);
                        }   break;
                    default:
                        if (i == 9 || i == 10 || i == 11 || i == 12 || i == 13 || i == 14 || i == 15 || i == 16 || i == 17) {
                            p.setBackground(backgroundColor);
                            switch (i) {
                                case 9:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 2, 1, 0, borderColor));
                                    JLabel label3 = new JLabel();
                                    p.add(label3);
                                    label3.setText("U");
                                    strikeArea.add(label3);
                                    break;
                                case 10:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 0, borderColor));
                                    JLabel label2 = new JLabel();
                                    p.add(label2);
                                    label2.setText("N");
                                    strikeArea.add(label2);
                                    break;
                                case 11:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 0, borderColor));
                                    JLabel label1 = new JLabel();
                                    p.add(label1);
                                    label1.setText("L");
                                    strikeArea.add(label1);
                                    break;
                                case 12:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 0, borderColor));
                                    JLabel label = new JLabel();
                                    p.add(label);
                                    label.setText("I");
                                    strikeArea.add(label);
                                    break;
                                case 13:
                                    //strikes = new JLabel();
                                    JLabel la = new JLabel();
                                    strikeArea.add(la);
                                    p.add(la);
                                    la.setText("M");
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 0, borderColor));
                                    break;
                                case 14:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 0, borderColor));
                                    JLabel l = new JLabel();
                                    strikeArea.add(l);
                                    l.setText("I");
                                    p.add(l);
                                    break;
                                case 15:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 0, borderColor));
                                    JLabel l1 = new JLabel();
                                    strikeArea.add(l1);
                                    l1.setText("T");
                                    p.add(l1);
                                    break;
                                case 16:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 0, borderColor));
                                    JLabel label4 = new JLabel();
                                    p.add(label4);
                                    label4.setText("E");
                                    strikeArea.add(label4);
                                    break;
                                case 17:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 2, borderColor));
                                    JLabel label5 = new JLabel();
                                    p.add(label5);
                                    label5.setText("D");
                                    strikeArea.add(label5);
                                    break;
                                default:
                                    break;
                            }
                            
                            index++;
                        } else {
                            p.setBackground(backgroundColor);
                            backgroundPanels.add(p);
                        }   break;
                }

                p2.add(p);
            } else {
                JButton button = new JButton();
                button.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, borderColor));
                int spot = x + 1;
                button.setText("" + spot);
                button.setBackground(gridColor);
                p2.add(button);
                options[x] = button;

                options[x].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver == false) {
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
                                        if (amountOfAttempts != -1) {
                                            strikeArea.get(strikeAmount).setText("X");
                                            strikeAmount++;
                                        }
                                        //strikes.setText(misses);
                                        if (strikeAmount == amountOfAttempts) {
                                            grid = losingGrid(grid);
                                            gameOver = true;
                                            switch (amountOfAttempts) {
                                                case 3:
                                                    strikeArea.get(0).setText("R");
                                                    strikeArea.get(1).setText("I");
                                                    strikeArea.get(2).setText("P");
                                                    break;
                                                case 5:
                                                    strikeArea.get(0).setText("L");
                                                    strikeArea.get(1).setText("O");
                                                    strikeArea.get(2).setText("S");
                                                    strikeArea.get(3).setText("E");
                                                    strikeArea.get(4).setText("R");
                                                    break;
                                                case 7:
                                                    strikeArea.get(0).setText("A");
                                                    strikeArea.get(1).setText("W");
                                                    strikeArea.get(2).setText(" ");
                                                    strikeArea.get(3).setText("M");
                                                    strikeArea.get(4).setText("A");
                                                    strikeArea.get(5).setText("N");
                                                    strikeArea.get(6).setText("!");
                                                    break;
                                                case 9:
                                                case -1:
                                                    //you lose!
                                                    strikeArea.get(0).setText("Y");
                                                    strikeArea.get(1).setText("O");
                                                    strikeArea.get(2).setText("U");
                                                    strikeArea.get(3).setText(" ");
                                                    strikeArea.get(4).setText("L");
                                                    strikeArea.get(5).setText("O");
                                                    strikeArea.get(6).setText("S");
                                                    strikeArea.get(7).setText("E");
                                                    strikeArea.get(8).setText("!");
                                                    break;
                                                default:
                                                    break;
                                            }
                                            strikeAmount = 0;

                                            System.out.println(strikeArea.size());
                                        }
                                    }
                                }
                            }

                            //check if the user won
                            boolean win = checkWin(grid);
                            if (win == true) {
                                grid = winningGrid(grid);
                                strikeArea.get(0).setText("WI");
                                strikeArea.get(1).setText("NN");
                                strikeArea.get(2).setText("ER");
                                strikeAmount = 0;
                                gameOver = true;
                            }

                        }
                    }

                });
                x++;

            }
        }

        p1.add(p2);

        this.add(p1);

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
                grid[i][k].setBackground(gridColor); //try this
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 518, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 503, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

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

    // Variables declaration - do not modify                     
    // End of variables declaration                   
    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
