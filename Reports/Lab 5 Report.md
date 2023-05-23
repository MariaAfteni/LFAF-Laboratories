#  Parser & Building an Abstract Syntax Tree

### Course: Formal Languages & Finite Automata
### Author: Afteni Maria

----

## Theory:
&ensp;&ensp;&ensp;A parser is a crucial component in compilers or interpreters that examines the structure of source code
based on a defined grammar and generates a parse tree or abstract syntax tree (AST) as an intermediate representation. 
By utilizing syntactic rules, the parser analyzes the sequence of tokens produced by the lexer to determine if the code
adheres to the grammar. The resulting parse tree or AST represents the hierarchical structure of the code, capturing the 
relationships between different language constructs such as expressions, statements, and declarations. It provides a 
simplified and higher-level representation of the code, excluding unnecessary details like punctuation or whitespace, 
and serves as a foundation for further analysis, optimization, or code generation.

## Objectives:
1. Get familiar with parsing, what it is and how it can be programmed [1].
2. Get familiar with the concept of AST [2].
3. In addition to what has been done in the 3rd lab work do the following:
   1. In case you didn't have a type that denotes the possible types of tokens you need to:
      1. Have a type __*TokenType*__ (like an enum) that can be used in the lexical analysis to categorize the tokens.
      2. Please use regular expressions to identify the type of the token.
   2. Implement the necessary data structures for an AST that could be used for the text you have processed in the 3rd lab work.
   3. Implement a simple parser program that could extract the syntactic information from the input text.

## Implementation description:
&ensp;&ensp;&ensp; Firstly, I reformatted the Lexer implementation, by adding an enum for token categories.

       public ArrayList<String> LexicalAnalysis(String[] tokens){
        enum TokenCategory {
            LETTER,
            NUMBER,
            PUNCTUATION,
            SPACE,
            BRACKET,
            UNRECOGNIZED
        }

&ensp;&ensp;&ensp; Then I started to implement the parser. The class `Parser` has the minimum attributes of a parser.
The Parser class contains a method called Parse, which takes an ArrayList of tokens as input.

    public void Parse(ArrayList<String> tokens) {
        int brackets = 0;
        boolean parse = false;
        for(String t : tokens){
            String[] tok = t.split("-");
            if ((!((t.toCharArray()[0] >= 'a' && t.toCharArray()[0] <= 'z')
                    || (t.toCharArray()[0] >= 'A' && t.toCharArray()[0] <= 'Z'))) && (tok[1].equals("LETTER"))){
                System.out.println("Invalid expression: \n   " + tok[0] + " - not english character.");
            }
            else if (tok[1].equals("BRACKET")){
                brackets ++;
            }
            else {
                parse = true;
            }


        }
        if(!(brackets % 2 == 0)){
            System.out.println("Invalid expression: \n  Bracket missing.");
            parse = false;
        }
        if (parse){
            System.out.println("Parsing completed successfully.");
            tree.buildAST(tokens);
        }
        else {
            System.out.println("\n Impossible to create AST");
        }

    }

&ensp;&ensp;&ensp; The  method performs lexical analysis and syntax validation on the tokens. It checks each token in 
the input ArrayList. The code then applies certain validation rules. For example, if a token is a non-English 
character and has the category "LETTER," it prints an error message indicating that the token is not an English character.
At the end, it checks the `parse` flag. If the parse flag is false, indicating parsing errors, it prints a message 
indicating that it is impossible to create the AST.
The Parser class relies on an AST object to build and store the Abstract Syntax Tree.

     public void buildAST(ArrayList<String> tokens){
        ArrayList<String> Nodes = new ArrayList<String>();
        String[] to = tokens.get(0).split("-");
        Nodes.add(to[0]);

        for (int i = 1; i < tokens.size(); i++) {
            String[] tok = tokens.get(i).split("-");
            if (!(tok[1].equals("PUNCTUATION") || tok[1].equals("Bracket"))) {
                if (i < tokens.size() - 1) {
                    Nodes.add(Nodes.get(i - 1) + tok[0]);
                }
            } else {
                Nodes.add(tok[0]);
            }
        }
        System.out.println("\n Array List Representation of AST: \n" + Nodes);
    }

&ensp;&ensp;&ensp;The `buildAST` method takes an ArrayList of tokens as input and constructs an abstract syntax tree (AST)
using an ArrayList called `Nodes`.  For each token, it splits it to extract the value and token category. If the token 
category is neither "PUNCTUATION" nor "Bracket", it prints the value and checks if it is not the last token in the list. 
If it's not the last token, it concatenates the value with the previous element in the `Nodes` ArrayList and adds the result
to the `Nodes` ArrayList. If the token category is "PUNCTUATION" or "Bracket", it prints the corresponding pattern and adds 
the token value to the `Nodes` ArrayList as a separate element.

## Conclusions / Results
To check the implementation I used 2 strings.

#### First check:

    String source = "Example ы23021 -,- <3";  

Output:

      Invalid expression:
        ы - not english character.
      Invalid expression:
        Bracket missing.
      
      Impossible to create AST

#### Second check:
      
      String source = "Example 23021 -,- 3"; 

Output:

      Parsing completed successfully.

      Array List Representation of AST:
      [E, Ex, Exa, Exam, Examp, Exampl, Example, Example2, Example23, Example230, Example2302, Example23021, Example23021, ,, ,]





 