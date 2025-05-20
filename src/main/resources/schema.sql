DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS recipe;

CREATE TABLE recipe (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        title VARCHAR(255),
                        description VARCHAR(255),
                        ingredients TEXT,
                        instructions TEXT
);

CREATE TABLE comment (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         author VARCHAR(255),
                         content TEXT,
                         rating INT,
                         recipe_id BIGINT,
                         CONSTRAINT fk_recipe FOREIGN KEY (recipe_id) REFERENCES recipe(id)
);