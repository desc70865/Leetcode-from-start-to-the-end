/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */


/**
 * Return an array of arrays of size *returnSize.
 * The sizes of the arrays are returned as *returnColumnSizes array.
 * Note: Both returned array and *columnSizes array must be malloced, assume caller calls free().
 */

#define ElementType struct TreeNode*

typedef struct Node{
    ElementType data;
    struct Node* next;
}queueNode;

typedef struct{
    queueNode* head;
    queueNode* tail;
    int size;
}Queue;

Queue* createQueue(){
    Queue* queue = (Queue*) malloc(sizeof(Queue));
    queue->head = NULL;
    queue->tail = NULL;
    queue->size = 0;
    return queue;
}

void offerElement(Queue* queue, ElementType item) {
    queueNode* node = (queueNode*) malloc(sizeof(queueNode));
    node->data = item;
    node->next = NULL;
    if (queue->head == NULL)
    {
        queue->head = node;
    }
    if (queue->tail == NULL)
    {
        queue->tail = node;
    }
    else
    {
        queue->tail->next = node;
        queue->tail = node;
    }
    queue->size++;
}

int isEmpty(Queue* queue){
    return queue->size == 0;
}

ElementType deleteElement(Queue* queue) {
    queueNode* tmp = queue->head;
    if (queue->head == queue->tail)
    {
        queue->head = NULL;
        queue->tail = NULL;
    }
    else
    {
        queue->head = queue->head->next;
    }
    ElementType item = tmp->data;
    free(tmp);
    queue->size--;
    return item;
}

int dfs(struct TreeNode* root){
    return root == NULL ? 0 : fmax(dfs(root->left), dfs(root->right)) + 1;
}

int** levelOrder(struct TreeNode* root, int* returnSize, int** returnColumnSizes){
    int maxDepth = dfs(root);
    *returnSize = maxDepth;
    *returnColumnSizes = (int*) malloc(maxDepth * sizeof(int));
    int** list = (int**) malloc(maxDepth * sizeof(int*));
    Queue* queue = createQueue();
    offerElement(queue, root);
    int depth = 0;
    while (! isEmpty(queue))
    {
        int size = queue->size;
        *returnColumnSizes[depth] = size;
        list[depth] = (int*) malloc(size * sizeof(int));
        int idx = 0;
        while (size-- > 0)
        {
            struct TreeNode* cur = (struct TreeNode*) deleteElement(queue);
            list[depth][idx++] = cur->val;
            if (cur->left)
            {
                offerElement(queue, cur->left);
            }
            if (cur->right)
            {
                offerElement(queue, cur->right);
            }
        }
        depth++;
    }
    return list;
}