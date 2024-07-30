cmake_minimum_required(VERSION 3.24)
project(algorithms4)

set(CMAKE_CXX_STANDARD 17)

#find_package(glm REQUIRED)
#find_package(spdlog REQUIRED)

add_executable(1395_Count_Number_of_Teams
        dp/1395_Count_Number_of_Teams.cpp
)

add_executable(1334_Find_the_City_With_the_Smallest_Number_of_Neighbors_at_a_Threshold_Distance
        graph/1334_Find_the_City_With_the_Smallest_Number_of_Neighbors_at_a_Threshold_Distance.cpp)

add_executable(${PROJECT_NAME}
        hello_cpp.cpp
        trees/kd/kd_tree.cpp trees/kd/kd_tree.h)

add_executable(433_Minimum_Genetic_Mutations
        trees/433.Minimum_Genetic_Mutations.cpp)

add_executable(912_Sort_an_Array
        sorting/912_Sort_an_Array.cpp)

#target_link_libraries(${EXE} glm::glm spdlog::spdlog)

