# JavaBall (2018-2019)
University of Glasgow's Java practice exercise for MSc students at Data Science, Computer Science, and Information Security courses (2018-2019).

## Specification
###Scenario 

The favourite sport in the country of Hibernia is the team game JavaBall. You are to produce a program that manages and processes results of JavaBall matches in a given tournament.

###Functionality
1. The functionality of the program should be accessed by a GUI. You are free to design this as you choose, using one or more windows.
2. A text file giving the names of the teams taking part in the JavaBall tournament should be read in automatically at the start of the program. The text file, TeamsIn.txt, contains one team name per line, and will contain between 4 and 8 teams. Team names are never more than 10 characters long, and will not contain spaces. Team names are listed in the file in an arbitrary order, however you may assume that the file contains valid data. If the text file is not found, the program should display an appropriate message and then terminate. For an example of a possible TeamsIn.txt file, see the appendix.
3. Each team will play every other team exactly once. As part of the initialisation, the program should produce a list of all the matches and display this in a text area, indicating that as yet there are no results. For the match display, the matches and the teams should be in alphabetical order of the first team name, and within the match list, matches with equal first team should be broken alphabetically on the second team name. For example:
    - within a match : Abbey v Park, NOT Park v Abbey;
    - within the match list: Abbey v Ford then Abbey v Park then Ford v Park.

    A line from the display might look like:

    `Abbey  v  Park   *** no results yet ***`

    You should calculate what the maximum possible number of matches could be, and ensure that the GUI is capable of displaying that many matches if necessary.

4. It is possible for a team to withdraw from the tournament after the list of matches has been created. The user should be able to specify the team to be withdrawn, and the program must then remove all the matches which include this team name, and then update the display of matches. There will be no need to keep details to show that the team was entered and then withdrawn: all trace of the team can be removed at this stage.
5. A text file containing some (possibly all, but usually most) of the results will be called ResultsIn.txt. On request via a button click, the text file should be read, and the results should be processed as described below. Note that ResultsIn.txt could contain results for a team t that has withdrawn from the tournament (for example, if t withdrew halfway through the season) – all results involving t that are read in from the results file should be ignored. Teams will not withdraw once the results file has been processed. The display of matches should be updated to show these results. You may assume that the file contains valid data, with no duplications of match details. However you cannot assume anything about the order of the teams within the matches, or the order of the matches themselves in the text file. If the text file is not found, the program should display an appropriate message and then terminate.
   Details of each match are supplied on a single line consisting of:
   - a team name;
   - the number of goals scored by the first team;
   - another team name;
   - the number of goals scored by the second team.
   
   For example:
   ```
   Park 2 Ford 2
   Abbey 0 Ford 1
   ```
   The display of matches should be updated to show these results. E.g.,
   ``` 
   Abbey 0  Ford 1
   Abbey v  Park *** no result yet
   Ford 2   Park 2
   ```
   For a complete example of a possible ResultsIn.txt file, see the appendix. This file should be read in exactly once during a single execution of your program.
   
6. The remaining results are then entered manually via a GUI. The functionality to add results manually should only be enabled after ResultsIn.txt has been read in. Ensure that the user is not able to enter details for matches that have already had their results allocated (there is no requirement to amend existing results). The goals scored must be an integer between 0 and 9. Again, either do not allow the user to enter an incorrect score, or produce an appropriate error message. Due to the nature of the game, it is not possible for a team to score more than 9 goals per match.
7. When all the match results have been supplied, the program must (on request via a button) make available the functionality to process the results and produce a ranked table as shown in the screenshot below. The entries in the various columns are calculated as follows (some column headings are self-explanatory):
    - match points are calculated by allocating 3 points for each match won, and 1 point for each match drawn;
    - the goal difference for a team t is the total number of goals scored against t, subtracted from the total number of goals scored by t;
    - the teams are listed in order of total match points then goal difference. That is, if two or more teams have the same number of match points, the teams should then be ranked by goal difference (the team with the highest goal difference should be listed first);
    - the ranking of each team is indicated, and this might need to reflect the possibility
      that two or more teams have equal points and equal goal difference. In the example
      below, both Bournevale and Park have rank 2, whilst the next team, Park, has rank 4.
    - the top teams are allocated medals, namely Gold, Silver and Bronze, as follows: the top-ranked team (or group of teams) are awarded Gold, the second-top-ranked team/s are awarded Silver, whilst the third-top-ranked team/s are awarded Bronze, as in the example below.
    |Team|Rank|Matches Won|Matches Drawn|Matches Lost|Goals For|Goals Against|Match points|Points Diff| Medal|
    |:---|:--:|:---------:|:-----------:|:----------:|:-------:|:-----------:|:----------:|:---------:|:----:|
    |Hillend| 1| 3| 2| 0| 19| 12| 11| 7| Gold|
    |Bournevale| 2| 1| 4 |0 |9| 6| 7| 3| Silver|
    |Park| 2| 2| 1| 2| 12| 9| 7| 3| Silver|
    |Abbey| 4| 2| 1| 2| 6| 8| 7| -2| Bronze|
    |Ford| 5| 0| 4| 1| 9| 14| 4| -5|
    |Cragfoot| 6| 0| 2| 3| 6| 12| 2| -6|

8. There should be an ‘exit’ button. When this button is clicked, the text area display should be written to an output text file called ResultsOut.txt. That is, if the results have not yet been processed, ResultsOut.txt should contain a list of the matches together with the results that have been entered so far. Otherwise ResultsOut.txt should contain the ranked table as illustrate above. After producing the output file, the program should terminate. To ensure that the user cannot exit the program without creating the output file, the normal close button of the main GUI frame should be disabled.


*NOTES ON TEXT FILES*
After submission your program will be tested, so it is particularly important that your file- handling within the program does NOT refer to the absolute location of your text files. When testing your program within Eclipse, store your data files in the current working directory (i.e., the same one that contains your Java program). Please do NOT use JFileChooser: it is too time-consuming when testing your program!