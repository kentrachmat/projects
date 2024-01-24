const path = require("path")
const webpack = require("webpack")

const TerserPlugin = require("terser-webpack-plugin")
const CopyPlugin = require("copy-webpack-plugin")
const HtmlWebpackPlugin = require("html-webpack-plugin")

const PRODUCTION = true

module.exports = {
  entry: path.resolve(__dirname, "src", "javascripts", "main.js"),

  output: {
    path: path.resolve(__dirname, PRODUCTION ? "../server/public" : "dist"),
    filename: "javascripts/bundle.js",
  },

  mode: PRODUCTION ? "production" : "development",
  devtool: PRODUCTION ? undefined : "eval-source-map",

  devServer: {
    static: {
      publicPath: path.resolve(__dirname, "dist"),
      watch: true,
    },
    host: "localhost",
    port: 9000,
    open: true,
    target: ["/"],
  },

  module: {
    rules: [
      {
        test: /\.(m?js$|jsx)/,
        exclude: /(node_modules)/,
        use: {
          loader: "babel-loader",
        },
      },
      {
        test: /\.css$/i,
        use: ["style-loader", "css-loader"],
      },
      {
        test: /\.(png|jpg|gif)/i,
        use: {
          loader: "file-loader",
          options: {
            name: "[name].[ext]",
            outputPath: "images",
          },
        },
      },
    ],
  },

  plugins: [
    new webpack.ProgressPlugin(),
    new HtmlWebpackPlugin({
      template: path.resolve(__dirname, "src", "main.html"),
      filename: "./main.html",
      hash: true,
    }),
    new CopyPlugin({
      patterns: [
        {
          context: path.resolve(__dirname, "src", "html"),
          from: "**/*.html",
          to: "",
          noErrorOnMissing: true,
          globOptions: {},
        },
        {
          context: path.resolve(__dirname, "src", "img"),
          from: "**/*",
          to: "img/[name][ext]",
          noErrorOnMissing: true,
        },
        {
          context: path.resolve(__dirname, "src", "stylesheets"),
          from: "**/*.css",
          to: "stylesheets/[name][ext]",
          noErrorOnMissing: true,
        },
        {
          context: path.resolve(__dirname, "src", "stylesheets"),
          from: "**/*.css",
          to: "stylesheets/[name][ext]",
          noErrorOnMissing: true,
        },
        {
          context: path.resolve(__dirname, "src", "scripts"),
          from: "**/*.js",
          to: "javascripts/[name][ext]",
          noErrorOnMissing: true,
        },
        {
          context: path.resolve(__dirname, "vendor"),
          from: "**/*.js",
          globOptions: {},
          noErrorOnMissing: true,
          to: "vendor",
        },
      ],
    }),
  ],

  // gestion de bibliothèques externes à exclure du bundle, ici cas de React
  externals: {
    react: "React",
    reactdom: "ReactDom",
  },

  // optimization: {
  //   minimize: true,
  //   minimizer: [new TerserPlugin()],
  // },

  optimization: {
    minimize: true,
    minimizer: [new TerserPlugin()],

    splitChunks: {
      cacheGroups: {
        vendors: {
          priority: -10,
          test: /[\\/]node_modules[\\/]/,
        },
      },

      chunks: "async",
      minChunks: 1,
      minSize: 30000,
      name: false,
    },
  },
}
