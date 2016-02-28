'use strict';

var auth = require('../../../lib/middleware/auth');

var User = require('../../../models/User');

module.exports = function(router) {

  var model = {status: 'ok'};

  router.route('/')
    .get(
      function(req, res, next) {
        res.send(req.session.jwtID);
      }
    ).post(
      auth.auth,
      function(req, res, next) {
        res.send(req.session.user);
      }
    );

};
