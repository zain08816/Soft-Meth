import java.security.PublicKey;

/**
 * @author Clarissa Hwang, Zain Ali
 */
public class Profile {
    private String fname;
    private String lname;

    /**
     * Constructor for Profile
     * @param fname first name of user
     * @param lname last name of user
     */
    public Profile(String fname, String lname) {
        this.fname = fname;
        this.lname = lname;
    }

    /**
     * Returns the first name of profile
     * @return Returns the first name of profile
     */
    public String getFname() {
        return fname;
    }

    /**
     * Returns the last name of profile
     * @return Returns the last name of profile
     */
    public String getLname() {
        return lname;
    }
}
