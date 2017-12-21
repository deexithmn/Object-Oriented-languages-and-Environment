package CodeGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LiteralExpression extends Base {
    String type="null";
    int expressionLayer = 0;

    public LiteralExpression(int Layer, String type) throws IOException {
        this.type = type;
        expressionLayer = Layer + 1;
        GrammarParser.sequenceGenerator(this, "LiteralExpression");
    }

    List<Base> Attributes = new ArrayList<Base>();

    public LiteralExpression() throws IOException {
        GrammarParser.sequenceGenerator(this, "LiteralExpression");
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