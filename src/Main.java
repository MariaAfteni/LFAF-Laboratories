import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
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
        String[] Production = new String[]{"S->aB", "S->AC", "A->a", "A->ASC", "A->BC", "A->aD", "B->b", "B->bS",
                "C->ε", "C->BA", "E->aB", "D->abC"};
//
//        --- Lab 1
//        Grammar grammar = new Grammar(Vn, Vt, prodKey, prodVal, start);
//
//        ArrayList <String> generatedWords = grammar.generateWords(5);
//        System.out.println(generatedWords);
//
//        FiniteAutomaton finiteAutomaton = grammar.FiniteAutomaton();
//        System.out.println("\nVerification of the strings");
//        for(int i=0; i<5; i++){
//            finiteAutomaton.wordIsValid(generatedWords.get(i));
//        }
//
//        --- Lab 2
//        System.out.println(grammar.CheckGrammarType(prodVal, prodKey, prodKey1));
//
//        System.out.println("\nProduction of converted FA to grammar: \n P = {");
//        FiniteAutomaton FA = new FiniteAutomaton(start, 'F');
//        ArrayList<String> FAProd;
//        FAProd = FA.FAtoGrammar(prodKeyFA, prodValFA);
//        for(int i = 0; i < prodKeyFA.length; i++){
//            System.out.println(FAProd.get(i));
//        }
//        System.out.println("}");
//
//        System.out.println("\nFinite Automata Type:");
//        FA.isNFA(Q, alphabet);
//
//        System.out.println("\nProduction of converted NFA to DFA: \n P = {");
//        ArrayList<String> DFA = FA.NFAToDFA(prodKeyFA, prodValFA );
//        for(int i = 0; i < prodKeyFA.length; i++){
//            System.out.println(DFA.get(i));
//        }
//        System.out.println("}");
//
        String source = "Example 23021 -,- 3";
        Lexer lexer = new Lexer();

//      --- Lab 3
//        System.out.println("\nLexical Analysis");
        String[] tokens = lexer.Tokenize(source);
        ArrayList<String> result = lexer.LexicalAnalysis(tokens);
//        for (String s : result) {
//            System.out.println(s);
//        }

//        --- Lab 4
//        NormalForm n1 = new NormalForm();
//        ArrayList<String> noEpsilon = n1.noEpsilon();
//        ArrayList<String> noRenaming = n1.noRenaming(noEpsilon);
//        ArrayList<String> noInaccessible = n1.noInaccessible(noRenaming);
//        ArrayList<String> noNonproductive = n1.noNonproductive(noInaccessible);
//        ArrayList<String> normalForm = n1.normalForm(noNonproductive);
//
//
//        if(Production.length == noEpsilon.size()){
//            System.out.println("Production doesn't contain epsilon transitions");
//        }else{
//            System.out.println("Final production with no epsilon transitions:");
//            for (String s : noEpsilon) {
//                System.out.println(s);
//            }
//        }
//        Thread.sleep(5000);
//
//        System.out.println("-----------------------------------------------------");
//        if(Production.length == noRenaming.size()){
//            System.out.println("Production doesn't contain renaming transitions");
//        }else{
//            System.out.println("Final production with no renaming transitions:");
//            for (String s : noRenaming) {
//                System.out.println(s);
//            }
//        }
//        Thread.sleep(5000);
//
//        System.out.println("-----------------------------------------------------");
//        if(Production.length == noInaccessible.size()){
//            System.out.println("Production doesn't contain inaccessible symbols");
//        }else{
//            System.out.println("Final production with no inaccessible symbols:");
//            for (String s : noInaccessible) {
//                System.out.println(s);
//            }
//        }
//        Thread.sleep(5000);
//
//        System.out.println("-----------------------------------------------------");
//        if(Production.length == noNonproductive.size()){
//            System.out.println("Production doesn't contain nonproductive transitions");
//        }else{
//            System.out.println("Final production with no nonproductive transitions:");
//            for (String s : noNonproductive) {
//                System.out.println(s);
//            }
//        }
//        Thread.sleep(5000);
//
//        System.out.println("-----------------------------------------------------");
//        System.out.println("Chomsky Normal Form Production");
//        for (String s : normalForm) {
//            System.out.println(s);
//        }

//        --- Lab 5
        Parser parser = new Parser();
        parser.Parse(result);
    }
}

