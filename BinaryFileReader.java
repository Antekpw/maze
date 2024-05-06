import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.print.StreamPrintService;
import java.io.RandomAccessFile;
import java.io.FileNotFoundException;


public class BinaryFileReader {
    public static void binRead(String fileName) {
        try (DataInputStream inputStream = new DataInputStream(new FileInputStream(fileName))) {
           
            RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "r");
            int fileID = Integer.reverseBytes(randomAccessFile.readInt());
            int escape = randomAccessFile.read();
                int columns = Short.reverseBytes(randomAccessFile.readShort());
                int lines = Short.reverseBytes(randomAccessFile.readShort());
                int entryX = Short.reverseBytes(randomAccessFile.readShort());
                int entryY = Short.reverseBytes(randomAccessFile.readShort());
                int exitX = Short.reverseBytes(randomAccessFile.readShort());
                int exitY = Short.reverseBytes(randomAccessFile.readShort());
                randomAccessFile.skipBytes(12);
                int counter = Integer.reverseBytes(randomAccessFile.readInt());
                randomAccessFile.readInt();
                char separator = (char) randomAccessFile.read();
                char wall = (char) randomAccessFile.read();
                char path = (char) randomAccessFile.read();
          
            int newline=0;
            FileOutputStream decodedMaze = new FileOutputStream("decoded.txt");
            for (int i = 0; i < counter; i++) {

                    separator = (char) randomAccessFile.read();
                    char value = (char) randomAccessFile.read();
                    int count = randomAccessFile.read();
                
                for (int j = 0; j <= count; j++) {

                    if (newline == lines) {
                        decodedMaze.write('\n');
                        newline=0;
                    }
                    if (value == wall) {
                        decodedMaze.write('X');
                        newline++;
                    } else if (value == path) {
                        decodedMaze.write(' ');
                        newline++;
                    }
                }
            }
            decodedMaze.close();
            
            System.out.println("Labirynt został zdekodowany i zapisany do pliku decoded_maze.txt");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
