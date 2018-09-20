package com.company.View;

import com.company.Configurations;
import com.company.Controller.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.*;

public class View extends JFrame implements ActionListener {

    Controller Ctr;
    JLabel titleBar;

    JList teamsList; //List of teams shown on the first screen
    DefaultListModel teamsListItems; //List of teams shown on the first screen
    JLabel teamsListLabel; //List of teams title label
    JButton withDrawTeam; //button to withdraw team on the first screen
    JLabel matchesListLabel; //List of matches title label
    JTextPane matchesList; //List of matches
    JLabel totalMatches; //Displaying the total matches
    JButton nextButton; //Moving to the next step - CTA
    JLabel editedMatchLabel; //Display the title of the currently edited match result
    JButton nextEditMatchButton; //Button to navigate to the next editable match result
    JComboBox teamAScoreList; //User input for teamA's score in match
    JComboBox teamBScoreList; //User input for teamB's score in match
    JTextPane statsPanel; //Tournament statistics displayed here
    JButton saveAndExitButton; //User saves the result to file and exits the program with this button



    int currentScreen = 1; //The ID of the form/screen the user is currently on
    String[] currentlyEditedMatch = new String[]{"",""}; //Contains team names for a match that's currently edited
    ArrayList<String> tournamentStatsText = new ArrayList<>(); //Contains the end statistics as text to display


    /*
     Size and location configuration for the elements
     */
    final int X_Jframe = 10;
    final int Y_Jframe = 10;
    final int W_JFrame = 640;
    final int H_JFrame = 650;
    final int X_TitleBar = X_Jframe + 10;
    final int Y_TitleBar = Y_Jframe + 10;
    final int W_TitleBar = 600;
    final int H_TitleBar = 100;
    final int W_nextButton = 150;
    final int H_nextButton = 50;
    final int X_nextButton = W_JFrame - (W_nextButton + 20);
    final int Y_nextButton = H_JFrame - (H_nextButton + 30);
    //First form (screen) elements
    final int X_TeamsListLabel = X_TitleBar;
    final int Y_TeamsListLabel = Y_TitleBar + 130;
    final int W_TeamListLabel = 250;
    final int H_TeamsListLabel = 30;
    final int X_TeamsList = X_TeamsListLabel;
    final int Y_TeamsList = Y_TeamsListLabel + H_TeamsListLabel + 10;
    final int W_TeamsList = W_TeamListLabel;
    final int H_TeamsList = 300;
    final int X_WithDrawButton = X_TeamsList;
    final int Y_WithDrawButton = Y_TeamsList + H_TeamsList + 20;
    final int H_WithDrawButton = 30;
    final int W_WithDrawButton = W_TeamsList;
    final int X_MatchesListLabel = X_TeamsListLabel + W_TeamListLabel + 50;
    final int Y_MatchesListLabel = Y_TeamsListLabel;
    final int W_MatchestListLabel = W_TeamListLabel + 50;
    final int H_MatchesListLabel = H_TeamsListLabel;
    final int X_MatchesList = X_MatchesListLabel;
    final int Y_MatchesList = Y_TeamsList;
    final int W_MatchesList = W_MatchestListLabel;
    final int H_MatchesList = H_TeamsList;
    final int X_totalMatchesLabel = X_MatchesListLabel;
    final int Y_totalMatchesLabel = Y_WithDrawButton;
    final int W_totalMatchesLabel = W_MatchestListLabel;
    final int H_totalMatchesLabel = H_WithDrawButton;
    //Second form (screen) elements
    final int X_editedMatchLabel = X_TeamsListLabel;
    final int Y_editedMatchLabel = Y_TeamsListLabel + 50;
    final int W_editedMatchLabel = W_TeamListLabel;
    final int H_editedMatchLabel = 20;
    final int X_teamAScoreList = X_editedMatchLabel;
    final int Y_teamAScoreList = Y_editedMatchLabel + H_editedMatchLabel + 20;
    final int H_teamAScoreList = 20;
    final int W_teamAScoreList = ((W_editedMatchLabel / 2) - 5);
    final int X_teamBScoreList = X_teamAScoreList + W_teamAScoreList + 10;
    final int Y_teamBScoreList = Y_teamAScoreList;
    final int W_teamBScoreList = W_teamAScoreList;
    final int H_teamBScoreList = H_teamAScoreList;
    final int W_nextEditMatchButton = 180;
    final int X_nextEditMatchButton = X_editedMatchLabel + W_editedMatchLabel - W_nextEditMatchButton;
    final int Y_nextEditMatchButton = Y_teamBScoreList + 50;
    final int H_nextEditMatchButton = 30;
    //Third form (screen) elements
    final int X_statsPanel = X_TitleBar;
    final int Y_statsPanel = Y_TitleBar + H_TitleBar + 20;
    final int H_statsPanel = H_JFrame - (Y_statsPanel + 70);
    final int W_statsPanel = W_TitleBar;
    final int W_saveAndExitButton = 200;
    final int H_saveAndExitButton = 40;
    final int X_saveAndExitButton = (W_JFrame / 2) - (W_saveAndExitButton / 2);
    final int Y_saveAndExitButton = (H_JFrame - H_saveAndExitButton) - 25;





