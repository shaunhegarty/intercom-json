public class CoordinateLocation {
    public static final double EARTH_RADIUS = 6.371e6; //mean earth radius

    private final double latitude;
    private final double longitude;

    /**
     *
     * @param latitude in units of degrees
     * @param longitude in units of degrees
     */
    public CoordinateLocation(final double latitude, final double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }


    public double getLatitudeInRads() {
        return latitude * Math.PI / 180;
    }

    public double getLongitudeInRads() {
        return longitude * Math.PI / 180;
    }

    /**
     * Return distance between two locations. Units are the same as those of the sphereRadius
     * @param location1
     * @param location2
     * @param sphereRadius
     * @return
     */
    public static double getDistance(CoordinateLocation location1, CoordinateLocation location2, double sphereRadius){
        double sinLat1 = Math.sin(location1.getLatitudeInRads());
        double sinLat2 = Math.sin(location2.getLatitudeInRads());
        double cosLat1 = Math.cos(location1.getLatitudeInRads());
        double cosLat2 = Math.cos(location2.getLatitudeInRads());
        double deltaLong = Math.abs(location1.getLongitudeInRads() - location2.getLongitudeInRads());
        double unitDistance = Math.acos(sinLat1 * sinLat2 + cosLat1 * cosLat2 * Math.cos(deltaLong));
        return sphereRadius * unitDistance;
    }

}
