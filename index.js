'use strict';

var express = require('express');
var kraken = require('kraken-js');
var mongoose = require('mongoose');
var session = require('express-session');
var MongoStore = require('connect-mongo')(session);

var options;
var app;

/*
 * Create and configure application. Also exports application instance for use by tests.
 * See https://github.com/krakenjs/kraken-js#options for additional configuration options.
 */
options = {
  onconfig: function(config, next) {
    /*
     * Add any additional config setup or overrides here. `config` is an initialized
     * `confit` (https://github.com/krakenjs/confit/) configuration object.
     */

    console.log('Conectando a MongoDB');
    mongoose.connect(
      config.get('MONGO_DB') || 'mongodb://localhost/chelathon',
      function(error) {
        if (error) {
          return next(error);
        }
        config.set('session:store', new MongoStore({
          mongooseConnection: mongoose.connection
        }));
        console.log('Conexión exitosa');
        next(null, config);
      }
    );
  }
};

app = module.exports = express();
app.use(kraken(options));
app.on('start', function() {
  console.log('Application ready to serve requests.');
  console.log('Environment: %s', app.kraken.get('env:env'));
});
