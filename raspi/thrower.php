<!-- /home/pi/public_html 直下に配置する
ADDRESS,PASSWORDを適切な値に変更する -->

<?php
define("THROWER", "/usr/bin/sudo /home/pi/TST_APP/raspi/thrower");
// define("ADDRESS", "192.168.1.89");
define("ADDRESS", "192.168.11.24");
define("USER","pi");
define("PASSWORD","co+in-345st");

$sconnection = ssh2_connect(ADDRESS, 22);
ssh2_auth_password($sconnection, USER, PASSWORD);
$command = THROWER;
$stdio_stream = ssh2_exec($sconnection, $command);

?>

