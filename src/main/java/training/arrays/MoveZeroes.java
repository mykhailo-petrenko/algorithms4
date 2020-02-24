package training.arrays;

/**
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 *
 * Example:
 * Input: [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 *
 * Note:
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 */
public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        int zeros = 0;

        if (nums[0]==0) {
            zeros = 1;
        }

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeros++;
                continue;
            }

            if (zeros > 0) {
                swap(nums, i, i-zeros);
            }
        }

        for (int i = nums.length - zeros; i<nums.length; i++) {
            nums[i] = 0;
        }
    }

    public void swap(int[] nums, int a, int b) {
        int buff = nums[a];
        nums[a] = nums[b];
        nums[b] = buff;
    }

    public static void main(String[] args) {
        int [] input = new int[]{0, 1, 0, 3, 12};

        MoveZeroes solution = new MoveZeroes();

        solution.moveZeroes(input);

        System.out.println(input);
    }
}
