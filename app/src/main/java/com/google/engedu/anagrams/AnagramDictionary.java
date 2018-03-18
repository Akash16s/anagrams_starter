/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    private ArrayList<String> wordList;
    private HashSet<String> wordSet;
    private HashMap<String, ArrayList<String>> lettersToWord;


    public AnagramDictionary(Reader reader) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        String line;
        wordList=new ArrayList<>();
        wordSet = new HashSet<>();
        lettersToWord = new HashMap<>();

        while((line = in.readLine()) != null) {
            String word = line.trim();
            wordList.add(word);
            wordSet.add(word);
            String add=sortLetters(word);
            if(lettersToWord.containsKey(add)){
                lettersToWord.get(add).add(word);
            }else{
                ArrayList<String> r= new ArrayList<>();
                r.add(word);
                lettersToWord.put(add,r);
            }




        }
    }

    public boolean isGoodWord(String word, String base) {
        return wordSet.contains(word) && !word.contains(base);
    }

    public List<String> getAnagrams(String targetWord) {
        ArrayList<String> result = new ArrayList<>();
        String k=sortLetters(targetWord);
        for(String i : wordList){
            if(k.equals(sortLetters(i))){
                result.add(i);
            }
        }

        return result;
    }

    public ArrayList<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> temp = new ArrayList<>();
        String k;
        for(char i='1';i<='z';i++){
            k = sortLetters(word.concat(""+i));
            if (lettersToWord.containsKey(k)){
                temp.addAll(lettersToWord.get(k));
            }

        }
        return temp;
    }

    public String sortLetters(String word) {
        char [] sorted= word.toCharArray();
        Arrays.sort(sorted);
        return new String (sorted);
    }
    

    public String pickGoodStarterWord() {
        return "stop";
    }
}
