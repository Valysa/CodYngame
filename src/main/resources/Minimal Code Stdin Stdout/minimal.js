const readline = require('readline');

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
    terminal: false
});

// Read input from stdin
rl.on('line', (line) => {
    // Print the input to stdout
    console.log(line);
});