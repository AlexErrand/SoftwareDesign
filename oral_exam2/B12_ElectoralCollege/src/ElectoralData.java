import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
/**
 * The ElectoralData class is my hub for all information to be used in my JavaFX program.
 * I chose to separate it this way because the controller class was getting very cluttered,
 * and I feel this is a better solution.
 * */
public class ElectoralData {
    public LinkedHashMap<String, Party> votes = new LinkedHashMap<>();
    public LinkedHashMap<String,Integer> points = new LinkedHashMap<>();
    public LinkedHashMap<String,Integer> colorValue = new LinkedHashMap<>();


    String[] stateNames = {"Alaska","Alabama","Arkansas", "Arizona","California","Colorado","Connecticut","Washington DC","Delaware",
    "Florida","Georgia","Hawaii","Iowa","Idaho","Illinois","Indiana","Kansas","Kentucky","Louisiana","Massachusetts","Maryland","Maine"
            ,"Michigan","Minnesota","Missouri","Mississippi", "Montana","North Carolina","North Dakota","Nebraska","New Hampshire","New Jersey","New Mexico","Nevada","New York",
    "Ohio","Oklahoma","Oregon","Pennsylvania","Rhode Island","South Carolina",
    "South Dakota","Tennessee","Texas","Utah","Virginia","Vermont","Washington","Wisconsin","West Virginia","Wyoming"}; //Maine 1, Maine 2, Nebraska 1, Nebraska 2, Nebraska 3

    int[] statePoints = {9,3,11,6,55,9,7,3,29,16,4,4,20,11,6,6,8,8,2,10,11,16,10,11,16,10,6,10,3,2,6,4,14,5,29,15,3,18,7,7,20,4,9,3,11,38,6,3,13,12,5,10,3};
    int[] colorValues = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

    /**
     * ElectoralData constructor puts all of the necessary values into their proper LinkedHashMaps,
     * */
    public ElectoralData(){
        for(int i=0; i<stateNames.length; i++){
            points.put(stateNames[i],statePoints[i]);
        }

        for(int j = 0; j<stateNames.length; j++){
            colorValue.put(stateNames[j],colorValues[j]);
        }
    }

    public ObservableList<String> getStates(){
        return FXCollections.observableList(new ArrayList<>(points.keySet()));
    }
    /**
     * getPoints retrievers the number of votes per party at any given time, and is used to update the poll accordingly
     * @param party
     * @return int number of points
     * */
    public int getPoints(Party party) {
        int count = 0;
        for (Map.Entry<String, Party> p : votes.entrySet()) {
            if (p.getValue().equals(party)) {
                if (points.containsKey(p.getKey())) {
                    count += points.get(p.getKey());

                }else{
                    System.out.println("cant find the key " + p.getKey());
                }
            }
        }
        return count;
    }

    /**
     * vote takes in a state and party and places a vote into the vote hashmap accordingly
     * @param party the party associated with the state
     * @param state the state who aligned with one of three parties
     * */
    public void vote(String state, Party party){
        this.votes.put(state, party);
    }

}
