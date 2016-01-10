package com.pragmatouch.calculator;

public enum KeypadButton 
{
	MC("<",KeypadButtonCategory.MEMORYBUFFER)
	, MR(">",KeypadButtonCategory.MEMORYBUFFER)
	, MS("=",KeypadButtonCategory.MEMORYBUFFER)
	, M_ADD(" and ",KeypadButtonCategory.MEMORYBUFFER)
	, M_REMOVE(" or ",KeypadButtonCategory.MEMORYBUFFER)
	, BACKSPACE(" xor ",KeypadButtonCategory.MEMORYBUFFER)
	, CE("CE",KeypadButtonCategory.CLEAR)
	, C("C",KeypadButtonCategory.CLEAR)
	, ZERO("0",KeypadButtonCategory.NUMBER)
	, ONE("1",KeypadButtonCategory.NUMBER)
	, TWO("2",KeypadButtonCategory.NUMBER)
	, THREE("3",KeypadButtonCategory.NUMBER)
	, FOUR("4",KeypadButtonCategory.NUMBER)
	, FIVE("5",KeypadButtonCategory.NUMBER)
	, SIX("6",KeypadButtonCategory.NUMBER)
	, SEVEN("7",KeypadButtonCategory.NUMBER)
	, EIGHT("8",KeypadButtonCategory.NUMBER)
	, NINE("9",KeypadButtonCategory.NUMBER)
	, PLUS(" + ",KeypadButtonCategory.OPERATOR)
	, MINUS(" - ",KeypadButtonCategory.OPERATOR)
	, MULTIPLY(" * ",KeypadButtonCategory.OPERATOR)
	, DIV(" / ",KeypadButtonCategory.OPERATOR)
	, RECIPROC("1/x",KeypadButtonCategory.OTHER)
	, DECIMAL_SEP(".",KeypadButtonCategory.OTHER)
	, B_OPEN("(",KeypadButtonCategory.OTHER)
	, B_CLOSE(")",KeypadButtonCategory.OTHER)
	, POW("^",KeypadButtonCategory.OPERATOR)
	, CALCULATE("???",KeypadButtonCategory.RESULT)
	, SQRT ("sqrt",KeypadButtonCategory.OTHER)
    , SQUARE ("^2",KeypadButtonCategory.OTHER)
	, GAMMA("!",KeypadButtonCategory.OTHER)
	, PRIME ("fact",KeypadButtonCategory.OTHER)
	, DUMMY("",KeypadButtonCategory.DUMMY);

	CharSequence mText; // Display Text
	KeypadButtonCategory mCategory;
	
	KeypadButton(CharSequence text,KeypadButtonCategory category) {
		mText = text;
		mCategory = category;
	}

	public CharSequence getText() {
		return mText;
	}
}
