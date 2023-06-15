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
        String begin = (i >= 0 && i < 26)?"":String.valueOf((char) ('A' + i/26-1));
        return begin+(char) ('A' + i%26);
//        if (i >= 0 && i < 58) {
//            return String.valueOf((char) ('A' + i));
//        } else {
//            return String.valueOf((char) ('A' + i/58-1))+(char) ('A' + i%58);
//        }

    }

    public int getDuration() {
        return duration;
    }

    public void addTime(int time){
        duration+=time;
    }

    public String getSymbol() {
        return symbol;
    }

    ////////
    public StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb) {
        if(leftChild!=null) {
            leftChild.toString(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
        }
        sb.append(prefix).append(isTail ? "└── " : "┌── ").append(symbol).append("\n");
        if(rightChild!=null) {
            rightChild.toString(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
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
