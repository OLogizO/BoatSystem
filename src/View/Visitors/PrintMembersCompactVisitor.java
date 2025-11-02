package View.Visitors;

import Model.Viewable.ViewableMember;
import Model.Viewable.ViewableMemberVisitor;

/**
 * Print members compact visitor is used to print the member information in a easy to read way.
 */
public class PrintMembersCompactVisitor implements ViewableMemberVisitor {

    public void visitViewableMember(ViewableMember viewableMember) {
        System.out.println("_______________________________________________");
        System.out.println("Name: " + viewableMember.getName() + ".");
        System.out.println("Member id: " + viewableMember.getId() + ".");
        System.out.println("Number of boats: " + viewableMember.getNumberOfBoats() + ".");
        System.out.println("_______________________________________________");

    }
}
