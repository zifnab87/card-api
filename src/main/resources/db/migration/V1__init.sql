create table country_cost (cost decimal(38,2), id integer not null auto_increment, country varchar(255), primary key (id)) engine=InnoDB;
alter table country_cost add constraint UK_s529x6dbfvl5qwnj8k0tf4df9 unique (country);
insert into country_cost (cost, country) values (10, 'Others');