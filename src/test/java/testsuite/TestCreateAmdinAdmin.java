package testsuite;

import lombok.extern.log4j.Log4j2;
import org.opensourcedemo.BaseTest.BaseTest;
import org.testng.annotations.Test;

@Log4j2
public class TestCreateAmdinAdmin extends BaseTest {

    public TestCreateAmdinAdmin(){
        super("src/main/resources/entry_data/scenario1/config.properties");
    }

    @Test
    public void createUserAdmin(){

    }

    @Test
    public void connectToUserAmdin(){

    }


}
