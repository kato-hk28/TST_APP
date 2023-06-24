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
    const int sleep_time = 2; //2s間停止
    if (wiringPiSetup() == -1) {
        printf("Error: setup failed\n");
        return -1;
    }

    pinMode(Action[SHOT], OUTPUT);
    pinMode(Action[SET], OUTPUT);
    digitalWrite(Action[SET], 0);
    digitalWrite(Action[SHOT], 1);

    sleep(sleep_time);

    digitalWrite(Action[SHOT], 0);
    digitalWrite(Action[SET], 1);

    sleep(sleep_time);
    digitalWrite(Action[SET], 0);

    return 0;
}
