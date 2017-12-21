package CodeGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestingExpression extends Base {
    String type="null";
    int expressionLayer = 0;

    public TestingExpression(int Layer, String type) throws IOException {
        expressionLayer = Layer + 1;
        this.type = type;
        GrammarParser.sequenceGenerator(this, "TestingExpression");
    }

    public void setType(String type) {
        this.type = type;
    }

    List<Base> Attributes = new ArrayList<Base>();

    public TestingExpression() throws IOException {
        GrammarParser.sequenceGenerator(this, "TestingExpression");
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