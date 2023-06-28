# Raspberry Pi
* .phpファイルは　~/pi/public_html 直下へ配置
* start_stream.sh は /home/pi/home/pi/mjpg-streamer/mjpg-streamer-experimental 直下へ配置

# modotDriver USAGE
```
 cd raspi
 gcc -lwiringPi -o motorDriver motorDriver.c
 sudo ./motorDriver 0
```
引数としてstate numberを入力することでモーターを制御する。  
| state number | Action |
|:-----------:|:------------:|
| 0  | 前進 |
| 1  | 後退 |
| 2  | 左旋回 |
| 3  | 右旋回 |
| 4  | 停止 |
| 5  | 投てき |

## USAGE:RasPi Cameraとmjpg_streamer
Raspberry Pi Camera の映像をWeb配信できるアプリケーション  
以下のサイトを参考にインストールおよびセットアップを行う。  
mjpg-streamerでraspberryPiのカメラからストリーミングをする方法:  
https://portaltan.hatenablog.com/entry/2016/11/29/185600  
Raspberry Pi用カメラモジュールおよび赤外線カメラPi NoIRの映像をandroidで表示してみた:  
https://neuralassembly.blogspot.com/2013/11/raspberry-pipi-noirandroid.html  
