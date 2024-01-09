## Prerequisites
[Conan 2 tutorial](https://docs.conan.io/2/tutorial/consuming_packages/build_simple_cmake_project.html)

Conan 2.x
CMake >= 3.24

```bash
brew install conan
brew install cmake

conan profile detect --force
conan install . --output-folder=build --build=missing
```