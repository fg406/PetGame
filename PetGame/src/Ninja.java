import java.util.List;
import java.util.Random;

public class Ninja extends Pet{


    static String[] sounds = {"...", "", "(silent judgement)", "No."};
    static String[] foods = {"secrecy", "sushi", "pizza"};

    static int[] hungerDepRange = {1,3};
    static int[] joyDepRange = {1,15};
    static int[] energyDepRange = {1,9};
    static int[] restRateRange = {3, 9};

    Boolean escaped;

    public Ninja(String _name, List<Pet> allPets){
        super(_name, allPets, foods[new Random().nextInt(foods.length)], sounds);
        super.seteDepRate(-(new Random().nextInt(energyDepRange[1]-energyDepRange[0])+energyDepRange[0]));
        super.sethRate(-(new Random().nextInt(hungerDepRange[1]-hungerDepRange[0])+hungerDepRange[0]));
        super.setjDepRate(-(new Random().nextInt(joyDepRange[1]-joyDepRange[0])+joyDepRange[0]));
        super.setRestRate((new Random().nextInt(restRateRange[1]-restRateRange[0])+restRateRange[0]));
        this.escaped = false;
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
    public int[] timeChange(int time){
        time = time/10;
        if(isAsleep()){
            changeJoy(time * getjDepRate() / 3);
            changeHunger(time * gethRate());
            changeEnergy(time * restRate);
            if(getEnergy() >= 100){
                wakeUp();
            }
        }
        else {
            if (!jChanged) {
                changeJoy(time * getjDepRate());
            }
            if (!hChanged) {
                changeHunger(time * gethRate());
            }
            if (!eChanged) {
                changeEnergy(time * geteDepRate());
            }
        }
        jChanged = false;
        hChanged = false;
        eChanged = false;
        if(new Random().nextInt(1000) > 995){
            escaped = true;
        }
        return getStats();
    }

    public Boolean getEscaped(){
        return escaped;
    }
}
