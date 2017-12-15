<?php
 include './config/koneksi.php';

$response = array();	
$vsid = $_POST['vsidmakanan'];
//$vsnim = '114';

$sql = "DELETE FROM tblmakanan WHERE id_makanan='$vsid'";
	if(mysqli_query($connection,$sql)){
	   $response["result"] = "1";
       $response["msg"] = "data makanan berhasil dihapus!!";
       echo json_encode($response);
		}else{
	   $response["result"] = "0";
       $response["msg"] = "maaf! gagal menghapus data!";
		echo json_encode($response);
		}
		mysqli_close($connection);
?>

