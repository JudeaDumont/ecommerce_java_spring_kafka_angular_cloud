-- Inserting data into the category table
insert into category (id, description, name)
values
    (nextval('category_seq'), 'Electronics', 'Electronics Category'),
    (nextval('category_seq'), 'Clothing', 'Clothing Category'),
    (nextval('category_seq'), 'Books', 'Books Category');

-- Inserting data into the product table
insert into product (id, description, name, available_quantity, price, category_id)
values
    (nextval('product_seq'), 'Smartphone model X', 'Smartphone X', 100, 599.99, (select id from category where name = 'Electronics Category')),
    (nextval('product_seq'), 'T-shirt size M', 'T-shirt M', 50, 19.99, (select id from category where name = 'Clothing Category')),
    (nextval('product_seq'), 'Book: Introduction to SQL', 'Intro SQL Book', 30, 29.99, (select id from category where name = 'Books Category'));
