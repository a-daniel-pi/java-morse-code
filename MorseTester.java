
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MorseTester {
    public static void main(String[] args) throws FileNotFoundException {
        MorseTree morseTree = new MorseTree();

        Scanner scanner = new Scanner(new File("morsecode.txt"));

        while(scanner.hasNext()) {
            TreeNode<Character> treeNode = morseTree.rootOfTree;
            String line = scanner.nextLine();
            int lineLength = line.length();

            char letter = line.charAt(0);
            for(int i = 2; i < lineLength; i+=2) {
                if (line.charAt(i)=='o') {
                    TreeNode<Character> next = treeNode.getLeft();
                    if(next == null) {
                        treeNode.setLeft(new TreeNode<Character>(letter));
                    }
                    treeNode = next;
                }
                else if (line.charAt(i)=='-') {
                    TreeNode<Character> next = treeNode.getRight();
                    if(next == null) {
                        treeNode.setRight(new TreeNode<Character>(letter));
                    }
                    treeNode = next;
                }
            }
        }

        System.out.println(morseTree.preorder());
        System.out.println(morseTree.postorder());

        String englishString = "The quick fox";
        String morse = morseTree.englishToMorse(englishString);


        System.out.println(morse);
        System.out.println(morseTree.morseToEnglish(morse));

        scanner.close();
    }
}
