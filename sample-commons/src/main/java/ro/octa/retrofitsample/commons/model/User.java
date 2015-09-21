package ro.octa.retrofitsample.commons.model;

/**
 * @author Octa on 9/21/2015.
 */
public class User implements HasId<Long> {

    private long id;
    private String email;
    private String password;
    private String userHash;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getUserHash() {
        return userHash;
    }
}
