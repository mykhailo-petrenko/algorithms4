cmake_minimum_required(VERSION 3.24)
project(algorithms4)

set(CMAKE_CXX_STANDARD 17)

#find_package(glm REQUIRED)
#find_package(spdlog REQUIRED)

add_executable(${PROJECT_NAME}
        hellocpp.cpp
        trees/kd/kd_tree.cpp trees/kd/kd_tree.h)

add_executable(433_Minimum_Genetic_Mutations
        trees/433.Minimum_Genetic_Mutations.cpp)

#target_link_libraries(${EXE} glm::glm spdlog::spdlog)

