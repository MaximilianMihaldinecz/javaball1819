package com.company.Controller;

import com.company.Configurations;
import com.company.Model.Model;
import com.company.Model.ModelFunctionSuccessResponse;
import com.company.View.View;

import java.util.ArrayList;

public class Controller {

    View view;
    Model model;

    public Controller()
    {
        view = new View(this);
        model = new Model();
    }

    /**
     * Entry point from the main()
     * Calls for displaying the GUI and read the default "Matches" file
     */
    public void startProgram()
    {
        //Display GUI
       view.displayStartScreen();

       //Read in the teams file
       ModelFunctionSuccessResponse result = model.readTeamFile(Configurations.DefaultTeamsFileName);
       if(result.success == false)
       {
           view.displayErrorMessage(result.errormsg, true,true);
           exitProgram();
       }

       //Display the list of teams on the GUI
        view.refreshTeamsList(model.getListOfTeamNames());

       //Display the number of matches on the GUI;
        view.refreshTotalMatchCount(model.getNumberOfMatches());

        //Display matches
        view.refreshMatchList(model.getResultsAsString());
    }

    public void userRequestsTeamWithDrawal(String teamName)
    {
        ModelFunctionSuccessResponse result =  model.withDrawTeam(teamName);
        if(result.success == false)
        {
            //Error during removal
            view.displayErrorMessage(result.errormsg, true,true);
            exitProgram();
        }
        else
        {
            //Successful removal. Updating the list on the GUI.
            view.refreshTeamsList(model.getListOfTeamNames());
            view.refreshTotalMatchCount(model.getNumberOfMatches());
            view.refreshMatchList(model.getResultsAsString());
        }
    }


    /**
     * Graceful exit of the program
     */
    private void exitProgram()
    {
        view.close();
        System.exit(0);
    }


    /**
     * Handles navigating from the first screen to the second.
     * Reads in the scores data from file then updates the screen.
     */
    public void userNavigatedToSecondScreen()
    {
        //Read in the results file
        ModelFunctionSuccessResponse response = model.readMatchResultFile(Configurations.DefaultMatchResultsInputFileName);
        if(response.success == false)
        {
            view.displayErrorMessage(response.errormsg, true,true);
            exitProgram();
        }
        else
        {
            refreshSecondScreenData();
        }
    }

    /**
     * Called from View when user provides scores for a match.
     * @param currentlyEditedMatch Representing the two teams in the match
     * @param scores the scores for the teams. Indexes of scores and teams are aligned.
     */
    public void userSavesScores(String[] currentlyEditedMatch, int[] scores)
    {
        boolean result = model.updateMatchScore(currentlyEditedMatch, scores);
        if(result == false)
        {
            view.displayErrorMessage(Configurations.Error_CouldNotUpdateScore,true,true);
        }
        else
        {
            refreshSecondScreenData();
        }
    }


    /**
     * Refreshes the content on the second screen (match information)
     * If there is no more matches to provide data for, it initiates moving to the next screen.
     */
    private void refreshSecondScreenData()
    {
        //Update the counter of remaining matches
        int noResultCount = model.getNumberOfMatchesWithoutResult();
        if(noResultCount == 0)
        {
            //No matches to update. We need to navigate to the next screen.

            //Display the matches with results on the GUI
            view.refreshMatchList(model.getResultsAsString());
            view.prepareForLastScreen();
            view.displayErrorMessage(Configurations.Error_NoMatchesToUpdate, false,false);
            view.nextScreenHandler();
        }
        else
        {
            //Display the matches with results on the GUI
            view.refreshMatchList(model.getResultsAsString());
            //Display number of matches without result
            view.updateRemainingMatchesToFill(noResultCount);
            //Display the name of the first match which has no result yet
            view.updateNextMatchLabel(model.getNextMatchWithoutScore());
        }

    }

    /**
     * Gets the final tournament statistics
     * @return a list of text, each item is a row
     */
    public ArrayList<String> getTournamentEndStats()
    {
        return model.getTournamentEndStats();
    }
}
