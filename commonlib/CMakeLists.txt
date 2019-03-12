cmake_minimum_required(VERSION 3.4.1)

add_library(
    sc_tool
    SHARED
    src/main/cpp/sc_tool.cpp
)


include_directories(src/main/cpp/include/)