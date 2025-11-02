package View.Menu;

import Model.Viewable.ViewableBoat;
import View.Visitors.PrintBoatInformationVisitor;

import java.util.Scanner;

/**
 * A class used for getting inputs from the user.
 */
public class ConsoleMenu implements Menu {

    /**
     * This function prints the menu using the MenuChoices enums and ask the user to choice one their and returns
     *
     * @param <T>         the type parameter
     * @param enumClass   the enum class
     * @param menuChoices the menu choices
     * @return The corresponding value of the menu choice that the user selected.
     */
    public <T extends Enum<T> & MenuChoices> int menu(Class<T> enumClass, MenuChoices[] menuChoices ) {
        for (MenuChoices values : menuChoices) {
            System.out.println(values.getValue() + ". " + values.getMenuString());
        }
        return getMenuInput(menuChoices);
    }

    /**
     * This function prints each boats information and ask the user to choose one.
     *
     * @param boats the boats to be selected from
     * @return the number the user inputted which corresponds to a boat.
     */
    public int menu (ViewableBoat[] boats) {
        String instructions = "Enter the number shown to the left of the option: ";
        boolean correctInput = false;
        int value = -1;
        for (int i = 0; i < boats.length; i++) {
            System.out.print( (i + 1) + ". ");
            boats[i].accept(new PrintBoatInformationVisitor());
        }
        while (!correctInput) {
            value = getIntInput(instructions);
            int length = boats.length;
            if (value <= length && value > 0) {
                correctInput = true;
            }
        }
        return value;
    }

    /**
     * Forces the user to input a number which corresponds to a menu choice. TODO: i could send in a string to this function to make my MenuHandler class function as a "Language" class
     * @param menuChoices
     * @return
     */
    private int getMenuInput (MenuChoices[] menuChoices) {
        Scanner input = new Scanner(System.in);
        boolean correctInput = false;
        int value = -1;
        String instructions = "Enter the number shown to the left of the option: ";
        while (!correctInput) {
            value = getIntInput(instructions);
            int length = menuChoices.length;
            for (int i =  0; i < length; i++) {
                if (value == menuChoices[i].getValue()) {
                    correctInput = true;
                    i = length;
                }
            }
        }
        return value;
    }


    /**
     * Forces the user to input an integer
     *
     * @param instructions the instructions for the user
     * @return the int input
     */
    public int getIntInput(String instructions) {
        Scanner input = new Scanner(System.in);
        System.out.print(instructions);
        while (!input.hasNextInt()) {
            System.out.print(instructions);
            input.next();
        }
        return input.nextInt();
    }

    /**
     * Forces the user to input a string
     *
     * @param instructions the instructions for the user
     * @return the string input
     */
    public String getStringInput(String instructions) {
        Scanner input = new Scanner(System.in);
        System.out.print(instructions);
        while (!input.hasNext()) {
            System.out.print(instructions);
            input.next();
        }
        return input.next();
    }
}
