package steps;

import io.cucumber.java.en.And;
import org.openqa.selenium.support.PageFactory;
import org.opensourcedemo.pagesobjects.pimpages.EmployeeDetailsPage;

public class EmployeeDetailsPageSteps {
    EmployeeDetailsPage employeeDetailsPage;
    public EmployeeDetailsPageSteps(BaseStep baseStep){
        employeeDetailsPage = PageFactory.initElements(baseStep.basePage.getDriver(),EmployeeDetailsPage.class);
    }
    @And("je click sur le bouton save de la page employe info")
    public void je_voisapparaitreIconSucce(){
        employeeDetailsPage.clickSaveButton();
    }

}
