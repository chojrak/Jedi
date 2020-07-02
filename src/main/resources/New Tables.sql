create table zakony (id serial,
nazwa_zakonu varchar(400),
primary key (id))

;

create table jedis (id serial,
nazwa_Jedi varchar(400), 
kolor_Miecza varchar(400), 
moc int, 
strona_Mocy varchar(40), 
zakon_id int,
primary key (id),
foreign key(zakon_id) references zakony (id)
)

;

insert into zakony (nazwa_zakonu) values ('niezrzeszony');
insert into zakony (nazwa_zakonu) values ('CS_4');

;

insert into jedis (nazwa_Jedi, kolor_Miecza, moc, strona_Mocy, zakon_id) values (
'chojrak', 'czerwony', '10001', 'ciemna', 2)


