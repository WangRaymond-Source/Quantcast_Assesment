import java.io.*;
import java.util.Scanner;

/**
 * The purpose of this class to help parse anything speciifc from a file
 */
public class Parse_File {
    //constants
    private static int COOKIE_START_INDEX_CONST = 0;
    private static int COOKIE_END_INDEX_CONST = 16;
    private static int DATE_START_INDEX_CONST = 17;
    private static int DATE_END_INDEX_CONST = 27;
    //instances
    private Scanner cookieDataFile;
    private String currLine;
    private String date;
    private String cookie;

    public Parse_File(String fileName) throws Exception{
        cookieDataFile = new Scanner(new File(fileName));
    }
    public boolean hasNextLine(){
        return cookieDataFile.hasNext();
    }
    public void readNextLine(){
        currLine = cookieDataFile.next();
    }
    public String getCookie(){
        return currLine.substring(COOKIE_START_INDEX_CONST,COOKIE_END_INDEX_CONST);
    }
    public String getDate(){
        return currLine.substring(DATE_START_INDEX_CONST,DATE_END_INDEX_CONST);
    }

}
