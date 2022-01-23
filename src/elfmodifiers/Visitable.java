package elfmodifiers;

public interface Visitable {
    /**
     * Method that starts the visit
     * @param v visitor that makes the visit
     */
    void accept(Visitor v);
}
