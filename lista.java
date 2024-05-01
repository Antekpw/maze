import java.util.ArrayList;


public class lista <T> extends ArrayList<T> {
    //private ArrayList<T> l = new ArrayList<>();
   
 
    public void print(){
        for(T item : this){
            System.out.print(item + "->");
        }
        System.out.print("NULL\n");
    }
    
}

