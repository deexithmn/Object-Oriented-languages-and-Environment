package CodeGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NumericExpression extends Base {
    String type="null";
    int expressionLayer = 0;

    public NumericExpression(int Layer, String type) throws IOException {
        expressionLayer = Layer + 1;
        this.type = type;
        GrammarParser.sequenceGenerator(this, "NumericExpression");
    }

    List<Base> Attributes = new ArrayList<Base>();

    public NumericExpression() throws IOException {
        GrammarParser.sequenceGenerator(this, "NumericExpression");
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