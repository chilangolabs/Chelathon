'use strict';

var Promise = require('bluebird');
var jwt = Promise.promisifyAll(require('jsonwebtoken'));
var User = require('../../models/User');

var Auth = function() {};
/**
 * Cuando el token de sesión está presente, busca al usuario en DB y lo guarda
 * en variables de sesión.
 */
Auth.prototype.auth = function(req, res, next) {
  var secret = req.app.kraken.get('JWTTOKEN') || 'secreto';
  var token = req.get('x-access-token') || req.session.token;
  if (req.session.user) {
    next();
  }
  if (!token) {
    return next(new Error('Not session token found.'));
  }
  jwt.verifyAsync(token, secret)
  .then(function(decripted) {
    User.findOne({_id: decripted}, function(err, doc) {
      if (err) {
        return next(err);
      }
      if (!doc) {
        return next(new Error('Not user found'));
      }
      req.session.user = doc;
      next();
    });
  })
  .catch(function(err) {next(err);});
};
/**
 * Cuando el usuario esté presente en la sesión, obtener el del usuario.
 */
Auth.prototype.getToken = function(req, res, next) {
  var secret = req.app.kraken.get('JWTTOKEN') || 'secreto';
  if (!req.session.user) {
    return next(new Error('No existe usuario.'));
  }
  req.session.token = jwt.sign(req.session.user._id, secret);
  next();
};
module.exports = new Auth();
