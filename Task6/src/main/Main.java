package main;

public class Main {
    public static int[] subArray(int[] arr) {
        int indexOf4 = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 4) indexOf4 = i;
        }
        if (indexOf4 == -1) throw new RuntimeException("There is no any '4'");
        int[] resultArr = new int[arr.length - indexOf4 - 1];
        for (int i = 0; i < resultArr.length; i++) {
            resultArr[i] = arr[i + indexOf4 + 1];
        }
        return resultArr;
    }

    public static boolean checkArray1And4(int[] arr) {
        boolean pres4 = false;
        boolean pres1 = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 4) pres4 = true;
            else if (arr[i] == 1) pres1 = true;
            else return false;
        }
        return pres1 && pres4;
    }
}
