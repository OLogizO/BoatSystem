package View.Visitors;

import Model.Viewable.ViewableBoat;
import Model.Viewable.ViewableMember;
import Model.Viewable.ViewableMemberVisitor;

/**
 * Print members verbose visitor is used to print the member with boats information in a easy to read way.
 */
public class PrintMembersVerboseVisitor implements ViewableMemberVisitor {

    public void visitViewableMember(ViewableMember viewableMember) {
        ViewableBoat[] boats = viewableMember.getViewableBoats();
        System.out.println("_______________________________________________");
        System.out.println("Name: " + viewableMember.getName() + ".");
        System.out.println("Personal number: " + viewableMember.getPersonalNumber() + ".");
        System.out.println("Member id: " + viewableMember.getId() + ".");
        System.out.println("//////////////////// Boats //////////////////// ");
        for (ViewableBoat boat : boats) {
            boat.accept(new PrintBoatInformationVisitor());
        }
        System.out.println("_______________________________________________");
    }
}
