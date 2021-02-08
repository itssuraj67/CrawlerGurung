package org.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public  class CommonUtils {
    public static String getProperty(String key){
        Properties prop=new Properties();;
//        read from application.properties
        try (InputStream input = new FileInputStream("src/main/java/org/example/application.properties")) {
            prop.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop.getProperty(key);
    }

}
