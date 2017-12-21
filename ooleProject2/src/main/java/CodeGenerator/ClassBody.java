package CodeGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClassBody extends Base {
    String type="null";
    List<Base> Attributes = new ArrayList<Base>();
    List<localVariableDeclaration> Fields = new ArrayList<>();
    public ClassBody() throws IOException {
        symbolTable.addscopeBlocks(this);
        GrammarParser.sequenceGenerator(this, "ClassBody");
        symbolTable.deletescopeBlocks();
    }
    public void addAttributes(Base attribute) {
        if(attribute instanceof FieldDeclaration) {
            for(Base Attribute:((FieldDeclaration) attribute).Attributes) {
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
        code=code+"\n"+"{";
        for(Base Attribute: Attributes) {
            Attribute.ValuePrint();
        }
        code=code+"\n"+"}";
    }
}
