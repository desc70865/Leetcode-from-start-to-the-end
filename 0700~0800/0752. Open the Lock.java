/* 
You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.

The lock initially starts at '0000', a string representing the state of the 4 wheels.

You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning and you will be unable to open it.

Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to open the lock, or -1 if it is impossible.

 

Example 1:

Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
Output: 6
Explanation:
A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
because the wheels of the lock become stuck after the display becomes the dead end "0102".
Example 2:

Input: deadends = ["8888"], target = "0009"
Output: 1
Explanation:
We can turn the last wheel in reverse to move from "0000" -> "0009".
Example 3:

Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
Output: -1
Explanation:
We can't reach the target without getting stuck.
Example 4:

Input: deadends = ["0000"], target = "8888"
Output: -1
 

Constraints:

1 <= deadends.length <= 500
deadends[i].length == 4
target.length == 4
target will not be in the list deadends.
target and deadends[i] consist of digits only.
 */

class Solution {
    private int[] x = {1, 10, 100, 1000};
    private int[] vis = new int[10000]; // 索引表示密码，值为1时，为死亡数字
    private int[] dis1 = new int[10000]; // 索引表示密码，值表示步数
    private int[] dis2 = new int[10000];
    private int touch = 0; // 相交
    private boolean flag = false;
    private Queue<Integer> q1 = new LinkedList<>();
    private Queue<Integer> q2 = new LinkedList<>();

    public int openLock(String[] deadends, String target) {
        int t1, t2;
        int[] temp = new int[2];
        for (String s : deadends)
            vis[Integer.parseInt(s)] = 1; // 死亡数字
        // q1从0000开始
        if (vis[0] == 1)
            return -1;
        q1.offer(0);
        vis[0] = 1;
        dis1[0] = 1;
        // q2从target开始
        int tar = Integer.parseInt(target);
        if (tar == 0)
            return 0;
        if (vis[tar] == 1)
            return -1;
        q2.offer(tar);
        vis[tar] = 1;
        dis2[tar] = 1;
        while (q1.size() > 0 && q2.size() > 0) {
            t1 = q1.poll();
            t2 = q2.poll();
            if (flag) {
                return dis1[touch];
            }
            for (int i = 0; i < 4; i++) {
                temp = js(t1, i);
                add(temp[0], dis1[t1], 1);
                add(temp[1], dis1[t1], 1);
            }
            for (int i = 0; i < 4; i++) {
                temp = js(t2, i);
                add(temp[0], dis2[t2], 2);
                add(temp[1], dis2[t2], 2);
            }
        }
        return -1;
    }

    int[] js(int t, int i) {
        int mid = t / x[i] % 10; // 返回第i位的数字
        int des = t - mid * x[i]; // 返回数字t，且第i位为0
        int i1 = 0, i2 = 0;
        i1 = (mid - 1 + 10) % 10;
        i2 = (mid + 1) % 10;
        i1 = des + i1 * x[i]; // 第i位的数-1，遇0为9
        i2 = des + i2 * x[i]; // 第i位的数+1，遇9为0
        return new int[]{i1, i2};
    }

    void add(int t, int d, int type) {
        if (vis[t] == 1) {
            if (!flag) {
                if ((type == 1 && dis2[t] != 0) || (type == 2 && dis1[t] != 0)) {
                    dis1[t] += dis2[t] + d - 1;
                    touch = t;
                    flag = true;
                }
            }
            return;
        }
        vis[t] = 1;
        if (type == 1) {
            q1.offer(t);
            dis1[t] = d + 1;
        } else if (type == 2) {
            q2.offer(t);
            dis2[t] = d + 1;
        }
        return;
    }
}