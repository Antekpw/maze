import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.print.StreamPrintService;
import java.io.RandomAccessFile;
import java.io.FileNotFoundException;


public class BinaryFileReader {
    public static void main(String[] args) {
        try (DataInputStream inputStream = new DataInputStream(new FileInputStream("maze.bin"))) {
            long fileId = inputStream.readInt() & 0xFFFFFFFFL; 
            System.out.println("File_Id: " + fileId);
            
            byte escape = inputStream.readByte(); 
            System.out.println("Escape: " + escape);

            int columns = inputStream.readUnsignedShort(); 
            System.out.println("Columns: " + columns);
            
            int lines = inputStream.readUnsignedShort(); 
            System.out.println("Lines: " + lines);
            
            int entryX = inputStream.readUnsignedShort(); 
            System.out.println("Entry_X: " + entryX);
            
            int entryY = inputStream.readUnsignedShort(); 
            System.out.println("Entry_Y: " + entryY);
            
            int exitX = inputStream.readUnsignedShort(); 
            System.out.println("Exit_X: " + exitX);
            
            int exitY = inputStream.readUnsignedShort(); 
            System.out.println("Exit_Y: " + exitY);
            
            long[] reserved = new long[3];
            for (int i = 0; i < 3; i++) {
                reserved[i] = inputStream.readInt() & 0xFFFFFFFFL; 
            }
            System.out.println("Reserved: " + java.util.Arrays.toString(reserved));
            
            long counter = inputStream.readInt() & 0xFFFFFFFFL; 
            System.out.println("Counter: " + counter);
            
            long solutionOffset = inputStream.readInt() & 0xFFFFFFFFL; 
            System.out.println("Solution_Offset: " + solutionOffset);
            
            byte separator = inputStream.readByte(); 
            System.out.println("Separator: " + separator);
            
            byte wall = inputStream.readByte(); 
            System.out.println("Wall: " + wall);
            
            byte path = inputStream.readByte(); 
            System.out.println("Path: " + path);
            int newline = 0;

            FileOutputStream decodedMaze = new FileOutputStream("decoded_maze.txt");
            for (int i = 0; i < 134500; i++) {
                byte separatorValue = inputStream.readByte();
                byte value = inputStream.readByte();
                int count = inputStream.readUnsignedByte();
                System.out.println("Path: " + count);
                
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
