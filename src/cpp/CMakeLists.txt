cmake_minimum_required(VERSION 3.24)
project(algorithms4)

set(CMAKE_CXX_STANDARD 17)

#find_package(glm REQUIRED)
#find_package(spdlog REQUIRED)

add_executable(${PROJECT_NAME}
        hellocpp.cpp
        trees/kd/kd_tree.cpp trees/kd/kd_tree.h)

#target_link_libraries(${EXE} glm::glm spdlog::spdlog)

