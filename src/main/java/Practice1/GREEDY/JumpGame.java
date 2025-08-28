package Practice1.GREEDY;

/**
 * Description:<br>
 * Date: 07/08/25-3:49 pm
 *
 * @author ishangarg
 * @since
 *
 *  0 1 2
 * [3,0,3]
 */
public class JumpGame {
    public boolean canJump(int[] nums) {
        int lastPos = nums.length - 1;

        for (int i = nums.length - 2; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }

        return lastPos == 0;
    }
}

