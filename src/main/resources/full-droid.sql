-- currently, the tables are being created and updated by hibernate

-- migration to version 1.6
alter table Event change creator longblob;
alter table Role change user longblob;
alter table Role drop user;
update Role set user_id = 1 where id = 1;
update Role set user_id = 2 where id = 2;
alter table Event drop creator;