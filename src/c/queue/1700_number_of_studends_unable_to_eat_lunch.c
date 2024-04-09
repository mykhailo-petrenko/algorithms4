/**
# LeetCode. 1700. Number of Students Unable to Eat Lunch (https://leetcode.com/problems/number-of-students-unable-to-eat-lunch/description/?envType=daily-question&envId=2024-04-08)

The school cafeteria offers circular and square sandwiches at lunch break, referred to by numbers 0 and 1 respectively.
All students stand in a queue. Each student either prefers square or circular sandwiches.

The number of sandwiches in the cafeteria is equal to the number of students. The sandwiches are placed in a stack. At each step:

If the student at the front of the queue prefers the sandwich on the top of the stack, they will take it and leave the queue.
Otherwise, they will leave it and go to the queue's end.
This continues until none of the queue students want to take the top sandwich and are thus unable to eat.

You are given two integer arrays students and sandwiches where sandwiches[i] is the type of the ith sandwich in the stack
 (i = 0 is the top of the stack) and students[j] is the preference of the jth student in the initial queue (j = 0 is the front of the queue).
 Return the number of students that are unable to eat.

## Example 1:
    Input: students = [1,1,0,0], sandwiches = [0,1,0,1]
    Output: 0
    Explanation:
    - Front student leaves the top sandwich and returns to the end of the line making students = [1,0,0,1].
    - Front student leaves the top sandwich and returns to the end of the line making students = [0,0,1,1].
    - Front student takes the top sandwich and leaves the line making students = [0,1,1] and sandwiches = [1,0,1].
    - Front student leaves the top sandwich and returns to the end of the line making students = [1,1,0].
    - Front student takes the top sandwich and leaves the line making students = [1,0] and sandwiches = [0,1].
    - Front student leaves the top sandwich and returns to the end of the line making students = [0,1].
    - Front student takes the top sandwich and leaves the line making students = [1] and sandwiches = [1].
    - Front student takes the top sandwich and leaves the line making students = [] and sandwiches = [].
    Hence all students are able to eat.

## Example 2:
    Input: students = [1,1,1,0,0,1], sandwiches = [1,0,0,0,1,1]
    Output: 3


## Constraints:
 - 1 <= students.length, sandwiches.length <= 100
 - students.length == sandwiches.length
 - sandwiches[i] is 0 or 1.
 - students[i] is 0 or 1.

*/

#include "utest.h"
#include "json.h"

int countStudents(int* students, int studentsSize, const int* sandwiches, int sandwichesSize) {
    int sandwich;

    // lets use cycle buffer
    int start = 0;
    int end = studentsSize - 1;
    int counter = 0;

    for (int sandwichesCursor = 0; sandwichesCursor < sandwichesSize; sandwichesCursor++) {
        sandwich = sandwiches[sandwichesCursor];

        // match the student
        while(start <= end) {
            if (students[start % studentsSize] == sandwich) {
                // student left the "queue"
                start++;
                counter = 0;
                break;
            } else {
                // move student to the end of a "queue"
                end++;
                students[end % studentsSize] = students[start % studentsSize];
                start++;
                counter++;
            }

            if ((end - start) < counter) {
                return (end - start) + 1;
            }
        }
    }


    return 0;
}

void doTest(char * students, char * sandwiches, int expected) {
    const int MAX_TEST_NAME = 1024;

    int studentsLength = 0;
    int * studentsArray = json_read_array(students, &studentsLength);

    int sandwichesLength = 0;
    int * sandwichesArray = json_read_array(sandwiches, &sandwichesLength);

    int actual = countStudents(studentsArray, studentsLength, sandwichesArray, sandwichesLength);

    char * name = malloc(sizeof(name) * MAX_TEST_NAME);

    sprintf(name, "countStudents(%s, %s) -> %d", students, sandwiches, expected);

    assert_equals_int(name, &expected, &actual);
}

int main() {
    doTest("[1,1,0,0]", "[0,1,0,1]", 0);

    doTest("[1,1,1,0,0,1]", "[1,0,0,0,1,1]", 3);

    doTest("[0,0,1,1,0,0,0,0,1,0,0,1,1,0,1,1]", "[1,0,0,0,0,0,0,1,0,0,1,0,1,1,1,0]", 1);
}
