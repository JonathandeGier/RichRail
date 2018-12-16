package richrail.ui.dsl;

import java.util.Scanner;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import parser.RichRailLexer;
import parser.RichRailListener;
import parser.RichRailParser;
import richrail.service.TreinController;

public class DSLMain {
    public static void dsl() {
        String input = "";
        Scanner sc = new Scanner(System.in);
        while(!input.equals("quit")) {
            input = sc.nextLine();
            interpret(input);
        }
        sc.close();
    }

    public static void interpret(String text) {
        CharStream lineStream = CharStreams.fromString(text);

        // Tokenize / Lexical analysis
        Lexer lexer = new RichRailLexer(lineStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Create Parse Tree
        RichRailParser parser = new RichRailParser(tokens);
        ParseTree tree = parser.command();

        // Create ParseTreeWalker and Custom Listener
        ParseTreeWalker walker = new ParseTreeWalker();
        RichRailListener listener = new DSLInterpreter(new TreinController());

        // Walk over ParseTree using Custom Listener that listens to enter/exit events
        walker.walk(listener, tree);
    }
}
