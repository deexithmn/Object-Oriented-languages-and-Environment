package CodeGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PostIncrementDecrement extends Base {
    String type= "int";
    int expressionLayer = 0;
    List<Base> Attributes = new ArrayList<Base>();

    public PostIncrementDecrement(int Layer, String type) throws IOException {
        expressionLayer = Layer + 1;
        GrammarParser.sequenceGenerator(this, "PostIncrementDecrement");
    }

    public PostIncrementDecrement() throws IOException {
        GrammarParser.sequenceGenerator(this, "PostIncrementDecrement");
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