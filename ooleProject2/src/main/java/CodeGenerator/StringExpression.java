package CodeGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StringExpression extends Base {
    String type="null";
    int expressionLayer = 0;
    public StringExpression(int Layer, String type) throws IOException {
        expressionLayer = Layer + 1;
        this.type = type;
        GrammarParser.sequenceGenerator(this, "StringExpression");
    }

    List<Base> Attributes = new ArrayList<Base>();
    public StringExpression() throws IOException {
        GrammarParser.sequenceGenerator(this, "StringExpression");
    }

    public void addAttributes(Base attribute) {
        this.Attributes.add(attribute);
    }

    public void ValuePrint() {
        for(Base Attribute: Attributes) {
            Attribute.ValuePrint();
        }
    }
}