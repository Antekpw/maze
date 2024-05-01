import java.io.*;


public class Fileread {
    public static void read(BufferedReader in, int[] x0, int[] y0, int[] xk, int[] yk, int[] width, int[] height) throws IOException {
        char c;
        int x = 0;
        int y = 0;
        int maxWidth = 0;
        int currentChar;
        while ((currentChar = in.read()) != -1) {
            c = (char) currentChar;
            switch (c) {
                case 'P':
                    x0[0] = x;
                    y0[0] = y;
                    break;
                case 'K':
                    xk[0] = x;
                    yk[0] = y;
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
        width[0] = maxWidth ; // Szerokość to największa liczba znaków w wierszu + 1
        height[0] = y +1 ; // Wysokość to liczba wierszy + 1
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








