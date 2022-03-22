DROP TABLE IF EXISTS "user";
CREATE TABLE "user"(
  user_id varchar(6) NOT NULL,
  user_name varchar(32) NOT NULL,
  PRIMARY KEY (user_id)
);
