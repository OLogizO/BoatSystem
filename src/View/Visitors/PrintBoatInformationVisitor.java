package View.Visitors;

import Model.Viewable.ViewableBoat;
import Model.Viewable.ViewableBoatVisitor;

/**
 * Print boat information visitor is used to print the boat information in a easy to read way.
 */
public class PrintBoatInformationVisitor implements ViewableBoatVisitor {
    public void visitBoat(ViewableBoat boat) {
        System.out.println("Type: " + getStringForType(boat.getType()) + " with length of " + boat.getLength() + "m.");
    }

    private String getStringForType(Model.Boat.Type type) {
        switch (type) {
            case SAILBOAT: return "Sailboat";
            case MOTROSAILER: return "Motorsailer";
            case KAYAK_CANOE: return "kayak/canoe";
            case OTHER: return "Other";
            default: return "this type of boat is not yet implemented";
        }
    }
}
