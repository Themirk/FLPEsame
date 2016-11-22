// Generated from Pie.g4 by ANTLR 4.5

  package pie.parser; 

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link PieParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface PieVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link PieParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(PieParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link PieParser#structDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructDefinition(PieParser.StructDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PieParser#functionDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDefinition(PieParser.FunctionDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PieParser#vardef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVardef(PieParser.VardefContext ctx);
	/**
	 * Visit a parse tree produced by {@link PieParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(PieParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatStructDefinition}
	 * labeled alternative in {@link PieParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatStructDefinition(PieParser.StatStructDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatAssigment}
	 * labeled alternative in {@link PieParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatAssigment(PieParser.StatAssigmentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatReturn}
	 * labeled alternative in {@link PieParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatReturn(PieParser.StatReturnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatPrint}
	 * labeled alternative in {@link PieParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatPrint(PieParser.StatPrintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatIf}
	 * labeled alternative in {@link PieParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatIf(PieParser.StatIfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StaWhile}
	 * labeled alternative in {@link PieParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStaWhile(PieParser.StaWhileContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatFunctionCall}
	 * labeled alternative in {@link PieParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatFunctionCall(PieParser.StatFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatNL}
	 * labeled alternative in {@link PieParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatNL(PieParser.StatNLContext ctx);
	/**
	 * Visit a parse tree produced by {@link PieParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(PieParser.FunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprCondition}
	 * labeled alternative in {@link PieParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprCondition(PieParser.ExprConditionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprFunctionCall}
	 * labeled alternative in {@link PieParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprFunctionCall(PieParser.ExprFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprMult}
	 * labeled alternative in {@link PieParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprMult(PieParser.ExprMultContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprString}
	 * labeled alternative in {@link PieParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprString(PieParser.ExprStringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprParens}
	 * labeled alternative in {@link PieParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprParens(PieParser.ExprParensContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprField}
	 * labeled alternative in {@link PieParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprField(PieParser.ExprFieldContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprInt}
	 * labeled alternative in {@link PieParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprInt(PieParser.ExprIntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprStructCreation}
	 * labeled alternative in {@link PieParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprStructCreation(PieParser.ExprStructCreationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprAdd}
	 * labeled alternative in {@link PieParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAdd(PieParser.ExprAddContext ctx);
	/**
	 * Visit a parse tree produced by {@link PieParser#field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField(PieParser.FieldContext ctx);
}