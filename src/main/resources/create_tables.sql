DROP TABLE IF EXISTS CUSTOMER CASCADE;
DROP TABLE IF EXISTS AUTHOR CASCADE;
DROP TABLE IF EXISTS BOOK CASCADE;
DROP TABLE IF EXISTS BOOK_AUTHOR CASCADE;
DROP TABLE IF EXISTS CART CASCADE;
DROP TABLE IF EXISTS CART_ITEM CASCADE;
DROP TABLE IF EXISTS CUSTOMER_ORDER CASCADE;
DROP TABLE IF EXISTS CUSTOMER_ORDER_ITEM CASCADE;

CREATE TABLE CUSTOMER
(
    id         BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    email      varchar2(320) unique not null,
    first_name varchar2(255)        not null,
    last_name  varchar2(255)        not null,
    password   varchar2(16)         not null
);

CREATE TABLE AUTHOR
(
    id         BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    first_name varchar2(255) not null,
    last_name  varchar2(255) not null
);

CREATE TABLE BOOK
(
    id                BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title             varchar2(255)  not null,
    price             decimal(10, 2) not null,
    author_id         BIGINT         NOT NULL,
    CONSTRAINT fk_book_author FOREIGN KEY (author_id) REFERENCES author (id)
);

CREATE TABLE BOOK_AUTHOR
(
    id        BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    book_id   bigint not null,
    author_id bigint not null,
    constraint fk_book foreign key (book_id) references BOOK (id),
    constraint fk_author foreign key (author_id) references AUTHOR (id)
);

CREATE TABLE CART
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    customer_id bigint         not null,
    constraint fk_cart_customer foreign key (customer_id) references CUSTOMER (id)
);

CREATE TABLE CART_ITEM
(
    id       BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    cart_id  bigint         not null,
    book_id  bigint         not null,
    quantity int            not null,
    price    DECIMAL(10, 2) not null,
    constraint fk_cart_item_book FOREIGN KEY (book_id) REFERENCES book (id),
    constraint fk_cart_item_cart FOREIGN KEY (cart_id) REFERENCES cart (id)
);

CREATE TABLE CUSTOMER_ORDER
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    customer_id bigint         not null,
    total_price DECIMAL(10, 2) not null,
    status      varchar2(15)   not null,
    created_at  timestamp      not null,
    constraint fk_customer_order_customer foreign key (customer_id) references CUSTOMER (id)
);

CREATE TABLE CUSTOMER_ORDER_ITEM
(
    id       BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    customer_order_id bigint         not null,
    book_id  bigint         not null,
    quantity integer        not null,
    price    DECIMAL(10, 2) not null,
    constraint fk_customer_order_item_customer_order foreign key (customer_order_id) references CUSTOMER_ORDER (id),
    constraint fk_customer_order_item_book foreign key (book_id) references BOOK (id)
);
