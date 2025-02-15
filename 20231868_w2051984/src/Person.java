public class Person {

    //attributes
    private String name;
    private String surname;
    private String email;

    //constructor
    public Person(String name,String surname,String email){
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public String getName(){
        return this.name;
    }

    public String getSurname(){
        return this.surname;
    }

    public String getEmail(){
        return this.email;
    }

    //setters
    public void setName(String name){
        this.name = name;
    }

    public void setSurname(String surname){
        this.surname = surname;
    }

    public void setEmail(String email){
        this.email = email;
    }

    //print method
    public void PersonInfo(){
        System.out.println("Name:" + getName() +
                "\nSurname:" + getSurname() +
                "\nEmail:" + getEmail());
    }

}

