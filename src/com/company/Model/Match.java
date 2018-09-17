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




}
