import java.util. *;
/**
 * @author Adam Whaley and
 */

public class Main {

    static ArrayList<Dog> inventoryDogs = null;
    static ArrayList<Cat> inventoryCats = null;
    static ArrayList<ExoticPet> inventoryExotics = null;
    static int petTypeChoice = 0;
    static int inventory  = 0;
    static ArrayList<Dog> cartDogs = new ArrayList<>();
    static ArrayList<Cat> cartCats = new ArrayList<>();
    static ArrayList<ExoticPet> cartExotics = new ArrayList<>();
    static int cartTotal = 0;
    static int adoptionChoice = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System. in);
        PetStore ps = new PetStore("Hallow Pet Store"); // Give your store a name!
        while (true) {      // keep running until terminated
            System.out.println("**** Welcome to " + ps.getStoreName() + "****");
            System.out.println("Select an Options");
            System.out.println("\t1. Buy a new pet");
            System.out.println("\t2. Register a new member");
            System.out.println("\t3. Start adoption Drive (add new pets)");
            System.out.println("\t4. Check current inventory");
            System.out.println("\t5. Register new Pet to Owner Profile");
            System.out.println("\t6. Exit");

            int choice1 = scanner.nextInt();
            switch (choice1) {
                case 1:
                    System.out.println("-----------------------------------");
                    if(purchase(ps, scanner, 0)) {
                        System.out.println();
                    }
                    break;
                case 2:
                    System.out.println("-----------------------------------");
                    registerNewMember(ps, scanner);
                    break;
                case 3:
                    System.out.println("-----------------------------------");
                    adoptPet(ps);
                    break;
                case 4:
                    System.out.println("-----------------------------------");
                    checkCurrentInventory(ps);
                    break;
                case 5:
                    break;
                case 6:
                    System.out.println("-----------------------------------");
                    System.out.println("Thanks for visiting!");
                    return;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }

    private static void adoptPet(PetStore petStore){
        System.out.println("Please which animal do you want to adopt");
        System.out.println("\t1. Dog");
        System.out.println("\t2. Cat");
        System.out.println("\t3. Exotic Pet");
        System.out.println("\t4. Exit");
        inventoryDogs = petStore.getAvailableDogs();
        inventoryCats = petStore.getAvailableCats();
        inventoryExotics = petStore.getAvailableExoticPets();

        Scanner scanner = new Scanner(System.in);
        int choice1 = scanner.nextInt();
        switch (choice1) {
            case 1 -> {
                System.out.println("-----------------------------------");
                System.out.println("Which Breed of Dog do you want to adopt");
                ArrayList<Dog> inventory1 = petStore.getAvailableDogs();
                if (!inventory1.isEmpty()) {
                    int itemNum = 1;
                    for (Dog pet : inventory1) {
                        System.out.println("\t" + itemNum + ". " + pet.getBreed() + "(" + pet.getName() + ")");
                        itemNum++;
                    }
                    int choice = scanner.nextInt();
                    if(choice <=inventory1.size() && choice > 0) {
                        cartDogs.add(inventoryDogs.get(choice - 1));
                        adoptionChoice = choice - 1;
                        checkout(petStore, scanner, cartDogs, cartCats, cartExotics, 2);    // 2 for adoption
                    }
                    else {
                        System.out.println("Sorry, invalid choice. Retry: ");
                        adoptPet(petStore);
                    }
                }
                else {
                    System.out.println("Sorry, we are out of stock");
                    adoptPet(petStore);
                }
            }
            case 2 -> {
                System.out.println("-----------------------------------");
                System.out.println("Which Breed of Cat do you want to adopt");
                ArrayList<Cat> inventory1 = petStore.getAvailableCats();
                if (!inventory1.isEmpty()) {
                    int itemNum = 1;
                    for (Cat pet : inventory1) {
                        System.out.println("\t" + itemNum + ". " + pet.getBreed() + "(" + pet.getName() + ")");
                        itemNum++;
                    }
                    int choice = scanner.nextInt();
                    if(choice <=inventory1.size() && choice > 0) {
                        cartCats.add(inventoryCats.get(choice - 1));
                        adoptionChoice = choice - 1;
                        checkout(petStore, scanner, cartDogs, cartCats, cartExotics, 2);    // 2 for adoption
                    }
                    else {
                        System.out.println("Sorry, invalid choice. Retry: ");
                        adoptPet(petStore);
                    }
                }
                else {
                    System.out.println("Sorry, we are out of stock");
                    adoptPet(petStore);
                }
            }

            case 3 -> {
                System.out.println("-----------------------------------");
                System.out.println("Which Breed of Exotic Pet do you want to adopt");
                ArrayList<ExoticPet> inventory1 = petStore.getAvailableExoticPets();
                if (!inventory1.isEmpty()) {
                    int itemNum = 1;
                    for (ExoticPet pet : inventory1) {
                        System.out.println("\t" + itemNum + ". " + pet.getBreed() + "(" + pet.getName() + ")");
                        itemNum++;
                    }
                    int choice = scanner.nextInt();
                    if(choice <=inventory1.size() && choice > 0) {
                        cartExotics.add(inventoryExotics.get(choice - 1));
                        adoptionChoice = choice - 1;
                        checkout(petStore, scanner, cartDogs, cartCats, cartExotics, 2);    // 2 for adoption
                    }
                    else {
                        System.out.println("Sorry, invalid choice. Retry: ");
                        adoptPet(petStore);
                    }
                }
                else {
                    System.out.println("Sorry, we are out of stock");
                    adoptPet(petStore);
                }
            }

            default -> System.out.println("Invalid choice, try again.");
        }
    }

    private static boolean purchase(PetStore petStore, Scanner scanner, int state) {
        String pet = "";
        if(state == 0) {
            inventory = 0;
            System.out.println("Choose Pet To Purchase?");
            System.out.println("\t1. Dogs");
            System.out.println("\t2. Cats");
            System.out.println("\t3. Exotic Pets");
            if(cartDogs.size() > 0 || cartCats.size() > 0 || cartExotics.size() > 0){       // just in case they want to check out
                System.out.println("\t4. Checkout");
            }
            petTypeChoice = scanner.nextInt();
            // display inventory menu
            int itemNum = 1;
            if (petTypeChoice == 1) {   // DOGS
                inventoryDogs = petStore.getAvailableDogs();
                if(!inventoryDogs.isEmpty())
                    inventory = inventoryDogs.size();
                pet = "Dog";
            }
            else if(petTypeChoice == 2) {       // CATS
                inventoryCats = petStore.getAvailableCats();
                if(!inventoryCats.isEmpty())
                    inventory = inventoryCats.size();
                pet = "Cat";
            }
            else if (petTypeChoice == 3) {    // EXOTIC PETS
                inventoryExotics = petStore.getAvailableExoticPets();
                if(!inventoryExotics.isEmpty())
                    inventory = inventoryExotics.size();
                pet = "Exotic Pet";
            }
            else if(petTypeChoice == 4 && inventory > 0) {
                return checkout(petStore, scanner, cartDogs, cartCats, cartExotics, 1);     // 1 for purchase
            }
            else {
                purchase(petStore, scanner, 0);
            }

            if(inventory > 0) {
                System.out.println("Choose Type of " + pet);
                if (petTypeChoice == 1) {
                    for (Dog eachDog : inventoryDogs) {
                        System.out.println("\t" + itemNum++ + ". $" + eachDog.getPrice() + " - " + eachDog.getBreed() + "(" + eachDog.getName() + ")");
                    }
                } else if (petTypeChoice == 2) {  // Cat
                    for (Cat eachCat : inventoryCats) {
                        System.out.println("\t" + itemNum++ + ". $" + eachCat.getPrice() + " - " + eachCat.getBreed() + "(" + eachCat.getName() + ")");
                    }
                } else if (petTypeChoice == 3) {      // EP
                    for (ExoticPet eachEx : inventoryExotics) {
                        System.out.println("\t" + itemNum++ + ". $" + eachEx.getPrice() + " - " + eachEx.getBreed() + "(" + eachEx.getName() + ")");
                    }
                }
            }
            else {
                System.out.println("Sorry, we are out of stock for " + pet + "s");
                purchase(petStore, scanner, 0);
            }
        }

        if (inventory > 0) {        // this pet list is not empty
            int choice = 0;
            // get user selection for pet to add arraylist (cart)
            if(state != 2) {
                choice = scanner.nextInt();
                if (choice <= inventory && choice > 0) {
                    if (petTypeChoice == 1)
                        cartDogs.add(inventoryDogs.get(choice - 1));
                    else if (petTypeChoice == 2)
                        cartCats.add(inventoryCats.get(choice - 1));
                    else if (petTypeChoice == 3)
                        cartExotics.add(inventoryExotics.get(choice - 1));
                    cartTotal++;            // increase cart size for each choice
                }
                else {
                    System.out.println("Sorry, invalid choice. Retry: ");
                    purchase(petStore, scanner, 1);         // skip initial step
                }
                //cart summary
                System.out.println("You have " + cartTotal + " items in your Cart. Are you done shopping?");
            }
            //update inventory for this pet choice
            petStore.removePet(pet, choice - 1);
            System.out.println("\t1. Yes");
            System.out.println("\t2. No");

            int doneShopping = scanner.nextInt();
            if (doneShopping == 1) {
                return checkout(petStore, scanner, cartDogs, cartCats, cartExotics, 1); // 1 for purchase
            }
            else if (doneShopping == 2) {            // more shopping
                purchase(petStore, scanner, 0); // recursively call purchase(...) until done
            }
            else {
                System.out.println("Invalid Choice. Continue Shopping?");
                purchase(petStore, scanner, 2);
            }
        }
        else {
            System.out.println("Sorry we are out of stock. Choose another Pet");
            purchase(petStore, scanner, 0);
        }
        return false;
    }

    private static boolean checkout(PetStore petStore, Scanner scanner, ArrayList<Dog> dogs, ArrayList<Cat> cats, ArrayList<ExoticPet> exotic_pets, int type) {
        // calculate total
        double total = 0;
        if(!dogs.isEmpty()) {   // dogs were added to cat
            for (Dog pet : dogs) {
                total += pet.getPrice();
            }
        }
        if(!cats.isEmpty()) {   // cats were added to cat
            for (Cat pet : cats) {
                total += pet.getPrice();
            }
        }
        if(!exotic_pets.isEmpty()) {   // cats were added to cat
            for (ExoticPet pet : exotic_pets) {
                total += pet.getPrice();
            }
        }

        if(type == 1) {
            System.out.println("Your total Purchase is $" + total + ". \nPlease select which member is making this" + " purchase.");
        }
        else {
            System.out.println("Who is Adopting this Pet?");
        }

        // list members and option to register
        int num = 1;
        for (Member member : petStore.getMemberList()) {
            System.out.println(num + ". " + member.getName());
            num++;
        }
        for (PremiumMember member : petStore.getPremiumMemberList()) {
            System.out.println(num + ". " + member.getName());
            num++;
        }
        System.out.println(num + ". Register a new Member.");

        System.out.println(); // space line
        int memberSelect = scanner.nextInt();
        Member purchaser = null;
        PremiumMember premiumPurchaser = null;

        if (memberSelect > petStore.getMemberList().size() + petStore.getPremiumMemberList().size() + 1) { // invalid selection
            System.out.println("Invalid Selection");
            checkout(petStore, scanner, dogs, cats, exotic_pets, type); // recursive call if valid user input
        }
        else { // valid selection
            if (memberSelect == (petStore.getMemberList().size() + petStore.getPremiumMemberList().size()) + 1) { // adding a new member
                boolean premium = registerNewMember(petStore, scanner);
                if (premium) {
                    premiumPurchaser = petStore.getPremiumMemberList().get(petStore.getPremiumMemberList().size() - 1);
                }
                else {
                    purchaser = petStore.getMemberList().get(petStore.getMemberList().size() - 1);
                }
            }
            else if (memberSelect <= (petStore.getMemberList().size())) {
                purchaser = petStore.getMemberList().get(memberSelect - 1);
            }
            else { // existing premium member
                premiumPurchaser = petStore.getPremiumMemberList().get(memberSelect - petStore.getMemberList().size() - 1);
            }

            if(type == 1) {
                // check if premium member and fees are due
                if (premiumPurchaser != null) {
                    if (!premiumPurchaser.isDuesPaid()) {
                        System.out.println("Premium Membership dues unpaid, $5 will be added to purchase to cover dues.");
                        total += 5;
                    }
                    premiumPurchaser.setDuesPaid(true);
                    // update amount of purchases for this member
                    premiumPurchaser.setAmountSpent(total); // done
                    System.out.println("Your purchase total was: " + total);
                    System.out.println("Congrats on your purchase " + premiumPurchaser.getName());
                } else {        // update amount of purchases for this member
                    assert purchaser != null;
                    purchaser.setAmountSpent(total);
                    System.out.println("Your purchase total was: " + total);
                    System.out.println("Congrats on your purchase " + purchaser.getName());
                }
            }
            else {      // adoption
                if(premiumPurchaser != null)
                    System.out.println("Congrats on your Adoption " + premiumPurchaser.getName());
                else {
                    assert purchaser != null;
                    System.out.println("Congrats on your Adoption " + purchaser.getName());
                }
                if(adoptionChoice > -1) {
                    if(!dogs.isEmpty())
                        petStore.removePet("Dog", adoptionChoice);
                    else if (!cats.isEmpty())
                        petStore.removePet("Cat", adoptionChoice);
                    else if (!exotic_pets.isEmpty())
                        petStore.removePet("Exotic Pet", adoptionChoice);
                }
            }
            // empty the carts
            cartDogs.clear();
            cartCats.clear();
            cartExotics.clear();
            System.out.println();
            return true;
        }
        return false;
    }

    private static boolean registerNewMember(PetStore petStore, Scanner scanner) {
        // prompt user to select membership type
        System.out.println("Let's get you registered as a new Member!");
        System.out.println( "Would you like to register as a free-tier or premium member?");
        System.out.println("\t1. Free-tier");
        System.out.println("\t2. Premium");

        // user selection
        int choice = scanner.nextInt();
        // if user selects a wrong choice
        if (choice > 2 || choice < 1) {
            System.out.println("Invalid choice.");
            registerNewMember(petStore, scanner); // try again
        } else {
            // prompt user for name
            System.out.println("Please enter your name:");
            scanner.nextLine();
            String name = scanner.nextLine();
            System.out.println("Welcome to our membership program! Thank you for registering.");
            if (choice == 1) { // regular membership
                petStore.addNewMember(name, false);
                return false;
            } else { // user entered 2 - premium membership
                petStore.addNewMember(name, true);
                return true;
            }
        }
        return false;
    }

    private static void checkCurrentInventory(PetStore petStore) {
        System.out.println("CURRENT PET INVENTORY");
        inventoryDogs = petStore.getAvailableDogs();
        inventoryCats = petStore.getAvailableCats();
        inventoryExotics = petStore.getAvailableExoticPets();
        System.out.println("DOGS - (" + inventoryDogs.size() + ")");
        int itemNum = 1;
        for (Dog eachDog : inventoryDogs) {
            System.out.println("\t" + itemNum++ + ". $" + eachDog.getPrice() + " - " + eachDog.getBreed() + "(" + eachDog.getName() + ")");
        }
        System.out.println("CATS - (" + inventoryCats.size() + ")");
        itemNum = 1;
        for (Cat eachCat : inventoryCats) {
            System.out.println("\t" + itemNum++ + ". $" + eachCat.getPrice() + " - " + eachCat.getBreed() + "(" + eachCat.getName() + ")");
        }
        System.out.println("EXOTIC PET - (" + inventoryExotics.size() + ")");
        itemNum = 1;
        for (ExoticPet eachEx : inventoryExotics) {
            System.out.println("\t" + itemNum++ + ". $" + eachEx.getPrice() + " - " + eachEx.getBreed() + "(" + eachEx.getName() + ")");
        }
        System.out.println();
    }

}