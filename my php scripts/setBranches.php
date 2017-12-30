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
$numParking =$_REQUEST["numParking"];
$city =$_REQUEST["city"];
$street =$_REQUEST["street"];
$numApart =$_REQUEST["numApart"];

$sql = "INSERT INTO `branches`(`_id`, `numParking`, `city`, `street`, `numApart`) VALUES ($_id, $numParking, $city, $street, $numApart)";
if($conn->query($sql) === TRUE)
	{echo "New record created succesfully";}
else {echo "error";}
$conn->close(); 
		
?>