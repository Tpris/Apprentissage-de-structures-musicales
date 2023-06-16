import Extractor.FileExtractor;
import Extractor.Music;
import TreeGrammar.GrammarGeneration;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, URISyntaxException {
        /*File file = new File("resGloballl.txt");
        try {
            PrintStream logOut = new PrintStream(new FileOutputStream(file));
//
            PrintStream teeStdOut = new TeeStream(System.out, logOut);
//            PrintStream teeStdErr = new TeeStream(System.err, logOut);
//
            System.setOut(teeStdOut);
//            System.setErr(teeStdErr);
*/
        File file = new File("res3210.txt");
        try {
            PrintStream printStream = new PrintStream(file);
            System.setOut(printStream);
            ArrayList<Music> musics = FileExtractor.fileExtraction();
            //musics.forEach(m-> System.out.println(m.toString()+ "\n"+m.getNotes().toString()));
            System.out.println("Nombre de musiques : "+musics.size());
            GrammarGeneration g = new GrammarGeneration(musics, 100);
            g.findGrammar();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}