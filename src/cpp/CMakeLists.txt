cmake_minimum_required(VERSION 3.21)
project(algorithms4)

set(CMAKE_CXX_STANDARD 14)

################## Conan bootstrap ###############
# see https://blog.conan.io/2018/06/11/Transparent-CMake-Integration.html
# auto-run conan https://github.com/conan-io/cmake-conan
list(APPEND CMAKE_MODULE_PATH ${CMAKE_CURRENT_SOURCE_DIR}/cmake-modules)
list(APPEND CMAKE_MODULE_PATH ${CMAKE_BINARY_DIR})
list(APPEND CMAKE_PREFIX_PATH ${CMAKE_BINARY_DIR})

if (NOT EXISTS "${CMAKE_BINARY_DIR}/conan.cmake")
message(STATUS "Downloading conan.cmake from https://github.com/conan-io/cmake-conan")
file(DOWNLOAD "https://raw.githubusercontent.com/conan-io/cmake-conan/v0.16.1/conan.cmake"
"${CMAKE_BINARY_DIR}/conan.cmake"
TLS_VERIFY ON)
endif ()


include(${CMAKE_BINARY_DIR}/conan.cmake)
conan_check(VERSION 1.40.0 REQUIRED)
conan_cmake_autodetect(settings)

# Make configuration file from template before conan so it could see local libs
include_directories(${PROPRIETARY_COMMON})
link_directories(${CMAKE_LIBRARY_OUTPUT_DIRECTORY})

set(CONAN_REBUILD missing)

conan_cmake_run(CONANFILE "./conanfile.txt"
BUILD_TYPE ${CMAKE_BUILD_TYPE}
BASIC_SETUP
BUILD missing ${CONAN_REBUILD}
SETTINGS ${settings}
)

##############################  END COANAN bottstrap

#find_package(glm REQUIRED)
#find_package(spdlog REQUIRED)

set(EXE hellocpp)

add_executable(${EXE}
        hellocpp.cpp
    )

#target_link_libraries(${EXE} glm::glm spdlog::spdlog)

