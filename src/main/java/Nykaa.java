//given a binary tree root, we need to return true if any path exists starting from root of tree, that has a path sum equal to target sum all nodes are having natural numbers
//
//
//        5(1)
//        /    \
//        4(2)      8(3)
//        /           /  \
//        11(4)      5(5)   5(6)
//        /
//        18(7)
//
//        bool checkPath(Node* n,int targetSum);
//
//        targetSum = 18
//        5 + 8 + 5
//
//        targetSum = 17
//
//
//        bool checkPath(Node n,int targetSum)
//        {
//
//        return checkPathSum(n,target,0);
//
//        }
//
//        bool checkPathSum(Node n,int targetSum,int sum)
//        {
//
//        if(targetSum==sum)
//        {
//        return true;
//        }
//
//        if(n==null)
//        {
//        return false;
//        }
//
//
//        if(sum>target)
//        {
//        return false;
//        }
//
//        if(checkPathSum(n.left,targetSum,sum+n.data))
//        {
//        return true;
//        }
//
//        if(checkPathSum(n.right,targetSum,sum+n.data))
//        {
//        return true;
//        }
//
//        return false;
//
//        }
//
//
//
//        given a binary matrix, we need to return distance of nearest 0 for each cell
//        up, down, left and right
//
//        0 0 0
//        0 1 0
//        1 1 1
//
//
//        0 0 0
//        0 1 0
//        1 2 1
//
//
//
//public int[][] binaryMartix(int a[][])
//        {
//
//        int b[][]=new int[a.length][a[0].length];
//
//        for(int i=0;i<a.length;i++)
//        {
//        for(int j=0;j<a[i].length;j++)
//        {
//        int sum=calculateShortestDistance(a,i,j,0);
//        b[i][j]=sum;
//        }
//        }
//
//        return b;
//
//        }
//
//
//
//public calculateShortestDistance(int a[][],int i,int j,int sum)
//        {
//        if(i>a.length)
//        {
//        return i;
//        }
//
//        if(j>a[i].length)
//        {
//        return j;
//        }
//
//        if(a[i][j]==0)
//        {
//        return 0;
//        }
//
//
//        if(calculateShortestDistance(a[i-1][j],i-1,j,sum)==0 || calculateShortestDistance(a[i+1][j],i+1,j,sum)==0 || calculateShortestDistance(a[i][j-1],i,j-1,sum)==0 || calculateShortestDistance(a[i][j+1],i,j+1,sum)==0)
//        {
//        sum=sum+1;
//        }
//
//        }
//
//
//
//        1 1 1 1
//        1 1 1 1
//        1 1 1 0
//
//        0,0 -> 1,0 -> 2,0 > 2,1 -> 1,2 -> 2,2 -> 2,3
//
//
//
//
//
