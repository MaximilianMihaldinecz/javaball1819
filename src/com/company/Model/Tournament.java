package com.company.Model;

import com.company.Configurations;
import sun.awt.ConstrainableGraphics;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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


    /**
     * Takes the content of a match results file and updates the match result stored in memory
     * @param rawFile each element contains a line from the matches file
     */
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
     * Calculates the number of matches which do not have results yet
     * @return Number of matches that have no result
     */
    public int getNumberOfMatchesWithoutResult()
    {
        int counter = 0;

        if(matches == null)
            return counter;

        for (int i = 0; i < matches.size() ; i++) {
            if(matches.get(i).isPlayed == false)
            {
                counter++;
            }
        }
        return counter;
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

    /**
     * @return returns the name of the two teams in a match which has no result.
     * Always returns one match in total (array of two items). Returns null if all matches have scores.
     */
    public String[] getNextMatchWithoutScore()
    {
        if(matches == null)
            return null;

        String[] result = null;
        for (int i = 0; i < matches.size() ; i++) {
            if(matches.get(i).isPlayed == false){
                result = matches.get(i).getTeamNames();
                break;
            }
        }
        return  result;
    }


    /**
     *
     * @return returns the stats table in HTML format
     */
    public ArrayList<String> getTournamentEndStatsHTML()
    {
        calculateTournamentResults();

        ArrayList<String> result = new ArrayList<>();


        //Add Header
        String header =
                new StringBuilder()
                        .append("<table border=1><tr>")
                        .append("<th>Team</th>")
                        .append("<th>Rank</th>")
                        .append("<th>Matches Won</th>")
                        .append("<th>Matches Drawn</th>")
                        .append("<th>Matches Lost</th>")
                        .append("<th>Goals For</th>")
                        .append("<th>Goals Against</th>")
                        .append("<th>Match Points</th>")
                        .append("<th>Goal Diff</th>")
                        .append("<th>Medal</th>")
                        .append("</tr>").toString();


        result.add(header);
        //Getting team results row by by
        for (int i = 0; i < teams.size() ; i++)
        {
            result.add(teams.get(i).getAsHTML());
        }

        //Close table
        result.add("</table>");

        return result;
    }

    /**
     * Returns the stats table in txt format. Columns divided by tabs.
     * @return each list item represents a line in a textfile
     */
    public ArrayList<String> getTournamentEndStatsTxt()
    {
        ArrayList<String> result = new ArrayList<>();

        String header =
                new StringBuilder()
                        .append("Team\t")
                        .append("Rank\t")
                        .append("Matches Won\t")
                        .append("Matches Drawn\t")
                        .append("Matches Lost\t")
                        .append("Goals For\t")
                        .append("Goals Against\t")
                        .append("Match Points\t")
                        .append("Goal Diff\t")
                        .append("Medal")
                        .toString();


        result.add(header);

        for (int i = 0; i < teams.size() ; i++)
        {
            result.add(teams.get(i).getAsTxt());
        }

        return result;
    }


    /**
     * Calculates the result of the tournament and ranks the teams
     */
    private void calculateTournamentResults()
    {
        if(matches == null)
            return;

        //Populate each team with match data
        for (int i = 0; i < matches.size() ; i++)
        {
            matches.get(i).populateTeamsWithData();
        }

        //rank teams
        rankTeams();
    }

    /**
     * Team ranking based on Match Points (primary rank) and Goal difference (secondary rank)
     * Ranking data is added to the team objects.
     */
    private void rankTeams()
    {
        Collections.sort(teams);
        int currentRank = 1;

        for (int i = 0; i < teams.size() ; i++)
        {
            //last iteration will always get the current rank
            if(i == teams.size()-1)
            {
                teams.get(i).setRank(currentRank);
                break;
            }

            //If the next team in the order has lower value then we increment the rank
            if(teams.get(i).compareTo(teams.get(i+1)) < 0 )
            {
                teams.get(i).setRank(currentRank);
                currentRank++;
            }

            //If the next team has the same value then they will share the rank
            if(teams.get(i).compareTo(teams.get(i+1)) == 0 )
            {
                teams.get(i).setRank(currentRank);
            }

        }


    }

}
