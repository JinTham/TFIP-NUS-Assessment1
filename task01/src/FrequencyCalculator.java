package assessment1.task01.src;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FrequencyCalculator {
    private Map<String, Integer> map;
    private int totalWord;
    private List<String> topArray = new LinkedList<>();
    //Constructor
    public FrequencyCalculator(Map<String, Integer> map, int totalWord){
        this.map = map;
        this.totalWord = totalWord;
        for (String key : map.keySet()){
            if (this.topArray.size()>0){
                break;
            }
            this.topArray.add(key);
        }
    }
    //Methods - check if current word is larger than any of the top 10 words, if yes then return the index, else return -1
    public int compareRanking(String key){
        int insertPos = -1;
        for (int j=topArray.size()-1;j>-1;j--){
            if (map.get(key)<=map.get(topArray.get(j))){
                break;
            }
            insertPos = j;
        }
        return insertPos;
    }
    //Methods - update top 10 words
    public void updateRanking(String key, int insertPos){
        if (!this.topArray.contains(key)){
            if (topArray.size()<10 && insertPos==-1){ //topArray still not fully filled yet and new word is smallest
                topArray.add(key); //add to the end of the list
            } else if (topArray.size()<10 && insertPos>-1){ //topArray still not fully filled yet and new word is not smallest
                topArray.add(insertPos,key); //add to the correct position and push rest of the element to the right
            } else if (topArray.size()>=10 && insertPos>-1){
                topArray.add(insertPos,key);
                topArray.remove(10); //remove the 11th element
            }
        }
    }
    //Printout top 10 words
    public void printTopWords(){
        Float totalWordFloat = (float) this.totalWord;
        for (int l=0;l<this.topArray.size();l++){
            String word = this.topArray.get(l);
            Float frequency = map.get(word)/totalWordFloat;
            System.out.printf("%d. %s: %.3f\n",l+1,word,frequency);
        }
    }

}
