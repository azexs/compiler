package compiler;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import parser.compilerParser;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;


public class App  {

    public static void main(String[] args) {

        String input = null;
        String output = null;
        CharStream charStream = null;

        try{
            input = args[0];
            output = args[1];
            charStream = CharStreams.fromFileName(input);

        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        parser.compilerLexer lexer = new parser.compilerLexer(charStream);
        compilerParser parser = new compilerParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.program();
        Compiler compiler = new Compiler();
        compiler.visit(tree);

        if (compiler.errors.size() > 0) {
            System.err.println(compiler.errors.get(0));
            return;
        }

        try (PrintStream out = new PrintStream(new FileOutputStream(output))) {
            compiler.commands.forEach(out::println);
            out.println("HALT");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}