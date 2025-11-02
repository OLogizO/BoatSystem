package View.Menu;

import Model.Boat;
import Model.Member;
import Model.Viewable.ViewableBoat;
import Model.Viewable.ViewableMember;
import Model.Viewable.ViewableMemberVisitor;
import View.Visitors.PrintMembersCompactVisitor;
import View.Visitors.PrintMembersVerboseVisitor;

/**
 * The menu handler keeps tracks of all the printout strings and is essentially a Languages class.
 * It does however handle the Menu class so i found the name "MenuHandler" to be better.
 */
public class MenuHandler { // TODO: try to remove the duplication since each menu follow the same structure
    private final Menu menu;

    /**
     * Instantiates a new Menu handler.
     *
     * @param menu A help class that the menu uses to get inputs from the user.
     */
    public MenuHandler(Menu menu) {
        this.menu = menu;
    }
    // TODO: should try to figure out how to move these to the an language interface
    public enum StartMenuChoice implements MenuChoices { // TODO: <---- each type of menu will need a enum associated with it and i cant seem to find a way to remove the function duplication in them.
        ADD(1, "Add member"),
        CHOOSE_MEMBER(2, "Choose member"),
        PRINT_MEMBERS(3, "Print members"),
        EXIT(4, "Exit");

        private final int value;
        private final String stringShownToUser;

        StartMenuChoice(int value, String stringShownToUser) {
            this.value = value;
            this.stringShownToUser = stringShownToUser;
        }

        public int getValue() {
            return value;
        }

        public String getMenuString() {
            return stringShownToUser;
        }
    }

    public enum MemberMenuChoice implements MenuChoices { // TODO: <---- each type of menu will need a enum associated with it and i cant seem to find a way to remove the function duplication in them.
        DELETE(1, "Delete member"),
        CHANGE(2, "Change member"),
        PRINT_MEMBER(3, "Print member"),
        ADD_BOAT(4, "Add boat"),
        CHOOSE_BOAT(5, "Choose boat "),
        EXIT(6, "Go back to main menu ");


        private final int value;
        private final String stringShownToUser;

        MemberMenuChoice(int value, String stringShownToUser) {
            this.value = value;
            this.stringShownToUser = stringShownToUser;
        }

        public int getValue() {
            return value;
        }

        public String getMenuString() {
            return stringShownToUser;
        }
    }
    
    public enum PrintMembersChoice implements MenuChoices {
        VERBOSE(1, "Verbose printout: name, personal number, member id and boats with boat information."),
        COMPACT(2, "Compact printout: name, member id and number of boats.");



        private final int value;
        private final String stringShownToUser;

        PrintMembersChoice(int value, String stringShownToUser) {
            this.value = value;
            this.stringShownToUser = stringShownToUser;
        }

        public int getValue() {
            return value;
        }

        public String getMenuString() {
            return stringShownToUser;
        }
    }

    public enum BoatTypeMenuChoice implements MenuChoices { // TODO: <---- each type of menu will need a enum associated with it and i cant seem to find a way to remove the function duplication in them.
        SAILBOAT(1, "Sailboat."),
        MOTORSAILER(2, "Motorsailer."),
        KAYAK_CANOE(3, "Kayak or canoe."),
        OTHER(4, "Other.");

        private final int value;
        private final String stringShownToUser;

        BoatTypeMenuChoice(int value, String stringShownToUser) {
            this.value = value;
            this.stringShownToUser = stringShownToUser;
        }

        public int getValue() {
            return value;
        }

        public String getMenuString() {
            return stringShownToUser;
        }
    }

    public enum BoatMenuChoice implements  MenuChoices {
        DELETE(1, "Delete."),
        CHANGE(2, "Change.");

        private final int value;
        private final String stringShownToUser;

        BoatMenuChoice(int value, String stringShownToUser) {
            this.value = value;
            this.stringShownToUser = stringShownToUser;
        }

        public int getValue() {
            return value;
        }

        public String getMenuString() {
            return stringShownToUser;
        }
    }