    @Override
    public void actionPerformed(ActionEvent e)
    {
        //Check if user tries to withdraw a team
        if(e.getSource() == withDrawTeam)
        {
            withDrawEventHandler();
        }
        //Check if user is about to navigate to the next screen
        if(e.getSource() == nextButton)
        {
            nextScreenHandler();
        }
        if(e.getSource() == nextEditMatchButton)
        {
            handleNextEditMatchButtonPressed();
        }
        if(e.getSource() == saveAndExitButton)
        {
            handleFinalSaveAndExit();
        }
    }




    public View(Controller ctr) {
        Ctr = ctr;
    }


    /**
     * Displays the default JFrame window with it's elements.
     */
    public void displayStartScreen()
    {
        //Setting window size and position and layout
        setTitle("JavaBall 2018-2019");
        setSize(W_JFrame, H_JFrame);
        setLocation(X_Jframe,Y_Jframe);
        setBackground(Color.lightGray);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //Disabling exit option
        setLayout(null); //Setting for absolute positioning without window manager
        setResizable(false); //Disable window resize
        setVisible(true);

        //Create a titlebar with step 1 text
        titleBar = new JLabel("Step 1/3:\nView generated matches and withdraw teams");
        titleBar.setBounds(X_TitleBar,Y_TitleBar, W_TitleBar, H_TitleBar);
        titleBar.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        titleBar.setBackground(Color.white);
        titleBar.setFont(new Font("Serif", Font.PLAIN, 18));
        titleBar.setOpaque(true);
        titleBar.setVisible(true);
        titleBar.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        this.add(titleBar);

        //Create next step button
        nextButton = new JButton("Next ->");
        nextButton.setBounds(X_nextButton, Y_nextButton, W_nextButton, H_nextButton);
        nextButton.setVisible(true);
        nextButton.addActionListener(this);
        this.add(nextButton);

        displayFirstForm();
    }

    /**
     * Displays the GUI items for required for the first step (show teams, generated matches and withdrawal)
     */
    public void displayFirstForm()
    {
        //Create  a label for the list of teams listbox
        teamsListLabel = new JLabel("List of teams");
        teamsListLabel.setBounds(X_TeamsListLabel,Y_TeamsListLabel,W_TeamListLabel,H_TeamsListLabel);
        teamsListLabel.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        teamsListLabel.setFont(new Font("Serif", Font.PLAIN, 18));
        teamsListLabel.setVisible(true);
        this.add(teamsListLabel);

        //Create a list box for the teams
        teamsListItems= new DefaultListModel<>();
        teamsList = new JList(teamsListItems);
        teamsList.setBounds(X_TeamsList,Y_TeamsList,W_TeamsList,H_TeamsList);
        teamsList.setVisible(true);
        this.add(teamsList);

        //Create withdraw button
        withDrawTeam = new JButton("Withdraw selected team");
        withDrawTeam.setBounds(X_WithDrawButton, Y_WithDrawButton, W_WithDrawButton, H_WithDrawButton);
        withDrawTeam.setVisible(true);
        this.add(withDrawTeam);
        withDrawTeam.addActionListener(this);

        //Create label for displaying matches
        matchesListLabel = new JLabel("Generated matches:");
        matchesListLabel.setBounds(X_MatchesListLabel, Y_MatchesListLabel, W_MatchestListLabel, H_MatchesListLabel);
        matchesListLabel.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        matchesListLabel.setFont(new Font("Serif", Font.PLAIN, 18));
        matchesListLabel.setVisible(true);
        this.add(matchesListLabel);

        //Create textarea displaying the generated list of matches
        matchesList = new JTextPane();
        matchesList.setContentType("text/html");
        matchesList.setEditable(false);
        matchesList.setBounds(X_MatchesList, Y_MatchesList, W_MatchesList, H_MatchesList);
        matchesList.setVisible(true);
        this.add(matchesList);

        //Create label displaying the total number of generated matches
        totalMatches = new JLabel("Total matches: ");
        totalMatches.setBounds(X_totalMatchesLabel, Y_totalMatchesLabel, W_totalMatchesLabel, H_totalMatchesLabel);
        totalMatches.setFont(new Font("Serif", Font.PLAIN, 18));
        totalMatches.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        totalMatches.setVisible(true);
        this.add(totalMatches);


        this.repaint();
    }

