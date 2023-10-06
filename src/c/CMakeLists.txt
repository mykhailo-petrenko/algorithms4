cmake_minimum_required(VERSION 3.21)
project(c C)

set(CMAKE_C_STANDARD 11)

include_directories(lib)

add_executable(343_Integer_Break lib/utest.h lib/utest.c dp/343_Integer_Break.c)
add_executable(1137_Nth_Tribonacci lib/utest.h lib/utest.c dp/1137_Nth_Tribonacci.c)

add_executable(417_Pacific_Atlantic_Water_Flow lib/utest.h lib/utest.c graph/417_Pacific_Atlantic_Water_Flow.c)

add_executable(706_Design_HashMap lib/utest.h lib/utest.c hash_map/706_Design_HashMap.c)

add_executable(heap_min_in heap/heap_min_int.c)
add_executable(heap_struct heap/heap_struct.c)

add_executable(test_json lib/utest.h lib/utest.c lib/json.h lib/json.c lib/test_json.c)

add_executable(count_of_smaller_numbers_after_self sorting/315_count_of_smaller_numbers_after_self.c lib/utest.c lib/utest.h)

add_executable(19_Remove_Nth_Node_From_End_of_List lib/utest.c lib/utest.h linked_list/19_Remove_Nth_Node_From_End_of_List.c)

add_executable(queue_array lib/utest.c lib/utest.h queue/queue_array.c)

add_executable(bsearch lib/utest.c lib/utest.h search/binary_search.c)

add_executable(424_Longest_Repeating_Character_Replacement lib/utest.c lib/utest.h sliding/424_Longest_Repeating_Character_Replacement.c)
add_executable(1297_Maximum_Number_of_Occurrences_of_a_Substring lib/utest.c lib/utest.h sliding/1297_Maximum_Number_of_Occurrences_of_a_Substring.c)
add_executable(1695_Maximum_Erasure_Value lib/utest.c lib/utest.h sliding/1695_Maximum_Erasure_Value.c)
add_executable(2269_k_beauty_of_a_number lib/utest.c lib/utest.h sliding/2269_k_beauty_of_a_number.c)
add_executable(2379_minimum_recolors_wb lib/utest.c lib/utest.h sliding/2379_minimum_recolors_wb.c)


add_executable(959_Regions_Cut_By_Slashes lib/utest.c lib/utest.h uf/959_Regions_Cut_By_Slashes.c)
add_executable(1584_Min_Cost_to_Connect_All_Points lib/utest.c lib/utest.h uf/1584_Min_Cost_to_Connect_All_Points.c)
add_executable(1905_Count_Sub_Islands lib/utest.c lib/utest.h uf/1905_Count_Sub_Islands.c)
add_executable(1971_find_existing_path lib/utest.c lib/utest.h uf/1971_find_existing_path.c)


add_executable(trie string/trie.c)
add_executable(uniq_letters_count string/uniq_letters_count.c)