    /**
     * Uses the StartMenuChoice enum and the menu to get the users choice.
     *
     * @return the start menu choice
     */
    public StartMenuChoice startMenu() {
        int choice = menu.menu(StartMenuChoice.class, StartMenuChoice.values());
        return getValueOf(StartMenuChoice.class, choice);
    }

    /**
     * Uses the MemberMenuChoice enum and the menu to get the users choice.
     *
     * @return the member menu choice
     */
    public MemberMenuChoice memberMenu() {
        int choice = menu.menu(MemberMenuChoice.class, MemberMenuChoice.values());
        return getValueOf(MemberMenuChoice.class, choice);
    }

    /**
     * Uses the PrintMembersChoice enum and the menu to get the users choice.
     *
     * @return the print members choice
     */
    public PrintMembersChoice printMembersMenu() {
        int choice = menu.menu(PrintMembersChoice.class, PrintMembersChoice.values());
        return getValueOf(PrintMembersChoice.class, choice);
    }

    /**
     * Uses the BoatChoiceMenu enum and the menu to get the users choice.
     *
     * @return the boat choice menu
     */
    public BoatMenuChoice boatMenu() {
        int choice = menu.menu(BoatMenuChoice.class, BoatMenuChoice.values());
        return getValueOf(BoatMenuChoice.class, choice);
    }

    public Boat getBoatInformationFromUser() {
        int length = getBoatLengthFromUser();
        Boat.Type type = getBoatTypeFromUser();
        return new Boat(type, length);
    }

    public Member getMemberInformationFromUser() {
        String name = getNameFromUser();
        String personalNumber = getPersonalNumberFromUser();
        return new Member(name, personalNumber);
    }

    private String getNameFromUser() {
        String instructions = "Please enter the name of the Member: ";
        return menu.getStringInput(instructions);
    }

    private String getPersonalNumberFromUser() {
        String instructions = "Please enter the personal number of the Member (in the format yymmddxxxx): ";
        return menu.getStringInput(instructions);
    }

    public int getMemberIdFromUser() {
        String instructions = "Please enter the members id: ";
        return menu.getIntInput(instructions);
    }

    private int getBoatLengthFromUser() {
        String instructions = "Please enter the boats length in meters: ";
        return menu.getIntInput(instructions);
    }

    private Boat.Type getBoatTypeFromUser() {
        Boat.Type[] boatTypes = Boat.Type.values();
        BoatTypeMenuChoice[] boatTypesMenuOptions = BoatTypeMenuChoice.values();
        // hidden dependency which i don't know how to remove.
        assert boatTypes.length == boatTypesMenuOptions.length : "ERROR: The number of boat types in the boat class (Boat.Type) dose not match the number of options in the menu (MenuHandler.BoatTypeMenuChoice)";

        int choice = menu.menu(BoatTypeMenuChoice.class, BoatTypeMenuChoice.values());
        return boatTypes[choice - 1];
    }

    public ViewableBoat getBoatFromUser(ViewableMember member) {
        ViewableBoat[] boats = member.getViewableBoats();
        int choice = menu.menu(boats);
        return boats[choice - 1];
    }

    public void noSuchMemberWarning(int memberId) {
        System.out.println("There is no member with id: " + memberId);
    }

    public ViewableMemberVisitor getPrintMembersCompactVisitor() { return new PrintMembersCompactVisitor(); }

    public ViewableMemberVisitor getPrintMembersVerboseVisitor() { return new PrintMembersVerboseVisitor(); } //TODO: make an facade for the view to the member handler that lets the view get acses to the accept functions

    /**
     * This function is used to get the matching enum value for the input choice that the user provided.
     * example: class = StartMenuChoice, code = 1. This function will then return ADD.
     *
     * @param <T>       An enum class which implements the MenuChoices interface
     * @param enumClass the enum class
     * @param code      the code
     * @return the enum value for the code for the class T.
     */
    private <T extends Enum<T> & MenuChoices> T getValueOf(Class<T> enumClass, Integer code) {
        for (T e : enumClass.getEnumConstants()) {
            if (e.getValue() ==  code) {
                return e;
            }
        }
        throw new IllegalArgumentException("No enum const " + enumClass.getName() + " with value \'" + code
                + "\'");
    }
}
