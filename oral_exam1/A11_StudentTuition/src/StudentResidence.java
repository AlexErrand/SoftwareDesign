import java.text.DecimalFormat;

public class StudentResidence extends Student{ //implemented StudentResidence to set the initial rate and extend directly from Student
    private final String placeOfResidence;

    public StudentResidence(String firstName, String lastName, String studentID, String placeOfResidence) {
        super(firstName, lastName,  studentID);

        //checks for invalid input and throws exception if so
        if(!placeOfResidence.equalsIgnoreCase("iowa") && !placeOfResidence.equalsIgnoreCase("domestic") && !placeOfResidence.equalsIgnoreCase("foreign")){
            throw new IllegalArgumentException("Invalid Residency");
        }

        this.placeOfResidence = placeOfResidence;
    }

    public String getPlaceOfResidence(){
        return placeOfResidence;
    }


    @Override
    public double tuition() { //check the place of residence in order to apply the appropriate discount
        if (getPlaceOfResidence().equalsIgnoreCase("iowa")) {
            return getBaseClassHourTuitionRate() - (getBaseClassHourTuitionRate()*0.35);
        }
        if (getPlaceOfResidence().equalsIgnoreCase("domestic")){
            return (getBaseClassHourTuitionRate());
        }
        if (getPlaceOfResidence().equalsIgnoreCase("foreign")){
            return getBaseClassHourTuitionRate() + getBaseClassHourTuitionRate()*0.03;
        }
        return getBaseClassHourTuitionRate();
    }

    @Override
    public String toString() { //toString method to return StudentResidence as a string
        DecimalFormat dForm = new DecimalFormat("0.00");
        return String.format("first name:%s last name:%s studentid: %s residency: %s final fee: %s",
                super.getFirstName(), super.getLastName(), super.getStudentID(), getPlaceOfResidence(), dForm.format(tuition()));
    }


}
