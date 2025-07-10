-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jul 08, 2025 at 06:51 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `autoaxis`
--

-- --------------------------------------------------------

--
-- Table structure for table `bookings`
--

CREATE TABLE `bookings` (
  `booking_id` varchar(10) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `vehicle_id` int(11) NOT NULL,
  `pickup_date` date NOT NULL,
  `return_date` date NOT NULL,
  `total_amount` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `bookings`
--

INSERT INTO `bookings` (`booking_id`, `customer_id`, `vehicle_id`, `pickup_date`, `return_date`, `total_amount`) VALUES
('AR905715', 7, 5, '2025-07-08', '2025-07-26', 730800.00);

-- --------------------------------------------------------

--
-- Table structure for table `car`
--

CREATE TABLE `car` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `model` varchar(100) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `is_available` tinyint(1) DEFAULT 1,
  `description` text DEFAULT NULL,
  `seats` int(11) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `transmission` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `car`
--

INSERT INTO `car` (`id`, `name`, `model`, `year`, `price`, `type`, `is_available`, `description`, `seats`, `image_url`, `transmission`) VALUES
(1, 'Mercedes', 'Maybach', 2023, 24000.00, 'Sedan', 1, 'Luxury sedan with premium features and exceptional comfort', 5, '/Images/s-class.jpg', 'Automatic'),
(2, 'Mercedes', 'S-class', 2023, 32500.00, 'Sedan', 1, 'Premium luxury sedan with advanced technology and superior performance', 5, '/Images/mercedes-S-class-sport.jpg', 'Automatic'),
(3, 'Mercedes', 'GLE', 2023, 44000.00, 'SUV', 1, 'Spacious luxury SUV with excellent road presence and comfort', 5, '/Images/mercede-gle.jpg', 'Automatic'),
(4, 'Porsche', '911 Turbo', 2023, 39000.00, 'Sports', 1, 'High-performance sports car with exceptional speed and handling', 5, '/Images/porsche-sports.jpg', 'Automatic'),
(5, 'Toyota', 'Crown', 2023, 35000.00, 'Sedan', 1, 'Reliable luxury sedan with excellent build quality', 5, '/Images/toyota-crown.jpg', 'Manual'),
(6, 'Porsche', 'Cayenne', 2023, 45000.00, 'SUV', 1, 'Premium SUV combining luxury with sporty performance', 5, '/Images/porsche-suv.jpg', 'Automatic'),
(7, 'Mercedes', 'E-class', 2023, 15000.00, 'Sedan', 1, 'Mid-size luxury sedan with excellent comfort and reliability', 5, '/Images/mercedes-E-class.jpg', 'Automatic'),
(8, 'Toyota', 'Landcruiser', 2023, 50000.00, 'SUV', 1, 'Rugged and reliable SUV perfect for all terrains', 5, '/Images/toyota-landcruiser.jpg', 'Manual'),
(9, 'BMW', 'M4', 2023, 42000.00, 'Sports', 1, 'High-performance sports coupe with aggressive styling', 5, '/Images/bmw-m4.jpg', 'Automatic');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `email`, `password`, `role`) VALUES
(1, 'admin1', 'admin1@gmail.com', 'pass', 'admin'),
(2, 'admin2', 'admin2@example.com', 'pass2', 'admin'),
(3, 'admin3', 'admin3@example.com', 'pass3', 'admin'),
(4, 'admin4', 'admin4@example.com', 'pass4', 'admin'),
(5, 'admin5', 'admin5@example.com', 'pass5', 'admin'),
(6, 'kariuki', 'kariuki@gmail.com', 'pass', 'renter'),
(7, 'james', 'james@gmail.com', 'pass', 'renter');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bookings`
--
ALTER TABLE `bookings`
  ADD PRIMARY KEY (`booking_id`),
  ADD KEY `customer_id` (`customer_id`),
  ADD KEY `vehicle_id` (`vehicle_id`);

--
-- Indexes for table `car`
--
ALTER TABLE `car`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `car`
--
ALTER TABLE `car`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bookings`
--
ALTER TABLE `bookings`
  ADD CONSTRAINT `bookings_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `bookings_ibfk_2` FOREIGN KEY (`vehicle_id`) REFERENCES `car` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
