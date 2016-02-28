'use strict';
var Promise = require('bluebird');
var mongoose = Promise.promisifyAll(require('mongoose'));

var UserModel = function() {
  var userSchema = new mongoose.Schema({
    chatId: String,
    status: String,
    location: [Number],
    order: {},
    amount: Number
  });

  var user = mongoose.model('User', userSchema);

  return user;
};

module.exports = new UserModel();
