CREATE UNIQUE INDEX uq_pending_invitation
    ON product_invitation (product_id, target_user_id)
    WHERE status = 'PENDING';