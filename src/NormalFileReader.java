import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;

public class NormalFileReader {
        public static ArrayList<String> readFromFileNormal(String fileName){
            ArrayList<String> outputList = new ArrayList<String>();

            try{
                File file = new File(fileName);
                if(file.isDirectory()){
                    file.listFiles();
                }
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
        public static ArrayList<String> readFilesInFolder(File folder) {
            ArrayList<String> fileList = new ArrayList<String>();
            for (File fileInFolder : folder.listFiles()) {
                if (fileInFolder.isDirectory()) {
                    readFilesInFolder(fileInFolder);
                } else {
                    fileList.add(fileInFolder.getName());

                }

            }
            return fileList;
        }

        public static void main(String[] args){
            readFromFileNormal("C:\\Users\\jacob\\Documents\\5_18.csv");
        }
}
