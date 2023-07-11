-- changeset lukesukhanov:3

SET search_path TO livestock_shop_dev;

-- 'oauth2_authorization_consent' table
DROP TABLE IF EXISTS oauth2_authorization_consent CASCADE;
CREATE TABLE oauth2_authorization_consent (
    registered_client_id varchar(255) NOT NULL,
    principal_name varchar(255) NOT NULL,
    authorities varchar(1000) NOT NULL,
    created_at timestamp with time zone NOT NULL,
	last_modified_at timestamp with time zone NOT NULL,
    PRIMARY KEY (registered_client_id, principal_name)
);
-- rollback DROP TABLE IF EXISTS oauth2_authorization_consent CASCADE;

-- Trigger for inserting the 'created_at' column
DROP TRIGGER IF EXISTS sync_insert_created_at_column ON oauth2_authorization_consent;
CREATE TRIGGER sync_insert_created_at_column
	BEFORE INSERT
	ON oauth2_authorization_consent
	FOR EACH ROW 
	EXECUTE FUNCTION sync_insert_created_at_column();
-- rollback DROP TRIGGER IF EXISTS sync_insert_created_at_column ON oauth2_authorization_consent;

-- Trigger for updating the 'created_at' column
DROP TRIGGER IF EXISTS sync_update_created_at_column ON oauth2_authorization_consent;
CREATE TRIGGER sync_update_created_at_column
	BEFORE UPDATE
	ON oauth2_authorization_consent
	FOR EACH ROW 
	EXECUTE FUNCTION sync_update_created_at_column();
-- rollback DROP TRIGGER IF EXISTS sync_update_created_at_column ON oauth2_authorization_consent;

-- Trigger for inserting the 'last_modified_at' column
DROP TRIGGER IF EXISTS sync_insert_last_modified_at_column ON oauth2_authorization_consent;
CREATE TRIGGER sync_insert_last_modified_at_column
	BEFORE INSERT
	ON oauth2_authorization_consent
	FOR EACH ROW 
	EXECUTE FUNCTION sync_last_modified_at_column();
-- rollback DROP TRIGGER IF EXISTS sync_insert_last_modified_at_column ON oauth2_authorization_consent;

-- Trigger for updating the 'last_modified_at' column
DROP TRIGGER IF EXISTS sync_update_last_modified_at_column ON oauth2_authorization_consent;
CREATE TRIGGER sync_update_last_modified_at_column
	BEFORE UPDATE
	ON oauth2_authorization_consent
	FOR EACH ROW 
	EXECUTE FUNCTION sync_last_modified_at_column();
-- rollback DROP TRIGGER IF EXISTS sync_update_last_modified_at_column ON oauth2_authorization_consent;