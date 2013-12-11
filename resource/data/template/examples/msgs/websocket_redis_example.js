var bus, webSocketServer, redis;

require('msgs/adapters/nodeStream');
require('msgs/adapters/redis');
require('msgs/channels/pubsub');

bus = require('msgs').bus();
redis = require('redis');

webSocketServer = ...;

bus.pubsubChannel('fromClient');
bus.pubsubChannel('toClient');

webSocketServer.on('connection', function (connection) {
    bus.nodeStreamGateway(connection, { output: 'fromClient', input: 'toClient' });
});

bus.redisGateway(redis.createClient, 'redisTopic', { output: 'toClient', input: 'fromClient' });
