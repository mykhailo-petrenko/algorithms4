//
// Created by Mykhailo Petrenko on 05/07/2022.
//

#ifndef C_UTEST_H
#define C_UTEST_H

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

void assert_equals_bool(const char* name, const bool* expect, const bool* real);

/**
 * Assert Equals int
 *
 * @param name {char*} String to show in test messate
 * @param expect Expected result
 * @param real Actual result
 */
void assert_equals_int(const char* name, const int* expect, const int* real);

void assert_equals_array(const char* name, const int* expect, const int* real, int size);

void print_array(const int* nums, int length);
void print_2d_array(const int** nums, int rows, int *columns);

typedef struct {
    int **arr;
    int rowsSize;
    int *columnsSize;
} ArrayPointer;
ArrayPointer * arrayToPointer(const int* in, const int rowNumbers, const int rowNumbers_1);

#endif //C_UTEST_H
