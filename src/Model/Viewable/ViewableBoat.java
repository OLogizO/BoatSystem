package Model.Viewable;

import Model.Boat;
import Model.Viewable.ViewableBoatVisitor;

/**
 * The interface Viewable boat is used to limit which functions should be accessible, its primary target is the view package.
 */
public interface ViewableBoat {
    Boat.Type getType ();
    int getLength();
    void accept(ViewableBoatVisitor visitor);
}
