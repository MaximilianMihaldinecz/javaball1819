package com.company.Model;

import com.company.Configurations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class FileManager
{
    /* Contains the list of teams as strings read from the input file */
    ArrayList<String> teamsInputFileContent;


    public FileManager()
    {
        teamsInputFileContent = new ArrayList<>();
    }



    public ModelFunctionSuccessResponse readTeamsFile(String fileName)
    {
        ModelFunctionSuccessResponse result = new ModelFunctionSuccessResponse();
        teamsInputFileContent = new ArrayList<>(); //Empty the current in-memory list

        //Read in the file
        try
        {
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String x = null;
            //Reading in line by line
            do {
                x = bufferedReader.readLine();
                if(x != null)
                {
                    teamsInputFileContent.add(x);
                }
            } while (x != null);

            bufferedReader.close();
            fileReader.close();

        } catch (Exception e)
        {
            result.success = false;
            result.errormsg = Configurations.Error_FileRead + fileName;
            result.errormsg += "\n\n Details:\n" + e.getMessage();
            return result;
        }

        //Check if we have sufficient number of teams
        if(teamsInputFileContent.size() < Configurations.MinimumNumberOfTeamsAllowed)
        {
            result.success = false;
            result.errormsg = Configurations.Error_FileRead + fileName;
            result.errormsg += "\n\n" + Configurations.Error_LessThanMinimumTeams;
        }

        //Sort the team alphabetically
        java.util.Collections.sort(teamsInputFileContent);

        return result;
    }

    public ArrayList<String> getTeamsInputFileContent()
    {
        return teamsInputFileContent;
    }

}
