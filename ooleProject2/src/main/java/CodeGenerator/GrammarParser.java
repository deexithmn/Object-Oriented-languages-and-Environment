package CodeGenerator;

import CodeGenerator.Integer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import static CodeGenerator.Identifier.rnd;

public class GrammarParser {
    String type=null;
    static BufferedReader br;
    static int statici = 0;
    static List<String> GrammarString = new ArrayList<String>();

    // Read the grammar file and store each line as a List of Strings
    public static void bufferedReaderInit() throws IOException {
        if (statici == 0) {
            br = new BufferedReader(new FileReader("./Grammar.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                GrammarString.add(line);
            }
            statici += 1;
        }
    }

    // Core function which handles the parser and creation of objects of each grammar type
    public static void sequenceGenerator(Base baseValue, String typeValue) throws IOException {
        bufferedReaderInit();
        // Reading the grammar to return entities that will be contained in a given entity
        // For example a call to this function with typeValue = ClassDeclaration will return ...
        // ClassDeclaration: Modifier class Identifier [ extends TypeList ] [ implements TypeList ] ClassBody
        // each of them will be stored as separate strings in string array Stringvalues
        String[] Stringvalues = readGrammar(typeValue);

        // Initializing variables
        int zeroOrNoChecker = 0;
        int zeroOrMoreChecker = 0;
        int extendsverifier = 0;
        int anyOneChecker = 0;
        Random rnd = new Random();
        Stack deciderStack = new Stack();
        deciderStack.push(1);
        int noOfIterationDecider = 1;

        // Iterating through each value returned by the grammar
        for(int i=1;i<Stringvalues.length;i++) {

            String value = Stringvalues[i];
            String subString[];

            // IF [ ] is found in the grammar, entity within the brackets may or may not be added
            if(value.equals("[")) {
                if(!deciderStack.empty())
                    if(deciderStack.peek().equals(0)) {
                        deciderStack.push(0);
                    }
                zeroOrNoChecker+=1;
                continue;
            } else if(value.equals("]")) {
                deciderStack.pop();
                extendsverifier = 0;
                continue;
            }

            // IF % is found in the grammar, only exactly entity within % % may be added
            if(value.equals("%")) {
                anyOneChecker+=1;
                continue;
            } else if(value.equals("%")) {
                anyOneChecker-=1;
                continue;
            }

            // if { } is found, statements inside brackets will be repeated 0 or more
            if(value.equals("{")) {
                if(deciderStack.peek().equals(0)) {
                    deciderStack.push(0);
                    continue;
                }
                zeroOrMoreChecker+=1;
                continue;
            } else if(value.equals("}")) {
                deciderStack.pop();
                noOfIterationDecider = 1;
                extendsverifier = 0;
                continue;
            }

            // Choosing one value from statements inside % % - anyOneChecker() function
            if(anyOneChecker == 1) {
                String[] listOfValues = value.split(",");
                value = anyOneChecker(baseValue, listOfValues);
            }

            // Deciding if statements inside [ ] in grammar will be executed - zeroOrNoChecker() function
            if(zeroOrNoChecker == 1) {
                if(zeroOrNoChecker(value, baseValue) == 0){
                    zeroOrNoChecker-=1;
                    deciderStack.push(0);
                } else {
                    zeroOrNoChecker-=1;
                    deciderStack.push(1);
                }
            }

            // Deciding no of times statements inside {} in grammar will be executed - zeroOrMoreChecker function
            if(zeroOrMoreChecker > 0 ) {
                noOfIterationDecider = zeroOrMoreChecker(value);
                if (noOfIterationDecider == 0) {
                    zeroOrMoreChecker -= 1;
                    deciderStack.push(0);
                } else {
                    zeroOrMoreChecker -= 1;
                    deciderStack.push(1);
                }
            }

            // Checking if the entity in grammar will be added
            if ((int)deciderStack.peek() == 1) {

                // Iterating for number of times the entity has to be added
                for(int j = 0; j < noOfIterationDecider; j++) {
                    // Creating objects for each of the entities in the grammar to be added to the base entity
                    switch (value) {

                        case "ClassDeclaration":
                            ClassDeclaration newClass = new ClassDeclaration();
                            baseValue.addAttributes(newClass);
                            break;

                        case "Modifier":
                            Modifier newModifier = new Modifier();
                            baseValue.addAttributes(newModifier);
                            break;

                        case "Identifier":
                            Identifier newIdentifier;
                            // If base class found is constructor - identifier should be same as the class identifier
                            if (baseValue instanceof ConstructorDeclaration || baseValue instanceof DefaultConstructor) {
                                newIdentifier = new Identifier(symbolTable.currentClassName);
                            }
                            // If base class found is declaration - any random identifier name can be assigned
                            else if (baseValue instanceof ClassDeclaration || baseValue instanceof MethodDeclaration || baseValue instanceof ParameterVariable || baseValue instanceof localVariableDeclaration || baseValue instanceof ArrayDeclaration)  {
                                newIdentifier = new Identifier();
                                if (baseValue instanceof ClassDeclaration) {
                                    symbolTable.currentClassName = newIdentifier.identifier;
                                }
                            }
                            // If identifier created is used in expression or any other statement
                            // --- Only identifiers which are declared in the scope can be used
                            // --- Also identifier corresponding to the required type has to be used
                            else {
                                newIdentifier = new Identifier(baseValue);
                            }
                            baseValue.addAttributes(newIdentifier);
                            break;

                        case "ClassBody":
                            ClassBody newClassBody = new ClassBody();
                            baseValue.addAttributes(newClassBody);
                            break;

                        case "Types":
                            Types newType = new Types();
                            baseValue.addAttributes(newType);
                            if( baseValue instanceof localVariableDeclaration) {
                                ((localVariableDeclaration) baseValue).type = newType.type;
                            }
                            if(baseValue instanceof MethodDeclaration) {
                                ((MethodDeclaration) baseValue).type = newType.type;
                            }
                            break;

                        case "TypeList":
                            TypeList newTypeList = new TypeList();
                            baseValue.addAttributes(newTypeList);
                            break;

                        case "FieldDeclaration":
                            FieldDeclaration newField = new FieldDeclaration();
                            baseValue.addAttributes(newField);
                            break;

                        case "MethodDeclaration":
                            MethodDeclaration newMethod = new MethodDeclaration();
                            baseValue.addAttributes(newMethod);
                            break;

                        case "DefaultConstructor":
                            DefaultConstructor newDefaultConstructor = new DefaultConstructor();
                            baseValue.addAttributes(newDefaultConstructor);
                            break;

                        case "ConstructorDeclaration":
                            ConstructorDeclaration newConstructor = new ConstructorDeclaration();
                            baseValue.addAttributes(newConstructor);
                            break;

                        case "MethodBody":
                            MethodBody newMethodBody = new MethodBody(((MethodDeclaration) baseValue).type);
                            newMethodBody.returnType = ((MethodDeclaration) baseValue).type;
                            baseValue.addAttributes(newMethodBody);
                            break;

                        case "Block":
                            Block newBlock;
                            if(baseValue instanceof MethodBody) {
                                newBlock = new Block(((MethodBody) baseValue).returnType);
                            }
                            else {
                                newBlock = new Block();
                            }
                            baseValue.addAttributes(newBlock);
                            if(baseValue instanceof MethodBody) {
                                newBlock.returnType = ((MethodBody) baseValue).returnType;
                            }
                            break;

                        case "BlockStatement":
                            BlockStatement newBlockStatement = new BlockStatement(((Block) baseValue).returnType);
                            baseValue.addAttributes(newBlockStatement);
                            if(baseValue instanceof Block) {
                                newBlockStatement.returnType = ((Block) baseValue).returnType;
                            }
                            break;

                        case "localVariableDeclaration":
                            localVariableDeclaration newlocalVariable = new localVariableDeclaration();
                            baseValue.addAttributes(newlocalVariable);
                            break;

                        case "Statement":
                            Statement newStatement = new Statement();
                            baseValue.addAttributes(newStatement);
                            break;

                        case "IfThenStatement":
                            IfThenStatement newIfStatement = new IfThenStatement();
                            baseValue.addAttributes(newIfStatement);
                            break;

                        case "ForStatement":
                            ForStatement newForStatement = new ForStatement();
                            baseValue.addAttributes(newForStatement);
                            break;

                        case "LabeledStatement":
                            LabeledStatement newLabeledStatement = new LabeledStatement();
                            baseValue.addAttributes(newLabeledStatement);
                            break;

                        case "EmptyStatement":
                            EmptyStatement newEmptyStatement = new EmptyStatement();
                            baseValue.addAttributes(newEmptyStatement);
                            break;

                        case "ConstructorBody":
                            ConstructorBody newConstructorBody = new ConstructorBody();
                            baseValue.addAttributes(newConstructorBody);
                            break;

                        case "Parameter":
                            Parameter newParameter = new Parameter();
                            baseValue.addAttributes(newParameter);
                            break;

                        case "extends":
                            extendsverifier = 1;
                            deciderStack.push(1);
                            Keyword newextendsKeyword = new Keyword(value,baseValue);
                            baseValue.addAttributes(newextendsKeyword);
                            break;

                        case "implements":
                            deciderStack.push(1);
                            Keyword newimplementKeyword = new Keyword(value,baseValue);
                            baseValue.addAttributes(newimplementKeyword);
                            break;

                        case "ReturnStatement":
                            ReturnStatement returnStatement=new ReturnStatement(((BlockStatement) baseValue).returnType);
                            returnStatement.type = ((BlockStatement) baseValue).type;
                            baseValue.addAttributes(returnStatement);
                            break;

                        case "ContinueStatement":
                            ContinueStatement continueStatement=new ContinueStatement();
                            baseValue.addAttributes(continueStatement);
                            break;

                        case "BreakStatement":
                            BreakStatement breakStatement=new BreakStatement();
                            baseValue.addAttributes(breakStatement);
                            break;

                        case "StringExpression":
                            StringExpression stringExpression;
                            if(baseValue instanceof Expression) {
                                stringExpression=new StringExpression(((Expression) baseValue).expressionLayer, ((Expression) baseValue).type);
                            } else {
                                stringExpression=new StringExpression();
                            }
                            baseValue.addAttributes(stringExpression);
                            break;

                        case "LiteralExpression":
                            LiteralExpression literalExpression;
                            if(baseValue instanceof Expression) {
                                literalExpression=new LiteralExpression(((Expression) baseValue).expressionLayer, ((Expression) baseValue).type);
                            } else if (baseValue instanceof RHS) {
                                literalExpression=new LiteralExpression(1, ((RHS) baseValue).type);
                            } else if(baseValue instanceof ArithmeticExpression) {
                                literalExpression=new LiteralExpression(((ArithmeticExpression) baseValue).expressionLayer, ((ArithmeticExpression) baseValue).type);
                            } else if(baseValue instanceof ReturnStatement) {
                                literalExpression=new LiteralExpression(1, ((ReturnStatement) baseValue).type);
                            } else {
                                literalExpression=new LiteralExpression();
                            }
                            baseValue.addAttributes(literalExpression);
                            break;

                        case "IntegerLiteral":
                            IntegerLiteral integerLiteral=new IntegerLiteral();
                            baseValue.addAttributes(integerLiteral);
                            break;

                        case "ArrayDeclaration":
                            ArrayDeclaration newArray=new ArrayDeclaration();
                            baseValue.addAttributes(newArray);
                            break;

                        case "FloatLiteral":
                            FloatLiteral floatLiteral=new FloatLiteral();
                            baseValue.addAttributes(floatLiteral);
                            break;

                        case "StringLiteral":
                            StringLiteral stringLiteral=new StringLiteral();
                            baseValue.addAttributes(stringLiteral);
                            break;

                        case "CharacterLiteral":
                            CharacterLiteral characterLiteral=new CharacterLiteral();
                            baseValue.addAttributes(characterLiteral);
                            break;

                        case "PostIncrementDecrement":
                            PostIncrementDecrement postIncrementDecrement;
                            if(baseValue instanceof NumericExpression) {
                                postIncrementDecrement = new PostIncrementDecrement(((NumericExpression) baseValue).expressionLayer, ((NumericExpression) baseValue).type);
                            } else {
                                postIncrementDecrement = new PostIncrementDecrement(((ForStatement) baseValue).expressionLayer, ((ForStatement) baseValue).type);
                            }
                            baseValue.addAttributes(postIncrementDecrement);
                            break;

                        case "PreIncrementDecrement":
                            PreIncrementDecrement preIncrementDecrement=new PreIncrementDecrement(((NumericExpression) baseValue).expressionLayer, ((NumericExpression) baseValue).type);
                            baseValue.addAttributes(preIncrementDecrement);
                            break;

                        case "PrePostOperations":
                            PrePostOperations prePostOperations=new PrePostOperations();
                            baseValue.addAttributes(prePostOperations);
                            break;

                        case "ArithmeticExpression":
                            ArithmeticExpression arithmeticExpression;
                            if(baseValue instanceof Expression) {
                                arithmeticExpression=new ArithmeticExpression(((Expression) baseValue).expressionLayer, ((Expression) baseValue).type);
                            } else if(baseValue instanceof NumericExpression) {
                                arithmeticExpression=new ArithmeticExpression(((NumericExpression) baseValue).expressionLayer, ((NumericExpression) baseValue).type);
                            } else {
                                arithmeticExpression=new ArithmeticExpression();
                            }
                            baseValue.addAttributes(arithmeticExpression);
                            break;

                        case "ArithmeticOperation":
                            ArithmeticOperation arithmeticOperation=new ArithmeticOperation();
                            baseValue.addAttributes(arithmeticOperation);
                            break;

                        case "Initialization":
                            Initialization newInitialization=new Initialization(((localVariableDeclaration) baseValue).type);
                            baseValue.addAttributes(newInitialization);
                            break;

                        case "BracketedExpression":
                            BracketedExpression bracketedExpression=new BracketedExpression();
                            baseValue.addAttributes(bracketedExpression);
                            break;

                        case "DoStatement":
                            DoStatement doStatement=new DoStatement();
                            baseValue.addAttributes(doStatement);
                            break;

                        case "Logical":
                            Logical logical;
                            if(baseValue instanceof Expression) {
                                logical = new Logical(((Expression) baseValue).expressionLayer, ((Expression) baseValue).type);
                            } else {
                                logical=new Logical();
                            }
                            baseValue.addAttributes(logical); break;

                        case "LogicalExpression":
                            LogicalExpression newLogicalExpression = new LogicalExpression();
                            baseValue.addAttributes(newLogicalExpression);
                            break;

                        case "NotExpression":
                            NotExpression notExpression;
                            if(baseValue instanceof Logical) {
                                notExpression=new NotExpression(((Logical) baseValue).expressionLayer, ((Logical) baseValue).type);
                            } else {
                                notExpression=new NotExpression();
                            }
                            baseValue.addAttributes(notExpression);
                            break;

                        case "SwitchStatement":
                            SwitchStatement switchStatement=new SwitchStatement();
                            baseValue.addAttributes(switchStatement);
                            break;

                        case "TerminalExpression":
                            TerminalExpression terminalExpression=new TerminalExpression();
                            baseValue.addAttributes(terminalExpression);
                            break;

                        case "TestingExpression":
                            TestingExpression newTestingExpression;
                            if(baseValue instanceof Expression) {
                                newTestingExpression=new TestingExpression(((Expression) baseValue).expressionLayer, ((Expression) baseValue).type);
                            } else {
                                newTestingExpression=new TestingExpression();
                            }
                            baseValue.addAttributes(newTestingExpression);
                            break;

                        case "Expression":
                            Expression expression;
                            if ( baseValue instanceof NumericExpression) {
                                expression=new Expression(((NumericExpression) baseValue).expressionLayer, ((NumericExpression) baseValue).type);
                            } else if ( baseValue instanceof ArithmeticExpression) {
                                expression=new Expression(((ArithmeticExpression) baseValue).expressionLayer, ((ArithmeticExpression) baseValue).type);
                            } else if ( baseValue instanceof TestingExpression) {
                                expression=new Expression(((TestingExpression) baseValue).expressionLayer, ((TestingExpression) baseValue).type);
                            } else if ( baseValue instanceof NotExpression) {
                                expression=new Expression(((NotExpression) baseValue).expressionLayer, ((NotExpression) baseValue).type);
                            } else if ( baseValue instanceof TernaryExpression) {
                                expression=new Expression(((TernaryExpression) baseValue).expressionLayer, ((TernaryExpression) baseValue).type);
                            } else if ( baseValue instanceof StringExpression) {
                                expression=new Expression(((StringExpression) baseValue).expressionLayer, ((StringExpression) baseValue).type);
                            } else if ( baseValue instanceof Assignment ) {
                                expression=new Expression(1 , ((Assignment) baseValue).type);
                            }  else {
                                expression=new Expression();
                            }
                            baseValue.addAttributes(expression);
                            break;

                        case "Assignment":
                            Assignment assignment=new Assignment();
                            baseValue.addAttributes(assignment);
                            break;

                        case "RelationalOperation":
                            RelationalOperation relationalOperation=new RelationalOperation(((TestingExpression) baseValue).type);
                            baseValue.addAttributes(relationalOperation);
                            break;

                        case "NumericExpression":
                            NumericExpression numericExpression;
                            if(baseValue instanceof Expression) {
                                numericExpression=new NumericExpression(((Expression) baseValue).expressionLayer, ((Expression) baseValue).type);
                            } else {
                                numericExpression=new NumericExpression();
                            }
                            baseValue.addAttributes(numericExpression);
                            break;

                        case "LogicalOps":
                            LogicalOps logicalOps=new LogicalOps();
                            baseValue.addAttributes(logicalOps);
                            break;

                        case "TernaryExpression":
                            TernaryExpression newTernaryExpression;
                            if(baseValue instanceof Logical) {
                                newTernaryExpression=new TernaryExpression(((Logical) baseValue).expressionLayer, ((Logical) baseValue).type);
                            } else {
                                newTernaryExpression=new TernaryExpression();
                            }
                            baseValue.addAttributes(newTernaryExpression);
                            break;

                        case "DefaultStatement":
                            DefaultStatement newDefaultStatement = new DefaultStatement();
                            baseValue.addAttributes(newDefaultStatement);
                            break;

                        case "CaseStatement":
                            CaseStatement newCaseStatement = new CaseStatement();
                            baseValue.addAttributes(newCaseStatement);
                            break;

                        case "Integer":
                            Integer newInteger = new Integer();
                            baseValue.addAttributes(newInteger);
                            break;

                        case "Integer0":
                            Integer0 newInteger0 = new Integer0();
                            baseValue.addAttributes(newInteger0);
                            break;

                        case "alphabets":
                            alphabets newalphabets = new alphabets();
                            baseValue.addAttributes(newalphabets);
                            break;

                        case "WhileStatement":
                            WhileStatement whileStatement=new WhileStatement();
                            baseValue.addAttributes(whileStatement);
                            break;

                        case "Dot":
                            Dot dot=new Dot();
                            baseValue.addAttributes(dot);
                            break;

                        case "DoubleQuotation":
                            DoubleQuotation doubleQuotation=new DoubleQuotation();
                            baseValue.addAttributes(doubleQuotation);
                            break;

                        case "SingleQuotation":
                            SingleQuotation singleQuotation=new SingleQuotation();
                            baseValue.addAttributes(singleQuotation);
                            break;

                        case "ParameterList":
                            ParameterList parameterList=new ParameterList();
                            baseValue.addAttributes(parameterList);
                            break;

                        case "comma":
                            comma newComma =new comma();
                            baseValue.addAttributes(newComma);
                            break;

                        case "ParameterVariable":
                            ParameterVariable parameterVariable=new ParameterVariable();
                            baseValue.addAttributes(parameterVariable);
                            break;

                        case "Parameter1":
                            Parameter1 newParameter1=new Parameter1();
                            baseValue.addAttributes(newParameter1);
                            break;

                        case "StringOperations":
                            StringOperations stringOperations=new StringOperations();
                            baseValue.addAttributes(stringOperations);
                            break;

                        case "StatementWithoutTrailingSubstatement":
                            StatementWithoutTrailingSubstatement statementWithoutTrailingSubstatement=new StatementWithoutTrailingSubstatement();
                            baseValue.addAttributes(statementWithoutTrailingSubstatement);
                            break;

                        case "RHS":
                            RHS rhs;
                            if(baseValue instanceof LoopAssignment) {
                                rhs=new RHS(((LoopAssignment) baseValue).type);
                            }
                            else {
                                rhs=new RHS(((TestingExpression) baseValue).type);
                            }
                            baseValue.addAttributes(rhs);
                            break;

                        case "LoopAssignment":
                            LoopAssignment loopAssignment=new LoopAssignment();
                            baseValue.addAttributes(loopAssignment);
                            break;

                        case "AssignExpression":
                            AssignExpression assignExpression=new AssignExpression();
                            baseValue.addAttributes(assignExpression);
                            break;

                        default:
                            Keyword newKeyword = new Keyword(value, baseValue);
                            baseValue.addAttributes(newKeyword);
                    }
                }
            }
        }
    }

    // Function
    private static int zeroOrMoreChecker(String value) {
        if (value.equals("extends")) {
            int possibleValues = TypeList.typeValues.size();
            if (possibleValues == 0) {
                return possibleValues;
            }
        } else if (value.equals("implements")) {
            int possibleInterfaceValues = symbolTable.listOfInterfaces.size();
            if (possibleInterfaceValues == 0) {
                return possibleInterfaceValues;
            }
        } else if (value.equals("FieldDeclaration")) {
            return rnd.nextInt(10) + Configuration.MinNoOfClassFields;
        } else if (value.equals("Statement")) {
            return rnd.nextInt(10) + Configuration.MinNoOfStatementsMethod;
        } else if (value.equals("ConstructorDeclaration")) {
            return rnd.nextInt(2) + Configuration.MinNoOfConstructors;
        } else if (value.equals("MethodDeclaration")) {
            return rnd.nextInt(3) + Configuration.MinNoOfMethodsDeclared;
        }  else if (value.equals("Parameter1")) {
            return rnd.nextInt(5) + Configuration.MinNoOfParametersMethod;
        }  else if (value.equals("ArrayDeclaration")) {
            return rnd.nextInt(3) + Configuration.MinNoOfArrayDeclarations;
        }
        return rnd.nextInt(5);
    }

    private static int zeroOrNoChecker(String value, Base basevalue) {
        if (value.equals("extends")) {
            int possibleValues = TypeList.typeValues.size();
            if (possibleValues == 0) {
                return possibleValues;
            }
        }
        else if(value.equals("ReturnStatement")) {
            String returnType = ((BlockStatement) basevalue).returnType;
            if(returnType.equals("void")) {
                return 0;
            }
            else {
                return 1;
            }
        }
        else if(value.equals("implements")) {
            int possibleInterfaceValues = symbolTable.listOfInterfaces.size();
            if (possibleInterfaceValues == 0) {
                return possibleInterfaceValues;
            }
        }
        return rnd.nextInt(2);
    }

    private static String anyOneChecker(Base baseValue, String[] listOfValues) {
        int selector = 0;
        String chosenstr = "";
        if(baseValue instanceof Statement) {
            selector = rnd.nextInt(15);
            if(selector < 6) {
                chosenstr = listOfValues[selector];
            } else {
                chosenstr = listOfValues[0];
            }
        }
        if (baseValue instanceof Expression) {
            if (rnd.nextInt(5) < 2) {
                String[] toChoose = new String[]{"Identifier"};
                selector = rnd.nextInt(toChoose.length);
                chosenstr = toChoose[selector];
            } else if (((Expression) baseValue).type == "int") {
                String[] toChoose = new String[]{"NumericExpression", "LiteralExpression"};
                selector = rnd.nextInt(toChoose.length);
                chosenstr = toChoose[selector];
            } else if (((Expression) baseValue).type == "String") {
                String[] toChoose = new String[]{"StringExpression", "LiteralExpression"};
                selector = rnd.nextInt(toChoose.length);
                chosenstr = toChoose[selector];
            } else if (((Expression) baseValue).type == "boolean") {
                String[] toChoose = new String[]{"Logical", "TestingExpression"};
                selector = rnd.nextInt(toChoose.length);
                chosenstr = toChoose[selector];
            } else if (((Expression) baseValue).type == "char") {
                chosenstr = "LiteralExpression";
            } else if (((Expression) baseValue).type == "float") {
                String[] toChoose = new String[]{"NumericExpression", "LiteralExpression"};
                selector = rnd.nextInt(toChoose.length);
                chosenstr = toChoose[selector];
            } else if (((Expression) baseValue).type == "double") {
                String[] toChoose = new String[]{"NumericExpression", "LiteralExpression"};
                selector = rnd.nextInt(toChoose.length);
                chosenstr = toChoose[selector];
            } else if (((Expression) baseValue).type == "byte") {
                String[] toChoose = new String[]{"NumericExpression", "LiteralExpression"};
                selector = rnd.nextInt(toChoose.length);
                chosenstr = toChoose[selector];
            } else if (((Expression) baseValue).type == "long") {
                String[] toChoose = new String[]{"NumericExpression", "LiteralExpression"};
                selector = rnd.nextInt(toChoose.length);
                chosenstr = toChoose[selector];
            } else if (((Expression) baseValue).type == "double") {
                String[] toChoose = new String[]{"NumericExpression", "LiteralExpression"};
                selector = rnd.nextInt(toChoose.length);
                chosenstr = toChoose[selector];
            }
        } else if (baseValue instanceof LiteralExpression) {
            if (((LiteralExpression) baseValue).type.equals("int")) {
                String[] toChoose = new String[]{"IntegerLiteral"};
                selector = rnd.nextInt(toChoose.length);
                chosenstr = toChoose[selector];
            } else if (((LiteralExpression) baseValue).type.equals("String")) {
                chosenstr = "StringLiteral";
            } else if (((LiteralExpression) baseValue).type.equals("boolean")) {
                String[] toChoose = new String[]{"true","false"};
                selector = rnd.nextInt(toChoose.length);
                chosenstr = toChoose[selector];
            } else if (((LiteralExpression) baseValue).type.equals("char")) {
                chosenstr = "CharacterLiteral";
            } else if (((LiteralExpression) baseValue).type.equals("float")) {
                String[] toChoose = new String[]{"FloatLiteral"};
                selector = rnd.nextInt(toChoose.length);
                chosenstr = toChoose[selector];
            } else if (((LiteralExpression) baseValue).type.equals("double")) {
                String[] toChoose = new String[]{"IntegerLiteral", "FloatLiteral"};
                selector = rnd.nextInt(toChoose.length);
                chosenstr = toChoose[selector];
            } else if (((LiteralExpression) baseValue).type.equals("short")) {
                String[] toChoose = new String[]{"IntegerLiteral"};
                selector = rnd.nextInt(toChoose.length);
                chosenstr = toChoose[selector];
            } else if (((LiteralExpression) baseValue).type.equals("long")) {
                String[] toChoose = new String[]{"IntegerLiteral"};
                selector = rnd.nextInt(toChoose.length);
                chosenstr = toChoose[selector];
            } else {
                selector = rnd.nextInt(listOfValues.length);
                chosenstr = listOfValues[selector];
            }
        } else if (baseValue instanceof RelationalOperation) {
            if (((RelationalOperation) baseValue).type.equals("boolean")) {
                String[] toChoose = new String[]{"==", "!="};
                selector = rnd.nextInt(toChoose.length);
                chosenstr = toChoose[selector];
            } else {
                selector = rnd.nextInt(listOfValues.length);
                chosenstr = listOfValues[selector];
            }
        } else if (baseValue instanceof Initialization) {
            if (((Initialization) baseValue).type.equals("boolean")) {
                String[] toChoose = new String[]{"true", "false"};
                selector = rnd.nextInt(toChoose.length);
                chosenstr = toChoose[selector];
            } else if (((Initialization) baseValue).type.equals("int")) {
                String[] toChoose = new String[]{"IntegerLiteral"};
                selector = rnd.nextInt(toChoose.length);
                chosenstr = toChoose[selector];
            } else if (((Initialization) baseValue).type.equals("String")) {
                String[] toChoose = new String[]{"StringLiteral"};
                selector = rnd.nextInt(toChoose.length);
                chosenstr = toChoose[selector];
            } else if (((Initialization) baseValue).type.equals("char")) {
                String[] toChoose = new String[]{"CharacterLiteral"};
                selector = rnd.nextInt(toChoose.length);
                chosenstr = toChoose[selector];
            } else if (((Initialization) baseValue).type.equals("double")) {
                String[] toChoose = new String[]{"FloatLiteral"};
                selector = rnd.nextInt(toChoose.length);
                chosenstr = toChoose[selector];
            } else {
                selector = rnd.nextInt(listOfValues.length);
                chosenstr = listOfValues[selector];
            }
        } else{
            selector = rnd.nextInt(listOfValues.length);
            chosenstr=listOfValues[selector];
        }
        return chosenstr;
    }

    public static String[] readGrammar(String typeValue) throws IOException {
        String[] seperateValues = null;
        for (String line:GrammarString) {
            seperateValues=line.split(" ");
            if(seperateValues[0].substring(0, seperateValues[0].length() - 1).equals(typeValue)) {
                break;
            }
        }
        return seperateValues;
    }
}
