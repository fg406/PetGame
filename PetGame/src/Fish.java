import java.util.List;
import java.util.Random;

public class Fish extends Pet {


    static String[] sounds = {"blub", "blub blub", ".o0", "bub"};
    static String[] foods = {"fish", "algae", "rocks"};

    static int[] hungerDepRange = {1,2};
    static int[] joyDepRange = {1,2};
    static int[] energyDepRange = {1,3};
    static int[] restRateRange = {1, 6};

    public Fish(String _name, List<Pet> allPets){
        super(_name, allPets, foods[new Random().nextInt(foods.length)], sounds);
        super.seteDepRate(-(new Random().nextInt(energyDepRange[1]-energyDepRange[0])+energyDepRange[0]));
        super.sethRate(-(new Random().nextInt(hungerDepRange[1]-hungerDepRange[0])+hungerDepRange[0]));
        super.setjDepRate(-(new Random().nextInt(joyDepRange[1]-joyDepRange[0])+joyDepRange[0]));
        super.setRestRate((new Random().nextInt(restRateRange[1]-restRateRange[0])+restRateRange[0]));
    }
}
