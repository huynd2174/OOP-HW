
import java.util.*;
public class FamilyTree {
    List<Person> members;

    public FamilyTree() {
        this.members = new ArrayList<>();
    }

    public void addMember(Person person) {
        this.members.add(person);
    }

    public List<Person> getUnmarriedMembers() {
        List<Person> unmarried = new ArrayList<>();
        for (Person member : members) {
            if (!member.isMarried()) {
                unmarried.add(member);
            }
        }
        return unmarried;
    }

    public List<Person> getCouplesWithTwoChildren() {
        List<Person> couples = new ArrayList<>();
        for (Person member : members) {
            if (member.isMarried() && member.hasTwoChildren()) {
                couples.add(member);
            }
        }
        return couples;
    }

    public List<Person> getLatestGeneration() {
        int maxGeneration = 0;
        for (Person member : members) {
            if (member.getGeneration() > maxGeneration) {
                maxGeneration = member.getGeneration();
            }
        }

        List<Person> latestGeneration = new ArrayList<>();
        for (Person member : members) {
            if (member.getGeneration() == maxGeneration) {
                latestGeneration.add(member);
            }
        }
        return latestGeneration;
    }
}