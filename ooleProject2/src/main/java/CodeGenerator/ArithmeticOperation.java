package CodeGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArithmeticOperation extends Base {

    String type="null";
    int expressionLayer = 0;
    List<Base> Attributes = new ArrayList<Base>();

    public ArithmeticOperation() throws IOException {
        GrammarParser.sequenceGenerator(this, "ArithmeticOperation");
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