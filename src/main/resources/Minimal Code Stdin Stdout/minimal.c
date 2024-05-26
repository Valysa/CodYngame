#include <stdio.h>

int main() {
    char input[100];

    // Read input from stdin
    while (fgets(input, sizeof(input), stdin) != NULL) {
        // Print the input to stdout
        printf("%s", input);
    }

    return 0;
}