#include <stdio.h>
#include <stdint.h>
#include <stdlib.h>
#include <wiringPi.h>
#include <unistd.h>

#define SERVO_right0 27
#define SERVO_right1 22
#define SERVO_left0 23
#define SERVO_left1 24

#define action_FORWARD 0
#define action_BACKWARD 1
#define action_LEFT 2
#define action_RIGHT 3
#define action_STOP 4


int main(int argc, char *argv[]) {

    if (argc < 2) {
      printf("Usage: %s FORWARD(0)/BACKWARD(1)/LEFT(2)/RIGHT(3)/STOP(4)\n", argv[0]);
		  return -1;
	  }
    if (wiringPiSetupGpio() == -1) {
        printf("Error: setup failed\n");
        return 1;
    }

    int action_state = atoi(argv[1]);

    if (action_state > 4) {
      printf("Error: action should be (0,1,2,3,4)\n");
		  return -1;
	  }
    // Set all pin mode to OUTPUT mode
    pinMode(SERVO_right0, OUTPUT);
    pinMode(SERVO_right1, OUTPUT);
    pinMode(SERVO_left0, OUTPUT);
    pinMode(SERVO_left1, OUTPUT);
    //
    // pwmSetMode(PWM_MODE_MS);
    // pwmSetClock(400);
    // pwmSetRange(1024);
    // Init reset
    digitalWrite(SERVO_right0, 0);
    digitalWrite(SERVO_right1, 0);
    digitalWrite(SERVO_left0, 0);
    digitalWrite(SERVO_left1, 0);

    switch (action_state)
    {
    case action_FORWARD:
      digitalWrite(SERVO_right0, 1);
      digitalWrite(SERVO_right1, 0);
      digitalWrite(SERVO_left0, 1);
      digitalWrite(SERVO_left1, 0);
      printf("ACTION_FORWARD\n");
      break;
    case action_BACKWARD:
      digitalWrite(SERVO_right0, 0);
      digitalWrite(SERVO_right1, 1);
      digitalWrite(SERVO_left0, 0);
      digitalWrite(SERVO_left1, 1);
      printf("ACTION_BACKWARD\n");     
      break;
    case action_LEFT:
      digitalWrite(SERVO_right0, 1);
      digitalWrite(SERVO_right1, 0);
      digitalWrite(SERVO_left0, 1);
      digitalWrite(SERVO_left1, 1);
      printf("ACTION_LEFT\n");
      break;
    case action_RIGHT:
      digitalWrite(SERVO_right0, 0);
      digitalWrite(SERVO_right1, 0);
      digitalWrite(SERVO_left0, 1);
      digitalWrite(SERVO_left1, 0);
      printf("ACTION_RIGHT\n");
      break;
    case action_STOP:
      digitalWrite(SERVO_right0, 0);
      digitalWrite(SERVO_right1, 0);
      digitalWrite(SERVO_left0, 0);
      digitalWrite(SERVO_left1, 0);
      printf("ACTION_STOP\n");
      break;
    default:
      printf("Nothing\n");
      break;
    }

  return 0;
}