package Trilingo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class TrilingoLexicon implements TrilingoConstants{

    private List<TransWord> lexicon;

    public TrilingoLexicon(String filename) throws IOException {
        lexicon= new ArrayList<>();
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);

        String line;
        while((line  = br.readLine()) != null){
            int index = line.indexOf(DELIMITER);
            String word = line.substring(0, index);
            System.out.println("word: " + word);
            String value = line.substring(index+1);
            System.out.println("value: "+value);
            addWord(new TransWord(word, value));
        }

        fr.close();
        br.close();
    }

    public void addWord(TransWord word){
        lexicon.add(word);
    }

    public TransWord getRandomWordPair(){
        if(lexicon.size() == 0)
            return null;
        Random rand = new Random();
        int index = rand.nextInt(lexicon.size()-1);
        return lexicon.get(index);
    }
}
