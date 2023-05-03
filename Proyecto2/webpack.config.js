const path = require('path');
const webpack = require('webpack');


module.exports = {
  entry: './src/index.js', // Punto de entrada de la aplicación
  output: {
    path: path.resolve(__dirname, 'dist'), // Carpeta donde se generará el bundle
    filename: 'bundle.js' // Nombre del archivo bundle
  },
  module: {
    rules: [
      // Reglas para procesar los archivos con diferentes loaders
      {
        test: /\.js$/,
        exclude: /node_modules/,
        use: {
          loader: 'babel-loader'
        }
      }
    ]
  },
  node: {
    fs: 'empty',
    net: 'empty',
    tls: 'empty'
  },
  resolve: {
    fallback: {
      path: false,
      "path": require.resolve("path-browserify"),
      fs: false
    }
  },
  
};