import oop.ex3.searchengine.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.lang.Math;
import java.util.Arrays;

/**
 * This class represents a partial implementation of the Booping website, a new hotel booking site.
 */
public class BoopingSite{

    // Name of the text file that is used as a data set.
    private String dataSet;

    // A comparator that compares by Hotel starRating in a descending order, from the highest starRating
    // to the lowest starRating.
    // Hotels with the same starRating will be sorted according to the alphabet order of the hotel's name.
    private Comparator<Hotel> compareByStar = new Comparator<Hotel>() {
        @Override
        public int compare(Hotel o1, Hotel o2) {
            if (o1.getStarRating() > o2.getStarRating()){
                return -1; // reverse order
            } else if (o1.getStarRating() < o2.getStarRating()){
                return 1;
            } else {return (o1.getPropertyName().compareTo(o2.getPropertyName()));}
        }};

    /**
     Initializes the data set name.
     * @param name name of the text file that is used as a data set.
     */
    public BoopingSite(String name) { dataSet = name; }

    /**
     * A method to convert an ArrayList of Hotel Objects into a simple java Array of Hotel objects.
     * @param hotelList ArrayList of Hotel objects.
     * @return Array of Hotel objects.
     */
    private Hotel[] convertListToArray (ArrayList<Hotel> hotelList){
        Hotel[] arr = new Hotel[hotelList.size()];
        arr = hotelList.toArray(arr);
        return arr;
    }

    /**
     * Finds all hotels located in the given city.
     * @param city a string representing a city name
     * @return An ArrayList of all hotel objects in the given city.
     */
    private ArrayList<Hotel> findHotelsInCity(String city) {
        Hotel[] hotelsInDataSet = HotelDataset.getHotels(dataSet);
        ArrayList<Hotel> inCityList = new ArrayList<>();
        for (Hotel hotel : hotelsInDataSet) {
            if (hotel.getCity().equals(city)) {
                inCityList.add(hotel);
            }
        }
        return inCityList; }

    /**
     * This method returns an array of hotels located in the given city, sorted from the highest star-rating
     * to the lowest.
     * Hotels that have the same rating will be organized according to the alphabet order of the
     * hotel's name. In case here are no hotels in the given city, this method returns an empty array.
     * @param city a string representing a city name
     * @return an array of Hotel objects located within the given city, sorted by starRating and hotel's name
     * as depicted.
     */
    public Hotel[] getHotelsInCityByRating(String city) {
        ArrayList<Hotel> inCityList = findHotelsInCity(city); // find all hotels in city
        inCityList.sort(compareByStar); // sort by starRating and then property name.
        Hotel[] inCityArr = convertListToArray(inCityList); // convert to an array
        return inCityArr;
    }

    /**
     * This method returns the euclidean distance between a given point and a given hotel's location.
     * @param latitude an angle, double
     * @param longitude a measurement, double
     * @param hotel Hotel object
     * @return the euclidean distance between the given point and given hotel.
     */
    private double getDistanceFrom(double latitude, double longitude, Hotel hotel){
        double hotel_latitude = hotel.getLatitude();
        double hotel_longitude = hotel.getLongitude();
        double latitudeDistance = Math.pow((latitude - hotel_latitude), 2);
        double longitudeDistance = Math.pow((longitude - hotel_longitude), 2);
        double distanceFrom = Math.sqrt(latitudeDistance + longitudeDistance);
        return distanceFrom;
    }

    /**
     * A helper method which compares and sorts a given array of Hotel object using the local comparator
     * defined within the method.
     * @param latitude an angle, double
     * @param longitude a measurement, double
     * @param hotelsArray an array of Hotel objects
     * @return The given array, sorted by euclidean distance and then by number of points of interest.
     */
    private Hotel[] compareByProximity(double latitude, double longitude, Hotel[] hotelsArray){
        // check for invalid coordinates
        if (latitude > 90 || latitude < -90){ return new Hotel[0];}
        if (longitude > 180 || longitude < -180){ return new Hotel[0];}

        Comparator<Hotel> compareByDistance = new Comparator<Hotel>() {
            @Override
            public int compare(Hotel o1, Hotel o2) {
                if (getDistanceFrom(latitude, longitude, o1) > getDistanceFrom(latitude, longitude, o2)){
                    return 1; // descending order, highest to lowest
                } else if (getDistanceFrom(latitude, longitude, o1) < getDistanceFrom(latitude, longitude, o2)){
                    return -1;
                } else { // same distance, compare by number of points of interest
                    if (o1.getNumPOI() > o2.getNumPOI()){
                    return -1; // decreasing order
                } else if (o1.getNumPOI() < o2.getNumPOI()){
                    return 1;
                } else {return 0;}}
            }};
        ArrayList<Hotel> hotelsList =  new ArrayList<>(Arrays.asList(hotelsArray)); // convert to arraylist
        hotelsList.sort(compareByDistance); // sort arraylist
        Hotel[] sortedHotelsArray = convertListToArray(hotelsList); // convert to array
        return sortedHotelsArray;
    }

    /**
     * This method
     * returns an array of hotels, sorted according to their euclidean distance from the given geographic
     * location, in ascending order.
     * Hotels that are at the same distance from the given location are organized according to the number
     * of points-of-interest for which they are close to (in a decreasing order).
     * In case of illegal input, this method returns an empty array.
     * @param latitude an angle, double
     * @param longitude a measurement, double
     * @return Hotel objects sorted as depicted.
     */
    public Hotel[] getHotelsByProximity(double latitude, double longitude){
        Hotel[] hotelsInDataSet = HotelDataset.getHotels(dataSet); // an array of all hotels in data set
        return compareByProximity(latitude, longitude, hotelsInDataSet); // return the sorted array
        }

    /**
     * This method returns an array of hotels in the given city, sorted according to their euclidean
     * distance from the given geographic location, in ascending order.
     * Hotels that are at the same distance from the given location are organized according to the
     * number of points-of-interest for which they are close to (in a decreasing order).
     * In case of illegal input, this method returns an empty array.
     * @param city a string representing a city name
     * @param latitude an angle, double
     * @param longitude a measurement, double
     * @return Hotel objects sorted as depicted.
     */
   public Hotel[] getHotelsInCityByProximity(String city, double latitude, double longitude){
        ArrayList<Hotel> inCityList = findHotelsInCity(city); // an arraylist of all hotels in city
        Hotel[] inCityArray = convertListToArray(inCityList); // convert to an array
        return compareByProximity(latitude, longitude, inCityArray); // return the sorted array
    }

}