import java.util.ArrayList;

public class Lexer {

    public String[] Tokenize(String source){
        return source.split("(?!^)");
    }

    public ArrayList<String> LexicalAnalysis(String[] tokens){
        ArrayList<String> lexer = new ArrayList<>();
        String type;
        int id = 1;
        for( String token: tokens){

            char c = token.toCharArray()[0];
            switch (Character.getType(c)) {
                case Character.UPPERCASE_LETTER, Character.LOWERCASE_LETTER ->
                        type = "Letter";
                case Character.DECIMAL_DIGIT_NUMBER -> type = "Number";
                case Character.CONNECTOR_PUNCTUATION, Character.DASH_PUNCTUATION, Character.START_PUNCTUATION,
                        Character.END_PUNCTUATION, Character.INITIAL_QUOTE_PUNCTUATION, Character.FINAL_QUOTE_PUNCTUATION, Character.OTHER_PUNCTUATION ->
                        type = "Punctuation";
                case Character.PARAGRAPH_SEPARATOR ->
                        type = "Space";
                default -> type = "Unrecognized";
            }
            if (c == '{' || c == '}' || c == '(' || c == ')' || c == '[' || c == ']' || c == '<' || c == '>' ){
                type = "Bracket";
            }

            if (type.equals("Space") || c == ' '){
                continue;
            }
            else {
                lexer.add("Token " + "'" + token + "' -> Category: " + type + "; Id: " + id + ";");
            }
            id++;
        }
        return lexer;
    }

}