CREATE TABLE IF NOT EXISTS QUERY_LOG (
    id INT AUTO_INCREMENT PRIMARY KEY,
    characterName VARCHAR(100) NOT NULL,
    characterId INT NOT NULL
);

INSERT INTO QUERY_LOG (characterName, characterId) VALUES
    ('Hulk', 1009351);