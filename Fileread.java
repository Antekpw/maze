import java.io.*;
public class Fileread {
    private int width;
    private int height;
    private int startX;
    private int startY;
    private int endX;
    private int endY;

    public Fileread() {
        this.width = 0;
        this.height = 0;
        this.startX = -1;
        this.startY = -1;
        this.endX = -1;
        this.endY = -1;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }
    public void changestart(int x, int y){
        startX = x;
        startY = y;
    }
    public void changeend(int x, int y){
        endX = x;
        endY = y;
    }
    public void read(BufferedReader in) throws IOException {
        char c;
        int x = 0;
        int y = 0;
        int maxWidth = 0;
        int currentChar;
        while ((currentChar = in.read()) != -1) {
            c = (char) currentChar;
            switch (c) {
                case 'P':
                    startX = x;
                    startY = y;
                    break;
                case 'K':
                    endX = x;
                    endY = y;
                    break;
                case '\n':
                    if (x > maxWidth) {
                        maxWidth = x;
                    }
                    y++;
                    x = 0;
                    break;
                default:
                    x++;
                    break;
            }
        }
        width = maxWidth;
        height = y + 1; // Wysokość to liczba wierszy + 1
    }

    public static void markVisited(char[][] lab , int x, int y){
        lab[y][x] = '+';
    }
    public static void markPath(char[][] lab , int x, int y){
        lab[y][x] = 'O';
    }
    public static boolean isInvalidPosition(char[][] lab , int x, int y) {
        return (lab[y][x] == '+' || lab[y][x] == 'X');
    }
    public static char[][] wczytajLabirynt(String sciezkaDoPliku) {
        try (BufferedReader brCount = new BufferedReader(new FileReader(sciezkaDoPliku))) {
            int liczbaWierszy = 0;
            int liczbaKolumn = 0;
            // Pobierz liczbę wierszy i kolumn labiryntu
            while (brCount.readLine() != null) {
                liczbaWierszy++;
            }
            brCount.close();

            // Teraz użyjemy osobnego BufferedReader do wczytania labiryntu
            BufferedReader brRead = new BufferedReader(new FileReader(sciezkaDoPliku));
            String linia;
            if ((linia = brRead.readLine()) != null) {
                liczbaKolumn = linia.length();
            }
            // Utwórz dwuwymiarową tablicę na podstawie rozmiaru labiryntu
            char[][] labirynt = new char[liczbaWierszy][liczbaKolumn];
            // Wczytaj labirynt z pliku
            int wiersz = 0;
            brRead.close();
            brRead = new BufferedReader(new FileReader(sciezkaDoPliku));
            while ((linia = brRead.readLine()) != null) {
                for (int kolumna = 0; kolumna < liczbaKolumn; kolumna++) {
                    labirynt[wiersz][kolumna] = linia.charAt(kolumna);
                }
                wiersz++;
            }
            return labirynt;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void print(char[][] lab){
        for(int i=0; i <lab.length; i++){
            for(int j = 0; j< lab[i].length; j++){
                System.out.print(lab[i][j]);
            }
            System.out.println();
        }
    }
    
}








