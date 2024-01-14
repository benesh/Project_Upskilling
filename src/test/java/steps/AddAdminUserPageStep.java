package steps;

import io.cucumber.java.en.And;
import org.openqa.selenium.support.PageFactory;
import org.opensourcedemo.pagesobjects.adminpages.AddAdminUserPage;

public class AddAdminUserPageStep {
    AddAdminUserPage addAdminUserPage;
    public AddAdminUserPageStep(BaseStep baseSte){
        addAdminUserPage = PageFactory.initElements(baseSte.basePage.getDriver(),AddAdminUserPage.class);
    }
    @And("j_insere un role Admin au user")
    public void j_insere_le_role_admin(){
        addAdminUserPage.setUserToAdminRole();
    }

    @And("j_insere le status enable au user")
    public void j_insere_statu_enable(){
        addAdminUserPage.setSatusUserAccountToEnable();
    }
    @And("je recherche et selection le nom complet du user {string} {string} {string}")
    public void j_insere_nom_compte(String firstname,String middlename,String lastname){
        addAdminUserPage.typeEmployeeName(firstname+" "+middlename+" "+lastname);
    }
    @And("j_insere le username {string} du admin")
    public void j_insere_username_pour_admin(String username){
        addAdminUserPage.inputUserAdminName(username);
    }
    @And("j_insere un password {string} du admin")
    public void j_insere_password_pour_admin(String password){
        addAdminUserPage.inputPassword(password);
    }
    @And("j_insere un mode de passe de confirmation {string}")
    public void j_insere_password_confirmation_pour_admin(String password){
        addAdminUserPage.inputPassworConfirmation(password);
    }
    @And("je clique sur le bouton Save de Add Admin Page")
    public void j_click_sur_bouton_Save_de_Admin(){
        addAdminUserPage.buttonSaveAdmin();
    }
    @And("je vois l'enregistrement s'est effectué avec succes")
    public void j_vois_enregistrement_avec_succes(){
        addAdminUserPage.handlerSuccessAlert();
    }

}
