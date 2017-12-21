package CodeGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Identifier extends Base{
    String type="null";
    String identifier;
    static final String identifierValues = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static final String identifierStart = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static Random rnd = new Random();

    public String getIdentifier() {
        return identifier;
    }

    public Identifier() {
        int len = rnd.nextInt(10)+4;
        StringBuilder sb = new StringBuilder( len );
        sb.append(identifierStart.charAt( rnd.nextInt(identifierStart.length())));
        for( int i = 1; i < len; i++ )
            sb.append( identifierValues.charAt( rnd.nextInt(identifierValues.length()) ) );
        this.identifier = sb.toString();
    }

    public Identifier(String value) {
        this.identifier = value;
    }

    public Identifier(Base baseValue) {
        List<localVariableDeclaration> variableList = symbolTable.identifierChooser();
        List<localVariableDeclaration> filteredVariables=new ArrayList<>();
        String type1 = "null";
        if(baseValue instanceof TestingExpression) {
            type1 = ((TestingExpression) baseValue).type;
        }
        else if(baseValue instanceof Assignment) {
            type1 = ((Assignment) baseValue).type;
        }
        else if(baseValue instanceof PostIncrementDecrement) {
            type1 = ((PostIncrementDecrement) baseValue).type;
        }
        else if(baseValue instanceof LoopAssignment) {
            type1 = ((LoopAssignment) baseValue).type;
        } else if(baseValue instanceof Expression) {
            type1 = ((Expression) baseValue).type;
        }
        if (type1.equals("null")) {
            localVariableDeclaration selectedVariable = variableList.get(rnd.nextInt(variableList.size()));
            for (Base attribute : selectedVariable.Attributes) {
                if (attribute instanceof Identifier) {
                    this.identifier = ((Identifier) attribute).identifier;
                }
                if (attribute instanceof Types) {
                    this.type = ((Types) attribute).type;
                }
            }
            baseValue.type = this.type;
            baseValue.setType(this.type);
        } else {
            for (localVariableDeclaration variable : variableList) {
                for (Base attributes : variable.Attributes) {
                    if (attributes instanceof Types) {
                        if (((Types) attributes).type.equals(type1)) {
                            filteredVariables.add(variable);
                        }
                    }
                }
            }
            localVariableDeclaration selectedVariable = filteredVariables.get(rnd.nextInt(filteredVariables.size()));
            for (Base attribute : selectedVariable.Attributes) {
                if (attribute instanceof Identifier) {
                    this.identifier = ((Identifier) attribute).identifier;
                }
                if (attribute instanceof Types) {
                    type = ((Types) attribute).type;
                }
            }
        }
    }

    public void ValuePrint() {
        code=code+" "+identifier;
    }

}
