import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Menu {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        JFrame mainFrame = new JFrame("Maze Solver");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(500, 100);
        mainFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(173, 216, 230));

        JButton button1 = new JButton("Znajdź najkrótszą ścieżkę");
        button1.setPreferredSize(new Dimension(200, 50));
        JButton button2 = new JButton("Wczytaj plik binarny");
        button2.setPreferredSize(new Dimension(200, 50));

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = JOptionPane.showInputDialog(mainFrame, "Podaj nazwę pliku:", "Wczytaj plik", JOptionPane.PLAIN_MESSAGE);
                if (fileName != null && !fileName.isEmpty()) {
                    findShortestPath(fileName);
                }
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = JOptionPane.showInputDialog(mainFrame, "Podaj nazwę pliku:", "Wczytaj plik", JOptionPane.PLAIN_MESSAGE);
                if (fileName != null && !fileName.isEmpty()) {
                }
            }
        });

        panel.add(button1);
        panel.add(button2);

        mainFrame.add(panel);

        mainFrame.setVisible(true);
    }

    private static void findShortestPath(String fileName) {
        int[] x0 = new int[1];
        int[] y0 = new int[1];
        int[] xk = new int[1];
        int[] yk = new int[1];
        int[] width = new int[1];
        int[] height = new int[1];
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            Fileread.read(reader, x0, y0, xk, yk, width, height);
            System.out.println("x0: " + x0[0] + ", y0: " + y0[0] + ", xk: " + xk[0] + ", yk: " + yk[0] + ", width: " + width[0] + ", height: " + height[0]);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int w = width[0];
        int x = 0;
        int y = 1;
        int x2 = xk[0];
        int y2 = yk[0];
        int h = height[0];
        char [][] lab = Fileread.wczytajLabirynt(fileName);
        GUI g = new GUI(lab);
        g.createMaze();
        g.mainFrame.add(g.maze);
        g.mainFrame.setVisible(true);
        g.changeColor(g.maze,0,1,w);

        BFS bfs = new BFS(lab, x, y, x2, y2, w, h,g);
        Thread t = new Thread(bfs);
        t.start();
    }
}
