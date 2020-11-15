package Model;

/**
 * Customizable interface
 * @author Clarissa Hwang, Zain Ali
 */
public interface Customizable {
    /**
     * Add to list
     * @param obj item to add
     * @return true if added, false if not
     */
    boolean add(Object obj);

    /**
     * Remove from list
     * @param obj item to remove
     * @return true if removed, false if not
     */
    boolean remove(Object obj);
}
