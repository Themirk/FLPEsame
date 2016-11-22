package main;

import pie.parser.PieBaseVisitor;
import pie.parser.PieParser;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by longm on 14/11/16.
 */
public class PieTranslator extends PieBaseVisitor<Object>
{
    //CAMPI
    private ArrayList<String> assembly = new ArrayList ();
    private int numGlobals = 0;
    private int actualLevel = 0;
    private HashMap<Integer, Integer> localsForLevel = new HashMap<Integer, Integer>();
    private int loopCount = 0;
    private HashMap<String, ArrayList<String>> listFunction = new HashMap();
    private String actualFunction;

    //COSTRUTTORE
    public PieTranslator(){}

    @Override
    public Object visitProgram(PieParser.ProgramContext ctx)
    {
        System.out.println("Sto visitando PROGRAM e contengo " + ctx.getText());
        List<PieParser.StatementContext> children  = ctx.statement();
        for (int i = 0; i < children.size(); i++) {
            if(children.get(i) instanceof PieParser.StatAssigmentContext)
                numGlobals++;
        }
        assembly.add(".def main: args=0" + ", locals=" + numGlobals);

        for (PieParser.StatementContext con : ctx.statement()) {
            //esecuzione del visit corretto
            visit(con);
        }
        assembly.add("halt");

        //visita della definizione di una funzione
        for (PieParser.FunctionDefinitionContext fun : ctx.functionDefinition())
        {
            visit(fun);
        }
        try
        {
            //trascrizione su file di testo
            BufferedWriter writer = new BufferedWriter(new FileWriter("programmaAssembly.txt"));
            for (int i = 0; i < assembly.size(); i++) {
                writer.write(assembly.get(i));
                writer.newLine();
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object visitFunctionDefinition(PieParser.FunctionDefinitionContext ctx)
    {
        System.out.println("Sto visitando FUNCTION DEFINITION e contengo " + ctx.getText());
        int index = assembly.size();
        listFunction.put(ctx.ID().getText(), new ArrayList<String>());
        actualFunction = ctx.ID().getText();
        for (int i = 0; i < ctx.vardef().size(); i++) {
            assembly.add("store " + i);
            listFunction.get(actualFunction).add(ctx.vardef(i).getText());
        }
        visit(ctx.block());
        localsForLevel.putIfAbsent(0, 0);
        assembly.add(index, ".def " + actualFunction + ": args=" + ctx.vardef().size() + ", locals=" + localsForLevel.get(0));
        return null;
    }

    @Override
    public Object visitVardef(PieParser.VardefContext ctx)
    {
        System.out.println("Sto visitando VARDEF e contengo " + ctx.getText());
        return null;
    }

    @Override
    public Object visitBlock(PieParser.BlockContext ctx) {
        System.out.println("Sto visitando BLOCK e contengo " + ctx.getText());
        int localCount = 0;
        for (PieParser.StatementContext con : ctx.statement()) {
            if (con instanceof PieParser.StatAssigmentContext) {
                localsForLevel.put(actualLevel++, ++localCount);
            }
            visit(con);
            if (con instanceof PieParser.StatementContext) {
                actualLevel--;
            }
        }
        return null;
    }

    @Override
    public Object visitStatAssigment(PieParser.StatAssigmentContext ctx)
    {
        System.out.println("Sto visitando STATASSIGMENT e contengo " + ctx.getText());
        return null;
    }

    @Override
    public Object visitStatReturn(PieParser.StatReturnContext ctx)
    {
        System.out.println("Sto visitando RETURN e contengo " + ctx.getText());
        visit(ctx.expr());
        assembly.add("ret");
        return null;
    }

    @Override
    public Object visitStatPrint(PieParser.StatPrintContext ctx)
    {
        System.out.println("Sto visitando STATPRINT e contengo " + ctx.getText());
        visit(ctx.expr());
        assembly.add("print");
        return null;
    }

    @Override public Object visitStatIf(PieParser.StatIfContext ctx) {								// blocco if
        System.out.println("Sto visitando IFTHENELSE e contengo " + ctx.getText());
        visit(ctx.expr());																			// visito l'espressione di condizione
        loopCount++;
        assembly.add("brf jumpIf_" + loopCount);													// se e falsa salto al blocco 1 cioe quello dell'else
        visit(ctx.block(0));																		// eseguo il corpo dell'if se l'espressione e vera
        assembly.add("br jumpElse_" + loopCount);													// salto il blocco dell'esle
        assembly.add("jumpIf_" + loopCount);

        if (ctx.block().size() > 1)																	// controllo se c'e l'else
            visit(ctx.block(1));
        assembly.add("jumpElse_" + loopCount);
        loopCount--;
        return null;
    }

    @Override
    public Object visitStatFunctionCall(PieParser.StatFunctionCallContext ctx) {
        System.out.println("Sto visitando STAT FUNCTION CALL e contengo " + ctx.getText());
        return null;
    }

    @Override
    public Object visitStatNL(PieParser.StatNLContext ctx) {
        System.out.println("Sto visitando STAT NL e contengo " + ctx.getText());
        return null;
    }

    @Override
    public Object visitFunctionCall(PieParser.FunctionCallContext ctx) {
        System.out.println("Sto visitando FUNCTION CALL e contengo " + ctx.getText());
        for (PieParser.ExprContext exp : ctx.expr())
        {
            visit(exp);
        }
        assembly.add("call " + ctx.ID());
        return null;
    }

    @Override
    public Object visitExprCondition(PieParser.ExprConditionContext ctx)
    {
        System.out.println("Sto visitando EXPR CONDITION e contengo " + ctx.getText());
        for (PieParser.ExprContext exp : ctx.expr())
        {
            visit(exp);
        }
        System.out.println("Il tipo di operatore è " + ctx.op.getText());
        if(ctx.op.getText().equals("=="))
            assembly.add("ieq");
        else
            assembly.add("ilt");
        return null;
    }

    @Override
    public Object visitExprFunctionCall(PieParser.ExprFunctionCallContext ctx) {
        System.out.println("Sto visitando EXPR FUNCTION CALL e contengo " + ctx.getText());
        visit(ctx.functionCall());
        return null;
    }

    @Override
    public Object visitExprMult(PieParser.ExprMultContext ctx) {
        System.out.println("Sto visitando EXPR MULT e contengo " + ctx.getText());
        for (PieParser.ExprContext exp : ctx.expr())
        {
            visit(exp);
        }
        System.out.println("Il tipo di operatore è " + ctx.op.getText());
        if(ctx.op.getText().equals("*"))
            assembly.add("imul");
        else
            assembly.add("idiv");
        return null;
    }

    @Override
    public Object visitExprString(PieParser.ExprStringContext ctx)
    {
        System.out.println("Sto visitando EXPR STRING e contengo " + ctx.getText());
        return null;
    }

    @Override
    public Object visitExprParens(PieParser.ExprParensContext ctx) {
        System.out.println("Sto visitando EXPR PARENS e contengo " + ctx.getText());
        return null;
    }

    @Override
    public Object visitExprField(PieParser.ExprFieldContext ctx)
    {
        System.out.println("Sto visitando EXPR FIELD e contengo " + ctx.getText());
        int index = listFunction.get(actualFunction).indexOf(ctx.getText());
        assembly.add("load " + index);
        return null;
    }

    @Override
    public Object visitExprInt(PieParser.ExprIntContext ctx) {
        System.out.println("Sto visitando EXPR INT e contengo " + ctx.getText());
        assembly.add("iconst " + ctx.INT());
        return null;
    }

    @Override
    public Object visitExprAdd(PieParser.ExprAddContext ctx) {
        System.out.println("Sto visitando EXPR ADD e contengo " + ctx.getText());
        for (PieParser.ExprContext exp : ctx.expr())
        {
            visit(exp);
        }
        System.out.println("Il tipo di operatore è " + ctx.op.getText());
        if(ctx.op.getText().equals("+"))
            assembly.add("iadd");
        else
            assembly.add("isub");
        return null;
    }

    @Override
    public Object visitField(PieParser.FieldContext ctx)
    {
        System.out.println("Sto visitando VISIT FIELD e contengo " + ctx.getText());
        return null;
    }
}
