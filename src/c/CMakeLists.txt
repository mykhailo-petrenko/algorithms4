cmake_minimum_required(VERSION 3.22)
project(c C)

set(CMAKE_C_STANDARD 11)

include_directories(lib)

add_executable(count_of_smaller_numbers_after_self sorting/315_count_of_smaller_numbers_after_self.c lib/utest.c lib/utest.h)

add_executable(bsearch lib/utest.c lib/utest.h search/binary_search.c)

add_executable(2379_minimum_recolors_wb lib/utest.c lib/utest.h sliding/2379_minimum_recolors_wb.c)