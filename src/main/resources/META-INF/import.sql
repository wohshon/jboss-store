-- drop table if exists orders_orderItems;
-- drop table if exists orderItems;
-- drop table if exists orders;
-- drop table if exists products;
-- drop table if exists customers;
-- create table products(id smallint auto_increment, name varchar(20), productId varchar(10), shortDesc varchar(50), descp varchar (200), price decimal(6,2), img varchar(40), primary key (id));
-- create table customers(id smallint auto_increment, name varchar(20), email varchar(20), address varchar(50), contact varchar (20), primary key (id));

insert into products (id, name, productId, shortDesc, descp, price,img) values (null,'Red Fedora','p0001', 'Red Hot Fedora for all occasions','Red Fedora, comes in sizes from XS to XXL, handcrafted!',25.99,'img/red_fedora_sm.png');
insert into products (id, name, productId, shortDesc, descp, price,img) values (null,'Tux Doll','p0002','The kernel mascot!','Life sized Pengiun Soft Toy, 50cm tall, comes with autographed Thank You card from Linus Torvalds.',35.99,'img/tux_sm.png');
insert into products (id, name, productId, shortDesc, descp, price,img) values (null,'Fabric8 Robot','p0003','The integrated development platform','Voice controlled Robot that can shoots laser beam and juggle metal cubes',55.99,'img/fabric_sm.png');
insert into products (id, name, productId, shortDesc, descp, price,img) values (null,'WildFly Plush Toy','p0004','Agile and Free!','Remote Controll Drone, long lasting battery life up to 5 days',105.99,'img/fly_sm.png');
insert into products (id, name, productId, shortDesc, descp, price,img) values (null,'Containers','p0005','everything you need in a package','Build once, run anywhere, all over again!',85.99,'img/container_sm.png');


insert into customers(id, name, email, address, contact) values (null, 'John Doe', 'johnd@gmail.com', '100, Shenton Way, 10-10 Singapore 123456','+65-1234567');
insert into customers(id, name, email, address, contact) values (null, 'Jane Doe', 'janed@gmail.com', '102, Shenton Way, 10-11 Singapore 123456','+65-12344567');
insert into customers(id, name, email, address, contact) values (null, 'Jeffe Doe', 'jeff@gmail.com', 'MBS, 10-11 Singapore 55555','+65-8844567');

insert into inventory(id, name, product, stock, uom) values (null, 'p0001','p0001', 50, 'UNIT' );
insert into inventory(id, name, product, stock, uom) values (null, 'p0002','p0002', 50, 'UNIT' );
insert into inventory(id, name, product, stock, uom) values (null, 'p0003','p0003', 50, 'UNIT' );
insert into inventory(id, name, product, stock, uom) values (null, 'p0004', 'p0004', 50, 'UNIT' );
insert into inventory(id, name, product, stock, uom) values (null, 'p0005', 'p0005', 50, 'UNIT' );