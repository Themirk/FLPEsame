package parser;// Generated from /Users/longm/IdeaProjects/FLPEsame/Assembler.g4 by ANTLR 4.5.3

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class AssemblerParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, ID=8, FUNC=9, 
		INT=10, STRING=11, FLOAT=12, WS=13, NEWLINE=14;
	public static final int
		RULE_program = 0, RULE_globals = 1, RULE_functionDeclaration = 2, RULE_labelAddress = 3, 
		RULE_instr = 4, RULE_operand = 5;
	public static final String[] ruleNames = {
		"program", "globals", "functionDeclaration", "labelAddress", "instr", 
		"operand"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'.globals'", "'.def'", "':'", "'args'", "'='", "','", "'locals'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, "ID", "FUNC", "INT", "STRING", 
		"FLOAT", "WS", "NEWLINE"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Assembler.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	    // Define the functionality required by the parser for code generation
	    protected void gen(Token instrToken){;}
	    protected void gen(Token instrToken, Token operandToken){;}
	    protected void checkForUnresolvedReferences(){;}
	    protected void defineFunction(Token idToken, int nargs, int nlocals){;}
	    protected void defineDataSize(int n) {;}
	    protected void defineLabel(Token idToken) {;}

	public AssemblerParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public List<FunctionDeclarationContext> functionDeclaration() {
			return getRuleContexts(FunctionDeclarationContext.class);
		}
		public FunctionDeclarationContext functionDeclaration(int i) {
			return getRuleContext(FunctionDeclarationContext.class,i);
		}
		public List<LabelAddressContext> labelAddress() {
			return getRuleContexts(LabelAddressContext.class);
		}
		public LabelAddressContext labelAddress(int i) {
			return getRuleContext(LabelAddressContext.class,i);
		}
		public List<InstrContext> instr() {
			return getRuleContexts(InstrContext.class);
		}
		public InstrContext instr(int i) {
			return getRuleContext(InstrContext.class,i);
		}
		public GlobalsContext globals() {
			return getRuleContext(GlobalsContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(AssemblerParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(AssemblerParser.NEWLINE, i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblerListener ) ((AssemblerListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblerListener ) ((AssemblerListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssemblerVisitor ) return ((AssemblerVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(13);
			_la = _input.LA(1);
			if (_la==T__0 || _la==NEWLINE) {
				{
				setState(12);
				globals();
				}
			}

			setState(18);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(15);
				functionDeclaration();
				}
				break;
			case 2:
				{
				setState(16);
				labelAddress();
				}
				break;
			case 3:
				{
				setState(17);
				instr();
				}
				break;
			}
			setState(32);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(21); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(20);
						match(NEWLINE);
						}
						}
						setState(23); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==NEWLINE );
					setState(28);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
					case 1:
						{
						setState(25);
						functionDeclaration();
						}
						break;
					case 2:
						{
						setState(26);
						labelAddress();
						}
						break;
					case 3:
						{
						setState(27);
						instr();
						}
						break;
					}
					}
					} 
				}
				setState(34);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			}
			setState(38);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(35);
				match(NEWLINE);
				}
				}
				setState(40);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			checkForUnresolvedReferences();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GlobalsContext extends ParserRuleContext {
		public Token INT;
		public TerminalNode INT() { return getToken(AssemblerParser.INT, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(AssemblerParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(AssemblerParser.NEWLINE, i);
		}
		public GlobalsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_globals; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblerListener ) ((AssemblerListener)listener).enterGlobals(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblerListener ) ((AssemblerListener)listener).exitGlobals(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssemblerVisitor ) return ((AssemblerVisitor<? extends T>)visitor).visitGlobals(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GlobalsContext globals() throws RecognitionException {
		GlobalsContext _localctx = new GlobalsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_globals);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(43);
				match(NEWLINE);
				}
				}
				setState(48);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(49);
			match(T__0);
			setState(50);
			((GlobalsContext)_localctx).INT = match(INT);
			setState(51);
			match(NEWLINE);
			defineDataSize((((GlobalsContext)_localctx).INT!=null?Integer.valueOf(((GlobalsContext)_localctx).INT.getText()):0));
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionDeclarationContext extends ParserRuleContext {
		public Token name;
		public Token a;
		public Token n;
		public TerminalNode ID() { return getToken(AssemblerParser.ID, 0); }
		public List<TerminalNode> INT() { return getTokens(AssemblerParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(AssemblerParser.INT, i);
		}
		public FunctionDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblerListener ) ((AssemblerListener)listener).enterFunctionDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblerListener ) ((AssemblerListener)listener).exitFunctionDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssemblerVisitor ) return ((AssemblerVisitor<? extends T>)visitor).visitFunctionDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionDeclarationContext functionDeclaration() throws RecognitionException {
		FunctionDeclarationContext _localctx = new FunctionDeclarationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_functionDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			match(T__1);
			setState(55);
			((FunctionDeclarationContext)_localctx).name = match(ID);
			setState(56);
			match(T__2);
			setState(57);
			match(T__3);
			setState(58);
			match(T__4);
			setState(59);
			((FunctionDeclarationContext)_localctx).a = match(INT);
			setState(60);
			match(T__5);
			setState(61);
			match(T__6);
			setState(62);
			match(T__4);
			setState(63);
			((FunctionDeclarationContext)_localctx).n = match(INT);
			defineFunction(((FunctionDeclarationContext)_localctx).name, (((FunctionDeclarationContext)_localctx).a!=null?Integer.valueOf(((FunctionDeclarationContext)_localctx).a.getText()):0), (((FunctionDeclarationContext)_localctx).n!=null?Integer.valueOf(((FunctionDeclarationContext)_localctx).n.getText()):0));
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LabelAddressContext extends ParserRuleContext {
		public Token ID;
		public TerminalNode ID() { return getToken(AssemblerParser.ID, 0); }
		public LabelAddressContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_labelAddress; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblerListener ) ((AssemblerListener)listener).enterLabelAddress(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblerListener ) ((AssemblerListener)listener).exitLabelAddress(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssemblerVisitor ) return ((AssemblerVisitor<? extends T>)visitor).visitLabelAddress(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LabelAddressContext labelAddress() throws RecognitionException {
		LabelAddressContext _localctx = new LabelAddressContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_labelAddress);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			((LabelAddressContext)_localctx).ID = match(ID);
			setState(67);
			match(T__2);
			defineLabel(((LabelAddressContext)_localctx).ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InstrContext extends ParserRuleContext {
		public Token ID;
		public OperandContext operand;
		public TerminalNode ID() { return getToken(AssemblerParser.ID, 0); }
		public OperandContext operand() {
			return getRuleContext(OperandContext.class,0);
		}
		public InstrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblerListener ) ((AssemblerListener)listener).enterInstr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblerListener ) ((AssemblerListener)listener).exitInstr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssemblerVisitor ) return ((AssemblerVisitor<? extends T>)visitor).visitInstr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstrContext instr() throws RecognitionException {
		InstrContext _localctx = new InstrContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_instr);
		try {
			setState(76);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(70);
				((InstrContext)_localctx).ID = match(ID);
				setState(71);
				((InstrContext)_localctx).operand = operand();
				gen(((InstrContext)_localctx).ID,(((InstrContext)_localctx).operand!=null?(((InstrContext)_localctx).operand.start):null));
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(74);
				((InstrContext)_localctx).ID = match(ID);
				gen(((InstrContext)_localctx).ID);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperandContext extends ParserRuleContext {
		public TerminalNode FUNC() { return getToken(AssemblerParser.FUNC, 0); }
		public TerminalNode ID() { return getToken(AssemblerParser.ID, 0); }
		public TerminalNode FLOAT() { return getToken(AssemblerParser.FLOAT, 0); }
		public TerminalNode STRING() { return getToken(AssemblerParser.STRING, 0); }
		public TerminalNode INT() { return getToken(AssemblerParser.INT, 0); }
		public OperandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblerListener ) ((AssemblerListener)listener).enterOperand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblerListener ) ((AssemblerListener)listener).exitOperand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssemblerVisitor ) return ((AssemblerVisitor<? extends T>)visitor).visitOperand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperandContext operand() throws RecognitionException {
		OperandContext _localctx = new OperandContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_operand);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ID) | (1L << FUNC) | (1L << INT) | (1L << STRING) | (1L << FLOAT))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\20S\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\3\2\5\2\20\n\2\3\2\3\2\3\2\5\2\25\n"+
		"\2\3\2\6\2\30\n\2\r\2\16\2\31\3\2\3\2\3\2\5\2\37\n\2\7\2!\n\2\f\2\16\2"+
		"$\13\2\3\2\7\2\'\n\2\f\2\16\2*\13\2\3\2\3\2\3\3\7\3/\n\3\f\3\16\3\62\13"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\5\6O\n\6\3\7\3\7\3\7\2\2\b\2"+
		"\4\6\b\n\f\2\3\3\2\n\16V\2\17\3\2\2\2\4\60\3\2\2\2\68\3\2\2\2\bD\3\2\2"+
		"\2\nN\3\2\2\2\fP\3\2\2\2\16\20\5\4\3\2\17\16\3\2\2\2\17\20\3\2\2\2\20"+
		"\24\3\2\2\2\21\25\5\6\4\2\22\25\5\b\5\2\23\25\5\n\6\2\24\21\3\2\2\2\24"+
		"\22\3\2\2\2\24\23\3\2\2\2\25\"\3\2\2\2\26\30\7\20\2\2\27\26\3\2\2\2\30"+
		"\31\3\2\2\2\31\27\3\2\2\2\31\32\3\2\2\2\32\36\3\2\2\2\33\37\5\6\4\2\34"+
		"\37\5\b\5\2\35\37\5\n\6\2\36\33\3\2\2\2\36\34\3\2\2\2\36\35\3\2\2\2\37"+
		"!\3\2\2\2 \27\3\2\2\2!$\3\2\2\2\" \3\2\2\2\"#\3\2\2\2#(\3\2\2\2$\"\3\2"+
		"\2\2%\'\7\20\2\2&%\3\2\2\2\'*\3\2\2\2(&\3\2\2\2()\3\2\2\2)+\3\2\2\2*("+
		"\3\2\2\2+,\b\2\1\2,\3\3\2\2\2-/\7\20\2\2.-\3\2\2\2/\62\3\2\2\2\60.\3\2"+
		"\2\2\60\61\3\2\2\2\61\63\3\2\2\2\62\60\3\2\2\2\63\64\7\3\2\2\64\65\7\f"+
		"\2\2\65\66\7\20\2\2\66\67\b\3\1\2\67\5\3\2\2\289\7\4\2\29:\7\n\2\2:;\7"+
		"\5\2\2;<\7\6\2\2<=\7\7\2\2=>\7\f\2\2>?\7\b\2\2?@\7\t\2\2@A\7\7\2\2AB\7"+
		"\f\2\2BC\b\4\1\2C\7\3\2\2\2DE\7\n\2\2EF\7\5\2\2FG\b\5\1\2G\t\3\2\2\2H"+
		"I\7\n\2\2IJ\5\f\7\2JK\b\6\1\2KO\3\2\2\2LM\7\n\2\2MO\b\6\1\2NH\3\2\2\2"+
		"NL\3\2\2\2O\13\3\2\2\2PQ\t\2\2\2Q\r\3\2\2\2\n\17\24\31\36\"(\60N";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}