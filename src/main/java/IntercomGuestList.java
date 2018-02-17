import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IntercomGuestList {

    private List<IntercomCustomer> guestList = new ArrayList<>();

    public List<IntercomCustomer> buildGuestList(String json, double partyRadius, CoordinateLocation partyLocation) {
        this.guestList = new ArrayList<>();
        JSONParser parser = new JSONParser();

        try{
            Object obj = parser.parse(json);
            JSONArray array = (JSONArray)obj;

            for(Object jsonObject : array){
                JSONObject customer = (JSONObject) jsonObject;
                IntercomCustomer intercomCustomer = IntercomCustomer.buildCustomer(customer);
                if(getDistanceToParty(intercomCustomer, partyLocation) < partyRadius){
                    this.guestList.add(intercomCustomer);
                }
            }

        }catch(ParseException pe){

            System.out.println("position: " + pe.getPosition());
            System.out.println(pe);
        }

        Collections.sort(guestList);
        return guestList;

    }

    public List<IntercomCustomer> getGuestList(){
        return this.guestList;
    }

    public double getDistanceToParty(IntercomCustomer customer, CoordinateLocation partyLocation){
        return CoordinateLocation.getDistance(customer.getCoordinates(), partyLocation, CoordinateLocation.EARTH_RADIUS);
    }
}
