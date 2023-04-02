import java.util.*;

public class FiniteAutomaton
{
    private final ArrayList<Transition> transitions;
    private final char initialState;
    private final char finalState;



    public FiniteAutomaton(char initialState, char finalState){
        transitions = new ArrayList<>();
        this.initialState = initialState;
        this.finalState = finalState;
    }


    public void setTransitions(Transition transition) {
        transitions.add(transition);
    }


    public char getFinalState() {
        return finalState;
    }

    public void wordIsValid(String word){
        boolean valid = false;
        char currentState = initialState;



        for(int i = 0; i < word.length(); i++){
            for(Transition tr : transitions){
                if(tr.currentState() == currentState &&
                        tr.transitionLabel() == word.charAt(i)){

                    currentState = tr.nextState();
                    valid = true;
                    break;
                }
                else{
                    valid = false;
                }
            }
        }

        if(valid && currentState == finalState){
            System.out.println("\n'"+ word +"' - valid");
        }
        else{
            System.out.println("\n'"+ word +"' - not valid");
        }
    }

    public ArrayList<String> FAtoGrammar(String[] prodKey, String[] prodVal) {

        ArrayList<String> prod = new ArrayList<>();

        for(int i = 0; i < prodKey.length; i++) {
            if (i == 0) {
                prod.add(prodKey[i] + " -> " + prodVal[i] );
            }
            else{
                if (!Objects.equals(prodKey[i], prodKey[i - 1] )) {
                    prod.add(prodKey[i] + " -> " + prodVal[i] );
                }
                else{
                    prod.add(  "\r | " + prodVal[i]);
                }
            }
        }
        return prod;
    }

    public void isNFA(String[] states, char[] alphabet){
        if(states.length > alphabet.length){
            System.out.println("Non-deterministic");
        }
        else{
            System.out.println("Deterministic");
        }
    }

    public ArrayList<String> NFAToDFA(String[] prodKey, String[] prodVal){
        ArrayList<String> DFA = new ArrayList<>(); // Create list that will contain final DFA transitions
        String[] Q = new String[20];  // Create string array of the DFA states
        String[] trans = new String[20] ; //Create an array that will store the transition value
        String[] newState = new String[20]; //Create an array to store the new states
        String[] Split;

        for(int i = 0; i < prodKey.length; i++){
            String val = prodVal[i];
            Split = (val.split("q", 0));
            trans[i] = Split[0];
            newState[i] = Arrays.toString(new String[]{"q" + Split[1]});
            if (i != 0) {
                if (prodKey[i].equals(prodKey[i-1]) && trans[i].equals(trans[i - 1])) {
                    newState[i] = newState[i - 1] + newState[i];
                    newState[i-1] = newState[i];
                }
                Q[i] = newState[i - 1];

            }
            else {
                Q[i] = newState[i];
            }
            DFA.add(("(" + Q[i] + "," + trans[i] + ")" + "=" + newState[i]));
            }

        return DFA;
    }
}

