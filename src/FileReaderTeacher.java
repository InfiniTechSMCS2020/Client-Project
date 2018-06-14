import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;
public class FileReaderTeacher {
    public static ArrayList<String> teacherList = new ArrayList<String>();
    public static ArrayList<String> emailList = new ArrayList<String>();
    public static void readFromFile(String fileName){
        int i =0;
        try{
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String read;
            while((read= br.readLine()) != null ){
                if(i%2 == 0){
                    teacherList.add(read);
                }
                else if(i%2 == 1){
                    emailList.add(read);
                }
                i++;
            }
            teacherList.remove(teacherList.size() -1);
            System.out.println(teacherList);
            System.out.println(emailList);
        }catch(IOException e){
            System.out.println("This file does not exist");
        }
    }
    public static void main(String[] args){
        readFromFile("Teachers.txt");
    }

}
