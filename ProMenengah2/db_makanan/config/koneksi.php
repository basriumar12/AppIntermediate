<?php

// definisikan koneksi ke database
$server = "localhost";
$username = "root";
$password = "";
$database = "db_makanan";

// Koneksi dan memilih database di server
$connection =mysqli_connect($server,$username,$password,$database) or die("Koneksi gagal");
//mysqli_select_db($database) or die("Database tidak bisa dibuka");
?>

