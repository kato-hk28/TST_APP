#!/bin/sh
# mjpg-streamerを起動するシェルスクリプト
# /home/pi/home/pi/mjpg-streamer/mjpg-streamer-experimental に配置する。
# ラズパイ起動時に自動起動するには
# /etc/rc.local の"exit 0"の前らへんに
# "sh /home/pi/mjpg-streamer/mjpg-streamer-experimental/start_stream.sh"
# と記述しておく

# MJPG-Streamerの格納先ディレクトリ
MJPG_DIR="/home/pi/mjpg-streamer/mjpg-streamer-experimental"
# カレントディレクトリ移動
cd $MJPG_DIR
# 既に動いているプロセスがあればkillしておく
pkill mjpg_streamer

PORT="8080" #ポート番号
WIDTH="640" #画面横サイズ
HEIGHT="480" #画面縦サイズ
SIZE="${WIDTH}x${HEIGHT}" #画面サイズ
FRAMERATE="30" #フレームレート
QUALITY="10" #jpg品質

export LD_LIBRARY_PATH=/usr/local/lib
mjpg_streamer \
-i "./input_raspicam.so -x $WIDTH -y $HEIGHT -fps $FRAMERATE -quality $QUALITY" \
-o "output_http.so -w ./www -p $PORT" \
