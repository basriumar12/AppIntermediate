<?php
//koneksi
// definisikan koneksi ke database
 include './config/koneksi.php';
  //mysqli_select_db($database) or die("Database tidak bisa dibuka");
   
    //query untuk menampilkan semua data ditable
    $sql=mysqli_query($con,"SELECT * FROM tblmakanan ORDER BY id_makanan desc");
    //untuk menampung isi data
    $response=array();
    $cek=mysqli_num_rows($sql);
    if($cek >0){
        $response["buku"]=array();
        //perulangan
        while ($row=mysqli_fetch_array($sql)){
            $data=array();
            $data["id_buku1"]=$row["id_buku"];
            $data["judul1"]=$row["judul"];
            $data["isi_buku1"]=$row["isi_buku"];
           $data["gambar1"]=$row["gambar"];
           $data["harga1"]=$row["harga"];
           $data["status1"]=$row["status"];
            
         $response["pesan"]="berhasil Mengambil Data";
         $response["sukses"]="true";    
            array_push($response["buku"],$data);
        }
        echo json_encode($response);
    }else{
        $response["pesan"]="Gagal Mengambil Data";
        $response["sukses"]="false";
        echo json_encode($response);
    } 

?>
