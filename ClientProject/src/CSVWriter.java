import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
public class CSVWriter{
    static int count=0;
    public static void writeFile(String file, String message){
        try{
            FileWriter fw = new FileWriter(file,true);
            fw.append(message);
            fw.append("\n");
            fw.close();
        }catch(IOException e){
            System.out.println("This file does not exist");
        }
    }
    public static void main(String[] args){
        System.out.println("Hi");
    }
}