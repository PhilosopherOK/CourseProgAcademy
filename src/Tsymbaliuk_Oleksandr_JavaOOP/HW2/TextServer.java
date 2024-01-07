package Tsymbaliuk_Oleksandr_JavaOOP.HW2;

/*
1) Создайте класс TextTransformer.
Метод:
● String transform(String text) — Переводит текст в верхний регистр. Пример hello→ HELLO
2) Создайте класс InverseTransformer как подкласс TextTransformer. Переопределите метод String
transform(String text) — Производит реверс текста. Пример hello → olleh
3) Создайте класс UpDownTransformer как подкласс TextTransformer. Переопределите метод String
transform(String text) — Каждая нечетная буква большая, четная маленькая. Пример hello → HeLlO
4) Создайте класс TextSaver.
Поля:
● TextTransformer transformer
● File file
Методы:
● void saveTextToFile(String text) — Сначала преобразует одним из трансформеров (поле
transformer) строку, после чего сохранить ее в файл(поле file).
 */

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;


public class TextServer {
    public static void main(String[] args) throws IOException {
        File file = new File("TextTransformerFile.txt");
        file.createNewFile();
        TextServer textServer = new TextServer(new InverseTransformer(), file);
        textServer.saveTextToFile("Hello");
    }

    private TextTransformer transformer;
    private File file;

    public void saveTextToFile(String text) throws IOException {
        PrintWriter pw = new PrintWriter(file);
        String transformText = transformer.transform(text);
        System.out.println(transformText.toString());
        pw.print(transformText);
        pw.close();
    }

    public TextServer(TextTransformer transformer, File file) {
        this.transformer = transformer;
        this.file = file;
    }
}

class TextTransformer {

    public String transform(String text) {
        return text.toUpperCase();
    }
}

class InverseTransformer extends TextTransformer {
    @Override
    public String transform(String text) {
        char[] arrayText = text.toCharArray();
        for (int i = 0; i < arrayText.length / 2; i++) {
            char temp = arrayText[i];
            arrayText[i] = arrayText[arrayText.length - 1 - i];
            arrayText[arrayText.length - 1 - i] = temp;
        }
        text = String.valueOf(arrayText);
        return text;
    }
}

class UpDownTransformer extends TextTransformer {
    @Override
    public String transform(String text) {
        text = text.toLowerCase();
        char[] arrayText = text.toCharArray();
        for (int i = 1; i <= arrayText.length; i++) {
            if (i % 2 == 1 && arrayText[i - 1] != ' ') {
                arrayText[i - 1] -= 32;
            }
        }
        return new String(arrayText);
    }
}
