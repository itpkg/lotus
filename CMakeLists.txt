CMAKE_MINIMUM_REQUIRED(VERSION 3.6.2)
PROJECT(lotus CXX)
SET (LOTUS_VERSION_MAJOR 1)
SET (LOTUS_VERSION_MINOR 0)

SET (CMAKE_CXX_FLAGS_RELEASE "${CMAKE_CXX_FLAGS_RELEASE} -s")

file(GLOB SRCS
  RELATIVE "${CMAKE_CURRENT_SOURCE_DIR}/"
  "src/*.cc"
  "src/**/*.cc"
  "src/**/**/*.cc"
)

file(GLOB TEST_SRCS
  RELATIVE "${CMAKE_CURRENT_SOURCE_DIR}"
  "src/*_test.cpp"
  "src/**/*_test.cpp"
  "src/**/**/*_test.cpp"
)

#-------boost--------------------------
find_package(Boost 1.60 REQUIRED)
ADD_DEFINITIONS(-DBOOST_LOG_DYN_LINK)
ADD_DEFINITIONS(-DBOOST_LOG_USE_NATIVE_SYSLOG)

find_package(mstch REQUIRED)
#------links----------------------------
set(LINKS
  pthread
  ssl
  crypto

  hiredis
  pq
  sodium
  fcgi
  fcgi++

  boost_program_options
  boost_filesystem
  boost_system
  boost_log
  boost_thread
  boost_log_setup
  boost_date_time
)

add_executable(lotus ${SRCS})
TARGET_COMPILE_FEATURES(lotus PRIVATE cxx_range_for)
TARGET_LINK_LIBRARIES(lotus ${LINKS} )


#--------------testing-----------------
enable_testing()
find_package(GTest REQUIRED)
add_executable(unit_tests ${TEST_SRCS} ${SRCS})
target_compile_features(unit_tests PRIVATE cxx_range_for)
target_link_libraries(unit_tests ${LINKS} GTest::GTest GTest::Main)

add_test(runUnitTests unit_tests)
