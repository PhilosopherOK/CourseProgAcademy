package Tsimbaliuk.HW10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.util.Map.Entry.comparingByValue;

/*
Основной уровень.
1)Считайте из файла текст на английском языке, вычислите относительную частоту
повторения каждой буквы и выведите на экран результат в порядке убывания
относительной частоты повторения.
 */
public class SortedDictionaryFromFile {
    public static void main(String[] args) throws FileNotFoundException {
        File engFile = new File("A.csv");
        Scanner scanner = new Scanner(engFile);
        String str = "";
        while (scanner.hasNext()) {
            str += scanner.nextLine();
        }
        System.out.println(str);
        HashMap<Character, Integer> dictionary = new HashMap<>();
        String[] strings = str.split(",");

        for (int i = 0; i < strings.length; i++) {
            String tempStr = strings[i];
            for (int j = 0; j < tempStr.length(); j++) {
                char findChar = tempStr.charAt(j);
                if (!dictionary.containsKey(findChar)) {
                    dictionary.put(findChar, 1);
                } else {
                    int count = dictionary.get(findChar);
                    dictionary.put(findChar, count + 1);
                }
            }
        }
        System.out.println(dictionary);
//        System.out.println(entriesSortedByValues(dictionary));


        LinkedHashMap<Character, Integer> sortedDictionary = new LinkedHashMap<>();
        int sizeOfDict = dictionary.size();
        for (int i = 0; i < sizeOfDict; i++) {
            int max = 0;
            char keyFromMax = ' ';
            for (Map.Entry<Character, Integer> map : dictionary.entrySet()) {
                if (max < map.getValue()) {
                    max = map.getValue();
                    keyFromMax = map.getKey();
                }
            }
            dictionary.remove(keyFromMax,max);
            sortedDictionary.put(keyFromMax,max);
        }
        System.out.println(sortedDictionary.toString());
    }

    // это решение подсмотрел на стак оверфлоу и доделал под задачу, но какое то оно уж очень тяжелое
//    static <K, V extends Comparable<? super V>> SortedSet<Map.Entry<K, V>> entriesSortedByValues(Map<K, V> map) {
//        SortedSet<Map.Entry<K, V>> sortedEntries = new TreeSet<>(
//                new Comparator<Map.Entry<K, V>>() {
//                    @Override
//                    public int compare(Map.Entry<K, V> e1, Map.Entry<K, V> e2) {
//                        int res = e1.getValue().compareTo(e2.getValue());
//                        return res != 0 ? -res : 1;
//                    }
//                }
//        );
//        sortedEntries.addAll(map.entrySet());
//        return sortedEntries;
//    }
}
