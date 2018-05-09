INSERT INTO position (`id`, `name`)
VALUES (1, 'Official')
ON DUPLICATE KEY UPDATE name = name;

INSERT INTO country (`id`, `iso`, `name`)
VALUES (2804, 'UA', 'Ukraine')
ON DUPLICATE KEY UPDATE iso = iso, name = name;

INSERT INTO place (`id`, `name`, `canonical_name`, `type`, `country_id`)
VALUES
  (1012835, 'Vinnytsia', 'Vinnytsia, Vinnyts\'ka oblast, Ukraine', 'CITY', 2804),
  (1012836, 'Lutsk', 'Lutsk, Volyns\'ka oblast, Ukraine', 'CITY', 2804),
  (1012837, 'Lysychans\'k', 'Lysychans\'k, Luhans\'ka oblast, Ukraine', 'CITY', 2804),
  (1012838, 'Luhansk', 'Luhansk, Luhans\'ka oblast, Ukraine', 'CITY', 2804),
  (1012839, 'Dnipro', 'Dnipro, Dnipropetrovsk Oblast, Ukraine', 'CITY', 2804),
  (1012840, 'Kryvyi Rih', 'Kryvyi Rih, Dnipropetrovsk Oblast, Ukraine', 'CITY', 2804),
  (1012841, 'Nikopol\'', 'Nikopol\', Dnipropetrovsk Oblast, Ukraine', 'CITY', 2804),
  (1012842, 'Donetsk', 'Donetsk, Donetsk Oblast, Ukraine', 'CITY', 2804),
  (1012843, 'Horlivka', 'Horlivka, Donetsk Oblast, Ukraine', 'CITY', 2804),
  (1012844, 'Makiivka', 'Makiivka, Donetsk Oblast, Ukraine', 'CITY', 2804),
  (1012845, 'Mariupol\'', 'Mariupol\', Donetsk Oblast, Ukraine', 'CITY', 2804),
  (1012846, 'Zhytomyr', 'Zhytomyr, Zhytomyrs\'ka oblast, Ukraine', 'CITY', 2804),
  (1012847, 'Uzhhorod', 'Uzhhorod, Zakarpats\'ka oblast, Ukraine', 'CITY', 2804),
  (1012848, 'Melitopol\'', 'Melitopol\', Zaporiz\'ka oblast, Ukraine', 'CITY', 2804),
  (1012849, 'Zaporizhia', 'Zaporizhia, Zaporiz\'ka oblast, Ukraine', 'CITY', 2804),
  (1012850, 'Ivano-Frankivsk', 'Ivano-Frankivsk, Ivano-Frankivs\'ka oblast, Ukraine', 'CITY', 2804),
  (1012851, 'Bila Tserkva', 'Bila Tserkva, Kyivs\'ka oblast, Ukraine', 'CITY', 2804),
  (1012852, 'Kiev', 'Kiev, Kyiv city, Ukraine', 'CITY', 2804),
  (1012853, 'Kropyvnytskyi', 'Kropyvnytskyi, Kirovohrads\'ka oblast, Ukraine', 'CITY', 2804),
  (1012854, 'Rivne', 'Rivne, Kirovohrads\'ka oblast, Ukraine', 'CITY', 2804),
  (1012859, 'Lviv', 'Lviv, Lviv Oblast, Ukraine', 'CITY', 2804),
  (1012860, 'Mykolaiv', 'Mykolaiv, Pustomytivs\'kyi district, L\'vivs\'ka, Ukraine', 'CITY', 2804),
  (1012861, 'Odessa', 'Odessa, Odessa Oblast, Ukraine', 'CITY', 2804),
  (1012862, 'Kremenchuk', 'Kremenchuk, Poltavs\'ka oblast, Ukraine', 'CITY', 2804),
  (1012863, 'Poltava', 'Poltava, Poltavs\'ka oblast, Ukraine', 'CITY', 2804),
  (1012864, 'Sumy', 'Sumy, Sums\'ka oblast, Ukraine', 'CITY', 2804),
  (1012865, 'Ternopil', 'Ternopil, Ternopil\'s\'ka oblast, Ukraine', 'CITY', 2804),
  (1012866, 'Kharkiv', 'Kharkiv, Kharkiv Oblast, Ukraine', 'CITY', 2804),
  (1012867, 'Kherson', 'Kherson, Khersons\'ka oblast, Ukraine', 'CITY', 2804),
  (1012868, 'Khmelnytskyi', 'Khmelnytskyi, Khmel\'nyts\'ka oblast, Ukraine', 'CITY', 2804),
  (1012869, 'Cherkasy', 'Cherkasy, Cherkas\'ka oblast, Ukraine', 'CITY', 2804),
  (1012870, 'Chernihiv', 'Chernihiv, Chernihivs\'ka oblast, Ukraine', 'CITY', 2804),
  (1012871, 'Chernivtsi', 'Chernivtsi, Chernivets\'ka oblast, Ukraine', 'CITY', 2804)
ON DUPLICATE KEY UPDATE canonical_name = canonical_name, name = name, type = type, country_id = country_id;