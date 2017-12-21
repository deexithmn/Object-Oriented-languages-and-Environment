package CodeGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NotExpression extends Base {
    String type="null";
    int expressionLayer = 0;

    public NotExpression(int Layer, String type) throws IOException {
        expressionLayer = Layer + 1;
        this.type = type;
        GrammarParser.sequenceGenerator(this, "NotExpression");
    }

    List<Base> Attributes = new ArrayList<Base>();
    public NotExpression() throws IOException {
        GrammarParser.sequenceGenerator(this, "NotExpression");
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