import java.util.List;

public class TextUI implements Out{

    static TextUI instance = null;

    private TextUI(){}

    public static TextUI getInstance(){
        if(instance == null){
            instance = new TextUI();
        }
        return instance;
    }

    @Override
    public void outputAllPets(List<Pet> pets) {
        int i = 0;
        for (Pet p : pets) {
            outputMessage(i++ + ") " + p.getName() + ", a " + p.getClass().getSimpleName().toLowerCase());
        }
        outputMessage(i++ + ") Buy a new pet");
        outputMessage(i + ") View past pets");
        outputMessage("What would you like to do?");
    }

    @Override
    public void outputMessage(String message){
        System.out.println(message);
    }

    @Override
    public void outputPetStore(String[] types, Boolean firstTime) {
        String mess = "What pet would you like?\n";
        for (int i = 0; i < types.length; i++){
            mess += i + ") " + types[i] + "\n";
        }
        if(!firstTime){
            mess += types.length + ") Back\n";
        }
        mess = mess.substring(0, mess.length()-1);
        outputMessage(mess);
    }

    @Override
    public void outputOptions(List<String> options) {
        String mess = "";
        for(int i = 0; i < options.size(); i++){
            mess += i + ") " + options.get(i).substring(0,1).toUpperCase() + options.get(i).substring(1).toLowerCase() + "\n";
        }
        outputMessage(mess);
    }

    @Override
    public void outputTime(int timeInMins) {
        String hour = Integer.toString((baseHour + timeInMins/60)%24);
        if((baseHour + timeInMins/60)%24 < 10){
            hour = "0" + hour;
        }
        String minute = Integer.toString(timeInMins % 60);
        if(timeInMins%60 < 10){
            minute = "0" + minute;
        }
        outputMessage("\n\nIt is " + hour + ":" + minute);
    }

    @Override
    public void outputPastPets(List<Pet> pastPets) {
        outputMessage("Name - Type - Fate");
        for(Pet p : pastPets){
            outputMessage(p.getName() + " - " + p.getClass().getSimpleName() + " - " + p.getCauseOfDeath());
        }
    }

    @Override
    public void outputState(Pet _p) {
        int[] stats = _p.getStats();
        String out = "\n" + _p.getName() + " the " + _p.getClass().getSimpleName().toLowerCase() + ":\nHappiness: ";
        for(int i = 0; i < stats[0]; i++){
            out += "0";
        }
        out += "\nFullness: ";
        for(int i = 0; i < stats[1]; i++){
            out += "0";
        }
        out += "\nEnergy: ";
        for(int i = 0; i < stats[2]; i++){
            out += "0";
        }
        if(_p.isAsleep()){
            out += "\n" + _p.getName() + " is asleep.";
        }
        outputMessage(out);
    }

    @Override
    public String input() {
        return System.console().readLine();
    }
}
