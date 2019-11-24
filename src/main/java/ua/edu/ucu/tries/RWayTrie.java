package ua.edu.ucu.tries;

import ua.edu.ucu.immutable.Queue;

import java.util.ArrayList;
import java.util.Arrays;

public class RWayTrie implements Trie {
    private static Integer R = 26;
    private static Node root;
    private int length;

    public RWayTrie() {
        root = new Node();
        root.isLeaf = true;
        length = 0;
    }

    @Override
    public void add(Tuple t) {
        if (t.weight == 0 || t.term == null) {
            return;
        }
        int len = t.weight;
        String word = t.term;
        int ind;


        Node temp = root;

        for (int i = 0; i < len; i++) {

            ind = (word.charAt(i) - 'a');

            if (temp.next[ind] == null) {
                temp.next[ind] = new Node();
                temp.next[ind].val = Character.toString(word.charAt(i));

            }
//           temp.val = Character.toString(word.charAt(i));
            temp = temp.next[ind];

        }
        temp.isLeaf = true;
        temp.fullWord = word;
        length += 1;
    }

    @Override
    public boolean contains(String word) {
        if (word == null) {
            return false;
        }
        int len = word.length();
        int ind;
        Node temp = root;
        for (int i = 0; i < len; i++) {
            ind = word.charAt(i) - 'a';
            if (temp.next[ind] == null) {
                return false;
            }
            temp = temp.next[ind];
        }
        return (temp != null && temp.isLeaf);
    }

    @Override
    public boolean delete(String word) {
        if (word == null || word.length() == 0) {
            return false;
        }
        if (size() == 0 || !contains(word)) {
            return false;
        }
        Node temp = root;
        int len = word.length();
        int ind;
        for (int i = 0; i < len; i++) {
            ind = word.charAt(i) - 'a';
            if (temp.next[ind] == null) {
                return false;
            }
            temp = temp.next[ind];
        }
        temp.isLeaf = false;
        temp.fullWord = null;
        length -= 1;
        return true;
    }

    @Override
    public Iterable<String> words() {
//        BFS for RWayTrie
        Queue trieQueue = new Queue();
        ArrayList<String> toReturn = new ArrayList<>();
        trieQueue.enqueue(root);
        while (!trieQueue.isEmpty()) {
            Node curr = (Node) trieQueue.dequeue();

            for (int i = 0; i < curr.next.length; i++) {
                if (curr.next[i] != null) {

                    trieQueue.enqueue(curr.next[i]);
                    if (curr.next[i].fullWord != null) {
                        toReturn.add(curr.next[i].fullWord);
                    }
                }
            }
        }
        return toReturn;
    }

    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        if (s == null || s.length() == 0) {
            throw new RuntimeException();
        }
        ArrayList<String> to_return = new ArrayList<>();
        Node temp = root;
        int ind;

        for (int i = 0; i < s.length(); i++) {

            ind = s.charAt(i) - 'a';
            temp = temp.next[ind];
        }
        if (temp == null) {
            return to_return;
        } else if (temp.isLeaf) {
            to_return.add(temp.fullWord);
        }
//        BFS for RWayTrie

        Queue myQueue = new Queue();
        myQueue.enqueue(temp);
        while (!myQueue.isEmpty()) {
            Node curr = (Node) myQueue.dequeue();
            for (int i = 0; i < R; i++) {
                if (curr.next[i] != null) {
                    myQueue.enqueue(curr.next[i]);
                    if (curr.next[i].fullWord != null) {
                        to_return.add(curr.next[i].fullWord);
                    }
                }
            }
        }
        String[] res = Arrays.copyOf(to_return.toArray(),
                to_return.size(), String[].class);
        return Arrays.asList(res);
    }

    @Override
    public int size() {
        return length;
    }

    private static class Node {
        Node[] next = new Node[R];
        boolean isLeaf;
        private String val;
        private String fullWord;

        Node() {
            this.isLeaf = false;
            for (int i = 0; i < R; i++) {
                next[i] = null;
            }
        }

    }

}
