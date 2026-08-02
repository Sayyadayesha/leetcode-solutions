/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {

        // Head se traversal start karo
        ListNode current = head;

        // Jab tak current aur uska next exist karta hai
        while (current != null && current.next != null) {

            // Agar current aur next ki value same hai
            if (current.val == current.next.val) {

                // Duplicate node ko skip kar do
                current.next = current.next.next;

            } else {

                // Duplicate nahi hai, aage badho
                current = current.next;
            }
        }

        // Updated linked list return
        return head;
    }
}