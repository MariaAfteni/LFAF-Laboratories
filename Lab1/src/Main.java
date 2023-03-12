import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        char[] Vn = {'S', 'P', 'Q'};
        char[] Vt = {'a', 'b', 'c', 'd', 'e', 'f'};
        char[] prodKey = {'S', 'S', 'P', 'P', 'P', 'P', 'Q', 'Q', 'Q'};
        String[] prodVal = {"aP", "bQ", "bP", "cP", "dQ", "e", "eQ", "fQ", "a"};
        char start = 'S';

        Grammar grammar = new Grammar(Vn, Vt, prodKey, prodVal, start);

        ArrayList <String> generatedWords = grammar.generateWords(5);
        System.out.println(generatedWords);

        FiniteAutomaton finiteAutomaton = grammar.FiniteAutomaton();
        System.out.println("\nVerification of the strings");
        for(int i=0; i<5; i++){
            finiteAutomaton.wordIsValid(generatedWords.get(i));
        }

    }
}