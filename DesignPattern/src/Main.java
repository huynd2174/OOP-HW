public class Main {
    public static void main(String[] args) throws Exception {
        FamilyTree familyTree = new FamilyTree();

        Person james = new Person("James", "01/01/1970", "Male");
        Person hana = new Person("Hana", "01/01/1972", "Female");
        james.marry(hana);

        Person ryan = new Person("Ryan", "01/01/1990", "Male");
        Person kai = new Person("Kai", "01/01/1992", "Male");
        james.addChild(ryan);
        james.addChild(kai);

        Person jennifer = new Person("Jennifer", "01/01/1992", "Female");
        kai.marry(jennifer);

        Person child1 = new Person("Child1", "01/01/2010", "Male");
        Person child2 = new Person("Child2", "01/01/2012", "Male");
        Person child3 = new Person("Child3", "01/01/2014", "Female");
        Person child4 = new Person("Child4", "01/01/2016", "Female");
        kai.addChild(child1);
        kai.addChild(child2);
        kai.addChild(child3);
        kai.addChild(child4);
        james.setGeneration(1);

        familyTree.addMember(james);
        familyTree.addMember(hana);
        familyTree.addMember(ryan);
        familyTree.addMember(kai);
        familyTree.addMember(jennifer);
        familyTree.addMember(child1);
        familyTree.addMember(child2);
        familyTree.addMember(child3);
        familyTree.addMember(child4);

        System.out.println("Unmarried members: ");
        for (Person unmarriedMember : familyTree.getUnmarriedMembers()) {
            System.out.println(unmarriedMember);
        }

        System.out.println("Couples with two children: ");
        for (Person couple : familyTree.getCouplesWithTwoChildren()) {
            System.out.println(couple);
        }

        System.out.println("Latest generation: ");
        for (Person latestGenMember : familyTree.getLatestGeneration()) {
            System.out.println(latestGenMember);
        }
    }
}