    /**
     * Clears and reloads the list of teams on the GUI (first screen)
     * @param listOfTeams
     */
    public void refreshTeamsList(ArrayList<String> listOfTeams)
    {
        teamsListItems.clear();

        for (int i = 0; i < listOfTeams.size() ; i++) {
            teamsListItems.addElement(listOfTeams.get(i));
        }

    }




    /**
     * Displays an error message to the user
     * @param msg The error message to display to the user
     * @param exitWarning If true, the user will be notified that the program will exit now.
     * @param errorTitle If false, the messagebox won't have a title. If true, it will show "Error" in title.
     */
    public void displayErrorMessage(String msg, boolean exitWarning, boolean errorTitle)
    {
        String title = "";
        if(errorTitle)
            title = "Error";


        if(exitWarning == false)
        {
            JOptionPane.showMessageDialog(null,msg,title,JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            JOptionPane.showMessageDialog(null,msg + "\n\n" + Configurations.Error_ProgramWillExit,title,JOptionPane.INFORMATION_MESSAGE);
        }

    }

    /**
     * Handling the withdraw event on UI level
     */
    private void withDrawEventHandler()
    {
        //Check if a team was selected from the list
        if(teamsList.getSelectedValue() == null)
        {
            displayErrorMessage(Configurations.Error_SelectTeamFromList, false, true);
        }else {
            Ctr.userRequestsTeamWithDrawal(teamsList.getSelectedValue().toString());
        }

    }

    /**
     * Updates the number of matches shown in the label (first screen)
     * @param numberOfMatches number of matches between the teams
     */
    public void refreshTotalMatchCount(int numberOfMatches)
    {
        totalMatches.setText("Total matches: " + Integer.toString(numberOfMatches));
    }

    /**
     * Reloads the list of matches on the GUI (first screen)
     * @param matches the text version of a test with or without results
     */
    public void refreshMatchList(ArrayList<String> matches)
    {
        String result = "<html><body style=\"font-size:10\"><table>";

        for (int i = 0; i < matches.size(); i++) {
            result +=  matches.get(i) + "<br>";
        }

        result += "</table>";
        matchesList.setText(result);
    }

    /**
     * Close down the frame
     */
    public void close()
    {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    /**
     * Handling when user clicks on the "next" button to navigate to the next screen.
     */
    public void nextScreenHandler()
    {
        if(currentScreen == 1)
        {
            currentScreen += 1;
            navigateToSecondScreen();
            Ctr.userNavigatedToSecondScreen();
        }
        else
        {
            if(currentScreen == 2)
            {
                currentScreen = 3;
                navigateToThirdScreen();
            }
        }

    }

    /**
     * Navigating to the third (last) screen)
     */
    private void navigateToThirdScreen()
    {
        //Hide items from the second screen
        teamsList.setVisible(false);
        totalMatches.setVisible(false);
        teamsListLabel.setVisible(false);
        matchesListLabel.setVisible(false);
        matchesList.setVisible(false);

        //Change main title
        titleBar.setText("Step 3/3: View tournament statistics");

        //Create textpane to display the results
        statsPanel =  new JTextPane();
        statsPanel.setContentType("text/html");
        statsPanel.setEditable(false);
        statsPanel.setBounds(X_statsPanel, Y_statsPanel, W_statsPanel, H_statsPanel);
        statsPanel.setVisible(true);
        this.add(statsPanel);


        //Create the save and exit button
        saveAndExitButton = new JButton("Save to file & Exit");
        saveAndExitButton.setBounds(X_saveAndExitButton, Y_saveAndExitButton, W_saveAndExitButton, H_saveAndExitButton);
        saveAndExitButton.setVisible(true);
        this.add(saveAndExitButton);
        saveAndExitButton.addActionListener(this);

        //Getting the tournamenet data text and displaying it
        tournamentStatsText = Ctr.getTournamentEndStats();
        String statsText = "<html><body style=\"font-size:14\">";
        if(tournamentStatsText != null)
        {
            for (int i = 0; i < tournamentStatsText.size(); i++)
            {
                statsText += tournamentStatsText.get(i);
            }
        }
        statsText += "</html>";
        statsPanel.setText(statsText);
    }

    /**
     * Updates the number of remaining matches user needs to provide results for
     * @param count number of remaining matches
     */
    public void updateRemainingMatchesToFill(int count)
    {
        teamsListLabel.setText("Enter missing match results (" + Integer.toString(count) + "):");

        if(count == 1)
        {
            nextEditMatchButton.setText("Save & Show ranking");
        }
    }

    /**
     * Navigating to the second screen
     */
    private void navigateToSecondScreen()
    {
        //Change the title bar
        titleBar.setText("Step 2/3: Provide match results");
        //hide withdraw button
        withDrawTeam.setVisible(false);
        //hide list of teams
        teamsList.setVisible(false);
        //change title of the team's label
        teamsListLabel.setText("Enter missing match results:");
        //Change title of generated match results
        matchesListLabel.setText("All matches:");
        //Disable next button
        nextButton.setVisible(false);


        //Create label showing currently edited match
        editedMatchLabel = new JLabel(" ");
        editedMatchLabel.setBounds(X_editedMatchLabel, Y_editedMatchLabel, W_editedMatchLabel, H_editedMatchLabel);
        editedMatchLabel.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        editedMatchLabel.setFont(new Font("Serif", Font.PLAIN, 14));
        editedMatchLabel.setVisible(true);
        this.add(editedMatchLabel);

        //Create button to navigate to the next editable match
        nextEditMatchButton = new JButton("Next match");
        nextEditMatchButton.setBounds(X_nextEditMatchButton, Y_nextEditMatchButton, W_nextEditMatchButton, H_nextEditMatchButton);
        nextEditMatchButton.setVisible(true);
        this.add(nextEditMatchButton);
        nextEditMatchButton.addActionListener(this);


        //Listbox for teamA
        teamAScoreList = new JComboBox(new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"} );
        teamAScoreList.setBounds(X_teamAScoreList, Y_teamAScoreList, W_teamAScoreList, H_teamAScoreList);
        teamAScoreList.setVisible(true);
        this.add(teamAScoreList);

        //Listbox for teamB
        teamBScoreList = new JComboBox(new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"} );
        teamBScoreList.setBounds(X_teamBScoreList, Y_teamBScoreList, W_teamBScoreList, H_teamBScoreList);
        teamBScoreList.setVisible(true);
        this.add(teamBScoreList);

    }


    /**
     * Updates the currently edited match results' title
     * @param nextMatchWithoutScore String array with two items. Each item is a name of a team.
     */
    public void updateNextMatchLabel(String[] nextMatchWithoutScore)
    {
        editedMatchLabel.setText(nextMatchWithoutScore[0] + " VS " + nextMatchWithoutScore[1]);
        currentlyEditedMatch = nextMatchWithoutScore;
    }


    /**
     * Handle the event on UI level when user presses the "edit next match score" button
     */
    private void handleNextEditMatchButtonPressed()
    {
        int[] scores = new int[]{Integer.parseInt(teamAScoreList.getSelectedItem().toString()),
                Integer.parseInt(teamBScoreList.getSelectedItem().toString())};

        Ctr.userSavesScores(currentlyEditedMatch, scores);

    }

    /**
     * Called after all the match scores supplied but the user is not moved to the last screen.
     * This method hides UI inputs related to scores, as there is no more data needed to be entered.
     */
    public void prepareForLastScreen()
    {
        editedMatchLabel.setVisible(false);
        teamAScoreList.setVisible(false);
        teamBScoreList.setVisible(false);
        teamsListLabel.setVisible(false);
        nextEditMatchButton.setVisible(false);

    }

    /**
     * Handles when user clicks on "save to file and exit" button on the last screen.
     */
    private void handleFinalSaveAndExit()
    {
        Ctr.userRequestsSaveAndExit();
    }
}
