import java.util.ArrayList;
import java.util.Date;

public class CreateAutofillFields {
    static ArrayList<ArrayList<String>> autofillArray;
    static ArrayList<String> idArray;

    public static void createFields() {
        ArrayList<String> fieldArray = NormalFileReader.readFromFileNormal("C:\\Users\\jacob\\Desktop\\Eclipse Workspace\\ClientProject\\studentsSchedule2018.mer");
        autofillArray = new ArrayList<ArrayList<String>>();
        for (int j = 1; j < fieldArray.size(); j++) {
            ArrayList<String> stringArray = new ArrayList<String>();
            fieldArray.set(j, fieldArray.get(j).substring(fieldArray.get(j).indexOf("\"") + 1));
            stringArray.add(fieldArray.get(j).substring(0, fieldArray.get(j).indexOf("\"")));
            fieldArray.set(j, fieldArray.get(j).substring(fieldArray.get(j).indexOf("\"") + 3));
            stringArray.add(fieldArray.get(j).substring(0, fieldArray.get(j).indexOf("\"")));
            fieldArray.set(j, fieldArray.get(j).substring(fieldArray.get(j).indexOf("\"") + 3));
            stringArray.add(fieldArray.get(j).substring(0, fieldArray.get(j).indexOf("\"")));
            fieldArray.set(j, fieldArray.get(j).substring(fieldArray.get(j).indexOf("\"") + 3));
            stringArray.add(fieldArray.get(j).substring(0, fieldArray.get(j).indexOf("\"")));
            fieldArray.set(j, fieldArray.get(j).substring(fieldArray.get(j).indexOf("\"") + 3));
            stringArray.add(fieldArray.get(j).substring(0, fieldArray.get(j).indexOf("\"")));
            fieldArray.set(j, fieldArray.get(j).substring(fieldArray.get(j).indexOf("\"") + 3));
            stringArray.add(fieldArray.get(j).substring(0, fieldArray.get(j).indexOf("\"")));
            fieldArray.set(j, fieldArray.get(j).substring(fieldArray.get(j).indexOf("\"") + 3));
            stringArray.add(fieldArray.get(j).substring(0, fieldArray.get(j).indexOf("\"")));
            fieldArray.set(j, fieldArray.get(j).substring(fieldArray.get(j).indexOf("\"") + 3));
            stringArray.add(fieldArray.get(j).substring(0, fieldArray.get(j).indexOf("\"")));
            fieldArray.set(j, fieldArray.get(j).substring(fieldArray.get(j).indexOf("\"") + 3));
            stringArray.add(fieldArray.get(j).substring(0, fieldArray.get(j).indexOf("\"")));
            fieldArray.set(j, fieldArray.get(j).substring(fieldArray.get(j).indexOf("\"") + 3));
            stringArray.add(fieldArray.get(j).substring(0, fieldArray.get(j).indexOf("\"")));
            fieldArray.set(j, fieldArray.get(j).substring(fieldArray.get(j).indexOf("\"") + 3));
            stringArray.add(fieldArray.get(j).substring(0, fieldArray.get(j).indexOf("\"")));
            fieldArray.set(j, fieldArray.get(j).substring(fieldArray.get(j).indexOf("\"") + 3));
            stringArray.add(fieldArray.get(j).substring(0, fieldArray.get(j).indexOf("\"")));
            if(stringArray.get(0).equals("350034")){
                stringArray.set(11,"Edward");
            }
            fieldArray.set(j, fieldArray.get(j).substring(fieldArray.get(j).indexOf("\"") + 3));
            stringArray.add(fieldArray.get(j).substring(0, fieldArray.get(j).indexOf("\"")));
            if(stringArray.get(0).equals("350034")){
                stringArray.set(12,"Reed");
            }
            autofillArray.add(stringArray);
        }
    }

    public static ArrayList<ArrayList<String>> periodOneList() {               //Change methods to make the options for normal/abnormal day in settings;
        int i = 0;
        ArrayList<ArrayList<String>> periodOneList = new ArrayList<ArrayList<String>>();
        while (i < autofillArray.size()) {
            if (autofillArray.get(i).get(6).equals("01")) {
                periodOneList.add(autofillArray.get(i));
                i++;
            } else if (!autofillArray.get(i).get(6).equals("01")) {
                i++;
            }
        }
        return periodOneList;
    }

