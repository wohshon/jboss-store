drop table if exists products;
create table products(id smallint auto_increment, name varchar(20), shortDesc varchar(50), descp varchar (200), price decimal(6,2), img varchar(40), primary key (id));
insert into products values (null,'Red Fedora','Red Hot Fedora for all occasions','Worn by all that are passionate about opensource softwares!',25.99,'img/red_fedora_sm.png');
insert into products values (null,'Tux Doll','The kernel mascot!','(T)orvalds (U)ni(X)... Need we say more??!',35.99,'img/tux_sm.png');
insert into products values (null,'Fabric8 Robot','The container juggler','startup, shutdown, toss and throw your containers around but never miss a beat!',55.99,'img/fabric_sm.png');
insert into products values (null,'WidFly RC Plane','The most agile flying monster around','size does not matter, wildfly tackles all kind of workload despite its lightweight and nimble footprint!',105.99,'img/fly_sm.png');
insert into products values (null,'Container','everything you need in a package','Always run the same, regardless of the environment it runs in!',85.99,'img/container_sm.png');