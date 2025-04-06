package ZZZtest;

public class zuidashu {
    public static void main(String[] args) {
        int number = 13534353;
        int maxNumber = findMaxNumber(number);
        System.out.println("最大的数是: " + maxNumber);
    }

    public static int findMaxNumber(int number) {
        String str = String.valueOf(number);
        String maxNumber = str;
        String concatStr = str + str;

        for (int i = 1; i < str.length(); i++) {
            String currentNumberStr = concatStr.substring(i, i + str.length());

            if (currentNumberStr.compareTo(maxNumber) > 0) {
                maxNumber = currentNumberStr;
            }
        }

        return Integer.parseInt(maxNumber);
    }
}