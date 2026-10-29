interface Greeting {
    void greet(String name);
}

class College {
    private String name;

    College(String name) {
        this.name = name;
    }

    // 1. static nested class: belongs to College, but needs NO College object
    static class Rules {
        void show() {
            System.out.println("Rules: pass mark 40%, attendance 75%");
        }
    }

    // 2. inner class: each Department belongs to ONE College object
    class Department {
        String deptName;

        Department(String deptName) {
            this.deptName = deptName;
        }

        void show() {
            System.out.println(deptName + " department of " + name);   // uses outer private field
        }
    }

    // 3. local class: made inside a method, used only there
    void conductExam(String subject) {
        class Invigilator {
            String teacher;

            Invigilator(String teacher) {
                this.teacher = teacher;
            }

            void check() {
                System.out.println(teacher + " checks hall tickets for the " + subject + " exam at " + name);
            }
        }

        Invigilator inv = new Invigilator("Mr. Verma");
        inv.check();
    }
}

public class NestedClasses {
    public static void main(String[] args) {
        College.Rules rules = new College.Rules();            // no College object needed
        rules.show();

        College vgu = new College("VGU College");
        College.Department cse = vgu.new Department("CSE");   // needs a College object
        cse.show();

        vgu.conductExam("Java");

        // 4. anonymous class: a class with no name, made and used once
        Greeting welcome = new Greeting() {
            @Override
            public void greet(String name) {
                System.out.println("Welcome to VGU College, " + name);
            }
        };
        welcome.greet("Hita");

        // the same with a lambda (Greeting has only one method)
        Greeting luck = name -> System.out.println("Best of luck for the exam, " + name);
        luck.greet("Somya");
    }
}
