public class GenericClasses {
    public static void main(String[] args) {
        // one Pair class (Pair.java), many types
        Pair<Integer, String> roll = new Pair<>(101, "Hita");
        Pair<String, Integer> result = new Pair<>("Somya", 465);
        Pair<String, Double> fee = new Pair<>("Sakshi", 150000.0);

        System.out.println("Roll  : " + roll.getKey() + " -> " + roll.getValue());
        System.out.println("Result: " + result);
        System.out.println("Fee   : " + fee);

        int marks = result.getValue();                   // no cast: the compiler knows V is Integer
        System.out.println("Somya's percentage: " + marks / 5.0);

        // roll = new Pair<>("Hita", 101);               // ERROR: roll must be Pair<Integer, String>
        // Pair<int, String> p;                          // ERROR: type arguments must be classes, use Integer
    }
}
