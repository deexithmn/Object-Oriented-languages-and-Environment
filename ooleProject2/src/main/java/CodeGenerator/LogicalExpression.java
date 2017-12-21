package CodeGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogicalExpression extends Base {
    int expressionLayer = 0;
    String type="null";
    public LogicalExpression(int Layer) throws IOException {
        expressionLayer = Layer + 1;
        GrammarParser.sequenceGenerator(this, "Expression");
    }

    List<Base> Attributes = new ArrayList<Base>();

    public LogicalExpression() throws IOException {
        GrammarParser.sequenceGenerator(this, "LogicalExpression");
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