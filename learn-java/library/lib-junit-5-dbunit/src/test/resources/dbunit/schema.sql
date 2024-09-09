CREATE TABLE IF NOT EXISTS ITEMS
(
    `id`       int PRIMARY KEY AUTO_INCREMENT NOT NULL,
    `title`    varchar(100)       NOT NULL,
    `produced` date,
    `price`    float
);
