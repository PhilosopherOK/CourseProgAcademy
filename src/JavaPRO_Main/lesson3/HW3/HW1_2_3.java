package JavaPRO_Main.lesson3.HW3;
/*
1.Создать аннотацию, которая принимает параметры для метода. Написать код,
который вызовет метод, помеченный этой аннотацией, и передаст параметры аннотации в вызываемый метод.
@Test(a=2, b=5)
public void test(int a, int b){...}

2. Написать класс TextContainer, который содержит в себе строку. С помощью механизма аннотаций указать:
1) в какой файл доc лжен сохраниться текст 2) метод, который выполнит сохранение. Написать класс Saver,
который сохранит поле класса TextContainer в указанный файл

3. написать код который сериализует и десериализирует в из файла
все значения полей коласса которые отмечены аннотацией @Save


4. Додаткове ДЗ:
Беремо задачу 3 по темі Reflection API і після реалізації простого сценарію, коли в класі можуть бути
@Save поля, робимо реалізацію, що зможе опрацювати рекурсію, наприклад:
class A {
  @Save int a;
  @Save String b;
  int z;
}

class B {
  @Save long e;
  @Save A a; // !!! тип, що теж має save поля !!!
  String x;
}
*/

import java.io.*;
import java.lang.annotation.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class HW1_2_3 {
    public static void main(String[] args) {
        //#1
        setAnnotParamToMethod();
    }

    //#1
    public static void setAnnotParamToMethod() {
        Class<HW1_2_3> hw123Class = HW1_2_3.class;
        Method[] methods = hw123Class.getMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(MyAnnotation.class)) {
                MyAnnotation myAnnotation = method.getAnnotation(MyAnnotation.class);
                try {
                    method.invoke(hw123Class.getClass(), myAnnotation.firstParam(), myAnnotation.secondParam());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @MyAnnotation(firstParam = "hi ", secondParam = "Avraam !")
    public static void test(String a, String b) {
        System.out.println(a + b);
    }
}

@Inherited
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
@interface MyAnnotation {
    String firstParam() default "first";

    String secondParam() default "second";
}


//#2

@Inherited
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
@interface Saver {
}

@Retention(value = RetentionPolicy.RUNTIME)
@interface SaveTo {
    String path() default "TextContainer.txt";
}

@SaveTo(path = "TestContOriginal.txt")
class TestContainer {
    private static String text = "qwratsdfas";

    public TestContainer(String text) {
        TestContainer.text = text;
    }

    @Saver
    public void save(String path) {
        File file = new File(path);

        try (PrintWriter pw = new PrintWriter(file)) {
            file.createNewFile();
            pw.println(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getText() {
        return text;
    }

    public static void setText(String text) {
        TestContainer.text = text;
    }
}

class SaverMain {
    public static void main(String[] args) {
        saveStrToFile();
    }

    public static void saveStrToFile() {
        Class<TestContainer> containerClass = TestContainer.class;
        TestContainer testContainer = new TestContainer("adfhdыаполародлрполджролдfghj");
        SaveTo saveTo = containerClass.getAnnotation(SaveTo.class);
        Method[] methods = containerClass.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Saver.class)) {
                try {
                    method.invoke(testContainer, saveTo.path());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}


//#3-4
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Save {
}

class TestSerializable implements Serializable {
    private String first = "1";
    @Save
    private String second = "2";
    @Save

    private String three = "3";
    private String four = "4";

    @Save
    private TestSerializable testSerializable;

    public TestSerializable(TestSerializable testSerializable) {
        this.testSerializable = testSerializable;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public String getThree() {
        return three;
    }

    public void setThree(String three) {
        this.three = three;
    }

    public String getFour() {
        return four;
    }

    public void setFour(String four) {
        this.four = four;
    }

    public TestSerializable getTestSerializable() {
        return testSerializable;
    }

    public void setTestSerializable(TestSerializable testSerializable) {
        this.testSerializable = testSerializable;
    }

    @Override
    public String toString() {
        return "TestSerializable{" +
                "first='" + first + '\'' +
                ", second='" + second + '\'' +
                ", three='" + three + '\'' +
                ", four='" + four + '\'' +
                ", testSerializable=" + testSerializable +
                '}';
    }
}

class testSerDeser {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        String path = saveToFile(getObjectWithSaveAnnot(new TestSerializable(new TestSerializable(new TestSerializable(null)))));
        System.out.println(getObjFromSer(path));
    }

    public static <T> T getObjectWithSaveAnnot(T anyObj) {
        Class clazz = anyObj.getClass();
        Arrays.stream(clazz.getDeclaredFields())
                .peek(f -> f.setAccessible(true))
                .forEach(f -> {
                    if (f.isAnnotationPresent(Save.class)) {
                        if (f.getType() == TestSerializable.class) {
                            try {
                                if (f.get(anyObj) != null) {
                                    T tObj = (T) f.get(anyObj);
                                    getObjectWithSaveAnnot(tObj);
                                }
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        try {
                            f.set(anyObj, null);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
        return anyObj;
    }

    public static String saveToFile(TestSerializable testSerializable) {
        String path = "save.ser";
        try (FileOutputStream outputStream = new FileOutputStream(path);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            objectOutputStream.writeObject(testSerializable);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    public static TestSerializable getObjFromSer(String path) {
        try (FileInputStream fileInputStream = new FileInputStream(path);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            return (TestSerializable) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}