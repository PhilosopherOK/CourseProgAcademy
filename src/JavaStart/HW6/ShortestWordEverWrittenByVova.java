package JavaStart.HW6;

public class ShortestWordEverWrittenByVova {
    public static void main(String[] args) {
        System.out.println(searchingShortWord("catcatcat"));
    }

    public static String searchingShortWord(String str) {
        String result = "";
        int maxCounter = 0;
        int counter = 0;
        for (int i = 1; i <= str.length(); i++) {
            counter = 0;
            String tempStr = str.substring(0, i);
            for (int j = i; j <= str.length() - tempStr.length(); j++) {
                if (tempStr.contains(str.substring(j, j + tempStr.length()))) {
                    counter++;
                }
            }
            if (counter >= maxCounter) {
                maxCounter = counter;
                result = tempStr;
            }
        }
        return result;
    }
}
