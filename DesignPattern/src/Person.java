import java.util.*;
import java.text.SimpleDateFormat;

public class Person {
    String name;
    Date dob;
    String gender;
    Person spouse;
    List<Person> children;
    int generation;

    // Constructor
    public Person(String name, String dob, String gender) throws Exception {
        this.name = name;
        this.dob = new SimpleDateFormat("dd/MM/yyyy").parse(dob);
        this.gender = gender;
        this.children = new ArrayList<>();
    }

    // Methods
    public void marry(Person spouse) {
        this.spouse = spouse;
        spouse.spouse = this;
    }

    public void addChild(Person child) {
        this.children.add(child);
    }

    public boolean isMarried() {
        return spouse != null;
    }

    public boolean hasTwoChildren() {
        return children.size() == 2;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
        for (Person child : children) {
            child.setGeneration(generation + 1);
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name)
                .append(", Date of Birth: ").append(new SimpleDateFormat("dd/MM/yyyy").format(dob))
                .append(", Gender: ").append(gender)
                .append(", Generation: ").append(generation)
                .append(", Married: ").append(isMarried());

        if (isMarried()) {
            sb.append(", Spouse: ").append(spouse.name)
                    .append(", Spouse's Date of Birth: ").append(new SimpleDateFormat("dd/MM/yyyy").format(spouse.dob))
                    .append(", Spouse's Gender: ").append(spouse.gender);
        }

        if (!children.isEmpty()) {
            sb.append(", Children: [");
            for (Person child : children) {
                sb.append(child.name).append(", ");
            }
            sb.setLength(sb.length() - 2); // Remove the trailing comma and space
            sb.append("]");
        }

        return sb.toString();
    }
}