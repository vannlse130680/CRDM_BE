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

create sequence public.hibernate_sequence;

alter sequence public.hibernate_sequence owner to postgres;

create sequence public.material_id_seq;

alter sequence public.material_id_seq owner to postgres;

create sequence public.functions_id_seq
	as integer;

alter sequence public.functions_id_seq owner to postgres;

create sequence public.material_function_id_seq
	as integer;

alter sequence public.material_function_id_seq owner to postgres;

create sequence public.suppliers_id_seq
	as integer;

alter sequence public.suppliers_id_seq owner to postgres;

create sequence public.client_id_seq;

alter sequence public.client_id_seq owner to postgres;

create sequence public.projects_id_seq;

alter sequence public.projects_id_seq owner to postgres;

create sequence public.formula_id_seq
	as integer;

alter sequence public.formula_id_seq owner to postgres;

create sequence public.users_id_seq;

alter sequence public.users_id_seq owner to postgres;

create sequence public.test_cases_id_seq
	as integer;

alter sequence public.test_cases_id_seq owner to postgres;

create sequence public.phases_id_seq
	as integer;

alter sequence public.phases_id_seq owner to postgres;

create sequence public.phase_details_id_seq
	as integer;

alter sequence public.phase_details_id_seq owner to postgres;

create sequence public.formula_details_id_seq
	as integer;

alter sequence public.formula_details_id_seq owner to postgres;

create table public.users
(
	id serial not null
		constraint user_pkey
			primary key,
	date_of_birth timestamp,
	name varchar(255),
	password varchar(255),
	phone varchar(255),
	role varchar(25),
	username varchar(255)
);

alter table public.users owner to postgres;

create table public.clients
(
	id serial not null
		constraint client_pkey
			primary key,
	name varchar(255),
	status varchar(25) not null
);

alter table public.clients owner to postgres;

create table public.projects
(
	id serial not null
		constraint project_pkey
			primary key,
	client_id integer not null
		constraint projects_client_id_fk
			references public.clients
				on update cascade,
	created_date timestamp with time zone not null,
	deadline timestamp with time zone,
	product varchar(255) not null,
	requirement text,
	status varchar(25) not null
);

alter table public.projects owner to postgres;

create table public.project_assignments
(
	id integer not null
		constraint project_assign_pkey
			primary key,
	project_id integer
		constraint project_assignments_projects_pk
			references public.projects,
	user_id integer
		constraint project_assignments_users_pk
			references public.users
);

alter table public.project_assignments owner to postgres;

create unique index project_assign_user_id_project_id_uindex
	on public.project_assignments (user_id, project_id);

create table public.functions
(
	id serial not null
		constraint functions_pk
			primary key,
	name text not null
);

alter table public.functions owner to postgres;

create table public.suppliers
(
	id serial not null
		constraint suppliers_pk
			primary key,
	name varchar(255) not null,
	status varchar(25) not null
);

alter table public.suppliers owner to postgres;

create table public.material
(
	id serial not null
		constraint material_pkey
			primary key,
	currency varchar(255),
	ddp double precision,
	name varchar(255),
	supplier_id integer
		constraint material_suppliers_id_fk
			references public.suppliers
				on update cascade,
	inci_name varchar(255),
	code varchar(255) not null,
	specification_link text,
	supplier_search_link text,
	status varchar(255) not null,
	quality_control_file_link text
);

alter table public.material owner to postgres;

create unique index material_code_uindex
	on public.material (code);

create table public.material_function
(
	id serial not null
		constraint material_function_pk
			primary key,
	function_id integer
		constraint material_function_functions_id_fk
			references public.functions
				on update cascade,
	material_id integer
		constraint material_function_material_id_fk
			references public.material
				on update cascade
);

alter table public.material_function owner to postgres;

create table public.formulas
(
	id serial not null
		constraint formula_versions_pk
			primary key,
	updated_at integer,
	updated_by integer,
	upgraded_from integer,
	outsource_test_file_link text,
	created_at integer,
	created_by integer,
	status varchar(25) not null,
	version_id integer,
	change_note text,
	project_id integer not null
		constraint formulas_projects_id_fk
			references public.projects
				on update cascade
);

alter table public.formulas owner to postgres;

create table public.test_cases
(
	id serial not null
		constraint test_cases_pk
			primary key,
	name varchar(255) not null,
	formula_id integer not null
		constraint test_cases_formulas_id_fk
			references public.formulas
				on update cascade,
	description text,
	status varchar(25) not null
);

alter table public.test_cases owner to postgres;

create table public.phases
(
	id serial not null
		constraint phases_pk
			primary key,
	name varchar(255) not null,
	description text,
	order_number integer not null,
	formula_id integer not null
		constraint phases_formulas_id_fk
			references public.formulas
);

alter table public.phases owner to postgres;

create table public.phase_details
(
	id serial not null
		constraint phase_details_pk
			primary key,
	phase_id integer not null
		constraint phase_details_phases_fk
			references public.phases,
	material_id integer not null
		constraint phase_details_materials_fk
			references public.material
);

alter table public.phase_details owner to postgres;

create table public.formula_details
(
	id serial not null
		constraint formula_details_pk
			primary key,
	formula_id integer not null
		constraint formula_details_formulas_id_fk
			references public.formulas
				on update cascade,
	material_id integer not null
		constraint formula_details_material_id_fk
			references public.material
				on update cascade,
	percentage integer not null
);

alter table public.formula_details owner to postgres;
