drop table if exists products;
create table products(id smallint auto_increment, name varchar(20), shortDesc varchar(50), descp varchar (200), price decimal(6,2), img varchar(40), primary key (id));
insert into products (id, name, shortDesc, descp, price,img) values (null,'Red Fedora','Red Hot Fedora for all occasions','Worn by all that are passionate about opensource softwares!',25.99,'img/red_fedora_sm.png');
insert into products (id, name, shortDesc, descp, price,img) values (null,'Tux Doll','The kernel mascot!','The concept of the Linux brand character being a penguin came from Linus Torvalds, the creator of Linux.',35.99,'img/tux_sm.png');
insert into products (id, name, shortDesc, descp, price,img) values (null,'Fabric8 Robot','The integrated development platform','opinionated open source Microservices Platform based on Docker, Kubernetes and Jenkins',55.99,'img/fabric_sm.png');
insert into products (id, name, shortDesc, descp, price,img) values (null,'WildFly Plush Toy','Agile and Free!','JBoss Application Server has a new name...and it is even faster than before',105.99,'img/fly_sm.png');
insert into products (id, name, shortDesc, descp, price,img) values (null,'Container','everything you need in a package','Build once, run anywhere, all over again!',85.99,'img/container_sm.png');