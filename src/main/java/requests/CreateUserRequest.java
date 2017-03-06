package requests;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Created by RENT on 2017-03-06.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class CreateUserRequest {

    private String login;
    private String name;
    private String mail;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
