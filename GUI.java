import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
public class GUI {
    private char[][] lab;
    public JFrame mainFrame;
    public JPanel maze;
    public JPanel buttonspanel;
    public JButton start;
    public JButton setstart;
    public JButton setend;
    public JButton delete;
    public Fileread f;
    private boolean b = false;
    private boolean t = false;
    private boolean c = false;
    public GUI(char[][] lab,Fileread f) {
        this.lab = lab;
        this.mainFrame = initWindow();
        this.f = f;
    }
    private void addButton(JPanel panel) {
        start = new JButton("START BFS");
        setstart= new JButton("Ustaw poczatek");
        setstart.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(b==false){
                    b = true;
                }
            }  
        });
        setend = new JButton("Ustaw koniec");
        setend.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if(t==false){
                    t = true;
                }
            }
                        
        });
        delete = new JButton("Usun lub dodaj sciane");
        delete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
               if(c==false){
                c = true;
               }
            }
            
        });
        panel.add(start);
        panel.add(setstart);
        panel.add(setend);
        panel.add(delete);
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
                final int x = j;
                final int y = i;
                label.addMouseListener(new MouseListener() {

                    @Override
                    public void mouseClicked(MouseEvent e) {
                     
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        if(b){
                        lab[y][x] = 'P';
                        setwhite(maze,f.getStartX(),f.getStartY(),lab[0].length+1);
                        lab[f.getStartY()][f.getStartX()] = ' ';
                        updatelab();
                        f.changestart(x, y);
                        System.out.printf("x0=%d,y0=%d\n",f.getStartX(),f.getStartY());
                        colorStart(maze, x, y, lab[0].length+1);
                        mainFrame.revalidate();
                        updatelab();
                        b = false;   
                        }
                        if(t){
                            lab[y][x] = 'K';
                            setwhite(maze,f.getEndX(),f.getEndY(),lab[0].length+1);
                            lab[f.getEndY()][f.getEndX()] = ' ';
                            updatelab();
                            f.changeend(x, y);
                            colorEnd(maze, x, y, lab[0].length+1);
                            mainFrame.revalidate();
                            updatelab();
                            t = false; 
                        }
                        if(c){
                            char c = lab[y][x];
                            int tempx = x;
                            int tempy = y;
                            switch(c){
                                case ' ':
                                    c ='X';
                                    setblack(maze, tempx, tempy, lab[0].length+1);
                                    updatelab();
                                    mainFrame.revalidate();
                                    break;
                                case 'X':
                                    c = ' ';
                                    setwhite(maze, tempx, tempy, lab[0].length+1);
                                    updatelab();
                                    mainFrame.revalidate();
                                    break;
                            }
                        }
                    }
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        // TODO Auto-generated method stub
                        
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        // TODO Auto-generated method stub
                        
                    }
                    
                });
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
    public char[][] updatelab(){
        return lab;
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
        if (component instanceof JLabel label) {
            label.setBackground(Color.RED);
            label.setVisible(true);
            mainFrame.revalidate();
        }
    }
    public void setwhite(JPanel maze, int x, int y, int w){
        int pos = y * (w - 1) + x;
        Component component = maze.getComponent(pos);
        if (component instanceof JLabel label) {
            label.setBackground(Color.white);
            label.setVisible(true);
            maze.revalidate();
            mainFrame.revalidate();
        }
    }
    public void setblack(JPanel maze, int x, int y, int w){
        int pos = y * (w - 1) + x;
        Component component = maze.getComponent(pos);
        if (component instanceof JLabel label) {
            label.setBackground(Color.BLACK);
            label.setVisible(true);
            maze.revalidate();
            mainFrame.revalidate();
        }
    }
}









