/* 
Suppose Andy and Doris want to choose a restaurant for dinner, and they both have a list of favorite restaurants represented by strings.

You need to help them find out their common interest with the least list index sum. If there is a choice tie between answers, output all of them with no order requirement. You could assume there always exists an answer.

 

Example 1:

Input: list1 = ["Shogun","Tapioca Express","Burger King","KFC"], list2 = ["Piatti","The Grill at Torrey Pines","Hungry Hunter Steakhouse","Shogun"]
Output: ["Shogun"]
Explanation: The only restaurant they both like is "Shogun".
Example 2:

Input: list1 = ["Shogun","Tapioca Express","Burger King","KFC"], list2 = ["KFC","Shogun","Burger King"]
Output: ["Shogun"]
Explanation: The restaurant they both like and have the least index sum is "Shogun" with index sum 1 (0+1).
Example 3:

Input: list1 = ["Shogun","Tapioca Express","Burger King","KFC"], list2 = ["KFC","Burger King","Tapioca Express","Shogun"]
Output: ["KFC","Burger King","Tapioca Express","Shogun"]
Example 4:

Input: list1 = ["Shogun","Tapioca Express","Burger King","KFC"], list2 = ["KNN","KFC","Burger King","Tapioca Express","Shogun"]
Output: ["KFC","Burger King","Tapioca Express","Shogun"]
Example 5:

Input: list1 = ["KFC"], list2 = ["KFC"]
Output: ["KFC"]
 

Constraints:

1 <= list1.length, list2.length <= 1000
1 <= list1[i].length, list2[i].length <= 30
list1[i] and list2[i] consist of spaces ' ' and English letters.
All the stings of list1 are unique.
All the stings of list2 are unique.
 */

class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        int m = list1.length, n = list2.length;
        Map<String, Integer> map = new HashMap<>();
        int i = -1, j = -1;
        int min = Integer.MAX_VALUE;
        List<Integer> res = new ArrayList<>();
        while (i < min || j < min) {
            if (++i < m) {
                if (! map.containsKey(list1[i])) map.put(list1[i], i);
                else {
                    int k = map.get(list1[i]);
                    if (i + k > min) ;
                    else if (i + k == min) res.add(i);
                    else {
                        min = i + k;
                        res = new ArrayList<>();
                        res.add(i);
                    }
                }
            }
            if (++j < n) {
                if (! map.containsKey(list2[j])) map.put(list2[j], j);
                else {
                    int k = map.get(list2[j]);
                    if (j + k > min) ;
                    else if (j + k == min) res.add(k);
                    else {
                        min = j + k;
                        res = new ArrayList<>();
                        res.add(k);
                    }
                }
            }
        }
        int N = res.size();
        String[] ans = new String[N];
        for (int p = 0; p < N; p++) ans[p] = list1[res.get(p)];
        return ans;
    }
}