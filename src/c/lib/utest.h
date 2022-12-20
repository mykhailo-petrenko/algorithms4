//
// Created by Mykhailo Petrenko on 05/07/2022.
//

#ifndef C_UTEST_H
#define C_UTEST_H

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

void assert_equals_bool(const char* name, const bool* expect, const bool* real);

void assert_equals_int(const char* name, const int* expect, const int* real);

void assert_equals_array(const char* name, const int* expect, const int* real, int size);

void print_array(const int* nums, int N);

#endif //C_UTEST_H
