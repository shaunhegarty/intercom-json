import org.json.simple.JSONObject;

public class IntercomCustomer implements Comparable<IntercomCustomer> {

    private long user_id;
    private double latitude;
    private double longitude;
    private String name;

    public long getUserId() {
        return user_id;
    }

    public void setUserId(long user_id) {
        this.user_id = user_id;
    }

    /**
     * Get latitude in degrees
     * @return latitude in degrees
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Set latitude in degrees
     * @param latitude in degrees
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Get longitude in degrees
     * @return longitude in degrees
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Set longitude in degrees
     * @param longitude in degrees
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public CoordinateLocation getCoordinates(){
        return new CoordinateLocation(getLatitude(), getLongitude());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private IntercomCustomer(){
        //private constructor
    }

    public static IntercomCustomer buildCustomer(JSONObject jsonCustomer){
        IntercomCustomer customer = new IntercomCustomer();
        customer.setLatitude(Double.valueOf((String) jsonCustomer.get("latitude")));
        customer.setLongitude(Double.valueOf((String) jsonCustomer.get("longitude")));
        customer.setName((String) jsonCustomer.get("name"));
        customer.setUserId((Long) jsonCustomer.get("user_id"));
        return customer;
    }

    /**
     * Sort by user id
     * @param intercomCustomer Takes an Intercom Customer
     * @return 0 - equal, negative number = less, positive = more
     */
    @Override
    public int compareTo(IntercomCustomer intercomCustomer) {
        return Long.compare(this.getUserId(), intercomCustomer.getUserId());
    }

    @Override
    public String toString(){
        return getUserId() + " : " + getName();
    }
}
