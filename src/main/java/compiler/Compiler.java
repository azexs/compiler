package compiler;


import parser.compilerBaseVisitor;
import parser.compilerParser;
import java.util.ArrayList;
import java.util.HashMap;


public class Compiler extends compilerBaseVisitor<Integer> {

    ArrayList<String> commands;
    HeheMemory memory;
    HashMap<String, Variable> variables;
    ArrayList<Error> errors;

    public Compiler() {
        this.memory = new HeheMemory();
        this.commands = new ArrayList<>();
        this.variables = new HashMap<>();
        this.errors = new ArrayList<>();
    }

    @Override
    public Integer visitDeclarations(parser.compilerParser.DeclarationsContext ctx) {
        Variable s;
        int line = ctx.start.getLine();
        String name = ctx.PIDENTIFIER().getText();

        if (variables.containsKey(name)) errors.add(new Error("Druga deklaracja zmiennej " + name, line));

        if (ctx.NUM(0) != null && ctx.NUM(1) != null) {
            long rangeStart = Long.parseLong(ctx.NUM(0).getSymbol().getText());
            long rangeEnd = Long.parseLong(ctx.NUM(1).getSymbol().getText());
            if (rangeStart > rangeEnd) errors.add(new Error("Niepoprawny zakres tablicy " + name, line));
            s = new Variable(Type.ARRAY, name);
            s.setArray(rangeStart, rangeEnd);
            s.isInitialized = true;
        } else {
            s = new Variable(Type.VARIABLE, name);
        }

        memory.declare(s);
        variables.put(name, s);

        return visitChildren(ctx);
    }

    @Override
    public Integer visitCmdWrite(compilerParser.CmdWriteContext ctx) {
        if (ctx.value().NUM() == null) {
            loadValue(ctx.value(), "a", "f");
        } else {
            commands.add("RESET f");
            this.commands.addAll(generateNumber(Long.parseLong(ctx.value().NUM().getText()), "a"));
            commands.add("STORE a f");
        }
        commands.add("PUT f");
        return 0;
    }

    @Override
    public Integer visitCmdRead(compilerParser.CmdReadContext ctx) {
        super.visitCmdRead(ctx);
        commands.add("GET b");
        return 0;
    }

    @Override
    public Integer visitCmdAssign(compilerParser.CmdAssignContext ctx) {
        visit(ctx.expression());
        visit(ctx.identifier());
        commands.add("STORE " + "a " + "b");
        return 0;
    }

    @Override
    public Integer visitIdentifier(compilerParser.IdentifierContext ctx) {
        if (ctx.NUM() != null) {
            //a(5);
            String name = ctx.PIDENTIFIER(0).getText();
            if (!variables.containsKey(name))
                errors.add(new Error("Niezadeklarowana zmienna: " + name, ctx.start.getLine()));
            else {
                Variable a = variables.get(name);
                long num = Long.parseLong(ctx.NUM().getText());
                if (!a.isArray)
                    errors.add(new Error("Niepoprawne uzycie zmiennej: " + name, ctx.start.getLine()));
                long shift = num - a.rangeStart;
                long arrayLocation = a.location + shift;
                this.commands.addAll(generateNumber(arrayLocation, "b"));
            }

        } else if (ctx.PIDENTIFIER(1) != null) {
            //a(b)
            String name = ctx.PIDENTIFIER(0).getText();
            if (!variables.containsKey(name))
                errors.add(new Error("Niezadeklarowana zmienna: " + name, ctx.start.getLine()));
            else {
                String name2 = ctx.PIDENTIFIER(1).getText();
                Variable a = variables.get(name);//findVar(name);
                if (!a.isArray)
                    errors.add(new Error("Niepoprawne uzycie zmiennej: " + name, ctx.start.getLine()));
                if (!variables.containsKey(name2))
                    errors.add(new Error("Niezadeklarowana zmienna: " + name2, ctx.start.getLine()));
                else {
                    Variable b = variables.get(name2);//findVar(name2);
                    if (b.isArray)
                        errors.add(new Error("Niepoprawne uzycie zmiennej: " + name2, ctx.start.getLine()));
                    this.commands.addAll(generateNumber(b.location, "f"));
                    commands.add("LOAD " + "b" + " f");
                    this.commands.addAll(generateNumber(a.location, "f"));
                    commands.add("ADD " + "b" + " f");
                    this.commands.addAll(generateNumber(a.rangeStart, "f"));
                    commands.add("SUB " + "b" + " f");
                }
            }
        } else {
            //n;
            String name = ctx.PIDENTIFIER(0).getText();
            if (!variables.containsKey(name)) {
                errors.add(new Error("Niezadeklarowana zmienna: " + name, ctx.start.getLine()));
            } else {
                Variable a = variables.get(name);
                if (a.isArray) errors.add(new Error("Niepoprawne uzycie zmiennej: " + name, ctx.start.getLine()));
                if (a.isIterator)
                    errors.add(new Error("Modyfikacja iteratora wewnatrz petli: " + name, ctx.start.getLine()));
                this.commands.addAll(generateNumber(a.location, "b"));
                a.isInitialized = true;
            }
        }
        return super.visitIdentifier(ctx);
    }


