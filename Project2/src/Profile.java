import java.security.PublicKey;
import java.util.Objects;

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
     * Checking if two profile objets are equal
     * @param obj profile object that you want to compare
     * @return return true if same, false if not same
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Profile profile = (Profile) obj;
        return Objects.equals(fname, profile.fname) &&
                Objects.equals(lname, profile.lname);
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
