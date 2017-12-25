<?php
header('Content-Type: text/html; charset = utf-8');
$servername = "localhost";
$username = "LiranTal";
$password = "LiranTal";
$dbname = "tades_";

//create connection
$conn = new mysqli($servername, $username, $password, $dbname);
mysql_query("SET NAMES 'utf8'", $conn);

//check connection
if($conn->connected_error)
{die("Connection failed:" .$conn->connected_error);}

$branch =$_REQUEST["branchID"];
$model =$_REQUEST["modelID"];
$kilometer =$_REQUEST["kilometer"];
$id =$_REQUEST["_ID"];
//$sql = "INSERT INTO 'tades_'.'cars' ('branchID', 'modelID', 'kilometer', 'ID') VALUES(\"$branch\", \"$model\", \"$kilometer\", \"$id\")";
$sql = "INSERT INTO `cars` (`branchID`, `modelID`, `kilometer`, `_ID`) VALUES('$branch','$model','$kilometer','$id')";

if($conn->query($sql) === TRUE)
	{echo "New record created succesfully";}
else {echo "Error: " . $sql . "<br>" . $conn->error;}
$conn->close(); 
		
?>