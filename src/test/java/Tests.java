import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class Tests {

    private static final CoordinateLocation intercomLocation = new CoordinateLocation(53.339428, -6.257664);

    @Test
    public void testDistance() {


        CoordinateLocation dublin = new CoordinateLocation(53.3353, -6.2614);
        CoordinateLocation letterkenny = new CoordinateLocation(54.9473, -7.7301);
        CoordinateLocation cork = new CoordinateLocation(51.8975, -8.4777);

        CoordinateLocation northPole = new CoordinateLocation(90, 0);
        CoordinateLocation southPole = new CoordinateLocation(-90, 0);

        double lkToDublin = CoordinateLocation.getDistance(letterkenny, dublin, CoordinateLocation.EARTH_RADIUS);
        double dublinToLk = CoordinateLocation.getDistance(dublin, letterkenny, CoordinateLocation.EARTH_RADIUS);
        Assert.assertEquals(lkToDublin, dublinToLk, 1); //Check that order doesn't matter

        double corkToDublin = CoordinateLocation.getDistance(cork, dublin, CoordinateLocation.EARTH_RADIUS);
        //Check order of magnitude with known values (geodistance)
        Assert.assertEquals(203000.0, lkToDublin, 1000);
        Assert.assertEquals(219000.0, corkToDublin, 1000);

        double corkToLk = CoordinateLocation.getDistance(cork, letterkenny, CoordinateLocation.EARTH_RADIUS);
        Assert.assertEquals(343000.0, corkToLk, 1000);


        double poleToPole = CoordinateLocation.getDistance(northPole, southPole, CoordinateLocation.EARTH_RADIUS);
        //Earth Circumference will be 2 * pi * radius, so pole to pole distance will be half that
        Assert.assertEquals(Math.PI * CoordinateLocation.EARTH_RADIUS, poleToPole, 1);
    }

    @Test
    public void testGuestList() {
        IntercomGuestList guestList = new IntercomGuestList();
        String json = getTestJsonSingle();
        guestList.buildGuestList(json, 10000, intercomLocation);
        Assert.assertEquals(0, guestList.getGuestList().size());

        guestList.buildGuestList(json, 1000000, intercomLocation);
        Assert.assertEquals(1, guestList.getGuestList().size());

        json = getTestJsonDuplicate();
        guestList.buildGuestList(json, 10000, intercomLocation);
        Assert.assertEquals(0, guestList.getGuestList().size());

        guestList.buildGuestList(json, 1000000, intercomLocation);
        Assert.assertEquals(3, guestList.getGuestList().size());

        json = getTestJsonMultiple();
        guestList.buildGuestList(json, 10000, intercomLocation);
        Assert.assertEquals(0, guestList.getGuestList().size());

        guestList.buildGuestList(json, 200000, intercomLocation);
        Assert.assertEquals(3, guestList.getGuestList().size());


    }

    private String getTestJsonSingle(){
        JSONArray customers = new JSONArray();
        JSONObject customer = new JSONObject();
        customer.put("latitude", "52.986375");
        customer.put("user_id", 12);
        customer.put("name" , "Christina McArdle");
        customer.put("longitude", "-6.043701");
        customers.add(customer);

        return customers.toJSONString();
    }


    private String getTestJsonDuplicate(){
        JSONArray customers = new JSONArray();
        JSONObject customer = new JSONObject();
        customer.put("latitude", "52.986375");
        customer.put("user_id", 12);
        customer.put("name" , "Christina McArdle");
        customer.put("longitude", "-6.043701");
        customers.add(customer);
        customers.add(customer);
        customers.add(customer);
        return customers.toJSONString();
    }

    private String getTestJsonMultiple() {
        JSONArray customers = new JSONArray();
        JSONObject customer = new JSONObject();
        customer.put("latitude", "52.986375");
        customer.put("longitude", "-6.043701");
        customer.put("user_id", 3);
        customer.put("name" , "Person One");
        customers.add(customer);

        customer = new JSONObject();
        customer.put("latitude", "51.986375");
        customer.put("longitude", "-6.043701");
        customer.put("user_id", 2);
        customer.put("name" , "Person Two");
        customers.add(customer);

        customer = new JSONObject();
        customer.put("latitude", "53.986375");
        customer.put("longitude", "-6.043701");
        customer.put("user_id", 1);
        customer.put("name" , "Person Three");
        customers.add(customer);

        customer = new JSONObject();
        customer.put("latitude", "56.986375");
        customer.put("longitude", "-5.043701");
        customer.put("user_id", 4);
        customer.put("name" , "Person Four");
        customers.add(customer);


        return customers.toJSONString();
    }
}
