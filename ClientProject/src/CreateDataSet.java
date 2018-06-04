import java.util.ArrayList;

public class CreateDataSet {
    private static ArrayList<Integer>  idList;
    private static ArrayList<String> teacherList;
    private static ArrayList<String> nameList;
    private static ArrayList<String> counselorList;
    private static ArrayList<Integer> gradeList;
    private static ArrayList<String> reasonList;

    public CreateDataSet(){
        idList = new ArrayList<Integer>();
        teacherList = new ArrayList<String>();
        nameList = new ArrayList<String>();
        counselorList = new ArrayList<String>();
        gradeList = new ArrayList<Integer>();
        reasonList = new ArrayList<String>();

    }

    public void fillLists(String fileName) {
        ArrayList<String> fileOutput = NormalFileReader.readFromFileNormal(fileName);
        for(int i=0; i<fileOutput.size(); i++){
            String s = fileOutput.get(i);
            idList.add(Integer.valueOf(s.substring(0,s.indexOf(","))));
            s=s.substring(s.indexOf(",")+1);
            teacherList.add(s.substring(0,s.indexOf(",")));
            s=s.substring(s.indexOf(",")+1);
            nameList.add(s.substring(0,s.indexOf(",")));
            s=s.substring(s.indexOf(",")+1);
            counselorList.add(s.substring(0,s.indexOf(",")));
            s=s.substring(s.indexOf(",")+1);
            gradeList.add(Integer.valueOf(s.substring(0,s.indexOf(","))));
            s=s.substring(s.indexOf(",")+1);
            reasonList.add(s.substring(0,s.length()-1));
        }

    }
    public void getAllLists(){
        System.out.println(idList);
        System.out.println(teacherList);
        System.out.println(nameList);
        System.out.println(counselorList);
        System.out.println(gradeList);
        System.out.println(reasonList);

    }
    public ArrayList<String> getReasonList(){
        return reasonList;
    }
    public ArrayList<Integer> getGradeList(){
        return gradeList;
    }
    public ArrayList<String> getCounselorList(){
        return counselorList;
    }

}
