package com.company.View;

import com.company.Configurations;
import com.company.Controller.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.*;

public class View extends JFrame implements ActionListener {
    Controller Ctr;


    @Override
    public void actionPerformed(ActionEvent e) {

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
        setTitle("JavaBall");
        setSize(1000, 600);
        setLocation(100,100);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //Disabling exit option

        setVisible(true);
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
     * Close down the frame
     */
    public void close()
    {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }



}
