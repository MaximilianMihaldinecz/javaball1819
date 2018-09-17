package com.company.Model;

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

}
