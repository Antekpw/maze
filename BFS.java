import javax.swing.*;

public class BFS implements Runnable {
    public char[][] lab;
    private int x0, y0, xk, yk, w, h;

    private GUI g;

    lista<Visitors> visited = new lista<>();
    public BFS(char[][] lab, int x0, int y0, int xk, int yk, int w, int h,GUI g) {
        this.lab = lab;
        this.x0 = x0;
        this.y0 = y0;
        this.xk = xk;
        this.yk = yk;
        this.w = w;
        this.h = h;
        this.g = g;
    }

    
    public lista<Visitors> bfs_search(){
        Queue<Element> q = new Queue<>();
        Fileread.markVisited(lab,x0,y0);
        Element el = new Element(x0,y0);
        q.enqueue(el);
        int [] dx = {0,1,0,-1};
        int [] dy = {-1,0,1,0};
        while(!q.isEmpty()){
            Element r = q.dequeue();
            int rx = r.x;
            int ry = r.y;
            Fileread.markVisited(lab,rx,ry);
            if(rx == xk && ry == yk){
                break;
            }
            for(int i=0; i<4; i++){
                int newx = rx + dx[i];
                int newy = ry + dy[i];
                if((newx<0 || newx >=w) || (newy<0 || newy>=h)){
                    continue;
                }
                if(Fileread.isInvalidPosition(lab,newx,newy)){
                        continue;
                    }
                Element nowy = new Element(newx,newy);
                Visitors v = new Visitors(newx,newy,(i+2)%4);
                visited.add(v);
                q.enqueue(nowy);
            }
            try {
                Thread.sleep(0,1);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return visited;
    }
    @Override
    public void run(){
        Queue<Element> q = new Queue<>();
        lista<Visitors> visited = new lista<>();
        Fileread.markVisited(lab,x0,y0);
        g.changeColor(g.maze,x0,y0,w);
        Element el = new Element(x0,y0);
        q.enqueue(el);
        int [] dx = {0,1,0,-1};
        int [] dy = {-1,0,1,0};
        while(!q.isEmpty()){
            Element r = q.dequeue();
            int rx = r.x;
            int ry = r.y;
            Fileread.markVisited(lab,rx,ry);
            g.changeColor(g.maze,rx,ry,w);
            if(rx == xk && ry == yk){
                break;
            }
            for(int i=0; i<4; i++){
                int newx = rx + dx[i];
                int newy = ry + dy[i];
                if((newx<0 || newx >=w) || (newy<0 || newy>=h)){
                    continue;
                }
                if(Fileread.isInvalidPosition(lab,newx,newy)){
                    continue;
                }
                Element nowy = new Element(newx,newy);
                Visitors v = new Visitors(newx,newy,(i+2)%4);
                visited.addFirst(v);
                q.enqueue(nowy);
            }
            try {
                Thread.sleep(0,1);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        Queue<Element> q2 = new Queue<>();
        Fileread.markPath(lab,xk,yk);
        g.colorPath(g.maze, xk, yk, w);
        Element e = new Element(xk,yk);
        q2.enqueue(e);
       
        while(!q2.isEmpty()){
            Element r = q2.dequeue();
            int rx = r.x;
            int ry = r.y;
            if(rx==x0 && ry == y0){
                Fileread.markPath(lab,rx,ry);
                g.colorPath(g.maze, rx, ry, w);
                break;
            }
            Fileread.markPath(lab,rx,ry);
            g.colorPath(g.maze, rx, ry, w);
            int dir = Visitors.get_direction(visited,rx,ry);
            int newx = rx + dx[dir];
            int newy = ry + dy[dir];
            //System.out.printf("newx = %d, newy = %d\n",newx,newy);
            if(newx == -1){
                break;
            }
            Element nowy = new Element(newx,newy);
            q2.enqueue(nowy);
            try {
                Thread.sleep(0,1);
            } catch (InterruptedException f) {
                // TODO Auto-generated catch block
                f.printStackTrace();
            }
        }
    Fileread.print(lab);
    }
}

