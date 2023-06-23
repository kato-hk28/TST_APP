#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>

#include <wiringPi.h>

#define TRIGER_PIN 2
#define ECHO_PIN 1

float distanceMeasure(void){
	struct timeval tv1;
	struct timeval tv2;
	long time1, time2;
	float distance;

	digitalWrite(TRIGER_PIN, LOW);
	delayMicroseconds(2);
	digitalWrite(TRIGER_PIN, HIGH);
	delayMicroseconds(10);
	digitalWrite(TRIGER_PIN, LOW);

	while(!(digitalRead(ECHO_PIN) == 1))
	gettimeofday(&tv1, NULL);

	while(!(digitalRead(ECHO_PIN) == 0))
	gettimeofday(&tv2, NULL);

	time1 = tv1.tv_sec * 1000000 + tv1.tv_usec;
	time2 = tv2.tv_sec * 1000000 + tv2.tv_usec;
	distance = (float)(time2 - time1) / 1000000 * 34000 / 2;
	return distance;
}

int main(int argc, char *argv[]){
	float distance;
	if(wiringPiSetup() == -1){
		return 1;
	}

	pinMode(ECHO_PIN, INPUT);
	pinMode(TRIGER_PIN, OUTPUT);

	distance = distanceMeasure();
	printf("%0.1f", distance);
	return 0;
}
