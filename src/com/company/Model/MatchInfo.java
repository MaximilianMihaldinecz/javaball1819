package com.company.Model;

/**
 * Simple object representing a read-only information about a Match (class)
 */
public class MatchInfo {

    public final String teamA;
    public final String teamB;
    public final boolean isPlayed;
    public final int scoreA;
    public final int scoreB;

    public  MatchInfo(String teamA, String teamB, boolean isPlayed, int scoreA, int scoreB)
    {
        this.teamA = teamA;
        this.teamB = teamB;
        this.isPlayed = isPlayed;
        this.scoreA = scoreA;
        this.scoreB = scoreB;
    }

}
