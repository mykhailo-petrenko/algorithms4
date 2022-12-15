//
// Created by Mykhailo Petrenko on 05/07/2022.
//

#ifndef C_UTEST_H
#define C_UTEST_H

#include <stdio.h>
#include <stdlib.h>

void assert_equals_int(char* name, int* expect, int* real);

void assert_equals_array(char* name, int* expect, int* real, int size);

void print_array(int* nums, int N);

#endif //C_UTEST_H
