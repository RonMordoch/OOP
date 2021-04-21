import oop.ex3.searchengine.*;
import org.junit.*;
import org.junit.Assert;

public class BoopingSiteTest {

    private static String dataSet = "hotel_dataset.txt";
    private static String shortDataSet = "hotels_tst1.txt";
    private static String emptyDataSet = "hotels_tst2.txt";

    private BoopingSite boop1 = new BoopingSite(dataSet);
    private BoopingSite boop2 = new BoopingSite(shortDataSet);
    private BoopingSite boop3 = new BoopingSite(emptyDataSet);
    private Hotel[] emptyArray = new Hotel[0]; // no hotels expected


    // Method to calculate distance
    private double getDistanceFrom(double latitude, double longitude, Hotel hotel){
        double hotel_latitude = hotel.getLatitude();
        double hotel_longitude = hotel.getLongitude();
        double latitudeDistance = Math.pow((latitude - hotel_latitude), 2);
        double longitudeDistance = Math.pow((longitude - hotel_longitude), 2);
        double distanceFrom = Math.sqrt(latitudeDistance + longitudeDistance);
        return distanceFrom;
    }

    /**
     * Method to test getHotelsInCityByRating.
     */
    @Test
    public void checkGetHotelsInCityByRating1(){
        Hotel[] hotelsInGoa = boop1.getHotelsInCityByRating("goa");
        for (int i=0; i<hotelsInGoa.length - 1 ;i++) {
            Assert.assertTrue(hotelsInGoa[i].getStarRating() >= hotelsInGoa[i + 1].getStarRating());
            Assert.assertFalse(hotelsInGoa[i].getPropertyName().
                    compareTo(hotelsInGoa[i+1].getPropertyName()) == 0);
        }
    }

    /**
     * Method to test getHotelsInCityByRating.
     */
    @Test
    public void checkGetHotelsInCityByRating2(){
        Hotel[] emptyHotelsArray = boop3.getHotelsInCityByRating("goa");
        Assert.assertArrayEquals(emptyArray, emptyHotelsArray);
    }

    /**
     * Method to test getting hotels by proximity.
     */
    @Test
    public void checkGetHotelsByProximity(){
        Hotel[] hotelsArray = boop1.getHotelsByProximity(0, 0);
        for (int i=0; i < hotelsArray.length - 1; i++ ){
            Assert.assertTrue( getDistanceFrom(0,0, hotelsArray[i]) <=
                    getDistanceFrom(0, 0, hotelsArray[i+1]));
            if (getDistanceFrom(0,0, hotelsArray[i]) ==
                    getDistanceFrom(0, 0, hotelsArray[i+1])) {
                Assert.assertTrue(hotelsArray[i].getNumPOI() >= hotelsArray[i + 1].getNumPOI());
            }
        }
    }

    /**
     * Method to test getting hotels in city by proximity.
     */
    @Test
    public void checkGetHotelsInCityByProximity(){
        Hotel[] hotelsArray = boop2.getHotelsInCityByProximity("bhuj", 0, 0);
        for (int i=0; i < hotelsArray.length - 1; i++ ){
            Assert.assertTrue( getDistanceFrom(0,0, hotelsArray[i]) <=
                    getDistanceFrom(0, 0, hotelsArray[i+1]));
            if (getDistanceFrom(0,0, hotelsArray[i])    ==
                    getDistanceFrom(0, 0, hotelsArray[i+1])){
                Assert.assertTrue(hotelsArray[i].getNumPOI() >= hotelsArray[i+1].getNumPOI());
            }
        }
    }

    /**
     * Method to check adding invalid coordinates to the proximity methods.
     */
    @Test
    public void checkGetHotelsByProximityInvalidInput(){
        Hotel[] hotelsArray1 = boop2.getHotelsInCityByProximity("bhuj", -91, 0);
        Assert.assertArrayEquals(emptyArray, hotelsArray1);
        Hotel[] hotelsArray2 = boop1.getHotelsByProximity(46, -200);
        Assert.assertArrayEquals(emptyArray, hotelsArray2);
    }

    /**
     * Check adding invalid city to city methods.
     */
    @Test
    public void checkAddingInvalidCities(){
        Hotel[] hotelsArray1 = boop2.getHotelsInCityByProximity("huji", 0, 0);
        Hotel[] hotelsArray2 = boop1.getHotelsInCityByRating("moodle");
        Assert.assertArrayEquals(emptyArray, hotelsArray1);
        Assert.assertArrayEquals(emptyArray, hotelsArray2);


    }
}
