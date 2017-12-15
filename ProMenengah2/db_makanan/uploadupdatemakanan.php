<?php 
 include './config/koneksi.php';

	//importing dbDetails file 
	// define('HOST','localhost');
	// define('USER','root');
	// define('PASS','');
	// define('DB','mhspoli');
	
	// $con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');


	//this is our upload folder 
	$upload_path = 'uploads/';
	
	//Getting the server ip 
	$server_ip = gethostbyname(gethostname());
	
	//creating the upload url 
	$upload_url = 'http://'.$server_ip.'/db_makanan/'.$upload_path; 
	
	//response array 
	$response = array(); 
	
	
	if($_SERVER['REQUEST_METHOD']=='POST'){
		
		//checking the required parameters from the request 
		//if(isset($_POST['name']) and isset($_FILES['image']['name'])){
			
			//connecting to the database 
		//	$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect...');
			
		// 	$nama = 'andi';	
		// $alamat = 'padang';
		// $kota = 'jkt';h
		// $notelp = '8222';
		// $username = 'sandi';
		// $level = 'admin';
		// $password = 'sandi11';
		
	//	$nim = $_POST['vsnim'];	
		$namamakanan = $_POST['vsnamamakanan'];	
		$id_makanan =$_POST['vsidmakanan'];
	
		// $namamakanan = 'bubur ayam';	
		// $id_makanan ='1';

// $id = $_POST["vspassword"];
			//getting name from the request 
			$name = 'fotomakanan';
			//getting file info from the request 
			$fileinfo = pathinfo($_FILES['image']['name']);
			//getting the file extension 
			$extension = $fileinfo['extension'];
			//file url to store in the database 
			$file_url = $upload_url . $_FILES['image']['name'];
		//	$file_url = 'http://192.168.95.20/crudmakanan/uploads/103.jpg';
			
			//file path to upload in the server 
			$file_path = $upload_path . $_FILES['image']['name']; 
			
			//trying to save the file in the directory 
			try{
				//saving the file 
				move_uploaded_file($_FILES['image']['tmp_name'],$file_path);
					move_uploaded_file($file_url);
			
			//	$sql = "INSERT INTO `tblmakanan` ( `makanan`,`foto_makanan`) VALUES ( '$namamakanan','$file_url');";
				$sql = "UPDATE tblmakanan SET makanan='$namamakanan' ,foto_makanan='$file_url' WHERE id_makanan='$id_makanan'";

				//adding the path and name to database 
				if(mysqli_query($connection,$sql)){
						echo 'update berhasil';
		
					//filling response array with values 
					// $response['error'] = false; 
					// $response['url'] = $file_url; 
					// $response['name'] = $name;
				}else{
			echo 'Terjadi kesalahan, ulangi lagi!';
		}
			//if some error occurred 
			}catch(Exception $e){
				$response['error']=true;
				$response['message']=$e->getMessage();
			}		
			//displaying the response 
			echo json_encode($response);
			
			//closing the connection 
			mysqli_close($connection);
		// }else{
		// 	$response['error']=true;
		// 	$response['message']='Please choose a file';
		// }
	}
	
	/*
		We are generating the file name 
		so this method will return a file name for the image to be upload 
	*/
	function getFileName(){
		include('db.php');    

		//$connection = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect...');
		$sql = "SELECT max(id_makanan) as id_makanan FROM tblmakanan";
		$sql1 = "SELECT min(id_makanan) as id_makanan FROM tblmakanan";
		
		$sql2 = "SELECT id_makanan as id_makanan FROM tblmakanan";

		$result = mysqli_fetch_array(mysqli_query($connection,$sql));
		$result1 = mysqli_fetch_array(mysqli_query($connection,$sql1));
		
		$result2 = mysqli_fetch_array(mysqli_query($connection,$sql2));
		mysqli_close($connection);
		if($result['id_makanan']==null)
			return 1; 
		else if(getFileName()==1)
			return $result2['id_makanan']; 
		else if($result2['id_makanan']==$result1['id_makanan'])
			return $result['id_makanan']; 
	}