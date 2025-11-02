package Model.Viewable;

/**
 * The interface Viewable boat visitor is used to make functions that wants to use boats information. (Visitor pattern)
 */
public interface ViewableBoatVisitor {
    void visitBoat(ViewableBoat boat);
}