    public Integer loadValue(compilerParser.ValueContext ctx, String registerN, String registerA) {
        if (ctx.NUM() != null) {
            this.commands.addAll(generateNumber(Long.parseLong(ctx.NUM().getText()), registerN));
        } else if (ctx.identifier().NUM() != null) {
            //a(5);
            String name = ctx.identifier().PIDENTIFIER(0).getText();
            if (!variables.containsKey(name))
                errors.add(new Error("Niezadeklarowana zmienna: " + name, ctx.start.getLine()));
            else {
                Variable a = variables.get(name);
                if (!a.isArray)
                    errors.add(new Error("Niepoprawne uzycie zmiennej: " + name, ctx.start.getLine()));
                long num = Long.parseLong(ctx.identifier().NUM().getText());
                long shift = num - a.rangeStart;
                long arrayLocation = a.location + shift;
                this.commands.addAll(generateNumber(arrayLocation, registerA));
                commands.add("LOAD " + registerN + " " + registerA);
            }
        } else if (ctx.identifier().PIDENTIFIER(1) != null) {
            //a(b)
            String name = ctx.identifier().PIDENTIFIER(0).getText();
            String name2 = ctx.identifier().PIDENTIFIER(1).getText();

            if (!variables.containsKey(name))
                errors.add(new Error("Niezadeklarowana zmienna: " + name, ctx.start.getLine()));
            else {
                Variable a = variables.get(name);
                if (!a.isArray)
                    errors.add(new Error("Niepoprawne uzycie zmiennej: " + name, ctx.start.getLine()));
                if (!variables.containsKey(name2))
                    errors.add(new Error("Niezadeklarowana zmienna: " + name2, ctx.start.getLine()));
                else {
                    Variable b = variables.get(name2);
                    if (b.isArray)
                        errors.add(new Error("Niepoprawne uzycie zmiennej: " + name2, ctx.start.getLine()));
                    if (!b.isInitialized)
                        errors.add(new Error("Niezainicjowana zmienna: " + name2, ctx.start.getLine()));
                    this.commands.addAll(generateNumber(b.location, "d"));
                    commands.add("LOAD " + registerA + "d");
                    this.commands.addAll(generateNumber(a.location, "d"));
                    commands.add("ADD " + registerA + " d");
                    this.commands.addAll(generateNumber(a.rangeStart, "d"));
                    commands.add("SUB " + registerA + " d");
                    commands.add("LOAD " + registerN + " " + registerA);
                }
            }
        } else {
            //n;
            String name = ctx.identifier().PIDENTIFIER(0).getText();
            if (!variables.containsKey(name))
                errors.add(new Error("Niezadeklarowana zmienna: " + name, ctx.start.getLine()));
            else {
                Variable var = variables.get(name);
                if (!var.isInitialized) errors.add(new Error("Niezainicjowana zmienna: " + name, ctx.start.getLine()));
                else {
                    this.commands.addAll(generateNumber(var.location, registerA));
                    commands.add("LOAD " + registerN + " " + registerA);
                }
            }
        }

        return 0;
    }

