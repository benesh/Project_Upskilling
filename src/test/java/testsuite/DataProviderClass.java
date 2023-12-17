package testsuite;

import org.testng.annotations.DataProvider;
        import org.testng.annotations.Test;

public class DataProviderClass {

    @DataProvider(name = "myDataProvider")
    public Object[][] dataProviderMethod() {
        return new Object[][]{
                {"path/to/dataSet1.json"},
                {"path/to/dataSet2.json"},
                // Add more data sets as needed
        };
    }
}