    public static ArrayList<ArrayList<String>> periodTwoList() {               //Change methods to make the options for normal/abnormal day in settings;
        int i = 0;
        ArrayList<ArrayList<String>> periodTwoList = new ArrayList<ArrayList<String>>();
        while (i < autofillArray.size()) {
            if (autofillArray.get(i).get(6).equals("02")) {
                periodTwoList.add(autofillArray.get(i));
                i++;
            } else if (!autofillArray.get(i).get(6).equals("02")) {
                i++;
            }
        }
        return periodTwoList;
    }

    public static ArrayList<ArrayList<String>> periodThreeList() {               //Change methods to make the options for normal/abnormal day in settings;
        int i = 0;
        ArrayList<ArrayList<String>> periodThreeList = new ArrayList<ArrayList<String>>();
        while (i < autofillArray.size()) {
            if (autofillArray.get(i).get(6).equals("03")) {
                periodThreeList.add(autofillArray.get(i));
                i++;
            } else if (!autofillArray.get(i).get(6).equals("03")) {
                i++;
            }
        }
        return periodThreeList;
    }

    public static ArrayList<ArrayList<String>> periodFourList() {               //Change methods to make the options for normal/abnormal day in settings;
        int i = 0;
        ArrayList<ArrayList<String>> periodFourList = new ArrayList<ArrayList<String>>();
        while (i < autofillArray.size()) {
            if (autofillArray.get(i).get(6).equals("04")) {
                periodFourList.add(autofillArray.get(i));
                i++;
            } else if (!autofillArray.get(i).get(6).equals("04")) {
                i++;
            }
        }
        return periodFourList;
    }

    public static ArrayList<ArrayList<String>> periodFiveList() {               //Change methods to make the options for normal/abnormal day in settings;
        int i = 0;
        ArrayList<ArrayList<String>> periodFiveList = new ArrayList<ArrayList<String>>();
        while (i < autofillArray.size()) {
            if (autofillArray.get(i).get(6).equals("05")) {
                periodFiveList.add(autofillArray.get(i));
                i++;
            } else if (!autofillArray.get(i).get(6).equals("05")) {
                i++;
            }
        }
        return periodFiveList;
    }

    public static ArrayList<ArrayList<String>> periodSixList() {               //Change methods to make the options for normal/abnormal day in settings;
        int i = 0;
        ArrayList<ArrayList<String>> periodSixList = new ArrayList<ArrayList<String>>();
        while (i < autofillArray.size()) {
            if (autofillArray.get(i).get(6).equals("06")) {
                periodSixList.add(autofillArray.get(i));
                i++;
            } else if (!autofillArray.get(i).get(6).equals("06")) {
                i++;
            }
        }
        return periodSixList;
    }

    public static ArrayList<ArrayList<String>> periodSevenList() {               //Change methods to make the options for normal/abnormal day in settings;
        int i = 0;
        ArrayList<ArrayList<String>> periodSevenList = new ArrayList<ArrayList<String>>();
        while (i < autofillArray.size()) {
            if (autofillArray.get(i).get(6).equals("07")) {
                periodSevenList.add(autofillArray.get(i));
                i++;
            } else if (!autofillArray.get(i).get(6).equals("07")) {
                i++;
            }
        }
        return periodSevenList;
    }

    public static ArrayList<ArrayList<String>> periodEightList() {               //Change methods to make the options for normal/abnormal day in settings;
        int i = 0;
        ArrayList<ArrayList<String>> periodEightList = new ArrayList<ArrayList<String>>();
        while (i < autofillArray.size()) {
            if (autofillArray.get(i).get(6).equals("08")) {
                periodEightList.add(autofillArray.get(i));
                i++;
            } else if (!autofillArray.get(i).get(6).equals("08")) {
                i++;
            }
        }
        return periodEightList;
    }



    public static void main(String[] args){
        createFields();
        System.out.println("Hi");
        ArrayList<ArrayList<String>> list = periodOneList();
        System.out.println(list);
        Date d = new Date();
        System.out.println(d.getHours());
        System.out.println(d.getMinutes());
        System.out.println(list);


    }
}
