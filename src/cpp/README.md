# Algorithms and coding problems C++ practice playground

## Leetcode tips
This disables the synchronization between the C and C++ standard streams. 
Then unties `cin` from `cout` and `cout` from `cin`.
```c++
ios_base::sync_with_stdio(false);
cin.tie(nullptr);
cout.tie(nullptr);
```

## Prerequisites (or how to run the project)
[Conan 2 tutorial](https://docs.conan.io/2/tutorial/consuming_packages/build_simple_cmake_project.html)

Conan 2.x
CMake >= 3.24

```bash
brew install conan
brew install cmake

conan profile detect --force
conan install . --output-folder=build --build=missing
```