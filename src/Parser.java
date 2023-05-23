import java.util.ArrayList;
import java.util.List;

public class Parser {
    AST tree = new AST();
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

    }}