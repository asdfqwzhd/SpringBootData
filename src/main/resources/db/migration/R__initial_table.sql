DROP TABLE IF EXISTS "user";
CREATE TABLE "user"(
  user_id varchar(6) NOT NULL,
  user_name varchar(32),
  company_code varchar(2) NOT NULL,
  company_name varchar(32),
  department_company_code varchar(2),
  department_no varchar(4),
  department_name varchar(32),
  valid_start_day date NOT NULL,
  valid_end_day date NOT NULL,
  insert_date_time timestamp NOT NULL,
  update_date_time timestamp NOT NULL,
  PRIMARY KEY (user_id)
);
