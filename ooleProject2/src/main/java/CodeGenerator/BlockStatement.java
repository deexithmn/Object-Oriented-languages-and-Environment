package CodeGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BlockStatement extends Base {
    String type="null";
    String returnType = "void";
    List<Base> Attributes = new ArrayList<Base>();
    public BlockStatement(String returnType) throws IOException {
        this.returnType = returnType;
        GrammarParser.sequenceGenerator(this, "BlockStatement");
    }
    public BlockStatement() throws IOException {
        GrammarParser.sequenceGenerator(this, "BlockStatement");
    }
    public void addAttributes(Base attribute) {
        this.Attributes.add(attribute);
    }

    public void ValuePrint() {
        //System.out.println("\nBlock Statement");
        for(Base Attribute: Attributes) {
            Attribute.ValuePrint();
        }
    }
}