import java.util.ArrayList;
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
}
