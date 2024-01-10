package steps;

import BaseTest.BaseStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.PageFactory;
import org.opensourcedemo.pagesobjects.pimpages.AddEmployeePage;

public class AddEmployeePageSteps {
    AddEmployeePage addEmployeePage;

    public AddEmployeePageSteps(BaseStep baseStep){
        addEmployeePage = PageFactory.initElements(baseStep.basePage.getDriver(),AddEmployeePage.class);

    }
    @And("j_insere un firstname {string} de ajouter Employe")
    public void j_insere_firstname(String firstname){
        addEmployeePage.typeFirstName(firstname);
    }
    @And("j_insere un middlename {string} de ajouter Employe")
    public void j_insereMiddlename(String middlename){
        addEmployeePage.typeMiddletName(middlename);
    }
    @And("j_insere un lastname {string} de ajouter Employe")
    public void j_insereLastname(String lastname){
        addEmployeePage.typeLastName(lastname);
    }
    @When("je click sur le bouton save")
    public void clickSaveButton(){
        addEmployeePage.clickSaveButton();
    }
    @Then("je constate l_enregistrement avec succes")
    public void je_voisapparaitreIconSucce(){
        addEmployeePage.handlerSuccessAlert();
    }
}
