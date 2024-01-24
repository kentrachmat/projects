import trie.Trie ;

public class TrieMain {
   
   public static void main (String args[]) {
      
      Trie t = new Trie();
      t.add("banane");
      t.add("citronnier");
      t.add("citron");
      t.add("pomme");
      t.add("poire");
      t.add("ci");
      t.print();
      
   }

}
