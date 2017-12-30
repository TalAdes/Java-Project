<?php
header('Content-Type: text/html; charset = utf-8');
$servername = "localhost";
$username = "LiranTal";
$password = "LiranTal";
$dbname = "tades_";

//create connection
$conn = new mysqli($servername, $username, $password, $dbname);

//check connection
if($conn->connected_error)
{die("Connection failed:" .$conn->connected_error);}

$_id =$_REQUEST["_id"];
$company =$_REQUEST["company"];
$model =$_REQUEST["model"];
$engine =$_REQUEST["engine"];
$gear =$_REQUEST["gear"];
$seats =$_REQUEST["seats"];

$sql = "INSERT INTO `carModels`(`_id`, `company`, `model`, `engine`, `gear`, `seats`) VALUES ($_id, $company, $model, $engine, $gear, $seats)";
if($conn->query($sql) === TRUE)
	{echo "New record created succesfully";}
else {echo "error";}
$conn->close(); 
		
?>