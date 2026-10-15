
// Type	Description	Default	Size	Example	Range
// boolean	Logical values	false	Not JVM-defined	true, false	true or false
// byte  	8-bit    signed   integer	0	1 byte	10	-128 to 127
// char   	16-bit   Unicode character	\u0000	2 bytes	'A', '\u0041'	0 to 65,535
// short	16-bit   signed integer	0	2 bytes	2000	-32,768 to 32,767
// int   	32-bit   signed integer	0	4 bytes	1000, -500	-2,147,483,648 to 2,147,483,647
// long	   64-bit    signed integer	0L	8 bytes	123456789L	±9.22e18
// float	32-bit   floating point	0.0f	4 bytes	3.14f	~6–7 digits precision
// double	64-bit   floating point	0.0d	8 bytes	3.14159d	~15–16 digits precision


//datatypes are the classification of data which tells the compiler or interpreter how the programmer intends to use the data.
//  Java is a strongly typed language, which means that every variable and expression type is already known at compile time.
public class DataTypes {
    public static void main(String[] args) {
        byte semester = 3;
        short students = 1200;
        int rollNo = 101;
        long phone = 9876543210L;   // L for long
        float cgpa = 8.5f;          // f for float
        double percentage = 87.6;
        char grade = 'A';
        boolean passed = true;

        String name = "Riya";       // non-primitive (reference) type

        System.out.println("Semester: " + semester);
        System.out.println("Students: " + students);
        System.out.println("Roll No: " + rollNo);
        System.out.println("Phone: " + phone);
        System.out.println("CGPA: " + cgpa);
        System.out.println("Percentage: " + percentage);
        System.out.println("Grade: " + grade);
        System.out.println("Passed: " + passed);
        System.out.println("Name: " + name);
    }
}
