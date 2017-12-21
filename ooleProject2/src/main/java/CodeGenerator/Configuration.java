package CodeGenerator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Integer;

public class Configuration {
    static int MinNoOfClasses = 0;
    static int MinNoOfMethodsDeclared = 0;
    static int MinNoOfClassFields = 0;
    static int MinNoOfConstructors = 0;
    static int MinNoOfParametersMethod = 0;
    static int MinNoOfStatementsMethod = 0;
    static int MinNoOfArrayDeclarations = 0;
    static BufferedReader br;

    // Reading the configuration file to get parameters
    public static void configurationReader() throws IOException {
        br = new BufferedReader(new FileReader("./Configuration.txt"));
        String line;
        while ((line = br.readLine()) != null) {
            String [] seperateValues=line.split(" ");
            if(seperateValues[0].equals("MinNoOfClasses:"))
                MinNoOfClasses = Integer.parseInt(seperateValues[1]);
            else if (seperateValues[0].equals("MinNoOfMethodsDeclared:"))
                MinNoOfMethodsDeclared = Integer.parseInt(seperateValues[1]);
            else if (seperateValues[0].equals("MinNoOfClassFields:"))
                MinNoOfClassFields = Integer.parseInt(seperateValues[1]);
            else if (seperateValues[0].equals("MinNoOfConstructors:"))
                MinNoOfConstructors = Integer.parseInt(seperateValues[1]);
            else if (seperateValues[0].equals("MinNoOfParametersMethod:"))
                MinNoOfParametersMethod = Integer.parseInt(seperateValues[1]);
            else if (seperateValues[0].equals("MinNoOfStatementsMethod:"))
                MinNoOfStatementsMethod = Integer.parseInt(seperateValues[1]);
            else if (seperateValues[0].equals("MinNoOfArrayDeclarations:"))
                MinNoOfArrayDeclarations = Integer.parseInt(seperateValues[1]);
        }
    }
}
