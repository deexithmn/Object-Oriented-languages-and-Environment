package CodeGenerator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ReturnStatement extends Base {
    String type="null";
    List<Base> Attributes = new ArrayList<Base>();

    public ReturnStatement(String type) throws IOException {
        this.type = type;
        GrammarParser.sequenceGenerator(this, "ReturnStatement");
    }

    public ReturnStatement() throws IOException {
        GrammarParser.sequenceGenerator(this, "ReturnStatement");
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