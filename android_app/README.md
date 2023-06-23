# Android APP
## USAGE:
### TOP画面
* 自動操縦(Auto)か手動操作(Manual)かを選択  
### Auto Activity
### Manual Activity
同じWifi内で接続してあるRaspberryPiを制御して、モーターを操作する。  
操作画面の背景はラジコン上のラズパイカメラから取り入れたプレビューを表示する。  

#### ボタンタッチの検出
OnTouchLisnerをすべてのボタンにくっつけてタッチを検出する。  
* MotionEvent.ACTION_DOWN が検出されるとき、どのボタンが押されているか判定する。
* MotorHttpGetTask クラスを使用してHttp通信でPHPサーバに接続する。
| state number | Action |
|:-----------:|------------:|
| 0  | 前進 |
| 1  | 後退 |
| 2  | 左旋回 |
| 3  | 右旋回 |
| 4  | 停止 |
| 5  | 投てき |
* MotionEvent.ACTION_UP が検出されたら停止のstateを送ることで停止する。動作のボタンを押している間だけ動き続けることができる。
