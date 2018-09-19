package com.company.Model;

/**
 * Represents a team
 */
public class Team implements Comparable<Team>
{
    String TeamName;

    int matchWon = 0;
    int matchDrawn = 0;
    int matchLost = 0;
    int goalsFor = 0;
    int goalsAgainst = 0;

    int rank = 0;
    String medal = "";


    /**
     * Sents the rank within the tournament. Also sets medal according to the rank.
     * @param newRank the value of new rank
     */
    public void setRank(int newRank)
    {
        rank = newRank;
        switch (rank)
        {
            case 1:
                medal = "Gold";
                break;

            case 2:
                medal = "Silver";
                break;

            case 3:
                medal = "Bronze";
                break;
        }
    }

    /**
     * Returns match point calculated as: 3 points for win, 1 point for drawn
     * @return match points
     */
    int getMatchPoints()
    {
        return (matchWon * 3) + (matchDrawn);
    }

    /**
     * Calculates the difference between scored and received goals. Can be negative
     * @return goals scored minus goals received
     */
    int getGoalsDiff()
    {
        return goalsFor - goalsAgainst;
    }

    /**
     * Adds the result of a match to the team's statistics. It also calculates win-drawn-lost
     * @param goalScored The number of goals the team has scored
     * @param goalSuffered The number of goal the opponent has scored against the team
     */
    public void addMatchResult(int goalScored, int goalSuffered)
    {
        goalsFor += goalScored;
        goalsAgainst += goalSuffered;

        if(goalScored == goalSuffered)
            matchDrawn++;
        if(goalScored>goalSuffered)
            matchWon++;
        if(goalScored<goalSuffered)
            matchLost++;
    }



    /**
     * Represents a team
     * @param teamName Name of the team
     */
    public Team(String teamName)
    {
        TeamName = teamName;
    }

    /**
     *
     * @return The name of the team
     */
    public String getTeamName()
    {
        return TeamName;
    }

    @Override
    public int compareTo(Team o) {
        int opponentMatchPoint = o.getMatchPoints();
        int opponentGoalDiff = o.getGoalsDiff();

        if(getMatchPoints() < opponentMatchPoint)
        {
            return 1;
        }
        if(getMatchPoints() > opponentMatchPoint)
        {
            return -1;
        }
        if(getMatchPoints() == opponentMatchPoint)
        {
            if(getGoalsDiff() < opponentGoalDiff)
            {
                return 1;
            }
            if(getGoalsDiff() > opponentGoalDiff)
            {
                return -1;
            }
            if(getGoalsDiff() == opponentGoalDiff)
            {
                return 0;
            }

        }

        return 0;
    }

    /**
     * Provides all the statistical data from the team in a HTML table row format
     * @return all the statistical data from the team in a HTML table row format
     */
    public String getAsHTML()
    {
        String result = "<tr>";

        result += "<td align=\"center\">" + TeamName + "</td>";
        result += "<td align=\"center\">" + Integer.toString(rank) + "</td>";
        result += "<td align=\"center\">" + Integer.toString(matchWon) + "</td>";
        result += "<td align=\"center\">" + Integer.toString(matchDrawn) + "</td>";
        result += "<td align=\"center\">" + Integer.toString(matchLost) + "</td>";
        result += "<td align=\"center\">" + Integer.toString(goalsFor) + "</td>";
        result += "<td align=\"center\">" + Integer.toString(goalsAgainst) + "</td>";
        result += "<td align=\"center\">" + Integer.toString(getMatchPoints()) + "</td>";
        result += "<td align=\"center\">" + Integer.toString(getGoalsDiff()) + "</td>";
        result += "<td align=\"center\">" + medal + "</td>";


        result += "</tr>";
        return result;
    }

    public String getAsTxt()
    {
        String result = "";

        result += TeamName + "\t";
        result += Integer.toString(rank) + "\t";
        result += Integer.toString(matchWon) + "\t";
        result += Integer.toString(matchDrawn) + "\t";
        result += Integer.toString(matchLost) + "\t";
        result += Integer.toString(goalsFor) + "\t";
        result += Integer.toString(goalsAgainst) + "\t";
        result += Integer.toString(getMatchPoints()) + "\t";
        result += Integer.toString(getGoalsDiff()) + "\t";
        result += medal;


        return result;
    }
}
