'use strict';

var mongoose = require('mongoose');

var UserModel = function() {
  var userSchema = new mongoose.Schema({
    name: String,
    phone: String,
    email: String,
    password: String,
    birthday: Date,
    city: String,
    address: [
      {
        name: String,
        street: String,
        externalNumber: String,
        internalNumber: String,
        zipCode: String,
        neighborhood: String,
        twon: String,
        state: String,
        phone: String,
        cellphone: String,
        references: String
      }
    ],
    cards: [String],
    _transactions: {
      type: mongoose.Schema.Types.ObjectId,
      ref: 'Transaction'
    }
  });

  var user = mongoose.model('User', userSchema);

  return user;
};

module.exports = new UserModel();
