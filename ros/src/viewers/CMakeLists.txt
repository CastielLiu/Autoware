cmake_minimum_required(VERSION 2.8.3)
project(viewers)


if(CMAKE_CXX_COMPILER_ID STREQUAL "GNU")
  set(CMAKE_SHARED_LINKER_FLAGS "${CMAKE_SHARED_LINKER_FLAGS} -Wl,--no-undefined")
elseif(CMAKE_CXX_COMPILER_ID MATCHES "Clang")
  set(CMAKE_SHARED_LINKER_FLAGS "${CMAKE_SHARED_LINKER_FLAGS} -Wl,-undefined,error")
endif()

# Enable support for C++11
if(${CMAKE_VERSION} VERSION_LESS "3.1.0")
  set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")
else()
  set(CMAKE_CXX_STANDARD 11)
endif()


find_package(catkin REQUIRED COMPONENTS
        roscpp
        sensor_msgs
        std_msgs
        autoware_msgs
        cv_bridge
        image_transport
        )

find_package(OpenCV REQUIRED)
find_package(OpenGL REQUIRED)

catkin_package(
        CATKIN_DEPENDS roscpp sensor_msgs std_msgs autoware_msgs cv_bridge image_transport
)

set(CMAKE_CXX_FLAGS "-O2 -Wall ${CMAKE_CXX_FLAGS}")


add_executable(image_viewer nodes/image_viewer/image_viewer.cpp)
target_link_libraries(image_viewer
        ${catkin_LIBRARIES}
        ${OpenCV_LIBRARIES}
        ${OPENGL_LIBRARIES}
        )
add_dependencies(image_viewer ${catkin_EXPORTED_TARGETS})


include_directories(
        ${catkin_INCLUDE_DIRS}
        include
)


install(TARGETS
        image_viewer
        ARCHIVE DESTINATION ${CATKIN_PACKAGE_LIB_DESTINATION}
        LIBRARY DESTINATION ${CATKIN_PACKAGE_LIB_DESTINATION}
        RUNTIME DESTINATION ${CATKIN_GLOBAL_BIN_DESTINATION})
