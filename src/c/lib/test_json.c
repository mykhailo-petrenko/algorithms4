//
// Created by Mykhailo Petrenko on 06/01/2023.
//

#include "json.h"
#include <stdio.h>
#include "utest.h"

void doTest1D(char * string) {
    printf("= doTest1D\nin: '%s'\n", string);

    int length = 0;
    int *out = json_read_array(string, &length);

    printf("Length: %d\n", length);

    print_array(out, length);
    printf("\n\n");
}


int main() {
    doTest1D("[ 1, 2, 3, 4, 5, 6 ]");
    doTest1D("[7]");
    doTest1D("[]");
    doTest1D("[1, 2,  3 , 4  ,5, 6 ]");

    return 0;
}
