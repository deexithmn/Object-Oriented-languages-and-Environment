package CodeGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Block extends Base {
    String type="null";
    String returnType = "void";
    List<Base> Attributes = new ArrayList<Base>();
    List<localVariableDeclaration> Fields = new ArrayList<>();

    public Block() throws IOException {
        symbolTable.addscopeBlocks(this);
        GrammarParser.sequenceGenerator(this, "Block");
        symbolTable.deletescopeBlocks();
        }
    public Block(String returnType) throws IOException {
        this.returnType = returnType;
        symbolTable.addscopeBlocks(this);
        GrammarParser.sequenceGenerator(this, "Block");
        symbolTable.deletescopeBlocks();
    }

    public void addAttributes(Base attribute) {
        if(attribute instanceof BlockStatement) {
            for(Base Attribute:((BlockStatement) attribute).Attributes) {
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
