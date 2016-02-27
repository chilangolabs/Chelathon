'use strict';

var User = require('../../../models/User');

module.exports = function(router) {

  var model = {status: 'ok'};

  router.get('/', function(req, res) {

    res.send('<code><pre>' + JSON.stringify(model, null, 2) + '</pre></code>');

  });

};
