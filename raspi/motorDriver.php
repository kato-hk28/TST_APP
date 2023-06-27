<!-- /home/pi/public_html 直下に配置する
ADDRESS,PASSWORDを適切な値に変更する -->

<?php
define("MOTOR", "/usr/bin/sudo /home/pi/TST_APP/raspi/motorDriver");
define("ADDRESS", $_GET["ip"]);
define("USER","pi");
define("PASSWORD","co+in-345st");

if(!is_null($_GET["state"])) {
$sconnection = ssh2_connect(ADDRESS, 22);
ssh2_auth_password($sconnection, USER, PASSWORD);
$command = MOTOR." ".$_GET["state"];
$stdio_stream = ssh2_exec($sconnection, $command);
}
?>

