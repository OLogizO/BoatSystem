package Controller;

import Model.*;
import Model.Viewable.ViewableBoat;
import Model.Viewable.ViewableMember;
import View.Menu.MenuHandler;

/**
 * The controller class acts as an event handler for the application
 */
public class Controller { // TODO: might wanna divide each menu into its own controller, this file is getting cluttered.
    private final MenuHandler menuHandler;
    private final MemberHandler memberHandler;

    /**
     * Instantiates a new Controller.
     *
     * @param menuHandler   the menu handler
     * @param memberHandler the member handler
     */
    public Controller(MenuHandler menuHandler, MemberHandler memberHandler) {
        this.memberHandler = memberHandler;
        this.menuHandler = menuHandler;
    }

    /**
     * Start application.
     */
    public void startApplication () {
        boolean userWantsToExit = false;
        while (!userWantsToExit) {
            switch (menuHandler.startMenu()) {
                case ADD -> handleAddMember();
                case CHOOSE_MEMBER -> handleChooseMember();
                case PRINT_MEMBERS -> handlePrintMembers();
                case EXIT -> {
                    memberHandler.saveDataLocally();
                    userWantsToExit = true;
                }
                default -> throwStandardMenuError();
            }
        }
    }

    /**
     * Is used to throw an exception when the controller gets an enum it doesn't have an switch case for.
     * @throws Exception
     */
    private void throwStandardMenuError() {
        try {
            throw new Exception("The option you choose is not implemented.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void handleAddMember() {

        memberHandler.addMember(menuHandler.getMemberInformationFromUser());
        
    }

    private void handleChooseMember() {
        ViewableMember member;
        int memberId = menuHandler.getMemberIdFromUser();
        member = memberHandler.getViewableMember(memberId);
        if (member != null) {
            handleMemberMenuInput(member);
        } else {
            menuHandler.noSuchMemberWarning(memberId);
        }
    }

    private void handlePrintMembers() { // TODO: unsure if i should make a print members function in menuHandler then make a ViewFacade to MemberHandler instead
        switch (menuHandler.printMembersMenu()) {
            case COMPACT -> memberHandler.visitAllMembers(menuHandler.getPrintMembersCompactVisitor());
            case VERBOSE -> memberHandler.visitAllMembers(menuHandler.getPrintMembersVerboseVisitor());
            default -> throwStandardMenuError();
        }
    }

    private void handleMemberMenuInput(ViewableMember vMember) {
        var userWantsToExit = false;
        while(!userWantsToExit) {
            switch (menuHandler.memberMenu()) {
                case DELETE: handleDeleteMember(vMember);
                userWantsToExit = true;
                    break;
                case CHANGE: handleChangeMember(vMember);
                    break;
                case PRINT_MEMBER: handlePrintMember(vMember);
                    break;
                case ADD_BOAT: handleAddBoat(vMember);
                    break;
                case CHOOSE_BOAT: handleChooseBoat(vMember);
                    break;
                case EXIT: userWantsToExit = true;
                    break;
                default: throwStandardMenuError();
                    break;
            }
        }
    }

    private void handleDeleteMember(ViewableMember vMember) {
        memberHandler.removeMember(vMember);
    }

    private void handleChangeMember(ViewableMember vMember) {
        memberHandler.changeMember(vMember, menuHandler.getMemberInformationFromUser());
    }

    private void handleAddBoat(ViewableMember vMember) {

        memberHandler.addBoatToMember(vMember, menuHandler.getBoatInformationFromUser());

    }

    private void handleChooseBoat(ViewableMember vMember) {
        if (vMember.getNumberOfBoats() != 0) {
            ViewableBoat boat = menuHandler.getBoatFromUser(vMember);
            handleBoatMenu(vMember, boat);
        }
    }

    private void handleBoatMenu(ViewableMember vMember, ViewableBoat vBoat) {
        switch (menuHandler.boatMenu()) {
            case CHANGE -> handleChangeBoat(vMember, vBoat);
            case DELETE -> memberHandler.deleteBoatForMember(vMember, vBoat);
        }
    }

    private void handleChangeBoat(ViewableMember vMember, ViewableBoat vBoat) {
        memberHandler.changeBoatForMember(vMember, vBoat, menuHandler.getBoatInformationFromUser());
    }

    private void handlePrintMember(ViewableMember vMember) { // TODO: unsure if i should make a print member function in menu handler then make a interface to MemberHandler instead
        vMember.accept(menuHandler.getPrintMembersVerboseVisitor());
    }

}


