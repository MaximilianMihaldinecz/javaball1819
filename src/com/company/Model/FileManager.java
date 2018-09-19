package com.company.Model;

import com.company.Configurations;

import java.io.*;
import java.util.ArrayList;

/**
 * Deals with reading and writing files
 */
public class FileManager
{
    /* Contains the list of teams as strings read from the input file */
    ArrayList<String> teamsInputFileContent;

    /* Contains the match results as read from the input file */
    ArrayList<String> matchResultsFileContent;


    public FileManager()
    {
        teamsInputFileContent = new ArrayList<>();
        matchResultsFileContent = new ArrayList<>();
    }


    /**
     * Reads in the match results file
     * @param filename the file to read it from
     * @return returns success confirmation or error message
     */
    public ModelFunctionSuccessResponse readMatchResultsFile(String filename)
    {
        ModelFunctionSuccessResponse result = readFile(filename, matchResultsFileContent);
        return result;
    }

    /**
     * Reads a file into an arraylist of string
     * @param fileName file to read
     * @param destination arraylist of strings to read to
     * @return success
     */
    private ModelFunctionSuccessResponse readFile(String fileName, ArrayList<String> destination)
    {
        ModelFunctionSuccessResponse result = new ModelFunctionSuccessResponse();
        destination.clear();

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
                    destination.add(x);
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

        return result;
    }

    /**
     * Reads in the list of teams from file
     * @param fileName file containing the list of teams
     * @return success confirmation or error message. Also returns error if insufficient number of teams found
     */
    public ModelFunctionSuccessResponse readTeamsFile(String fileName)
    {
        ModelFunctionSuccessResponse result = readFile(fileName, teamsInputFileContent);

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

    public ArrayList<String> getMatchResultFileContent()
    {
        return matchResultsFileContent;
    }



    /**
     * Writes the content parameter to the output file
     * @param content the list of texts to write into file
     * @param fileName the filename to use
     * @return returns error if occured or success message
     */
    public ModelFunctionSuccessResponse writeToFile(ArrayList<String> content, String fileName)
    {
        ModelFunctionSuccessResponse result = new ModelFunctionSuccessResponse();


        try
        {
            File file = new File(fileName);
            FileWriter fileWriter = new FileWriter(file, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (int i = 0; i < content.size() ; i++) {
                bufferedWriter.write(content.get(i)+"\n");
            }

            bufferedWriter.flush();
            bufferedWriter.close();
            fileWriter.close();

        } catch (Exception e)
        {
            result.success = false;
            result.errormsg = Configurations.Error_FileWrite + fileName;
            result.errormsg += "\n\n Details:\n" + e.getMessage();
            return result;
        }

        return result;
    }
}
