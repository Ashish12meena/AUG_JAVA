// 2.2 Multilevel inheritance: CollegeMember -> Student -> ResearchStudent
// WHY extend Student? ResearchStudent IS-A Student: same 5 marks, grade and fee,
// plus a research topic and a guide. We reuse all of that and change only what is different.
// WHY not "PgStudent extends UgStudent"? A PG student is NOT a UG student. Wrong IS-A.
// 2.8 final class: no class can extend ResearchStudent
// WHY final? It is the last level in our college, nothing comes below it.
public final class ResearchStudent extends Student {
    private String researchTopic;
    private String guide;

    public ResearchStudent(String name, String researchTopic, String guide) {
        super(name);   // 2.3 runs Student(), which runs CollegeMember()
        this.researchTopic = researchTopic;
        this.guide = guide;
    }

    // 2.5 Overriding: research fee is different
    // WHY? Same question "what is your fee?", different answer.
    // balance() in FeePayer calls fee(), so it automatically uses 150000 here.
    @Override
    public double fee() {
        return 150000;
    }

    // 2.5 + 2.3: must pass every subject (parent rule) AND get 50% overall
    // WHY super.isPassed()? Reuse the parent's rule instead of writing it again.
    @Override
    public boolean isPassed() {
        return super.isPassed() && percentage() >= 50;
    }

    @Override
    public String role() {
        return "Research Student";
    }

    // 2.3 super.method(): reuse the parent's printing, then add topic and guide
    @Override
    public void showDetails() {
        super.showDetails();
        System.out.println("      Topic: " + researchTopic + "  Guide: " + guide);
    }
}
