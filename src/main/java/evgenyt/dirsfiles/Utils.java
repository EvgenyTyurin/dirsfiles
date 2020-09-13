package evgenyt.dirsfiles;

import java.util.ArrayList;

public class Utils {

    static class StringPatch implements Comparable {
        boolean isInt;
        Integer intVal;
        String strVal;

        public StringPatch(String str) {
            try {
                intVal = Integer.parseInt(str);
                isInt = true;
            } catch (NumberFormatException ignored) {
                strVal = str.toUpperCase();
                isInt = false;
            }
        }

        @Override
        public String toString() {
            return isInt ? String.valueOf(intVal) : strVal;
        }

        @Override
        public int compareTo(Object o) {
            StringPatch other = (StringPatch) o;
            if (isInt)
                return other.isInt ? intVal.compareTo(other.intVal) : -1;
            else
                return other.isInt ? 1 : strVal.compareTo(other.strVal);
        }
    }

    public static int compareFileNames(String name1, String name2) {
        ArrayList<Utils.StringPatch> patches1 = Utils.StrToPatches(name1.split("\\.")[0]);
        ArrayList<Utils.StringPatch> patches2 = Utils.StrToPatches(name2.split("\\.")[0]);
        return Utils.compareLists(patches1, patches2);
    }

    private static ArrayList<StringPatch> StrToPatches(String str) {
        String[] strings = str.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
        ArrayList<StringPatch> patches = new ArrayList<>();
        for (String s : strings) {
            patches.add(new StringPatch(s));
        }
        return patches;
    }

    private static int compareLists(ArrayList<StringPatch> listA, ArrayList<StringPatch> listB) {
        ArrayList<StringPatch> shortList = listA.size() < listB.size() ? listA : listB;
        ArrayList<StringPatch> longList = listA.size() >= listB.size() ? listA : listB;
        int result = 0;
        for (int idx = 0; idx < shortList.size(); idx++) {
            result = shortList.get(idx).compareTo(longList.get(idx));
            if (result != 0)
                break;
        }
        if (result == 0 && listA.size() == listB.size())
            return 0;
        if (shortList != listA)
            result = -1 * result;
        return result;
    }

}
