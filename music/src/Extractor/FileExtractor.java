package Extractor;

import com.sun.tools.javac.Main;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileExtractor {
    private FileExtractor() {}

    private static File[] getResourceFolderFiles() {
        URL url = Main.class.getClassLoader().getResource("Omnibook_xml");
        String path = url.getPath();
        return new File(path).listFiles();
    }

    public static ArrayList<Music> fileExtraction() throws ParserConfigurationException, SAXException, URISyntaxException {
        ArrayList<Music> musics = new ArrayList<>();
        try {
            //URL url = Main.class.getClassLoader().getResource("Omnibook_xml/Chi_Chi.xml");
            //File file = Paths.get(url.toURI()).toFile();
            for (File file : getResourceFolderFiles()) {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document document = db.parse(file);
                document.getDocumentElement().normalize();
                System.out.println("Root Element :" + document.getDocumentElement().getNodeName());
                NodeList nList = document.getElementsByTagName("note");
                int startNote = 'A';

                Music music = new Music(file.getName());
                for (int temp = 0; temp < nList.getLength(); temp++) {
                    Node nNode = nList.item(temp);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        if (eElement.getElementsByTagName("pitch").item(0) != null) {
                            System.out.println("\nNote : " + eElement.getElementsByTagName("pitch").item(0).getChildNodes().item(1).getTextContent());
                            System.out.println("Duration : " + eElement.getElementsByTagName("duration").item(0).getTextContent());

                            int duration = Integer.parseInt(eElement.getElementsByTagName("duration").item(0).getTextContent());
                            int degree = eElement.getElementsByTagName("pitch").item(0).getChildNodes().item(1).getTextContent().charAt(0);
                            degree = Math.floorMod((degree - startNote - 2), 7) + 1;
                            Note n = new Note(degree, duration);
                            System.out.println(n.toString());
                            music.addNote(n);
                        }
                    }
                }
                musics.add(music);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return musics;
    }
}
