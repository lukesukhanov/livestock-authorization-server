-- changeset lukesukhanov:4

SET search_path TO livestock_shop_dev;

-- 'users' table
DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users (
	id bigint NOT NULL PRIMARY KEY DEFAULT nextval('common_id_seq'),
	email varchar NOT NULL CHECK(email LIKE '__%@__%.__%'),
	password varchar NOT NULL,
	enabled boolean NOT NULL,
	created_at timestamp with time zone NOT NULL,
	last_modified_at timestamp with time zone NOT NULL
);
-- rollback DROP TABLE IF EXISTS users CASCADE;

-- Trigger for inserting the 'created_at' column
DROP TRIGGER IF EXISTS sync_insert_created_at_column ON users;
CREATE TRIGGER sync_insert_created_at_column
	BEFORE INSERT
	ON users
	FOR EACH ROW 
	EXECUTE FUNCTION sync_insert_created_at_column();
-- rollback DROP TRIGGER IF EXISTS sync_insert_created_at_column ON users;

-- Trigger for updating the 'created_at' column
DROP TRIGGER IF EXISTS sync_update_created_at_column ON users;
CREATE TRIGGER sync_update_created_at_column
	BEFORE UPDATE
	ON users
	FOR EACH ROW 
	EXECUTE FUNCTION sync_update_created_at_column();
-- rollback DROP TRIGGER IF EXISTS sync_update_created_at_column ON users;

-- Trigger for inserting the 'last_modified_at' column
DROP TRIGGER IF EXISTS sync_insert_last_modified_at_column ON users;
CREATE TRIGGER sync_insert_last_modified_at_column
	BEFORE INSERT
	ON users
	FOR EACH ROW 
	EXECUTE FUNCTION sync_last_modified_at_column();
-- rollback DROP TRIGGER IF EXISTS sync_insert_last_modified_at_column ON users;

-- Trigger for updating the 'last_modified_at' column
DROP TRIGGER IF EXISTS sync_update_last_modified_at_column ON users;
CREATE TRIGGER sync_update_last_modified_at_column
	BEFORE UPDATE
	ON users
	FOR EACH ROW 
	EXECUTE FUNCTION sync_last_modified_at_column();
-- rollback DROP TRIGGER IF EXISTS sync_update_last_modified_at_column ON users;