// This solution assumes that the node to itself or well at a node is equivaluent to get pipe from another node which we can assume as an imaginary node -0
// Then this solution will boil down to MST. This solution uses kruskals algorithm to find the minimum cost in connecting all nodes without cycles
// We first form all the edges including node 0
// We keep forming union of all the nodes by picking the smallest possible edge so far
// We keep adding to min cost if both nodes or disjoint otherwise not
class Solution {
    int minCost = 0;
    int[] union;
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        int[][] edges = new int[pipes.length+wells.length][3];
        for(int i=0;i<pipes.length;i++) {
            edges[i] = pipes[i];
        }
        for(int i=pipes.length;i<pipes.length+wells.length;i++) {
            int[] temp = new int[3];
            temp[0] = 0;
            temp[1] = i-pipes.length+1;
            temp[2] = wells[i-pipes.length];
            edges[i] = temp;
        }

        // Sort the array
        Arrays.sort(edges, (a,b)-> Integer.compare(a[2], b[2]));

        union = new int[n+1];
        for(int i=0;i<n+1;i++) {
            union[i] = i;
        }

        for(int i=0;i<edges.length;i++) {
            int[] temp = edges[i];
            boolean unionStatus = union(temp[0], temp[1]);
            if(unionStatus) {
                minCost+=temp[2];
            }
        }
        return minCost;
    }

    private boolean union(int i, int j) {
        int rootA = find(i);
        int rootB = find(j);
        if(rootA!=rootB) {
            union[rootA]=rootB;
            return true;
        }
        return false;
    }

    private int find(int i) {
        if(union[i]!=i) {
            union[i] = find(union[i]);
            return union[i];
        }
        return i;
    }
}
