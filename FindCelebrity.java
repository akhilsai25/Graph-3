/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */
// This solution uses two level apprach. First we find a potential candidate who is a celebrity by checking any potential celebrity iteratively who does not know anyone
// Next step we reverse check for previous persons if this potential celebrity knows anyone else and also at the same time check if there is any one who does not know this celebrity
public class Solution extends Relation {
    public int findCelebrity(int n) {
        int celebrity = 0;
        for(int i=1;i<n;i++) {
            if(knows(celebrity, i)) {
                celebrity = i;
            }
        }

        for(int i=0;i<n;i++) {
            if(i<celebrity) {
                if(knows(celebrity, i) || !knows(i, celebrity)) return -1;
            } else {
                if(!knows(i, celebrity)) return -1;
            }
        }
        return celebrity;
     }
}

