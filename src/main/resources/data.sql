INSERT INTO recipe (title, description, ingredients, instructions) VALUES
('Tomato Soup', 'Warm and comforting', 'Tomatoes, onions, garlic, cream, spices', 'Cook tomatoes with garlic and onions. Blend. Add cream. Simmer.'),
('Spaghetti Carbonara', 'Classic Italian pasta', 'Spaghetti, eggs, pancetta, parmesan', 'Boil pasta. Cook pancetta. Mix with egg and cheese. Combine.'),
('Pancakes', 'Fluffy breakfast treat', 'Flour, eggs, milk, sugar, baking powder', 'Mix ingredients. Fry on pan.');

INSERT INTO comment (author, content, rating, recipe_id) VALUES
('Alice', 'Delicious and easy to make!', 5, 1),
('Bob', 'Needed more salt.', 3, 1),
('Charlie', 'Perfect for brunch.', 4, 2);