    @Override
    public Integer visitCondition(compilerParser.ConditionContext ctx) {

        switch (ctx.op.getType()) {
            case compilerParser.EQ:
                loadValue(ctx.value(0), "a", "f");
                loadValue(ctx.value(1), "b", "f");
                loadValue(ctx.value(0), "c", "f");
                loadValue(ctx.value(1), "d", "f");
                this.commands.add("SUB a b");
                this.commands.add("SUB d c");
                this.commands.add("ADD a d");
                this.commands.add("JZERO a 2");
                this.commands.add("JUMP ??");
                break;
            case compilerParser.NEQ:
                loadValue(ctx.value(0), "a", "f");
                loadValue(ctx.value(1), "b", "f");
                loadValue(ctx.value(0), "c", "f");
                loadValue(ctx.value(1), "d", "f");
                this.commands.add("SUB a b");
                this.commands.add("SUB d c");
                this.commands.add("ADD a d");
                this.commands.add("JZERO a ??");
                break;
            case compilerParser.LE:
                loadValue(ctx.value(1), "a", "f");
                loadValue(ctx.value(0), "b", "f");
                this.commands.add("SUB a b");
                this.commands.add("JZERO a ??");
                break;
            case compilerParser.GE:
                loadValue(ctx.value(0), "a", "f");
                loadValue(ctx.value(1), "b", "f");
                this.commands.add("SUB a b");
                this.commands.add("JZERO a ??");
                break;
            case compilerParser.LEQ:
                loadValue(ctx.value(1), "a", "f");
                loadValue(ctx.value(0), "b", "f");
                this.commands.add("RESET f");
                this.commands.add("ADD f b");
                this.commands.add("SUB f a");
                this.commands.add("JZERO f 2");
                this.commands.add("JUMP ??");
                break;
            case compilerParser.GEQ:
                loadValue(ctx.value(0), "a", "f");
                loadValue(ctx.value(1), "b", "f");
                this.commands.add("RESET f");
                this.commands.add("ADD f b");
                this.commands.add("SUB f a");
                this.commands.add("JZERO f 2");
                this.commands.add("JUMP ??");
                break;
            default:
                System.out.println("2137");
        }
        return 997;
    }

    @Override
    public Integer visitCmdRepeat(compilerParser.CmdRepeatContext ctx) {

        int line = this.commands.size();
        visit(ctx.commands());
        visit(ctx.condition());

        switch (ctx.condition().op.getType()) {
            case compilerParser.LE:
            case compilerParser.GE:
            case compilerParser.NEQ:
                commands.set(this.commands.size() - 1, "JZERO a " + (line - this.commands.size() + 1));
                break;
            case compilerParser.LEQ:
            case compilerParser.GEQ:
            case compilerParser.EQ:
                commands.set(this.commands.size() - 1, "JUMP " + (line - this.commands.size() + 1));
                break;
            default:
                System.out.println("2137");
        }

        return 0;
    }

    @Override
    public Integer visitCmdWhile(compilerParser.CmdWhileContext ctx) {

        int tline = this.commands.size();
        visit(ctx.condition());
        int line = this.commands.size();
        visit(ctx.commands());
        int lineAfter = this.commands.size();

        switch (ctx.condition().op.getType()) {
            case compilerParser.LE:
            case compilerParser.GE:
            case compilerParser.NEQ:
                commands.set(line - 1, "JZERO a " + (lineAfter - line + 2));
                break;
            case compilerParser.LEQ:
            case compilerParser.GEQ:
            case compilerParser.EQ:
                commands.set(line - 1, "JUMP " + (lineAfter - line + 2));
                break;
            default:
                System.out.println("2137");
        }

        int j = lineAfter - tline;
        this.commands.add("JUMP -" + j);
        return 0;
    }


    @Override
    public Integer visitCmdIf(compilerParser.CmdIfContext ctx) {

        visit(ctx.condition());
        int line = this.commands.size();
        visit(ctx.commands());
        int lineAfter = this.commands.size();

        switch (ctx.condition().op.getType()) {
            case compilerParser.LE:
            case compilerParser.GE:
            case compilerParser.NEQ:
                commands.set(line - 1, "JZERO a " + (lineAfter - line + 1));
                break;
            case compilerParser.LEQ:
            case compilerParser.GEQ:
            case compilerParser.EQ:
                commands.set(line - 1, "JUMP " + (lineAfter - line + 1));
                break;
            default:
                System.out.println("2137");
        }

        return 0;
    }


