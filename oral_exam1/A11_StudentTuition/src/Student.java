import java.text.DecimalFormat;

public abstract class Student { //abstract to provide common implementation to subclasses, specifically the abstract double tuition().
    private final String firstName;
    private final String lastName;
    private final String studentID;
    public static double baseClassHourTuitionRate;

    public Student(String firstName, String lastName, String studentID){
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentID = studentID;

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStudentID() {
        return studentID;
    }


    /**tuition() returns the calculated tuition of a student based on how the method has
     * been implemented
     *
     * @return value of student tuition
     */
    public abstract double tuition();

    public double getBaseClassHourTuitionRate() {
        return baseClassHourTuitionRate;
    }

    public static void setBaseClassHourTuitionRate(double newRate) {
       baseClassHourTuitionRate = newRate;
    }

    /**
     * toString method to return object values as a string
     * @return string of object values of student, depending on method implementation
     */
    @Override
    public String toString() {
        DecimalFormat dForm = new DecimalFormat("0.00");
        return String.format("first name:%s last name:%s studentid: %s initial rate for student:%s",
                getFirstName(), getLastName(), getStudentID(), dForm.format(getBaseClassHourTuitionRate()));
    }

}
