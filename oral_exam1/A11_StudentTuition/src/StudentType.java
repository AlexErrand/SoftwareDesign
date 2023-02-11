import java.text.DecimalFormat;

public class StudentType extends StudentResidence{ //decided to extend StudentType from StudentResidence in order to apply the correct rate if the student
                                                   //was over the amount of hours for a full load
    private final String studentStatus;
    public boolean fullLoadExceed;
    private final int numberOfHoursRegistered;

    public StudentType(String firstName, String lastName, String studentID,String placeOfResidence, int numberOfHoursRegistered, String studentStatus) {
        super(firstName, lastName, studentID, placeOfResidence);
        //included exceptions for invalid inputs
        if (!studentStatus.equalsIgnoreCase("graduate") && !studentStatus.equalsIgnoreCase("undergraduate") && !studentStatus.equalsIgnoreCase("open enrolled")){
            throw new IllegalArgumentException("Not a valid type of student status");
        }

        if (numberOfHoursRegistered < 1){
            throw new IllegalArgumentException("Student cannot have less than 1 hour and be enrolled");
        }

        this.studentStatus = studentStatus;
        this.numberOfHoursRegistered = numberOfHoursRegistered;
    }

    public String getStudentStatus(){
        return studentStatus;
    }

    public int getNumberOfHoursRegistered(){return numberOfHoursRegistered;}

    @Override
    public double tuition() { //used abstract implementation of tuition to take the previous value of it in StudentResidence and apply it here as well
        if((getStudentStatus().equalsIgnoreCase("Graduate") && getNumberOfHoursRegistered() > 9) || (getStudentStatus().equalsIgnoreCase("Undergraduate") && getNumberOfHoursRegistered() >15) || (getStudentStatus().equalsIgnoreCase("Open Enrolled") && getNumberOfHoursRegistered() >3)){
            fullLoadExceed = true; //this if statement checks for if fullLoad is exceeded
        }
        if (super.getPlaceOfResidence().equalsIgnoreCase("Iowa") && fullLoadExceed) { //fullLoad does not need == true and can be simplified to just fullLoad
            return ((super.tuition() + (super.tuition()*0.35)) - super.tuition()*0.1);
        }
        if (super.getPlaceOfResidence().equalsIgnoreCase("Domestic") && fullLoadExceed) {//these three if statements correct the discount from StudentResidence if needed
            return super.tuition();
        }
        if (super.getPlaceOfResidence().equalsIgnoreCase("Foreign") && fullLoadExceed) {
            return ((super.tuition() - (super.tuition()*0.1)));
        }
        return super.tuition();
    }

    @Override
    public String toString() { //toString method to return a string representation of StudentType
        DecimalFormat dForm = new DecimalFormat("0.00");
        return String.format("|||||Name:%s %s || studentid: %s || hours registered: %s || residency: %s || Status: %s || final fee: %s",
                super.getFirstName(), super.getLastName(), super.getStudentID(), getNumberOfHoursRegistered(), super.getPlaceOfResidence(), getStudentStatus(), dForm.format(tuition()));
    }






}
