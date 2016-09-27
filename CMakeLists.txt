CMAKE_MINIMUM_REQUIRED(VERSION 3.6.2)
PROJECT(lotus CXX)
SET (LOTUS_VERSION_MAJOR 1)
SET (LOTUS_VERSION_MINOR 0)

SET (CMAKE_CXX_FLAGS_RELEASE "${CMAKE_CXX_FLAGS_RELEASE} -s")

#-------boost--------------------------
find_package(Boost 1.60 REQUIRED)
ADD_DEFINITIONS(-DBOOST_LOG_DYN_LINK)
ADD_DEFINITIONS(-DBOOST_LOG_USE_NATIVE_SYSLOG)

set(LIB_LINKS
  pthread
  ssl
  crypto

  hiredis
  pq

  boost_program_options
  boost_filesystem
  boost_system
  boost_log
  boost_thread
  boost_log_setup
  boost_date_time
)

AUX_SOURCE_DIRECTORY(src DIR_SRCS)
ADD_EXECUTABLE(lotus ${DIR_SRCS})
TARGET_COMPILE_FEATURES(lotus PRIVATE cxx_range_for)
TARGET_LINK_LIBRARIES(lotus ${LIB_LINKS} )
