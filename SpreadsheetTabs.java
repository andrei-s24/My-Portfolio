public class SpreadsheetTabs{
    //Creating a string array called "workbook".
    private String[] workbook;
    //The integer nextAvailable is the next available index position in the array.
    //It is also the size of the array or the amount of elements in the array.
    private int nextAvailable;
    //Creating an integer nextSheetNumber for the future sheets that will be created.
    private int nextSheetNumber;
    public SpreadsheetTabs(){
        //Making the capacity of the string array equal to 256.
        workbook = new String[256];
        //Creating the first three default names for the sheets.
        workbook[0] = "Sheet1";
        workbook[1] = "Sheet2";
        workbook[2] = "Sheet3";
        //nextAvailable = 3 because the size is now equal to 3;
        nextAvailable = 3;        
        nextSheetNumber = 4;
    }
   
    //Displays all the sheet names in order.
    public void Display() {
        for (int i = 0; i < nextAvailable; i++){
            System.out.println(workbook[i]);    
        }
        System.out.println();
    }
   
    //Returns the length of the array.
    public int length() {
        return nextAvailable;
    }
   
    //Shows the index of a specific sheet.
    //We use ".equalsIgnoreCase" because the program is supposed to be case insensitive.
    public int index(String sheetName) {
        for (int i = 0; i < nextAvailable; i++){
            if ( sheetName.equalsIgnoreCase(workbook[i]) == false){
                continue;
            }
            else{
                return i + 1;
            }
        }
        return -1;
    }
   
    public int rename (String currentName, String newName) {
        //Creating two boolean variables that indicate whether the array contains the currentName ("contains1") and if it
        //already contains the newName ("contains2")
        boolean contains1 = false;
        boolean contains2 = false;
        int i;    

        for ( i = 0; i < nextAvailable; i++){
            if (currentName.equalsIgnoreCase(workbook[i]) == false){
                continue;
            }    
            else{
                contains1 = true;
                break;
            }
        }
        //Storing i which is now equal to the java index position of the sheet that is being renamed.
        int n = i;
        for(i = 0; i < nextAvailable; i++){
            if(newName.equalsIgnoreCase(workbook[i]) == false){
                continue;
            }
            else{
                contains2 = true;
                break;
            }
        }
   
        if(contains1 == false || contains2 == true){
            return -1;
        }    
        else{    
            workbook[n] = newName;
            return n+1;
        }    
    }

    public boolean add(){
        //Check if the size of the array is already 256 which is the maximum.
        if (nextAvailable < 256){
            workbook[nextAvailable] = ("Sheet"+(nextSheetNumber));
            nextAvailable++;
            nextSheetNumber++;
            return true;
        }
        else{
            return false;
        }
    }    
    //The way we programmed the remove function is that the sheets after the removed one are moved back by 1 index position and then the last string gets removed.
    public String remove(int index){
        int i = 0;
        //Storing the name of the removed sheet:
        String removed = workbook[index - 1];
        //Checking all the possible reasons to not run the remove function:               
        if((index < 1) || (index > nextAvailable) || (nextAvailable == 1)) {
        return null;    
        }
        else{
        int n = index;
        //(nextAvailable - index) is the amount of times we want to step the sheets down.
        for (i = 0; i < nextAvailable - index; i++){
        //The current String becomes equal to the next String:
        workbook[n - 1] = workbook[n];  
        n++;
        }
        //Nullifying the last String
        workbook[nextAvailable - 1] = null;
        //Obviously the length of the array decreases after removing an element:
        nextAvailable--;
        return removed;  
        }
    }
   
    public int remove(String sheetName){
        //Checking if the array contains the sheetName:
        boolean contains = false;
        int i;
        for(i = 0; i < nextAvailable; i++){
            if (sheetName.equalsIgnoreCase(workbook[i]) == false){
                continue;
            }    
            else{
                contains = true;
                break;
            }
        }        
        //Checking all the possible reasons to not run the remove function:
        if((contains == false) || (nextAvailable == 1)){
            return -1;            
        }
        else{
            //Saving the index and adding 1 to it to turn it into the user's index:
            int index = i + 1;
            int n = i;
            //Same operation as the other remove function, i.e. everything gets moved down 
            for(i = 0; i < nextAvailable - index; i++){
                workbook[n] = workbook[n + 1];
                n++;
            }
            workbook[nextAvailable - 1] = null;
            nextAvailable--;
            return index;
        }
    }
   
    public String moveToEnd(int from){
        //Checking if the entered index exists:
        if((from > nextAvailable) || (from < 1)){
        return null;    
        }
        //Checking if the sheet is already at the end of the array:
        else if (from == nextAvailable){
        return workbook[from - 1];
        }
        else{
        String temp = workbook[from - 1];
        int n = from;
            //Same operation as the remove functions everything gets moved down: 
            for(int i = 0; i < nextAvailable - from; i++){
                workbook[n - 1] = workbook[n];    
                n++;
            }
        //Putting the sheet that was removed at the end and returning it:
        workbook[nextAvailable - 1] = temp;
        return workbook[nextAvailable - 1];
       }      
    }
    //This is the same operation except there's an extra step at the start to check if the String "from" exists in the array.
    public int moveToEnd(String from){
        boolean contains = false;
        int i;
        for(i = 0; i < nextAvailable; i++){
            if (from.equalsIgnoreCase(workbook[i]) == false){
                continue;
            }    
            else{
                contains = true;
                break;
            }
        }
        if(contains == false){
            return -1;
        }
        else if(i == nextAvailable - 1){
            return nextAvailable;
        }
        else{          
            int n = i;
            int m = i;
                for(i = 0; i < nextAvailable - n; i++){
                    workbook[m] = workbook[m + 1];    
                    m++;
                }
            workbook[nextAvailable - 1] = from;
            return n + 1;
        }
    }
   
    public int move(String from, String to, boolean before){
        //Here we have to check if both Strings exist in the array, but this time we also save the index positions of both strings:
        boolean contains1 = false;
        boolean contains2 = true;
        int i;        
        for(i = 0; i < nextAvailable; i++){
            if(from.equalsIgnoreCase(workbook[i]) == false){
                continue;
            }
            else{
                contains1 = true;
                break;
            }
        }
        int indexFrom = i;
        for(i = 0; i < nextAvailable; i++){
            if(to.equalsIgnoreCase(workbook[i]) == false){
                continue;
            }
            else{
                contains2 = true;
                break;
            }
        }
        int indexTo = i;
        if((contains1 = false) || (contains2 = false) || indexFrom == indexTo){
            return -1;
        }
        else{
            //The modulus difference between the two positions is the amount of times we want to step up or step down.
            int diff = indexTo - indexFrom;
            //If the difference is positive, the sheet has to move up, and therefore the other sheets must move down by one index position:
            if(diff > 0){
                //If the user wishes to put the sheet before the sheet "to" then the amount of sheets that need to be moved down is decreased by one.
                //The final index position also decreases by one.
                if(before == true){
                    diff--;
                    indexTo--;
                }
                for(i = 0; i < diff; i++){
                    workbook[indexFrom] = workbook[indexFrom + 1];
                    indexFrom++;
                }
                workbook[indexTo] = from;
                return indexTo + 1;
            }
            //If the difference is negative, the sheet has to move down, and therefore the other sheets must move up by one index position:
            else{
                //Making the difference value positive:
                diff = diff * -1;
                //If the user wishes to put the sheet before the sheet "to" then the amount of sheets that need to be moved down is increaased by one.
                if(before == true){
                    diff++;                    
                }
                for(i = 0; i < diff; i++){
                    workbook[indexFrom] = workbook[indexFrom - 1];
                    indexFrom--;
                }
                workbook[indexTo] = from;
                return indexTo + 1;
            }
        }
    }
    //The same operation as before except the index positions are already given by the user:
    public String move(int from, int to, boolean before){
        if((from > nextAvailable) || (to > nextAvailable) || (from < 1) || (to < 1) || (from == to)){
            return null;
        }
        else{
            from--;
            to--;
            String frOm = workbook[from];
            int diff = to - from;
            if(diff > 0){
                if(before == true){
                    diff--;
                    to--;
                }
                for(int i = 0; i < diff; i++){
                    workbook[from] = workbook[from + 1];
                    from++;
                }
                workbook[to] = frOm;
                return frOm;
            }
            else{
                diff = diff * -1;
                if(before == true){
                    diff++;
                    to--;
                }
                for(int i = 0; i < diff; i++){
                    workbook[from] = workbook[from - 1];
                    from--;
                }
                workbook[to] = frOm;
                return frOm;
            }
        }
    }    
}    