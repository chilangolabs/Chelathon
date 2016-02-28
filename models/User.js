'use strict';
var Promise = require('bluebird');
var config  = require('../src/env');
var mongoose = Promise.promisifyAll(require('mongoose'));

mongoose.connect(config.MONGO_DB);

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
