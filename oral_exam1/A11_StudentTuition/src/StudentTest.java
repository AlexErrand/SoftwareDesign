public class StudentTest {


    public static void main(String[] args)
    {
        //polymorphism refers to the ability of a class to provide different implementations of a method,
        // depending on the type of object that is passed to the method.
        Student Jonothan = new StudentCollege("Jonothan", "Jones", "2762890", "Iowa", "Engineering", 1, "Graduate");
        Student Jones = new StudentCollege("Jones", "West", "2762890", "Iowa", "liberal arts", 10, "Graduate");
        Student Jeremy = new StudentCollege("Jeremy", "Petersen", "2762890", "Iowa", "Engineering", 2, "undergraduate");
        Student Jeremiah = new StudentCollege("Jeremiah", "Jones", "2762890", "Iowa", "liberal arts", 5, "undergraduate");
        Student Julie = new StudentCollege("Julie", "Heart", "2762890", "Iowa", "Engineering", 4, "open enrolled");
        Student Jack = new StudentCollege("Jack", "Daniel", "2762890", "Iowa", "Engineering", 11, "open enrolled");
        Student Darius = new StudentCollege("Darius", "Griddy", "2762890", "domestic", "Engineering", 22, "Graduate");
        Student Alex = new StudentCollege("Alex", "Arand", "2762890", "domestic", "liberal arts", 7, "Graduate");
        Student Alexis = new StudentCollege("Alexis", "Jonas", "2762890", "domestic", "Engineering", 8, "undergraduate");
        Student Hans = new StudentCollege("Hans", "Johnson", "2762890", "domestic", "liberal arts", 9, "undergraduate");
        Student Isaiah = new StudentCollege("Isaiah", "Cheville", "2762890", "domestic", "Engineering", 1, "open enrolled");
        Student Cory = new StudentCollege("Cory", "in the House", "2762890", "domestic", "Engineering", 5, "open enrolled");
        Student Gabe = new StudentCollege("Gabe", "Newell", "2762890", "foreign", "Engineering", 4, "Graduate");
        Student Eric = new StudentCollege("Eric", "Smith", "2762890", "foreign", "liberal arts", 13, "Graduate");
        Student John = new StudentCollege("John", "Smith", "2762890", "foreign", "Engineering", 20, "undergraduate");
        Student Brady = new StudentCollege("Brady", "Smith", "2762890", "foreign", "liberal arts", 19, "undergraduate");
        Student Jason = new StudentCollege("Jason", "Slowinski", "2762890", "foreign", "Engineering", 7, "open enrolled");
        Student Wyeth = new StudentCollege("Wyeth", "Brown", "2762890", "foreign", "Engineering", 3, "open enrolled");

        Student.setBaseClassHourTuitionRate(350);
        System.out.println(Jonothan.toString());
        System.out.println(Jones.toString());
        System.out.println(Jeremy.toString());
        System.out.println(Jeremiah.toString());
        System.out.println(Julie.toString());
        System.out.println(Jack.toString());
        System.out.println(Darius.toString());
        System.out.println(Alex.toString());
        System.out.println(Alexis.toString());
        System.out.println(Hans.toString());
        System.out.println(Isaiah.toString());
        System.out.println(Cory.toString());
        System.out.println(Gabe.toString());
        System.out.println(Eric.toString());
        System.out.println(John.toString());
        System.out.println(Brady.toString());
        System.out.println(Jason.toString());
        System.out.println(Wyeth.toString());

        Student.setBaseClassHourTuitionRate(200);
        System.out.println("FROM BOARD OF REGENTS: TUITION HAS BEEN LOWERED TO $200");

        System.out.println(Jonothan.toString());
        System.out.println(Jones.toString());
        System.out.println(Jeremy.toString());
        System.out.println(Jeremiah.toString());
        System.out.println(Julie.toString());
        System.out.println(Jack.toString());
        System.out.println(Darius.toString());
        System.out.println(Alex.toString());
        System.out.println(Alexis.toString());
        System.out.println(Hans.toString());
        System.out.println(Isaiah.toString());
        System.out.println(Cory.toString());
        System.out.println(Gabe.toString());
        System.out.println(Eric.toString());
        System.out.println(John.toString());
        System.out.println(Brady.toString());
        System.out.println(Jason.toString());
        System.out.println(Wyeth.toString());


    }
}

