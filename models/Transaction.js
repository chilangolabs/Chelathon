'use strict';

var mongoose = require('mongoose');

var TransactionModel = function() {
  var transactionSchema = new mongoose.Schema({
    code: String,
    status: String,
    data: {},
    orden: {},
    address: {},
    paymentMethod: String,
    _user: {
      type: mongoose.Schema.Types.ObjectId,
      ref: 'User'
    },
    _createdAt: {
      type: Date,
      default: Date.now
    }
  });

  var transaction = mongoose.model('Transaction', transactionSchema);

  return transaction;
};

module.exports = new TransactionModel();
