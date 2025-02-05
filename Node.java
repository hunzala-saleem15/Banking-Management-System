public class Node{
    Account account;
    Node nextNode;

    public Node(Account account) {
        this.account = account;
        this.nextNode = null;
    }
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }
}