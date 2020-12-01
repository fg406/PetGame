import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Cat extends Pet {

    static String[] sounds = {"mew", "miao", "hiss", "purr"};
    static String[] foods = {"fish", "tuna brick", "mouse"};

    static int[] hungerDepRange = {1,3};
    static int[] joyDepRange = {2,4};
    static int[] energyDepRange = {3,7};
    static int[] restRateRange = {5, 9};

    public Cat(String _name, List<Pet> allPets){
        super(_name, allPets, foods[new Random().nextInt(foods.length)], sounds);
        super.seteDepRate(-(new Random().nextInt(energyDepRange[1]-energyDepRange[0])+energyDepRange[0]));
        super.sethRate(-(new Random().nextInt(hungerDepRange[1]-hungerDepRange[0])+hungerDepRange[0]));
        super.setjDepRate(-(new Random().nextInt(joyDepRange[1]-joyDepRange[0])+joyDepRange[0]));
        super.setRestRate((new Random().nextInt(restRateRange[1]-restRateRange[0])+restRateRange[0]));
    }

    @Override
    public String feed(String food){
        String mess = getName() + ": " + getSound();
        if(getHunger() == 100){
            mess += "\n" + getName() + " did not eat any of the food!";
        }
        else if(getHunger() > 50){
            changeHunger(100-getHunger());
            mess += "\n" + getName() + " did not eat all of the food!";
        }
        else{
            changeHunger(50);
        }
        if(food.equals(fFood)){
            changeJoy(40);
        }
        changeEnergy(10);
        return (mess);
    }

    @Override
    protected void die(String cause){
        List<Pet> fish = allPets.stream().filter(p -> p.getClass().getSimpleName().equals("Fish")).collect(Collectors.toList());
        if(cause.equals("hunger") && !fish.isEmpty()){
            for(Pet f : fish){
                f.die(getName() + "'s hunger");
                changeHunger(15);
            }
        }
        else {
            if(getAlive()) {
                nullattribs();
                setAlive(false);
                setCauseOfDeath(cause);
            }
        }
    }
}
