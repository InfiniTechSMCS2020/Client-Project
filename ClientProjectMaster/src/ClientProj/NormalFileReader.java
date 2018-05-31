package ClientProj;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;

public class NormalFileReader {
        public static ArrayList<String> readFromFileNormal(String fileName){
            ArrayList<String> outputList = new ArrayList<String>();

            try{
                FileReader fr = new FileReader(fileName);
                BufferedReader br = new BufferedReader(fr);
                String string;
                while((string = br.readLine()) != null ){
                    outputList.add(string);
                }

            }catch(IOException e){
                System.out.println("This file does not exist");
            }
            return outputList;
        }
        public static void main(String[] args){
            readFromFileNormal("Documents\\5_18.csv");
        }
}
