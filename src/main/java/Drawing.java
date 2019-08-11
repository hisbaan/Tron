/*
File: Drawing.java
Created: 11/08/2019
Author: Hisbaan Noorani
*/

import javax.swing.*;
import java.awt.*;

public class Drawing extends JPanel {
    Drawing() {
        repaint();
    }

    @Override
    public void paint(Graphics g1) {
        Graphics2D g = (Graphics2D) g1;

        g.setColor(Color.black);
        g.fillRect(0, 0, 800, 800);

        for (int y = 0; y < 50; y++) {
            for (int x = 0; x < 50; x++) {
                if (Tron.position[x][y][0] > 0) {
                    g.setColor(Color.CYAN);
                    g.fillRect(16 * x + 1, 16 * y + 1, 14, 14);
                }

                if (Tron.position[x][y][1] > 0) {
                    g.setColor(Color.ORANGE);
                    g.fillRect(16 * x + 1, 16 * y + 1, 14, 14);
                }
            }
        }
    }
}
