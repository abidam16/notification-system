CREATE TABLE product_invitation (
                                    id BIGSERIAL PRIMARY KEY,
                                    product_id BIGINT NOT NULL,
                                    inviter_user_id BIGINT NOT NULL,
                                    target_user_id BIGINT NOT NULL,
                                    requested_role SMALLINT NOT NULL,
                                    status SMALLINT NOT NULL,
                                    message TEXT NULL,
                                    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                    responded_at TIMESTAMP NULL,
                                    expires_at TIMESTAMP NULL,
                                    version INT NOT NULL DEFAULT 0
);

CREATE INDEX idx_product_invitation_target_created
    ON product_invitation (target_user_id, created_at DESC);

CREATE INDEX idx_product_invitation_target_status_created
    ON product_invitation (target_user_id, status, created_at DESC);

CREATE UNIQUE INDEX uq_pending_invitation
    ON product_invitation (product_id, target_user_id)
    WHERE status = 0;

CREATE TABLE user_product_membership (
                                         id BIGSERIAL PRIMARY KEY,
                                         product_id BIGINT NOT NULL,
                                         user_id BIGINT NOT NULL,
                                         role SMALLINT NOT NULL,
                                         granted_by_user_id BIGINT NULL,
                                         granted_via_invitation_id BIGINT NULL,
                                         created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                         CONSTRAINT uq_user_product_membership UNIQUE (product_id, user_id)
);

CREATE TABLE notification (
                              id BIGSERIAL PRIMARY KEY,
                              user_id BIGINT NOT NULL,
                              from_user_id BIGINT NOT NULL,
                              type SMALLINT NOT NULL,
                              reference_id BIGINT NOT NULL,
                              title VARCHAR(255) NOT NULL,
                              body TEXT NULL,
                              status SMALLINT NOT NULL DEFAULT 0,
                              created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              read_at TIMESTAMP NULL
);

CREATE INDEX idx_notification_user_status_created
    ON notification (user_id, status, created_at DESC);

CREATE INDEX idx_notification_user_created
    ON notification (user_id, created_at DESC);

CREATE INDEX idx_notification_user_from_user
    ON notification (user_id, from_user_id, created_at DESC);

CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       email VARCHAR(254) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       name VARCHAR(254) NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP
);

CREATE TABLE product (
                         id BIGSERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP
);

INSERT INTO users(email, password, name)
VALUES ('rawr123@yopmail.com', 'alsdkjflsajkk', 'rawr');

INSERT INTO product(name)
VALUES ('product A');