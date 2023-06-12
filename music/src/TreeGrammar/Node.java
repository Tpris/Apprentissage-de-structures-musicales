package TreeGrammar;

import Extractor.Note;

import java.util.Objects;

public abstract class Node {
    private static int numSym = 0;
    private String symbol;
    private int duration;

    private Node leftChild;

    private Node rightChild;

    /**
     * Used by Note
     * @param duration
     */
    public Node(String symbol, int duration){
        this.symbol = symbol;
        this.duration = duration;
        this.leftChild = this.rightChild = null;
    }

    Node(Node leftChild, Node rightChild) {
        this.symbol = numToLetter(numSym++);
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.duration = leftChild.getDuration()+rightChild.getDuration();
    }

    private static String numToLetter(int i) {
        if (i >= 0 && i <= 25) {
            return String.valueOf((char) ('A' + i));
        } else {
            return "?";
        }
    }

    public int getDuration() {
        return duration;
    }

    public String getSymbol() {
        return symbol;
    }

    ////////
    public StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb) {
        if(rightChild!=null) {
            rightChild.toString(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
        }
        sb.append(prefix).append(isTail ? "└── " : "┌── ").append(symbol).append("\n");
        if(leftChild!=null) {
            leftChild.toString(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
        }
        return sb;
    }

    @Override
    public String toString() {
        return this.toString(new StringBuilder(), true, new StringBuilder()).toString();
    }
    ////////


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node node)) return false;
        return Objects.equals(getSymbol(), node.getSymbol());
    }
}
