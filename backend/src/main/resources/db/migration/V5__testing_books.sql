-- Shtimi i një autori të ri
INSERT INTO authors (id ,first_name, last_name, biography, birth_date, nationality)
VALUES (1, 'Ismail', 'Kadare', 'Shkrimtar i njohur shqiptar me vepra të përkthyera ndërkombëtarisht.', '1936-01-28', 'Shqiptar');

-- Merr ID-n e autorit që u fut (nëse databaza e mbështet këtë)
-- ose mund ta marrësh me një query si:
-- SELECT id FROM authors WHERE first_name = 'Ismail' AND last_name = 'Kadare';

-- Supozojmë që autori mori ID = 1 (ndryshoje nëse është ndryshe)
-- Supozojmë gjithashtu që category_id = 1 dhe publisher_id = 1 ekzistojnë
-- Fut një kategori test
INSERT INTO categories (tenant_id, name, description)
VALUES (1, 'Roman', 'Romane letrare të ndryshme');

INSERT INTO publishers (name, tenant_id)
VALUES ('Toena', 1);


-- Shtimi i librit 1
INSERT INTO book (id,title, published_year, available_copies, tenant_id, author_id, category_id, publisher_id)
VALUES (1,'Prilli i Thyer', 1978, 5, 1, 1, 1, 1);

-- Shtimi i librit 2
INSERT INTO book (id,title, published_year, available_copies, tenant_id, author_id, category_id, publisher_id)
VALUES (2,'Kronikë në Gur', 1971, 4, 1, 1, 1, 1);

-- Shtimi i librit 3
INSERT INTO book (id,title, published_year, available_copies, tenant_id, author_id, category_id, publisher_id)
VALUES (3,'Pallati i Ëndrrave', 1981, 3, 1, 1, 1, 1);
