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

$branchID=$_REQUEST["branchID"];
$modelID =$_REQUEST["modelID"];
$kilometer =$_REQUEST["kilometer"];
$_ID =$_REQUEST["_ID"];

$sql = "INSERT INTO `cars`(`branchID`, `modelID`, `kilometer`, `_ID`) VALUES ($branchID, $modelID, $kilometer, $_ID)";
if($conn->query($sql) === TRUE)
	{echo "New record created succesfully";}
else {echo "error";}
$conn->close(); 
		
?>