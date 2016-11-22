package main;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import pie.parser.PieLexer;
import pie.parser.PieParser;
import pie.symtab.ScopePrinter;
import pie.symtab.SymbolTableBuilderError;
import pie.symtab.Validator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by longm on 14/11/16.
 */
public class PieMain
{
    private CharStream input = null;
    private PieMain(CharStream input)
    {
        this.input = input;
    }
    private void start()
    {
        //PARSE
        System.out.println(">>>PARSING...");
        PieLexer lexer = new PieLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        PieParser parser = new PieParser(tokens);
        ParseTree parseTree = parser.program();

        if (parser.getNumberOfSyntaxErrors() > 0)
        {
            System.out.println(parser.getNumberOfSyntaxErrors() + " errors.");
            return;
        }

        //BUILDING SYMBOL TABLE
        System.out.println(">>>BUILDING SYMBOL TABLE...");
        Validator symBuilder = new Validator();
        ParseTreeWalker walker = new ParseTreeWalker();
        try
        {
            walker.walk(symBuilder, parseTree);
        }
        catch (SymbolTableBuilderError e)
        {
            System.exit(1);
        }

        //PTINTING SYMBOL TABLE
        System.out.println(">>>PRINTING SCOPES...");
        ScopePrinter printer = new ScopePrinter(symBuilder.getScopes());
        walker.walk(printer, parseTree);

        //TRANSLATION
        System.out.println(">>>STARTING TRANSLATION...");
        PieTranslator traduttore = new PieTranslator();
        traduttore.visit(parseTree);
    }

    public static void main(String[] args) throws IOException
    {
        InputStream input = new FileInputStream(new File("test/prova.pie"));
        PieMain main = new PieMain(new ANTLRInputStream(input));
        main.start();
    }
}
