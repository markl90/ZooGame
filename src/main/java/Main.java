import utils.Waits;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private long startTime;
    private int exhibits;
    private int cash;
    private int earnings;
    private int cashedTime;
    private int uncashedMultiplier;
    private boolean open = false;
    private float popularity;
    private int customers;
    private int collectedTickets;

    public String capFirst(String input){
        String word = input;
        String cap = word.substring(0, 1).toUpperCase()+ word.substring(1);
        return cap;
    }

    public Main(Scanner scanner) {  Zoo zoo = new Zoo();
        System.out.println("please type add to add, remove to remove, or edit to edit.");
        System.out.println("Starter cash $5000.");
        cash = 5000;
        while(scanner.hasNext()){
            String value = "";
            System.out.println("please type add to add, remove, edit, show all, group, open, quit, cash, fill, empty, find, type");

            value = scanner.next();
            switch(value.toLowerCase()){
                case "add":
                    System.out.println("add an animal, first line is name, second line is species");
                    String name = scanner.next();
                    String cap = name.substring(0, 1).toUpperCase()+ name.substring(1);
                    String species = scanner.next();
                    if(cash<135){
                        System.out.println("Sorry, you don't have enough cash right now to buy an animal, \n Maybe later....");
                        break;
                    }
                    zoo.addAnimal(new Animal(cap, species));
                    cash -= 135;
                    System.out.println("new animal " + name + " added. " + name +" is a " + species);
                    System.out.println("The zoo has " + zoo.getAnimals().size() + " animals.");
                    break;
                case "show":
                    System.out.println("Here are all the animals that are in the zoo.");
                    System.out.println(zoo.getAnimals().toString()+"\n");
                    System.out.println("There are " + zoo.getAnimals().size() + " animals in total.");
                    break;
                case "edit":
                    System.out.println("Please enter the id of the animal you'd like to edit.");
                    String id = scanner.next();
                    Animal animal = zoo.findByID(id);
                    if(animal!=null){
                        System.out.println("Animal "+ animal.getId() + " found. " + animal.getName()+ " the " + animal.getSpecies());
                        System.out.println("Please type the attribute you wish to edit (name, species, age), return to return to the previous menu.");
                        String attribute = scanner.next();


                        switch(attribute){
                            case "name":
                                System.out.println("Please choose a new name: ");
                                zoo.editName(animal, capFirst(scanner.next()));
                                System.out.println("Animal " + animal.getId() + "'s name was changed to: " + animal.getName());
                                break;
                            case "age":
                                System.out.println("Please choose a new name: ");
                                zoo.editAge(animal, scanner.nextInt());
                                System.out.println("Animal " + animal.getId() + "'s age was changed to: " + animal.getAge());
                                break;
                            case "species":
                                System.out.println("Please choose a new name: ");
                                zoo.editSpecies(animal, capFirst(scanner.next()));
                                System.out.println("Animal " + animal.getId() + "'s species was changed to: " + animal.getSpecies());
                                break;
                        }

                    }
                    break;

                case "remove":
                    System.out.println("To remove an animal, please type the in id.");
                    String removeId = scanner.next();
                    zoo.removeAnimal(removeId);
                    System.out.println("The zoo has " + zoo.getAnimals().size() + " animals.");
                    break;
                case "find":
                    System.out.println("Please type the in id of the animal you'd like to find.");
                    String findId ="";
                    try{
                        findId = scanner.next();
                    } catch (InputMismatchException e){
                        System.out.println("Finder was expecting 3 digit ID.");
                    }
                    Animal animalFound = zoo.findByID(findId);
                    if(animalFound!=null) {
                        System.out.println(animalFound.toString());
                    }
                    int count = 0;
                    while(animalFound==null && count<3){
                        System.out.println("Please enter the ID of the animal you'd like to find.");
                        try{
                            animalFound = zoo.findByID(scanner.next());
                        } catch (InputMismatchException e){
                            System.out.println("Finder was expecting 3 digit ID.");
                        }
                        count++;
                        if(count%2==0) {
                            System.out.println("exit find? (y/n)");
                            if (scanner.next().equals("y")) {
                                break;
                            }
                        }
                    }
                    break;

                case "empty":
                    System.out.println("Now emptying the Zoo.");
                    zoo.removeAllAnimals();
                    break;
                case "fill":
                    cash = zoo.fillZoo(cash);
                    break;
                case "type":
                    System.out.println("What type of animal are you searching for?");
                    List types = zoo.findByType(scanner.next());
                    if(types.isEmpty()){
                        System.out.println("We didn't find any animals of that type.");
                    }
                    while(types.isEmpty()){
                        System.out.println("Try again? (y/n)");
                        String choice = scanner.next();
                        if(choice.equals("n"))break;
                        types = zoo.findByType(scanner.next());
                    }
                    System.out.println(types.toString());
                    System.out.println(types.size()+" animals found.");
                    break;
                case "listen":
                    int counter = 0;
                    System.out.println("what do you hear?");
                    while(counter<4) {
                        if(!zoo.getAnimals().isEmpty()) {
                            Random random = new Random();
                            try {
                                Thread.sleep(3500);
                                System.out.println(".....");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            new Animal().makeNoise(random.nextInt(25)+1);
                            counter++;
                        } else {
                            System.out.println(".....");
                            System.out.println(".........");
                            System.out.println(".........I don't think there's anything in the zoo yet!");
                            break;
                        }
                    }
                    break;
                case "count":
                    System.out.println("There are "+ zoo.getAnimals().size() + " animals currently in the zoo.");
                    break;
                case "quit":
                    System.out.println("Leaving the Zoo!");
                    scanner.close();

                    long end = System.currentTimeMillis();
                    float sec = (end - startTime) / 1000F;
                    System.out.println(sec + " seconds");
                    if(!open){
                        sec = 0;
                    }
                    System.out.println("Well done you, kept the Zoo open for "+ sec + " seconds!");
                    System.out.println("Total customers attracted was: "+ customers);
                    System.out.println("Popularity level: "+ popularity);
                    System.out.println("Final cash: $"+ cash);
                    System.out.println("Total exhibits: "+ exhibits);
                    System.out.println("Total animals: "+ zoo.getAnimals().size());
                    return;
                case "group":
                    System.out.println("Lets add a group of animals to the Zoo.");
                    System.out.println("How many would you like to add?");
                    try{
                        cash -= zoo.addGroup(scanner.nextInt(), cash);
                    }catch (InputMismatchException e){
                        System.out.println("Group was expecting an integer.");
                    }
                    break;
                case "bait":
                    System.out.println("You've laid some bait, lets see what it attracts!");
                    Waits.wait(3);
                    Random random = new Random();
                    if(zoo.getAnimals().size()!=0){
                        Animal ani = zoo.getAnimals().get(random.nextInt(zoo.getAnimals().size()+1));
                        System.out.println(ani.toString());
                        System.out.println("It's a " + ani.getSpecies() + "!");

                    } else {
                        Waits.wait(2);
                        System.out.println("It's doesn't look like there are any animals in the Zoo yet...");
                        Waits.wait(3);
                        System.out.println("Better try another time...");
                    }
                    break;
                case "open":
                    if(open) {
                        System.out.println("The park is already open!");
                        break;
                    }
                    if(zoo.getAnimals().size()==0){
                        System.out.println("We can't open without any animals!");
                    } else {
                        System.out.println("Everything's ready, open up? that's a good idea!");
                        System.out.println("Opening day events will cost %500, do you want to go ahead? (y/n)");
                        if(scanner.next().equals("y")){
                            if(cash<500){
                                System.out.println("Not enough funds! You'll have to try again another time!");
                                break;
                            } else {
                                cash -= 500;
                                System.out.println("$500 spent on opening event.");
                                System.out.println("$"+cash+"remaining.");
                            }
                        }
                        Waits.wait(3);
                        System.out.println("(The gates creak open...)");
                        Waits.wait(1);
                        System.out.println("The Zoo's open for business!");
                        startTime = System.currentTimeMillis();
                        long end1 = System.currentTimeMillis();
                        float sec1 = (end1 - startTime) / 1000F;
                        Waits.stopWatch(startTime);
                        open=true;
                        System.out.println("The opening events went well, you're getting customer's attention!");
                        break;
                    }
                case "time":
                    System.out.println("Check on how long you've been open.");
                    Waits.openingTime(startTime);
                    break;
                case "exhibit":
                    System.out.println("We'll need to add exhibits to interest more customers.");
                    System.out.println("The cost of a new exhibit is $600. Would you lik to buy one? (y/n)");
                    String answer = scanner.next();
                    if(answer.equals("y")){
                        System.out.println("Ok Good, checking funds...");
                        Waits.wait(2);
                        if(cash>600){
                            System.out.println("We'll done, you just bought a brand new exhibit...");
                            exhibits++;
                            System.out.println("You now have "+ exhibits+ " exhibits!");
                            cash -= 600;
                            break;
                        } else {
                            System.out.println("Sorry it doesn't look like you have enough cash for a new exhibit...");
                            System.out.println("Maybe another time...");
                            break;
                        }
                    }
                case "cash":
                    System.out.println("You have $"+ cash +" cash left.");
                    break;
                case "earn":
                    if(!open){
                        System.out.println("You'll need to open before you can start earning money!");
                        break;
                    }
                    System.out.println("Increasing animals and exhibits will let you earn more.");
                    System.out.println("Keep the Zoo open as long as you can!");
                    uncashedMultiplier =  (int)(Waits.openingTime(startTime)/60) - cashedTime;
                    earnings = (zoo.getAnimals().size() * exhibits * uncashedMultiplier);
                    cashedTime = (int)(Waits.openingTime(startTime)/60);
                    System.out.println("Earnings since your last visit are $"+ earnings);
                    cash += earnings;
                    System.out.println("Total funds remaining: $"+cash);
                    break;
                case "forage":
                    System.out.println("Desperate times call for desperate measures...");
                    System.out.println("Check the park grounds for loose cash (y/n)");
                    if(scanner.next().equals("y")){
                        Random ran = new Random();
                        int chance = ran.nextInt(50)+1;
                        System.out.println("Searching the park....");
                        Waits.wait(5);
                        System.out.println("Searching the park.......");
                        Waits.wait(3);
                        System.out.println("Searching the park..........");
                        Waits.wait(2);
                        if(chance>25){
                            int found = ran.nextInt(100)+1;
                            if(!open){ found = found / 3 ;
                                System.out.println("You found $"+ found);
                                cash += found;
                                System.out.println("Total cash is now $"+cash);
                                break;
                            } else {
                                System.out.println("Unfortunately you didn't find anything!");
                                System.out.println("Better luck next time!");
                                break;
                            }

                        }
                    }
                case "reset":
                    scanner.reset();
                    break;
                case "promote":
                    System.out.println("Promote the park to increase the number of customers!");
                    System.out.println("How much do you want to spend on promotion?");
                    int budget = scanner.nextInt();
                    if(budget<cash){
                        Random rand = new Random();
                        cash -= budget;
                        popularity += (rand.nextInt(20)+1 * budget);
                    }
                    break;
                case "popularity":
                    popularity = (Waits.openingTime(startTime)/60) * (zoo.getAnimals().size()/2) * (exhibits);
                    System.out.println("Popularity rating is currently "+ popularity);
                    break;
                case "customers":
                    customers = zoo.getAnimals().size() * exhibits;
                    customers = customers * (int)popularity;
                    System.out.println("Total number of customers is "+ customers);
                    break;
                case "clean":
                    System.out.println("You need to keep the park clean to stay popular.");
                    System.out.println("Send in a cleaning crew for %250? (y/n)");
                    float unlceaned =0;
                    if(open){
                        unlceaned = Waits.openingTime(startTime);
                    }
                    if(scanner.next().equals("y")){
                        if(cash>250){
                            unlceaned = 0;
                            cash -= 250;
                            System.out.println("Park is good as new again!");
                            break;
                        } else {
                            customers  -= (int)unlceaned * Waits.openingTime(startTime);
                            System.out.println("Customers are leaving because of the mess.");
                            System.out.println(customers+" customers remaining.");
                            break;
                        }
                    }
                case "tickets":
                    System.out.println("Ticket sales depend on the number of customers.");
                    if(!open){
                        System.out.println("The park needs to be open to collect ticket sales.");
                        break;
                    }
                    int sales = (customers * 4)- collectedTickets;
                    cash += sales;
                    System.out.println("Total ticket sales $"+ sales);
                    System.out.println("Cash after sales collected $"+ cash);
                    collectedTickets = customers;
                    break;
            }
        }
    }

    public static void main(String[] args) {
        Main main = new Main(new Scanner(System.in));
    }
}
