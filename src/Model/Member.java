package Model;

import Model.Viewable.ViewableBoat;
import Model.Viewable.ViewableMember;
import Model.Viewable.ViewableMemberVisitor;

import java.util.ArrayList;
import java.util.List;

public class Member implements ViewableMember {
    private String name;
    private String personalNumber;
    private int memberId;
    private List<Boat> boats = new ArrayList<Boat>();

    /**
     * Instantiates a new Member.
     *
     * @param name           the name
     * @param personalNumber the personal number
     * @param memberId       the member id
     */
    public Member(String name, String personalNumber, int memberId) {
        this.name = name;
        this.personalNumber = personalNumber;
        this.memberId = memberId;
    }

    public Member(String name, String personalNumber) {
        this.name = name;
        this.personalNumber = personalNumber;
    }
    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }
    public void changeMemberTo(Member member) {
        setName(member.getName());
        setPersonalNumber(member.getPersonalNumber());
    }

    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    /**
     * Sets personal number.
     *
     * @param personalNumber the personal number
     */
    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public int getId() {
        return memberId;
    }

    public int getNumberOfBoats() {
        return boats.size();
    }

    public ViewableBoat[] getViewableBoats() {
        Boat[] boatsArray = new Boat[boats.size()];
        return boats.toArray(boatsArray);
    }

    /**
     * Add boat.
     *
     * @param boat the boat
     */
    public void addBoat(Boat boat) {
        boats.add(boat);
    }


    /**
     * Gets boat.
     *
     * @param vBoat the v boat
     * @return the boat
     */
    public Boat getBoat(ViewableBoat vBoat) {
        int index = boats.indexOf(vBoat);
        return boats.get(index);
    }

    /**
     * Delete boat.
     *
     * @param boat the boat
     */
    public void deleteBoat(ViewableBoat boat) {
        boats.remove(boat);
    }

    /** visitor pattern for member
     *
     * @param visitor the visitor
     */
    public void accept(ViewableMemberVisitor visitor) { visitor.visitViewableMember(this);
    }
}
