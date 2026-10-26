import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

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

public class WhyCollections {
    public static void main(String[] args) {
        
        // // 1. Array: size is fixed when it is made
        // Student[] section = new Student[3];
        // section[0] = new Student(101, "Hita", 438);
        // section[1] = new Student(102, "Sakshi", 244);
        // section[2] = new Student(103, "Somya", 465);
        // // section[3] = new Student(104, "Ritika", 412);   // ERROR at run time: ArrayIndexOutOfBoundsException

        // // to find Somya we must write our own loop
        // for (int i = 0; i < section.length; i++) {
        //     if (section[i].name.equals("Somya")) {
        //         System.out.println("Found in array: " + section[i]);
        //     }
        // }
        // System.out.println();


        // 2. ArrayList: grows by itself and has ready-made methods
        List<Student> list = new ArrayList<>();         // interface on the left, class on the right
        list.add(new Student(101, "Hita", 438));
        list.add(new Student(102, "Sakshi", 244));
        list.add(new Student(103, "Somya", 465));
        list.add(new Student(104, "Ritika", 412));      // no problem: the list grows
        list.add(new Student(105, "Disha", 438));

        System.out.println("Students in list: " + list.size());
        System.out.println(list);                         // prints all, using toString()

        list.remove(1);                                   // Sakshi moves to section B: no shifting by hand
        System.out.println("After Sakshi leaves: " + list);
        System.out.println();

        // 3. Collections (with "s"): a helper class full of static methods
        List<String> names = new ArrayList<>();
        names.add("Somya");
        names.add("Hita");
        names.add("Ritika");
        Collections.sort(names);                        // no sorting code written by us
        System.out.println("Sorted names: " + names);


        List<Integer> list2 = new ArrayList<>();
        list2.add(34);
        list2.add(32);
        list2.add(1);
        list2.add(77);

        Object[] arr =  list2.toArray();

        Collections.sort(list2);;
        System.out.println(list2);

        System.out.println(arr);

        
       



    }   
}
