package com.company.Controller;

import com.company.Configurations;
import com.company.Model.Model;
import com.company.Model.ModelFunctionSuccessResponse;
import com.company.View.View;

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
       ModelFunctionSuccessResponse result = model.readMatchFile(Configurations.DefaultTeamsFileName);
       if(result.success == false)
       {
           view.displayErrorMessage(result.errormsg, true);
           exitProgram();
       }

       //Display the list of teams on the GUI
        view.refreshTeamsList(model.getListOfTeamNames());

    }

    public void userRequestsTeamWithDrawal(String teamName)
    {
        ModelFunctionSuccessResponse result =  model.withDrawTeam(teamName);
        if(result.success == false)
        {
            //Error during removal
            view.displayErrorMessage(result.errormsg, true);
            exitProgram();
        }
        else
        {
            //Successful removal. Updating the list on the GUI.
            view.refreshTeamsList(model.getListOfTeamNames());
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

}
