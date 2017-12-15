<?php 
        // $nama = $_GET['vsnama'];
        // $alamat=$_GET['vsalamat'];
        // $kota=$_GET['vskota'];
        // $notelp=$_GET['vsnotelp'];

        // $iduser=$_GET['vsiduser']; 
    $response = array();

          $vsusername = $_POST['edtusername'];
          $vspassword = md5($_POST['edtpassword']);
          $vslevel = $_POST['vslevel'];
     

        // $vsusername = 'sandi';
        // $vspassword = '827ccb0eea8a706c4c34a16891f84e7b';
        // $vslevel = 'admin';

       //  $nama ='andi';
       //  $alamat='andi';
       //  $kota='aceh';
       //  $notelp='123';
       //  $iduser='40';
       //  $vsusername = 'monica';
      // $vspassword = md5('monica');
       // $vslevel = 'admin';

//$vsuser="dini";
// //       $vsiddevice ='00:0a:00:57:f2:4d';
//$vsiddevice ='devicesatu'; 
//$con = mysqli_connect("localhost","root","", "dbpulsa");
   include './config/koneksi.php';
   

         
        #Query the database to get the user details. 
        $leveldetails = mysqli_query($connection, "SELECT * FROM tbluser WHERE username= '$vsusername' and level ='$vslevel' and password ='$vspassword' "); 
//      $leveldetails = mysqli_query($con, "SELECT nama as nm FROM tbluser WHERE nama= '$vsuser'"); 
        #If no data was returned, check for any SQL errors 
        if (!$leveldetails) { 
	echo 'Could not run query: ' . mysqli_error($connection); 
           		exit;
        } 
  
        #Get the first row of the results 
        $row = mysqli_fetch_row($leveldetails); 

        #Build the result array (Assign keys to the values) 
        $result_data = array( 
            'nama' => $row[0],
            'alamat' => $row[1],
            'jenkel' => $row[2],
            'no_telp'   => $row[3],
            'id_user'   => $row[4],
            'username' => $row[5],
		'password' => $row[6],
        'level' => $row[7]


            ); 



	
        #Output the JSON data 
      if (mysqli_num_rows($leveldetails) > 0) {
      $response['result'] = "1";
      $response['msg'] = "Berhasil login!!";
      $response['user'] = $result_data;
      
     // $response['username'] = $vsusername ;

    }else{
      $response['result'] = "0";
      $response['msg'] = "Gagal login!!";

    }
      echo json_encode($response);


    //   $response["success"] = 1;
    //   echo json_encode($response);
     
?>