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
		// $kota = 'jkt';
		// $notelp = '8222';
		// $username = 'sandi';
		// $level = 'admin';
		// $password = 'sandi11';
		
	//	$nim = $_POST['vsnim'];	
		$nama = $_POST['vsnama'];	
		
		$alamat = $_POST['vsalamat'];
		$jenkel = $_POST['vsjenkel'];
		$notelp = $_POST['vsnotelp'];
		$username = $_POST['vsusername'];
		$level = $_POST["vslevel"];
		$password = md5($_POST["vspassword"]);
// $id = $_POST["vspassword"];
	
			//getting name from the request 
			$name = $_POST['name'];
			
			//getting file info from the request 
			$fileinfo = pathinfo($_FILES['image']['name']);
			
			//getting the file extension 
			$extension = $fileinfo['extension'];
			
			//file url to store in the database 
			$file_url = $upload_url . $_FILES['image']['name'];
			
			//file path to upload in the server 
			$file_path = $upload_path . $_FILES['image']['name']; 
			
			//trying to save the file in the directory 
			try{
				//saving the file 
				move_uploaded_file($_FILES['image']['tmp_name'],$file_path);
				$sql = "INSERT INTO `tbluser` ( `nama`,`alamat`,`no_telp`,`jenkel`,`username`,`password`,`level`,`url`, `name`) VALUES ( '$nama', '$alamat', '$notelp', '$jenkel', '$username', '$password', '$level','$file_url', '$name');";
				
				//adding the path and name to database 
				if(mysqli_query($connection,$sql)){
						echo 'Setting berhasil';
		
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
		//$connection = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect...');
		$sql = "SELECT max(id_user) as id_user FROM tbluser";
		$result = mysqli_fetch_array(mysqli_query($connection,$sql));
		
		mysqli_close($connection);
		if($result['id_user']==null)
			return $name; 
		else 
			return ++$result['nam']; 
	}