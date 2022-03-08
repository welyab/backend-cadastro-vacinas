create table vaccine(
    id varchar(36) primary key,
    name varchar(60),
    doses numeric(1),
    batch_number varchar(10)
);

create table applicator(
    id varchar(36) primary key,
    name varchar(60),
    cpf numeric(11),
    coren varchar(10)
);

create table application(
    id varchar(36) primary key,
    person_name varchar(60),
    person_cpf numeric(11),
    person_birth_date date,
    applicator_name varchar(60),
    applicator_cpf numeric(11),
    coren varchar(10),
    vaccine_name varchar(60),
    batch_number varchar(10),
    application_date date
);
