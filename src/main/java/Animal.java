import java.util.Random;

public class Animal {

    private String name;
    private int age;
    private String species;
    String id;

    public Animal() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = String.format("%03d", Integer.valueOf(id));
    }

    public Animal(String name, String species) {
        this.name = name;
        this.species = species;
    }

    public Animal(String name, String species, int age) {
        this.name = name;
        this.age = age;
        this.species = species;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    @Override
    public String toString() {
        return String.format("Id: %-5s Name: %-9s  Species: %-10s  Age: %-3s  \n", id, name, species, age);
//        return "\nAnimal{" +
//                "Name:'" + name + '\'' +
//                ", Age:" + age +
//                ", Species:'" + species + '\'' +
//                ", ID:" + id +
//                '}'+"";
    }

    public void makeNoise(int noise){
        switch(noise){
            case 1:
                System.out.println("Grrrr...");
                break;
            case 2:
                System.out.println("Rawwwwwwr...");
                break;
            case 3:
                System.out.println("Quack...Quack...");
                break;
            case 4:
                System.out.println("Baaa...Baaa...");
                break;
            case 5:
                System.out.println("Sssss...Ssss...");
                break;
            case 6:
                System.out.println("Cheep...Cheep...");
                break;
            case 7:
                System.out.println("zeet...zzZZeet...");
                break;
            case 8:
                System.out.println("Bzzz...BzzzzZZzz...");
                break;
            case 9:
                System.out.println("Rawwwwwwr...");
                break;
            case 10:
                System.out.println(".......");
                break;
            case 11:
                System.out.println("fssh...");
                break;
            case 12:
                System.out.println("flap...flap");
                break;
            case 13:
                System.out.println("Sssss...Ssss...");
                break;
            case 14:
                System.out.println("eeooorh....");
                break;
            case 15:
                System.out.println("Hi there....");
                break;
            case 16:
                System.out.println("Oink....Oink...");
                break;
            case 17:
                System.out.println("Snap....SNap...");
                break;
            case 18:
                System.out.println("Ribbbet....Ribbet...");
                break;
            case 19:
                System.out.println("flap...flap");
                break;
            case 20:
                System.out.println("Swish...");
                break;
            case 21:
                System.out.println("Grrrr...Rawwwwwwr...");
                break;
            case 22:
                System.out.println("Pawoo. Pawoo. Pawoo!");
                break;
            case 23:
                System.out.println("Bleat. Bleat!");
                break;
            case 24:
                System.out.println("Splash..........zzzt!");
                break;
        }
    }
}
