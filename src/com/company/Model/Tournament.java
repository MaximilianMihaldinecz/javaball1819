package com.company.Model;

import com.company.Configurations;
import sun.awt.ConstrainableGraphics;

import java.util.ArrayList;

/**
 * Represents a tournament with matches and teams
 */
public class Tournament {

    ArrayList<Team> teams;
    ArrayList<Match> matches;

    public Tournament()
    {

        teams = new ArrayList<>();
        matches = new ArrayList<>();
    }

    /**
     * Creates an array of Team objects from a list of team names
     * @param teamsList list of team names
     */
    public void instantiateTeams(ArrayList<String> teamsList)
    {
        teams.clear(); //Empty the list of teams
        matches.clear(); //Empty the list of matches

        if(teamsList == null)
            return;
        if(teamsList.size() == 0)
            return;


        //instantiating Teams
        for (int i = 0; i < teamsList.size(); i++) {
            teams.add(new Team(teamsList.get(i)));
        }
    }

    /**
     * Generates the list of matches between the teams
     * Each team will play against each other ones.
     */
    public void generateMatchList()
    {
        matches.clear();

        if(teams.size() == 0)
            return;


        for (int i = 0; i <  teams.size()-2; i++) {
            for (int j = 0; j < teams.size()-1 ; j++) {

                matches.add(new Match(teams.get(i), teams.get(j)));
            }
        }
    }

    /**
     * Returns the list of teams' names
     * @return arraylist containing team names
     */
    public ArrayList<String> getAllTeamNames()
    {
        ArrayList<String> result = new ArrayList<>();

        if(teams == null)
            return result;

        for (int i = 0; i < teams.size(); i++)
        {
            result.add(teams.get(i).getTeamName());
        }

        return result;

    }

    public ModelFunctionSuccessResponse withDrawTeam(String teamName)
    {
        ModelFunctionSuccessResponse result = new ModelFunctionSuccessResponse();

        //find the team with the name
        int index = getTeamIndex(teamName);
        if(index < 0)
        {
            //Could not find team with such name. This should not happen.
            result.success = false;
            result.errormsg = Configurations.Error_CantFindTeamName + teamName;
            return  result;
        }
        else
        {
            //Found the team. Remove it.
            teams.remove(index);
            // Check if we still have sufficient amount of teams for the tournament.
            if(teams.size() < Configurations.MinimumNumberOfTeamsAllowed)
            {
                result.success = false;
                result.errormsg = Configurations.Error_NotEnoughTeams + Integer.toString(Configurations.MinimumNumberOfTeamsAllowed);
            }

            //Success
            return  result;
        }

    }



    /**
     * Returns the index of the first team matching the name. Returns -1 if no team found
     * @param teamName name of the team
     * @return index in the team array. -1 if no team found
     */
    private int getTeamIndex(String teamName)
    {
       int result = -1;
        for (int i = 0; i < teams.size() ; i++) {
            if(teams.get(i).getTeamName() == teamName)
            {
                result = i;
                break;
            }
        }
        return result;
    }
}
