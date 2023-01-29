-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 29 Sty 2023, 14:19
-- Wersja serwera: 10.4.25-MariaDB
-- Wersja PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `jdbcdatabase`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `faktury`
--

CREATE TABLE `faktury` (
  `nr_faktury` int(11) NOT NULL,
  `nazwa_klienta` varchar(50) COLLATE utf8mb4_polish_ci NOT NULL,
  `koszyk` text COLLATE utf8mb4_polish_ci NOT NULL,
  `kwota` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

--
-- Zrzut danych tabeli `faktury`
--

INSERT INTO `faktury` (`nr_faktury`, `nazwa_klienta`, `koszyk`, `kwota`) VALUES
(1, 'Januszex', 'Folia na ekran Iphone 11,Szkło hartowane Samsung S21', 68.3),
(3, 'KowalskiTech', 'Szkło hartowane Iphone 12 Pro', 55),
(4, 'Telepol', 'Folia na ekran Iphone 11,Folia na ekran Iphone 11,Szkło hartowane Samsung S21', 99.8),
(5, 'Serwis u Mirka', 'Folia na ekran Iphone 11,Szkło hartowane Iphone 11', 73.6),
(6, 'Telepol', 'Folia na ekran Iphone 11,Kabel Baseus USB-C 3m', 50.5),
(7, 'KowalskiTech', 'Folia na ekran Samsung A22', 35),
(8, '', 'Kabel USB - Lightning APPLE 1m', 119),
(10, '', 'Szkło hartowane Samsung S21', 36.8),
(11, '', 'Etui SPIGEN IPhone 12 Mini  ', 48.5);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `klienci`
--

CREATE TABLE `klienci` (
  `id_klienta` int(11) NOT NULL,
  `nazwa_klienta` varchar(50) COLLATE utf8mb4_polish_ci NOT NULL,
  `nip_klienta` varchar(10) COLLATE utf8mb4_polish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

--
-- Zrzut danych tabeli `klienci`
--

INSERT INTO `klienci` (`id_klienta`, `nazwa_klienta`, `nip_klienta`) VALUES
(1, 'KowalskiTech', '5582377789'),
(2, 'MajsterJanusz', '7786981163'),
(3, 'Serwis u Mirka', '8890035621'),
(4, 'Januszex', '6663588122'),
(6, 'Telepol', '8431166893');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `produkty`
--

CREATE TABLE `produkty` (
  `nazwa` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `kod` int(4) UNSIGNED ZEROFILL NOT NULL,
  `kategoria` varchar(40) COLLATE utf8_polish_ci NOT NULL,
  `cena` decimal(8,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `produkty`
--

INSERT INTO `produkty` (`nazwa`, `kod`, `kategoria`, `cena`) VALUES
('Etui SPIGEN IPhone 12', 1001, 'Etui, folie i szkła', '47.50'),
('Etui SPIGEN Samsung S22 ', 1002, 'Etui, folie i szkła', '39.00'),
('Etui SPIGEN IPhone 12 Mini  ', 1003, 'Etui, folie i szkła', '48.50'),
('Folia na ekran Samsung S22', 1004, 'Etui, folie i szkła', '23.50'),
('Folia na ekran Iphone 12', 1005, 'Etui, folie i szkła', '25.00'),
('Folia na ekran Iphone 12 Mini', 1006, 'Etui, folie i szkła', '25.00'),
('Kabel Samsung USB-C 1,5m', 1007, 'Kable i ładowarki', '40.00'),
('Kabel Baseus USB-C 3m', 1008, 'Kable i ładowarki', '19.00'),
('Kabel Reinston USB-C 0,6m', 1009, 'Kable i ładowarki', '39.00'),
('Kabel USB-C - Lightning APPLE 1m', 1010, 'Kable i ładowarki', '119.00'),
('Kabel USB - Lightning APPLE 1m', 1011, 'Kable i ładowarki', '119.00'),
('Ładowarka sieciowa APPLE 20W', 1013, 'Kable i ładowarki', '124.00'),
('Ładowarka sieciowa SAMSUNG 25W', 1014, 'Kable i ładowarki', '101.00'),
('Ładowarka sieciowa 51W', 1015, 'Kable i ładowarki', '99.00'),
('Powerbank HAMA 5000mAh', 1016, 'Powerbanki', '69.00'),
('Powerbank GREEN CELL 20000mAh', 1017, 'Powerbanki', '189.00'),
('Powerbank 4SMARTS 10000mAh', 1018, 'Powerbanki', '129.00'),
('Etui SPIGEN Samsung S21', 1019, 'Etui, folie i szkła', '74.00'),
('Etui SPIGEN Samsung A22', 1020, 'Etui, folie i szkła', '39.00'),
('Etui SPIGEN Iphone 11', 1021, 'Etui, folie i szkła', '55.50'),
('Folia na ekran Iphone 11', 1022, 'Etui, folie i szkła', '31.50'),
('Folia na ekran Samsung A22', 1023, 'Etui, folie i szkła', '35.00'),
('Folia na ekran Samsung S21', 1024, 'Etui, folie i szkła', '29.90'),
('Szkło hartowane Samsung S21', 1025, 'Etui, folie i szkła', '36.80'),
('Szkło hartowane Iphone 12 Pro', 1026, 'Etui, folie i szkła', '55.00'),
('Szkło hartowane Iphone 11', 1027, 'Etui, folie i szkła', '42.10'),
('Szkło hartowane Iphone 12 Pro Max', 1029, 'Etui, folie i szkła,', '72.00'),
('Kabel Baseus USB-C 0,1m', 1031, 'Kable i ładowarki,', '19.00');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `uzytkownicy`
--

CREATE TABLE `uzytkownicy` (
  `id_uzytkownika` int(11) NOT NULL,
  `nazwa_uzytkownika` varchar(25) COLLATE utf8mb4_polish_ci NOT NULL,
  `haslo_uzytkownika` varchar(25) COLLATE utf8mb4_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

--
-- Zrzut danych tabeli `uzytkownicy`
--

INSERT INTO `uzytkownicy` (`id_uzytkownika`, `nazwa_uzytkownika`, `haslo_uzytkownika`) VALUES
(1, 'admin', 'admin'),
(5, 'user', 'user');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `faktury`
--
ALTER TABLE `faktury`
  ADD PRIMARY KEY (`nr_faktury`);

--
-- Indeksy dla tabeli `klienci`
--
ALTER TABLE `klienci`
  ADD PRIMARY KEY (`id_klienta`);

--
-- Indeksy dla tabeli `produkty`
--
ALTER TABLE `produkty`
  ADD PRIMARY KEY (`kod`);

--
-- Indeksy dla tabeli `uzytkownicy`
--
ALTER TABLE `uzytkownicy`
  ADD PRIMARY KEY (`id_uzytkownika`);

--
-- AUTO_INCREMENT dla zrzuconych tabel
--

--
-- AUTO_INCREMENT dla tabeli `faktury`
--
ALTER TABLE `faktury`
  MODIFY `nr_faktury` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT dla tabeli `klienci`
--
ALTER TABLE `klienci`
  MODIFY `id_klienta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT dla tabeli `produkty`
--
ALTER TABLE `produkty`
  MODIFY `kod` int(4) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1032;

--
-- AUTO_INCREMENT dla tabeli `uzytkownicy`
--
ALTER TABLE `uzytkownicy`
  MODIFY `id_uzytkownika` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
