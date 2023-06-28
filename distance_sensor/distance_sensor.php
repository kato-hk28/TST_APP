<!-- /home/pi/public_html 直下に配置する
ADDRESS,PASSWORDを適切な値に変更する -->

<?php
define("GET_DISTANCE", "/usr/bin/sudo /home/pi/distance_sensor");
define("ADDRESS", "127.0.0.1");
define("USER", "pi");
define("PASSWORD", "rastai");

$sconnection = ssh2_connect(ADDRESS, 22);
ssh2_auth_password($sconnection, USER, PASSWORD);

$command = GET_DISTANCE;
$stream = ssh2_exec($sconnection, $command);
stream_set_blocking($stream, true);
$stream_out = ssh2_fetch_stream($stream, SSH2_STREAM_STDIO);
$distance = stream_get_contents($stream_out);

header('Content-Type: application/json; charset=UTF-8');
$arr["distance"] = $distance;

print json_encode($arr, JSON_PRETTY_PRINT);
?>
