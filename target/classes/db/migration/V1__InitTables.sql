-- Customer or user table
CREATE TABLE IF NOT EXISTS customer (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(75) NOT NULL UNIQUE,
    pswd VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL,
    last_updated TIMESTAMP
);

CREATE TYPE recipe_category AS ENUM ('appetizer', 'main dish', 'grilling', 'side dish', 'cookie', 'dessert');
-- Table for storing the category/type of a recipe
CREATE TABLE IF NOT EXISTS category (
    id SMALLSERIAL PRIMARY KEY,
    name recipe_category NOT NULL
);

-- Populates the category table
INSERT INTO category
    VALUES  (1, 'appetizer'),
            (2, 'main dish'),
            (3, 'grilling'),
            (4, 'side dish'),
            (5, 'cookie'),
            (6, 'dessert');

-- Main recipe table
CREATE TABLE IF NOT EXISTS recipe (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    note TEXT,
    created_at TIMESTAMP NOT NULL,
    last_updated TIMESTAMP,
    customer_id BIGINT,
    category_id SMALLINT,
    FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES category(id)
);

-- Table containing the steps/directions/tasks order for a recipe
CREATE TABLE IF NOT EXISTS task (
    id BIGSERIAL PRIMARY KEY,
    description TEXT NOT NULL,
    number SMALLINT NOT NULL,
    recipe_id BIGINT,
    FOREIGN KEY (recipe_id) REFERENCES recipe(id) ON DELETE CASCADE
);

-- Table containing list of ingredients for a recipe
CREATE TABLE IF NOT EXISTS ingredient (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    amount DECIMAL(10, 5) NOT NULL,
    measuring_unit VARCHAR (20) NOT NULL,
    recipe_id BIGINT,
    FOREIGN KEY (recipe_id) REFERENCES recipe(id) ON DELETE CASCADE
);