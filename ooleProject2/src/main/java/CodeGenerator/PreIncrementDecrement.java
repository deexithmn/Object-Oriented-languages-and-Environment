package CodeGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PreIncrementDecrement extends Base {
    String type="null";
    int expressionLayer = 0;
    List<Base> Attributes = new ArrayList<Base>();

    public PreIncrementDecrement(int Layer, String type) throws IOException {
        expressionLayer = Layer + 1;
        this.type = type;
        GrammarParser.sequenceGenerator(this, "PreIncrementDecrement");
    }

    public PreIncrementDecrement() throws IOException {
        GrammarParser.sequenceGenerator(this, "PreIncrementDecrement");
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