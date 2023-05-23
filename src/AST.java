import java.util.ArrayList;

public class AST {

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
}