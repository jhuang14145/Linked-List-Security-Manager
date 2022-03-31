import java.util.ArrayList;
public class SecurityCheck {
    Line headLine, tailLine, cursorLine;
    int lineCount;

    /**
     * Constructor for securtiycheck objeect
     */
    public SecurityCheck(){
        headLine = new Line();
        tailLine = headLine;
        lineCount = 1;
    }

    /**
     * adds person to next available spot based on rules
     * @param name name of person
     * @param seatNumber seatnumber ofperson
     */
    public void addPerson(String name, int seatNumber){
        Person new_Person = new Person(name, seatNumber, null);
        //System.out.println("Inseting Person...");
        // in case line is null
        if(headLine == null){
            headLine = new Line();
            headLine.addPerson(new_Person);
            headLine.length++;
            return;
        }
        Line lineIt = headLine;
        // iterates through all lines and people to see if there is a duplicate seat number
        while(lineIt!= null){
            Person personIt = lineIt.headPerson;
            while(personIt != null){
                if(personIt.getSeatNumber() == seatNumber){
                    try{
                        throw new TakenSeatException("Error");
                    }
                    catch(TakenSeatException e){
                        System.out.println("Error: There is already someone taking that seat");
                        return;
                    }
                }
                personIt = personIt.nextPerson;
            }
            lineIt = lineIt.lineLink;
        }

        lineIt = headLine;
        // loops through the lines to see which spot is best to insert the new Person
        while(lineIt.lineLink != null){
            // if line is empty, insert it there
            if(lineIt.length == 0){
                break;
            }
            // if the next line is smaller that the current, insert it here
            if(lineIt.lineLink.length > lineIt.length){
                break;
            }
            // move on if current is same or higher
            if(lineIt.lineLink.length < lineIt.length || lineIt.lineLink.length == 0){
                lineIt = lineIt.lineLink;
                break;
            }
            lineIt = lineIt.lineLink;
        }
        lineIt.addPerson(new_Person);
        //System.out.println(lineIt.length);
    }

    /**
     * remove next person
     * @return person that you removed
     */
    public Person removeNextAttendee(){
        // if head is null
        if(headLine == null)
            return null;
        // this is the line iterator
        Line lineIt = headLine;
        int smallest = 100;
        // find tthe smallest length line
        while(lineIt != null){
            if(lineIt.length < smallest){
                smallest = lineIt.length;
            }
            lineIt = lineIt.lineLink;
        }
        lineIt = headLine;
        // search for the line that is 1 more than the smallest if it exists, otherwise insert at the end of the list
        while(lineIt.lineLink != null){
            if(lineIt.length == smallest + 1){
                break;
            }
            lineIt = lineIt.lineLink;
        }
        // if you are at the tail and it is empty, error
        if(lineIt == tailLine && lineIt.length == 0){
            try{
                throw new AllLinesEmptyException("Error");
            }
            catch(AllLinesEmptyException e){
                System.out.println("All lines are empty, cannot remove next person.");
                return null;
            }
        }
        return lineIt.removeFrontPerson();
    }

    /**
     * add new lines based on user input
     * @param newLines
     */
    public void addNewLines(int newLines){
        // if input is negative, give error
        if(newLines < 0){
            try{
                throw new InvalidLineCountException("Error");
            }
            catch(InvalidLineCountException e){
                System.out.println("Error: line count cannot be negative or zero");
                return;
            }
        }
        Line itr = tailLine;
        /* Another implementation to test // 
        for(int j = 0; j < newLines; j++){
            itr.lineLink = new Line();
            itr = itr.lineLink;
        }
        tailLine = itr;
        while(true){
            Person tempPerson1;
            tempPerson1 = removeNextAttendee();
            if(tempPerson1 == removeNextAttendee()){
                addPerson(tempPerson1.getName(), tempPerson1.getSeatNumber());
                break;
            }
            addPerson(tempPerson1.getName(), tempPerson1.getSeatNumber());
        }
        */
        

        /**
         * This is for storing all people to then be redistributed later
         */
        ArrayList<Person> temp = new ArrayList<>();
        Person ptemp;
        while(true){
            try{
                ptemp = removeNextAttendee();
                if(ptemp == null){
                    break;
                }
                temp.add(ptemp);
                System.out.println("reordering...");
            }
            catch(Exception e){
                System.out.println("reordering...done");
                break;
            }
        }
        itr = tailLine;
        // creates the new lines and links them using the tail reference
        for(int j = 0; j < newLines; j++){
            System.out.println(j);
            Line linetemp = new Line();
            itr.lineLink = linetemp;
            itr = itr.lineLink;
        }
        lineCount += newLines;
        tailLine = itr;
        System.out.println(tailLine.length);
        // inserts each person one by one to best position
        for(int i = 0; i < temp.size(); i++){
            addPerson(temp.get(i).getName(), temp.get(i).getSeatNumber());
        }
        System.out.println("Successfully added the new lines");
    }

    /**
     * removes lines based on input
     * @param removedLines
     */
    public void removeLines(int[] removedLines){
        //if you tyr to remove only line avaialbe
        if(lineCount == 1){
            try{
                throw new SingleLineRemovalException("error");
            }
            catch(SingleLineRemovalException e){
                System.out.println("Error: Cannot remove only line available.");
                return;
            }
        }
        int z = 0;
        // checks if any of the line indices are out of bounds
        for(int i = 0; i < removedLines.length; i++){
            if(removedLines[i] > lineCount){
                try{
                    throw new LineDoesNotExistException("Error");
                }
                catch(LineDoesNotExistException e){
                    System.out.println("Error: Line does not exist.");
                    return;
                }
            }
            if(removedLines[i] != 0)
                z++;
        }
        ArrayList<Person> temp = new ArrayList<>();
        Person ptemp;
        // stores all people and pops them from the lines
        while(true){
            try{
                ptemp = removeNextAttendee();
                if(ptemp == null){
                    break;
                }
                temp.add(ptemp);
                System.out.println("reordering...");
            }
            catch(Exception e){
                System.out.println("reordering...done");
                break;
            }
        }
        int number_of_new_lines = lineCount - z;
        
        headLine = new Line();
        tailLine = headLine;
        lineCount = number_of_new_lines;
        Line itr = tailLine;
        // creates the new lines and links them using the tail reference
        for(int j = 0; j < number_of_new_lines; j++){
            System.out.println(j);
            Line linetemp = new Line();
            itr.lineLink = linetemp;
            itr = itr.lineLink;
        }
        tailLine = itr;
        //System.out.println(tailLine.length);
        // inserst each person into new sized lines one by one
        for(int i = 0; i < temp.size(); i++){
            addPerson(temp.get(i).getName(), temp.get(i).getSeatNumber());
        }
        addNewLines(0);
        System.out.println("Successfully removed the lines");
        /*
            Line itr = headLine;
            int j;
            for(j = 0; j < removedLines[j]-1; j++){
                itr = itr.lineLink;
            }
            if(removedLines[j] == 1){
                headLine = headLine.lineLink;
            }
            else{
                // check for null
                itr.lineLink = itr.lineLink.lineLink;
            }
            
            Person pitr = itr.headPerson;
            while(pitr != null){
                addPerson(pitr.getName(), pitr.getSeatNumber());
                pitr = pitr.nextPerson;
            }*/
    }


}
