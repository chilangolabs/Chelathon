'use strict';

var jwt = require('jsonwebtoken');
var User = require('../../../models/User');

module.exports = function(router) {

  var model = {status: 'ok'};

  router.post('/', function(req, res) {
    var authKey = req.headers['x-access-token'];
    var token = req.body.token;
    var userId;

    if (!authKey) {
      return res.status(401).json({
        ok: false,
        message: 'Unauthorized, send a valid Access Token'
      });
    }

    if (!token) {
      return res.status(400).json({
        ok: false,
        message: 'Token is required'
      });
    }

    jwt.verify(authKey, '', function(err, decoded) {
      if (err) {
        console.error(err);
        return res.sendStatus(500);
      }

      userId = decoded;
    });

    User.findByIdAndUpdate(userId, {
      $push: {
        cards: token
      }
    }, function(err) {
      if (err) {
        console.error(err);
        return res.sendStatus(500);
      }

      res.json({
        ok: true,
        message: 'Card add correctly'
      });

    });

  });

};
