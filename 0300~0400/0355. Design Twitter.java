/* 
Design a simplified version of Twitter where users can post tweets, follow/unfollow another user and is able to see the 10 most recent tweets in the user's news feed. Your design should support the following methods:

postTweet(userId, tweetId): Compose a new tweet.
getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
follow(followerId, followeeId): Follower follows a followee.
unfollow(followerId, followeeId): Follower unfollows a followee.
Example:

Twitter twitter = new Twitter();

// User 1 posts a new tweet (id = 5).
twitter.postTweet(1, 5);

// User 1's news feed should return a list with 1 tweet id -> [5].
twitter.getNewsFeed(1);

// User 1 follows user 2.
twitter.follow(1, 2);

// User 2 posts a new tweet (id = 6).
twitter.postTweet(2, 6);

// User 1's news feed should return a list with 2 tweet ids -> [6, 5].
// Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
twitter.getNewsFeed(1);

// User 1 unfollows user 2.
twitter.unfollow(1, 2);

// User 1's news feed should return a list with 1 tweet id -> [5],
// since user 1 is no longer following user 2.
twitter.getNewsFeed(1);
 */

class Twitter {
    private final Map<Integer, Set<Integer>> watchList;
    private final Map<Integer, List<Integer>> tweetsList;
    private final Map<Integer, Integer> timeTable;
    private static int timestamp = 0;

    /**
     * Initialize your data structure here.
     */
    public Twitter() {
        this.watchList = new HashMap<>();
        this.tweetsList = new HashMap<>();
        this.timeTable = new HashMap<>();
    }

    /**
     * Compose a new tweet.
     */
    public void postTweet(int userId, int tweetId) {
        timeTable.put(tweetId, this.timestamp++);
        tweetsList.computeIfAbsent(userId, z -> new ArrayList<>()).add(tweetId);
    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users
     * who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
     */
    public List<Integer> getNewsFeed(int userId) {
        Set<Integer> users = watchList.getOrDefault(userId, new HashSet<>());
        users.add(userId);
        final int size = users.size();
        // int[0] -> useId, int[1] -> tweetIdIndex
        PriorityQueue<int[]> messageQueue = new PriorityQueue<>(size,
            Collections.reverseOrder(Comparator.comparingInt(tl -> timeTable.get(tweetsList.get(tl[0]).get(tl[1])))));
        for (Integer user: users) {
            List<Integer> tweets = tweetsList.get(user);
            if (tweets != null && !tweets.isEmpty()) {
                messageQueue.add(new int[]{user, tweets.size() - 1});
            }
        }
        int tweetsNum = 10;
        List<Integer> newsFeed = new ArrayList<>(tweetsNum);
        while (tweetsNum-- > 0 && !messageQueue.isEmpty()) {
            int[] poll = messageQueue.poll();
            int user = poll[0];
            int tweetIndex = poll[1];
            List<Integer> tweets = tweetsList.get(user);
            newsFeed.add(tweets.get(tweetIndex));
            if (tweetIndex > 0) {
                messageQueue.add(new int[]{user, tweetIndex - 1});
            }
        }
        users.remove(userId);
        return newsFeed;
    }

    /**
     * Follower follows a followee. If the operation is invalid, it should be a no-op.
     */
    public void follow(int followerId, int followeeId) {
        if (followeeId != followerId) {
            watchList.computeIfAbsent(followerId, z -> new HashSet<>()).add(followeeId);
        }
    }

    /**
     * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
     */
    public void unfollow(int followerId, int followeeId) {
        if (watchList.containsKey(followerId)) {
            watchList.get(followerId).remove(followeeId);
        }
    }
}

/**
 * Your Twitter object will be instantiated and called as such: Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId); List<Integer> param_2 = obj.getNewsFeed(userId); obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */