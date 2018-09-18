package com.company.Model;

import java.util.ArrayList;

/**
 * Represents a match between two teams
 */
public class Match {

    //Contains two teams
    Team[] teams;

    //Scores for teams. Index of scores matches the team index.
    int[] scores;

    boolean isPlayed;

    /**
     * Represents a match between two teams
     * @param teamA First team
     * @param teamB Second team
     */
    public Match(Team teamA, Team teamB)
    {
        teams = new Team[2];
        teams[0] = teamA;
        teams[1] = teamB;

        scores = new int[2];
        scores[0] = -1;
        scores[1] = -1;

        isPlayed = false;
    }


    /**
     * Returns the match as a line of text. Contains the team name and results (if available)
     * @return
     */
    public String toString()
    {
        String result = "";
        if(isPlayed == false)
        {
            result =  teams[0].getTeamName() + " VS " + teams[1].getTeamName();
            result += "(No results yet)";
        }
        else
        {
            result = teams[0].getTeamName() + " (" + Integer.toString(scores[0]) + ")";
            result += " VS ";
            result += teams[1].getTeamName() + " (" + Integer.toString(scores[1]) + ")";
        }


        return result;
    }


    /**
     * Returns the match as an HTML table row. Contains the team name and results (if available)
     * @return
     */
    public String toStringHTMLTable()
    {
        String result;
        result = "<tr>";
        if(isPlayed == false)
        {
            result += "<td>";
            result +=  teams[0].getTeamName() + " VS " + teams[1].getTeamName();
            result += "</td><td>";
            result += "(No results yet)";
            result += "</td>";
        }
        else
        {
            result += "<td>";
            result += teams[0].getTeamName() + " (" + Integer.toString(scores[0]) + ")";
            result += " VS ";
            result += teams[1].getTeamName() + " (" + Integer.toString(scores[1]) + ")";
            result += "</td><td>";
            result += " ";
            result += "</td>";
        }

        result += "</tr>";
        return result;
    }

    /**
     * Returns true if name of the teams in this match equals to the input strings
     * @param teamA first team's name
     * @param teamB second team's name
     * @return returns true if both team names match
     */
    public boolean hasTeams(String teamA, String teamB)
    {
        boolean result = false;
        if(teams[0].equals(teamA) || teams[1].equals(teamA))
            if(teams[0].equals(teamB) || teams[1].equals(teamB))
                result = true;

        return result;
    }


    /**
     * Sets the scores for both of the teams. It assumes that the team names match correctly. Otherwise scores stay negative;
     * @param teamA first team's name
     * @param scoreA first team's score
     * @param teamB second team's name
     * @param scoreB second team's score
     */
    public void setScores(String teamA, int scoreA, String teamB, int scoreB)
    {
        isPlayed = true;

        if(teams[0].equals(teamA))
            scores[0] = scoreA;

        if(teams[1].equals(teamA))
            scores[1] = scoreA;

        if(teams[0].equals(teamB))
            scores[0] = scoreB;

        if(teams[1].equals(teamB))
            scores[1] = scoreB;
    }

    /**
     * Returns read-only match information object
     * @return Returns read-only match information object
     */
    public MatchInfo getMatchInfo()
    {
        return new MatchInfo(teams[0].getTeamName(), teams[1].getTeamName(), isPlayed, scores[0], scores[1]);
    }


}
