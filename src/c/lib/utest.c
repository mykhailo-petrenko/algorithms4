//
// Created by Mykhailo Petrenko on 05/07/2022.
//

#include "utest.h"

#define ANSI_RESET_ALL          "\x1b[0m"
#define ANSI_COLOR_RED          "\x1b[31m"
#define ANSI_COLOR_GREEN        "\x1b[32m"

void assert_equals_bool(const char* name, const bool* expect, const bool* real) {
    printf(">> %s: ", name);

    if (*expect != *real) {
        printf(ANSI_COLOR_RED "Error: Expected '%d' but got the '%d' \n" ANSI_RESET_ALL, *expect, *real);
        printf(ANSI_RESET_ALL "\n");
        exit(1);
    }

    printf(ANSI_COLOR_GREEN "+ PASSED! '%d' != '%d'\n" ANSI_RESET_ALL, *expect, *real);
}

/**
 * Assert Equals int
 *
 * @param name {char*} String to show in test messate
 * @param expect Expected result
 * @param real Actual result
 */
void assert_equals_int(const char* name, const int* expect, const int* real) {
    printf(">> %s: ", name);

    if (*expect != *real) {
        printf(ANSI_COLOR_RED "Error: Expected '%d' but got the '%d' \n" ANSI_RESET_ALL, *expect, *real);
        printf(ANSI_RESET_ALL "\n");
        exit(1);
    }

    printf(ANSI_COLOR_GREEN "+ PASSED! '%d' == '%d'\n" ANSI_RESET_ALL, *expect, *real);
}

/**
 * Assert Equals Long
 *
 * @param name {char*} String to show in test messate
 * @param expect Expected result
 * @param real Actual result
 */
void assert_equals_long(const char* name, const long long * expect, const long long * real) {
    printf(">> %s: ", name);

    if (*expect != *real) {
        printf(ANSI_COLOR_RED "Error: Expected '%lld' but got the '%lld' \n" ANSI_RESET_ALL, *expect, *real);
        printf(ANSI_RESET_ALL "\n");
        exit(1);
    }

    printf(ANSI_COLOR_GREEN "+ PASSED! '%lld' == '%lld'\n" ANSI_RESET_ALL, *expect, *real);
}

void assert_equals_array(const char* name, const int* expect, const int* real, int size) {
    printf(">> %s: ", name);

    int n = size;
    const int* expectedCursor = expect;
    const int* realCursor = real;

    while(n-- > 0) {
        if (*expectedCursor != *realCursor) {
            printf(ANSI_COLOR_RED "Error: '%d' != '%d' \n" ANSI_RESET_ALL, *expectedCursor, *realCursor);
            print_array(expect, size);
            printf(" != " ANSI_COLOR_RED);
            print_array(real, size);
            printf(ANSI_RESET_ALL "\n");
            exit(1);
        }

        expectedCursor++;
        realCursor++;
    }

    printf(ANSI_COLOR_GREEN "+ PASSED!\n" ANSI_RESET_ALL);
}

/**
 * print 1D Array
 * @param nums {int *} pointer to the array
 * @param length {int} array length
 */
void print_array(const int* nums, int length) {
    printf("[");

    while(length-- > 0) {
        printf("%d", *nums++);
        if (length != 0) {
            printf(", ");
        }
    }

    printf("]");
}

/**
 * Print 2D Array
 * @param nums {int**} pointer to the 2D array
 * @param rows {int} rows count
 * @param columns {int*} columns count per each row - columns[rowId]
 */
void print_2d_array(const int** nums, int rows, int *columns) {
    printf("[");

    for (int row=0; row < rows; row++) {
        printf("\n");
        print_array(nums[row], columns[row]);
    }

    printf("\n]\n");
}

ArrayPointer * arrayToPointer(const int* in, const int rowNumbers, const int columnsNumber) {
    ArrayPointer * pointer = malloc(sizeof(ArrayPointer));

    pointer->arr = malloc(sizeof(int *) * rowNumbers);
    pointer->rowsSize = rowNumbers;
    pointer->columnsSize = malloc(sizeof(int) * rowNumbers);

    for (unsigned r = 0; r < rowNumbers; ++r) {
        pointer->arr[r] = malloc(sizeof(int) * columnsNumber);

        for (unsigned c = 0; c < columnsNumber; ++c) {
            pointer->arr[r][c] = in[r * columnsNumber + c];
        }

        pointer->columnsSize[r] = columnsNumber;
    }

    return pointer;
}
