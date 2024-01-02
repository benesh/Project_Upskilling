package org.opensourcedemo.core.properties_manager;

import org.opensourcedemo.core.webdriver_manager.Env;
import org.opensourcedemo.core.webdriver_manager.Headless;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GlobalConfig {

    public static String DEFAULT_BROWSER;
    public static int EXPLICITWAIT;
    public static int IMPLICITWAIT;
    public static Env ENV;
    public static Headless HEADLESS;
    public static String USER;
    public static String HOSTNAME;
    public static String SOFTWARETEST;
    public static String DOCUMENTTITLE;
    public static String REPORTNAME;
    public static String PATHREPORT;
    public static String PATHSCREENCHSOT;
    public static Boolean REPORTTEST=false;

    public static String HUB_URL;
    public static String getDateForName(){
        return new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
    }

}
