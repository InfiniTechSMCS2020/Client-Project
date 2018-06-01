import java.util.ArrayList;

public class CreateAutofillFields {
    static ArrayList<String> autofillArray;
    public static void createFields() {
        ArrayList<String> fieldArray = NormalFileReader.readFromFileNormal("C:\\Users\\jacob\\Documents\\ScheduleIDs.txt");
        System.out.println(fieldArray);
        autofillArray = new ArrayList<String>();
        for (int i = 0; i < fieldArray.size(); i++) {
            if (!autofillArray.contains(fieldArray.get(i).substring(fieldArray.get(i).indexOf("\"") + 1, fieldArray.get(i).indexOf(",") - 1))) {
                autofillArray.add(fieldArray.get(i).substring(fieldArray.get(i).indexOf("\"") + 1, fieldArray.get(i).indexOf(",") - 1));
                fieldArray.set(i, fieldArray.get(i).substring(fieldArray.get(i).indexOf(",") + 1));
                String last = fieldArray.get(i).substring(fieldArray.get(i).indexOf("\"") + 1, fieldArray.get(i).indexOf(",") - 1);
                fieldArray.set(i, fieldArray.get(i).substring(fieldArray.get(i).indexOf(",") + 1));
                autofillArray.add(fieldArray.get(i).substring(fieldArray.get(i).indexOf("\"") + 1, fieldArray.get(i).indexOf(",") - 1) + " " + last);
                fieldArray.set(i, fieldArray.get(i).substring(fieldArray.get(i).indexOf(",") + 1));
                autofillArray.add(fieldArray.get(i).substring(fieldArray.get(i).indexOf("\"") + 1, fieldArray.get(i).indexOf(",") - 1));
                fieldArray.set(i, fieldArray.get(i).substring(fieldArray.get(i).indexOf(",") + 1));
                fieldArray.set(i, fieldArray.get(i).substring(fieldArray.get(i).indexOf(",") + 1));
                fieldArray.set(i, fieldArray.get(i).substring(fieldArray.get(i).indexOf(",") + 1));
                fieldArray.set(i, fieldArray.get(i).substring(fieldArray.get(i).indexOf(",") + 1));
                fieldArray.set(i, fieldArray.get(i).substring(fieldArray.get(i).indexOf(",") + 1));
                fieldArray.set(i, fieldArray.get(i).substring(fieldArray.get(i).indexOf(",") + 1));
                fieldArray.set(i, fieldArray.get(i).substring(fieldArray.get(i).indexOf(",") + 1));
                fieldArray.set(i, fieldArray.get(i).substring(fieldArray.get(i).indexOf(",") + 1));
                String counsFirst = fieldArray.get(i).substring(fieldArray.get(i).indexOf("\"") + 1, fieldArray.get(i).indexOf(",") - 1);
                fieldArray.set(i, fieldArray.get(i).substring(fieldArray.get(i).indexOf(",") + 1));
                autofillArray.add(counsFirst + " " + fieldArray.get(i).substring(fieldArray.get(i).indexOf("\"") + 1, fieldArray.get(i).length() - 2));
                autofillArray.add("/");
            }
        }
    }
    public static void main(String[] args){
        createFields();
        System.out.println(autofillArray);
    }
}
