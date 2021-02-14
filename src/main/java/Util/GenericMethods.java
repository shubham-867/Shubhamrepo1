package Util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class GenericMethods {
    public Properties envReader() {
        FileReader reader = null;   // same functions as fileinputstream but diff is...
        // former is to read by chracter and latter readers byte
        try {
            reader = new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\ENV.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Properties env = new Properties();
        try {
            env.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return env;   //align code crtl+alt+shift+L

    }

    public Properties envReaderSQLQuery(){
        FileReader sqlreader = null;
        try {
            sqlreader = new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\sqlQuery.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Properties env = new Properties();
        try {
            env.load(sqlreader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return env;

    }

}
