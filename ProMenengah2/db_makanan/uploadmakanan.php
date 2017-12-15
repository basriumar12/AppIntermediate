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
		$namamakanan = $_POST['vsnamamakanan'];	
//		$namamakanan = 'ktp';	

// $id = $_POST["vspassword"];
			//getting name from the request 
			$name = 'fotomakanan';

		    // $vsid = '54';
		     $vsid = $_POST['vsiduser'];
		
			//getting file info from the request 
			$fileinfo = pathinfo($_FILES['image']['name']);
			//getting the file extension 
			$extension = $fileinfo['extension'];
			//file url to store in the database 
			$file_url = $upload_url . $_FILES['image']['name'];
			
		//	$file_url = 'http://192.168.20.35/db_makanan/uploads/rendang.jpg';
			//file path to upload in the server 
			$file_path = $upload_path . $_FILES['image']['name']; 
			
			//trying to save the file in the directory 
			try{

				//INSERT INTO tblmakanan ( `makanan`,`foto_makanan`,`id_user`) VALUES ( 'ktp','asds','54')   FROM tblmakanan tm,tbluser tu WHERE tm.id_user=54 AND tm.id_user=tu.id_user

				//saving the file 
				move_uploaded_file($_FILES['image']['tmp_name'],$file_path);
				$sql = "INSERT INTO tblmakanan ( `makanan`,`foto_makanan`,`id_user`) VALUES ( '$namamakanan','$file_url','$vsid')";
				
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
		include('db.php');    

		//$connection = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect...');
		$sql = "SELECT max(id_makanan) as id_makanan FROM tblmakanan";

		$sql1 = "SELECT id_makanan as id_makanan FROM tblmakanan";
		
		$result = mysqli_fetch_array(mysqli_query($connection,$sql));
		$result1 = mysqli_fetch_array(mysqli_query($connection,$sql1));
		
		mysqli_close($connection);
		if($result1['id_makanan']==null)
			return 1; 
		else 
			return ++$result['id_makanan']; 
	}