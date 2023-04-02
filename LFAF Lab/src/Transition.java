public record Transition(char currentState, char transitionLabel, char nextState) {

    @Override
    public String toString() {
        return "currentState = " + currentState + "\n" +
                "transitionLabel = " + transitionLabel + "\n" +
                "nextState = " + nextState + "\n";
    }
}