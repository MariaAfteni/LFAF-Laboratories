import java.util.ArrayList;

public class Lexer {

    public String[] Tokenize(String source){
        return source.split("(?!^)");
    }

    public ArrayList<String> LexicalAnalysis(String[] tokens){
        enum TokenCategory {
            LETTER,
            NUMBER,
            PUNCTUATION,
            SPACE,
            BRACKET,
            UNRECOGNIZED
        }

        ArrayList<String> lexer = new ArrayList<>();
        String type;
        int id = 1;
        for (String token : tokens) {
            char c = token.toCharArray()[0];
            TokenCategory category;

            switch (Character.getType(c)) {
                case Character.UPPERCASE_LETTER, Character.LOWERCASE_LETTER ->
                        category = TokenCategory.LETTER;
                case Character.DECIMAL_DIGIT_NUMBER ->
                        category = TokenCategory.NUMBER;
                case Character.CONNECTOR_PUNCTUATION, Character.DASH_PUNCTUATION, Character.START_PUNCTUATION,
                        Character.END_PUNCTUATION, Character.INITIAL_QUOTE_PUNCTUATION, Character.FINAL_QUOTE_PUNCTUATION,
                        Character.OTHER_PUNCTUATION ->
                        category = TokenCategory.PUNCTUATION;
                case Character.PARAGRAPH_SEPARATOR ->
                        category = TokenCategory.SPACE;
                default ->
                        category = TokenCategory.UNRECOGNIZED;
            }

            if (c == '{' || c == '}' || c == '(' || c == ')' || c == '[' || c == ']' || c == '<' || c == '>') {
                category = TokenCategory.BRACKET;
            }

            if (category == TokenCategory.SPACE || c == ' ') {
                continue;
            } else {
                lexer.add(token + "-" + category );

            }

            id++;
        }

        return lexer;
    }



}
