import java.text.DecimalFormat;

public class StudentCollege extends StudentType {
    //decided to add StudentCollege last because the fee would presumably be added for the specific students college after the initial fees and discounts.
    private final String collegeEnrolled;

    public StudentCollege(String firstName, String lastName, String studentID, String placeOfResidence, String collegeEnrolled, int numberOfHoursRegistered, String studentStatus) {
        super(firstName, lastName, studentID, placeOfResidence, numberOfHoursRegistered, studentStatus );
        //checks if collegeEnrolled is invalid
        if(!collegeEnrolled.equalsIgnoreCase("engineering") && !collegeEnrolled.equalsIgnoreCase("liberal arts")){
            throw new IllegalArgumentException("Student is not enrolled in a valid college");
        }
        this.collegeEnrolled = collegeEnrolled;
    }

    public String getCollegeEnrolled() {
        return collegeEnrolled;
    }

    @Override
    public double tuition() { //the following if statements return the super.tuition() value from StudentType, has to be done this way due to Java limitations and single inheritance
        if ((getCollegeEnrolled().equalsIgnoreCase("Engineering") && (getNumberOfHoursRegistered() > 6))) {
            return super.tuition() + 200;
        }

        if (getCollegeEnrolled().equalsIgnoreCase("Liberal Arts") && (getNumberOfHoursRegistered() > 3)) {
            return super.tuition() + 400;
        }
        return super.tuition();
    }


    @Override
    public String toString() { //toString method for returning StudentCollege as a string
        DecimalFormat dForm = new DecimalFormat("0.00");
        return String.format("|| Name:%s %s || studentid: %s || hours registered: %s || college enrolled: %s || residency: %s || Status: %s || final fee: %s",
                super.getFirstName(), super.getLastName(), super.getStudentID(), super.getNumberOfHoursRegistered(), getCollegeEnrolled(), super.getPlaceOfResidence(), getStudentStatus(), dForm.format(tuition()));
    }


}


