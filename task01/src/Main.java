package assessment1.task01.src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException{
        if (args.length<1){
            System.out.println("Enter a file name");
        }
        String fileName = args[0];
        //Read the file
        File file = new File(String.format("./%s.txt",fileName));
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferReader = new BufferedReader(fileReader);
        //Create a map to store the data
        Map<String,Integer> map = new HashMap<>();
        //Process the text and calculate the sum of words in the text
        int wordTotal = 0;
        String line;
        while (null != (line = bufferReader.readLine())){
            //Skip empty line
            if (line.equals("")){
                continue;
            }
            //To lower case and ignore punctuation, then split by space
            String[] lineArray = line.toLowerCase().replaceAll("[^a-zA-Z0-9\\s]","").trim().split(" ");
            for (int i=0;i<lineArray.length;i++){
                //Add word into map if ady exists, else add as new key
                if (map.containsKey(lineArray[i])){
                    map.put(lineArray[i],map.get(lineArray[i])+1);
                } else {
                    map.put(lineArray[i],1);
                }
                wordTotal ++;
            }
        }
        //Check if current word is larger than any of the top 10
        List<String> topArray = new LinkedList<>();
        int fillCount = 0;
        for (String key : map.keySet()){
            if (topArray.size()==0){
                topArray.add(key);
                fillCount ++;
            } else {
                int insertPos = -1;
                for (int j=topArray.size()-1;j>-1;j--){
                    if (map.get(key)>map.get(topArray.get(j))){
                        insertPos = j;
                    } else {
                        break;
                    }
                }
                //Update top 10 array
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
        //Print out top 10 words
        Float wordTotalFloat = (float) wordTotal;
        for (int l=0;l<topArray.size();l++){
            String topWord = topArray.get(l);
            Float frequency = 0.000f;
            frequency = map.get(topWord)/wordTotalFloat;
            System.out.printf("%d. %s: %.3f\n",l+1,topWord,frequency);
        }
        System.out.println(wordTotal);
        //Close reader
        fileReader.close();
        bufferReader.close();
    }
}
