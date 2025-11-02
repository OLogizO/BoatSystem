package Model.PersistentStorage;

import Model.Member;

import java.util.List;

/**
 * The interface Persistent storage is used as an abstraction so other persistent storages can be used in the future.
 */
public interface PersistentStorage {
    void saveMembers(List<Member> members);
    List<Member> getMembers();
    void updateMemberCount (String text);
    int getMemberCount();
}
