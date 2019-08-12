/*
File: Tron.java
Created: 11/08/2019
Author: Hisbaan Noorani
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Tron implements ActionListener, KeyListener, WindowListener {
    JFrame mainMenuFrame = new JFrame("Main Menu");
    JPanel mainMenuBottomPannel = new JPanel();
    JButton startGameButton = new JButton("Star Game");

    JFrame gameFrame = new JFrame("Snake");
    JPanel gameBottomPannel = new JPanel();
    JButton gameBackButton = new JButton("Back");
    Drawing board = new Drawing();

    String winner = "";

    char direction1 = 'w';
    char direction2 = 'e';

    static int[][][] position = new int[50][50][2];

    Timer movement;

    public static void main(String[] args) {
        new Tron();
    }

    Tron() {
        movement = new Timer(100, e -> {
            board.validate();
            board.repaint();
            move();
            collisionDetection();
        });

        mainMenu();
    }

    public void mainMenu() {
        resetGame();
        mainMenuFrame.setSize(800, 800);
        mainMenuFrame.setLayout(new BorderLayout());
        mainMenuFrame.setResizable(false);

        if (mainMenuFrame.getWindowListeners().length < 1) mainMenuFrame.addWindowListener(this);

        mainMenuFrame.add(mainMenuBottomPannel, BorderLayout.SOUTH);
        mainMenuBottomPannel.setLayout(new GridLayout(1, 3));
        mainMenuBottomPannel.add(startGameButton);

        if (startGameButton.getActionListeners().length < 1) startGameButton.addActionListener(this);

        mainMenuFrame.setVisible(true);
        gameFrame.setVisible(false);
    }

    public void startGame() {
        gameFrame.setSize(800, 850);
        gameFrame.setLayout(new BorderLayout());
        gameFrame.setResizable(false);

        if (gameFrame.getWindowListeners().length < 1) gameFrame.addWindowListener(this);

        gameFrame.add(gameBottomPannel, BorderLayout.SOUTH);

        gameBottomPannel.setLayout(new GridLayout(1, 3));
        gameBottomPannel.add(gameBackButton);
        if (gameBackButton.getActionListeners().length < 1) gameBackButton.addActionListener(this);

        if (gameFrame.getKeyListeners().length < 1) gameFrame.addKeyListener(this);

        gameFrame.setFocusable(true);
        gameFrame.add(board, BorderLayout.CENTER);

        position[0][24][1] = 2;
        position[49][24][0] = 2;


        movement.start();

        mainMenuFrame.setVisible(false);
        gameFrame.setVisible(true);
    }

    public void resetGame() {
        for (int y = 0; y < 50; y++) {
            for (int x = 0; x < 50; x++) {
                position[x][y][0] = 0;
                position[x][y][1] = 0;
            }
        }

        position[0][24][1] = 2;
        position[49][24][0] = 2;

        direction1 = 'w';
        direction2 = 'e';

        movement.stop();
    }

    public void collisionDetection() {
        try {
            for (int y = 0; y < 50; y++) {
                for (int x = 0; x < 50; x++) {
                    if (direction1 == 'n') {
                        if (position[x][y][0] == 2 && position[x][y - 1][0] == 1) {
                            orangeWins();
                        }
                        if (position[x][y][0] == 1 && position[x][y - 1][1] == 1) {
                            orangeWins();
                        }

                        if (position[x][y][1] == 2 && position[x][y - 1][1] == 1) {
                            blueWins();
                        }
                        if (position[x][y][1] == 1 && position[x][y - 1][0] == 1) {
                            blueWins();
                        }
                    }
                    if (direction1 == 's') {
                        if (position[x][y][0] == 2 && position[x][y + 1][0] == 1) {
                            orangeWins();
                        }
                        if (position[x][y][0] == 1 && position[x][y + 1][1] == 1) {
                            orangeWins();
                        }
                        if (position[x][y][1] == 2 && position[x][y + 1][1] == 1) {
                            blueWins();
                        }
                        if (position[x][y][1] == 1 && position[x][y + 1][0] == 1) {
                            blueWins();
                        }
                    }
                    if (direction1 == 'e') {
                        if (position[x][y][0] == 2 && position[x + 1][y][0] == 1) {
                            orangeWins();
                        }
                        if (position[x][y][0] == 1 && position[x + 1][y][1] == 1) {
                            orangeWins();
                        }
                        if (position[x][y][1] == 2 && position[x + 1][y][1] == 1) {
                            blueWins();
                        }
                        if (position[x][y][1] == 1 && position[x + 1][y][0] == 1) {
                            blueWins();
                        }
                    }
                    if (direction1 == 'w') {
                        if (position[x][y][0] == 2 && position[x - 1][y][0] == 1) {
                            orangeWins();
                        }
                        if (position[x][y][0] == 1 && position[x - 1][y][1] == 1) {
                            orangeWins();
                        }
                        if (position[x][y][1] == 2 && position[x - 1][y][1] == 1) {
                            blueWins();
                        }
                        if (position[x][y][1] == 1 && position[x - 1][y][0] == 1) {
                            blueWins();
                        }
                    }
                }
            }
        } catch (Exception e) {

        }
    }

    public void orangeWins() {
        winner = "Orange";
        gameOver();
    }

    public void blueWins() {
        winner = "Blue";
        gameOver();
    }

    public void move() {
        try {
            switch (direction1) {
                case 'n':
                    north(0);
                    break;
                case 's':
                    south(0);
                    break;
                case 'e':
                    east(0);
                    break;
                case 'w':
                    west(0);
                    break;
                default:
                    break;
            }

            switch (direction2) {
                case 'n':
                    north(1);
                    break;
                case 's':
                    south(1);
                    break;
                case 'e':
                    east(1);
                    break;
                case 'w':
                    west(1);
                    break;
                default:
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
//            gameOver();
        }
    }

    public void north(int n) throws ArrayIndexOutOfBoundsException {
        for (int y = 0; y < 50; y++) {
            for (int x = 0; x < 50; x++) {
                if (position[x][y][n] == 2) {
                    position[x][y - 1][n] = 2;
                    position[x][y][n] = 1;

                    System.out.println("north");
                }
            }
        }
    }

    public void south(int n) throws ArrayIndexOutOfBoundsException {
        for (int y = 49; y >= 0; y--) {
            for (int x = 0; x < 50; x++) {
                if (position[x][y][n] == 2) {
                    position[x][y + 1][n] = 2;
                    position[x][y][n] = 1;

                    System.out.println("south");
                }
            }
        }
    }

    public void east(int n) throws ArrayIndexOutOfBoundsException {
        for (int y = 0; y < 50; y++) {
            for (int x = 49; x >= 0; x--) {
                if (position[x][y][n] == 2) {
                    position[x + 1][y][n] = 2;
                    position[x][y][n] = 1;

                    System.out.println("east");
                }
            }
        }
    }

    public void west(int n) throws ArrayIndexOutOfBoundsException {
        for (int y = 0; y < 50; y++) {
            for (int x = 0; x < 50; x++) {
                if (position[x][y][n] == 2) {
                    position[x - 1][y][n] = 2;
                    position[x][y][n] = 1;

                    System.out.println("west");
                }
            }
        }
    }

    public void gameOver() {
        JOptionPane.showMessageDialog(gameFrame, "Game Over\n" + winner + " wins!", "Game Over", JOptionPane.PLAIN_MESSAGE, null);
        resetGame();
        mainMenu();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startGameButton) {
            startGame();
        }
        if (e.getSource() == gameBackButton) {
            resetGame();
            movement.stop();
            mainMenu();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (direction1 == 'n') {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                direction1 = 'e';
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                direction1 = 'w';
            }
        }

        if (direction1 == 's') {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                direction1 = 'e';
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                direction1 = 'w';
            }
        }

        if (direction1 == 'e') {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                direction1 = 'n';
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                direction1 = 's';
            }
        }

        if (direction1 == 'w') {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                direction1 = 'n';
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                direction1 = 's';
            }
        }

        if (direction2 == 'n') {
            if (e.getKeyCode() == KeyEvent.VK_D) {
                direction2 = 'e';
            }
            if (e.getKeyCode() == KeyEvent.VK_A) {
                direction2 = 'w';
            }
        }

        if (direction2 == 's') {
            if (e.getKeyCode() == KeyEvent.VK_D) {
                direction2 = 'e';
            }
            if (e.getKeyCode() == KeyEvent.VK_A) {
                direction2 = 'w';
            }
        }

        if (direction2 == 'e') {
            if (e.getKeyCode() == KeyEvent.VK_W) {
                direction2 = 'n';
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                direction2 = 's';
            }
        }

        if (direction2 == 'w') {
            if (e.getKeyCode() == KeyEvent.VK_W) {
                direction2 = 'n';
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                direction2 = 's';
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}