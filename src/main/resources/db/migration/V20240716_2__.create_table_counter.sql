CREATE TABLE counter (
    id SERIAL PRIMARY KEY,
    daily_visitor_count BIGINT DEFAULT 0,
    daily_link_count BIGINT DEFAULT 0,
    monthly_visitor_count BIGINT DEFAULT 0,
    monthly_link_count BIGINT DEFAULT 0,
    total_visitor_count BIGINT DEFAULT 0,
    total_link_count BIGINT DEFAULT 0
);