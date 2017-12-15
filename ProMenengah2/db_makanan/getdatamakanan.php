<?php


 include './config/koneksi.php';

//    $connection = mysqli_connect("localhost","root","","dbpulsa") or die("Error " . mysqli_error($connection));

     $vsid = $_POST['vsiduser'];
      //$vsid ='54';
        
    $sql = "SELECT * FROM tblmakanan tm,tbluser tu WHERE tm.id_user=$vsid AND tm.id_user=tu.id_user";

    $result = mysqli_query($connection, $sql) or die("Error in Selecting " . mysqli_error($connection));

    //create an array
    $emparray = array();
    while($row =mysqli_fetch_assoc($result))
    {
        $emparray[] = $row;
    }
   
    $akhir = array(
	
   	'DataMakanan' => $emparray
    );

 	echo json_encode($akhir);


    //close the db connection
    mysqli_close($connection);
?>
