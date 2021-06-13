import java.util.*;

class Main_hw2 {
    public static <T> Collection<T> removeDuplicates(Collection<T> collection){
        return new HashSet<>(collection);
    }

    public static <K, V> Map<V, Collection<K>> reverse_map(Map<? extends K, ? extends V> map){
        Map<V, Collection<K>> new_map = new HashMap<>();
        for (Map.Entry<? extends K, ? extends V> entry: map.entrySet())
            if(new_map.containsKey(entry.getValue())){
                new_map.get(entry.getValue()).add(entry.getKey());
            }
            else {
                new_map.put(entry.getValue(), new ArrayList());
                new_map.get(entry.getValue()).add(entry.getKey());
            }
        return new_map;
    }
    public static String winer (String[] mas){
        String[] input = new String[]{
                "Ivan 5", "Petr 3", "Alex 10",
                "Petr 8", "Ivan 6", "Alex 5",
                "Ivan 1", "Petr 5", "Alex 1"
        };
        int best_score = 0;
        String name_winer = "123";
        for (int i=0; i<input.length;i++){
            String[] new_mas = mas[i].split(" ");
            String name = new_mas[0];
            Integer score = Integer.parseInt(new_mas[1]);
            if (best_score<score){
                best_score = score;
                name_winer = name;
            }
        }
        return name_winer;
    }

    public static void main(String[] args) {
        HashSet<Integer> hs = new HashSet<Integer>();
        hs.add(1);
        hs.add(2);
        hs.add(3);
        hs.add(1);
        hs.add(4);
        System.out.println("Задание 1");
        System.out.println(removeDuplicates(hs));

        HashMap <Integer,Integer> test = new HashMap<>();
        test.put(228, 1337);
        test.put(322, 213);
        test.put(333, 1337);
        System.out.println("Задание 2");
        System.out.println(test);
        System.out.println(reverse_map(test));

        String[] input = new String[]{
                "Ivan 5", "Petr 3", "Alex 10",
                "Petr 8", "Ivan 6", "Alex 5",
                "Ivan 1", "Petr 5", "Alex 1"
        };
        System.out.println("Задание 3");
        System.out.println("Победитель - " + winer(input));
    }
}
