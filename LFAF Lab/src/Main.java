import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        char[] Vn = {'S', 'P', 'Q'};
        char[] Vt = {'a', 'b', 'c', 'd', 'e', 'f'};
        char[] prodKey = {'S', 'S', 'P', 'P', 'P', 'P', 'Q', 'Q', 'Q'};
        String[] prodKey1 = {"S", "S", "P", "P", "P", "P", "Q", "Q", "Q"};
        String[] prodVal = {"aP", "bQ", "bP", "cP", "dQ", "e", "eQ", "fQ", "a"};
        char start = 'S';
        String[] Q = {"q0", "q1", "q2", "q3"};
        char[] alphabet = {'a', 'c', 'b'};
        String[] prodKeyFA = {"q0", "q0", "q1", "q1", "q2", "q3"};
        String[] prodValFA = {"aq0", "aq1", "cq1", "bq2", "bq3", "aq1"};


        Grammar grammar = new Grammar(Vn, Vt, prodKey, prodVal, start);

        ArrayList <String> generatedWords = grammar.generateWords(5);
        System.out.println(generatedWords);

        FiniteAutomaton finiteAutomaton = grammar.FiniteAutomaton();
        System.out.println("\nVerification of the strings");
        for(int i=0; i<5; i++){
            finiteAutomaton.wordIsValid(generatedWords.get(i));
        }

        System.out.println(grammar.CheckGrammarType(prodVal, prodKey, prodKey1));

        System.out.println("\nProduction of converted FA to grammar: \n P = {");
        FiniteAutomaton FA = new FiniteAutomaton(start, 'F');
        ArrayList<String> FAProd;
        FAProd = FA.FAtoGrammar(prodKeyFA, prodValFA);
        for(int i = 0; i < prodKeyFA.length; i++){
            System.out.println(FAProd.get(i));
        }
        System.out.println("}");

        System.out.println("\nFinite Automata Type:");
        FA.isNFA(Q, alphabet);

        System.out.println("\nProduction of converted NFA to DFA: \n P = {");
        ArrayList<String> DFA = FA.NFAToDFA(prodKeyFA, prodValFA );
        for(int i = 0; i < prodKeyFA.length; i++){
            System.out.println(DFA.get(i));
        }
        System.out.println("}");

    }



}
