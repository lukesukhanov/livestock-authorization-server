-- changeset lukesukhanov:5

SET search_path TO livestock_stage;

-- 'oauth2_authorization' table
DROP TABLE IF EXISTS oauth2_authorization CASCADE;
CREATE TABLE oauth2_authorization (
	id varchar(255) NOT NULL PRIMARY KEY,
	registered_client_id varchar(255) NOT NULL,
    principal_name varchar(255) NOT NULL,
    authorization_grant_type varchar(255) NOT NULL,
    authorized_scopes varchar(1000) DEFAULT NULL,
    attributes varchar(4000) DEFAULT NULL,
    state varchar(500) DEFAULT NULL,
    authorization_code_value varchar(4000) DEFAULT NULL,
    authorization_code_issued_at timestamp DEFAULT NULL,
    authorization_code_expires_at timestamp DEFAULT NULL,
    authorization_code_metadata varchar(2000) DEFAULT NULL,
    access_token_value varchar(4000) DEFAULT NULL,
    access_token_issued_at timestamp DEFAULT NULL,
    access_token_expires_at timestamp DEFAULT NULL,
    access_token_metadata varchar(2000) DEFAULT NULL,
    access_token_type varchar(255) DEFAULT NULL,
    access_token_scopes varchar(1000) DEFAULT NULL,
    refresh_token_value varchar(4000) DEFAULT NULL,
    refresh_token_issued_at timestamp DEFAULT NULL,
    refresh_token_expires_at timestamp DEFAULT NULL,
    refresh_token_metadata varchar(2000) DEFAULT NULL,
    oidc_id_token_value varchar(4000) DEFAULT NULL,
    oidc_id_token_issued_at timestamp DEFAULT NULL,
    oidc_id_token_expires_at timestamp DEFAULT NULL,
    oidc_id_token_metadata varchar(2000) DEFAULT NULL,
    oidc_id_token_claims varchar(2000) DEFAULT NULL,
    user_code_value varchar(4000) DEFAULT NULL,
    user_code_issued_at timestamp DEFAULT NULL,
    user_code_expires_at timestamp DEFAULT NULL,
    user_code_metadata varchar(2000) DEFAULT NULL,
    device_code_value varchar(4000) DEFAULT NULL,
    device_code_issued_at timestamp DEFAULT NULL,
    device_code_expires_at timestamp DEFAULT NULL,
    device_code_metadata varchar(2000) DEFAULT NULL,
	created_at timestamp with time zone NOT NULL,
	last_modified_at timestamp with time zone NOT NULL
);
-- rollback DROP TABLE IF EXISTS oauth2_authorization CASCADE;

-- Trigger for inserting the 'created_at' column
DROP TRIGGER IF EXISTS sync_insert_created_at_column ON oauth2_authorization;
CREATE TRIGGER sync_insert_created_at_column
	BEFORE INSERT
	ON oauth2_authorization
	FOR EACH ROW 
	EXECUTE FUNCTION sync_insert_created_at_column();
-- rollback DROP TRIGGER IF EXISTS sync_insert_created_at_column ON oauth2_authorization;

-- Trigger for updating the 'created_at' column
DROP TRIGGER IF EXISTS sync_update_created_at_column ON oauth2_authorization;
CREATE TRIGGER sync_update_created_at_column
	BEFORE UPDATE
	ON oauth2_authorization
	FOR EACH ROW 
	EXECUTE FUNCTION sync_update_created_at_column();
-- rollback DROP TRIGGER IF EXISTS sync_update_created_at_column ON oauth2_authorization;

-- Trigger for inserting the 'last_modified_at' column
DROP TRIGGER IF EXISTS sync_insert_last_modified_at_column ON oauth2_authorization;
CREATE TRIGGER sync_insert_last_modified_at_column
	BEFORE INSERT
	ON oauth2_authorization
	FOR EACH ROW 
	EXECUTE FUNCTION sync_last_modified_at_column();
-- rollback DROP TRIGGER IF EXISTS sync_insert_last_modified_at_column ON oauth2_authorization;

-- Trigger for updating the 'last_modified_at' column
DROP TRIGGER IF EXISTS sync_update_last_modified_at_column ON oauth2_authorization;
CREATE TRIGGER sync_update_last_modified_at_column
	BEFORE UPDATE
	ON oauth2_authorization
	FOR EACH ROW 
	EXECUTE FUNCTION sync_last_modified_at_column();
-- rollback DROP TRIGGER IF EXISTS sync_update_last_modified_at_column ON oauth2_authorization;