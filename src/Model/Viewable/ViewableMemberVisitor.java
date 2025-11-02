package Model.Viewable;

/**
 * The interface Viewable member visitor is used to make functions that wants to use boats information. (Visitor pattern)
 */
public interface ViewableMemberVisitor {
    void visitViewableMember(ViewableMember viewableMember);
}
