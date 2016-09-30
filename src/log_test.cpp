#include "log.hpp"

#include <gtest/gtest.h>

TEST(log, file) {
  lotus::log::use_native_syslog_backend();
  BOOST_LOG_TRIVIAL(info) << "test file backend ";
}

TEST(log, syslog) {
  lotus::log::use_file_backend("test");
  BOOST_LOG_TRIVIAL(info) << "test syslog backend";
}
