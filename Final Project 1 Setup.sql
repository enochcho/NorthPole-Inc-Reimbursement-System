
--drop order
drop view total;
drop table ers_reimbursement;
drop table ers_users;
drop table ers_user_roles ;
drop table ers_reimbursement_status;
drop table ers_reimbursement_type ;





create table ers_reimbursement_status (
	reimb_status_id serial primary key,
	reimb_status text
);

insert into ers_reimbursement_status (reimb_status) values ('Pending'),('Approved'), ('Denied');


create table ers_reimbursement_type (
	reimb_type_id serial primary key,
	reimb_type text
);

insert into ers_reimbursement_type (reimb_type) values ('LODGING'),('TRAVEL'), ('FOOD'),('OTHER');


create table ers_user_roles (
	ers_user_role_id serial primary key,
	user_role text
);

insert into ers_user_roles (user_role) values ('Financial Manager'),('Employee');


create table ers_users(
	ers_users_id serial primary key,
	ers_username text unique,
	ers_password text, 
	user_first_name text,
	user_last_name text,
	user_email text unique,
	user_role_id integer references ers_user_roles(ers_user_role_id) check(user_role_id >0 and user_role_id <3)
);

--function to create hash passwords 
create or replace function hashPassword()
returns trigger
as $$
	begin 
		if(new.ers_password = old.ers_password and new.ers_username = old.ers_username)then
			return new;
		end if;
		new.ers_password := md5(new.ers_username||new.ers_password||'candy');
	return new;
	end;
$$ language plpgsql;

--trigger that will call function to hash password when needed
create trigger hashPass
before insert or update on ers_users
for each row
execute function hashPassword();

--function to login by hashing input and matching to 
--hashed value. 
create or replace procedure login(
	username in ers_users.ers_username%type, 
	password in ers_users.ers_password %type,
	user_id inout int
) language plpgsql
as $$
begin 
	password := md5(username||password||'candy');
	select ers_users_id from ers_users where ers_username = username and ers_password = password into user_id;
end;
$$;

--inserted mrs.claus, rudolph, santa
insert into ers_users (ers_username,ers_password,user_first_name,user_last_name,user_email,user_role_id) 
values 
     ('mrsclaus', 'christmas','Mrs.','Claus', 'mrsclaus@northpole.net','1'),
     ('rudolph','christmas','Rudolph','Reindeer','rednose@northpole.net','2'),
     ('santa','christmas','Santa','Claus','santa@northpole.net','2'),
     ('elf', 'christmas', 'Elf', 'Claus', 'elf@northpole.net', '2');

    
create table ers_reimbursement(
	reimb_id serial primary key, --1
	reimb_amount numeric(20,2) not null, --2
	reimb_submitted timestamp not null default now(), --3
	reimb_resolved timestamp default null, --4
	reimb_description text not null, --5
	reimb_receipt bytea default null, --6
	reimb_author integer references ers_users(ers_users_id),--7
 	reimb_resolver integer references ers_users(ers_users_id) default null,--8
	reimb_status_id integer references ers_reimbursement_status(reimb_status_id) default 1, --9
	reimb_type_id integer references ers_reimbursement_type(reimb_type_id) not null --10
);

insert into ers_reimbursement (
		reimb_amount,
		reimb_resolved,
		reimb_description,
		reimb_author,
		reimb_resolver,
		reimb_status_id,
		reimb_type_id
	) 
values 
  (10.00,null,'milk and cookies',3,null,1,3),
  (20.00,now(),'hoof cleaner',2,1,2,4),
  (30.00, now(), 'video games', 4, 1, 3, 4),
  (40.00, null, 'wrapping paper', 3, null, 1, 4);

 
 
 
 --function to add resolvetime 
create or replace function resolutionTime()
returns trigger
as $$
	begin 
		if(new.reimb_resolved = old.reimb_resolved and new.reimb_status_id = old.reimb_status_id)then
			return new;
		end if;
		new.reimb_resolved := now();
	return new;
	end;
$$ language plpgsql;
--trigger that will add resolved timestamp when a ticket is approved/denied
create trigger resolveTime
before update on ers_reimbursement
for each row
execute function resolutionTime();

--select statements for individual tables
select * from ers_user_roles; -- e
select * from ers_reimbursement_type; -- c
select * from ers_reimbursement_status; -- d
select * from ers_users; -- b
select * from ers_reimbursement; -- a


 --view of all values for this project. 
create view total
as select  
	a.reimb_id "Reimbursement ID", --1
	a.reimb_amount "Amount", --2
	a.reimb_submitted "Time Submitted",	--3
	a.reimb_resolved "Time Resolved", --4
	a.reimb_type_id "Type ID", --5
	c.reimb_type "Reimbursment Type",  --6
	a.reimb_description "Description",	--7
	a.reimb_receipt "File",		--8
	a.reimb_status_id "Status ID", --9
	d.reimb_status "Status",	--10
	a.reimb_author "Employee ID", --11
	b.ers_username "Employee Username",	--12
	b.user_first_name "Employee First Name",--13
	b.user_last_name "Employee Last Name", --14
	b.user_email "Employee Email",	--15
	b.user_role_id "Employee Role ID", --16
	e.user_role "Employee Role",  --17
	a.reimb_resolver "Approver ID",	--18
	bb.ers_username "Manager Username",	--19
	bb.user_first_name "Manager First Name",--20
	bb.user_last_name "Manager Last Name", --21
	bb.user_email "Manager Email",	--22
	bb.user_role_id "Manager Role ID", --23
	ee.user_role "Manager Role"	--24
from ers_reimbursement a 		
	join ers_users b on a.reimb_author = b.ers_users_id
	left outer join ers_users bb on a.reimb_resolver = bb.ers_users_id 
	join ers_reimbursement_type c on a.reimb_type_id = c.reimb_type_id
	join ers_reimbursement_status d on a.reimb_status_id = d.reimb_status_id
	join ers_user_roles e on b.user_role_id  = e.ers_user_role_id
	left outer join ers_user_roles ee on bb.user_role_id = ee.ers_user_role_id;

select * from total;

drop view total;
