// Generated from Pie.g4 by ANTLR 4.5

  package pie.parser; 

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PieParser}.
 */
public interface PieListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PieParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(PieParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link PieParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(PieParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link PieParser#structDefinition}.
	 * @param ctx the parse tree
	 */
	void enterStructDefinition(PieParser.StructDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PieParser#structDefinition}.
	 * @param ctx the parse tree
	 */
	void exitStructDefinition(PieParser.StructDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PieParser#functionDefinition}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDefinition(PieParser.FunctionDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PieParser#functionDefinition}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDefinition(PieParser.FunctionDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PieParser#vardef}.
	 * @param ctx the parse tree
	 */
	void enterVardef(PieParser.VardefContext ctx);
	/**
	 * Exit a parse tree produced by {@link PieParser#vardef}.
	 * @param ctx the parse tree
	 */
	void exitVardef(PieParser.VardefContext ctx);
	/**
	 * Enter a parse tree produced by {@link PieParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(PieParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link PieParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(PieParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatStructDefinition}
	 * labeled alternative in {@link PieParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatStructDefinition(PieParser.StatStructDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatStructDefinition}
	 * labeled alternative in {@link PieParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatStructDefinition(PieParser.StatStructDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatAssigment}
	 * labeled alternative in {@link PieParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatAssigment(PieParser.StatAssigmentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatAssigment}
	 * labeled alternative in {@link PieParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatAssigment(PieParser.StatAssigmentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatReturn}
	 * labeled alternative in {@link PieParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatReturn(PieParser.StatReturnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatReturn}
	 * labeled alternative in {@link PieParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatReturn(PieParser.StatReturnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatPrint}
	 * labeled alternative in {@link PieParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatPrint(PieParser.StatPrintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatPrint}
	 * labeled alternative in {@link PieParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatPrint(PieParser.StatPrintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatIf}
	 * labeled alternative in {@link PieParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatIf(PieParser.StatIfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatIf}
	 * labeled alternative in {@link PieParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatIf(PieParser.StatIfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StaWhile}
	 * labeled alternative in {@link PieParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStaWhile(PieParser.StaWhileContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StaWhile}
	 * labeled alternative in {@link PieParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStaWhile(PieParser.StaWhileContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatFunctionCall}
	 * labeled alternative in {@link PieParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatFunctionCall(PieParser.StatFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatFunctionCall}
	 * labeled alternative in {@link PieParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatFunctionCall(PieParser.StatFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatNL}
	 * labeled alternative in {@link PieParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatNL(PieParser.StatNLContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatNL}
	 * labeled alternative in {@link PieParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatNL(PieParser.StatNLContext ctx);
	/**
	 * Enter a parse tree produced by {@link PieParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(PieParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link PieParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(PieParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprCondition}
	 * labeled alternative in {@link PieParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprCondition(PieParser.ExprConditionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprCondition}
	 * labeled alternative in {@link PieParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprCondition(PieParser.ExprConditionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprFunctionCall}
	 * labeled alternative in {@link PieParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprFunctionCall(PieParser.ExprFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprFunctionCall}
	 * labeled alternative in {@link PieParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprFunctionCall(PieParser.ExprFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprMult}
	 * labeled alternative in {@link PieParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprMult(PieParser.ExprMultContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprMult}
	 * labeled alternative in {@link PieParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprMult(PieParser.ExprMultContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprString}
	 * labeled alternative in {@link PieParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprString(PieParser.ExprStringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprString}
	 * labeled alternative in {@link PieParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprString(PieParser.ExprStringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprParens}
	 * labeled alternative in {@link PieParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprParens(PieParser.ExprParensContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprParens}
	 * labeled alternative in {@link PieParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprParens(PieParser.ExprParensContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprField}
	 * labeled alternative in {@link PieParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprField(PieParser.ExprFieldContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprField}
	 * labeled alternative in {@link PieParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprField(PieParser.ExprFieldContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprInt}
	 * labeled alternative in {@link PieParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprInt(PieParser.ExprIntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprInt}
	 * labeled alternative in {@link PieParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprInt(PieParser.ExprIntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprStructCreation}
	 * labeled alternative in {@link PieParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprStructCreation(PieParser.ExprStructCreationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprStructCreation}
	 * labeled alternative in {@link PieParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprStructCreation(PieParser.ExprStructCreationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprAdd}
	 * labeled alternative in {@link PieParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprAdd(PieParser.ExprAddContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprAdd}
	 * labeled alternative in {@link PieParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprAdd(PieParser.ExprAddContext ctx);
	/**
	 * Enter a parse tree produced by {@link PieParser#field}.
	 * @param ctx the parse tree
	 */
	void enterField(PieParser.FieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link PieParser#field}.
	 * @param ctx the parse tree
	 */
	void exitField(PieParser.FieldContext ctx);
}