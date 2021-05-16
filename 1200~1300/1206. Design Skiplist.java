/* 
Design a Skiplist without using any built-in libraries.

A Skiplist is a data structure that takes O(log(n)) time to add, erase and search. Comparing with treap and red-black tree which has the same function and performance, the code length of Skiplist can be comparatively short and the idea behind Skiplists are just simple linked lists.

For example: we have a Skiplist containing [30,40,50,60,70,90] and we want to add 80 and 45 into it. The Skiplist works this way:


Artyom Kalinin [CC BY-SA 3.0], via Wikimedia Commons

You can see there are many layers in the Skiplist. Each layer is a sorted linked list. With the help of the top layers, add , erase and search can be faster than O(n). It can be proven that the average time complexity for each operation is O(log(n)) and space complexity is O(n).

To be specific, your design should include these functions:

bool search(int target) : Return whether the target exists in the Skiplist or not.
void add(int num): Insert a value into the SkipList. 
bool erase(int num): Remove a value in the Skiplist. If num does not exist in the Skiplist, do nothing and return false. If there exists multiple num values, removing any one of them is fine.
See more about Skiplist : https://en.wikipedia.org/wiki/Skip_list

Note that duplicates may exist in the Skiplist, your code needs to handle this situation.

 

Example:

Skiplist skiplist = new Skiplist();

skiplist.add(1);
skiplist.add(2);
skiplist.add(3);
skiplist.search(0);   // return false.
skiplist.add(4);
skiplist.search(1);   // return true.
skiplist.erase(0);    // return false, 0 is not in skiplist.
skiplist.erase(1);    // return true.
skiplist.search(1);   // return false, 1 has already been erased.
 

Constraints:

0 <= num, target <= 20000
At most 50000 calls will be made to search, add, and erase.
 */

class Skiplist {
    private static int DEFAULT_MAX_LEVEL = 32;
    private static double DEFAULT_P_FACTOR = 0.25;

    Node head = null;
    int currentLevel = 1;

    public Skiplist() {
        this.head = new Node(null, DEFAULT_MAX_LEVEL);
    }
    
    public void add(int num) {
        int level = randomLevel();
        Node updateNode = head;
        Node newNode = new Node(num, level);
        for (int i = currentLevel - 1; i >= 0; i--) {
            updateNode = findClosest(updateNode, i, num);
            if (i < level) {
                Node tmp = updateNode.next[i];
                updateNode.next[i] = newNode;
                newNode.next[i] = tmp;
            }
        }
        if (level > currentLevel) {
            for (int i = currentLevel; i < level; i++) {
                head.next[i] = newNode;
            }
            currentLevel = level;
        }
    }

    public boolean search(int num) {
        Node node = head;
        for (int i = currentLevel - 1; i >= 0; i--) {
            node = findClosest(node, i, num);
            if (node.next[i] != null && node.next[i].value == num) {
                return true;
            }
        }
        return false;
    }

    public boolean erase(int num) {
        boolean delete = false;
        Node node = head;
        for (int i = currentLevel - 1; i >= 0; i--) {
            node = findClosest(node, i, num);
            if (node.next[i] != null && node.next[i].value == num) {
                node.next[i] = node.next[i].next[i];
                delete = true;
            }
        }
        return delete;
    }
    
    private Node findClosest(Node node, int levelIndex, int value) {
        while ((node.next[levelIndex]) != null && node.next[levelIndex].value < value) {
            node = node.next[levelIndex];
        }
        return node;
    }
    
    private static int randomLevel() {
        int level = 1;
        while (Math.random() < DEFAULT_P_FACTOR && level < DEFAULT_MAX_LEVEL) {
            level++;
        }
        return level;
    }

    class Node{
        Integer value;
        Node[] next;

        public Node(Integer value, int size) {
            this.value = value;
            this.next = new Node[size];
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }
}

/**
 * Your Skiplist object will be instantiated and called as such:
 * Skiplist obj = new Skiplist();
 * boolean param_1 = obj.search(target);
 * obj.add(num);
 * boolean param_3 = obj.erase(num);
 */