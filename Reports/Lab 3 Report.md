#  Lexer & Scanner

### Course: Formal Languages & Finite Automata
### Author: Afteni Maria

----

## Theory:
&ensp;&ensp;&ensp;Lexical analysis converts a sequence of characters into a sequence of tokens. It removes extra whitespace and comments 
written in the source code.The lexical analyzer consists of two consecutive processes that incorporate scanning and 
lexical analysis. Lexical analysis stage performs the tokenization on the output given by the scanner and subsequently produces tokens.



## Objectives:
1. Understand what lexical analysis is.
2. Get familiar with the inner workings of a lexer/scanner/tokenizer.
3. Implement a sample lexer and show how it works.


## Implementation description:
&ensp;&ensp;&ensp;To implement a lexer, I used the `Tokenize` method that takes a string as input and returns a String array 
that contains each token of the input string. To do this I used the `split` method. The regex pattern used is "(?!^)",
which splits the string at every position except for the beginning. This means that the split method will split the 
source string into an array of substrings, each containing a single character from the original source string.

    public String[] Tokenize(String source){
        return source.split("(?!^)");
    }

&ensp;&ensp;&ensp;The resulting tokens are the input for the second method of the `Lexer` class. The `LexicalAnalysis` method checks the 
type of each token and gives it and ID. It uses a switch statement to identify the type of character `c`, where 
`char c = token.toCharArray()[0]` and then assigns a string type that represents the identified type. The 
`Character.getType(c)` method returns an integer that represents the general category of the character `c`. 
Then the switch statement uses this integer value to determine which case to execute. The token can be a letter, a number,
a bracket, a punctuation or a space.

    
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

&ensp;&ensp;&ensp;If the character is a space, then the method skips the current iteration, and the token is not added to the final ArrayList.

    if (type.equals("Space") || c == ' '){
        continue;
    }

&ensp;&ensp;&ensp;After each token gets its type, they are added to a final `lexer` ArrayList with their category and ID.

    lexer.add("Token " + "'" + token + "' -> Category: " + type + "; Id: " + id + ";");

## Conclusions / Results

&ensp;&ensp;&ensp;The lexer can take any string as input and do a lexical analysis of it. Let's do an example, where the source string is 
`String source = "Example 23021 -,- <3";`

After compilation the program output is:

    Lexical Analysis
    Token 'E' -> Category: Letter; Id: 1;
    Token 'x' -> Category: Letter; Id: 2;
    Token 'a' -> Category: Letter; Id: 3;
    Token 'm' -> Category: Letter; Id: 4;
    Token 'p' -> Category: Letter; Id: 5;
    Token 'l' -> Category: Letter; Id: 6;
    Token 'e' -> Category: Letter; Id: 7;
    Token '2' -> Category: Number; Id: 8;
    Token '3' -> Category: Number; Id: 9;
    Token '0' -> Category: Number; Id: 10;
    Token '2' -> Category: Number; Id: 11;
    Token '1' -> Category: Number; Id: 12;
    Token '-' -> Category: Punctuation; Id: 13;
    Token ',' -> Category: Punctuation; Id: 14;
    Token '-' -> Category: Punctuation; Id: 15;
    Token '<' -> Category: Bracket; Id: 16;
    Token '3' -> Category: Number; Id: 17;

&ensp;&ensp;&ensp; During this laboratory work, I got the chance to implement a Lexer, which scans the input string, 
splits it into tokens and categorizes each token afterwords. It is a simple class, but the implementation helped me to 
understand the process of lexical analysis. The program ca be improved, by adding the possibility to detect keywords and 
function, but for now it wasn't the main goal.
