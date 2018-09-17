package com.company.Model;

/**
 * Represents a team
 */
public class Team
{
    String TeamName;

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
}
