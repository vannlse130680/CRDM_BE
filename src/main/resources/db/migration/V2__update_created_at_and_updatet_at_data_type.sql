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

alter table public.formulas drop column updated_at;
alter table public.formulas add updated_at timestamptz not null;

alter table public.formulas drop column created_at;
alter table public.formulas add created_at timestamptz not null;
