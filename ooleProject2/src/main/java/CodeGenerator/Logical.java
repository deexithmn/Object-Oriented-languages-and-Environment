package CodeGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Logical extends Base {
    String type="null";
    int expressionLayer = 0;

    public Logical(int Layer, String type) throws IOException {
        expressionLayer = Layer + 1;
        this.type = type;
        GrammarParser.sequenceGenerator(this, "Logical");
    }

    public void setType(String type) {
        this.type = type;
    }

    List<Base> Attributes = new ArrayList<Base>();

    public Logical() throws IOException {
        GrammarParser.sequenceGenerator(this, "Logical");
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