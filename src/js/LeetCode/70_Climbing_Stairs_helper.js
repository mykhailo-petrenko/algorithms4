/**
 * Climbing Stairs Naive implementation
 * 
 * @param {*} n 
 * @returns 
 */
var climbStairsN = function(n) {
    
    const climb = (n) => {
        if (n == 0) {
            return 1;
        } else if (n < 0 ) {
            return 0;
        }

        return climb(n-1) + climb(n-2);
    };

    return climb(n);
};

// Close look allow us to see that climbing stairs looks like fibonachi number
// restriction is 1 <= n <= 45. We can basically generate 45 answers =)))) 
// or implement clasic fibonachi calc function via recursion with memoisation
const a = [];
for (let i=0; i <= 30; i++) {
    a.push(climbStairsN(i));
    console.log(i, a[i]);
}

console.log(JSON.stringify(a));


/**
 * @param {number} n
 * @return {number}
 */
var climbStairs = function(n) {

    if (climbStairs.cache[n] == undefined) {
        climbStairs.cache[n] = climbStairs(n-1) + climbStairs(n-2);
    }

    return climbStairs.cache[n];
};

climbStairs.cache = [1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987,1597,2584,4181,6765,10946,17711,28657,46368,75025,121393,196418,317811,514229,832040,1346269];