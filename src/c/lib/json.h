//
// Created by Mykhailo Petrenko on 06/01/2023.
//

#ifndef C_JSON_H
#define C_JSON_H

/**
 * Read 1d json array like [1,2,3]
 *
 * @param string Input string
 * @param length Length of array
 * @return int* Pointer to array
 */
int *json_read_array(char *string, int *length);
int **json_read_2d_array(char *string, int *length, int **columnSize);

#endif //C_JSON_H
