CREATE TABLE item_rules (
  id            INTEGER PRIMARY KEY,
  item_name     VARCHAR(64) NOT NULL,
  item_count    INTEGER NOT NULL,
  special_price INTEGER NOT NULL);

CREATE TABLE items (
  id        INTEGER PRIMARY KEY,
  name      VARCHAR(64) NOT NULL UNIQUE,
  price     INTEGER NOT NULL);