-- changeset lukesukhanov:8

SET search_path TO livestock_dev;

-- 'authorities' table
DROP TABLE IF EXISTS authorities CASCADE;
CREATE TABLE authorities (
	users_id bigint NOT NULL REFERENCES users(id) ON DELETE CASCADE,
	authority varchar NOT NULL,
	created_at timestamp with time zone NOT NULL,
	last_modified_at timestamp with time zone NOT NULL,
	PRIMARY KEY (users_id, authority)
);
-- rollback DROP TABLE IF EXISTS authorities CASCADE;

-- Trigger for inserting the 'created_at' column
DROP TRIGGER IF EXISTS sync_insert_created_at_column ON authorities;
CREATE TRIGGER sync_insert_created_at_column
	BEFORE INSERT
	ON authorities
	FOR EACH ROW 
	EXECUTE FUNCTION sync_insert_created_at_column();
-- rollback DROP TRIGGER IF EXISTS sync_insert_created_at_column ON authorities;

-- Trigger for updating the 'created_at' column
DROP TRIGGER IF EXISTS sync_update_created_at_column ON authorities;
CREATE TRIGGER sync_update_created_at_column
	BEFORE UPDATE
	ON authorities
	FOR EACH ROW 
	EXECUTE FUNCTION sync_update_created_at_column();
-- rollback DROP TRIGGER IF EXISTS sync_update_created_at_column ON authorities;

-- Trigger for inserting the 'last_modified_at' column
DROP TRIGGER IF EXISTS sync_insert_last_modified_at_column ON authorities;
CREATE TRIGGER sync_insert_last_modified_at_column
	BEFORE INSERT
	ON authorities
	FOR EACH ROW 
	EXECUTE FUNCTION sync_last_modified_at_column();
-- rollback DROP TRIGGER IF EXISTS sync_insert_last_modified_at_column ON authorities;

-- Trigger for updating the 'last_modified_at' column
DROP TRIGGER IF EXISTS sync_update_last_modified_at_column ON authorities;
CREATE TRIGGER sync_update_last_modified_at_column
	BEFORE UPDATE
	ON authorities
	FOR EACH ROW 
	EXECUTE FUNCTION sync_last_modified_at_column();
-- rollback DROP TRIGGER IF EXISTS sync_update_last_modified_at_column ON authorities;