package steps;

import io.cucumber.java.en.And;
import org.openqa.selenium.support.PageFactory;
import org.opensourcedemo.pagesobjects.pimpages.PimPage;

public class PimPageSteps{
    PimPage pimpage;
    public PimPageSteps(BaseStep baseStep){
        pimpage = PageFactory.initElements(baseStep.basePage.getDriver(), PimPage.class);
    }
    @And("je clique sur +Add de PIM")
    public void clickSurAddButton() {
        pimpage.clickAddButton();
    }
}
