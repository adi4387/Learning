package com.adruy.ds;

public class SumTwoListNode {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = null;
        ListNode temp;
        int quot;
        int sum;
        int rem = 0;
        while(l1.next != null && l2.next != null) {
            temp = new ListNode(rem);
            sum = l1.val + l2.val + temp.val;
            quot = sum / 10;
            rem = sum % 10;
            if(result == null) {
                result = new ListNode(quot);
            }
        }
        return new ListNode();
    }
}

