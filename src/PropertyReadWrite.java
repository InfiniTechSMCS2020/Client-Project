import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
public class PropertyReadWrite {

        public static String readPassword(){
            Properties prop = new Properties();
            InputStream input = null;
            try {

                input = new FileInputStream("config.properties");

                // load a properties file
                prop.load(input);

                // get the property value and print it out
                System.out.println("Password: " + prop.getProperty("admin_password"));

                String value = prop.getProperty("admin_password");

                return value;

            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;

        }

        public static void writePassword(String password){
            Properties prop = new Properties();
            OutputStream output = null;

            try {

                output = new FileOutputStream("config.properties");

                // set the properties value
                prop.setProperty("admin_password", password);

                // save properties to project root folder
                prop.store(output, null);

            } catch (IOException io) {
                io.printStackTrace();
            } finally {
                if (output != null) {
                    try {
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }




