package JavaStart.HW6;

import java.util.Scanner;

public class HowMuchMoneyDoYouHave {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How much money do you have ? (format xxx,xx / xxx)");
        String money = scanner.nextLine();

        System.out.println("You have: " + transferIntToString(money));
    }

    public static String convertToStrBillionThousandHundred(int number) {
        String[] massiveNumbers = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        for (int i = 0; i < massiveNumbers.length; i++) {
            if (number == (i + 1)) {
                return massiveNumbers[i];
            }
        }
        return "";
    }

    public static String convertToStrTens(int number) {
        String[] massiveNumbers = {"twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
        for (int i = 0; i < massiveNumbers.length; i++) {
            if (number == (i + 2)) {
                return massiveNumbers[i];
            }
        }
        return "";
    }

    public static String convertToTwenty(int number) {
        String[] massiveNumbers = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
                "ten", "eleven", "twelve", "thirteen", "fourteen",
                "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
        for (int i = 0; i < massiveNumbers.length; i++) {
            if (number == (i + 1)) {
                return massiveNumbers[i];
            }
        }
        return "";
    }

    public static String[] massiveOfParts(String dollars) {
        int parts = (int) Math.ceil(dollars.length() / 3.0);
        String[] massiveOfParts = new String[parts];

        int count = dollars.length() - 1;
        for (int i = massiveOfParts.length - 1; i >= 0; i--) {
            String temp = "";
            for (int j = 2; j >= 0; j--) {
                if (count - j < 0) {
                    continue;
                }
                temp = temp + dollars.charAt(count - j);
            }
            massiveOfParts[i] = temp;
            count -= 3;
        }
        return massiveOfParts;
    }

    public static String transferXXXNumberToStr(String dollars, String part) {
        String result = "";
        if (Integer.parseInt(dollars) == 0) {
            return "";
        }
        result = result + transferXNumberToStr(String.valueOf(dollars.charAt(0))) + " " + part + " "
                + transferXXNumberToStr(dollars.substring(1));
        return result;
    }

    public static String transferXXNumberToStr(String dollars) {
        String result = "";
        int dollarsInInt = Integer.parseInt(dollars);
        if (dollarsInInt == 0) {
            result += "";
        } else if (dollarsInInt <= 19) {
            result += convertToTwenty(dollarsInInt);
        } else if (dollarsInInt > 19) {
            String tens = convertToStrTens(Integer.parseInt(String.valueOf(dollars.charAt(0))));
            if (dollars.charAt(1) == 0) {
                result += tens;
            } else {
                String nums = transferXNumberToStr(String.valueOf(dollars.charAt(1)));
                result = result + tens + " " + nums;
            }
        }
        return result;
    }

    public static String transferXNumberToStr(String dollars) {
        String result = "";
        int dollarsInInt = Integer.parseInt(dollars);
        if (dollarsInInt == 0) {
            return "";
        }
        result += convertToStrBillionThousandHundred(dollarsInInt);
        return result;
    }

    public static String dollarToString(String dollars) {
        String result = "";
        String[] massiveOfParts = massiveOfParts(dollars);
        String hundred = "hundred";
        String prefix = "billion";
        for (int i = 0, j = massiveOfParts.length; i < massiveOfParts.length; i++, j--) {
            if (j == 1)
                prefix = "dollars";
            if (j == 2)
                prefix = "thousand";
            if (j == 3)
                prefix = "million";
            if (massiveOfParts[i].length() == 1) {
                result = result + transferXNumberToStr(massiveOfParts[i]) + " " + prefix + " ";
            } else if (massiveOfParts[i].length() == 2) {
                result = result + transferXXNumberToStr(massiveOfParts[i]) + " " + prefix + " ";
            } else {
                result = result + transferXXXNumberToStr(massiveOfParts[i], hundred) + " " + prefix + " ";
            }
        }
        return result;
    }

    public static String centsToString(String cents) {
        String result = "";
        result = result + transferXXNumberToStr(cents) + " cents";
        return result;
    }

    public static String transferIntToString(String str) {
        String result = "";
        String[] strings = str.split(",");
        String dollars = "0";
        String cent = "0";
        if (strings.length == 2) {
            dollars = strings[0];
            cent = strings[1];
        } else {
            dollars = str;
        }
        result = dollarToString(dollars);

        if (!cent.equals("0")) {
            result = result + centsToString(cent);
        }
        return result;
    }
}
