package org.opensourcedemo.core;

import org.openqa.selenium.WebDriver;

public class DriverFactory {


    public BaseDriver getDriverFactory(Driver paramDriver){
        switch (paramDriver){
            case Driver.CHROME -> {
                return new BaseDriverChrome();
            }
            case Driver.FIREFOX -> {
                return new BaseDriverFirefox();
            }

        }
        return null;
    }

}
