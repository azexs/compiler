// Generated from compiler.g4 by ANTLR 4.7.1

    package parser;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class compilerLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, BLANK=9, 
		COMMENT=10, MINUS=11, PIDENTIFIER=12, NUM=13, READ=14, WRITE=15, ASSIGN=16, 
		PLUS=17, TIMES=18, DIV=19, MOD=20, FOR=21, EQ=22, NEQ=23, LE=24, GE=25, 
		LEQ=26, GEQ=27, IF=28, THEN=29, ELSE=30, ENDIF=31, WHILE=32, DO=33, REPEAT=34, 
		UNTIL=35, ENDWHILE=36, ENDDO=37, FROM=38, TO=39, DOWNTO=40, ENDFOR=41;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "BLANK", 
		"COMMENT", "MINUS", "PIDENTIFIER", "NUM", "READ", "WRITE", "ASSIGN", "PLUS", 
		"TIMES", "DIV", "MOD", "FOR", "EQ", "NEQ", "LE", "GE", "LEQ", "GEQ", "IF", 
		"THEN", "ELSE", "ENDIF", "WHILE", "DO", "REPEAT", "UNTIL", "ENDWHILE", 
		"ENDDO", "FROM", "TO", "DOWNTO", "ENDFOR"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'DECLARE'", "'BEGIN'", "'END'", "','", "'('", "':'", "')'", "';'", 
		null, null, null, null, null, "'READ'", "'WRITE'", "':='", "'+'", "'*'", 
		"'/'", "'%'", "'FOR'", "'='", "'!='", "'<'", "'>'", "'<='", "'>='", "'IF'", 
		"'THEN'", "'ELSE'", "'ENDIF'", "'WHILE'", "'DO'", "'REPEAT'", "'UNTIL'", 
		"'ENDWHILE'", "'ENDDO'", "'FROM'", "'TO'", "'DOWNTO'", "'ENDFOR'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, "BLANK", "COMMENT", 
		"MINUS", "PIDENTIFIER", "NUM", "READ", "WRITE", "ASSIGN", "PLUS", "TIMES", 
		"DIV", "MOD", "FOR", "EQ", "NEQ", "LE", "GE", "LEQ", "GEQ", "IF", "THEN", 
		"ELSE", "ENDIF", "WHILE", "DO", "REPEAT", "UNTIL", "ENDWHILE", "ENDDO", 
		"FROM", "TO", "DOWNTO", "ENDFOR"
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


	public compilerLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "compiler.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2+\u0103\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3"+
		"\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\7\13x\n\13"+
		"\f\13\16\13{\13\13\3\13\3\13\3\13\3\13\3\f\3\f\3\r\6\r\u0084\n\r\r\r\16"+
		"\r\u0085\3\16\6\16\u0089\n\16\r\16\16\16\u008a\3\17\3\17\3\17\3\17\3\17"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\23\3\23\3\24"+
		"\3\24\3\25\3\25\3\26\3\26\3\26\3\26\3\27\3\27\3\30\3\30\3\30\3\31\3\31"+
		"\3\32\3\32\3\33\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\36"+
		"\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3 \3!\3!\3!\3!\3!\3"+
		"!\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3#\3$\3$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3"+
		"%\3%\3%\3%\3&\3&\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3)\3)\3)\3)"+
		"\3)\3)\3)\3*\3*\3*\3*\3*\3*\3*\3y\2+\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21"+
		"\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30"+
		"/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+\3\2\5\5"+
		"\2\13\f\17\17\"\"\4\2aac|\3\2\62;\2\u0105\2\3\3\2\2\2\2\5\3\2\2\2\2\7"+
		"\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2"+
		"\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2"+
		"\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2"+
		"\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2"+
		"\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2"+
		"\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M"+
		"\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\3U\3\2\2\2\5]\3\2\2\2\7c\3\2"+
		"\2\2\tg\3\2\2\2\13i\3\2\2\2\rk\3\2\2\2\17m\3\2\2\2\21o\3\2\2\2\23q\3\2"+
		"\2\2\25u\3\2\2\2\27\u0080\3\2\2\2\31\u0083\3\2\2\2\33\u0088\3\2\2\2\35"+
		"\u008c\3\2\2\2\37\u0091\3\2\2\2!\u0097\3\2\2\2#\u009a\3\2\2\2%\u009c\3"+
		"\2\2\2\'\u009e\3\2\2\2)\u00a0\3\2\2\2+\u00a2\3\2\2\2-\u00a6\3\2\2\2/\u00a8"+
		"\3\2\2\2\61\u00ab\3\2\2\2\63\u00ad\3\2\2\2\65\u00af\3\2\2\2\67\u00b2\3"+
		"\2\2\29\u00b5\3\2\2\2;\u00b8\3\2\2\2=\u00bd\3\2\2\2?\u00c2\3\2\2\2A\u00c8"+
		"\3\2\2\2C\u00ce\3\2\2\2E\u00d1\3\2\2\2G\u00d8\3\2\2\2I\u00de\3\2\2\2K"+
		"\u00e7\3\2\2\2M\u00ed\3\2\2\2O\u00f2\3\2\2\2Q\u00f5\3\2\2\2S\u00fc\3\2"+
		"\2\2UV\7F\2\2VW\7G\2\2WX\7E\2\2XY\7N\2\2YZ\7C\2\2Z[\7T\2\2[\\\7G\2\2\\"+
		"\4\3\2\2\2]^\7D\2\2^_\7G\2\2_`\7I\2\2`a\7K\2\2ab\7P\2\2b\6\3\2\2\2cd\7"+
		"G\2\2de\7P\2\2ef\7F\2\2f\b\3\2\2\2gh\7.\2\2h\n\3\2\2\2ij\7*\2\2j\f\3\2"+
		"\2\2kl\7<\2\2l\16\3\2\2\2mn\7+\2\2n\20\3\2\2\2op\7=\2\2p\22\3\2\2\2qr"+
		"\t\2\2\2rs\3\2\2\2st\b\n\2\2t\24\3\2\2\2uy\7]\2\2vx\13\2\2\2wv\3\2\2\2"+
		"x{\3\2\2\2yz\3\2\2\2yw\3\2\2\2z|\3\2\2\2{y\3\2\2\2|}\7_\2\2}~\3\2\2\2"+
		"~\177\b\13\2\2\177\26\3\2\2\2\u0080\u0081\7/\2\2\u0081\30\3\2\2\2\u0082"+
		"\u0084\t\3\2\2\u0083\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0083\3\2"+
		"\2\2\u0085\u0086\3\2\2\2\u0086\32\3\2\2\2\u0087\u0089\t\4\2\2\u0088\u0087"+
		"\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b"+
		"\34\3\2\2\2\u008c\u008d\7T\2\2\u008d\u008e\7G\2\2\u008e\u008f\7C\2\2\u008f"+
		"\u0090\7F\2\2\u0090\36\3\2\2\2\u0091\u0092\7Y\2\2\u0092\u0093\7T\2\2\u0093"+
		"\u0094\7K\2\2\u0094\u0095\7V\2\2\u0095\u0096\7G\2\2\u0096 \3\2\2\2\u0097"+
		"\u0098\7<\2\2\u0098\u0099\7?\2\2\u0099\"\3\2\2\2\u009a\u009b\7-\2\2\u009b"+
		"$\3\2\2\2\u009c\u009d\7,\2\2\u009d&\3\2\2\2\u009e\u009f\7\61\2\2\u009f"+
		"(\3\2\2\2\u00a0\u00a1\7\'\2\2\u00a1*\3\2\2\2\u00a2\u00a3\7H\2\2\u00a3"+
		"\u00a4\7Q\2\2\u00a4\u00a5\7T\2\2\u00a5,\3\2\2\2\u00a6\u00a7\7?\2\2\u00a7"+
		".\3\2\2\2\u00a8\u00a9\7#\2\2\u00a9\u00aa\7?\2\2\u00aa\60\3\2\2\2\u00ab"+
		"\u00ac\7>\2\2\u00ac\62\3\2\2\2\u00ad\u00ae\7@\2\2\u00ae\64\3\2\2\2\u00af"+
		"\u00b0\7>\2\2\u00b0\u00b1\7?\2\2\u00b1\66\3\2\2\2\u00b2\u00b3\7@\2\2\u00b3"+
		"\u00b4\7?\2\2\u00b48\3\2\2\2\u00b5\u00b6\7K\2\2\u00b6\u00b7\7H\2\2\u00b7"+
		":\3\2\2\2\u00b8\u00b9\7V\2\2\u00b9\u00ba\7J\2\2\u00ba\u00bb\7G\2\2\u00bb"+
		"\u00bc\7P\2\2\u00bc<\3\2\2\2\u00bd\u00be\7G\2\2\u00be\u00bf\7N\2\2\u00bf"+
		"\u00c0\7U\2\2\u00c0\u00c1\7G\2\2\u00c1>\3\2\2\2\u00c2\u00c3\7G\2\2\u00c3"+
		"\u00c4\7P\2\2\u00c4\u00c5\7F\2\2\u00c5\u00c6\7K\2\2\u00c6\u00c7\7H\2\2"+
		"\u00c7@\3\2\2\2\u00c8\u00c9\7Y\2\2\u00c9\u00ca\7J\2\2\u00ca\u00cb\7K\2"+
		"\2\u00cb\u00cc\7N\2\2\u00cc\u00cd\7G\2\2\u00cdB\3\2\2\2\u00ce\u00cf\7"+
		"F\2\2\u00cf\u00d0\7Q\2\2\u00d0D\3\2\2\2\u00d1\u00d2\7T\2\2\u00d2\u00d3"+
		"\7G\2\2\u00d3\u00d4\7R\2\2\u00d4\u00d5\7G\2\2\u00d5\u00d6\7C\2\2\u00d6"+
		"\u00d7\7V\2\2\u00d7F\3\2\2\2\u00d8\u00d9\7W\2\2\u00d9\u00da\7P\2\2\u00da"+
		"\u00db\7V\2\2\u00db\u00dc\7K\2\2\u00dc\u00dd\7N\2\2\u00ddH\3\2\2\2\u00de"+
		"\u00df\7G\2\2\u00df\u00e0\7P\2\2\u00e0\u00e1\7F\2\2\u00e1\u00e2\7Y\2\2"+
		"\u00e2\u00e3\7J\2\2\u00e3\u00e4\7K\2\2\u00e4\u00e5\7N\2\2\u00e5\u00e6"+
		"\7G\2\2\u00e6J\3\2\2\2\u00e7\u00e8\7G\2\2\u00e8\u00e9\7P\2\2\u00e9\u00ea"+
		"\7F\2\2\u00ea\u00eb\7F\2\2\u00eb\u00ec\7Q\2\2\u00ecL\3\2\2\2\u00ed\u00ee"+
		"\7H\2\2\u00ee\u00ef\7T\2\2\u00ef\u00f0\7Q\2\2\u00f0\u00f1\7O\2\2\u00f1"+
		"N\3\2\2\2\u00f2\u00f3\7V\2\2\u00f3\u00f4\7Q\2\2\u00f4P\3\2\2\2\u00f5\u00f6"+
		"\7F\2\2\u00f6\u00f7\7Q\2\2\u00f7\u00f8\7Y\2\2\u00f8\u00f9\7P\2\2\u00f9"+
		"\u00fa\7V\2\2\u00fa\u00fb\7Q\2\2\u00fbR\3\2\2\2\u00fc\u00fd\7G\2\2\u00fd"+
		"\u00fe\7P\2\2\u00fe\u00ff\7F\2\2\u00ff\u0100\7H\2\2\u0100\u0101\7Q\2\2"+
		"\u0101\u0102\7T\2\2\u0102T\3\2\2\2\6\2y\u0085\u008a\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}