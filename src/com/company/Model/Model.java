package com.company.Model;

public class Model
{

    FileManager fileManager;
    Tournament tournament;

    public Model()
    {
        fileManager = new FileManager();
        tournament = new Tournament();
    }


    public ModelFunctionSuccessResponse readMatchFile(String defaultTeamsFileName)
    {
        //Read the raw textfile
        ModelFunctionSuccessResponse result = fileManager.readTeamsFile(defaultTeamsFileName);

        if(result.success == false)
        {
            return result;
        }
        else
        {
            //Successful file reading

            //Generate Team objects from the raw text
            tournament.instantiateTeams(fileManager.getTeamsInputFileContent());
            //Generate game matches between teams
            tournament.generateMatchList();

            return new ModelFunctionSuccessResponse();
        }


    }
}
