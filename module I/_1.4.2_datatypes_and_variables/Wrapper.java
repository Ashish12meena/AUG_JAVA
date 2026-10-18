// # 1.4 Wrapper Classes

// - A wrapper class wraps a primitive value inside an object.

// | Primitive | Wrapper |
// | --- | --- |
// | `byte` | `Byte` |
// | `short` | `Short` |
// | `int` | `Integer` |
// | `long` | `Long` |
// | `float` | `Float` |
// | `double` | `Double` |
// | `char` | `Character` |
// | `boolean` | `Boolean` |

public class Wrapper {

    public static void main(String[] args) {
        int a = 10;
        System.out.println("Primitive int: " + a);

        Integer b = Integer.valueOf(a); // Boxing
        System.out.println("Wrapper Integer: " + b);
    }
    
}
