CREATE SCHEMA IF NOT EXISTS jooq_test_catalog;

CREATE TYPE jooq_test_catalog.prediction AS (
  x1 NUMERIC(5, 4),
  y1 NUMERIC(5, 4),
  x2 NUMERIC(5, 4),
  y2 NUMERIC(5, 4),

  label varchar(20),
  confidence NUMERIC(5, 4)
);

CREATE TYPE jooq_test_catalog.measurement_reference AS (
  source_name VARCHAR(255),
  x1 NUMERIC(5, 4),
  y1 NUMERIC(5, 4),
  x2 NUMERIC(5, 4),
  y2 NUMERIC(5, 4),
  distance_milimeters NUMERIC(8, 4)
);

CREATE TABLE jooq_test_catalog.test_table (
  id INTEGER NOT NULL,
  data jooq_test_catalog.prediction[] NOT NULL,
  data_two jooq_test_catalog.prediction[] NOT NULL
);

CREATE TABLE jooq_test_catalog.test_table_other (
  id INTEGER NOT NULL,
  data jooq_test_catalog.measurement_reference[] NOT NULL,
  data_two jooq_test_catalog.measurement_reference[] NOT NULL
);