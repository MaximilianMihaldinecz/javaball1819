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


    public ModelFunctionSuccessResponse readMatchFile(String defaultTeamsFileName)
    {
        //Read the raw textfile
        ModelFunctionSuccessResponse result = fileManager.readTeamsFile(defaultTeamsFileName);

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
}
