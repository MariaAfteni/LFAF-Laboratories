import java.util.*;

public class Grammar {
    private final ArrayList<Character> nonTerminalVariables = new ArrayList<>();
    private final HashMap<Character, ArrayList<String>> productions = new HashMap<>();
    private final char startSymbol;

    public Grammar(char[] vn, char[] vt, char[] prodKey,
                   String[] prodVal, char startSymbol){

        for (char c : vn) {
            nonTerminalVariables.add(c);
        }

        for (char c : vt) {
            ArrayList<Character> terminalVariables = new ArrayList<>();
            terminalVariables.add(c);
        }

        for(int i = 0; i < prodKey.length; i++){

            if(!productions.containsKey(prodKey[i])){
                productions.put(prodKey[i], new ArrayList<>());
            }
            productions.get(prodKey[i]).add(prodVal[i]);
        }

        this.startSymbol = startSymbol;
    }

    public ArrayList<String> generateWords(int wordsAmount){
        ArrayList<String> result = new ArrayList<>();
        Random random = new Random();

        System.out.println("\nResults of production:");
        while(result.size()  < wordsAmount){
            Stack<Character> stack = new Stack<>();
            StringBuilder stringBuilder = new StringBuilder();

            stack.add(startSymbol);

            while(!stack.isEmpty()){
                char term = stack.pop();

                if(nonTerminalVariables.contains(term)){
                    ArrayList<String> tempArrayRes = productions.get(term);
                    String tempRes = tempArrayRes.get(random.nextInt(tempArrayRes.size()));

                    for(int i = tempRes.length() - 1; i >= 0; i--){
                        stack.add(tempRes.charAt(i));
                    }
                }
                else{
                    stringBuilder.append(term);
                }
            }
            result.add(stringBuilder.toString());
        }
        return result;
    }

    public FiniteAutomaton FiniteAutomaton(){
        FiniteAutomaton finiteAutomaton = new FiniteAutomaton(
                startSymbol, 'F');
        for(char key: productions.keySet()){
            for(String element: productions.get(key)){
                if(element.length() < 2){
                    finiteAutomaton.setTransitions(new Transition(key,element.charAt(0),
                            finiteAutomaton.getFinalState()));
                }
                else{
                    finiteAutomaton.setTransitions(new Transition(key,element.charAt(0),
                            element.charAt(1)));
                }
            }
        }
        return finiteAutomaton;
    }

    public String CheckGrammarType(String[] prodVal, char[] prodKey, String[] prodKey1){

        // Check if the grammar is regular
        if (isRegular(prodVal, prodKey)) {
            return "\nGrammar Type 3: Regular";
        }

        // Check if the grammar is context-free
        if (isContextFree(prodKey)) {
            return "\nGrammar Type 2: Context-free";
        }

        // Check if the grammar is context-sensitive
        if (isContextSensitive(prodKey1)) {
            return "\nGrammar Type 1: Context-sensitive";
        }
      else{
        // The grammar is recursively enumerable by default
        return "\nGrammar Type 0: Recursively enumerable";}
    }

    public boolean isRegular(String[] prodVal, char[] prodKey){
        for ( String val : prodVal ) {
            for (char key : prodKey) {
                if ((val.length() == 1 && Character.isUpperCase(key)) || (val.length() == 2 && Character.isUpperCase(val.charAt(0)) && Character.isLowerCase(val.charAt(1)))
                        || (val.length() == 2 && Character.isUpperCase(val.charAt(1)) && Character.isLowerCase(val.charAt(0)))) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isContextFree(char[] prodKey) {
        for (char key : prodKey) {
            if (Character.isLowerCase(key)){
                return false;
            }
        }
        return true;
    }

    public boolean isContextSensitive(String[] prodKey1) {
        for (String key : prodKey1) {
            if (key.length() == 1 || key.length() == 2){
                return true;
            }
        }
        return false;
    }
}