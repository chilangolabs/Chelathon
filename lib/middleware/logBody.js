'use strict';

module.exports = function() {
  return function(req, res, next) {
      console.log('req.body', req.body);
    };
};
