package View.Menu;

import Model.Viewable.ViewableBoat;

/**
 * A Interface used for getting inputs from the user.
 */
public interface Menu {
    /**
     * Presents the menu for the user based on the menu choices
     *
     * @param <T>         the type parameter
     * @param enumClass   the enum class
     * @param menuChoices the menu choices
     * @return the int
     */
    <T extends Enum<T> & MenuChoices> int menu(Class<T> enumClass, MenuChoices[] menuChoices );

    /**
     * Let the member choice between boats
     *
     * @param boats the boats
     * @return the int
     */
    int menu (ViewableBoat[] boats);

    /**
     * Let the user input an integer
     *
     * @param instructions the instructions
     * @return the int input
     */
    int getIntInput(String instructions);

    /**
     * Let the user input a string
     *
     * @param instructions the instructions
     * @return the string input
     */
    String getStringInput(String instructions);
}
