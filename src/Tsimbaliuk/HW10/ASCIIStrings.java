package Tsimbaliuk.HW10;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
Продвинутый уровень.
1)Реализуйте программу которая сопоставит каждой букве ее представление в виде
ASCII- art, например
Ваша программа должна дать возможность вывода произвольного текста на экран в
виде его ASCII-art представления.
 */
public class ASCIIStrings {
    public static void main(String[] args) {
        takeASCIIFromStrInConsole("Hello World");
    }

    public static String takeASCIIFromStrInConsole(String str) {
        String[] resultArr = new String[]{"", "", "", "", "", "", "", ""};
        str = str.toLowerCase();
        for (int i = 0; i < str.length(); i++) {
            String[] strArr = takeASCIIFromLetter(str.charAt(i)).split("\n");
            for (int s = 0; s < strArr.length; s++) {
                resultArr[s] += strArr[s] + " ";
            }
        }
        for (int i = 0; i < resultArr.length; i++) {
            System.out.println(resultArr[i]);
        }
        return Arrays.toString(resultArr);
    }

    public static String takeASCIIFromLetter(Character letter) {
        Map<Character, String> dictionaryOfASCII = new HashMap<>();
        dictionaryOfASCII.put(' ', "   \n" +
                "   \n" +
                "   \n" +
                "   \n" +
                "   \n" +
                "   \n" +
                "   \n" +
                "   ");
        dictionaryOfASCII.put('a', " _______ \n" +
                "(  ___  )\n" +
                "| (   ) |\n" +
                "| (___) |\n" +
                "|  ___  |\n" +
                "| (   ) |\n" +
                "| )   ( |\n" +
                "|/     \\|");
        dictionaryOfASCII.put('b', " ______  \n" +
                "(  ___ \\ \n" +
                "| (   ) )\n" +
                "| (__/ / \n" +
                "|  __ (  \n" +
                "| (  \\ \\ \n" +
                "| )___) )\n" +
                "|/ \\___/ ");
        dictionaryOfASCII.put('c', " _______ \n" +
                "(  ____ \\\n" +
                "| (    \\/\n" +
                "| |      \n" +
                "| |      \n" +
                "| |      \n" +
                "| (____/\\\n" +
                "(_______/");
        dictionaryOfASCII.put('d', " ______  \n" +
                "(  __  \\ \n" +
                "| (  \\  )\n" +
                "| |   ) |\n" +
                "| |   | |\n" +
                "| |   ) |\n" +
                "| (__/  )\n" +
                "(______/ ");
        dictionaryOfASCII.put('e', " _______ \n" +
                "(  ____ \\\n" +
                "| (    \\/\n" +
                "| (__    \n" +
                "|  __)   \n" +
                "| (      \n" +
                "| (____/\\\n" +
                "(_______/\n");
        dictionaryOfASCII.put('f', " _______ \n" +
                "(  ____ \\\n" +
                "| (    \\/\n" +
                "| (__    \n" +
                "|  __)   \n" +
                "| (      \n" +
                "| )      \n" +
                "|/       \n");
        dictionaryOfASCII.put('g', " _______ \n" +
                "(  ____ \\\n" +
                "| (    \\/\n" +
                "| |      \n" +
                "| | ____ \n" +
                "| | \\_  )\n" +
                "| (___) |\n" +
                "(_______)");
        dictionaryOfASCII.put('h', "         \n" +
                "|\\     /|\n" +
                "| )   ( |\n" +
                "| (___) |\n" +
                "|  ___  |\n" +
                "| (   ) |\n" +
                "| )   ( |\n" +
                "|/     \\|\n");
        dictionaryOfASCII.put('i', "_________\n" +
                "\\__   __/\n" +
                "   ) (   \n" +
                "   | |   \n" +
                "   | |   \n" +
                "   | |   \n" +
                "___) (___\n" +
                "\\_______/");
        dictionaryOfASCII.put('j', "_________\n" +
                "\\__    _/\n" +
                "   )  (  \n" +
                "   |  |  \n" +
                "   |  |  \n" +
                "   |  |  \n" +
                "|\\_)  )  \n" +
                "(____/   \n");
        dictionaryOfASCII.put('k', " _       \n" +
                "| \\    /\\\n" +
                "|  \\  / /\n" +
                "|  (_/ / \n" +
                "|   _ (  \n" +
                "|  ( \\ \\ \n" +
                "|  /  \\ \\\n" +
                "|_/    \\/\n");
        dictionaryOfASCII.put('l', " _       \n" +
                "( \\      \n" +
                "| (      \n" +
                "| |      \n" +
                "| |      \n" +
                "| |      \n" +
                "| (____/\\\n" +
                "(_______/\n");
        dictionaryOfASCII.put('m', " _______ \n" +
                "(       )\n" +
                "| () () |\n" +
                "| || || |\n" +
                "| |(_)| |\n" +
                "| |   | |\n" +
                "| )   ( |\n" +
                "|/     \\|");
        dictionaryOfASCII.put('n', " _       \n" +
                "( (    /|\n" +
                "|  \\  ( |\n" +
                "|   \\ | |\n" +
                "| (\\ \\) |\n" +
                "| | \\   |\n" +
                "| )  \\  |\n" +
                "|/    )_)");
        dictionaryOfASCII.put('o', " _______ \n" +
                "(  ___  )\n" +
                "| (   ) |\n" +
                "| |   | |\n" +
                "| |   | |\n" +
                "| |   | |\n" +
                "| (___) |\n" +
                "(_______)");
        dictionaryOfASCII.put('p', " _______ \n" +
                "(  ____ )\n" +
                "| (    )|\n" +
                "| (____)|\n" +
                "|  _____)\n" +
                "| (      \n" +
                "| )      \n" +
                "|/       ");
        dictionaryOfASCII.put('q', " _______ \n" +
                "(  ___  )\n" +
                "| (   ) |\n" +
                "| |   | |\n" +
                "| |   | |\n" +
                "| | /\\| |\n" +
                "| (_\\ \\ |\n" +
                "(____\\/_)");
        dictionaryOfASCII.put('r', " _______ \n" +
                "(  ____ )\n" +
                "| (    )|\n" +
                "| (____)|\n" +
                "|     __)\n" +
                "| (\\ (   \n" +
                "| ) \\ \\__\n" +
                "|/   \\__/");
        dictionaryOfASCII.put('s', " _______ \n" +
                "(  ____ \\\n" +
                "| (    \\/\n" +
                "| (_____ \n" +
                "(_____  )\n" +
                "      ) |\n" +
                "/\\____) |\n" +
                "\\_______)");
        dictionaryOfASCII.put('t', "_________\n" +
                "\\__   __/\n" +
                "   ) (   \n" +
                "   | |   \n" +
                "   | |   \n" +
                "   | |   \n" +
                "   | |   \n" +
                "   )_(   ");
        dictionaryOfASCII.put('u', "|\\     /|\n" +
                "| )   ( |\n" +
                "| |   | |\n" +
                "| |   | |\n" +
                "| |   | |\n" +
                "| (___) |\n" +
                "(_______)");
        dictionaryOfASCII.put('v', "          \n" +
                "|\\     /|\n" +
                "| )   ( |\n" +
                "| |   | |\n" +
                "( (   ) )\n" +
                " \\ \\_/ / \n" +
                "  \\   /  \n" +
                "   \\_/   \n");
        dictionaryOfASCII.put('w', "         \n" +
                "|\\     /|\n" +
                "| )   ( |\n" +
                "| | _ | |\n" +
                "| |( )| |\n" +
                "| || || |\n" +
                "| () () |\n" +
                "(_______)\n");
        dictionaryOfASCII.put('x', "          \n" +
                "|\\     /|\n" +
                "( \\   / )\n" +
                " \\ (_) / \n" +
                "  ) _ (  \n" +
                " / ( ) \\ \n" +
                "( /   \\ )\n" +
                "|/     \\|\n");
        dictionaryOfASCII.put('y', "          \n" +
                "|\\     /|\n" +
                "( \\   / )\n" +
                " \\ (_) / \n" +
                "  \\   /  \n" +
                "   ) (   \n" +
                "   | |   \n" +
                "   \\_/   \n");
        dictionaryOfASCII.put('z', " _______ \n" +
                "/ ___   )\n" +
                "\\/   )  |\n" +
                "    /   )\n" +
                "   /   / \n" +
                "  /   /  \n" +
                " /   (_/\\\n" +
                "(_______/");
        return dictionaryOfASCII.get(letter);
    }

}
