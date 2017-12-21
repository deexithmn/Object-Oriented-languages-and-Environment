package CodeGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MethodBody extends Base  {
    String type="null";
    String returnType = "void";
    List<Base> Attributes = new ArrayList<Base>();

    public MethodBody(String returnType) throws IOException {
        this.returnType = returnType;
        GrammarParser.sequenceGenerator(this, "MethodBody");
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