package assessment1.task01.src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
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
        //Process the text and calculate the sum of words in the text, and store in a map
        Map<String,Integer> map = new HashMap<>();
        int totalWord = 0;
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
                totalWord ++;
            }
        }
        //Check if current word is larger than any of the top 10
        FrequencyCalculator frequencyCalculator = new FrequencyCalculator(map, totalWord);
        for (String key : map.keySet()){
            //Compare current word with top 10 words
            int insertPos = frequencyCalculator.compareRanking(key);
            //Update top 10 array
            frequencyCalculator.updateRanking(key, insertPos);
        }
        //Print out top 10 words
        System.out.println("Total words: "+totalWord);
        frequencyCalculator.printTopWords();
        //Close reader
        fileReader.close();
        bufferReader.close();
    }
}
