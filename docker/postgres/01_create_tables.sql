CREATE TYPE policy_type AS ENUM ('INDIVIDUAL', 'COLECTIVA');
CREATE TYPE policy_status AS ENUM ('ACTIVA', 'VENCIDA', 'CANCELADA', 'RENOVADA');

CREATE TABLE policies (
    policy_number VARCHAR(50) PRIMARY KEY,
    policy_type policy_type NOT NULL,
    status policy_status NOT NULL,
    start_date DATE NOT NULL,
    policy_duration int NOT NULL,
    monthly_rent DECIMAL(15,2) NOT NULL,
    premium_amount DECIMAL(15,2) NOT NULL,
    auto_renewal BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE risks (
    id SERIAL PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE policy_risks (
    id SERIAL PRIMARY KEY,
    policy_number VARCHAR(50) NOT NULL,
    id_risk int NOT NULL,

    CONSTRAINT fk_policy_risk
        FOREIGN KEY (id_risk)
        REFERENCES risks(id),

    CONSTRAINT uk_policy_risk
        UNIQUE (policy_number, id_risk)
);
