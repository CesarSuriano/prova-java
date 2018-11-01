create table employee(
   id integer ,
   name varchar(255) not null,
   salary double not null,
   primary key(id)
);

create table project(
    id integer,
    name varchar(255) not null,
    primary key(id)
);

create table emp_proj(
  emp_id integer not null,
  proj_id integer not null,

  foreign key(emp_id) references employee(id),
  foreign key(proj_id)references project(id),

  primary key(emp_id, proj_id)
);