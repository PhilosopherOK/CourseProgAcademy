package Tsimbaliuk.HW10;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
2)Написать программу словарь (англо-украинский). Добавить возможность ручного
наполнения словаря и возможность его сохранения и вычитки из файла.
 */
public class MyEnglishUkrainianDictionary {
    public static void main(String[] args) throws IOException {
        Map<String,String> dictionary = new HashMap<>();
        createNewPareOfWords(dictionary);
        createNewPareOfWords(dictionary);
        System.out.println(dictionary.toString());
        String nameOfFile = saveTranslate(dictionary);
        dictionary = takeDictionaryFromFile(new File(nameOfFile));
        System.out.println(dictionary.toString());
    }

    public static void createNewPareOfWords(Map<String, String> dictionary){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter eng word");
        String engWord = scanner.nextLine();
        System.out.println("Please enter translate to ukr");
        String ukrTranslate = scanner.nextLine();
        dictionary.put(engWord,ukrTranslate);
        System.out.println("your translate will be added");
    }
    public static String saveTranslate(Map<String, String> map){
        String nameOfFile = "EnglishUkrainianDictionary.txt";
        File file = new File(nameOfFile);
        try(PrintWriter pw = new PrintWriter(file)) {
            file.createNewFile();
            for(Map.Entry<String, String> words: map.entrySet()){
                pw.println(words.getKey() + " - " + words.getValue());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return nameOfFile;
    }
    public static Map<String ,String> takeDictionaryFromFile(File file){
        Map<String ,String> dictionary = new HashMap<>();
        try(Scanner scanner = new Scanner(file)) {
            while(scanner.hasNext()){
                String strOfDict = scanner.nextLine();
                String [] strings = strOfDict.split(" - ");
                dictionary.put(strings[0], strings[1]);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return dictionary;
    }
}


















