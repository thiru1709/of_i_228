import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        String filepath = "src/moby.txt";
        int mostRepeatedWordsCount = 5;

        String fileContent = FileContent.getFileContent(filepath);
        List<String> listOfExclusions = getListOfExclusions();
        //Get the count of each word and creating a Map
        Map<String, Integer> wordByCountMap = getWordCountMap(fileContent,listOfExclusions);


       /* Identify and display the top 5 most frequently used words,
        along with their usage counts. Exclude prepositions, pronouns, conjunctions,
        and articles from this analysis.*/
        getTheMostRepeatedWords(wordByCountMap, mostRepeatedWordsCount);

        /*List all unique words in alphabetical order, excluding
            prepositions, pronouns, conjunctions, and articles.*/
        getWordsAlphabeticallySorted(wordByCountMap);
    }

    private static void getWordsAlphabeticallySorted(Map<String, Integer> wordByCountMap) {
        TreeMap<String,Integer> wordsInSortedOrder = new TreeMap<>(wordByCountMap);
        System.out.println("####################################################################\n Words after Alphabetically sorted -> and their count");
        for(Map.Entry<String,Integer> entry : wordsInSortedOrder.entrySet()){
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        System.out.println("####################################################################\n");
    }

    private static void getTheMostRepeatedWords(Map<String, Integer> wordByCountMap, int mostRepeatedWordsCount) {
        Map<String, Integer> wordInSortedOrderByCount = new HashMap<>(wordByCountMap);
        //Creating a list with the Map entries
        List<Map.Entry<String,Integer>> entryList = new ArrayList<>(wordInSortedOrderByCount.entrySet());
        //Passing the comparator to sort the list by the value in descending order
        entryList.sort(Map.Entry.<String,Integer>comparingByValue().reversed());
        System.out.println("####################################################################\nThe top " + mostRepeatedWordsCount +" most repeated words are -> and their count");
        for(int i = 0 ; i < mostRepeatedWordsCount ; i++){
            System.out.println(entryList.get(i).getKey() + " -> " + entryList.get(i).getValue());
        }
        System.out.println("####################################################################\n");
    }

    private static List<String> getListOfExclusions() {
        //Hardcoded the words that needs to be excluded
        String[] exclusions = {"in","on","at","he","she","it","and","or","but","the","a","an","is","was"};
        return Arrays.asList(exclusions);
    }

    private static Map<String, Integer> getWordCountMap(String fileContent, List<String> listOfExclusions) {
        Map<String,Integer> wordByCountMap = new HashMap<>();
        String[] words = fileContent.toLowerCase().split("[\\s\\p{Punct}]+");
        for(String word : words){
            int count = 0;
            if(!word.isBlank() && !listOfExclusions.contains(word)) {
                if(wordByCountMap.containsKey(word)) {
                    count = wordByCountMap.get(word);
                    wordByCountMap.put(word, ++count);
                }else{
                    wordByCountMap.put(word,++count);
                }

            }
        }
        return wordByCountMap;
    }
}