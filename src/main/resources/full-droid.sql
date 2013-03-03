create table User (
  birth datetime,
  email varchar(255),
  id int primary key auto_increment,
  name varchar(255),
  telephone int
);
create table Event (
  id int primary key auto_increment,
  creator int,
  constraint foreign key (creator) references User (id),
  dateEnd timestamp,
  dateStart timestamp,
  description varchar(255),
  done boolean,
  privacy varchar(255),
  repetition varchar(255),
  title varchar(255)
);
create table Location (
  id int primary key auto_increment,
  address varchar(255),
  description varchar(255),
  founded datetime,
  image varchar(255),
  title varchar(255),
  url varchar(255)
);
create table Reminder (
  id int primary key auto_increment,
  minutes bigint,
  notification varchar(255)
);