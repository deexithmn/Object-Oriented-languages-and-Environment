package CodeGenerator;

public class InheritedClass extends Base {
    ClassDeclaration parentClass;
    String type="null";

    public InheritedClass(String className) {
        int classverifier=0;
        for(ClassDeclaration eachClass:symbolTable.listOfClasses) {
            for(Base eachAttribute:eachClass.Attributes) {
                if(eachAttribute instanceof Identifier) {
                    if(((Identifier) eachAttribute).identifier.equals(className)) {
                        classverifier = 1;
                        break;
                    }
                    break;
                }
            }
            if(classverifier == 1) {
                parentClass = eachClass;
                break;
            }
        }
    }

}