import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;
import java.awt.*;
public class Main  {
    public static void main(String[] args) {
        Fileread f = new Fileread();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/100x100.txt"));
            f.read(reader);
            System.out.println("x0: " + f.getStartX() + ", y0: " + f.getStartY() + ", xk: " + f.getEndX() + ", yk: " +f.getEndY() + ", width: " + f.getWidth()+ ", height: " + f.getHeight());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        char [][] lab = f.wczytajLabirynt("src/100x100.txt");
        GUI g = new GUI(lab);
        g.createMaze();
        g.mainFrame.add(g.maze);
        g.colorStart(g.maze,f.getStartX(),f.getStartY(),f.getWidth());
        g.colorEnd(g.maze,f.getEndX(),f.getEndY(),f.getWidth());
        g.mainFrame.setVisible(true);
        g.start.addActionListener(e -> {
            BFS bfs = new BFS(lab, f.getStartX(), f.getStartY(), f.getEndX(), f.getEndY(), f.getWidth(), f.getHeight(), g);
            Thread t = new Thread(bfs);
            t.start();
        });


    }
}