package Tsymbaliuk_Oleksandr_JavaOOP.HW4;
/*
Продвинутый уровень.
1) Объявите интерфейс
public interface CSVConverter{
public String toCSVString();
public Student fromCSVString (String str);
}
Класс Студент должен реализовывать этот интерфейс. Логика реализации следующая — на
основе Студента создать строку с его CSV представлением и наоборот на основе этой строки
создать Студента.
 */
import Tsymbaliuk_Oleksandr_JavaOOP.HW3.Student;

public interface CSVConverter{
    public String toCSVString();
    public Student fromCSVString (String str);
}












