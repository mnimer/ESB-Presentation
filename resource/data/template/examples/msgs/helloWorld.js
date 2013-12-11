var bus = require('msgs').bus();

bus.channel('lowercase');

bus.transform(function (message) {
    return message.toUpperCase();
}, {
    input: 'lowercase', output: 'uppercase'
});

bus.channel('uppercase');

bus.outboundAdapter(function (str) {
    console.log(str);
}, {
    input: 'uppercase'
});

bus.send('lowercase', 'hello world'); // 'HELLO WORLD'