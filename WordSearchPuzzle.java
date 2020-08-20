import java.util.*;
import java.io.*;
public class WordSearchPuzzle{
    private char[][] puzzle;
    private ArrayList<String> puzzleWords = new ArrayList<String>();
    private int dimensions;    
    private ArrayList<String> positions = new ArrayList<String>();
    private char[][] puzzletemp;
    
    private static ArrayList<String> loadWordsFromFile(String filename) {
        // Create an ArrayList and add each word read
        // from the named file to the list
        try {
            // The following statement attempts to get access to the file and open it.
            // If it fails it will "throw" (cause) an exception (error) that will
            // be "caught" by the "catch" code.
            FileReader aFileReader = new FileReader(filename);

            // If it succeeds it will connect to the file and give your program
            // read access to it. The system will also create a "buffer" (space)
            // to store the data transferred from the file and available to
            // your program.
            BufferedReader aBufferReader = new BufferedReader(aFileReader);
            
            // Now, we need to create the ArrayList that will store the words read from the file
            ArrayList<String> words = new ArrayList<String>();

            // Create a String variable to store just ONE word read from the file
            String aWord;

            // Start the process by ATTEMPTING to read the first word. If the file
            // is EMPTY (i.e. there are NO WORDS in the file) the system will return
            // a null value
            aWord = aBufferReader.readLine() ;
            // Check if we have reached the end of the file (i.e. there are no more
            // words in the file) and the system has returned a null result
            while (aWord != null) { 
                // MUST have read another word so add it ot the list
                words.add(aWord.toUpperCase());
                // Attempt to read the next one and repeat 'while' test
                aWord = aBufferReader.readLine() ;
            }
            // The 'while' has stopped so ALL the words in the file have been read
            // Remove the buffer from memory
            aBufferReader.close();
            // Close the file and allow access to it by others
            aFileReader.close();
            // return the reference to the ArrayList of words
            return words ;
        }
        catch(IOException x) {
            // An Input/Output exception (e.g. the file wasn't found, your program doesn't
            // have access rights to the file, etc.) has occurred.
            // Return a null reference value to signal that no data was read.
            return null ;
        }
    }
    
    public WordSearchPuzzle(ArrayList<String> userSpecifiedWords){
        puzzleWords = userSpecifiedWords;
    }
    
    public WordSearchPuzzle(String wordFile, int wordCount, int shortest, int longest){
        ArrayList<String> allWords = new ArrayList<String>();
        allWords = loadWordsFromFile(wordFile);
        String word;        
        while(puzzleWords.size() < wordCount){
            //Generating a list of wordCount amount of random words:
            word  = allWords.get((int)(Math.random() * (allWords.size())));
            //Checking if the word is within the range of shortest - longest:
            if(word.length() < shortest || word.length() > longest){
                continue;
            }
            else{
                puzzleWords.add(word);               
            }
        }
        //Getting the sum of the letters of the words in the list:
        int sum = 0;
        for(int i = 0; i < puzzleWords.size(); i++){
            sum = sum + (puzzleWords.get(i)).length();
        }
        //Setting the dimensions for the grid:
        dimensions = (int) Math.sqrt(sum * 3);
        puzzle = new char[dimensions][dimensions];
        generateWordSearchPuzzle();
    }
    
    public List<String> getWordSearchList(){
        for(int i = 0; i < puzzleWords.size(); i++){
            System.out.println(puzzleWords.get(i));
            System.out.println();
        }        
        return puzzleWords;
    }    
       
