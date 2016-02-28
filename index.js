'use strict';

console.log('Loading function');

var Promise     = require('bluebird');
var db          = require('./src/db');
var telegramAPI = require('./src/api');
var config      = require('./src/env');
var User        = require('./model/User');

exports.handler = function(event, context) {
  console.log('event', event);
  console.log('context', context);
  if (event.webhook) {
    console.log('Setting Webhoook');
    telegramAPI('setWebhook', {url: config.APPLICATION_WEBHOOK})
    .then(function() {
      context.succeed(true);
    }).catch(function(err) {
      context.fail(err);
    });
    return;
  }
  if (!event.message) {
    return context.fail('Evento no reconocido.');
  }
  var getUser = User.findOneAsync({chatId: event.message.chat.id})
  .then(function(doc) {
    var promise = Promise.resolve();
    if (!doc) {
      doc = new User({chatId: event.message.chat.id});
    }
    return promise.then(function() {
      if (!doc.status) {
        doc.status = 'selection';
        doc.saveAsync().then(function() {
          return telegramAPI('sendMessage', {
            chat_id: event.message.chat.id,
            text: 'Bienvenido a Modelo Now Bot\n' +
            'La nueva forma sencilla de pedir tus cheves.',
          });
        }).then(function() {
          return telegramAPI('sendMessage', {
            chat_id: event.message.chat.id,
            text: '¿Qué deseas comprar?',
            reply_markup: {
              keyboard: [
                ['Bud Light', 'Corona Extra'],
                ['Michelob Ultra', 'Modelo Especial'],
                ['Corona Light', 'Modelo Especial'],
                ['Stella Artois', 'Barrilito'],
                ['Bocanegra Dunkel', 'Bocanegra Pilsner']
              ]
            }
          });
        }).then(function() {
          return doc;
        });
      }
      switch (doc.status) {
        case 'selection':

          break;
        default:

      }
    });
  }).then(function(res) {
    return '*success* ' + JSON.stringify(res);
  }).catch(function(err) {
    return '*fail* ' + JSON.stringify(err);
  }).then(function(text) {
    context.succeed({
      method: 'sendMessage',
      parse_mode: 'Markdown',
      chat_id: event.message.chat.id,
      text: text
    });
  }).catch(function(err) {
    context.fail(err);
  });
  // Si no es ningún mensaje

};
