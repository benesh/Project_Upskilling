package steps;

import io.cucumber.java.en.And;
import org.openqa.selenium.support.PageFactory;
import org.opensourcedemo.pagesobjects.DashbordPage;
import org.testng.Assert;

public class DashbordPageSteps {
    DashbordPage dashbord;
    public DashbordPageSteps(BaseStep baseStep){
        dashbord = PageFactory.initElements(baseStep.basePage.getDriver(),DashbordPage.class);
    }
    @And("je clique sur pim")
    public void clickPimPage(){
        dashbord.clickPimPage();
    }
    @And("je clique sur la Admin")
    public void je_clique_sur_AdminPage(){
        dashbord.clickAdminPage();
    }
    @And("je vois que le nom sur le profil correspond a celui cree {string} {string}")
    public void je_verifie_le_nom_correspond(String firstname,String lastname){
        Assert.assertEquals(dashbord.getNameProfil(),firstname+" "+lastname);
    }
}
