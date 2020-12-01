import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class projectTest {

    @Test
    public void petStats_decrease_OverTime(){
        // Arrange
        Pet pet = new Cat("Tuna", new ArrayList<>());
        int[] startStats = new int[] {pet.getJoy(), pet.getHunger(), pet.getEnergy()};

        // Act
        pet.timeChange(10);
        startStats[0] = (startStats[0] + 1 * pet.getjDepRate())/10;
        startStats[1] = (startStats[1] + 1 * pet.gethRate())/10;
        startStats[2] = (startStats[2] + 1 * pet.geteDepRate())/10;

        // Assert

        assertEquals(pet.getStats()[0], startStats[0]);
        assertEquals(pet.getStats()[1], startStats[1]);
        assertEquals(pet.getStats()[2], startStats[2]);
    }

    @Test
    public void petStats_petDies_ifUnfed(){
        // Arrange
        Pet pet = new Cat("Felix", new ArrayList<>());

        // Act
        pet.timeChange(100);

        // Assert
        assertFalse(pet.getAlive());
    }


}
