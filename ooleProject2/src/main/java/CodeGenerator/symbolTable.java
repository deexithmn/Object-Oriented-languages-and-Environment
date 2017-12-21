package CodeGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class symbolTable {
    String type="null";
    static String currentClassName;
    static List<ClassDeclaration> listOfClasses = new ArrayList<ClassDeclaration>();
    static List<InterfaceDeclaration> listOfInterfaces = new ArrayList<InterfaceDeclaration>();
    static List<localVariableDeclaration> listOfFields = new ArrayList<localVariableDeclaration>();
    static Stack<Base> scopeBlocks = new Stack<>();
    static void addClasses(ClassDeclaration newClass) {
        listOfClasses.add(newClass);
    }
    static void addscopeBlocks(Base newscopeBlock) {
        scopeBlocks.push(newscopeBlock);
    }
    static void deletescopeBlocks() {
        scopeBlocks.pop();
    }
    static List<localVariableDeclaration> identifierChooser() {
        listOfFields.clear();
        for (Base block : scopeBlocks) {
            if( block instanceof ClassBody) {
                listOfFields.addAll(((ClassBody) block).getFields());
            } else if( block instanceof MethodDeclaration) {
                listOfFields.addAll(((MethodDeclaration) block).getFields());
            } else if( block instanceof Block) {
                listOfFields.addAll(((Block) block).getFields());
            }
        }
        return listOfFields;
    }
}
