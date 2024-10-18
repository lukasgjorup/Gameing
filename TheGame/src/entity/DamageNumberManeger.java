package entity;

import java.awt.*;
import java.io.IOException;

public class DamageNumberManeger {

    public static DamageNumbers[] damageNumbersArray = new DamageNumbers[10];
    public static int damageNumbersIndex = 0;

    public DamageNumberManeger() {
    }

    public static void newDamageNumber(int x, int y, int damage) throws IOException {
        if (damageNumbersIndex < damageNumbersArray.length) {  // Use array length
            damageNumbersArray[damageNumbersIndex] = new DamageNumbers(x, y, damage);
            damageNumbersIndex++;
        } else {
            System.out.println("Damage number is full");
        }
    }

    public void update() {
        if (damageNumbersIndex > 0 && damageNumbersArray[damageNumbersIndex - 1] != null) {  // Index bound check
            boolean temp = false;
            int tempIndex = damageNumbersIndex - 1;  // Start at damageNumbersIndex - 1
            for (int i = tempIndex; i >= 0; i--) {
                temp = damageNumbersArray[i].update();
                if (temp) {
                    damageNumbersArray[i] = null;  // Remove the damage number
                    // Shift elements to the left
                    for (int j = i; j < damageNumbersIndex - 1; j++) {  // Correct shifting loop
                        damageNumbersArray[j] = damageNumbersArray[j + 1];
                    }
                    damageNumbersArray[damageNumbersIndex - 1] = null;  // Nullify last element
                    damageNumbersIndex--;  // Update index
                }
            }
        }
    }

    public static void draw(Graphics2D g2d) {
        for (DamageNumbers damageNumbers : damageNumbersArray) {
            if (damageNumbers != null) {  // Check for null before calling draw
                damageNumbers.draw(g2d);
            }
        }
    }







}
