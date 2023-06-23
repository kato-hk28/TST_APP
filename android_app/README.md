# Android APP
## TOP画面
* 手動操作(Manual)を選択  
## Auto Activity
実装できず。。。ざんねん
## Manual Activity
同じWifi内で接続するRaspberryPiを制御して、モーターを操作する。  
操作画面の背景はラジコン上のラズパイカメラから取り入れたプレビューを表示する。  

### ボタンタッチの検出
OnTouchLisnerをすべてのボタンにくっつけてタッチを検出する。  
* MotionEvent.ACTION_DOWN が検出されるとき、どのボタンが押されているか判定する。
* AsyncTaskを実装したMotorHttpGetTask クラスを使用してHttp通信でPHPサーバに接続する。  
* MotionEvent.ACTION_UP が検出されたら停止のstateを送ることで停止する。動作のボタンを押している間だけ動き続けることができる。

| state number | Action |
|:-----------:|:------------:|
| 0  | 前進 |
| 1  | 後退 |
| 2  | 左旋回 |
| 3  | 右旋回 |
| 4  | 停止 |
| 5  | 投てき |

### android-mjpeg-view
Android のためのMJPEG配信を映すためのCustomViewを提供しているライブラリ(?)  
配信しているサイトのhttp(s) URLを指定するだけで配信映像をとってこれる。  
https://github.com/perthcpe23/android-mjpeg-view