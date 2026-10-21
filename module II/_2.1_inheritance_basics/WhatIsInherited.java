class LibraryMember {
    private String password;     // private: NOT visible in child
    String name;

    LibraryMember(String name) {
        this.name = name;

    }

    public boolean checkPassword(String p) {
        return password.equals(p);
    }

    public void setPassword(String p) {
        if (p.length() <= 6){
           System.out.println("Password must be more than 6 characters");
        }
        password = p;
    }
}

class StudentMember extends LibraryMember {
    int booksIssued;

    StudentMember(String name, String password) {
        super(name);   // constructors are not inherited, so call parent's
        super.setPassword(password);
        
    }

    void show() {
        System.out.println(name + " has " + booksIssued + " books");
        // System.out.println(password);   // ERROR: password has private access
    }
}

public class WhatIsInherited {
    public static void main(String[] args) {
        StudentMember m1 = new StudentMember("Disha", "vgu1234");
        m1.booksIssued = 2;
        m1.show();

        // private field is used through a public method
        System.out.println("Password ok? " + m1.checkPassword("vgu1234"));
    }
}
