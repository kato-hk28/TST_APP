#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <wiringPi.h>

const int Action[] = {9, 8};

enum {
    SET,
    SHOT
};

int main(int argc, char *argv[]) {
    if (wiringPiSetup() == -1) {
        printf("Error: setup failed\n");
        return -1;
    }

    pinMode(Action[SET], OUTPUT);
    pinMode(Action[SHOT], OUTPUT);
    digitalWrite(Action[SHOT], 0);
    digitalWrite(Action[SET], 1);

    sleep(sleep_time);

    digitalWrite(Action[SET], 0);

    return 0;
}
