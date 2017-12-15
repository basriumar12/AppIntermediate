-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jul 17, 2017 at 08:24 PM
-- Server version: 10.1.19-MariaDB
-- PHP Version: 5.5.38

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `crud_makanan`
--

-- --------------------------------------------------------

--
-- Table structure for table `tblmakanan`
--

CREATE TABLE `tblmakanan` (
  `makanan` varchar(30) NOT NULL,
  `id_makanan` int(20) NOT NULL,
  `foto_makanan` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tblmakanan`
--

INSERT INTO `tblmakanan` (`makanan`, `id_makanan`, `foto_makanan`) VALUES
('jdskajdks', 82, 'http://192.168.43.117/crudmakanan/uploads/1.jpg'),
('sdsd', 83, 'http://192.168.43.117/crudmakanan/uploads/83.jpg'),
('dsd', 84, 'http://192.168.43.117/crudmakanan/uploads/84.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `tbluser`
--

CREATE TABLE `tbluser` (
  `nama` varchar(30) NOT NULL,
  `alamat` varchar(30) NOT NULL,
  `jenkel` varchar(30) NOT NULL,
  `no_telp` int(12) NOT NULL,
  `id_user` int(11) NOT NULL,
  `username` varchar(130) NOT NULL,
  `password` varchar(120) NOT NULL,
  `level` varchar(20) NOT NULL,
  `name` varchar(30) NOT NULL,
  `url` varchar(120) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbluser`
--

INSERT INTO `tbluser` (`nama`, `alamat`, `jenkel`, `no_telp`, `id_user`, `username`, `password`, `level`, `name`, `url`) VALUES
('jsjjs', 'hshsh', 'laki-laki', 618181, 11, 'hehdhd', '7fa8282ad93047a4d6fe6111c93b308a', 'admin', 'hdheh', 'http://192.168.95.134/crudmakanan/uploads/1.jpg'),
('tri', 'tri', 'Laki-laki', 0, 12, 'trii', '96e79218965eb72c92a549dd5a330112', 'Admin', 'maka', 'http://192.168.95.134/crudmakanan/uploads/1.jpg'),
('tri', 'tri', 'Laki-laki', 0, 13, 'trii', '96e79218965eb72c92a549dd5a330112', 'Admin', 'maka', 'http://192.168.95.134/crudmakanan/uploads/1.jpg'),
('test', 'test', 'Laki-laki', 0, 14, 'triri', 'e3ceb5881a0a1fdaad01296d7554868d', 'Admin', 'trr', 'http://192.168.95.134/crudmakanan/uploads/1.jpg'),
('andi', 'padang', 'laki-laki', 88888888, 15, 'sandi', '7fa8282ad93047a4d6fe6111c93b308a', 'admin', 'ima', 'http://192.168.95.134/crudmakanan/uploads/1.jpg'),
('qwerty', 'qwerty', 'laki-laki', 123456789, 16, 'qwerty', 'fcea920f7412b5da7be0cf42b8c93759', 'admin', 'qwert', 'http://192.168.95.20/crudmakanan/uploads/1.jpg'),
('sandi', 'padang', 'laki-laki', 2147483647, 17, 'sandi', 'fcea920f7412b5da7be0cf42b8c93759', 'admin', 'sandi', 'http://192.168.43.117/crudmakanan/uploads/1.png'),
('tari', 'jkt', 'perempuan', 2147483647, 18, 'tari', 'fcea920f7412b5da7be0cf42b8c93759', 'admin', '', 'http://192.168.95.87/crudmakanan/uploads/1.jpeg'),
('tari', 'jkt', 'laki-laki', 2147483647, 19, 'tari', 'fcea920f7412b5da7be0cf42b8c93759', 'admin', 'tariii', 'http://192.168.95.87/progar/uploads/1.jpeg'),
('jkdjfkdjsk', 'jkjfdskjks', 'laki-laki', 62232323, 20, 'iswandi', 'fcea920f7412b5da7be0cf42b8c93759', 'admin', '', 'http://192.168.1.149/crudmakanan/uploads/1.jpg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tblmakanan`
--
ALTER TABLE `tblmakanan`
  ADD PRIMARY KEY (`id_makanan`);

--
-- Indexes for table `tbluser`
--
ALTER TABLE `tbluser`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tblmakanan`
--
ALTER TABLE `tblmakanan`
  MODIFY `id_makanan` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=85;
--
-- AUTO_INCREMENT for table `tbluser`
--
ALTER TABLE `tbluser`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
