public class Person{
    String name;
    int seatNumber;
    Person nextPerson;

    /**
     * default constructor
     * @param name
     * @param seatNumber
     * @param nextPerson
     */
    Person(String name, int seatNumber, Person nextPerson){
        this.name = name;
        this.seatNumber = seatNumber;
        this.nextPerson = nextPerson;
    }

    String getName(){
        return name;
    }
    
    int getSeatNumber(){
        return seatNumber;
    }

    Person getNextPerson(){
        return nextPerson;
    }

    void setName(String name){
        this.name = name;
    }

    void setSeatNumber(int seatNumber){
        this.seatNumber = seatNumber;
    }

    void setNextPerson(Person nextPerson){
        this.nextPerson = nextPerson;
    }

}