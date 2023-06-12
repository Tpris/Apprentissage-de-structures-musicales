import Extractor.FileExtractor;
import Extractor.Music;
import TreeGrammar.GrammarGeneration;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, URISyntaxException {
//        File file = new File("res.txt");
//        try {
//            PrintStream printStream = new PrintStream(file);
//            System.setOut(printStream);
            ArrayList<Music> musics = FileExtractor.fileExtraction();
            //musics.forEach(m-> System.out.println(m.toString()+ "\n"+m.getNotes().toString()));
            System.out.println(musics.size());
            GrammarGeneration g = new GrammarGeneration(musics.get(0).getNotes(), 10);
            g.findGrammar();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
    }
}