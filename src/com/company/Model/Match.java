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


}
