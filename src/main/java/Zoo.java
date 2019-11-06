import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Zoo {

    ArrayList<Animal> animals;

    public Zoo() {
        animals = new ArrayList<Animal>();
    }

    public void addAnimal(Animal animal){
        Random random = new Random();
        String id = String.format("%03d", random.nextInt(1000)+1);
        while(idExists(id)) {
            id = String.format("%03d", random.nextInt(1000)+1);
        }
        animal.setId(id);
        System.out.println("Animal ID created: " + id);
        if(animals.size()==999){
            System.out.println("The zoo's too packed for more!");
            return;
        }
        animals.add(animal);
    }

    public void removeAnimal(String id){
        ArrayList<Animal> temp = new ArrayList<Animal>();
        for(Animal animal: animals){
            if(animal.getId().equals(id)){
                temp.add(animal);
                System.out.println("removed animal " + animal.getId() + " " + animal.getName()+" the "+ animal.getSpecies() + " is no longer part of the Zoo." );
            }
        }
        animals.removeAll(temp);
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    public boolean idExists(String id){
        return animals.stream().anyMatch(an -> an.getId().equals(id));
    }

    public void editName(Animal animal, String name){
        animal.setName(name);
    }

    public void editAge(Animal animal, int age){
        animal.setAge(age);
    }

    public void editSpecies(Animal animal, String species){
        animal.setSpecies(species);
    }

    public Animal findByID(String id){
        for(Animal animal: animals){
            if (animal.getId().equals(id)){
                return animal;
            }
        }
        System.out.println("No animal with that id was found.");
        return null;
    }

    public void removeAllAnimals(){
        ArrayList<Animal> removalList = new ArrayList<>();
        animals.stream().map(an -> removalList.add(an)).collect(Collectors.toList());
        animals.removeAll(removalList);
    }

    public List<Animal> findByType(String type){
        List<Animal> types = new ArrayList<>();
        for(Animal animal: animals){
            if(animal.getSpecies().toLowerCase().equals(type.toLowerCase())){
                types.add(animal);
            }
        }
        return types;
    }

    public int addGroup(int count, int cash){
        AnimalList animalList = new AnimalList();
        Random random = new Random();
        int total =0;
        for(int i = 0; i<count; i++){
            if(cash<135){System.out.println("Not enough cash to buy more animals!");return 0;}
            addAnimal(new Animal(animalList.getNames().get(random.nextInt(animalList.getNames().size())), animalList.getAnimals().get(random.nextInt(animalList.getAnimals().size())), random.nextInt(20)+1));
            total += 135;
            System.out.println("#New animal bought for %65");
        }
        System.out.println(count +" new animals added!");
        return total;
    }

    public int fillZoo(int cash){
        AnimalList animalList = new AnimalList();
        Random random = new Random();
        for(int i = 0; i<100; i++){
            if(cash<135){System.out.println("Not enough cash to buy more animals!");return 0;}
            addAnimal(new Animal(animalList.getNames().get(random.nextInt(animalList.getNames().size())), animalList.getAnimals().get(random.nextInt(animalList.getAnimals().size())), random.nextInt(20)+1));
            cash -= 135;
            System.out.println("#New animal bought for %65");
        }
        System.out.println("Lots of animals in the zoo!");
        return cash;
    }


}
