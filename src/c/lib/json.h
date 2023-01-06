//
// Created by Mykhailo Petrenko on 06/01/2023.
//

#ifndef C_JSON_H
#define C_JSON_H

int *json_read_array(char *string, int *length);
int **json_read_2d_array(char *string, int *length, int **columnSize);

#endif //C_JSON_H