    @Override
    public Integer visitCmdIfElse(compilerParser.CmdIfElseContext ctx) {

        visit(ctx.condition());
        int line = this.commands.size();
        visit(ctx.commands(0));
        commands.add("JUMP ???? ");
        int lineAfter = this.commands.size();
        visit(ctx.commands(1));
        int tline = this.commands.size();

        switch (ctx.condition().op.getType()) {
            case compilerParser.LE:
            case compilerParser.GE:
            case compilerParser.NEQ:
                commands.set(line - 1, "JZERO a " + (lineAfter - line + 1));
                break;
            case compilerParser.LEQ:
            case compilerParser.GEQ:
            case compilerParser.EQ:
                commands.set(line - 1, "JUMP " + (lineAfter - line + 1));
                break;
            default:
                System.out.println("2137");
        }

        commands.set(lineAfter - 1, "JUMP " + (tline - lineAfter + 1));

        return 0;
    }

    @Override
    public Integer visitCmdForUp(compilerParser.CmdForUpContext ctx) {

        Variable it = memory.getTemp();
        it.name = ctx.PIDENTIFIER().getText();
        it.isInitialized = true;
        it.isIterator = true;
        variables.put(it.name, it);
        Variable end = memory.getTemp();

        loadValue(ctx.value(0), "a", "f");
        if (ctx.value(0).getText().equals(it.name))
            errors.add(new Error("Niezadeklarowana zmienna o tej samej wartości co iterator: " + it.name, ctx.start.getLine()));
        commands.addAll(generateNumber(it.location, "b"));
        commands.add("STORE a b");

        loadValue(ctx.value(1), "a", "f");
        commands.addAll(generateNumber(end.location, "b"));
        commands.add("STORE a b");

        int conditionStartLine = commands.size();

        commands.addAll(generateNumber(it.location, "b"));
        commands.add("LOAD a b");
        commands.addAll(generateNumber(end.location, "b"));
        commands.add("LOAD c b");
        commands.add("SUB a c");
        commands.add("JZERO a 2");
        commands.add("JUMP ??");

        int conditionEndLine = commands.size();

        visitCommands(ctx.commands());

        commands.addAll(generateNumber(it.location, "b"));
        commands.add("LOAD a b");
        commands.add("INC a");
        commands.add("STORE a b");
        commands.add("JUMP ??????");

        int instructionEndLine = commands.size();

        commands.set(conditionEndLine - 1, "JUMP " + (instructionEndLine - conditionEndLine + 1));
        commands.set(instructionEndLine - 1, "JUMP " + (conditionStartLine - instructionEndLine + 1));

        memory.remove(it);
        memory.remove(end);
        variables.remove(it.name);



        return 0;
    }


    @Override
    public Integer visitCmdForDown(compilerParser.CmdForDownContext ctx) {

        Variable it = memory.getTemp();
        it.name = ctx.PIDENTIFIER().getText();
        it.isInitialized = true;
        it.isIterator = true;
        variables.put(it.name, it);
        Variable end = memory.getTemp();

        loadValue(ctx.value(0), "a", "f");
        commands.addAll(generateNumber(it.location, "b"));
        commands.add("STORE a b");

        loadValue(ctx.value(1), "a", "f");
        commands.addAll(generateNumber(end.location, "b"));
        commands.add("STORE a b");

        int conditionStartLine = commands.size();

        commands.addAll(generateNumber(it.location, "b"));
        commands.add("LOAD a b");
        commands.addAll(generateNumber(end.location, "b"));
        commands.add("LOAD c b");
        commands.add("SUB c a ");
        commands.add("JZERO c 2");
        commands.add("JUMP ??");

        int conditionEndLine = commands.size();

        visitCommands(ctx.commands());

        commands.addAll(generateNumber(it.location, "b"));
        commands.add("LOAD a b");
        commands.add("JZERO a 4");
        commands.add("DEC a");
        commands.add("STORE a b");
        commands.add("JUMP ??????");

        int instructionEndLine = commands.size();

        commands.set(conditionEndLine - 1, "JUMP " + (instructionEndLine - conditionEndLine + 1));
        commands.set(instructionEndLine - 1, "JUMP " + (conditionStartLine - instructionEndLine + 1));

        memory.remove(it);
        memory.remove(end);
        variables.remove(it.name);

        return 0;
    }


    @Override
    public Integer visitSimpleExp(compilerParser.SimpleExpContext ctx) {
        loadValue(ctx.value(), "a", "f");
        return 0;
    }

    @Override
    public Integer visitPlusExp(compilerParser.PlusExpContext ctx) {
        loadValue(ctx.value(0), "a", "f");
        loadValue(ctx.value(1), "b", "f");
        this.commands.add("ADD a b");
        return 0;
    }

