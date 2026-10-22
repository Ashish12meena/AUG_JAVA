// 2.2 Multilevel inheritance: CollegeMember -> UgStudent -> PgStudent
// 2.8 final class: no class can extend PgStudent
public final class PgStudent extends UgStudent {
    private String thesis;

    public PgStudent(String name, String thesis) {
        super(name);   // 2.3 runs UgStudent(), which runs CollegeMember()
        this.thesis = thesis;
    }

    // 2.5 Overriding: PG fee is different
    @Override
    public double fee() {
        return 150000;
    }

    // 2.5 + 2.3: PG must pass every subject (parent rule) AND get 50% overall
    @Override
    public boolean isPassed() {
        return super.isPassed() && percentage() >= 50;
    }

    @Override
    public String role() {
        return "PG Student";
    }

    // 2.3 super.method(): reuse the parent's printing, then add the thesis
    @Override
    public void showDetails() {
        super.showDetails();
        System.out.println("      Thesis: " + thesis);
    }
}
