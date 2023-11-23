package JavaStart.classLessons;

public class Dog {

    String name;
    int pawn;
    void walk(){
        System.out.println("i am go !");
    }


    public Dog(String name) {
        this.name = name;
    }

    public Dog(int pawn, String name){
        this.name = name;
        this.pawn = pawn;
    }



    void howMuchPawn(){
        System.out.println(name + " have pawns " + pawn);
    }
    void setPawn(int pawn){
        this.pawn = pawn;
    }
}
