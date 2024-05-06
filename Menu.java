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
        Fileread f = new Fileread();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/100x100.txt"));
            f.read(reader);
            System.out.println("x0: " + f.getStartX() + ", y0: " + f.getStartY() + ", xk: " + f.getEndX() + ", yk: " +f.getEndY() + ", width: " + f.getWidth()+ ", height: " + f.getHeight());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        char [][] lab = Fileread.wczytajLabirynt(fileName);
        GUI g = new GUI(lab);
        g.createMaze();
        g.mainFrame.add(g.maze);
        g.colorStart(g.maze,f.getStartX(),f.getStartY(),f.getWidth());
        g.colorEnd(g.maze,f.getEndX(),f.getEndY(),f.getWidth());
        g.mainFrame.setVisible(true);

        BFS bfs = new BFS(lab, f.getStartX(), f.getStartY(), f.getEndX(), f.getEndY(), f.getWidth(), f.getHeight(), g);
        Thread t = new Thread(bfs);
        t.start();
    }
}
