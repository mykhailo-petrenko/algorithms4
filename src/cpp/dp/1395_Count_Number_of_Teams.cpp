//
// Created by Mykhailo Petrenko on 29/07/2024.
//
/**
 * LeetCode. 1395. Count Number of Teams (https://leetcode.com/problems/count-number-of-teams/description/)
 * There are n soldiers standing in a line. Each soldier is assigned a unique rating value.
 *
 * You have to form a team of 3 soldiers amongst them under the following rules:
 * - Choose 3 soldiers with index (i, j, k) with rating (rating[i], rating[j], rating[k]).
 * - A team is valid if: (rating[i] < rating[j] < rating[k]) or (rating[i] > rating[j] > rating[k]) where (0 <= i < j < k < n).
 *
 * Return the number of teams you can form given the conditions. (soldiers can be part of multiple teams).
 *
*/

#import "vector"
#import "cassert"
#import "iostream"

using namespace std;

class Solution {
public:
    int numTeams(vector<int>& rating) {
        int n = rating.size();
        int teams = 0;

        // Tables for increasing and decreasing sequences
        // Dimension Nx4 and fill by zeroes.
        // 4 because 0-indexed array, but we interested in 1, 2, 3 (count)
        vector<vector<int>> increasingTeams(n, vector<int>(4, 0));
        vector<vector<int>> decreasingTeams(n, vector<int>(4, 0));

        // Fill the base cases. Each soldier is a sequence of length 1.
        for (int i = 0; i < n; i++) {
            increasingTeams[i][1] = 1;
            decreasingTeams[i][1] = 1;
        }

        // Fill the tables for cases count in (2,3)
        for (int count = 2; count <= 3; count++) {
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    // total teams which soldier can attend with higher rank

                    if (rating[j] > rating[i]) {
                        increasingTeams[j][count] += increasingTeams[i][count - 1];
                    }

                    if (rating[j] < rating[i]) {
                        decreasingTeams[j][count] += decreasingTeams[i][count - 1];
                    }

                }
            }
        }

        // Sum up the results
        for (int i = 0; i < n; i++) {
            // All sequences of length 3
            teams += increasingTeams[i][3] + decreasingTeams[i][3];
        }

        return teams;
    }
};


int main() {
    cout << "# 1395. Count Number of Teams" << endl;

    Solution solution;
    int actual;

    vector<int> rating1 = {2,5,3,4,1};
    actual = solution.numTeams(rating1);
    assert(actual == 3);
    cout << "Test 1: " << actual << endl;

    vector<int> rating2 = {2,1,3};
    actual = solution.numTeams(rating2);
    assert(actual == 0);
    cout << "Test 2: " << actual << endl;

    vector<int> rating3 = {1,2,3,4};
    actual = solution.numTeams(rating3);
    assert(actual == 4);
    cout << "Test 3: " << actual << endl;
}