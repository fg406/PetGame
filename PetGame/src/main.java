import java.util.ArrayList;
import java.util.List;

public class main {

    private static Out out;
    private static String[] petTypes = {"Cat", "Fish", "Ninja"};

    private static List<Pet> myPets  = new ArrayList<>();
    private static List<Pet> pastPets = new ArrayList<>();
    private static int chInt;
    private static int timeInMins = 0;

    public static void main(String[] args) {
        out = TextUI.getInstance();

        while (true){
            out.outputTime(timeInMins);
            if(myPets.isEmpty()){
                out.outputMessage("You have no pets!");
                myPets.add(getPet(false, true));
                incrementTime(30);
            }
            else{
                // Asks which pet to look at
                out.outputAllPets(myPets);
                try{
                    chInt = Integer.parseInt(out.input());
                    if(chInt < 0 || chInt > myPets.size() + 2){
                        throw new NoPets("You don't have that many pets!");
                    }
                    else if(chInt == myPets.size()){
                        Pet newPet = getPet(false, false);
                        if(newPet != null){
                            myPets.add(newPet);
                        }
                        incrementTime(30);
                    }
                    else if(chInt == myPets.size() + 1){
                        out.outputPastPets(pastPets);
                    }
                    else {
                        Pet cur = myPets.get(chInt);
                        if (!cur.getAlive()) {
                            pastPets.add(cur);
                            myPets.remove(cur);
                            throw new NoPets("That pet is dead!");
                        }
                        else if(cur.getClass().getSimpleName().equals("Ninja") && ((Ninja) cur).getEscaped()){
                            pastPets.add(cur);
                            cur.setCauseOfDeath("escaped");
                            myPets.remove(cur);
                            throw new NoPets(cur.getName() + " has escaped!");
                        }
                        else {
                            chosenPet(cur);
                            incrementTime(10);
                        }
                    }
                }
                catch(NoPets np){
                    out.outputMessage(np.getMessage());
                }
                catch(Exception e){
                    out.outputMessage("Please choose a number!");
                }
            }
        }
    }

    public static void chosenPet(Pet pet){
        out.outputState(pet);
        List<String> options = new ArrayList<>();
        options.addAll(pet.getInteractions());
        options.add("back");
        out.outputOptions(options);
        String choice = out.input();
        try{
            int choiceInt = Integer.parseInt(choice);
            while(choiceInt < 0 || choiceInt > options.size()-1){
                out.outputMessage("Please make a valid choice!");
                choice = out.input();
                choiceInt = Integer.parseInt(choice);
            }
            choice = options.get(choiceInt);
            if(choice.equals("play")){
                out.outputMessage(pet.play());
            }
            else if(choice.equals("sleep")){
                out.outputMessage(pet.sleep());
            }
            else if(choice.equals("feed")){
                out.outputMessage("What would you like to feed your pet?");
                out.outputMessage(pet.feed(out.input()));
            }
        }
        catch(Exception e){
            out.outputMessage("Please choose a valid number!");
            chosenPet(pet);
        }

    }

    public static Pet getPet(Boolean repeated, Boolean firstTime){
        if(!repeated) {
            out.outputPetStore(petTypes, firstTime);
        }
        String response = out.input();
        try{
            int choice = Integer.parseInt(response);
            if( choice < 0 || (choice > petTypes.length-1 && firstTime) || (choice > petTypes.length && !firstTime)){
                throw new NoPets("That's not an option!");
            }
            else if(!firstTime && choice == petTypes.length){
                return null;
            }
            else{
                out.outputMessage("What do you want to call your new " + petTypes[choice] + "?");
                if(choice == 0){
                    return new Cat(out.input(), myPets);
                }
                else if(choice == 1){
                    return new Fish(out.input(), myPets);
                }
                else if(choice == 2){
                    return new Ninja(out.input(), myPets);
                }
            }
        }
        catch (NoPets np){
            out.outputMessage(np.getMessage());
            getPet(true,  firstTime);
        }
        catch (NumberFormatException e){
            out.outputMessage("Please choose a number!");
            getPet(true, firstTime);
        }
        catch (Exception e) {
            getPet(true, firstTime);
        }
        return null;
    }

    private static void incrementTime(int time){
        timeInMins += time;
        for(Pet p : myPets){
            if(p.getAlive()) {
                p.timeChange(time);
                if(!p.getAlive()){
                    pastPets.add(p);
                    myPets.remove(p);
                    out.outputMessage(p.getName() + " has died of " + p.getCauseOfDeath() + "!");
                }
            }
        }
    }
}
