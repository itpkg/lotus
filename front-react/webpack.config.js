const path = require('path')
const webpack = require('webpack')
const HtmlWebpackPlugin = require('html-webpack-plugin')
const CleanWebpackPlugin = require('clean-webpack-plugin')
const ExtractTextPlugin = require('extract-text-webpack-plugin')
const StatsPlugin = require('stats-webpack-plugin')
const SriPlugin = require('webpack-subresource-integrity')

const SRC_ROOT = 'app'

module.exports = function (options) {
  var entry = {
    app: path.join(__dirname, SRC_ROOT)
  }

  entry.vendor = [
    'react',
    'react-bootstrap',
    'react-dom',
    'react-router',
    'react-redux',
    'react-router-redux',
    'react-timeago',
    'react-markdown',
    'react-tap-event-plugin',
    'react-i18next',

    'jwt-decode',

    'i18next',
    'i18next-browser-languagedetector'
  ]

  var plugins = []
  var loaders = [{
    test: /\.jsx?$/,
    exclude: /(node_modules)/,
    loader: 'babel'
  }, {
    test: /\.(png|jpg|jpeg|gif|ico|svg|ttf|woff|woff2|eot)$/,
    loader: 'file'
  }, {
    test: /\.json$/,
    loader: 'json'
  }]

  var env = {
    'process.env.CONFIG': JSON.stringify({
      backend: options.backend,
      version: '2016.9.18'
    })
  }
  var output = {
    path: path.join(__dirname, 'dist'),
    publicPath: '/'
  }
  var htmlOptions = {
    inject: true,
    template: path.join(SRC_ROOT, 'index.html'),
    filename: 'index.html',
    favicon: path.join(__dirname, SRC_ROOT, 'favicon.png'),
    title: 'LOTUS'
  }

  if (options.minify) {
    env['process.env.NODE_ENV'] = JSON.stringify('production')
    output.filename = '[id]-[chunkhash].js'
    htmlOptions.minify = {
      collapseWhitespace: true,
      removeComments: true
    }

    plugins.push(new CleanWebpackPlugin([output.path]))
    plugins.push(new webpack.optimize.UglifyJsPlugin({
      compress: {
        drop_console: true,
        drop_debugger: true,
        // dead_code: true,
        // unused: true,

        warnings: false
      },
      output: {
        comments: false
      }
    }))
    plugins.push(new webpack.optimize.CommonsChunkPlugin({
      name: 'vendor',
      minChunks: 3
    }))
    plugins.push(new webpack.optimize.DedupePlugin())
    plugins.push(new webpack.optimize.OccurrenceOrderPlugin(true))
    plugins.push(new webpack.NoErrorsPlugin())
    plugins.push(new ExtractTextPlugin('[id]-[chunkhash].css'))
    plugins.push(new SriPlugin(['sha256', 'sha384']))

    loaders.push({
      test: /\.css$/,
      loader: ExtractTextPlugin.extract('style', 'css')
    })
  } else {
    output.filename = '[name].js'

    plugins.push(new webpack.SourceMapDevToolPlugin({}))
    plugins.push(new StatsPlugin('stats.json', {
      chunkModules: true,
      exclude: [/node_modules/]
    }))
    loaders.push({
      test: /\.css$/,
      loaders: ['style', 'css']
    })
  }

  plugins.push(new webpack.DefinePlugin(env))
  plugins.push(new HtmlWebpackPlugin(htmlOptions))

  return {
    entry: entry,
    output: output,
    plugins: plugins,
    module: {
      preLoaders: [{
        test: /\.jsx?$/,
        loader: 'eslint-loader',
        exclude: /node_modules/
      }],
      loaders: loaders
    },
    resolve: {
      extensions: ['', '.js', '.jsx']
    },
    devServer: {
      historyApiFallback: true,
      port: 4200
    }
    // eslint: {
    //     fix: true
    // }
  }
}