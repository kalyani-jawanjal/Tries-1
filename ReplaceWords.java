import java.util.List;

class ReplaceWords {

    class TrieNode {
        boolean isEnd;
        TrieNode[] children;
        public TrieNode() {
            isEnd = false;
            children = new TrieNode[26];
        }
    }

    TrieNode root;

    public void createTrie(List<String> dictionary) {
        root = new TrieNode();

        for(String word : dictionary) {
            TrieNode curr = root;
            for(int i=0; i<word.length(); i++) {
                char ch = word.charAt(i);
                if(curr.children[ch - 'a'] == null) {
                    curr.children[ch - 'a'] = new TrieNode();
                }
                curr = curr.children[ch - 'a'];
            }
            curr.isEnd = true;
        }
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        if(dictionary == null || dictionary.size() == 0 || sentence == null || sentence.length() == 0) {
            return sentence;
        }

        createTrie(dictionary);

        StringBuilder result = new StringBuilder();
        String[] strs = sentence.split(" ");

        for(int i=0; i<strs.length; i++) {
            if(i > 0) {
                result.append(" ");
            }
            TrieNode curr = root;
            StringBuilder sb = new StringBuilder();
            String word = strs[i];
            for(int j=0; j<word.length(); j++) {
                char ch = word.charAt(j);
                if(curr.children[ch - 'a'] == null || curr.isEnd) {
                    break;
                }
                sb.append(ch);
                curr = curr.children[ch - 'a'];
            }
            if(curr.isEnd) {
                result.append(sb);
            } else {
                result.append(word);
            }
        }

        return result.toString();
    }
}