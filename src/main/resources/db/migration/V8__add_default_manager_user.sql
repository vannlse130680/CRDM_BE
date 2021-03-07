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

INSERT INTO public.users (id, date_of_birth, name, password, phone, role, username, status)
VALUES (1, '2021-03-08 00:12:24.000000', 'Manager', '$2a$05$D88SWhPyZL9itTaOzDCvMOLstrb6tgqlxbgBKnyP4lUdyX0TJGG3S',
        '0123456789', 'MANAGER', 'manager', 'ACTIVE');

ALTER SEQUENCE public.users_id_seq RESTART WITH 2;