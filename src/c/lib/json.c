//
// Created by Mykhailo Petrenko on 06/01/2023.
//

#include "json.h"

#include <string.h>
#include <stdlib.h>

/**
 * Read 1d json array like [1,2,3]
 * @param string Input string
 * @param length Length of array
 * @return int* Pointer to array
 */
int * json_read_array(char *string, int *length) {
    size_t N = strlen(string) + 1;

    if (N <= 3) return NULL;

    char * input = malloc(sizeof(char) * N);
    strcpy(input, string);

    int i = 0;
    int * out = malloc(sizeof(int) * N);

    char *token = strtok(input, "[");
    if (token) {
        token = strtok(token, ",");
    }
    while(token) {
        out[i++] = (int)strtol(token, (char **)NULL, 10);;
        token = strtok(NULL, ",");
    }

    *length = i;

    return realloc(out, sizeof(int) * (*length));
}

int **json_read_2d_array(char *string, int *length, int **columnSize) {
    return NULL;
}