    @Override
    public Integer visitMinusExp(compilerParser.MinusExpContext ctx) {
        loadValue(ctx.value(0), "a", "f");
        loadValue(ctx.value(1), "b", "f");
        this.commands.add("SUB a b");
        return 0;
    }

    @Override
    public Integer visitTimesExp(compilerParser.TimesExpContext ctx) {
        loadValue(ctx.value(0), "c", "f");
        loadValue(ctx.value(1), "d", "f");

        this.commands.add("RESET a");
        this.commands.add("ADD a d");
        this.commands.add("SUB a c");
        this.commands.add("JZERO a 7");
        this.commands.add("RESET a");
        this.commands.add("ADD a d");
        this.commands.add("RESET d");
        this.commands.add("ADD d c");
        this.commands.add("RESET c");
        this.commands.add("ADD c a");
        this.commands.add("RESET a");
        this.commands.add("JZERO d 17 ");
        this.commands.add("RESET b");
        this.commands.add("INC b");
        this.commands.add("RESET e");
        this.commands.add("ADD e c");
        this.commands.add("RESET f");
        this.commands.add("ADD f b");
        this.commands.add("SUB f d");
        this.commands.add("JZERO f 6");
        this.commands.add("SHR b");
        this.commands.add("SHR e");
        this.commands.add("SUB d b");
        this.commands.add("ADD a e");
        this.commands.add("JUMP -13 ");
        this.commands.add("SHL b");
        this.commands.add("SHL e");
        this.commands.add("JUMP -11");
        return 0;
    }


    @Override
    public Integer visitDivExp(compilerParser.DivExpContext ctx) {

        loadValue(ctx.value(0), "c", "f");
        loadValue(ctx.value(1), "d", "f");
        this.commands.add("RESET a");
        this.commands.add("JZERO d 22");
        this.commands.add("RESET f");
        this.commands.add("ADD f d");
        this.commands.add("SUB f c");
        this.commands.add("JZERO f 2 ");
        this.commands.add("JUMP 17 ");
        this.commands.add("RESET b");
        this.commands.add("INC b");
        this.commands.add("RESET e");
        this.commands.add("ADD e d");
        this.commands.add("RESET f");
        this.commands.add("ADD f e");
        this.commands.add("SUB f c");
        this.commands.add("JZERO f 6");
        this.commands.add("SHR b");
        this.commands.add("SHR e");
        this.commands.add("SUB c e");
        this.commands.add("ADD a b");
        this.commands.add("JUMP -17 ");
        this.commands.add("SHL b");
        this.commands.add("SHL e");
        this.commands.add("JUMP -11");
        return 0;
    }

    @Override
    public Integer visitModExp(compilerParser.ModExpContext ctx) {
        this.commands.add("RESET a");
        loadValue(ctx.value(1), "b", "f");
        this.commands.add("JZERO b ??");
        int line = commands.size();
        loadValue(ctx.value(0), "a", "f");
        commands.set(line - 1, "JZERO b " + (commands.size() - line + 23));
        this.commands.add("RESET c");
        this.commands.add("RESET f");
        this.commands.add("ADD f b");
        this.commands.add("SUB f a");
        this.commands.add("JZERO f 2");
        this.commands.add("JUMP 17 ");
        this.commands.add("RESET d");
        this.commands.add("INC d");
        this.commands.add("RESET e");
        this.commands.add("ADD e b");
        this.commands.add("RESET f");
        this.commands.add("ADD f e");
        this.commands.add("SUB f a");
        this.commands.add("JZERO f 6");
        this.commands.add("SHR d");
        this.commands.add("SHR e");
        this.commands.add("SUB a e");
        this.commands.add("ADD c d");
        this.commands.add("JUMP -17 ");
        this.commands.add("SHL d");
        this.commands.add("SHL e");
        this.commands.add("JUMP -11");
        return 0;
    }

    private ArrayList<String> generateNumber(long number, String register) {
        ArrayList<String> commands = new ArrayList<>();
        while (number != 0) {
            if (number % 2 == 0) {
                number /= 2;
                commands.add(0, "SHL " + register);
            } else {
                number = number - 1;
                commands.add(0, "INC " + register);
            }
        }
        commands.add(0, "RESET " + register);

        return commands;
    }

}
