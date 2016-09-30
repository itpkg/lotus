#include "web.hpp"
#include <boost/program_options.hpp>
#include <iostream>

namespace po = boost::program_options;

namespace lotus {
namespace web {
int App::main(int argc, const char *argv[]) {
  po::options_description desc("Generic options");
  desc.add_options()("config", po::value<std::string>(&std::string)->{},
                     "configuration file")("help", "pring help message")(
      "version", "pring version")("compression", po::value<int>(),
                                  "set compression level");

  po::variables_map vm;
  po::store(po::parse_command_line(argc, argv, desc), vm);
  po::notify(vm);

  if (vm.count("help")) {
    std::cout << desc << "\n";
    return 1;
  }

  if (vm.count("compression")) {
    std::cout << "Compression level was set to " << vm["compression"].as<int>()
              << ".\n";
  } else {
    std::cout << "Compression level was not set.\n";
  }
}
}
}
