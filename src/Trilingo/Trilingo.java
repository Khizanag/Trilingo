package Trilingo;

import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.List;

public class Trilingo implements TrilingoConstants {

    private TrilingoLexicon lexicon;
    private BufferedWriter writer;
    private TransWord currWord;
    public DefaultTableModel model;

    public Trilingo() throws IOException {
        lexicon = new TrilingoLexicon(DATABASE_FILENAME);
        initModel();
        initWriter();
        new TrilingoFrame(this);
    }

    private void initModel(){
        model = new DefaultTableModel(0, 2){
            private String[] names = {"From word", "word to guess"};
            @Override
            public String getColumnName(int i){
                return names[i];
            }
        };
    }

    private void initWriter() throws IOException {
        writer = new BufferedWriter(new FileWriter(DATABASE_FILENAME, true) );
    }

    public void addWordToLexicon(String fromWord, String toWord) throws IOException {
        writer.append(fromWord + DELIMITER + toWord + "\n");
        lexicon.addWord(new TransWord(fromWord, toWord));
    }

    public void finish() throws IOException {
        writer.close();
    }

    public void askNextWord(){
        currWord = lexicon.getRandomWordPair();
        System.out.println(currWord.getWord());
        String[] row = {currWord.getWord(), ""};
        model.addRow( row);
    }

    public void showAnswer(){
        if(currWord != null)
            model.setValueAt((Object)currWord.getValue(), model.getRowCount()-1, 1);
    }

    public static void main(String[] args) throws IOException {
        new Trilingo();
    }


}
