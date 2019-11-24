package ua.edu.ucu.autocomplete;

import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;

import java.util.ArrayList;
import java.util.LinkedList;


/**
 * @author andrii
 */
public class PrefixMatches {

    private Trie trie;


    public PrefixMatches(Trie trie) {
        this.trie = trie;
    }

    public int load(String... strings) {
        int counter = 0;
        if (strings == null) {
            throw new RuntimeException();
        }
        for (int i = 0; i < strings.length; i++) {
            if (strings[i] != null) {
                if (strings[i].length() > 2) {
                    Tuple tuple = new Tuple(strings[i], strings[i].length());
                    trie.add(tuple);
                    counter += 1;
                }
            }
        }
        return counter;
    }

    public boolean contains(String word) {
        return trie.contains(word);
    }

    public boolean delete(String word) {
        return trie.delete(word);
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        if (pref.length() >= 2) {
            return trie.wordsWithPrefix(pref);
        } else {
            return new ArrayList<String>();
        }

    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        Iterable<String> lst = trie.wordsWithPrefix(pref);
        ArrayList<String> to_return = new ArrayList<>();
        LinkedList<Integer> lenLst = new LinkedList<>();
        int ind = 0;
        for (String i : lst) {
            if (!lenLst.contains(i.length())) {
                ind += 1;
                if (ind == k + 1) {
                    break;
                }
                lenLst.add(i.length());
            }
            to_return.add(i);


        }
        return to_return;
    }

    public int size() {
        return trie.size();
    }
}
