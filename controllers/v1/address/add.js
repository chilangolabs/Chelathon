'use strict';

var jwt = require('jsonwebtoken');
var User = require('../../../models/User');

module.exports = function(router) {
  router.post('/', function(req, res) {
    var authKey = req.headers['x-access-token'];
    var name = req.body.name;
    var street = req.body.street;
    var externalNumber = req.body.externalnumber;
    var internalNumber = req.body.internalnumber;
    var zip = req.body.zip;
    var town = req.body.town;
    var state = req.body.state;
    var phone = req.body.phone;
    var cellphone = req.body.cellphone;
    var references = req.body.references;
    var userId;

    if (!authKey) {
      return res.status(401).json({
        ok: false,
        message: 'Unauthorized, send a valid Access Token'
      });
    }

    if (!name || !street || !externalNumber || !internalNumber || !zip
      || !town || !state || !phone) {
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

    var newAddress = {
      name: name,
      street: street,
      externalNumber: externalNumber,
      internalNumber: internalNumber,
      zipCode: zip,
      town: town,
      state: state,
      phone: phone,
      cellphone: cellphone,
      references: references
    };

    User.findByIdAndUpdate(userId, {
      $push: {
        address: newAddress
      }
    }, function(err) {
      if (err) {
        console.error(err);
        return res.sendStatus(500);
      }

      res.json({
        ok: true,
        message: 'Address add correctly'
      });

    });

  });

};
