public class Line {
    // typical variables
    Person headPerson, tailPerson;
    int length;
    Line lineLink;


    /**
     * class constructor
     */
    public Line(){
        headPerson = tailPerson = null;
        length = 0;
        lineLink = null;
    }

    /**
     * 
     * @param attendee
     * Takes in a person and then adds to the line
     */
    public void addPerson(Person attendee){
        System.out.println("Inserting Person...");
        if(headPerson == null){
            // this makes sure that if the head is null, whichi ist should never be because there is always one line, to fix that
            headPerson = attendee;
            tailPerson = attendee;
            length+=1;
            return;
        }
        if(headPerson.getSeatNumber() > attendee.getSeatNumber()){
            // if the head is next largest seat number, insert at the head
            attendee.setNextPerson(headPerson);
            headPerson = attendee;
            length+=1;
            return;
        }
        else{
            // else just iterate through until you find a larger seat number
            Person it = headPerson;
            while(it.nextPerson != null){
                // this iterats
                // follinwg if statement comapres for seat number
                if(it.nextPerson.getSeatNumber() > attendee.getSeatNumber()){
                    break;
                }
                // advance iterator
                it = it.nextPerson;
            }
            // if the tail is not larger, then insert at tail, and update tail pointer.
            if(it == tailPerson)
                tailPerson = attendee;
                // call teh set next person method
            attendee.setNextPerson(it.getNextPerson());
            it.setNextPerson(attendee);
            System.out.println("Successfully Inserted Person.");
            // update legnth of line
            length+=1;
        }
    }

    /**
     *  
     * @return @Person is returned because it is at the head
     */
    public Person removeFrontPerson(){
        // if line is null, break
        if(headPerson == null)
            return null;
        // update the head and then return while updating the length
        Person temp = headPerson;
        headPerson = headPerson.nextPerson;
        length--;
        return temp;
    }
}