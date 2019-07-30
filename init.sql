CREATE SCHEMA IF NOT EXISTS jooq_test_catalog;

CREATE TABLE jooq_test_catalog.user (
  id INTEGER NOT NULL,
  name TEXT NOT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE jooq_test_catalog.thing (
  id INTEGER NOT NULL,
  description TEXT NOT NULL,
  user_id INTEGER NOT NULL,
  timestamp TIMESTAMPTZ NOT NULL,
  FOREIGN KEY (user_id) REFERENCES jooq_test_catalog.user(id) ON DELETE RESTRICT
);