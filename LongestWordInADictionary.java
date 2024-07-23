import java.util.LinkedList;
import java.util.Queue;

//Time - O(nk), space - O(nk)
class Solution {
    class TrieNode {
        String word;
        TrieNode[] children;
        public TrieNode() {
            children = new TrieNode[26];
        }
    }

    TrieNode root;

    private void insert(String word) {
        TrieNode curr = root;
        for(int i=0; i<word.length(); i++) {
            char ch = word.charAt(i);
            if(curr.children[ch - 'a'] == null) {
                curr.children[ch - 'a'] = new TrieNode();
            }
            curr = curr.children[ch - 'a'];
        }
        curr.word = word;
    }

    public String longestWord(String[] words) {
        if(words == null || words.length == 0) {
            return "";
        }

        root = new TrieNode();

        for(String word : words) {
            insert(word);
        }

        Queue<TrieNode> q = new LinkedList<>();
        q.add(root);
        TrieNode curr = null;
        while(!q.isEmpty()) {
            curr = q.poll();
            for(int i=25; i>=0; i--) {
                if(curr.children[i] != null && curr.children[i].word != null) {
                    q.add(curr.children[i]);
                }
            }
        }
        if(curr.word == null) return "";
        return curr.word;
    }
}