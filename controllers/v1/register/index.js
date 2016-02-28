'use strict';

var auth = require('../../../lib/middleware/auth');

var User = require('../../../models/User');

module.exports = function(router) {

  var model = {status: 'ok'};

  router.route('/')
    .get(function(req, res, next) {
      res.send(req.path);
    })
    .post(
      function(req, res, next) {
        var _user = {
          name: req.body.name,
          phone: req.body.phone,
          email: req.body.email,
          password: req.body.password,
          birthday: req.body.birthday,
          city: req.body.city
        };

        var user = new User(_user);
        user.save(function(err) {
          if (err) { return next(err); }
          console.log('user', user.toObject());
          req.session.user = user;
          next();
        });
      },
      auth.getToken,
      function(req, res, next) {
        console.log('token', req.session.token);
        res.send({token: req.session.token});
      }
    );

};
