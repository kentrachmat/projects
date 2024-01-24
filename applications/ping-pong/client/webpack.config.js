const path = require("path")
const webpack = require("webpack")

const TerserPlugin = require("terser-webpack-plugin")
const CopyPlugin = require("copy-webpack-plugin")
const HtmlWebpackPlugin = require("html-webpack-plugin")

const PRODUCTION = false

module.exports = {
  entry: path.resolve(__dirname, "src", "scripts", "pong.js"),

  // output: {
  //   path: path.resolve(__dirname, "dist"),
  //   filename: "scripts/bundle.js",
  // },

  output: {
    path: path.resolve(__dirname, "../server/public"),
    filename: "scripts/bundle.js",
  },

  mode: PRODUCTION ? "production" : "development",
  devtool: PRODUCTION ? undefined : "eval-source-map",

  devServer: {
    static: {
      publicPath: path.resolve(__dirname, "dist"),
      watch: true,
    },
    host: "localhost",
    port: 8080,
    open: {
      target: ["/"],
      app: ["firefox"],
    },
  },

  plugins: [
    new webpack.ProgressPlugin(),
    new HtmlWebpackPlugin({
      template: path.resolve(__dirname, "src", "index.html"),
      filename: "./index.html",
      hash: true,
    }),
    new CopyPlugin({
      patterns: [
        {
          context: path.resolve(__dirname, "src", "html"),
          from: "**/*.html",
          to: "html",
          noErrorOnMissing: true,
          globOptions: {},
        },
        {
          context: path.resolve(__dirname, "src", "images"),
          from: "**/*",
          to: "images/[name].[ext]",
          noErrorOnMissing: true,
        },
        {
          context: path.resolve(__dirname, "src", "style"),
          from: "**/*.css",
          to: "style/[name].[ext]",
          noErrorOnMissing: true,
        },
      ],
    }),
  ],

  optimization: {
    minimize: true,
    minimizer: [new TerserPlugin()],
  },
}
