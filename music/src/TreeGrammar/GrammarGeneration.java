package TreeGrammar;
import Extractor.Note;

import java.util.*;
import java.util.stream.Collectors;

public class GrammarGeneration {

    private ArrayList<Node> corpus;
    private int length;

    private double threshold;

    public GrammarGeneration(ArrayList<Note> corpus, double threshold) {
        this.corpus = new ArrayList<>();
        this.corpus.addAll(corpus);
        this.length = corpus.size();
        this.threshold = threshold;
    }

    private int count(Node a, Node b){
        int c = 0;
        for(Node n : corpus){
            int i = corpus.indexOf(n);
            if(i!= 0 && n.equals(b) && corpus.get(i-1).equals(a)){
                ++c;
            }
        }
        return c;
    }

    private int count(Node a){
        return (int) corpus.stream().filter(sym -> a.equals(sym)).count();
    }

    private List<Node> getNotes(){
        return getVocabulary().stream().filter(sym -> sym instanceof Note).collect(Collectors.toList());
    }

    private ArrayList<Node> getVocabulary(){
        return new ArrayList<>(new HashSet<>(corpus));
    }

    private Node[] maxCount(){
        ArrayList<Node> symbolList = getVocabulary();
        Node aMax = null, bMax = null;
        int maxCount = 0;
        for (Node a : symbolList){
            for (Node b : symbolList){
                int count = count(a,b);
                if(count > maxCount) {
                    maxCount = count;
                    aMax = a;
                    bMax = b;
                }
            }
        }
        return new Node[]{aMax, bMax};
    }

    private double J(Node a, Node b){
        return Math.log(count(a,b)*length/(count(a)*count(b)));
    }

    private double J2(Node a, Node b){
        return J(a,b)/(a.getDuration()+b.getDuration());
    }

    private boolean J3(Node a, Node b){
//        System.out.println("J3 : begin \n"+a+"\n"+b);
        ArrayList<Node> voc = getVocabulary();
        double K = voc.size();
        K *= K;
        double sum = 0;
        for(Node u : voc){
            for(Node v : voc){
                double Ju = J(u,a)+J(u,b);
                double Jv = J(a,v)+J(b,v);
                sum += Ju*Ju + Jv*Jv / K;
            }
        }
//        System.out.println("J3 : end");
        return sum <= threshold;
    }

    private Node[] maxJ2(){
        List<Node> symbolList = getNotes();
        Node aMax = null, bMax = null;
        double maxCount = 0;
        for (Node a : symbolList){
                for (Node b : symbolList) {
                    double count = J2(a, b);
                    if (count > maxCount) {
                        maxCount = count;
                        aMax = a;
                        bMax = b;
                    }
                }
        }
        return new Node[]{aMax, bMax};
    }

    private boolean containsNote(){
        int count = 0;
        for(Node n : corpus){
            if(n instanceof Note) ++count;
        }
        return count>1;
    }

    private void replaceSymbol(Node a, Node b, Node X){
        Iterator<Node> itr = corpus.iterator();
        while (itr.hasNext()) {
            Node n = itr.next();
            int i = corpus.indexOf(n);
            if(i!= 0 && n.equals(b) && corpus.get(i-1).equals(a)){
                corpus.set(i-1,X);
                itr.remove();
            }
        }
    }

    private void joinSymbols(Node X){
//        System.out.println("Join : begin");
        ArrayList<Node> voc = getVocabulary();
        for(Node n : voc){
            if(!n.equals(X) && n instanceof Symbol && J3(X,n)){
                    Collections.replaceAll(corpus,n,X);
                    System.out.println(n.getSymbol()+" -> "+X.getSymbol());
            }
        }
//        System.out.println("Join : end");
    }

    public void findGrammar(){
        int count = 0;
        while (containsNote() && length>2*count++){
            Node[] ab = maxJ2();
            Node a = ab[0];
            Node b = ab[1];
            Node Xab = new Symbol(a,b);
            System.out.println(Xab.getSymbol()+" -> "+a.getSymbol()+" "+b.getSymbol());
            replaceSymbol(a,b,Xab);
            joinSymbols(Xab);
            //System.out.println(corpus);
            corpus.forEach(System.out::print);
        }
    }
}