    private void generateWordSearchPuzzle(){
        int row, col;
        int direction;
        int i = 0;
        int wordLength;
                
        while(i < puzzleWords.size()){
            String word = puzzleWords.get(i);
            wordLength = (puzzleWords.get(i)).length();
            //Pick random number for direction:
            direction = (int)(Math.random() * 4);
            //Random position:
            row = (int)(Math.random() * dimensions);
            col = (int)(Math.random() * dimensions);
            //Storing the row and column temporarily so that we can use them in the loops:
            int rowtemp = row;
            int coltemp = col;
            
            //Horizontal, left to right
            if(direction == 0){
                //Check if the word will fit on the grid:
                if(dimensions - (col + 1) >= wordLength){
                    //Checking of those places on the grid are empty with no letters already inside them:
                    int empty;
                    for(empty = 0; empty < wordLength; empty++){
                        if(Character.isLetter(puzzle[row][coltemp]) == false){
                            coltemp++;
                        }
                        else{
                            //If there is a letter already there we break the loop with a value of empty that won't interfere with the next if statement:
                            empty = wordLength + 1;
                        }
                    }
                    //Checking if there were enough empty slots:
                    if(empty == wordLength){
                        //Filling in the slots with the letters of the word: 
                        for(int j = 0; j < wordLength; j++){
                            puzzle[row][col] = word.charAt(j);
                            col++;
                        }
                        //Moving to the next word:
                        i++;
                        //Storing the position and orientation of the word in a string ArrayList:
                        positions.add(word + ", row = " + row + ", column = " + (col - wordLength) + ", left to right,");
                    }
                }
            }
            //Horizontal, right to left
            else if(direction == 1){
                if(dimensions - (col + 1) >= wordLength){
                    int empty;
                    for(empty = 0; empty < wordLength; empty++){
                        if(Character.isLetter(puzzle[row][coltemp]) == false){
                            coltemp++;
                        }
                        else{
                            empty = wordLength + 1;
                        }
                    }
                    if(empty == wordLength){
                        //Everything is the same except this time the word is inverted:
                        for(int j = (wordLength - 1); j >= 0; j--){
                            puzzle[row][col] = word.charAt(j);
                            col++;
                        }
                        i++;
                        positions.add(word + ", row = " + row + ", column = " + (col - wordLength) + ", right to left,");
                    }
                }
            }
            //Vertical, down
            else if(direction == 2){
                //This time we're changing the row and keeping the column constant:
                if(dimensions - (row + 1) >= wordLength){
                    int empty;
                    for(empty = 0; empty < wordLength; empty++){
                        if(Character.isLetter(puzzle[rowtemp][col]) == false){
                            rowtemp++;
                        }
                        else{
                            empty = wordLength + 1;
                        }
                    }
                    if(empty == wordLength){
                        for(int j = 0; j < wordLength; j++){
                            puzzle[row][col] = word.charAt(j);
                            row++;
                        }
                        i++;
                        positions.add(word + ", row = " + (row - wordLength) + ", column = " + col + ", down,");
                    }
                }
            }
            //Vertical, up
            else{
                if(dimensions - (row + 1) >= wordLength){
                    int empty;
                    for(empty = 0; empty < wordLength; empty++){
                        if(Character.isLetter(puzzle[rowtemp][col]) == false){
                            rowtemp++;
                        }
                        else{
                            empty = wordLength + 1;
                        }
                    }
                    if(empty == wordLength){
                        for(int j = (wordLength - 1); j >= 0; j--){
                            puzzle[row][col] = word.charAt(j);
                            row++;
                        }
                        i++;
                        positions.add(word + ", row = " + (row - wordLength) + ", column = " + col + ", down,");
                    }
                }
            }              
        }                
        //Filling the rest of the spaces with random letters from the alphabet:
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for(i = 0; i < dimensions; i++){
            for(int j = 0; j < dimensions; j++){
                if(Character.isLetter(puzzle[i][j]) == false){
                    puzzle[i][j] = alphabet.charAt((int)(Math.random() * 26));                    
                }                         
            }            
        }        
    }
    
    public String getPuzzleAsString(){
        String puzzleAsString = "";
        for(int i = 0; i < dimensions; i++){
            for(int j = 0; j < dimensions; j++){
                puzzleAsString = puzzleAsString + puzzle[i][j]; 
            }
            puzzleAsString = puzzleAsString + "\n";
        }
        System.out.printf(puzzleAsString);
        System.out.println();
        return puzzleAsString;
    }
    
    public char[][] getPuzzleAsGrid(){
        for(int i = 0; i < dimensions; i++){
            for(int j = 0; j < dimensions; j++){
                System.out.print(puzzle[i][j] + " ");
            }
            System.out.println();
        }    
        System.out.println();
        return puzzle;
    }
    
    public void showWordSearchPuzzle(boolean hide) {
        if(hide == true){            
            //Printing the grid:
            for(int i = 0; i < dimensions; i++){
                for(int j = 0; j < dimensions; j++){
                    System.out.print(puzzle[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
        else{
            //Printing all the positions from the ArrayList "positions":
            for(int i = 0; i < positions.size(); i++){
                System.out.println(positions.get(i));
            }
            System.out.println();
        }
    }
}

