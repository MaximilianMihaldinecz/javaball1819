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





    @Override
    public void actionPerformed(ActionEvent e)
    {
        //Check if user tries to withdraw a team
        if(e.getSource() == withDrawTeam)
        {
            withDrawEventHandler();
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
     */
    public void displayErrorMessage(String msg, boolean exitWarning)
    {
        if(exitWarning == false)
        {
            JOptionPane.showMessageDialog(null,msg,"Error",JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            JOptionPane.showMessageDialog(null,msg + "\n\n" + Configurations.Error_ProgramWillExit,"Error",JOptionPane.INFORMATION_MESSAGE);
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
            displayErrorMessage(Configurations.Error_SelectTeamFromList, false);
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
        String result = "<html><table>";

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




}
