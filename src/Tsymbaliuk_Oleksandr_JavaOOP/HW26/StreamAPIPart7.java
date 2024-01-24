package Tsymbaliuk_Oleksandr_JavaOOP.HW26;
/*
Stream API
Часть 7
Терминальные методы collect
Задание для самостоятельной проработки
1) Используя Collector напишите свою реализацию метода Collectors.toSet()
2) Используя Collector соберите в карту, только те слова в потоке строк, в которых есть гласные.
 */

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class StreamAPIPart7 {
    private static Set<Character> vowels = Set.of('a', 'e', 'y', 'u', 'i', 'o');
    public static void main(String[] args) {
        //#2
        Map<String, List<String>> map = Arrays.stream("asdgf asdhjo gfgj5rt gdfhhg sdfjo".split(" "))
                .collect(HashMap::new, (m, str) -> {
                    for (int i = 0; i < str.length(); i++) {
                        if (!m.containsKey("vowels")) {
                            m.put("vowels", new ArrayList<>());
                        }
                        if (vowels.contains(str.charAt(i))) {
                            m.get("vowels").add(str);
                            break;
                        }
                    }
                }, HashMap::putAll);
        System.out.println(map);
    }
}

//#1
class MyCollector1<T> implements Collector<T, Set<T>, Set<T>> {

    @Override
    public Supplier supplier() {
        return HashSet::new;
    }

    @Override
    public BiConsumer<Set<T>, T> accumulator() {
        return (a, b) -> a.add(b);
    }

    @Override
    public BinaryOperator<Set<T>> combiner() {
        return (s1, s2) -> {
            s1.addAll(s2);
            return s1;
        };
    }

    @Override
    public Function<Set<T>, Set<T>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of();
    }
}
