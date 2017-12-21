package CodeGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MethodDeclaration extends Base{
    String type="null";
    List<Base> Attributes = new ArrayList<Base>();
    List<localVariableDeclaration> Fields = new ArrayList<>();

    public MethodDeclaration() throws IOException {
        symbolTable.addscopeBlocks(this);
        GrammarParser.sequenceGenerator(this, "MethodDeclaration");
        symbolTable.deletescopeBlocks();
    }
    public void addAttributes(Base attribute) {
        if(attribute instanceof Parameter) {
            for(Base Attribute:((Parameter) attribute).Attributes) {
                if (Attribute instanceof localVariableDeclaration) {
                    this.Fields.add((localVariableDeclaration) Attribute);
                }
            }
        }
        this.Attributes.add(attribute);
    }

    public List<localVariableDeclaration> getFields() {
        return this.Fields;
    }

    public void ValuePrint() {
        for(Base Attribute: Attributes) {
            Attribute.ValuePrint();
        }
    }
}