import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    private char[][] lab;
    public JFrame mainFrame;
    public JPanel maze;
    public JPanel buttonspanel;
    public JButton start;
    public JButton setstartandend;
    public JButton read;

    public GUI(char[][] lab) {
        this.lab = lab;
        this.mainFrame = initWindow();
    }

    private void addButton(JPanel panel) {
        start = new JButton("START BFS");
        setstartandend= new JButton("Ustaw poczatek i koniec");
        read = new JButton("wczytaj plik");
        panel.add(start);
        panel.add(setstartandend);
        panel.add(read);
    }


    private JFrame initWindow() {
        JFrame mainFrame = new JFrame("Maze Solver");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();

        int width = screenWidth / 2;
        int height = (int) ((double) 80 / 100 * screenHeight);
        mainFrame.setSize(width, height);
        mainFrame.setLocationRelativeTo(null);
        buttonspanel = new JPanel(new FlowLayout());
        addButton(buttonspanel);
        mainFrame.add(buttonspanel, BorderLayout.NORTH);
        return mainFrame;
    }

    public void createMaze() {
        maze = new JPanel();
        maze.setLayout(new GridLayout(lab.length, lab[0].length));
        for (int i = 0; i < lab.length; i++) {
            for (int j = 0; j < lab[0].length; j++) {
                JLabel label = new JLabel();
                label.setOpaque(true);
                label.setHorizontalAlignment(JLabel.CENTER);
                if (lab[i][j] == 'X') {
                    label.setBackground(Color.BLACK);
                } else if (lab[i][j] == ' ') {
                    label.setBackground(Color.WHITE);
                } else if (lab[i][j] == 'P') {
                    label.setBackground(Color.WHITE);
                } else if (lab[i][j] == 'K') {
                    label.setBackground(Color.WHITE);
                }
                maze.add(label);
            }
        }

    }

    private static JLabel makeLabel(char c) {
        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setPreferredSize(new Dimension(10, 10));
        switch (c) {
            case 'X':
                label.setBackground(Color.BLACK);
                break;
            case ' ':
                label.setBackground(Color.WHITE);
                break;
            case 'P':
                label.setBackground(Color.WHITE);
                break;
            case 'K':
                label.setBackground(Color.WHITE);
                break;
        }
        label.setOpaque(true);
        return label;
    }

    public void changeColor(JPanel maze, int x, int y, int w) {
        int pos = y * (w - 1) + x;
        Component component = maze.getComponent(pos);
        if (component instanceof JLabel label) {
            label.setBackground(Color.BLUE);
            label.setVisible(true);
            mainFrame.revalidate();

        }
    }

    public void colorPath(JPanel maze, int x, int y, int w) {
        int pos = y * (w - 1) + x;
        Component component = maze.getComponent(pos);
        if (component instanceof JLabel label) {
            label.setBackground(Color.ORANGE);
            label.setVisible(true);
            mainFrame.revalidate();

        }
    }

    public void colorStart(JPanel maze, int x, int y, int w) {
        int pos = y * (w - 1) + x;
        Component component = maze.getComponent(pos);
        if (component instanceof JLabel label) {
            label.setBackground(Color.GREEN);
            label.setVisible(true);
            mainFrame.revalidate();
        }

    }

    public void colorEnd(JPanel maze, int x, int y, int w) {
        int pos = y * (w - 1) + x;
        Component component = maze.getComponent(pos);
        //System.out.println(component);
        if (component instanceof JLabel label) {
            //System.err.printf("dla pos=%d zmieniam kolor\n",pos);
            label.setBackground(Color.RED);
            label.setVisible(true);
            //label.setOpaque(true); //
            mainFrame.revalidate();

        }
    }
}