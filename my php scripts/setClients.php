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

$Fname =$_REQUEST["Fname"];
$Lname =$_REQUEST["Lname"];
$_id =$_REQUEST["_id"];
$phoneNumber =$_REQUEST["phoneNumber"];
$email =$_REQUEST["email"];
$numCredit =$_REQUEST["numCredit"];

$sql = "INSERT INTO `clients`(`Fname`, `Lname`, `_id`, `phoneNumber`, `email`, `numCredit`) VALUES ($Fname, $Lname, $_id, $phoneNumber, $email, $numCredit)";
if($conn->query($sql) === TRUE)
	{echo "New record created succesfully";}
else {echo "error";}
$conn->close(); 
		
?>