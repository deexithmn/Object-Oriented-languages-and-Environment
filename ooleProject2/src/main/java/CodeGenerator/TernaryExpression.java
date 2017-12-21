package CodeGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TernaryExpression extends Base {
    String type="null";
    int expressionLayer = 0;

    public TernaryExpression(int Layer, String type) throws IOException {
        expressionLayer = Layer + 1;
        this.type = type;
        GrammarParser.sequenceGenerator(this, "TernaryExpression");
    }

    List<Base> Attributes = new ArrayList<Base>();

    public TernaryExpression() throws IOException {
        GrammarParser.sequenceGenerator(this, "TernaryExpression");
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