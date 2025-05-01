public class MorseTree {
    public TreeNode<Character> rootOfTree;

    public MorseTree() {
        rootOfTree = new TreeNode<Character>('_');
    }

    private String preorderImpl(TreeNode<Character> node) {
        if (node == null){
            return "";
        }
        String result = "";
        result += node.getElement() + " ";
        result += preorderImpl(node.getLeft());
        result += preorderImpl(node.getRight());
        return result;
    }

    public String preorder() {
        return preorderImpl(rootOfTree);
    }
    public String postorder() {
        return postorderImpl(rootOfTree);
    }

    private String postorderImpl(TreeNode<Character> node) {
        if (node == null){
            return "";
        }
        String result = "";
        result += postorderImpl(node.getLeft());
        result += postorderImpl(node.getRight());
        result += node.getElement() + " ";
        return result;
    }

    private String charToMorse(char letter, TreeNode<Character> treeNode) {
        if(treeNode==null) {
            return null; // The letter is not here
        }
        if(treeNode.getElement()==letter) {
            return ""; // The letter is here, so the location will be added when derecursing
        }
        String left = charToMorse(letter, treeNode.getLeft());
        String right = charToMorse(letter, treeNode.getRight());
        if(left != null) { // The letter is on the left side
            return "o " + left; 
        }
        else if(right != null) { // The letter is on the right side
            return "- " + right;
        }
        else { // The letter is not on this branch of the tree
            return null;
        }
    }

    public String englishToMorse(String english) {
        String result = "";
        for(int i = 0; i < english.length(); i++) {
            char letter = english.charAt(i);
            if(letter==' ') {
                continue;
            }
            letter = Character.toLowerCase(letter);
            result += charToMorse(letter, rootOfTree) + "| ";
        }
        return result;
    }
    public String morseToEnglish(String morse) {
        String result = "";
        TreeNode<Character> treeNode = rootOfTree;
        for(int i = 0; i < morse.length(); i++) {
            char morseChar = morse.charAt(i);

            switch (morseChar) {
                case ' ':
                    continue;
                case '|':
                    result+=treeNode.getElement();
                    treeNode = rootOfTree;
                    break;
                case 'o':
                    treeNode = treeNode.getLeft();
                    break;
                case '-':
                    treeNode = treeNode.getRight();
                    break;
                default:
                    break;
            }
        }
        return result;
    }
}