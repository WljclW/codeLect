package leecode_Debug._hot100;

import leecode_Debug.linkList.ListNode;

import java.util.LinkedList;

public class _00hard {
    /*
    42. 接雨水
    * */
    /**最优解法：双指针
        【关键的一句话】只要height[left]<height[right]，则必有leftMax<rightMax...(可以用反证法
     证明)
        【流程】
        ①每到一个位置先更新leftMax，rightMax。
        ②如果height[left]<height[right]，则说明leftMax小，因此计算左边能接到的雨水，计算方式————
     leftMax-height[left]，并更新左指针；否则的话计算右边能接到的雨水————rightMax-height[right]，
     并更新右指针。。。当前位置的雨水需要累加到结果
     * */
    public int trap(int[] height) {
        int res = 0;
        int left = 0,right = height.length-1;
        int leftMax = 0,rightMax = 0;
        while (left<right){
            leftMax = Math.max(height[left],leftMax);
            rightMax = Math.max(height[right],rightMax);
            if (height[left]<height[right]){
                res += (leftMax-height[left]);
                left++;
            }else{
                res += (rightMax-height[right]);
                right--;
            }
        }
        return res;
    }



    /*
    239. 滑动窗口最大值
    * */
    /**
     * 双端队列解法。
     * */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        LinkedList<Integer> queue = new LinkedList<>();
        /*第一步：先将窗口的大小扩大为k*/
        for (int i = 0; i < k; i++) {
            //队列内要求单调减。因此只要不小于队列尾就要弹出队列尾部元素
            while (!queue.isEmpty() && nums[i] >= nums[queue.peekLast()]) { /**等于的时候队尾也要出队，比如"7,7"前面的7永远不会成为窗口内的最大值了*/
                queue.pollLast();
            }
            queue.offerLast(i);
        }
        res[0] = nums[queue.peekFirst()];
        /*第二步：对于剩下的元素依次进入。由于窗口的大小固定k，因此要注意判断队列头的元素是不是已经出窗口了*/
        for (int i = k; i < nums.length; i++) {
            while (!queue.isEmpty() && nums[i] >= nums[queue.peekLast()]) {
                queue.pollLast();
            }
            queue.offerLast(i);
            if (queue.peekFirst() == i - k) { //判断队头元素是不是变得不合法
                queue.pollFirst();
            }
            res[i - k + 1] = nums[queue.peekFirst()];
        }
        return res;
    }

    /*
    41. 缺失的第一个正数
    * */
    public int firstMissingPositive(int[] nums) {
        int cur = 0;
        /*step1：将每一个数放在正确的位置*/
        while(cur<nums.length){
            if (nums[cur]>0&&nums[cur]<=nums.length&&nums[nums[cur]-1]!=nums[cur]){ //进入if说明数没有放在正确的位置
                swap(nums,nums[cur]-1,cur);
                continue; //交换来的数可能也不在正确的位置，因此cur指针不能动，下一轮循环还需要继续研究交换来的数
            }
            cur++; //走到这一步必然没有进入if，没有进入if说明nums[cur]放在正确的位置，下一轮循环继续研究下一个位置即cur++;
        }
        /*step2：从index=0开始，找第一个缺失的数*/
        for (int i=0;i<nums.length;i++){
            if (nums[i]!=i+1){ /**err：if条件语句写错。索引0处的应该是1，因此nums[i]和i+1比较*/
                return i+1; /**err：返回i+1。。求的是第一个缺失的整数，而不是索引*/
            }
        }
        return nums.length+1; /**▲err：如果所有的数都在正确位置比如：[1,2,3]。这种情况应该返回4*/
    }

    private void swap(int[] nums, int num, int cur) {
        int tmp = nums[num];
        nums[num] = nums[cur];
        nums[cur] = tmp;
    }


    /*
    25. K 个一组翻转链表
    * */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy,end = dummy;
        while(end.next!=null){ /**end是已经完成反转部分的最后一个节点即A组前面一组的最后一个节点。“end.next!=null”是保证下一组A组还有节点*/
            /*step1：先数k个节点，如果不够k个(end==null)，则结束循环，返回*/
            for (int i=0;i<k&&end!=null;i++){ //经过这一轮循环，end会来到新的一组即A组的最后一个节点
                end = end.next;
            }
            if (end==null)  break; //说明这一组不够k个节点了，因此不用动结束循环即可
            /*step2：记录几个节点。此时——————
             *      start指向这一组的第一个节点；end指向这一组的最后一个节点；pre指向这一组前面的那个节点（即前一组的最后一个节点）；next指
             *  向下一组的第一个节点*/
            ListNode start = pre.next;
            ListNode next = end.next;
            /*step3：几个节点之间调整连接关系*/
            end.next = null;
            pre.next = reverse(pre); //reverse返回的是 这一组翻转 之后的开始节点。
            start.next = next; //这一组翻转完成后，start变成了这一组的最后一个节点。。因此将start.next赋值为next，其中next为下一组的第一个节点
            /*step4：pre和end更新，更新为这一组的最后一个节点。。和最开始的“pre = dummy,end = dummy”呼应，每一轮之后状态回归到开始的样子*/
            pre = start;
            end = start;
        }
        return dummy.next;
    }

    /*翻转链表的原始代码————不变*/
    public ListNode reverse(ListNode pre){
        ListNode cur = pre.next,next = pre.next;
        while(cur!=null){
            next = next.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }


    /*
    23. 合并 K 个升序链表
    * */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists==null||lists.length==0) return null;
        if (lists.length==1) return lists[0];
        return merge(lists,0,lists.length-1);
    }

    private ListNode merge(ListNode[] lists, int left, int right) {
        if (left==right) return lists[left];
        int mid = left +(right-left)/2;
        return mergeTwoList(merge(lists,left,mid),merge(lists,mid+1,right));
    }

    private ListNode mergeTwoList(ListNode merge1, ListNode merge2) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while(merge2!=null&&merge1!=null){
            if (merge1.val<=merge2.val){
                cur.next = merge1;
                merge1 = merge1.next;
            }else{
                cur.next =merge2;
                merge2 = merge2.next;
            }
            cur = cur.next;
        }
        cur.next = merge1==null?merge2:merge1;
        return dummy.next;
    }


    /*
    32. 最长有效括号
    * */
    /**
     * 【关键】dp[i]表示以index=i位置的括号结尾的最长有效括号长度。。。暗含着一条信息：
     *       如果s.charAt(i) = '('，则dp[i]必然是0，没有任何的括号串是以左括号结尾的；因此
     *  整个过程只需要关注(或研究)当前位置是')'的时候
     *【解题步骤】整个的大前提是在s.charAt(i) = ')'这个大前提下。。
     * */
    public int longestValidParentheses1(String s) {
        int res = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = i - 2 >= 0 ? dp[i - 2] + 2 : 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1] >= 2) ? dp[i - dp[i - 1] - 2] + 2 : 2);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public int longestValidParentheses2(String s) {
        int res = 0;
        int[] dp = new int[s.length()];
        for (int i=1;i<s.length();i++){
            char c = s.charAt(i);
            if (c==')'){ /**【说明】只研究右括号的位置.。因为如果当前位置是左括号，以左括号结尾的有效括号串长度必然是0*/
                if (s.charAt(i-1)=='('){
                    dp[i] = (i>=2)?dp[i-2]+2:2;
                }else { //说明前面的一个位置也是右括号')'
                    /**err：下面的计算方式容易写错*/
                    if (i-dp[i-1]>0&&s.charAt(i-dp[i-1]-1)=='('){
                        dp[i] = (i-dp[i-1]-2>=0)?dp[i-dp[i-1]-2]+2+dp[i-1]:2+dp[i-1];
                    }
                }
                res = Math.max(res,dp[i]); //更新结果。。如果index位置不是右括号，则对应的dp[index]就是0，也不用更新最大值
            }
        }
        return res;
    }


    public static void main(String[] args) {
        _00hard thsiss = new _00hard();
//        thsiss.maxSlidingWindow(new int[]{7,7,7,6,6},3);
        thsiss.firstMissingPositive(new int[]{1,2,0});
    }
}
