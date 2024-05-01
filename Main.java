import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;
import java.awt.*;
public class Main  {
    public static void main(String[] args) {
        int[] x0 = new int[1];
        int[] y0 = new int[1];
        int[] xk = new int[1];
        int[] yk = new int[1];
        int[] width = new int[1];
        int[] height = new int[1];
        try {
            BufferedReader reader = new BufferedReader(new FileReader("100x100.txt"));
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
        char [][] lab = Fileread.wczytajLabirynt("100x100.txt");
        GUI g = new GUI(lab);
        g.createMaze();
        g.mainFrame.add(g.maze);
        g.mainFrame.setVisible(true);
        g.changeColor(g.maze,0,1,w);

        BFS bfs = new BFS(lab, x, y, x2, y2, w, h,g);
        Thread t = new Thread(bfs);
        t.start();
       // lista <Visitors> v = bfs.bfs_search();
        //v.print();
        //Sciezka.recreatePath(lab, bfs.visited, x2,y2,x,y);



        /*Sciezka.recreatePath(lab,visited,x2,y2,x,y);*/
        
      }
}