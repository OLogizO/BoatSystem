package Model;

import Model.Viewable.ViewableBoat;
import Model.Viewable.ViewableBoatVisitor;

public class Boat implements ViewableBoat {
    public enum Type {

        SAILBOAT,
        MOTROSAILER,
        KAYAK_CANOE,
        OTHER;

    }

    private Type type;
    private int length;

    public Boat (Type type, int length) {
        this.type = type;
        this.length = length;
    }

    public void changeBoatTo(Boat boat) {
        setType(boat.getType());
        setLength(boat.getLength());
    }

    public Type getType () {
        return  type;
    }

    public void setType(Type type) {
        this.type =  type;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    /**
     * This function is used to accept visitors. (Visitor pattern)
     * @param visitor
     */
    public void accept(ViewableBoatVisitor visitor) {
        visitor.visitBoat(this);
    }
}
