import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

class Student {
    int id;
    String name;
    int total;                  // marks out of 500

    Student(int id, String name, int total) {
        this.id = id;
        this.name = name;
        this.total = total;
    }

    @Override
    public String toString() {
        return id + "  " + name + "  " + total;
    }
}

public class Maps {

    public static void main(String[] args) {
        // List<String> stringList = new LinkedList<>();
        
        // 1. HashMap: roll number (key) -> student (value). Find without a loop.
        Map<Integer, Student> register = new HashMap<>();
        register.put(101, new Student(101, "Hita", 438));
        register.put(102, new Student(102, "Sakshi", 244));
        register.put(103, new Student(103, "Somya", 465));
        register.put(104, new Student(104, "Ritika", 412));
        register.put(105, new Student(105, "Disha", 438));

        System.out.println("Roll 103   : " + register.get(103));
        System.out.println("Roll 999   : " + register.get(999));               // null: no such key
        System.out.println("Has 102?   : " + register.containsKey(102));

        register.put(102, new Student(102, "Sakshi", 310));   // same key: old value is replaced
        System.out.println("Re-checked : " + register.get(102));

        register.putIfAbsent(101, new Student(101, "Someone", 0));   // 101 is taken: nothing changes
        System.out.println("Roll 101   : " + register.get(101));

        register.remove(105);                                  // Disha moves to section B
        System.out.println("Students   : " + register.size());
        // System.out.println();

        // // 2. Visiting a map: keySet(), values(), entrySet()
        System.out.println("Keys   : " + register.keySet());
        System.out.println("Values : " + register.values());
        for (Map.Entry<Integer, Student> e : register.entrySet()) {
            System.out.println(e.getKey() + " -> " + e.getValue().name);
        }
        System.out.println();

        // // 3. Counting with a map: how many students got each grade?
        String[] grades = {"B", "D", "A", "B", "B"};          // Hita, Sakshi, Somya, Ritika, Disha
        Map<String, Integer> gradeCount = new TreeMap<>();    // TreeMap: keys in A, B, C order
        for (String g : grades) {
            gradeCount.put(g, gradeCount.getOrDefault(g, 0) + 1);   // 0 the first time
        }
        // B , 3
        // D, 1
        // A , 1
        
        System.out.println("Grade count: " + gradeCount);
        // System.out.println();

        // // 4. Same data, three maps, three orders
        String[] names = {"Somya", "Hita", "Ritika"};
        int[] totals = {465, 438, 412};
        Map<String, Integer> hash = new HashMap<>();
        Map<String, Integer> linked = new LinkedHashMap<>();
        Map<String, Integer> tree = new TreeMap<>();
        for (int i = 0; i < names.length; i++) {
            hash.put(names[i], totals[i]);
            linked.put(names[i], totals[i]);
            tree.put(names[i], totals[i]);
        }
        // System.out.println("HashMap      : " + hash);        // no fixed order
        // System.out.println("LinkedHashMap: " + linked);      // order of put()
        // System.out.println("TreeMap      : " + tree);        // keys A to Z
        // System.out.println("Anyone got 465? " + tree.containsValue(465));
        // System.out.println();

        // // 5. TreeMap extra methods: keys are sorted
        TreeMap<Integer, Student> byRoll = new TreeMap<>(register);
        System.out.println("First roll   : " + byRoll.firstKey());
        System.out.println("Last roll    : " + byRoll.lastKey());
        System.out.println("Below 103    : " + byRoll.headMap(103).keySet());
        System.out.println("103 and above: " + byRoll.tailMap(103).keySet());
    }
}
