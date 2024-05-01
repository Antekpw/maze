public class Element {
        int x;
        int y;
        public Element(int x, int y){
            this.x = x;
            this.y = y;
        }
        @Override
        public String toString(){
            return "( " + x + ", " + y + ")";
        }
    }

