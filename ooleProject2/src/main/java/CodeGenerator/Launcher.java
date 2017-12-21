package CodeGenerator;

import org.apache.commons.io.FileUtils;

import java.io.*;


public class Launcher {
    public static void main(String[] args) throws IOException {
        Base basestart = new Base();
        Configuration.configurationReader();
        FileUtils.cleanDirectory(new File("./src/main/java/generatedFiles/"));
        for (int i = 0; i < Configuration.MinNoOfClasses; i++) {
            ClassDeclaration newClass = new ClassDeclaration();
            newClass.ValuePrint();
            System.out.println(Base.code);
            String fileWriter = "package generatedFiles;\n";
            fileWriter += Base.code;
            FileUtils.writeStringToFile(new File("./src/main/java/generatedFiles/" + symbolTable.currentClassName + ".java"), fileWriter);
            Base.code = "";
        }
    }
}
