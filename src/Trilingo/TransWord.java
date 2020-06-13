package Trilingo;

import java.util.Iterator;
import java.util.List;

public class TransWord {

    private String word;
    private String value;

    public TransWord(String word, String value){
        this.word = word;
        this.value = value;
    }

    public String getWord(){
        return word;
    }

    public String getValue(){
        return value;
    }
}
