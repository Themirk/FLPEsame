package parser;// Generated from /Users/longm/IdeaProjects/FLPEsame/Assembler.g4 by ANTLR 4.5.3

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class AssemblerLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, ID=8, FUNC=9, 
		INT=10, STRING=11, FLOAT=12, WS=13, NEWLINE=14;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "ID", "FUNC", 
		"LETTER", "INT", "STRING", "STR_CHARS", "FLOAT", "WS", "NEWLINE"
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


	    // Define the functionality required by the parser for code generation
	    protected void gen(Token instrToken){;}
	    protected void gen(Token instrToken, Token operandToken){;}
	    protected void checkForUnresolvedReferences(){;}
	    protected void defineFunction(Token idToken, int nargs, int nlocals){;}
	    protected void defineDataSize(int n) {;}
	    protected void defineLabel(Token idToken) {;}


	public AssemblerLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Assembler.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 8:
			FUNC_action((RuleContext)_localctx, actionIndex);
			break;
		case 11:
			STRING_action((RuleContext)_localctx, actionIndex);
			break;
		case 14:
			WS_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void FUNC_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			;
			break;
		}
	}
	private void STRING_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:
			;
			break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2:
			skip();
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\20\u0097\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\5"+
		"\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\7\tG"+
		"\n\t\f\t\16\tJ\13\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\f\5\fU\n\f\3\f"+
		"\6\fX\n\f\r\f\16\fY\3\r\3\r\3\r\3\r\3\r\3\16\7\16b\n\16\f\16\16\16e\13"+
		"\16\3\17\3\17\3\17\7\17j\n\17\f\17\16\17m\13\17\3\17\3\17\6\17q\n\17\r"+
		"\17\16\17r\3\17\3\17\3\17\7\17x\n\17\f\17\16\17{\13\17\3\17\3\17\6\17"+
		"\177\n\17\r\17\16\17\u0080\5\17\u0083\n\17\3\20\6\20\u0086\n\20\r\20\16"+
		"\20\u0087\3\20\3\20\3\21\3\21\7\21\u008e\n\21\f\21\16\21\u0091\13\21\3"+
		"\21\5\21\u0094\n\21\3\21\3\21\2\2\22\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21"+
		"\n\23\13\25\2\27\f\31\r\33\2\35\16\37\17!\20\3\2\5\4\2C\\c|\3\2$$\4\2"+
		"\13\13\"\"\u00a3\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13"+
		"\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\27\3\2\2"+
		"\2\2\31\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\3#\3\2\2\2\5,\3\2"+
		"\2\2\7\61\3\2\2\2\t\63\3\2\2\2\138\3\2\2\2\r:\3\2\2\2\17<\3\2\2\2\21C"+
		"\3\2\2\2\23K\3\2\2\2\25Q\3\2\2\2\27T\3\2\2\2\31[\3\2\2\2\33c\3\2\2\2\35"+
		"\u0082\3\2\2\2\37\u0085\3\2\2\2!\u008f\3\2\2\2#$\7\60\2\2$%\7i\2\2%&\7"+
		"n\2\2&\'\7q\2\2\'(\7d\2\2()\7c\2\2)*\7n\2\2*+\7u\2\2+\4\3\2\2\2,-\7\60"+
		"\2\2-.\7f\2\2./\7g\2\2/\60\7h\2\2\60\6\3\2\2\2\61\62\7<\2\2\62\b\3\2\2"+
		"\2\63\64\7c\2\2\64\65\7t\2\2\65\66\7i\2\2\66\67\7u\2\2\67\n\3\2\2\289"+
		"\7?\2\29\f\3\2\2\2:;\7.\2\2;\16\3\2\2\2<=\7n\2\2=>\7q\2\2>?\7e\2\2?@\7"+
		"c\2\2@A\7n\2\2AB\7u\2\2B\20\3\2\2\2CH\5\25\13\2DG\5\25\13\2EG\4\62;\2"+
		"FD\3\2\2\2FE\3\2\2\2GJ\3\2\2\2HF\3\2\2\2HI\3\2\2\2I\22\3\2\2\2JH\3\2\2"+
		"\2KL\5\21\t\2LM\7*\2\2MN\7+\2\2NO\3\2\2\2OP\b\n\2\2P\24\3\2\2\2QR\t\2"+
		"\2\2R\26\3\2\2\2SU\7/\2\2TS\3\2\2\2TU\3\2\2\2UW\3\2\2\2VX\4\62;\2WV\3"+
		"\2\2\2XY\3\2\2\2YW\3\2\2\2YZ\3\2\2\2Z\30\3\2\2\2[\\\7$\2\2\\]\5\33\16"+
		"\2]^\7$\2\2^_\b\r\3\2_\32\3\2\2\2`b\n\3\2\2a`\3\2\2\2be\3\2\2\2ca\3\2"+
		"\2\2cd\3\2\2\2d\34\3\2\2\2ec\3\2\2\2fg\5\27\f\2gk\7\60\2\2hj\5\27\f\2"+
		"ih\3\2\2\2jm\3\2\2\2ki\3\2\2\2kl\3\2\2\2l\u0083\3\2\2\2mk\3\2\2\2np\7"+
		"\60\2\2oq\5\27\f\2po\3\2\2\2qr\3\2\2\2rp\3\2\2\2rs\3\2\2\2s\u0083\3\2"+
		"\2\2tu\5\27\f\2uy\7.\2\2vx\5\27\f\2wv\3\2\2\2x{\3\2\2\2yw\3\2\2\2yz\3"+
		"\2\2\2z\u0083\3\2\2\2{y\3\2\2\2|~\7.\2\2}\177\5\27\f\2~}\3\2\2\2\177\u0080"+
		"\3\2\2\2\u0080~\3\2\2\2\u0080\u0081\3\2\2\2\u0081\u0083\3\2\2\2\u0082"+
		"f\3\2\2\2\u0082n\3\2\2\2\u0082t\3\2\2\2\u0082|\3\2\2\2\u0083\36\3\2\2"+
		"\2\u0084\u0086\t\4\2\2\u0085\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0085"+
		"\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u008a\b\20\4\2"+
		"\u008a \3\2\2\2\u008b\u008c\7=\2\2\u008c\u008e\13\2\2\2\u008d\u008b\3"+
		"\2\2\2\u008e\u0091\3\2\2\2\u008f\u008d\3\2\2\2\u008f\u0090\3\2\2\2\u0090"+
		"\u0093\3\2\2\2\u0091\u008f\3\2\2\2\u0092\u0094\7\17\2\2\u0093\u0092\3"+
		"\2\2\2\u0093\u0094\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0096\7\f\2\2\u0096"+
		"\"\3\2\2\2\20\2FHTYckry\u0080\u0082\u0087\u008f\u0093\5\3\n\2\3\r\3\3"+
		"\20\4";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}