import java.util.Scanner;
public class SecurityManager {
    public static void main(String [] args){
        // varibales
        String name, dummy;
        int seatNumber;
        char c;
        // Scanner object for reading user input
        Scanner s = new Scanner(System.in);
        SecurityCheck sc = new SecurityCheck();


        loop: while(true){
            System.out.println("------------------------------------------");
            System.out.println("Menu:\n\t(A) - Add Person\n\t(N) - Next Person\n\t(R) - Remove Lines\n\t(L) - Add Lines\n\t(P) - Print All Lines\n\t(Q) - Quit\n");
            System.out.println("------------------------------------------");
            System.out.print("Input: ");
            // obtain user input
            c = s.nextLine().charAt(0);
            // case statement for checking answers
            switch(c){
                /**
                 * For adding new People
                 */
                case 'A':{
                    System.out.println("Now inserting a new person...");
                    System.out.println("Name: ");
                    name = s.nextLine();
                    System.out.println("Seat number: ");
                    seatNumber = s.nextInt();
                    dummy = s.nextLine(); // dummy for buffer clearing
                    sc.addPerson(name, seatNumber);
                    break;
                }
                case 'a':{
                    System.out.println("Now inserting a new person...");
                    System.out.println("Name: ");
                    name = s.nextLine();
                    System.out.println("Seat number: ");
                    seatNumber = s.nextInt();
                    dummy = s.nextLine(); // dummy for buffer clearing
                    sc.addPerson(name, seatNumber);
                    break;
                }

                /**
                 * For removing the next availbale person in line.
                 */
                case 'N':{
                    System.out.println("Now removing the next person...");
                    Person removed = sc.removeNextAttendee();
                    if(removed == null){
                        System.out.println("Error: there is no available person to be removed.");
                        break;
                    }
                    System.out.println(removed.name + " from seat " + removed.seatNumber + " has been removed.");
                    break;
                }
                case 'n':{
                    System.out.println("Now removing the next person...");
                    Person removed = sc.removeNextAttendee();
                    if(removed == null){
                        System.out.println("Error: there is no available person to be removed.");
                        break;
                    }
                    System.out.println(removed.name + " from seat " + removed.seatNumber + " has been removed.");
                    break;
                }

                /**
                 * For removing defined number of lines
                 */
                case 'R':{
                    System.out.println("Now removing lines...");
                    int [] arr = new int[sc.lineCount];
                    char q = 'z';
                    int z = 0;
                    while(true){
                        System.out.println("Input line number to be removed (input 'q' when finished): ");
                        // obtain to see when to break asking for user, breaks on 'q' or when you reach maximum capacity of the lines
                        if(q == 'q' || q == 'Q' || z == sc.lineCount){
                            break;
                        }
                        //System.out.println("test");
                        q = s.nextLine().charAt(0);
                        // convert from char to int
                        arr[z] = (int)(q-'0');
                        z++;
                    }
                    arr[z-1] = 0;
                    //printer to test values properly stored
                    for(int x : arr){
                        System.out.println(x);
                    }
                    sc.removeLines(arr);
                    System.out.println("Successfully removed lines.");
                    break;
                }
                case 'r':{
                    System.out.println("Now removing lines...");
                    int [] arr = new int[sc.lineCount];
                    char q = 'z';
                    int z = 0;
                    while(true){
                        System.out.println("Input line number to be removed (input 'q' when finished): ");
                        if(q == 'q' || q == 'Q' || z == sc.lineCount){
                            break;
                        }
                        //System.out.println("test");
                        q = s.nextLine().charAt(0);
                        arr[z] = (int)(q-'0');
                        z++;
                    }
                    arr[z-1] = 0;
                    for(int x : arr){
                        System.out.println(x);
                    }
                    sc.removeLines(arr);
                    System.out.println("Successfully removed lines.");
                    break;
                }

                /**
                 * For adding lines from user input
                 */
                case 'L':{
                    System.out.println("Now adding lines...");
                    int num_lines;
                    System.out.println("Input number of lines to add: ");
                    num_lines = (int)(s.nextLine().charAt(0)-'0');
                    sc.addNewLines(num_lines);
                    break;
                }
                case 'l':{
                    System.out.println("Now adding lines...");
                    int num_lines;
                    System.out.println("Input number of lines to add: ");
                    num_lines = (int)(s.nextLine().charAt(0)-'0');
                    sc.addNewLines(num_lines);
                    break;
                }

                /**
                 * Prints all lines and peopel of the lline
                 */
                case 'P':{
                    System.out.println("Now Printing all lines...");
                    // scan for user input
                    Line itrLine = sc.headLine;
                    Person itrPerson = itrLine.headPerson;
                    int line_number = 0;
                    // itterate through all lines and people
                    System.out.println("|\tLine\t|\t\tName\t\t|\tSeat Number\t|");
                    System.out.println("=========================================================================");
                    // prints out and kinda format the names?? idk how to do that tho
                    while(itrLine != null){
                        line_number+=1;
                        itrPerson = itrLine.headPerson;
                        while(itrPerson != null){
                            System.out.println("|\t " + line_number + "\t|\t\t" + itrPerson.name + "\t\t|\t\t" + itrPerson.seatNumber + "\t|");
                            itrPerson = itrPerson.nextPerson;
                        }
                        itrLine = itrLine.lineLink;
                    }
                    System.out.println("=========================================================================");
                    //System.out.println("|\t    \t|\t\t    \t\t|\t           \t|");
                    break;
                }
                case 'p':{
                    System.out.println("Now Printing all lines...");
                    // scan for user input
                    Line itrLine = sc.headLine;
                    Person itrPerson = itrLine.headPerson;
                    int line_number = 0;
                    // itterate through all lines and people
                    System.out.println("|\tLine\t|\t\tName\t\t|\tSeat Number\t|");
                    System.out.println("=========================================================================");
                    // prints out and kinda format the names?? idk how to do that tho
                    while(itrLine != null){
                        line_number+=1;
                        itrPerson = itrLine.headPerson;
                        while(itrPerson != null){
                            System.out.println("|\t " + line_number + "\t|\t\t" + itrPerson.name + "\t\t|\t\t" + itrPerson.seatNumber + "\t|");
                            itrPerson = itrPerson.nextPerson;
                        }
                        itrLine = itrLine.lineLink;
                    }
                    System.out.println("=========================================================================");
                    //System.out.println("|\t    \t|\t\t    \t\t|\t           \t|");
                    break;
                    /*
                    System.out.println("Now Printing all lines...");
                    // scan for user input
                    Line itrLine = sc.headLine;
                    Person itrPerson = itrLine.headPerson;
                    int line_number = 0;
                    // itterate through all lines and people
                    System.out.println("|\tLine\t|\t\t\tName\t\t\t|\t\tSeat Number\t|");
                    System.out.println("=================================================================================================");
                    while(itrLine != null){
                        line_number+=1;
                        itrPerson = itrLine.headPerson;
                        while(itrPerson != null){
                            String str =  String.format("|%-15d|%.2s|%-15d|%n",line_number, itrPerson.name, itrPerson.seatNumber);
                            //System.out.println("|\t " + line_number + "\t|\t\t\t" + itrPerson.name + "\t\t\t|\t" + itrPerson.seatNumber + "\t\t|");
                            System.out.println(str);
                            itrPerson = itrPerson.nextPerson;
                        }
                        itrLine = itrLine.lineLink;
                    }
                    System.out.println("=================================================================================================");
                    //System.out.println("|\t    \t|\t\t    \t\t|\t           \t|");
                    break;
                    */
                }
                
                /**
                 * Break out of case and infinite loop
                 */
                case 'Q':{
                    break loop;
                }
                case 'q':{
                    break loop;
                }

                default: break;
            }
            Line itrLine = sc.headLine;
            //Person itrPerson = itrLine.headPerson;
            int line_number = 0;
            // itterate through all lines and people
            while(itrLine != null){
                line_number+=1;
                if(itrLine.length == 1){
                    System.out.println("Line " + line_number + ": " + itrLine.length + " Person Waiting");
                }
                else{
                    System.out.println("Line " + line_number + ": " + itrLine.length + " People Waiting");
                }
                itrLine = itrLine.lineLink;
            }



        }
        System.out.println("Exiting");
        s.close();
    }
}
