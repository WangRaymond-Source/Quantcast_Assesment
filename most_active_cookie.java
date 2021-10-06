/**
 * This program is to determine and output all of the most active cookies given
 * a specifically formatted cookie log file and a date.
 */
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.ArrayList;

public class most_active_cookie{
    //constants for arguments
    private static int CSV_FILE_CONST = 0;
    private static int FLAG_CONST = 1;
    private static int DATE_CONST = 2;

    public static void main(String[] args) throws Exception {
        //make sure only 3 arguments
        if(args.length != 3){
            throw new IllegalArgumentException("3 arguments required: *file_name*.CSV -d [date]");
        }
        //check for flag
        if(!args[FLAG_CONST].equals("-d")){
            throw new InvalidParameterException("Missing flag: -d ");
        }
        //gets the path of the file to parse
        Path filePath = Paths.get(args[CSV_FILE_CONST]);
        //used to help parse the cookie file
        Parse_File cookieDataFile = new Parse_File(filePath.toAbsolutePath().toString());

        ArrayList<String> activeCookies = findActiveCookies(cookieDataFile, args[DATE_CONST]);
        //output results by printing them on command line
        for(String cookies : activeCookies){
            System.out.println(cookies);
        }
    }

    /**
     * findActiveCookies() is a function that is used to find all of the active
     * cookies in a given date and a give data file of cookies and dates.
     * @param cookieDataFile
     * @param date
     * @return List of cookies active on a particular date
     */
    public static ArrayList<String> findActiveCookies(Parse_File cookieDataFile, String date){
        //HashMap to help store the frequency of each cookie at a specific date
        HashMap<String,Integer> cookieFreqs = new HashMap<>();
        int highestFrequency = 0;      //stores the highest frequency of a cookie on a specific date
        //help store all of the most active cookies
        ArrayList<String> cookieList = new ArrayList<>();
        //parse each line from cookie log
        while(cookieDataFile.hasNextLine()){
            cookieDataFile.readNextLine();
            if(cookieDataFile.getDate().equals(date)){
                //current cookie being read
                String currCookie = cookieDataFile.getCookie();
                //store its frequency
                cookieFreqs.put(currCookie, cookieFreqs.getOrDefault(currCookie,0) + 1);

                //store the cookie if it contains the same highest frequency
                if(cookieFreqs.get(currCookie) == highestFrequency) {
                    cookieList.add(currCookie);
                    /*if frequency of the current cookie has a high freq
                    then empty all active cookies and add the current cookie
                    to list*/
                }else if(cookieFreqs.get(currCookie) > highestFrequency){
                    highestFrequency = cookieFreqs.get(currCookie);
                    cookieList.clear();
                    cookieList.add(currCookie);
                }
            }
        }
        return cookieList;
    }
}
