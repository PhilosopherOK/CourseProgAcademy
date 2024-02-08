package JavaPRO_ExtraCourse.HW3;
/*
3. написать код который сериализует и десериализирует в из файла
все значения полей коласса которые отмечены аннотацией @Save
 */
import java.io.*;
import java.lang.annotation.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

public class HW1_2_3 {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException {
        //#1
//        setAnnotParamToMethod();
        //#2
//        TestContainer testContainer = new TestContainer();
//        TestContainer.Saver s = new TestContainer.Saver();
//        s.save();
    }

    //#1
    public static void setAnnotParamToMethod() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException {
        Class<HW1_2_3> hw123Class = HW1_2_3.class;
        Method[] methods = hw123Class.getMethods();

        for (int i = 0; i < methods.length; i++) {
            if (methods[i].isAnnotationPresent(MyAnnotation.class)) {
                MyAnnotation myAnnotation = methods[i].getAnnotation(MyAnnotation.class);
                String methodName = methods[i].getName();
                Method neededMeth = hw123Class.getDeclaredMethod("test", String.class, String.class);

                neededMeth.setAccessible(true);
                neededMeth.invoke(hw123Class.getClass(), myAnnotation.firstParam(), myAnnotation.secondParam());

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
@SaveTo(path = "AAAA\\TestContOriginal.txt")
class TestContainer {
    private static String text = "qwratsdfas";

    public TestContainer(String text) {
        TestContainer.text = text;
    }

    static class Saver {
        Class<?> clazz = TestContainer.class;
        SaveTo saveTo = clazz.getAnnotation(SaveTo.class);

        public void save() {
            try (PrintWriter pw = new PrintWriter(saveTo.path())) {
                pw.println(text);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getText() {
        return text;
    }

    public static void setText(String text) {
        TestContainer.text = text;
    }
}

@Retention(value = RetentionPolicy.RUNTIME)
@interface SaveTo {
    String path() default "AAAA\\TextContainer.txt";
    String printWriter() default "PrintWriter";
}


//#3
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Save {
}

class TestSerializable implements Serializable {
    private static final long serialVersionUID = 1L;
    private String first = "1";
    @Save
    private String second = "2";
    private String three = "3";
    @Save
    private String four = "4";

    public TestSerializable() {
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

    @Override
    public String toString() {
        return "TestSerializable{" +
                "first='" + first + '\'' +
                ", second='" + second + '\'' +
                ", three='" + three + '\'' +
                ", four='" + four + '\'' +
                '}';
    }
}

class testSerDeser {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        String path = saveToFile(getObjectWithSaveAnnot(new TestSerializable()));
        System.out.println(getObjFromSer(path));
    }

    public static TestSerializable getObjectWithSaveAnnot(TestSerializable testSerializable) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        TestSerializable newTestSer = new TestSerializable();
        Class clazz = newTestSer.getClass();
        Arrays.stream(clazz.getMethods())
                .filter(f -> f.getName().startsWith("set"))
                .map(s -> s.getName().substring(3).toLowerCase())
                .collect(Collectors.toList())
                .stream()
                .map(s -> {
                    try {
                        return clazz.getDeclaredField(s);
                    } catch (NoSuchFieldException e) {
                        throw new RuntimeException(e);
                    }
                })
                .filter(f -> !(f.getAnnotation(Save.class) != null))
                .peek(f -> f.setAccessible(true))
                .forEach(f -> {
                    try {
                        f.set(newTestSer, null);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });
        return newTestSer;
    }

    public static String saveToFile(TestSerializable testSerializable) {
        String path = "AAAA\\save.ser";
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


