package Extractor;

import TreeGrammar.Node;

import java.util.Objects;

public class Note extends Node {

    Note(int degree, int duration){
        super(Integer.toString(degree), duration);
    }

        @Override
    public String toString() {
        return getSymbol() + "(" + getDuration() +") ";
    }
}
