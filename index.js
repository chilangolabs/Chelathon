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
      if (doc.status === 'more') {
        if (event.message.text === 'Sí') {
          doc.status = null;
        }
      }
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
          doc.order.selection = event.message.text;
          doc.status = 'quantity';
          return Promise.all([
            telegramAPI('sendMessage', {
              chat_id: event.message.chat.id,
              text: '¿Cuántas vas a querer?',
              reply_markup: {
                hide_keyboard: true
              }
            }),
            doc.saveAsync()
          ]);
        case 'quantity':
          doc.order.cart = doc.order.cart || [];
          doc.order.cart.push({
            type: doc.order.selection,
            quantity: event.message.text
          });
          doc.status = 'more';
          return Promise.all([
            telegramAPI('sendMessage', {
              chat_id: event.message.chat.id,
              text: '¿Quieres agregar algo más?',
              reply_markup: {
                keyboard: [
                  ['Sí', 'No, ya envíamelo.']
                ]
              }
            }),
            doc.saveAsync()
          ]);
        case 'more':
          doc.status = 'location';
          return Promise.all([
            telegramAPI('sendMessage', {
              chat_id: event.message.chat.id,
              text: 'Envíanos tu ubicación',
              reply_markup: {
                hide_keyboard: true
              }
            }),
            doc.saveAsync()
          ]);
        case 'location':
          doc.status = 'confirmation';
          return Promise.all([
            telegramAPI('sendMessage', {
              chat_id: event.message.chat.id,
              text: 'Confirmanos tu órden. ¿Deséas recibirla?',
              reply_markup: {
                keyboard: [
                  ['Sí', 'Cancelar']
                ]
              }
            }).then(function() {
              var message = '';
              (doc.order.cart || []).forEach(function(value, index, arr) {
                message += value.type + ' x' + value.quantity;
              });
              return telegramAPI('sendMessage', {
                chat_id: event.message.chat.id,
                text: message,
                reply_markup: {
                  hide_keyboard: true
                }
              });
            }),
            doc.saveAsync()
          ]);
        case 'confirmation':
          doc.status = null;
          return Promise.all([
            telegramAPI('sendMessage', {
              chat_id: event.message.chat.id,
              text: 'Uno de nuestros repartidores ha sido ' +
                'asignado y va en camino.',
              reply_markup: {
                hide_keyboard: true
              }
            }),
            doc.saveAsync()
          ]);
      }
    });
  }).then(function(res) {
  }).catch(function(err) {
    return '*fail* ' + JSON.stringify(err);
  }).then(function(text) {
    if (!res) {
      context.succeed(true);
    }
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
