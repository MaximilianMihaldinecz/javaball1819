package com.company;


//Constants, default values, magic numbers
public class Configurations {

    //File paths
    public static final String DefaultTeamsFileName = "TeamsIn.txt";
    public static final String DefaultMatchResultsInputFileName = "ResultsIn.txt";
    public static final String DefaultMatchResultsOutputFilename = "ResultsOut.txt";

    //Magic numbers
    public static final int MinimumNumberOfTeamsAllowed = 3;

    //Error messages
    public static final String Error_FileRead = "Error while trying to read the following file: ";
    public static final String Error_LessThanMinimumTeams = "The file contains less than the minimum number of allowed teams.";
    public static final String Error_ProgramWillExit = "This program will exit after this message. Thank you.";


}
