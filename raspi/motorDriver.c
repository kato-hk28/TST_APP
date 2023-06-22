#include <stdio.h>
#include <stdint.h>
#include <stdlib.h>
#include <wiringPi.h>
#include <unistd.h>

// ピン番号とActionを番号付け
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

    // 引数はActionの番号のみ
    if (argc < 2) {
      printf("Usage: %s FORWARD(0)/BACKWARD(1)/LEFT(2)/RIGHT(3)/STOP(4)\n", argv[0]);
		  return -1;
	  }
    // wiringPiのセットアップ
    if (wiringPiSetupGpio() == -1) {
        printf("Error: setup failed\n");
        return 1;
    }

    int action_state = atoi(argv[1]);

    // Actionは前/後/左/右/停止/の4パターン
    if (action_state > 4) {
      printf("Error: action should be (0,1,2,3,4)\n");
		  return -1;
	  }

    // すべてのGPIOピンをOUTPUTモードに設定
    pinMode(SERVO_right0, OUTPUT);
    pinMode(SERVO_right1, OUTPUT);
    pinMode(SERVO_left0, OUTPUT);
    pinMode(SERVO_left1, OUTPUT);
    // すべてのGPIOピンを初期化
    digitalWrite(SERVO_right0, 0);
    digitalWrite(SERVO_right1, 0);
    digitalWrite(SERVO_left0, 0);
    digitalWrite(SERVO_left1, 0);

    switch (action_state)
    {
    case action_FORWARD:
      // 前進
      digitalWrite(SERVO_right0, 1);
      digitalWrite(SERVO_right1, 0);
      digitalWrite(SERVO_left0, 1);
      digitalWrite(SERVO_left1, 0);
      printf("ACTION_FORWARD\n");
      break;
    case action_BACKWARD:
      // 後退
      digitalWrite(SERVO_right0, 0);
      digitalWrite(SERVO_right1, 1);
      digitalWrite(SERVO_left0, 0);
      digitalWrite(SERVO_left1, 1);
      printf("ACTION_BACKWARD\n");     
      break;
    case action_LEFT:
      // 左旋回
      // 右のモーターのみを動かす（または右モーター正回転,左モーター負回転）
      digitalWrite(SERVO_right0, 1);
      digitalWrite(SERVO_right1, 0);
      digitalWrite(SERVO_left0, 0);
      digitalWrite(SERVO_left1, 0);
      printf("ACTION_LEFT\n");
      break;
    case action_RIGHT:
      // 右旋回
      // 左のモーターのみを動かす（または左モーター正回転,右モーター負回転）
      digitalWrite(SERVO_right0, 0);
      digitalWrite(SERVO_right1, 0);
      digitalWrite(SERVO_left0, 1);
      digitalWrite(SERVO_left1, 0);
      printf("ACTION_RIGHT\n");
      break;
    case action_STOP:
      // 停止、初期化
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