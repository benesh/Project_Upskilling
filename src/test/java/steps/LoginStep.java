package steps;

import io.cucumber.java.en.And;
import org.opensourcedemo.pagesobjects.LoginPage;

public class LoginStep extends BaseStep {
    LoginPage loginpage= new LoginPage();
    public LoginStep(){}

    @And("j_insere un username {string}")
    public void j_entre_username(String username){
        loginpage.inputUserName(username);
    }
    @And("j_insere un password {string}")
    public void j_entre_psws(String pswd){
        loginpage.inputPwd(pswd);
    }


}
