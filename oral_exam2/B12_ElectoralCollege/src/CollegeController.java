import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;

public class CollegeController {

    //declaring the state string null to be used later in different methods, as well as the initial URL so the map is present on startup
    String state = null;
    String linkToGoogle = "https://chart.googleapis.com/chart?cht=t&chs=440x220&chtm=usa&chco=<colors>&chf=bg,s,EAF7FE,chld=ALAKAZARCACOCTDEFLGAHIIDILINIAKSKYLAMEMDMAMIMNMSMOMTNENVNHNJNMNYNCNDOHOKORPARISCSDTNTXUTVTVAWAWVWIWY,chd=t:0,100,50,chco=FFFFFF,FF0000,00FF00";
    URL url = new URL(linkToGoogle);

    @FXML
    public ToggleGroup votingButtons;
    //the group of radiobuttons I used to control the votes in the election, only one can be selected at a time


    @FXML
    private RadioButton democraticRB;//radiobutton for democratic party

    @FXML
    private RadioButton republicanRB;//radiobutton for republican party

    @FXML
    private RadioButton undecidedRB;//radiobutton for undecided

    @FXML
    private Label demoVoteStatus; //next labels are for the voting status of each state

    @FXML
    private Label repVoteStatus;

    @FXML
    private Label undVoteStatus;

    @FXML
    private ImageView votingMap; //ImageView is used with a URL to get an image from the map

    @FXML
    private PieChart pieChart; //piechart that does into top left corner

    @FXML
    private ChoiceBox<String> listOfStates;

    @FXML
    private Label electionStatus;

    public ElectoralData electoralData = new ElectoralData();

    public CollegeController() throws MalformedURLException {
    }


    /**
     * initialize is called on startup and sets visibility and values of the listOfStates
     * and the pieChart, which have to be handled through initialize otherwise errors occur
     * */
    @FXML
    public void initialize() {
        listOfStates.setItems(electoralData.getStates());
        listOfStates.setOnAction((event)->{onDropdown();}); //anonymous function
        pieChart.setTitle("Electoral Data");
        pieChart.setVisible(true);
        pieChart.setClockwise(true);
        pieChart.setLabelLineLength(50);
        pieChart.setLabelsVisible(true);
        pieChart.setStartAngle(180);

        initImageURL(); //do not uncomment if not connected to the internet
    }

    //this will cause an error when not connected to the internet
    /**
     * initImageURL sets up the URL that generates the map in the bottom left corner of the window.
     * */
    public void initImageURL() {
        try {
            BufferedImage image = ImageIO.read(url);
            votingMap.setImage(SwingFXUtils.toFXImage(image, null));

        } catch (Exception e) {
        throw new RuntimeException(e);
    }
    }

    /**
     * updateImageURL is called every time a state is voted for,
     * and updates the current URL with a new URL generated to accommodate the newly
     * voted on state.
     * */
    public void updateImageURL() throws MalformedURLException {
        state = listOfStates.getValue();
        StringBuilder finalEncoding = new StringBuilder();

        if (electoralData.votes.get(state) == Party.Democratic) {
            electoralData.colorValue.put(state, 50);
        }
        if (electoralData.votes.get(state) == Party.Republican) {
            electoralData.colorValue.put(state, 100);
        }


        electoralData.colorValue.entrySet().forEach(entry -> finalEncoding.append(entry.getValue()).append(","));

        String toOutput = finalEncoding.substring(0,finalEncoding.length()-1);

        //I decided to forego the extra parts of Maine and Nebraska as it was causing some bugs that ruined the integrity of the program.

        linkToGoogle = "https://chart.googleapis.com/chart?cht=t&chs=440x220&chtm=usa&chf=bg,s,EAF7FE,chld=AZARCACOCTDEDCFLGAHIIDILINIAKSKYLAMEMDMAMIMNMSMOMTNENVNHNJNMNYNCNDOHOKORPARISCSDTNTXUTVTVAWAWVWIWY&chd=t:"+ toOutput + "&chco=FFFFFF,FFFFFF,00FFFF,FF0000";
        URL urlUpdate = new URL(linkToGoogle);

        try {
            BufferedImage image = ImageIO.read(urlUpdate);
            votingMap.setImage(SwingFXUtils.toFXImage(image, null));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }



    /**
     * onDropdown updates the votes and makes sure if a vote is already selected for a state, it will
     * appear if select it again.
     * */
    @FXML
    private void onDropdown() {
        state = listOfStates.getValue();
        democraticRB.setSelected(electoralData.votes.get(state)==Party.Democratic);
        republicanRB.setSelected(electoralData.votes.get(state)==Party.Republican);
        undecidedRB.setSelected(electoralData.votes.get(state)==Party.Undecided);
    }
    /**
     * onDemSelected takes in an ActionEvent and puts a new vote into the vote Hashmap.
     * it also updates the VoteCounter and updates the voting map
     * @param event from clicking on a radiobutton
     * */

    @FXML
    private void onDemSelected(ActionEvent event) throws MalformedURLException {
        if(state == null){
            return;
        }
        electoralData.vote(state,Party.Democratic);
        updateVoteCounter();
        updateImageURL();

    }


    /**
     * onRepSelected takes in an ActionEvent and puts a new vote into the vote Hashmap.
     * it also updates the VoteCounter and updates the voting map
     * @param event from clicking on a radiobutton
     * */

    @FXML
    private void onRepSelected(ActionEvent event) throws MalformedURLException {
        if(state == null){
            return;
        }

        electoralData.vote(state,Party.Republican);
        updateVoteCounter();
        updateImageURL();

    }
    /**
     * onUndSelected takes in an ActionEvent and puts a new vote into the vote Hashmap.
     * it also updates the VoteCounter and updates the voting map
     * @param event from clicking on a radiobutton
     * */
    @FXML
    private void onUndSelected(ActionEvent event) throws MalformedURLException {
        if(state == null){
            return;
        }
        electoralData.vote(state,Party.Undecided);
        updateVoteCounter();
        updateImageURL();
    }

    /**
     * updateVoteCounter updates the labels that contain the current total of points in the election.
     * It also updates the piechart and checks for a winner of the election.
     * */
    private void updateVoteCounter(){
        demoVoteStatus.setText("Democratic Votes: " + electoralData.getPoints(Party.Democratic));
        repVoteStatus.setText("Republican Votes: " + electoralData.getPoints(Party.Republican));
        undVoteStatus.setText("Undecided Votes: " + electoralData.getPoints(Party.Undecided));
        ObservableList<PieChart.Data> pChartData = FXCollections.observableArrayList(
                new PieChart.Data("Democratic Votes", electoralData.getPoints(Party.Democratic)),
                new PieChart.Data("Republican Votes", electoralData.getPoints(Party.Republican)));
                new PieChart.Data("Undecided Votes", electoralData.getPoints(Party.Undecided));
        pieChart.setData(pChartData);
        whoWonState();
    }


    /**
     * whoWonState determines a winner of the election once votes exceed 270.
     * It is called whenever the vote counter is updated
     * */
    private void whoWonState(){
        if(electoralData.getPoints(Party.Democratic) >= 270){
            electionStatus.setText("Election Status: Democrats Win!");
        }
        if(electoralData.getPoints(Party.Republican) >= 270){
            electionStatus.setText("Election Status: Republicans Win!");
        }
        if (electoralData.getPoints(Party.Republican) < 270 && electoralData.getPoints(Party.Democratic) < 270) {
            electionStatus.setText("Election Status: Ongoing");
        }

    }
}




