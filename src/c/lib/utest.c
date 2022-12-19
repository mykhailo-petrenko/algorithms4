//
// Created by Mykhailo Petrenko on 05/07/2022.
//

#include "utest.h"

#define ANSI_RESET_ALL          "\x1b[0m"
#define ANSI_COLOR_RED          "\x1b[31m"
#define ANSI_COLOR_GREEN        "\x1b[32m"

void assert_equals_int(const char* name, const int* expect, const int* real) {
    printf(">> %s: ", name);

    if (*expect != *real) {
        printf(ANSI_COLOR_RED "Error: Expected '%d' but got the '%d' \n" ANSI_RESET_ALL, *expect, *real);
        printf(ANSI_RESET_ALL "\n");
        exit(1);
    }

    printf(ANSI_COLOR_GREEN "+ PASSED! '%d' == '%d'\n" ANSI_RESET_ALL, *expect, *real);
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

void print_array(const int* nums, int N) {
    printf("[");

    while(N-- > 0) {
        printf("%d", *nums++);
        if (N != 0) {
            printf(", ");
        }
    }

    printf("]");
}