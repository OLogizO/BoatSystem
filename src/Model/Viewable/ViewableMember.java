package Model.Viewable;

/**
 * The interface Viewable member is used to limit which functions should be accessible, its primary target is the view package.
 */
public interface ViewableMember {
    String getName();
    String getPersonalNumber();
    int getId();
    int getNumberOfBoats();
    ViewableBoat[] getViewableBoats();
    void accept(ViewableMemberVisitor visitor);
}
