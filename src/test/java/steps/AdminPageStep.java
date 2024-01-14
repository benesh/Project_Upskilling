package steps;

import io.cucumber.java.en.And;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.support.PageFactory;
import org.opensourcedemo.pagesobjects.adminpages.AdminPage;

@Log4j2
public class AdminPageStep {
    AdminPage adminPage;
    public AdminPageStep(BaseStep baseStep){
        log.info("initiate Step in AdminPage step");
        adminPage = PageFactory.initElements(baseStep.basePage.getDriver(),AdminPage.class);
    }
    @And("je clique sur +Add de Admin")
    public void je_clique_sur_Add(){
        log.info("step click add");
        adminPage.clickAddAmdin();
    }
    @And("je me deconnecte")
    public void je_me_deconnecte(){
        adminPage.clickToProfil()
                .logOutbutton();
    }
}
