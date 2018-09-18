package com.company.Model;

import java.util.ArrayList;

public class Model
{

    FileManager fileManager;
    Tournament tournament;

    public Model()
    {
        fileManager = new FileManager();
        tournament = new Tournament();
    }


    /**
     * Reads and processes the file containing team names
     * @param fileName file containing the team names
     * @return returns with success flag or error message
     */
    public ModelFunctionSuccessResponse readTeamFile(String fileName)
    {
        //Read the raw textfile
        ModelFunctionSuccessResponse result = fileManager.readTeamsFile(fileName);

        if(result.success == false)
        {
            return result;
        }
        else
        {
            //Successful file reading

            //Generate Team objects from the raw text
            tournament.instantiateTeams(fileManager.getTeamsInputFileContent());
            //Generate game matches between teams
            tournament.generateMatchList();

            return new ModelFunctionSuccessResponse();
        }

    }

    public ModelFunctionSuccessResponse readMatchResultFile(String fileName)
    {
        //Read the raw textfile
        ModelFunctionSuccessResponse result = fileManager.readMatchResultsFile(fileName);

        if(result.success == false)
        {
            return  result;
        }
        else
        {
            //Successful file reading. Process match results
            tournament.processMatchResultsFile(fileManager.getMatchResultFileContent());
            return result;
        }
    }

    /**
     * Retrieves the list of team names
     * @return List of team names as a string arraylist
     */
    public ArrayList<String> getListOfTeamNames()
    {
        return tournament.getAllTeamNames();
    }

    /**
     * Withdraws a team from the tournament and regenerates the match list
     * @param teamName Name of the team to withdraw
     * @return Returns if the operation was successful or if error occured.
     */
    public ModelFunctionSuccessResponse withDrawTeam(String teamName)
    {
        ModelFunctionSuccessResponse result =  tournament.withDrawTeam(teamName);
        if(result.success == false)
        {
            return result;
        }
        else
        {
            tournament.generateMatchList();
            return result;
        }
    }

    /**
     * Returns the number of matches in the turnament
     * @return number of matches in the tournament
     */
    public int getNumberOfMatches()
    {
        return tournament.getNumberOfMatches();
    }

    /**
     * Returns the matches between the teams as a string.
     * The string contains the name of the teams and the results if available.
     * Example: "TeamA VS TeamB (No results yet)"
     * @return list of matches as string
     */
    public ArrayList<String> getResultsAsString()
    {
        return tournament.getMatchesAsString();
    }
}
