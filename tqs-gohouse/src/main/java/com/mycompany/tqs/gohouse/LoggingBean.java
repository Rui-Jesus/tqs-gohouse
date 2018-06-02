
package com.mycompany.tqs.gohouse;

import dbclasses.PlatformUser;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;

import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import other.CurrentUser;

/**
 *
 * @author joaos
 */
@ManagedBean(name = "loggingBean", eager = true)
@SessionScoped
public class LoggingBean implements Serializable {
    
    //Database handler
    private final DBHandler dbHandler;
    private String userName;
    private String userMail;
    
    //Constructor
    public LoggingBean() {
        dbHandler = new DBHandler();
    }

    //userName getter
    public String getUserName() {
        return userName;
    }

    //userName setter
    public void setUserName(String userName) {
        this.userName = userName;
    }

    //userMail getter
    public String getUserMail() {
        return userMail;
    }

    //userMail setter
    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    
    /**
     * Aquires user's name and email when user finishes signing in via Google SignIn
     */
    public void userSignIn(){
        //System.out.println("Getting Login Values. (NOTE: Google will remember if you logged in a previous time!)");
        
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        userName  = params.get("name");
        userMail  = params.get("email");
        

        System.out.println("User detected! -> " + userName +"\t"+ userMail);
        dbHandler.registerUser(userMail, userName, LocalDate.MIN, false);

    }
}



