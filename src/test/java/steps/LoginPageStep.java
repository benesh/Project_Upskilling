package steps;

import BaseTest.BaseStep;
import io.cucumber.java.en.And;
import org.openqa.selenium.support.PageFactory;
import org.opensourcedemo.pagesobjects.LoginPage;

public class LoginPageStep {
    LoginPage loginpage;
    public LoginPageStep(BaseStep baseStep){
        loginpage = PageFactory.initElements(baseStep.basePage.getDriver(),LoginPage.class);
    }
    @And("j_insere un username {string}")
    public void j_entre_username(String username){
        loginpage.inputUserName(username);
    }
    @And("j_insere un password {string}")
    public void j_entre_psws(String pswd){
        loginpage.inputPwd(pswd);
    }
    @And("je clique sur le bouton connexion")
    public void jeClikLogin(){
        loginpage.clickButtonLogin();
    }
}
