public class Sciezka {
    private char[][] lab;
    private int x0, y0, xk, yk, w, h;

    private GUI g;
    private lista<Visitors> visited;
    public static void recreatePath(char [][] lab,lista<Visitors> visited, int xk, int yk, int x0, int y0){
        Queue<Element> q = new Queue<>();
        Fileread.markPath(lab,xk,yk);
        Element e = new Element(xk,yk);
        q.enqueue(e);
        int [] dx = {0,1,0,-1};
        int [] dy = {-1,0,1,0};
        while(!q.isEmpty()){
            Element r = q.dequeue();
            int rx = r.x;
            int ry = r.y;
            if(rx==x0 && ry == y0){
                Fileread.markPath(lab,rx,ry);
                break;
            }
            Fileread.markPath(lab,rx,ry);
            int dir = Visitors.get_direction(visited,rx,ry);
            int newx = rx + dx[dir];
            int newy = ry + dy[dir];
            //System.out.printf("newx = %d, newy = %d\n",newx,newy);
            if(newx == -1){
                break;
            }
            Element nowy = new Element(newx,newy);
            q.enqueue(nowy);
        }

    }
}
