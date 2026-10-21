// 1. Common Ancestor
class Student {
    void attendLab() {
        System.out.println("Attending standard student lab.");
    }
}

// 2. Parent Branch A
class TechStudent extends Student {
    @Override
    void attendLab() {
        System.out.println("Writing Java code in the computer lab.");
    }
}

// 3. Parent Branch B
class ArtsStudent extends Student {
    @Override
    void attendLab() {
        System.out.println("Painting on canvas in the design studio.");
    }
}

// 4. INVALID JAVA CODE: This will not compile!
class MultimediaStudent  {
    // void startDay() {
    //     // AMBIGUITY: Should they go to the Computer Lab or the Design Studio?
    //     attendLab(); 
    // }
}