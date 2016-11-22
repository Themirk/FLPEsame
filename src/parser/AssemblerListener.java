package parser;// Generated from /Users/longm/IdeaProjects/FLPEsame/Assembler.g4 by ANTLR 4.5.3
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link AssemblerParser}.
 */
public interface AssemblerListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link AssemblerParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(AssemblerParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblerParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(AssemblerParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblerParser#globals}.
	 * @param ctx the parse tree
	 */
	void enterGlobals(AssemblerParser.GlobalsContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblerParser#globals}.
	 * @param ctx the parse tree
	 */
	void exitGlobals(AssemblerParser.GlobalsContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblerParser#functionDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDeclaration(AssemblerParser.FunctionDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblerParser#functionDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDeclaration(AssemblerParser.FunctionDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblerParser#labelAddress}.
	 * @param ctx the parse tree
	 */
	void enterLabelAddress(AssemblerParser.LabelAddressContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblerParser#labelAddress}.
	 * @param ctx the parse tree
	 */
	void exitLabelAddress(AssemblerParser.LabelAddressContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblerParser#instr}.
	 * @param ctx the parse tree
	 */
	void enterInstr(AssemblerParser.InstrContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblerParser#instr}.
	 * @param ctx the parse tree
	 */
	void exitInstr(AssemblerParser.InstrContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblerParser#operand}.
	 * @param ctx the parse tree
	 */
	void enterOperand(AssemblerParser.OperandContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblerParser#operand}.
	 * @param ctx the parse tree
	 */
	void exitOperand(AssemblerParser.OperandContext ctx);
}