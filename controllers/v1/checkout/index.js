'use strict';

var Conekta = require('conekta');
var jwt = require('jsonwebtoken');
var User = require('../../../models/User');

module.exports = function(router) {
  router.post('/', function(req, res) {
    var authKey = req.headers['x-access-token'];
    var indexCard = req.body.indexcard;
    var indexAddress = req.body.indexaddress;
    var userId;

    if (!authKey) {
      return res.status(401).json({
        ok: false,
        message: 'Unauthorized, send a valid Access Token'
      });
    }

    if (!indexCard || !indexAddress) {
      return res.status(400).json({
        ok: false,
        message: 'Missing some parameter'
      });
    }

    jwt.verify(authKey, '', function(err, decoded) {
      if (err) {
        console.error(err);
        return res.sendStatus(500);
      }

      userId = decoded;
    });

    Conekta.api_key = req.app.krake.get('conekta:privateKey');

    User.findById(userId, function(err, user) {
      if (err) {
        console.error(err);
        return res.sendStatus(500);
      }

      Conekta.Charge.create({
        'description': 'ModeloNow',
        'amount': 2000, // Change amount
        'currency': 'MXN',
        'card': user.card[indexCard], // TODO
        'details': {
          'name': user.name,
          'phone': user.phone,
          'email': user.email,
          'line_items': [
            {
              'name': 'Other name', // TODO ??
              'description': 'Some description', // TODO ??
              'unit_price': 20000, // TODO
              'quantity': 1 // TODO
            }
          ],
          'shipment': {
            'carrier': 'Modelo',
            'service': 'Now',
            'price': 0,
            'address': {
              'street1': user.address[indexAddress].street,
              'city': user.address[indexAddress].street,
              'state': user.address[indexAddress].this.state.,
              'country': 'MX',
              'zip': user.address[indexAddress].zipCode
            }
          }
        }
      }, function(err, res) {
        if (err) {
          console.error(err);
          return res.sendStatus(500);
        }
      });
    });
  });

};
