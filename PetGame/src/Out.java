import java.util.List;

interface Out {
    int baseHour = 9;
    void outputState(Pet _p);
    void outputAllPets(List<Pet> pets);
    void outputMessage(String message);
    void outputPetStore(String[] types, Boolean firstTime);
    void outputOptions(List<String> options);
    void outputTime(int timeInMins);
    void outputPastPets(List<Pet> pastPets);
    String input();
}
