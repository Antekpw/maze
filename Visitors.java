public class Visitors{
    private int x;
    private int y;

    private int dir;
    Visitors(int x, int y, int dir){
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
    @Override
    public String toString(){
        return "(" + x + ","+y +"), dir = " + dir;
    }

    @Override
    public boolean equals (Object o){
        return o instanceof Visitors && (((Visitors) o).x == this.x && ((Visitors) o).y == this.y);
    }
    public static int get_direction(lista<Visitors> v, int x, int y){
        
        for(Visitors item : v){
            if(item.x == x && item.y == y){
                return item.dir;
            }
            
        }
        return -1;
    }

}
