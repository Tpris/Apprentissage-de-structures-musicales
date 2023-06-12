package TreeGrammar;

import java.util.ArrayList;
import java.util.List;

public class Symbol extends Node{

    private List<String> degrees;
    Symbol(Node leftChild, Node rightChild) {
        super(leftChild, rightChild);
        this.degrees = new ArrayList<>();
    }

    public void addDegrees(String d){
        this.degrees.add(d);
    }

    public void addDegreesList(List<String> degrees){
        this.degrees.addAll(degrees);
    }
}
