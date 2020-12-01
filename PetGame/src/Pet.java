import java.util.ArrayList;
import java.util.List;
import java.util.Random;

abstract class Pet {

    protected String Name;
    protected int energy;
    protected int joy;
    protected int hunger;

    protected Boolean asleep;

    protected Boolean alive;
    protected String causeOfDeath;

    protected int eDepRate;
    protected int jDepRate;
    protected int hRate;
    protected int restRate;

    protected Boolean jChanged = false;
    protected Boolean hChanged = false;
    protected Boolean eChanged = false;

    protected String fFood;
    public String[] sounds;
    protected List<String> interactions;

    protected final List<Pet> allPets;

    public Pet(String _name, List<Pet> allPets){
        this.Name = _name;
        this.energy = 75;
        this.joy = 75;
        this.hunger = 75;
        this.alive = true;
        this.asleep = false;
        this.interactions = new ArrayList<>(List.of("play", "sleep", "feed"));
        this.allPets = allPets;
    }

    public Pet(String _name, List<Pet> allPets,String _fFood, String[] _sounds){
        this.Name = _name;
        this.energy = 75;
        this.joy = 75;
        this.hunger = 75;
        this.asleep = false;
        this.fFood = _fFood;
        this.sounds = new String[_sounds.length];
        for(int i = 0; i < sounds.length-1; i++){
            sounds[i] = _sounds[i];
        }
        this.alive = true;
        this.interactions = new ArrayList<>(List.of("play", "sleep", "feed"));
        this.allPets = allPets;
    }

    protected void die(String cause){
        if(getAlive()){
            nullattribs();
            setAlive(false);
            setCauseOfDeath(cause);
        }
    }

    public int[] timeChange(int time){
        time = time/10;
        if(isAsleep()){
            changeJoy(time * getjDepRate() / 3);
            changeHunger(time * gethRate());
            changeEnergy(time * getRestRate());
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
        if(getEnergy() <= 0){
            sleep();
        }
        jChanged = false;
        hChanged = false;
        eChanged = false;
        return getStats();
    }

    public String feed(String food){
        String mess = getName() + ": " + getSound();
        if(isAsleep()){
            mess = "You woke " + getName() + " up!\n" + mess;
            wakeUp();
        }
        changeHunger(50);
        if(food.equals(fFood)){
            changeJoy(40);
        }
        changeEnergy(10);
        return mess;
    }

    public String play(){
        String mess = getName() + ": " + getSound();
        if(isAsleep()){
            mess = "You woke " + getName() + " up!\n" + mess;
            wakeUp();
        }
        changeHunger(gethRate());
        changeEnergy(geteDepRate());
        changeJoy(30);
        return mess;
    }

    public String sleep(){
        String message = getName() + ": zzz";
        asleep = true;
        return message;
    }

    public String wakeUp(){
        String message = getName() + ": " + getSound();
        message = getName() + " has woken up!\n" + message;
        asleep = false;
        return message;
    }

    public int[] getStats(){
        return new int[]{getJoy()/10, getHunger()/10, getEnergy()/10};
    }

    // Getters and Setters

    public String getName(){
        return Name;
    }

    public int getEnergy(){
        return energy;
    }

    public int getHunger() {
        return hunger;
    }

    public int getJoy() {
        return joy;
    }

    public int geteDepRate() {
        return eDepRate;
    }

    public int gethRate() {
        return hRate;
    }

    public Boolean getAlive() {
        return alive;
    }

    public int getjDepRate() {
        return jDepRate;
    }

    public String getCauseOfDeath() {
        return causeOfDeath;
    }

    public int getRestRate(){
        return restRate;
    }

    public String getfFood() {
        return fFood;
    }

    public Boolean isAsleep(){
        return asleep;
    }

    public String getSound() {
        return sounds[new Random().nextInt(sounds.length-1)];
    }

    public List<String> getInteractions(){
        return interactions;
    }

    public void changeEnergy(int change) {
        this.energy += change;
        if(this.energy > 100){
            this.energy = 100;
        }
    }

    public void changeJoy(int change) {
        this.joy += change;
        if(this.joy > 100){
            this.joy = 100;
        }
        else if(this.joy < 0){
            die("sadness");
        }
    }

    public void changeHunger(int change) {
        this.hunger += change;
        if(this.hunger > 100){
            die("overfeeding");
        }
        else if(this.hunger < 0){
            die("hunger");
        }
    }

    public void nullattribs(){
        this.energy = 0;
        this.joy = 0;
        this.hunger = 0;
    }

    public void setAlive(Boolean _alive){
        this.alive = _alive;
    }

    public void sethRate(int hR){
        this.hRate = hR;
    }

    public void seteDepRate(int eDepRate) {
        this.eDepRate = eDepRate;
    }

    public void setjDepRate(int jDep){
        this.jDepRate = jDep;
    }

    public void setRestRate(int rR){
        this.restRate = rR;
    }

    public void setCauseOfDeath(String causeOfDeath) {
        this.causeOfDeath = causeOfDeath;
    }

    public void setInteractions(List<String> _inter){
        this.interactions = List.copyOf(_inter);
    }

    @Override
    public String toString(){
        return getName();
    }
}
