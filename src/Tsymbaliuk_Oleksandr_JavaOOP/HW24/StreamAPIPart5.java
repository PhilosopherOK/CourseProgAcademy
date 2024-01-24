package Tsymbaliuk_Oleksandr_JavaOOP.HW24;
/*
Stream API
Часть 5
Терминальные методы генерирующие
результат на основании данных потока
Задание для самостоятельной проработки
1)Вычитайте из текстового файла адреса каталогов файловой системы. Верните первый
из каталогов в котором расположено более 3-х текстовых файлов.
2)Реализуйте возможность выбора из списка языков программирования (пример в лекции)
язык с заданной сложностью обучения. Верните любой из обладающих нужной
сложностью.
 */

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StreamAPIPart5 {
    //#1
    public static File getCatalogWith3OrMoreFiles(File file) throws IOException {
        Optional<File> file1 = Files.lines(Path.of(file.getAbsolutePath()))
                .map(s -> new File(s))
                .filter(f -> f.isDirectory())
                .filter(f -> Arrays.stream(f.listFiles()).filter(f1 -> f1.getAbsolutePath().endsWith(".txt")).count() > 3)
                .findFirst();
        return file1.orElse(null);
    }

    //#2
    public static ProgrammingLanguage getSomeProgrammingLangWithComplexity(List<ProgrammingLanguage> list, DifficultyLevel dL) {
        Optional<ProgrammingLanguage> programmingLanguage = list.stream()
                .filter(pL -> pL.getComplexity().equals(dL))
                .findAny();
        return programmingLanguage.orElse(null);
    }
}


enum DifficultyLevel {
    EASY, MEDIUM, HARD;
}

class ProgrammingLanguage {
    private String name;
    private DifficultyLevel complexity;

    public ProgrammingLanguage(String name, DifficultyLevel complexity) {
        super();
        this.name = name;
        this.complexity = complexity;
    }

    public String getName() {
        return name;
    }

    public DifficultyLevel getComplexity() {
        return complexity;
    }

    @Override
    public String toString() {
        return "ProgrammingLanguage [name=" + name + ", complexity=" + complexity + "]";
    }
}