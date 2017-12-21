package CodeGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArithmeticExpression extends Base {

    int expressionLayer = 0;
    String type="null";

    public ArithmeticExpression(int Layer, String type) throws IOException {
        this.expressionLayer = Layer + 1;
        this.type = type;
        GrammarParser.sequenceGenerator(this, "ArithmeticExpression");
    }

    List<Base> Attributes = new ArrayList<Base>();
    public ArithmeticExpression() throws IOException {
        GrammarParser.sequenceGenerator(this, "ArithmeticExpression");
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