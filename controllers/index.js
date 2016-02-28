'use strict';

// var IndexModel = require('../models/index');

module.exports = function(router) {

  var model = {status: 'ok'};

  router.get('/', function(req, res) {
    res.send(model);
  });

};
