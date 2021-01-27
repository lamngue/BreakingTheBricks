import com.game.BrickPanel;
import com.game.StartPanel;

import javax.swing.*;
import java.awt.*;

public class Game {

    public static JLabel getLabel(String text, int alignment) {
        JLabel l = new JLabel(text, alignment);
        return l;
    }

    public static void main(String[] args) {
        JFrame brickFrame = new JFrame("BreakingTheBricks");
        JFrame startFrame = new JFrame("Welcome");
        BrickPanel brickPanel = new BrickPanel(startFrame, brickFrame);
        StartPanel startPanel = new StartPanel(startFrame, brickFrame);

        startPanel.setLayout(new BorderLayout());
        startPanel.add(getLabel(
                "Press enter to start", SwingConstants.CENTER), BorderLayout.CENTER);

        startFrame.setSize(500, 600);
        startFrame.getContentPane().add(startPanel);
        startFrame.setVisible(true);
        startFrame.setResizable(false);


        brickFrame.getContentPane().add(brickPanel);
        brickFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        brickFrame.setVisible(false);
        brickFrame.setSize(500, 600);
        brickFrame.setResizable(false);
    }
}
