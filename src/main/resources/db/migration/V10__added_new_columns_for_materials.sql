SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

create table public.material_types
(
	id serial
		constraint material_types_pk
			primary key,
	type varchar(25) not null
);
alter table public.material_types owner to postgres;

create unique index material_types_type_uindex
	on public.material_types (type);

alter table public.material
	add lead_time varchar(255);

alter table public.material
	add moq text;

alter table public.material
	add "expiryDate" timestamptz;

alter table public.material
	add payment varchar(255);

alter table public.material
	add note text;

alter table public.material
    add type varchar(25) not null;

alter table public.material
    add constraint material_material_types_pk_type_fk
        foreign key (type) references public.material_types (type) on update cascade;
