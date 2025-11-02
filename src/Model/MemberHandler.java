package Model;

import Model.PersistentStorage.PersistentStorage;
import Model.Viewable.ViewableBoat;
import Model.Viewable.ViewableMember;
import Model.Viewable.ViewableMemberVisitor;

import java.util.List;

/**
 * Is a class used to handle the members data and any manipulation of them.
 */
public class MemberHandler {
    private final PersistentStorage persistentStorage;
    private final List<Member> membersList;
    private int memberCount;

    /**
     * Instantiates a new Member handler.
     *
     * @param persistentStorage the persistent storage that is used to save files localy to the computer
     */
    public MemberHandler(PersistentStorage persistentStorage) {
        this.persistentStorage = persistentStorage;
        membersList = persistentStorage.getMembers();
        memberCount = persistentStorage.getMemberCount();
    }

    private Member getMember (int id) {
       for (Member member : membersList) {
            if (member.getId() == id) {
                return member;
            }
       }
       return null;
    }

    public ViewableMember getViewableMember(int id) {
        for (Member member : membersList) {
            if (member.getId() == id) {
                return member;
            }
        }
        return null;
    }

    public void addMember(Member member) {
        member.setMemberId(memberCount++);
        membersList.add(member);
    }

    public void removeMember(ViewableMember member) {
        membersList.remove(member);
    }

    /**
     * Visits all members.
     *
     * @param visitor the visitor. (Visitor pattern)
     */
    public void visitAllMembers(ViewableMemberVisitor visitor) {
        for (Member member : membersList) {
            member.accept(visitor);
        }
    }

    public void changeMember(ViewableMember vMember, Member member) {
        getMember(vMember.getId()).changeMemberTo(member);
    }

    public void addBoatToMember(ViewableMember vMember, Boat boat) {
        getMember(vMember.getId()).addBoat(boat);
    }

    public void changeBoatForMember(ViewableMember vMember, ViewableBoat oldVBoat, Boat newBoat) {
       getMember(vMember.getId()). getBoat(oldVBoat). changeBoatTo(newBoat);
    }

    public void deleteBoatForMember(ViewableMember vMember, ViewableBoat vBoat) {
        getMember(vMember.getId()).deleteBoat(vBoat);
    }

    public void saveDataLocally () {
        persistentStorage.updateMemberCount(Integer.toString(memberCount));
        persistentStorage.saveMembers(membersList);
    }
}
