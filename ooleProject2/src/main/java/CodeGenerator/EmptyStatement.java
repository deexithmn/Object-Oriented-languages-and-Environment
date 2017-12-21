package CodeGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmptyStatement extends Base{
    String type="null";

    public EmptyStatement() throws IOException {
        GrammarParser.sequenceGenerator(this, "EmptyStatement");
    }

    public void ValuePrint() {
        code=code+" "+";";
    }
}
