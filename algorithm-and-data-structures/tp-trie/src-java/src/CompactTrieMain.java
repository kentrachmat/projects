import trie.CompactTrie ;

public class CompactTrieMain {
   
   public static void main (String args[]) {
      
      CompactTrie t = new CompactTrie();
      t.add("banane");
      t.add("citronnier");
      t.add("citron");
      t.add("pomme");
      t.add("poire");
      t.add("ci");
      t.print();
      
   }

}
