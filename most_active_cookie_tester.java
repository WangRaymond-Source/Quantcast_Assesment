/**
 * Class most_active_cookie_tester. This class is suppose to test the function
 * of most_active_cookie if it indeed returns all of the most active cookie
 * during a particular date given. We will test using Junit and java utilities.
 */
import org.junit.*;
import static org.junit.Assert.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidParameterException;
import java.util.*;
public class most_active_cookie_tester {
    @Test
    /**
     * check if all of the arguments parameters are met
     */
    public void testMainArguments() throws Exception {
        String[] args = new String[]{"cookie_log.csv", "-d", "2018-12-09", "2018-12-12"};
        String[] args2 = new String[]{"cookie_log.csv"};
        try{
            most_active_cookie.main(args);
            fail();
        }catch(IllegalArgumentException e){
        }
        try{
            most_active_cookie.main(args2);
            fail();
        }catch(IllegalArgumentException e){

        }
    }
    @Test
    /**
     * Check if the -d flag is correct
     */
    public void testFlag()throws Exception{
        String[] args = new String[]{"cookie_log.csv", "-t", "2018-12-09"};
        String[] args2 = new String[]{"cookie_log.csv", "-b", "2018-23-07"};
        try{
            most_active_cookie.main(args);
            fail();
        }catch(InvalidParameterException e){

        }
        try{
            most_active_cookie.main(args2);
            fail();
        }catch(InvalidParameterException e){

        }
    }
    @Test
    /**
     * Test if the function findActiveCookies is returning all of the active
     * cookies on a particular date
     */
    public void testFindAC() throws Exception {
        //gets the path of the file to parse
        Path filePath = Paths.get("cookie_log.csv");
        //used to help parse the cookie file
        Parse_File cookieDataFile = new Parse_File(filePath.toAbsolutePath().toString());
        ArrayList<String> expectedCookies = new ArrayList<>();
        expectedCookies.add("SAZuXPGUrfbcn5UA");
        expectedCookies.add("4sMM2LxV07bPJzwf");
        expectedCookies.add("fbcn5UAVanZf6UtG");
        ArrayList<String> results = most_active_cookie.findActiveCookies(cookieDataFile,"2018-12-08");
        assertEquals(3, results.size());
        for(int i= 0; i < results.size(); i++){
            assertEquals(expectedCookies.get(i), results.get(i));
        }
        //gets the path of the file to parse
        Path filePath2 = Paths.get("cookie_log_test.csv");
        //used to help parse the cookie file
        Parse_File cookieDataFile2 = new Parse_File(filePath2.toAbsolutePath().toString());
        ArrayList<String> expectedCookies2 = new ArrayList<>();
        expectedCookies2.add("432BHJkHjsahjgdh");
        ArrayList<String> results2 = most_active_cookie.findActiveCookies(cookieDataFile2, "2017-13-03");
        assertEquals(1,results2.size());
        for(int i= 0; i < results2.size(); i++){
            assertEquals(expectedCookies2.get(i), results2.get(i));
        }
    }






}
