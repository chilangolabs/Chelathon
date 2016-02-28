'use strict';

var Promise = require('bluebird');
var JWT = Promise.promisifyAll(require('jsonwebtoken'));

var Auth = function() {};
Auth.prototype.auth = function(req, res, next) {
  var secret = req.app.kraken.get('JWTTOKEN') || 'secreto';
  var token = req.get('x-access-token') || req.session.token;
  if (req.session.user) {
    next();
  }
  if (!token) {
    next(new Error('Not session token found.'));
  }
  JWT.verifyAsync(token, secret)
  .then(function(decripted) {
    User.findOne({_id: decripted}, function(err, doc) {
      if (err) {
        next(err);
      }
      if (!doc) {
        next(new Error('Not user found'));
      }
      req.session.user = doc;
      next();
    });
  })
  .catch(function(err) {next(err);});
};
Auth.prototype.getToken = function(req, res, next) {
  var secret = req.app.kraken.get('JWTTOKEN') || 'secreto';
  if (!req.session.user) {
    next(new Error('No existe usuario.'));
  }
  JWT.signAsync(req.session.user._id, secret)
  .then(function(token) {
    req.session.token = token;
    next();
  })
  .catch(function(err) {next(err);});
};
module.exports = new Auth();
