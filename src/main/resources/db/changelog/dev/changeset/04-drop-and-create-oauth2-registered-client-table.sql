-- changeset lukesukhanov:4

SET search_path TO livestock_dev;

-- 'oauth2_registered_client' table
DROP TABLE IF EXISTS oauth2_registered_client CASCADE;
CREATE TABLE oauth2_registered_client (
	id varchar(255) NOT NULL PRIMARY KEY,
	client_id varchar(255) NOT NULL,
    client_id_issued_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    client_secret varchar(255) DEFAULT NULL,
    client_secret_expires_at timestamp DEFAULT NULL,
    client_name varchar(255) NOT NULL,
    client_authentication_methods varchar(1000) NOT NULL,
    authorization_grant_types varchar(1000) NOT NULL,
    redirect_uris varchar(1000) DEFAULT NULL,
    post_logout_redirect_uris varchar(1000) DEFAULT NULL,
    scopes varchar(1000) NOT NULL,
    client_settings varchar(2000) NOT NULL,
    token_settings varchar(2000) NOT NULL,
	created_at timestamp with time zone NOT NULL,
	last_modified_at timestamp with time zone NOT NULL
);
-- rollback DROP TABLE IF EXISTS oauth2_registered_client CASCADE;

-- Trigger for inserting the 'created_at' column
DROP TRIGGER IF EXISTS sync_insert_created_at_column ON oauth2_registered_client;
CREATE TRIGGER sync_insert_created_at_column
	BEFORE INSERT
	ON oauth2_registered_client
	FOR EACH ROW 
	EXECUTE FUNCTION sync_insert_created_at_column();
-- rollback DROP TRIGGER IF EXISTS sync_insert_created_at_column ON oauth2_registered_client;

-- Trigger for updating the 'created_at' column
DROP TRIGGER IF EXISTS sync_update_created_at_column ON oauth2_registered_client;
CREATE TRIGGER sync_update_created_at_column
	BEFORE UPDATE
	ON oauth2_registered_client
	FOR EACH ROW 
	EXECUTE FUNCTION sync_update_created_at_column();
-- rollback DROP TRIGGER IF EXISTS sync_update_created_at_column ON oauth2_registered_client;

-- Trigger for inserting the 'last_modified_at' column
DROP TRIGGER IF EXISTS sync_insert_last_modified_at_column ON oauth2_registered_client;
CREATE TRIGGER sync_insert_last_modified_at_column
	BEFORE INSERT
	ON oauth2_registered_client
	FOR EACH ROW 
	EXECUTE FUNCTION sync_last_modified_at_column();
-- rollback DROP TRIGGER IF EXISTS sync_insert_last_modified_at_column ON oauth2_registered_client;

-- Trigger for updating the 'last_modified_at' column
DROP TRIGGER IF EXISTS sync_update_last_modified_at_column ON oauth2_registered_client;
CREATE TRIGGER sync_update_last_modified_at_column
	BEFORE UPDATE
	ON oauth2_registered_client
	FOR EACH ROW 
	EXECUTE FUNCTION sync_last_modified_at_column();
-- rollback DROP TRIGGER IF EXISTS sync_update_last_modified_at_column ON oauth2_registered_client;