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


        for (int i = 0; i <  teams.size()-1; i++) {
            for (int j = i+1; j < teams.size() ; j++) {

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

    /**
     * Returns number of matches between the teams
     * @return number of matches
     */
    public int getNumberOfMatches()
    {
        if(matches == null)
            return 0;

        return matches.size();
    }


    /**
     * Returns the matches between the teams as a string.
     * The string contains the name of the teams and the results if available.
     * Example: "TeamA VS TeamB (No results yet)"
     * @return list of matches as string
     */
    public ArrayList<String> getMatchesAsString()
    {
        ArrayList<String> result = new ArrayList<>();
        if(matches == null)
            return result;

        for (int i = 0; i < matches.size(); i++)
        {
            result.add(matches.get(i).toStringHTMLTable()) ;
        }

        return result;
    }


    public void processMatchResultsFile(ArrayList<String> rawFile)
    {
        if(rawFile == null)
            return;

        for (int i = 0; i < rawFile.size() ; i++)
        {
            String[] splitted = splitMatchResultFileRow(rawFile.get(i));
            if(splitted != null)
            {
                updateMatchScore(splitted[0], Integer.parseInt(splitted[1]), splitted[2], Integer.parseInt(splitted[3]));
            }
        }
    }

    /**
     *
     * @param row A string containing a match result from a file
     * @return returns an array of 4 elements or null (in case of error)
     */
    private String[] splitMatchResultFileRow(String row)
    {

        String[] result = row.trim().split(" ");
        if(result.length != 4)
            return null;
        else
            return result;
    }

    /**
     * Updates a match's score.
     * @param teamA name of the first team
     * @param scoreA score of the first team
     * @param teamB name of the second team
     * @param scoreB score of the second team
     * @return Returns false if could not find a match with the team names. Returns true if update successfull.
     */
    public boolean updateMatchScore(String teamA, int scoreA, String teamB, int scoreB)
    {
        boolean result = false;
        if(matches == null)
            return result;

        for (int i = 0; i < matches.size() ; i++)
        {
            if(matches.get(i).hasTeams(teamA,teamB))
            {
                result = true;
                matches.get(i).setScores(teamA, scoreA, teamB, scoreB);
                break;
            }
        }
        return result;
    }





}
