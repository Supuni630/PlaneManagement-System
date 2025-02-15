import java.io.FileWriter;
import java.io.IOException;





public class Ticket {
    // Ticket.java

    //attributes
    private char row;
    private int seat;
    private int price;
    private Person person;

    public Ticket(char row, int seat, int price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    // Getters
    public char getRow() {
        return row;
    }

    public int getSeat() {
        return seat;
    }

    public int getPrice() {
        return price;
    }

    public Person getPerson() {
        return this.person;
    }

    // Setters
    public void setRow(char row) {
        this.row = row;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    // Method to print ticket information
    public void TicketInfo() {
        System.out.println("Row: " + getRow() +
                            "\nSeat: " + getSeat() +
                            "\nPrice:" + getPrice());
        person.PersonInfo();

    }

    public void save(){
        try{
            String path = getRow() + "" + getSeat() + ".txt";

            FileWriter newfile = new FileWriter(path);
            newfile.write("Ticket Info\n");
            newfile.write("Row:" + getRow() + "\tSeat:" + getSeat());
            newfile.write("\nPrice:" + getPrice());
            newfile.write("\nPerson Info");
            newfile.write("\nName:"+ person.getName() );
            newfile.write("\nSurname: "+ person.getSurname());
            newfile.write("\nEmail: " + person.getEmail());

            newfile.close();


        }catch(IOException e){
            System.out.println("Error occured");
        }
    }


}


