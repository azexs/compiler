// Generated from compiler.g4 by ANTLR 4.7.1

    package parser;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link compilerParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface compilerVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link compilerParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(compilerParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link compilerParser#declarations}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarations(compilerParser.DeclarationsContext ctx);
	/**
	 * Visit a parse tree produced by {@link compilerParser#commands}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommands(compilerParser.CommandsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cmdAssign}
	 * labeled alternative in {@link compilerParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdAssign(compilerParser.CmdAssignContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cmdIfElse}
	 * labeled alternative in {@link compilerParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdIfElse(compilerParser.CmdIfElseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cmdIf}
	 * labeled alternative in {@link compilerParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdIf(compilerParser.CmdIfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cmdWhile}
	 * labeled alternative in {@link compilerParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdWhile(compilerParser.CmdWhileContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cmdRepeat}
	 * labeled alternative in {@link compilerParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdRepeat(compilerParser.CmdRepeatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cmdForUp}
	 * labeled alternative in {@link compilerParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdForUp(compilerParser.CmdForUpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cmdForDown}
	 * labeled alternative in {@link compilerParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdForDown(compilerParser.CmdForDownContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cmdRead}
	 * labeled alternative in {@link compilerParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdRead(compilerParser.CmdReadContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cmdWrite}
	 * labeled alternative in {@link compilerParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdWrite(compilerParser.CmdWriteContext ctx);
	/**
	 * Visit a parse tree produced by the {@code plusExp}
	 * labeled alternative in {@link compilerParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlusExp(compilerParser.PlusExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code minusExp}
	 * labeled alternative in {@link compilerParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMinusExp(compilerParser.MinusExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code timesExp}
	 * labeled alternative in {@link compilerParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTimesExp(compilerParser.TimesExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code divExp}
	 * labeled alternative in {@link compilerParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivExp(compilerParser.DivExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code modExp}
	 * labeled alternative in {@link compilerParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModExp(compilerParser.ModExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code simpleExp}
	 * labeled alternative in {@link compilerParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleExp(compilerParser.SimpleExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link compilerParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(compilerParser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link compilerParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(compilerParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link compilerParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(compilerParser.IdentifierContext ctx);
}