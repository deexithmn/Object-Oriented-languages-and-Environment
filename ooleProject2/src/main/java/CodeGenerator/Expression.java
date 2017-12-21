package CodeGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Expression extends Base {
    String type="null";
    int expressionLayer = 0;

    public Expression(int Layer, String type) throws IOException {
        this.type = type;
        GrammarParser.sequenceGenerator(this, "Expression");
        expressionLayer = Layer + 1;
    }

    List<Base> Attributes = new ArrayList<Base>();
    public Expression() throws IOException {
        GrammarParser.sequenceGenerator(this, "Expression");
    }
    public void addAttributes(Base attribute) {
        this.Attributes.add(attribute);
    }

    public void ValuePrint() {
        //System.out.println("\nExpression");
        for(Base Attribute: Attributes) {
            Attribute.ValuePrint();
        }
        //System.out.println("\nEnd of Expression");
    }
}
