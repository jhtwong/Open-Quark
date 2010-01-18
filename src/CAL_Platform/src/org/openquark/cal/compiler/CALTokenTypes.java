// $ANTLR 2.7.6 (2005-12-22): "CAL.g" -> "CALParser.java"$

// Package declaration
package org.openquark.cal.compiler;

public interface CALTokenTypes {
	int EOF = 1;
	int NULL_TREE_LOOKAHEAD = 3;
	int CALDOC_OPEN = 4;
	int CALDOC_CLOSE = 5;
	int CONS_ID = 6;
	int VAR_ID = 7;
	int ORDINAL_FIELD_NAME = 8;
	int EQUALS = 9;
	int COMMA = 10;
	int DOT = 11;
	int CALDOC_NEWLINE = 12;
	int CALDOC_WS = 13;
	int CALDOC_NEWLINE_WITH_LEADING_ASTERISK = 14;
	int CALDOC_NEWLINE_WITH_OPTIONAL_LEADING_ASTERISK_SPEC = 15;
	int CALDOC_NEWLINE_WITH_OPTIONAL_LEADING_ASTERISK = 16;
	int CALDOC_IGNORED_DOC_WS = 17;
	int CALDOC_SEE_TAG_FUNCTION_CONTEXT = 18;
	int CALDOC_SEE_TAG_MODULE_CONTEXT = 19;
	int CALDOC_SEE_TAG_DATACONS_CONTEXT = 20;
	int CALDOC_SEE_TAG_TYPECONS_CONTEXT = 21;
	int CALDOC_SEE_TAG_TYPECLASS_CONTEXT = 22;
	int CALDOC_SEE_TAG_CONTEXT = 23;
	int CALDOC_SEE_TAG_CONS_ID = 24;
	int CALDOC_SEE_TAG_VAR_ID = 25;
	int CALDOC_SEE_TAG_QUOTE = 26;
	int CALDOC_SEE_TAG_EQUALS = 27;
	int CALDOC_SEE_TAG_COMMA = 28;
	int CALDOC_SEE_TAG_DOT = 29;
	int CALDOC_AUTHOR_TAG = 30;
	int CALDOC_DEPRECATED_TAG = 31;
	int CALDOC_RETURN_TAG = 32;
	int CALDOC_VERSION_TAG = 33;
	int CALDOC_ARG_TAG = 34;
	int CALDOC_ARG_TAG_VAR_ID = 35;
	int CALDOC_ARG_TAG_ORDINAL_FIELD_NAME = 36;
	int CALDOC_SEE_TAG = 37;
	int CALDOC_OPEN_INLINE_TAG = 38;
	int CALDOC_CLOSE_INLINE_TAG = 39;
	int CALDOC_INLINE_SUMMARY_TAG = 40;
	int CALDOC_INLINE_EM_TAG = 41;
	int CALDOC_INLINE_STRONG_TAG = 42;
	int CALDOC_INLINE_SUP_TAG = 43;
	int CALDOC_INLINE_SUB_TAG = 44;
	int CALDOC_INLINE_UNORDERED_LIST_TAG = 45;
	int CALDOC_INLINE_ORDERED_LIST_TAG = 46;
	int CALDOC_INLINE_ITEM_TAG = 47;
	int CALDOC_INLINE_CODE_TAG = 48;
	int CALDOC_INLINE_URL_TAG = 49;
	int CALDOC_INLINE_LINK_TAG = 50;
	int CALDOC_INLINE_TAG = 51;
	int CALDOC_INLINE_UNKNOWN_TAG = 52;
	int CALDOC_UNKNOWN_TAG = 53;
	int CALDOC_SEE_TAG_UNKNOWN_CONTEXT = 54;
	int CALDOC_SEE_TAG_UNKNOWN_REFERENCE = 55;
	int CALDOC_REGULAR_TEXT_LINE = 56;
	int CALDOC_BLANK_TEXT_LINE = 57;
	int CALDOC_TEXT_LINE = 58;
	int TOP_LEVEL_FUNCTION_DEFN = 59;
	int FUNCTION_PARAM_LIST = 60;
	int STRICT_PARAM = 61;
	int LAZY_PARAM = 62;
	int LAMBDA_DEFN = 63;
	int APPLICATION = 64;
	int ALT_LIST = 65;
	int ALT = 66;
	int LET_DEFN_LIST = 67;
	int LET_DEFN = 68;
	int LET_PATTERN_MATCH_DECL = 69;
	int TOP_LEVEL_TYPE_DECLARATION = 70;
	int LET_DEFN_TYPE_DECLARATION = 71;
	int TYPE_DECLARATION = 72;
	int FUNCTION_TYPE_CONSTRUCTOR = 73;
	int TYPE_APPLICATION = 74;
	int TUPLE_CONSTRUCTOR = 75;
	int LIST_CONSTRUCTOR = 76;
	int DATA_DECLARATION = 77;
	int TYPE_CONS_PARAM_LIST = 78;
	int DATA_CONSTRUCTOR_DEFN_LIST = 79;
	int DATA_CONSTRUCTOR_DEFN = 80;
	int DATA_CONSTRUCTOR_ARG_LIST = 81;
	int DATA_CONSTRUCTOR_NAMED_ARG = 82;
	int TYPE_CONTEXT_LIST = 83;
	int TYPE_CONTEXT_SINGLETON = 84;
	int TYPE_CONTEXT_NOTHING = 85;
	int CLASS_CONTEXT_LIST = 86;
	int CLASS_CONTEXT_SINGLETON = 87;
	int CLASS_CONTEXT_NOTHING = 88;
	int CLASS_CONTEXT = 89;
	int LACKS_FIELD_CONTEXT = 90;
	int FRIEND_DECLARATION_LIST = 91;
	int IMPORT_DECLARATION_LIST = 92;
	int OUTER_DEFN_LIST = 93;
	int ACCESS_MODIFIER = 94;
	int QUALIFIED_VAR = 95;
	int QUALIFIED_CONS = 96;
	int HIERARCHICAL_MODULE_NAME = 97;
	int HIERARCHICAL_MODULE_NAME_EMPTY_QUALIFIER = 98;
	int COMPILATION_UNIT = 99;
	int MODULE_DEFN = 100;
	int PATTERN_CONSTRUCTOR = 101;
	int UNIT_TYPE_CONSTRUCTOR = 102;
	int LIST_TYPE_CONSTRUCTOR = 103;
	int TUPLE_TYPE_CONSTRUCTOR = 104;
	int INT_PATTERN = 105;
	int CHAR_PATTERN = 106;
	int FOREIGN_FUNCTION_DECLARATION = 107;
	int PRIMITIVE_FUNCTION_DECLARATION = 108;
	int FOREIGN_DATA_DECLARATION = 109;
	int TYPE_CLASS_DEFN = 110;
	int CLASS_METHOD_LIST = 111;
	int CLASS_METHOD = 112;
	int INSTANCE_DEFN = 113;
	int INSTANCE_NAME = 114;
	int INSTANCE_METHOD_LIST = 115;
	int INSTANCE_METHOD = 116;
	int UNPARENTHESIZED_TYPE_CONSTRUCTOR = 117;
	int GENERAL_TYPE_CONSTRUCTOR = 118;
	int PATTERN_VAR_LIST = 119;
	int DATA_CONSTRUCTOR_NAME_LIST = 120;
	int DATA_CONSTRUCTOR_NAME_SINGLETON = 121;
	int MAYBE_MINUS_INT_LIST = 122;
	int CHAR_LIST = 123;
	int RECORD_CONSTRUCTOR = 124;
	int BASE_RECORD = 125;
	int FIELD_MODIFICATION_LIST = 126;
	int FIELD_EXTENSION = 127;
	int FIELD_VALUE_UPDATE = 128;
	int RECORD_TYPE_CONSTRUCTOR = 129;
	int RECORD_VAR = 130;
	int FIELD_TYPE_ASSIGNMENT_LIST = 131;
	int FIELD_TYPE_ASSIGNMENT = 132;
	int SELECT_RECORD_FIELD = 133;
	int SELECT_DATA_CONSTRUCTOR_FIELD = 134;
	int RECORD_PATTERN = 135;
	int BASE_RECORD_PATTERN = 136;
	int FIELD_BINDING_VAR_ASSIGNMENT_LIST = 137;
	int FIELD_BINDING_VAR_ASSIGNMENT = 138;
	int TYPE_SIGNATURE = 139;
	int STRICT_ARG = 140;
	int EXPRESSION_TYPE_SIGNATURE = 141;
	int UNARY_MINUS = 142;
	int OPTIONAL_CALDOC_COMMENT = 143;
	int CALDOC_COMMENT = 144;
	int CALDOC_TEXT = 145;
	int CALDOC_DESCRIPTION_BLOCK = 146;
	int CALDOC_TAGGED_BLOCKS = 147;
	int CALDOC_AUTHOR_BLOCK = 148;
	int CALDOC_DEPRECATED_BLOCK = 149;
	int CALDOC_RETURN_BLOCK = 150;
	int CALDOC_VERSION_BLOCK = 151;
	int CALDOC_ARG_BLOCK = 152;
	int CALDOC_SEE_BLOCK = 153;
	int CALDOC_SEE_FUNCTION_BLOCK = 154;
	int CALDOC_SEE_MODULE_BLOCK = 155;
	int CALDOC_SEE_DATACONS_BLOCK = 156;
	int CALDOC_SEE_TYPECONS_BLOCK = 157;
	int CALDOC_SEE_TYPECLASS_BLOCK = 158;
	int CALDOC_SEE_BLOCK_WITHOUT_CONTEXT = 159;
	int CALDOC_CHECKED_MODULE_NAME = 160;
	int CALDOC_UNCHECKED_MODULE_NAME = 161;
	int CALDOC_CHECKED_QUALIFIED_VAR = 162;
	int CALDOC_UNCHECKED_QUALIFIED_VAR = 163;
	int CALDOC_CHECKED_QUALIFIED_CONS = 164;
	int CALDOC_UNCHECKED_QUALIFIED_CONS = 165;
	int CALDOC_TEXT_PREFORMATTED_BLOCK = 166;
	int CALDOC_TEXT_BLOCK_WITHOUT_INLINE_TAGS = 167;
	int CALDOC_TEXT_LINE_BREAK = 168;
	int CALDOC_TEXT_INLINE_BLOCK = 169;
	int CALDOC_TEXT_URL = 170;
	int CALDOC_TEXT_LINK = 171;
	int CALDOC_TEXT_EMPHASIZED_TEXT = 172;
	int CALDOC_TEXT_STRONGLY_EMPHASIZED_TEXT = 173;
	int CALDOC_TEXT_SUPERSCRIPT_TEXT = 174;
	int CALDOC_TEXT_SUBSCRIPT_TEXT = 175;
	int CALDOC_TEXT_SUMMARY = 176;
	int CALDOC_TEXT_CODE_BLOCK = 177;
	int CALDOC_TEXT_ORDERED_LIST = 178;
	int CALDOC_TEXT_UNORDERED_LIST = 179;
	int CALDOC_TEXT_LIST_ITEM = 180;
	int CALDOC_TEXT_LINK_FUNCTION = 181;
	int CALDOC_TEXT_LINK_MODULE = 182;
	int CALDOC_TEXT_LINK_DATACONS = 183;
	int CALDOC_TEXT_LINK_TYPECONS = 184;
	int CALDOC_TEXT_LINK_TYPECLASS = 185;
	int CALDOC_TEXT_LINK_WITHOUT_CONTEXT = 186;
	int VIRTUAL_LET_NONREC = 187;
	int VIRTUAL_LET_REC = 188;
	int VIRTUAL_DATA_CONSTRUCTOR_CASE = 189;
	int VIRTUAL_RECORD_CASE = 190;
	int VIRTUAL_TUPLE_CASE = 191;
	int VIRTUAL_UNIT_DATA_CONSTRUCTOR = 192;
	int LITERAL_module = 193;
	int SEMICOLON = 194;
	int LITERAL_import = 195;
	int LITERAL_using = 196;
	int LITERAL_function = 197;
	int LITERAL_typeConstructor = 198;
	int LITERAL_dataConstructor = 199;
	int LITERAL_typeClass = 200;
	int LITERAL_friend = 201;
	int LITERAL_class = 202;
	int COLONCOLON = 203;
	int IMPLIES = 204;
	int OPEN_PAREN = 205;
	int CLOSE_PAREN = 206;
	int BACKSLASH = 207;
	int RARROW = 208;
	int OPEN_BRACKET = 209;
	int CLOSE_BRACKET = 210;
	int OPEN_BRACE = 211;
	int CLOSE_BRACE = 212;
	int BAR = 213;
	int LITERAL_data = 214;
	int LITERAL_foreign = 215;
	int LITERAL_unsafe = 216;
	int LITERAL_jvm = 217;
	int PLING = 218;
	int LITERAL_deriving = 219;
	int LITERAL_where = 220;
	int LITERAL_default = 221;
	int LITERAL_instance = 222;
	int STRING_LITERAL = 223;
	int LITERAL_primitive = 224;
	int LITERAL_public = 225;
	int LITERAL_private = 226;
	int LITERAL_protected = 227;
	int LITERAL_let = 228;
	int LITERAL_in = 229;
	int LITERAL_if = 230;
	int LITERAL_then = 231;
	int LITERAL_else = 232;
	int LITERAL_case = 233;
	int LITERAL_of = 234;
	int DOLLAR = 235;
	int BARBAR = 236;
	int AMPERSANDAMPERSAND = 237;
	int LESS_THAN = 238;
	int LESS_THAN_OR_EQUALS = 239;
	int EQUALSEQUALS = 240;
	int NOT_EQUALS = 241;
	int GREATER_THAN_OR_EQUALS = 242;
	int GREATER_THAN = 243;
	int COLON = 244;
	int PLUSPLUS = 245;
	int PLUS = 246;
	int MINUS = 247;
	int ASTERISK = 248;
	int SOLIDUS = 249;
	int PERCENT = 250;
	int POUND = 251;
	int BACKQUOTE = 252;
	int COLONEQUALS = 253;
	int UNDERSCORE = 254;
	int CHAR_LITERAL = 255;
	int INTEGER_LITERAL = 256;
	int FLOAT_LITERAL = 257;
	int WS = 258;
	int SL_COMMENT = 259;
	int ML_COMMENT = 260;
	int ESC = 261;
	int HEX_DIGIT = 262;
	int EXPONENT = 263;
}