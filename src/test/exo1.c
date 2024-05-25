#include <stdio.h>

int main() {
    // Boucle pour lire 10 entiers et afficher leur double
    for (int i = 0; i < 10; i++) {
        int entry;
        scanf("%d", &entry);
        int exit = entry * 16;
        printf("%d\n", exit);
    }
    return 0;
}
