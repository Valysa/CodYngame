<?php
// Read input from stdin
$input = fopen('php://stdin', 'r');
while ($line = fgets($input)) {
    // Print the input to stdout
    echo $line;
}
fclose($input);
?>